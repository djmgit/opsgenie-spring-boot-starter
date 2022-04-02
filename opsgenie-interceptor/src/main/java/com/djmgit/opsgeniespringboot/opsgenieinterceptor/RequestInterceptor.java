package com.djmgit.opsgeniespringboot.opsgenieinterceptor;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.ModelAndView;

public class RequestInterceptor implements HandlerInterceptor {

    private Opsgenie opsgenie;

    public RequestInterceptor() {}

    public RequestInterceptor(Opsgenie opsgenie) {
        this.opsgenie = opsgenie;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        
        System.out.println("Pre Handle method is Calling");
        return true;
    }
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        
        System.out.println("Post Handle method is Calling");
        System.out.println("Sending test alert ...");
        opsgenie.raiseOpsgenieStatusAlert("alertStatusCode", "alertStatusClass");
    }
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception exception) throws Exception {
        
        System.out.println("Request and Response is completed");
    }


    
}
