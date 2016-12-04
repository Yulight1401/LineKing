package com.callmefather.controller;

import org.springframework.http.HttpHeaders;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by xinliu on 12/3/16.
 */
public class ControllerUtil {
    public static String getParam(HttpServletRequest request, String key, String defaultValue) {
        String parameter = request.getParameter(key);
        return (parameter != null) ? parameter : defaultValue;
    }

    public static void setState(boolean success, HttpServletResponse response) {
        int stat = success ? HttpServletResponse.SC_OK : HttpServletResponse.SC_BAD_REQUEST;
        response.setHeader(HttpHeaders.ACCESS_CONTROL_ALLOW_ORIGIN,"*");
        response.setStatus(stat);
    }
}
