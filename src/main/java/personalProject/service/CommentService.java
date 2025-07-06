package personalProject.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import personalProject.model.Country;
import personalProject.model.User;
import personalProject.model.Comment;
import personalProject.model.Continent;
import personalProject.repository.CommentRepository;
import personalProject.repository.CountryRepository;

@Service
public class CommentService {

	@Autowired
	private CommentRepository commentRepository;
	
	@Autowired
	private CountryService countryService;
	
		
	
	
	public List<Comment> getCommentsByCountry(Long countryId) {
        Country country = countryService.getCountryById(countryId);
        List<Comment> allComments = (List<Comment>) commentRepository.findAll();
        return allComments.stream()
                .filter(c -> c.getCountry().getId().equals(country.getId()))
                .collect(Collectors.toList());
    }

	
	
    // You can still have this for saving comments
    public void addComment(String text, Country country, User user) {
        Comment comment = new Comment(text, user, country);
        commentRepository.save(comment);
    }
	
	
	
	
	 
	
	
		
	
	
}
