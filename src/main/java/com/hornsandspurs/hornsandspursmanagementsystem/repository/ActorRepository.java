package com.isakayabasi.crudapplicationspringboot.repository;

import com.isakayabasi.crudapplicationspringboot.model.Actor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ActorRepository extends JpaRepository<Actor,Long> {
    Actor findByFirstName (String firstName);



}
