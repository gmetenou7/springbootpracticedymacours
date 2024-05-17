package com.dyma.springpracticecours.repository;

import com.dyma.springpracticecours.entity.PlayerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PlayerRepository extends JpaRepository<PlayerEntity, Long> {

    /*list by lastName*/
    Optional<PlayerEntity> findOneByLastNameIgnoreCase(String lastName);


}
