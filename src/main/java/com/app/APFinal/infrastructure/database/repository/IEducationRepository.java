package com.app.APFinal.infrastructure.database.repository;

import com.app.APFinal.infrastructure.database.entity.Education;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IEducationRepository extends JpaRepository<Education, Long> {

  Education findByIdAndSoftDeletedFalse(long id);

  List<Education> findAllBySoftDeletedFalse();

}
