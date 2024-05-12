package com.secondhand.presentationadvertapi.common;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@ApiImplicitParams({@ApiImplicitParam(
        name = "X-UserEmail",
        value = "Request needs valid user email.",
        paramType = "header",
        dataTypeClass = String.class,
        required = true
)})
public @interface AuthenticatedUserEmail {
}
