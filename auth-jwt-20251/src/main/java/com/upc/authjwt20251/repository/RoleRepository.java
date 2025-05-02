package com.upc.authjwt20251.repository;

import com.upc.authjwt20251.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
}
