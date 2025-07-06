package personalProject.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import jakarta.transaction.Transactional;
import personalProject.model.Country;


public interface CountryRepository extends CrudRepository<Country,Long>{

}
