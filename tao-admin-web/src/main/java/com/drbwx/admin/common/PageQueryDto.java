package com.drbwx.admin.common;


/**
 * 分页对象
 * @author ce
 *
 */
public class PageQueryDto<T> implements java.io.Serializable {
    
    private static final long serialVersionUID = 1L;
    
    private int DEFAULT_PAGE_SIZE = 10;
    private int DEFAULT_CURRENTPAGE = 1;
    
    private int pageSize; // 每页默认10条数据
    private int currentPage; // 当前页

    private T paramObj;
    
    public PageQueryDto(T paramObj) {
        this.paramObj = paramObj;
        this.pageSize = DEFAULT_PAGE_SIZE;
        this.currentPage = DEFAULT_CURRENTPAGE;
    }
    
    public PageQueryDto(T paramObj,Integer currentPage,Integer pageSize){
    	if(currentPage==null || currentPage == 0){
    		currentPage = 1;
    	}
    	if(pageSize==null || pageSize == 0){
    		pageSize = 10;
    	}
        this.paramObj = paramObj;
        this.pageSize = pageSize;
        this.currentPage = currentPage;    	
    }

    /**
     * 初始化分页参数:需要先设置totalRows
     */
    public void init(T paramObj,int pageSize, int currentPage) {
        this.paramObj = paramObj;
        this.currentPage = currentPage;
        this.pageSize = pageSize;        
    }
    public T getParamObj() {
        return paramObj;
    }

    public void setParamObj(T paramObj) {
        this.paramObj = paramObj;
    }



    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }


}
