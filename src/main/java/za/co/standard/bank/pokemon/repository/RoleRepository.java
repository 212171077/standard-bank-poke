package za.co.standard.bank.pokemon.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import za.co.standard.bank.pokemon.model.entities.Role;
import za.co.standard.bank.pokemon.model.enums.ERole;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

    Optional<Role> findByName(ERole name);
}
