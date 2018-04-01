package springmvc_example.config;

import javax.naming.NamingException;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jndi.JndiTemplate;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages="springmvc_example")
public class WebConfig {
	@Autowired
	DataSource dataSource;
	
	@Bean
	public NamedParameterJdbcTemplate geParameterJdbcTemplate(){
		return new NamedParameterJdbcTemplate(dataSource);
	}
	
	@Bean
	public DataSource getDataSource() throws NamingException{
		JndiTemplate jndiTemplate = new JndiTemplate();
		DataSource dataSource=(DataSource) jndiTemplate.lookup("java:comp/env/jdbc/springmvc");
		return dataSource;
	}
	public void addReSourceHandlers(ResourceHandlerRegistry registry){
		registry.addResourceHandler("/resource/**").addResourceLocations("/resources/");
	}
	
	@Bean
	public InternalResourceViewResolver viewResolver(){
		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
		viewResolver.setViewClass(JstlView.class);
		viewResolver.setPrefix("/WEB_INF/jsp/");
		viewResolver.setSuffix(".jsp");
		return viewResolver;
	}

}
