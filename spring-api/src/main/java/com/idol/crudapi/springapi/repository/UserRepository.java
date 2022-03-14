package com.idol.crudapi.springapi.repository;
//
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.idol.crudapi.springapi.model.User;
//
public interface UserRepository extends JpaRepository<User, Long>{
	List<User> findByFirstName(String firstName);
	List<User> findByLastName(String lastName);
}