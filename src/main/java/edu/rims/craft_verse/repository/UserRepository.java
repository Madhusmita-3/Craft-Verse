package edu.rims.craft_verse.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.rims.craft_verse.entity.User;

public interface UserRepository extends JpaRepository<User, Integer> {

}
