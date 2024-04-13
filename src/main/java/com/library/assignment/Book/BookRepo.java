package com.library.assignment.book;


import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookRepo extends JpaRepository<Book, Long> {
    List<Book> findByBorrowerId(Long borrowerId);
}
