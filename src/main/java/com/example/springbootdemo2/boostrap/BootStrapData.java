package com.example.springbootdemo2.boostrap;

import com.example.springbootdemo2.model.Author;
import com.example.springbootdemo2.model.Book;
import com.example.springbootdemo2.repositories.AuthorRepository;
import com.example.springbootdemo2.repositories.BookRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component // This indicates that it is a Spring managed component
public class BootStrapData implements CommandLineRunner {

    // Dependency injection
    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;

    public BootStrapData(AuthorRepository authorRepository, BookRepository bookRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        Author eric=new Author("Eric","Evans");
        Book ddd=new Book("Domain Driven Design","123123");

        // Assign books to author and vice versa
        eric.getBooks().add(ddd);
        ddd.getAuthors().add(eric);

        // Save them to h2 database
        authorRepository.save(eric);
        bookRepository.save(ddd);

        Author rod=new Author("Rod","Johnson");
        Book noEJB=new Book("J2EE Development without EJB","39346464");

        rod.getBooks().add(noEJB);
        noEJB.getAuthors().add(rod);

        authorRepository.save(rod);
        bookRepository.save(noEJB);

        System.out.println("Started in Bootstrap");
        System.out.println("Number of Books: "+bookRepository.count());
    }
}
