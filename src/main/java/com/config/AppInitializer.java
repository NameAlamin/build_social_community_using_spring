package com.config;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.multipart.support.MultipartFilter;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.*;

public class AppInitializer implements WebApplicationInitializer {
    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        // ------------------ region RootContext creation and registration ----------------------
        AnnotationConfigWebApplicationContext rootContext1 = new AnnotationConfigWebApplicationContext();
        rootContext1.register(RootConfig.class,DbConfig.class);
        rootContext1.refresh();

        servletContext.addListener(new ContextLoaderListener(rootContext1));
        // ------------------- endregion RootContext creation and registration ----------------------



        // ------------------ region ServletContext creation and registration ----------------------
        AnnotationConfigWebApplicationContext servletRegisterer = new AnnotationConfigWebApplicationContext();
        servletRegisterer.register(ServletConfig.class);

        // Multipart Config
//        ServletRegistration.Dynamic servletRegistration = servletContext.addServlet("servlet", new DispatcherServlet(servletRegisterer));
//        servletRegistration.setMultipartConfig(new MultipartConfigElement("/", 2097152, 4194304, 50));

        // Multipart Filter Config
//        FilterRegistration.Dynamic multipartFilter = servletContext.addFilter("multipartFilter", MultipartFilter.class);
//        multipartFilter.addMappingForUrlPatterns(null, true, "/*");


        ServletRegistration.Dynamic registration = servletContext.addServlet("servlet",
                new DispatcherServlet(servletRegisterer));
        // ------------------ endregion ServletContext creation and registration ----------------------

        registration.setLoadOnStartup(1);
        registration.addMapping("/");
    }
}
