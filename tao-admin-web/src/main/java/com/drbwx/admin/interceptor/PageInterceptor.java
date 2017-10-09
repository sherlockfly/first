package com.drbwx.admin.interceptor;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;

import org.apache.commons.lang.StringUtils;
import org.apache.ibatis.executor.ErrorContext;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.executor.ExecutorException;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.MappedStatement.Builder;
import org.apache.ibatis.mapping.ParameterMapping;
import org.apache.ibatis.mapping.ParameterMode;
import org.apache.ibatis.mapping.SqlSource;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.property.PropertyTokenizer;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.type.TypeHandler;
import org.apache.ibatis.type.TypeHandlerRegistry;

import com.drbwx.admin.common.PageQueryDto;
import com.drbwx.admin.common.ReturnPage;




/**
 * mybatis分页拦截器
 * 创建分页sql，装载分页信息
 * @author ce
 *
 */
//只拦截select部分
@Intercepts( {@Signature(type = Executor.class, method = "query", args = {MappedStatement.class, Object.class,
        RowBounds.class, ResultHandler.class})})
public class PageInterceptor implements Interceptor {
    @Override
	public Object intercept(Invocation invocation) throws Throwable {
        MappedStatement mappedStatement = (MappedStatement) invocation.getArgs()[0];
        Object argObj = invocation.getArgs()[1];
        if(!(argObj instanceof PageQueryDto<?>)) {
            return invocation.proceed();
        }
        PageQueryDto<?> pagination = (PageQueryDto<?>) invocation.getArgs()[1];
        Object paramObj = pagination.getParamObj();
        BoundSql boundSql = mappedStatement.getBoundSql(paramObj);
        if (boundSql == null || StringUtils.isEmpty(boundSql.getSql()))
            return null;
        String originalSql = boundSql.getSql().trim();
        int totalCount = 0;
        StringBuffer countSql = new StringBuffer();
        countSql.append("SELECT COUNT(1) FROM (").append(originalSql).append(") T");
        Connection connection = mappedStatement.getConfiguration().getEnvironment().getDataSource()
                .getConnection();
        PreparedStatement countStmt = connection.prepareStatement(countSql.toString());
        
        BoundSql countBS = new BoundSql(mappedStatement.getConfiguration(), countSql.toString(), boundSql
                .getParameterMappings(), paramObj);
        setParameters(countStmt, mappedStatement, countBS, paramObj);
        ResultSet rs = countStmt.executeQuery();
        if (rs.next()) {
            totalCount = rs.getInt(1);
        }
        rs.close();
        countStmt.close();
        connection.close();
        RowBounds rowBounds = (RowBounds) invocation.getArgs()[2];
        if (rowBounds == null || rowBounds == RowBounds.DEFAULT) {
             rowBounds = new RowBounds(pagination.getPageSize() * (pagination.getCurrentPage() - 1), pagination.getPageSize());
        }

        // 分页查询 本地化对象 修改数据库注意修改实现
        String pagesql = getLimitString(originalSql, rowBounds.getOffset(), rowBounds.getLimit());
        invocation.getArgs()[2] = new RowBounds(RowBounds.NO_ROW_OFFSET, RowBounds.NO_ROW_LIMIT);
        BoundSql newBoundSql = new BoundSql(mappedStatement.getConfiguration(), pagesql, boundSql
                .getParameterMappings(), boundSql.getParameterObject());
        MappedStatement newMs = copyFromMappedStatement(mappedStatement, new BoundSqlSqlSource(newBoundSql));
        invocation.getArgs()[1] = paramObj;
        invocation.getArgs()[0] = newMs;
        Object obj = invocation.proceed();
        if (!(obj instanceof List<?>)) {
            throw new Exception("返回值必须为List");
        }
        ReturnPage<?> retPage = new ReturnPage(totalCount,10,(List<?>) obj);
     
        return retPage;

    }

    public static class BoundSqlSqlSource implements SqlSource {
        BoundSql boundSql;

        public BoundSqlSqlSource(BoundSql boundSql) {
            this.boundSql = boundSql;
        }

        @Override
		public BoundSql getBoundSql(Object parameterObject) {
            return boundSql;
        }
    }

    @Override
	public Object plugin(Object arg0) {
        return Plugin.wrap(arg0, this);
    }

    @Override
	public void setProperties(Properties arg0) {

    }

