package net.proselyte.jwtappdemo.model;

import lombok.Data;

import javax.persistence.*;


@Entity
@Table(name = "clients")
@Data
public class Client{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "package")
    private String Package;

    @Column(name = "description")
    private String description;

    @Column(name = "adresssender")
    private String adressSender;

    @Column(name = "adressrecipient")
    private String adressRecipient;

    @Column(name = "city")
    private String city;

    @Column(name = "phone")
    private String phone;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private Status status;


}
