package com.library.assignment.book;

import com.library.assignment.user.User;
import com.library.assignment.user.UserRepo;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class BookService {

    BookRepo bookRepo;
    UserRepo userRepo;
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

    public ResponseEntity<Boolean> borrowBook(Long bookId, Long userId) {
        Optional<Book> optionalBookToBorrow = bookRepo.findById(bookId);
        Optional<User> optionalUser = userRepo.findById(userId);
        if (optionalUser.isPresent() && optionalBookToBorrow.isPresent() && !optionalBookToBorrow.get().isBorrowed()){
            Book book = optionalBookToBorrow.get();
            book.setBorrowed(true);
            book.setBorrowerId(userId);
            book.setBorrowedDate(LocalDate.now());
            bookRepo.save(book);
            return ResponseEntity.ok().build();
        }else {
            return ResponseEntity.noContent().build();
        }
    }

    public ResponseEntity<Boolean> returnBook(Long bookId, Long userId) {
        Optional<Book> optionalBookToBorrow = bookRepo.findById(bookId);
        Optional<User> optionalUser = userRepo.findById(userId);
        if (optionalUser.isPresent() && optionalBookToBorrow.isPresent() && optionalBookToBorrow.get().getBorrowerId().equals(userId)){
            Book book = optionalBookToBorrow.get();
            book.setBorrowed(false);
            book.setBorrowerId(null);
            book.setBorrowedDate(null);
            bookRepo.save(book);
            return ResponseEntity.ok().build();
        }else {
            return ResponseEntity.noContent().build();
        }
    }

    public ResponseEntity<Void> deleteBook(Long bookId) {

        Optional<Book> bookOptional = bookRepo.findById(bookId);
        if (bookOptional.isPresent()) {
            bookRepo.deleteById(bookId);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.noContent().build();

    }
}

