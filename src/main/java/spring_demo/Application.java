package spring_demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import spring_demo.models.Person;
import spring_demo.services.PersonService;

@SpringBootApplication
public class Application {

	private static ConfigurableApplicationContext context;
	private final static Logger logger = LoggerFactory.getLogger(Application.class);

	public static void main(String[] args) throws Exception {
		context = SpringApplication.run(Application.class, args);
		testService(context);
		
	}
	
	private static void testService(ConfigurableApplicationContext context) {
		PersonService ps = context.getBean(PersonService.class);

		logger.info("Aplicação arrancou");
		logger.info("" + ps.countPerson());
		ps.savePerson(new Person("name1", "lastname1", "email1"));
		logger.info("" + ps.countPerson());
		logger.info("" + ps.getAll());
		Person toDelete = ps.getPersonById(1);
		logger.info("" + toDelete);
		ps.deletePerson(toDelete);
		logger.info("" + ps.countPerson());
	}
}
