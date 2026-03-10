package com.example.demo.controller;

import com.example.demo.model.Book;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/books")
public class BookController {

    private List<Book> books = new ArrayList<>();

    public BookController() {
        books.add(new Book(1, "Spring Boot", "Huy Cuong"));
        books.add(new Book(2, "Java Core", "Tuan Anh"));
    }

    @GetMapping
    public String listBooks(Model model) {
        model.addAttribute("books", books);
        return "book/list";
    }

    @GetMapping("/add")
    public String addBookForm(Model model) {
        model.addAttribute("book", new Book());
        return "book/add";
    }

    @PostMapping("/add")
    public String addBook(@ModelAttribute("book") Book book) {
        if (!books.isEmpty()) {
            book.setId(books.get(books.size() - 1).getId() + 1);
        } else {
            book.setId(1);
        }
        books.add(book);
        return "redirect:/books";
    }

    @GetMapping("/edit/{id}")
    public String editBookForm(@PathVariable("id") int id, Model model) {
        Optional<Book> book = books.stream().filter(b -> b.getId() == id).findFirst();
        if (book.isPresent()) {
            model.addAttribute("book", book.get());
            return "book/edit";
        }
        return "redirect:/books";
    }

    @PostMapping("/edit")
    public String editBook(@ModelAttribute("book") Book updatedBook) {
        for (int i = 0; i < books.size(); i++) {
            if (books.get(i).getId() == updatedBook.getId()) {
                books.set(i, updatedBook);
                break;
            }
        }
        return "redirect:/books";
    }

    @GetMapping("/delete/{id}")
    public String deleteBook(@PathVariable("id") int id) {
        books.removeIf(b -> b.getId() == id);
        return "redirect:/books";
    }
}