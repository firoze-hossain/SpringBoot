package com.roze;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class MyApp {
   // @Value("roze")
    //read the the value from properties dynamically
    @Value("${app.name}")
    private String appName;

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }
}
