package spring_demo.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import spring_demo.models.Person;

@Repository
public interface PersonRepository extends CrudRepository<Person,Integer>{
	public Person findByEmail(String email);
}
