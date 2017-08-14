/**
 * 
 */
package com.imooc.web.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.boot.autoconfigure.web.DefaultErrorAttributes;
import org.springframework.boot.autoconfigure.web.ErrorAttributes;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.imooc.validator.ValidateException;

/**
 * @author zhailiang
 *
 */
@ControllerAdvice
public class ControllerExceptionHandler {

	private ErrorAttributes errorAttributes = new DefaultErrorAttributes();

	@ExceptionHandler(ValidateException.class)
	@ResponseBody
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public Map<String, Object> handleValidateException(HttpServletRequest request, ValidateException ex) {
		System.out.println("处理异常");
		RequestAttributes requestAttributes = new ServletRequestAttributes(request);
		Map<String, Object> result = this.errorAttributes.getErrorAttributes(requestAttributes, false);
		result.put("details", ex.getErrors());
		result.put("status", 500);
		return result;
	}

}
