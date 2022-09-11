package com.app.APFinal.infrastructure.database.repository;

import com.app.APFinal.infrastructure.database.entity.Experience;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IExperienceRepository extends JpaRepository<Experience, Long> {

  Experience findByIdAndSoftDeletedFalse(long id);

  List<Experience> findAllBySoftDeletedFalse();

}
