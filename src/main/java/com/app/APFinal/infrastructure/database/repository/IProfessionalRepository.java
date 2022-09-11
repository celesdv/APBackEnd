package com.app.APFinal.infrastructure.database.repository;

import com.app.APFinal.infrastructure.database.entity.Professional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IProfessionalRepository extends JpaRepository<Professional, Long> {

  Professional findByName(String name);

}