    /**
     * 对SQL参数(?)设值,参考org.apache.ibatis.executor.parameter.DefaultParameterHandler
     * 
     * @param ps
     * @param mappedStatement
     * @param boundSql
     * @param parameterObject
     * @throws SQLException
     */
    @SuppressWarnings({"rawtypes", "unchecked"})
    private void setParameters(PreparedStatement ps, MappedStatement mappedStatement, BoundSql boundSql,
            Object parameterObject) throws SQLException {
        ErrorContext.instance().activity("setting parameters").object(mappedStatement.getParameterMap().getId());
        List<ParameterMapping> parameterMappings = boundSql.getParameterMappings();
        if (parameterMappings != null) {
            Configuration configuration = mappedStatement.getConfiguration();
            TypeHandlerRegistry typeHandlerRegistry = configuration.getTypeHandlerRegistry();
            MetaObject metaObject = parameterObject == null ? null : configuration.newMetaObject(parameterObject);
            for (int i = 0; i < parameterMappings.size(); i++) {
                ParameterMapping parameterMapping = parameterMappings.get(i);
                if (parameterMapping.getMode() != ParameterMode.OUT) {
                    Object value;
                    String propertyName = parameterMapping.getProperty();
                    PropertyTokenizer prop = new PropertyTokenizer(propertyName);
                    if (parameterObject == null) {
                        value = null;
                    } else if (typeHandlerRegistry.hasTypeHandler(parameterObject.getClass())) {
                        value = parameterObject;
                    } else if (boundSql.hasAdditionalParameter(propertyName)) {
                        value = boundSql.getAdditionalParameter(propertyName);
                    } /*else if (propertyName.startsWith(ForEachSqlNode.ITEM_PREFIX)
                            && boundSql.hasAdditionalParameter(prop.getName())) {
                        value = boundSql.getAdditionalParameter(prop.getName());
                        if (value != null) {
                            value = configuration.newMetaObject(value).getValue(
                                    propertyName.substring(prop.getName().length()));
                        }
                    } */else {
                        value = metaObject == null ? null : metaObject.getValue(propertyName);
                    }
                    TypeHandler typeHandler = parameterMapping.getTypeHandler();
                    if (typeHandler == null) {
                        throw new ExecutorException("There was no TypeHandler found for parameter " + propertyName
                                + " of statement " + mappedStatement.getId());
                    }
                    typeHandler.setParameter(ps, i + 1, value, parameterMapping.getJdbcType());
                }
            }
        }
    }

    private MappedStatement copyFromMappedStatement(MappedStatement ms, SqlSource newSqlSource) {
        Builder builder = new MappedStatement.Builder(ms.getConfiguration(), ms.getId(), newSqlSource, ms
                .getSqlCommandType());
        builder.resource(ms.getResource());
        builder.fetchSize(ms.getFetchSize());
        builder.statementType(ms.getStatementType());
        builder.keyGenerator(ms.getKeyGenerator());
        //builder.keyProperty(ms.getKeyProperty());
        builder.timeout(ms.getTimeout());
        builder.parameterMap(ms.getParameterMap());
        builder.resultMaps(ms.getResultMaps());
        builder.resultSetType(ms.getResultSetType());
        builder.cache(ms.getCache());
        builder.flushCacheRequired(ms.isFlushCacheRequired());
        builder.useCache(ms.isUseCache());

        return builder.build();
    }

    /**
     * 取分页对象Pagination
     * @param obj
     * @return Pagination
     */
    @SuppressWarnings("rawtypes")
    private Object getSqlParam(Object obj) {
        if (obj!=null) {
            Class objClass =obj.getClass();
            try {
                Field fd=objClass.getDeclaredField("paramObj");
                fd.setAccessible( true ); // 设置些属性是可以访问的
                Object val = fd.get(obj); // 得到此属性的值  
                return val;
            }catch (Exception e) {
                e.printStackTrace();
            }
           /* Map map = (Map) obj;
            if(map.get("0")!=null && map.get("0") instanceof Pagination){
                return map.get("0");
            }*/
            /**
            if(map.get(EapConstant.PAGINATION)!=null && map.get(EapConstant.PAGINATION) instanceof Pagination){
                return map.get(EapConstant.PAGINATION);
            }**/
        } else if (obj instanceof PageQueryDto) {
            return obj;
        } 
        return null;

    }
    
    /**
     * 得到分页的SQL
     * @param offset    偏移量
     * @param limit     位置
     * @return  分页SQL
     */
    private String getLimitString(String querySelect,int offset, int limit) {
        if(StringUtils.isEmpty(querySelect)){
            return querySelect;
        }
        querySelect = getLineSql(querySelect);
        //String sql =  querySelect.replaceAll("[^\\s,]+\\.", "") +" limit "+ offset +" ,"+ limit;
        //oracle sql
        //String sql ="SELECT * FROM (SELECT a.*, ROWNUM rn FROM ("+querySelect +")a WHERE ROWNUM <= "+limit+") WHERE rn >"+offset;
        // mysql sql
        String sql = querySelect + " LIMIT " +offset +","+ limit;
   System.out.println(sql);     
        return sql;
        
    }
    
    /**
     * 将SQL语句变成一条语句，并且每个单词的间隔都是1个空格
     * @param sql SQL语句
     * @return 如果sql是NULL返回空，否则返回转化后的SQL
     */
    private String getLineSql(String sql) {
        return sql.replaceAll("[\r\n]", " ").replaceAll("\\s{2,}", " ");
    }
}