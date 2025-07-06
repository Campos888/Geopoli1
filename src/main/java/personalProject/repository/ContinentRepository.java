package personalProject.repository;

import org.springframework.data.repository.CrudRepository;

import personalProject.model.Continent;
import personalProject.model.Country;


public interface ContinentRepository extends CrudRepository<Continent,Long>{

}

