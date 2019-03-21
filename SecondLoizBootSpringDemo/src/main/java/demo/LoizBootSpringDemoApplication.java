package demo;


import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
//import org.loiz.Demo.dao.UserRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import demo.dao.UserRepository;
import demo.model.User;
@SpringBootApplication
//@Configuration
//@EnableAutoConfiguration
//@ComponentScan({"org.loiz.Demo.controllers","org.loiz.Demo.dao","org.loiz.Demo.model","org.loiz.Demo.Service"})
//@ComponentScan(basePackages = "org.loiz.demo")
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = "demo.dao")
public class LoizBootSpringDemoApplication extends SpringBootServletInitializer 
implements CommandLineRunner
{
	
	@Autowired
	UserRepository objBeanUser ;
	
	public static void main(String[] args) {
		SpringApplication.run(LoizBootSpringDemoApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		objBeanUser.save(new User("prenom1","Nom1")) ;
		objBeanUser.save(new User("prenom2","Nom3")) ;
		objBeanUser.save(new User("prenom3","Nom3")) ;
		
	}
	
	  @Override
	  protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
	      return builder.sources(LoizBootSpringDemoApplication.class);
	  }

}

