package net.proselyte.jwtappdemo.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "user_clients")
@Data
public class ClientUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id")
    private Long user_id;

    @Column(name = "client_id")
    private Long client_id;

}
