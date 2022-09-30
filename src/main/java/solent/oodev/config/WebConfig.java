package solent.oodev.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import solent.oodev.model.classes.Cart;
import solent.oodev.model.classes.user.User;
import solent.oodev.utils.ViewNames;
import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.UrlBasedViewResolver;
import javax.sql.DataSource;

@EnableWebMvc
@Configuration
@ComponentScan(basePackages = "solent.oodev")
@PropertySource("classpath:store_db.properties")
public class WebConfig implements WebMvcConfigurer {
    //  constants
    public static final String RESOLVER_PREFIX = "WEB-INF/view/";
    public static final String RESOLVER_SUFFIX = ".jsp";

    @Value("${jdbc.driverClassName}")
    private String driverClassName;
    @Value("${jdbc.username}")
    private String userName;
    @Value("${jdbc.password}")
    private String password;
    @Value("${jdbc.url}")
    private String url;

    // Setting up serving the static content
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/**")
                .addResourceLocations("classpath:/static/","classpath:/image/")
                .setCachePeriod(0);
    }

    //  Bean methods

    @Bean
    public ViewResolver viewResolver() {
        UrlBasedViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setPrefix(RESOLVER_PREFIX);
        viewResolver.setSuffix(RESOLVER_SUFFIX);
        return viewResolver;
    }

    @Bean
    public DataSource dataSource() {
        BasicDataSource dataSource = new BasicDataSource();

        dataSource.setUsername(userName);
        dataSource.setPassword(password);
        dataSource.setUrl(url);
        dataSource.setDriverClassName(driverClassName);
        return dataSource;
    }

    @Bean
    public JdbcTemplate jdbcTemplate(@Autowired DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }

    @Bean
    public Cart cart() {
        return new Cart();
    }

    @Bean
    public User loggedUser() {
        return new User();
    }

    @Bean
    public String message() {
        return "";
    }


    // >>> static paths handlers
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        //  >>> home path
        registry.addViewController("/").setViewName(ViewNames.HOME);
        //  >>> contact path
        registry.addViewController("contact").setViewName(ViewNames.CONTACT);
        //  >>> about path
        registry.addViewController("about").setViewName(ViewNames.ABOUT);
    }

}
