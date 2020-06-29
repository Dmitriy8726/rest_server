package net.proselyte.jwtappdemo.repository;


import net.proselyte.jwtappdemo.model.Client;
import net.proselyte.jwtappdemo.model.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ClientRepository extends JpaRepository<Client, Long> {

    @Query("FROM Client u WHERE u.status = ?1 AND u.city = ?2")
    List<Client> findAllByStatusAndByCity(Status active, String city);
}
