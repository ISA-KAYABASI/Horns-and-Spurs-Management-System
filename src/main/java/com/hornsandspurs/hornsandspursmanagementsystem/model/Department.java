package com.isakayabasi.crudapplicationspringboot.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.List;


@Entity
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name="department")
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @NotEmpty(message = "Department cannot be empty")
    @Column(name ="departmentName" )
    private String departmentName;



}
