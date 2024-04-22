package com.library.assignment.book;



import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Date;

@Table
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Book {
    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY)
    private Long bookId;
    @Column(nullable = false)
    private String title;
    @Column(nullable = false)
    private String author;
    @Column(nullable = false)
    @JsonFormat(pattern = "YYYY")
    private Date releaseDate;
    @Column
    private boolean borrowed = false;
    @Column
    private LocalDate borrowedDate;
    @Column
    private Long borrowerId;

}
