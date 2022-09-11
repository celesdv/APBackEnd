package com.app.APFinal.infrastructure.database.repository;

import com.app.APFinal.infrastructure.database.entity.User;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface IUserRepository extends JpaRepository<User, Long> {

  User findByEmail(String email);

  @Query(value = "SELECT u FROM User u WHERE u.softDeleted=false")
  List<User> findAllActiveUsers();

  User findByIdAndSoftDeletedFalse(Long id);

}
