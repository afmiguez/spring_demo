package spring_demo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import spring_demo.models.Person;
import spring_demo.repositories.PersonRepository;

@Service
public class PersonService implements PersonServiceI {

	@Autowired
	private PersonRepository pr;

	@Override
	public Person savePerson(Person p) {
		return pr.save(p);
	}

	@Override
	public List<Person> getAll() {
		return (List<Person>) pr.findAll();
	}

	@Override
	public Person getPersonById(Integer id) {
		return pr.findOne(id);
	}

	@Override
	public boolean updatePerson(Person p) {
		return pr.save(p) != null;

	}

	@Override
	public boolean deletePerson(Person p) {
		try {
			pr.delete(p);
			return true;
		} catch (IllegalArgumentException iae) {
			return false;
		}
	}

	@Override
	public Long countPerson() {
		return pr.count();
	}

	@Override
	public boolean existsPerson(Person p) {
		return pr.findByEmail(p.getEmail())!=null;
	}

}
