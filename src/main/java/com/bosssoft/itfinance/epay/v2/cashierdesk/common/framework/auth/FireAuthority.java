package com.bosssoft.itfinance.epay.v2.cashierdesk.common.framework.auth;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface FireAuthority
{
	
	LoginStatus loginStatus() default LoginStatus.NO_LOGIN;
	
	
	
}
