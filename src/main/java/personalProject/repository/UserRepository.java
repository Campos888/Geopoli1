package personalProject.repository;

import org.springframework.data.repository.CrudRepository;

import personalProject.model.User;

public interface UserRepository extends CrudRepository<User, Long> {
}