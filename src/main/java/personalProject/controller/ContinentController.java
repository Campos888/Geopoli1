package personalProject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import personalProject.service.ContinentService;


@Controller
public class ContinentController {

	
	@Autowired
	ContinentService continentService;
	
	
}
