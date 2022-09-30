package solent.oodev.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

@Slf4j
public class WebAppInitializer implements WebApplicationInitializer {

    private static final String DISPATCHER_SERVLET_NAME = "dispatcher";
    @Override
    public void onStartup(javax.servlet.ServletContext servletContext) throws ServletException {
        log.info("Application Started");
        //  Create Web Application context
        AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();
        context.register(WebConfig.class);

        //  Create the Dispather servlet

        DispatcherServlet dispatcherServlet = new DispatcherServlet(context);

        //  Register and configured the dispatcher servlet

        ServletRegistration.Dynamic registration =
                servletContext.addServlet(DISPATCHER_SERVLET_NAME, dispatcherServlet);

        registration.setLoadOnStartup(1);

        registration.addMapping("/");
    }
}
