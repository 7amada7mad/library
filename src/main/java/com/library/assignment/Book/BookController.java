package com.library.assignment.book;

import com.library.assignment.user.User;
import com.library.assignment.user.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;


@RestController
@AllArgsConstructor
@RequestMapping("api/v1/books")
public class BookController {
        BookService bookService;

        @GetMapping
        private ResponseEntity<List<Book>> getAllBooks(){
            return bookService.getAllBooks();
        }
        @GetMapping("{id}")
        private ResponseEntity<Book> getBook(@PathVariable Long id){
            return bookService.findBookById(id);
        }
        @PostMapping
        private ResponseEntity<Void> addBook(@RequestBody Book book, UriComponentsBuilder ucb) {

            return bookService.addBook(book, ucb);
        }
}
