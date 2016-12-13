package spring_demo.controllers;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import spring_demo.models.Person;
import spring_demo.services.PersonServiceI;

@Controller
@RequestMapping("/person")
public class PersonController {
	@Autowired
	private PersonServiceI ps;
	private final static Logger logger = LoggerFactory.getLogger(PersonController.class);

	@RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<List<Person>> getAllPeople() {
		return ResponseEntity.ok(ps.getAll());
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<Person> getAllPeople(@PathVariable("id") Integer id) {
		return ResponseEntity.ok(ps.getPersonById(id));
	}

	@RequestMapping(method = RequestMethod.POST, consumes = {
			MediaType.APPLICATION_JSON_VALUE }, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<Person> createPerson(@RequestBody Person p) {
		if (!ps.existsPerson(p)) {
			return ResponseEntity.ok(ps.savePerson(p));
		} else {
			return ResponseEntity.badRequest().body(null);
		}
	}
	@RequestMapping(value = "/form", method = RequestMethod.POST, consumes = {
			MediaType.APPLICATION_FORM_URLENCODED_VALUE }, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<Person> createPersonForm(Person p) {
		if (!ps.existsPerson(p)) {
			return ResponseEntity.ok(ps.savePerson(p));
		} else {
			return ResponseEntity.badRequest().body(null);
		}
	}

	@RequestMapping(method = RequestMethod.PUT, consumes = {
			MediaType.APPLICATION_JSON_VALUE }, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<Person> updatePerson(@RequestBody Person p) {
		logger.info("" + p);
		return ResponseEntity.ok(ps.savePerson(p));
	}

	@RequestMapping(value = "/form", method = RequestMethod.PUT, consumes = {
			MediaType.APPLICATION_FORM_URLENCODED_VALUE }, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<Person> updatePersonForm(Person p) {
		logger.info("" + p);
		return ResponseEntity.ok(ps.savePerson(p));
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE, consumes = {
			MediaType.APPLICATION_JSON_VALUE }, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<Person> deletePersonById(@PathVariable("id") Integer id) {
		logger.info("" + id);
		try {
			ps.deletePerson(ps.getPersonById(id));
			return ResponseEntity.accepted().body(null);
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(null);
		}
	}

	@RequestMapping(value = "/{id}/form", method = RequestMethod.DELETE, consumes = {
			MediaType.APPLICATION_FORM_URLENCODED_VALUE }, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<Person> deletePersonByIDForm(@PathVariable("id") Integer id) {
		logger.info("" + id);
		try {
			ps.deletePerson(ps.getPersonById(id));
			return ResponseEntity.accepted().body(null);
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(null);
		}
	}

	@RequestMapping(method = RequestMethod.DELETE, consumes = {
			MediaType.APPLICATION_JSON_VALUE }, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<Person> deletePerson(@RequestBody Person p) {
		logger.info("" + p);
		ps.deletePerson(p);
		return ResponseEntity.accepted().body(null);

	}

	@RequestMapping(value = "/form", method = RequestMethod.DELETE, consumes = {
			MediaType.APPLICATION_FORM_URLENCODED_VALUE }, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<Person> deletePersonForm(Person p) {
		logger.info("" + p);
		ps.deletePerson(p);
		return ResponseEntity.accepted().body(null);

	}

}
