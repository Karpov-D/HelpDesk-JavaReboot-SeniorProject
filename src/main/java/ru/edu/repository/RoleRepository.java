package ru.edu.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.edu.entity.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {


}
