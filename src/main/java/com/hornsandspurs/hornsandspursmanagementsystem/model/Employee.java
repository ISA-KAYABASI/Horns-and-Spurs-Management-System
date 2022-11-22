package com.isakayabasi.crudapplicationspringboot.model;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.Date;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name="employees")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Size(min = 2,max = 15,message = "The employee first name must be between 2 and 15 characters")
    @Column(name ="first_name" )
    @Pattern(regexp = "^[a-zA-Z\s]+$", message = "Firstname must be letter with no special characters")
    private String firstName;

    @Size(min = 2,max = 15,message = "The employee last name must be between 2 and 15 characters")
    @Column(name ="last_name" )
    @Pattern(regexp = "^[a-zA-Z]+$",message = "Lastname must be letter with no special characters")
    private String lastName;

    @Column(name = "salary")
    private Double salary;

//    @Column(name = "birthday")
//    @Past(message = "Date should be int he past")

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "dd/MM/yyyy")
    @Temporal(TemporalType.DATE)
    private Date birthday;


    @Column(name = "active ")
    private boolean isActive  = true;

    @Column(name ="departmentName" )
    private String departmentName;


    public Date getBirthday(Date currentDate) {
        return currentDate;
    }
}
