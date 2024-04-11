package com.library.assignment.Book;

import com.library.assignment.user.User;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class BookService {

    BookRepo bookRepo;
    public ResponseEntity<List<Book>> getAllBooks() {
        Optional<List<Book>> bookListOptional = Optional.of(bookRepo.findAll());
        if (bookListOptional.isPresent()){
            return ResponseEntity.ok(bookListOptional.get());
        }else {
            return ResponseEntity.notFound().build();
        }
    }

    public ResponseEntity<Book> findBookById(Long bookId) {
        Optional<Book> wantedBook = bookRepo.findById(bookId);
        if (wantedBook.isPresent()){
            return ResponseEntity.ok(wantedBook.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    public ResponseEntity<Void> addBook(Book book, UriComponentsBuilder ucb) {
        bookRepo.save(book);
        URI locationOfNewUser = ucb
                .path("api/v1/books/{id}")
                .buildAndExpand(book.getBookId())
                .toUri();
        return ResponseEntity.created(locationOfNewUser).build();
    }
}

