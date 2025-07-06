package personalProject.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import personalProject.model.Continent;
import personalProject.model.Country;
import personalProject.repository.ContinentRepository;

@Service
public class ContinentService {

	@Autowired
	private ContinentRepository continentRepository;
	
	public Continent getContinentById(Long id) {
		return continentRepository.findById(id).get();
	}
	
	public Iterable<Continent> getAllContinents(){
		return continentRepository.findAll();
	}
	
	public Continent save(Continent continent) {
        return continentRepository.save(continent); 
    }

	public Iterable<Country> getAllCountries(Long continentId) {
        Optional<Continent> continent = continentRepository.findById(continentId);
        return continent.map(Continent::getCountries).orElse(List.of());
    }

	
	
}
