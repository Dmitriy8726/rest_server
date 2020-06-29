package net.proselyte.jwtappdemo.repository;

import net.proselyte.jwtappdemo.model.Client;
import net.proselyte.jwtappdemo.model.ClientUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface ClientUserRepository extends JpaRepository<ClientUser, Long> {
    @Query("SELECT CASE WHEN COUNT(u) > 0 THEN 'true' ELSE 'false' " +
            "END FROM ClientUser u WHERE u.user_id = ?1 AND u.client_id = ?2")
    public Boolean existsByUser_idAndByClient_id(Long user_id, Long client_id);

    @Query("FROM ClientUser u WHERE u.user_id = ?1")
    List<ClientUser> findAllByUser_Id(Long user_id);
}