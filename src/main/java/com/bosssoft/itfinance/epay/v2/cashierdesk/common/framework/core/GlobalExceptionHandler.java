package com.bosssoft.itfinance.epay.v2.cashierdesk.common.framework.core;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bosssoft.itfinance.epay.v2.cashierdesk.common.framework.exception.DeniedException;
import com.bosssoft.itfinance.epay.v2.teamcommon.BaseException;

@ControllerAdvice
public class GlobalExceptionHandler {

	private final static Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class.getName());

	@ExceptionHandler(Exception.class)
	@ResponseBody
	public MsgResult handleException(Exception ex, HttpServletRequest request) {
		if (ex.getClass().getSimpleName().equals("ClientAbortException")) {
			// 无视客户端强制断开异常
		} else {
			logger.error(ex.getMessage(), ex);
		}
		return new MsgResult(Status.ServerException.toString(), new BusinessMsg("0", ex.getMessage()));
	}

	@ExceptionHandler(BaseException.class)
	@ResponseBody
	public MsgResult handleBusinessException(BaseException ex, HttpServletRequest request) {
		logger.info(ex.getMessage());
		return new MsgResult(Status.BusinessException.toString(), new BusinessMsg(ex.getErrCode(), ex.getMessage()));
	}

	@ExceptionHandler(DeniedException.class)
	@ResponseBody
	public MsgResult handleDeniedException(Exception ex, HttpServletRequest request, HttpServletResponse response) {
		logger.debug(ex.getMessage(), ex);
		// 清除客户端信息
		clearCientInfo(request, response);
		return new MsgResult(Status.DeniedException.toString(), new BusinessMsg("0", ex.getMessage()));
	}

	@ExceptionHandler(BindException.class)
	@ResponseBody
	public MsgResult handleBindException(BindException ex, HttpServletRequest request) {
		BindingResult bindingResult = ex.getBindingResult();
		FieldError fieldError = bindingResult.getFieldError();
		return new MsgResult("0", new BusinessMsg("0", fieldError.getField() + ":" + fieldError.getDefaultMessage()));
	}

	private void clearCientInfo(HttpServletRequest request, HttpServletResponse response) {
		Cookie[] cookies = request.getCookies();
		if(cookies!=null){
			for (Cookie cookie : cookies) {
				cookie.setMaxAge(0);
				cookie.setValue("");
				cookie.setDomain(".bosssoft.com");
				cookie.setPath("/");
				response.addCookie(cookie);
			}
		}
	}

}
