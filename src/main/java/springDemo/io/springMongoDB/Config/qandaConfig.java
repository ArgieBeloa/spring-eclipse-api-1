package springDemo.io.springMongoDB.Config;


import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class qandaConfig implements  WebMvcConfigurer{

	
	
	 @Override
	    public void addCorsMappings(CorsRegistry registry) {
	        registry.addMapping("/**") // Allow all endpoints
	                .allowedOrigins("*") // Allow all origins
	                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") // Allow all HTTP methods
	                .allowedHeaders("*"); // Allow all headers
	    }
}
