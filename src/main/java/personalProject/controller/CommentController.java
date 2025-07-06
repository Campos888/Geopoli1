package personalProject.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import personalProject.model.Country;
import personalProject.model.Credentials;
import personalProject.model.User;
import personalProject.service.CommentService;
import personalProject.service.CountryService;
import personalProject.service.CredentialsService;
import personalProject.service.UserService;

@Controller
public class CommentController {

	
	@Autowired
	UserService userService; 
	@Autowired
	CountryService countryService; 
	@Autowired
	CommentService commentService;
	@Autowired
	CredentialsService credentialsService;
	
	
	@PostMapping("/addComment")
	public String addComment(@RequestParam("countryId") Long countryId,
	                         @RequestParam("text") String text,
	                         Principal principal) {

	    // Get the user and country
	    User user = credentialsService.findByUsername(principal.getName());
	    Country country = countryService.getCountryById(countryId);

	    // Add the comment
	    commentService.addComment(text, country, user);

	    // Redirect back to the country's detail page
	    return "redirect:/country/" + countryId;
	}

	
}
