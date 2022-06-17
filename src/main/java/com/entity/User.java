package com.entity;

import com.Role;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private long id;

    @Column(name = "name")
    private String name;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(name="role")
    private Role role;

    @OneToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL) // error ocered for missing cascade but why?
    @JoinColumn(name = "attachment_id",referencedColumnName = "id")
    private Attachment attachment;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "location_id",referencedColumnName = "id")
    private Location location;


}
