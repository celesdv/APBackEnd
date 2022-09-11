package com.app.APFinal.infrastructure.database.repository;

import com.app.APFinal.infrastructure.database.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IRoleRepository extends JpaRepository<Role, Long> {

  Role findByName(String name);

}
