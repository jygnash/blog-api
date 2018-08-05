package click.blogs.blogapi;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
@EnableAutoConfiguration
@EnableGlobalMethodSecurity
@ComponentScan(basePackages = {"click.blogs.blogapi.web","click.blogs.blogapi.services"})
@EntityScan(basePackages = {"click.blogs.blogapi.model"})
@EnableJpaRepositories(basePackages = {"click.blogs.blogapi.respository"})
@EnableWebMvc
@Import({SecurityConfig.class})
@SpringBootApplication
public class BlogApiApplication  {
	private static final Logger logger = LogManager.getLogger(BlogApiApplication.class);
	
	@Value("${security.user.password}")
	private String basicAuth;
	
	public static void main(String[] args) {
		SpringApplication.run(BlogApiApplication.class, args);
	}
	
	@Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurerAdapter() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**").allowedMethods("*").allowedHeaders("*").allowedOrigins("*");
            }
        };
    }
	
	@Bean(name="basicAuth")
	public String getBasicAuth() {
		return this.basicAuth;
	}
}
