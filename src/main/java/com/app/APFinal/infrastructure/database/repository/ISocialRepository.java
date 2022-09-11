package com.app.APFinal.infrastructure.database.repository;

import com.app.APFinal.infrastructure.database.entity.Social;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ISocialRepository extends JpaRepository<Social, Long> {

  Social findByIdAndSoftDeletedFalse(long id);

  List<Social> findAllBySoftDeletedFalse();

}
