package com.isakayabasi.crudapplicationspringboot.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Collection;


@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "Actor")
public class Actor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long actor_id;

    @Column(name ="first_name" )
    private String firstName;

    @Column(name = "active")
    private boolean active = true;

    @Column(name = "role")
    private String role;

    @Column(name = "password")
    private String password;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(
            name = "actors_roles",
            joinColumns = @JoinColumn(
                    name = "actor_id" , referencedColumnName = "actor_id"),
            inverseJoinColumns = @JoinColumn(
                    name = "role_id", referencedColumnName = "id"))

    private Collection<Role> actorRoles ;


}
