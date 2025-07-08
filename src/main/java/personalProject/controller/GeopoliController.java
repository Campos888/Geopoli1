package personalProject.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import jakarta.validation.Valid;

import personalProject.model.Comment;
import personalProject.model.Continent;
import personalProject.model.Country;
import personalProject.service.CommentService;
import personalProject.service.ContinentService;
import personalProject.service.CountryService;

@Controller
public class GeopoliController {

	@Autowired
	CountryService countryService;
	@Autowired
	ContinentService continentService;
	@Autowired
	CommentService commentService;
	@Autowired
	CountryService countriesService;
	

	
	
	@GetMapping("/")
    public String index(Model model) {
        return "indexGeopoli.html"; // must match one of your templates (e.g., movies.html)
    }
	
	@GetMapping("/continents")
	public String showContinents(Model model) {
		model.addAttribute("continents", this.continentService.getAllContinents());
		return "continents.html";
	}
	
	
	
	@GetMapping("/continentCountries/{continentId}")
	public String showContinentCountries(@PathVariable("continentId") Long continentId, Model model) {
		model.addAttribute("continent", this.continentService.getContinentById(continentId));
		model.addAttribute("continentCountries", this.continentService.getAllCountries(continentId));
		return "countries.html";
	}
	
	
	@GetMapping("/country/{id}")
	public String getCountry(@PathVariable("id") Long id, Model model) {
	    Country country = countryService.getCountryById(id);
	    model.addAttribute("country", country);
	    return "country.html";
	}

	
	
	
	
	@GetMapping("/admin/formUpdateCountryImage/{countryId}")
    public String formUpdateCountryImage(@PathVariable("countryId") Long countryId, Model model) {
		model.addAttribute("country", this.countryService.getCountryById(countryId));
		return "formUpdateCountryImage.html";
    }
	
	
	
	
	
	
	@PostMapping("/admin/updateCountryImage")
	public String updateCountryImage(@RequestParam("id") Long id,
	                                 @RequestParam("urlImage") String urlImage,
	                                 Model model) {
	    Country country = countryService.getCountryById(id);
	    // Update and save the new image URL
	    country.setUrlImage(urlImage);
	    countryService.save(country);

	    // Redirect to the updated country's detail page
	    return "redirect:/country/" + id;
	}
	
	//---------------
	
	
	@GetMapping("/admin/formUpdateCountry/{countryId}")
	public String formUpdateCountry(@PathVariable("countryId") Long countryId, Model model) {
		model.addAttribute("country", this.countryService.getCountryById(countryId));
		return "formUpdateCountry.html";
	}
	
	
	
	@PostMapping("/admin/updateCountry")
	public String updateCountry(@RequestParam("id") Long id,
	                          @RequestParam("name") String name,
	                          @RequestParam("code") String code,
	                          @RequestParam(value = "urlImage", required = false) String urlImage,
	                          // Key features
	                          @RequestParam(value = "population", required = false) Integer population,
	                          @RequestParam(value = "capital", required = false) String capital,
	                          @RequestParam(value = "area", required = false) Integer area,
	                          @RequestParam(value = "currency", required = false) String currency,
	                          @RequestParam(value = "government", required = false) String government,
	                          @RequestParam(value = "current_temperautre", required = false) Integer current_temperautre,
	                          @RequestParam(value = "conflict_status", required = false) String conflict_status,
	                          // Metrics (0-100)
	                          @RequestParam(value = "political_stability", required = false) Integer political_stability,
	                          @RequestParam(value = "healthcare", required = false) Integer healthcare,
	                          @RequestParam(value = "education", required = false) Integer education,
	                          @RequestParam(value = "envaironment", required = false) Integer envaironment,
	                          @RequestParam(value = "military_strength", required = false) Integer military_strength,
	                          @RequestParam(value = "wealth", required = false) Integer wealth,
	                          @RequestParam(value = "travel_safty", required = false) Integer travel_safty,
	                          Model model) {
	    
	    // Get the existing country
	    Country country = countryService.getCountryById(id);
	    
	    // Update basic information
	    country.setName(name);
	    country.setCode(code);
	    country.setUrlImage(urlImage);
	    
	    // Update key features
	    country.setPopulation(population);
	    country.setCapital(capital);
	    country.setArea(area);
	    country.setCurrency(currency);
	    country.setGovernment(government);
	    country.setCurrent_temperautre(current_temperautre);
	    country.setConflict_status(conflict_status);
	    
	    // Update metrics
	    country.setPolitical_stability(political_stability);
	    country.setHealthcare(healthcare);
	    country.setEducation(education);
	    country.setEnvaironment(envaironment);
	    country.setMilitary_strength(military_strength);
	    country.setWealth(wealth);
	    country.setTravel_safty(travel_safty);
	    
	    // Save the updated country
	    countryService.save(country);
	    
	    // Redirect to the updated country's detail page
	    return "redirect:/country/" + id;
	}
	
	//------------------
	
	@GetMapping("/admin/formAddCountry")
	public String formAddCountry(Model model) {
		model.addAttribute("country", new Country());
		model.addAttribute("continents", continentService.getAllContinents());
		return "formAddCountry.html";
	}
	
	@PostMapping("/admin/addCountry")
	public String addCountry(@ModelAttribute Country country, @RequestParam Long continentId) {
		
		
	    // You now have the selected continent ID!
	    Continent continent = continentService.getContinentById(continentId);

	    
	    continent.getCountries().add(country); // Link the new country
	    continentService.save(continent);      // Cascade saves the country

	    return "redirect:/continents";
	}
	
	@GetMapping("/admin/deleteCountry/{countryId}")
	public String deleteCountry(@PathVariable Long countryId) {
	    countryService.deleteCountryById(countryId);
	    return "redirect:/continents";
	}
	
	
	
	
	
	@GetMapping("/search")
	public String searchCountry(
	    @RequestParam("query") String query,
	    @RequestParam("continentId") Long continentId,
	    Model model
	) {
		model.addAttribute("continent", continentService.getContinentById(continentId)); // ✅
	    List<Country> results = countryService.searchCountriesByNameStartingWith(query);
	    System.out.println("Searching for: " + query);
	    System.out.println("Results: " + results.size());

	    model.addAttribute("continentCountries", results);
	    model.addAttribute("query", query);
	    return "countries.html";
	}

	
	/*
    
	@GetMapping("/api/search")
	@ResponseBody
	public Map<String, Object> search(@RequestParam("query") String query) {
	    List<Country> countries= countryService.searchCountriesByNameStartingWith(query);

	    Map<String, Object> result = new HashMap<>();
	    result.put("continentCountries", countries);
	  
	    return result;
	}
	 */
	



	
}
