package com.library.assignment.user;

import com.library.assignment.book.Book;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

import java.util.ArrayList;

@Table
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class User {
    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY)
    private Long userId;

    @Column(nullable = false)
    private String firstName;
    @Column(nullable = false)
    private String lastName;
    @Email
    @Column(nullable = false, unique = true)
    private String email;
    @Column(nullable = false)
    private String pinCode;
    @Enumerated
    @Column(nullable = false)
    private UserType userType = UserType.BORROWER;
    /*@OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "book_id")
    private ArrayList<Book> borrowedBooks;*/
}
