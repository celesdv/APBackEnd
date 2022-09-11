package com.app.APFinal.infrastructure.database.repository;

import com.app.APFinal.infrastructure.database.entity.HardSkill;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IHardSkillRepository extends JpaRepository<HardSkill, Long> {

  HardSkill findByIdAndSoftDeletedFalse(long id);

  List<HardSkill> findAllBySoftDeletedFalse();

}
