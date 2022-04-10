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
            if (((this.opsgenie.getOpsgenieConfig().getMonitoredEndpoints() != null) && (this.opsgenie.isPathPresent(endpoint, this.opsgenie.getOpsgenieConfig().getMonitoredEndpoints()))) ||
                ((this.opsgenie.getOpsgenieConfig().getMonitoredEndpoints() == null) && !((this.opsgenie.getOpsgenieConfig().getIgnoredEndpoints() != null) && (this.opsgenie.isPathPresent(endpoint, this.opsgenie.getOpsgenieConfig().getIgnoredEndpoints()))))) {
                    if ((this.opsgenie.getOpsgenieConfig().getAlertStatusCodes() != null) && (this.opsgenie.getOpsgenieConfig().getAlertStatusCodes().contains(statusCode))) {

                        // raise alert for unwanted status code
                    } else if ((this.opsgenie.getOpsgenieConfig().getAlertStatusClasses() != null) && (this.opsgenie.getOpsgenieConfig().getAlertStatusClasses().contains(statusClass))) {

                        // rasie alert for unwanted status class
                    }
            }
        }

        if ((this.opsgenie.getOpsgenieConfig().getThresholdResponseTime() != -1) && (this.opsgenie.getOpsgenieConfig().getResponseTimeMonitoredEndpoints() != null) &&
                (this.opsgenie.isPathPresent(endpoint, this.opsgenie.getOpsgenieConfig().getResponseTimeMonitoredEndpoints())) &&
                (elapsedTime > this.opsgenie.getOpsgenieConfig().getThresholdResponseTime())) {

                    // raise alert for response latency breach
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
