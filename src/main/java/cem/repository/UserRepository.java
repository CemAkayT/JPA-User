package cem.repository;

import cem.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> { // hvilken type skal vi gemme? User og Long som id

}