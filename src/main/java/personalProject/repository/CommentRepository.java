package personalProject.repository;

import org.springframework.data.repository.CrudRepository;

import personalProject.model.Comment;


public interface CommentRepository extends CrudRepository<Comment,Long>{

}