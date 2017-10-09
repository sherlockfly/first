package com.drbwx.admin.utils;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import com.drbwx.admin.common.RespCode;
import com.drbwx.admin.exceptions.BusinessRuntimeException;

public class ValidatorUtil {
	/**
	 * 输入参数验证
	 * 
	 * @param business
	 * @author david.feng
	 * @creation 2014-7-8 上午10:54:29
	 */
	public static <T> void checkParameter(T t) throws BusinessRuntimeException {
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		Validator validator = factory.getValidator();
		Set<ConstraintViolation<T>> constraintViolations = validator.validate(t);
		if (!constraintViolations.isEmpty()) {
			String message = constraintViolations.iterator().next().getMessage();
			BusinessRuntimeException be = new BusinessRuntimeException();
			be.setErrorCode(RespCode.E_0003.getCode());
			be.setErrorDesc(RespCode.E_0003.getName() + "," + message);
			throw be;
		}
	}
	
}
