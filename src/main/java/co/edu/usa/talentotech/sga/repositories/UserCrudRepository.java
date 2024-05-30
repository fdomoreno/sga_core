package co.edu.usa.talentotech.sga.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import co.edu.usa.talentotech.sga.models.entities.User;
import java.util.Optional;


/*
 * Created on May 29, 2024
 * @author fdomoreno
 */

public interface UserCrudRepository extends JpaRepository<User, Long>{

    public Optional<User> findByClientId(String clientId);
}
