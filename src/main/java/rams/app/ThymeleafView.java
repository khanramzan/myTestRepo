package rams.app;


import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.annotation.Resource;

import nz.net.ultraq.thymeleaf.LayoutDialect;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Description;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.core.env.Environment;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;
import org.thymeleaf.dialect.IDialect;
import org.thymeleaf.extras.springsecurity3.dialect.SpringSecurityDialect;
import org.thymeleaf.spring4.SpringTemplateEngine;
import org.thymeleaf.spring4.view.ThymeleafViewResolver;
import org.thymeleaf.templateresolver.ITemplateResolver;
import org.thymeleaf.templateresolver.ServletContextTemplateResolver;



@Configuration
public class ThymeleafView {
	
	@Resource
    private Environment environment;
	private static final String PROPERTY_NAME_MESSAGESOURCE_BASENAME = "message.source.basename";
    private static final String PROPERTY_NAME_MESSAGESOURCE_USE_CODE_AS_DEFAULT_MESSAGE = "message.source.use.code.as.default.message";

	
    @Bean
    public SpringTemplateEngine templateEngine() {
        SpringTemplateEngine engine = new SpringTemplateEngine();
        Set<IDialect> dialects = new HashSet<IDialect>();
        dialects.add(new SpringSecurityDialect());
        dialects.add(new LayoutDialect());
        engine.setAdditionalDialects(dialects);
        LinkedHashSet<ITemplateResolver> templateResolvers = new LinkedHashSet<ITemplateResolver>(2);
        templateResolvers.add(templateResolverServlet());
        templateResolvers.add(layoutTemplateResolverServlet());
        engine.setTemplateResolvers(templateResolvers);
        return engine;
    }

    @Bean
    public ServletContextTemplateResolver layoutTemplateResolverServlet() {
        ServletContextTemplateResolver templateResolver = new ServletContextTemplateResolver();
        templateResolver.setPrefix("/WEB-INF/templates/");
        templateResolver.setSuffix(".html");
        //templateResolver.setTemplateMode("LEGACYHTML5");
        templateResolver.setOrder(1);
        
        templateResolver.setCacheable(false);
        
        
        
        
        return templateResolver;
    }
    
    
    @Bean
    public ServletContextTemplateResolver templateResolverServlet() {
        ServletContextTemplateResolver templateResolver = new ServletContextTemplateResolver();
        templateResolver.setPrefix("/WEB-INF/pages/");
        templateResolver.setSuffix(".jsp");
        //templateResolver.setTemplateMode("LEGACYHTML5");
        templateResolver.setOrder(2);
        templateResolver.setCacheable(false);
        return templateResolver;
    }

    @Bean
    public ViewResolver thymeleafViewResolver() {
    	InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setViewClass(JstlView.class);
        resolver.setOrder(1);
        resolver.setCache(false);
        return resolver;
    }
    
    @Bean
    @Description("Spring message resolver")
    public ResourceBundleMessageSource messageSource() {  
        ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();  
        messageSource.setBasename("i18n/messages");  
        
        return messageSource;  
    }
    
    
   
}