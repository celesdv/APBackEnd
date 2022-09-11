package com.app.APFinal.infrastructure.database.repository;

import com.app.APFinal.infrastructure.database.entity.SoftSkill;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ISoftSkillRepository extends JpaRepository<SoftSkill, Long> {

  SoftSkill findByIdAndSoftDeletedFalse(long id);

  List<SoftSkill> findAllBySoftDeletedFalse();

}
