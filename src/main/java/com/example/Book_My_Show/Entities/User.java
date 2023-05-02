package com.example.Book_My_Show.Entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor    // is helpful for entity in mysql
@AllArgsConstructor
@Builder  // requires @ all args contructor
// We can use @Table(name= "") anno for custom table name in sql
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  int id;

    private String name;

    @Column(unique = true, nullable = false) //for making column unique and not null ... for not null we can use @Nonnull also
    private String email;

    private int age;
    @NonNull
    @Column(unique = true)
    private String mobNo;  // we can search through mobNo also

    private String address;

    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
    List<Ticket> ticketList = new ArrayList<>();
}
