package com.roze.initializer;

import com.roze.config.HelloWorldConfig;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class HelloWorldAppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[0];
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[]{HelloWorldConfig.class};
    }

    @Override
    protected String[] getServletMappings() {
        return new String[]{"/myapp.com/*"};
    }
}
