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
        
        request.setAttribute("requestStartTime", System.currentTimeMillis());
        return true;
    }
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

        long elapsedTime = System.currentTimeMillis() - (long)request.getAttribute("requestStartTime");
        int statusCode = response.getStatus();
        String statusClass = this.opsgenie.getResponseStatusClass(statusCode);
        String endpoint = request.getRequestURI();

        if (((this.opsgenie.getOpsgenieConfig().getAlertStatusCodes() != null) && (this.opsgenie.getOpsgenieConfig().getAlertStatusCodes().contains(statusCode))) ||
                ((this.opsgenie.getOpsgenieConfig().getAlertStatusClasses() != null) && (this.opsgenie.getOpsgenieConfig().getAlertStatusClasses().contains(statusClass)))) {
                    
        }
        
        //System.out.println("Post Handle method is Calling");
        //System.out.println("Sending test alert ...");
        //opsgenie.raiseOpsgenieStatusAlert("alertStatusCode", "alertStatusClass");
    }
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception exception) throws Exception {
        
        System.out.println("Request and Response is completed");
    }


    
}
