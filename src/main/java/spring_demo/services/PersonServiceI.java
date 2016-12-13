package spring_demo.services;

import java.util.List;

import spring_demo.models.Person;

public interface PersonServiceI {
	public Person savePerson(Person p);
	
	public List<Person> getAll();
	
	public Person getPersonById(Integer id);
	
	public boolean updatePerson(Person p);
	
	public boolean deletePerson(Person p);
	
	public Long countPerson();
	
	public boolean existsPerson(Person p);
}
