package ru.vlsv.ShopSpring.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.vlsv.ShopSpring.entities.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
	Role findOneByName(String name);
}
