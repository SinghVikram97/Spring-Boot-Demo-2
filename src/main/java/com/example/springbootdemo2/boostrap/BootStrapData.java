package com.example.springbootdemo2.boostrap;

import com.example.springbootdemo2.model.Author;
import com.example.springbootdemo2.model.Book;
import com.example.springbootdemo2.model.Publisher;
import com.example.springbootdemo2.repositories.AuthorRepository;
import com.example.springbootdemo2.repositories.BookRepository;
import com.example.springbootdemo2.repositories.PublisherRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component // This indicates that it is a Spring managed component
public class BootStrapData implements CommandLineRunner {

    // Dependency injection
    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;
    private final PublisherRepository publisherRepository;

    public BootStrapData(AuthorRepository authorRepository, BookRepository bookRepository, PublisherRepository publisherRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        Author eric=new Author("Eric","Evans");
        Book ddd=new Book("Domain Driven Design","123123");
        Publisher publisher=new Publisher("SFG Publishing","St Petersburg","FL","USA","110065");
        publisherRepository.save(publisher);

        // Assign books to author and vice versa
        eric.getBooks().add(ddd);
        ddd.getAuthors().add(eric);

        ddd.setPublisher(publisher);
        publisher.getBooks().add(ddd);

        // Save them to h2 database
        authorRepository.save(eric);
        bookRepository.save(ddd);
        publisherRepository.save(publisher);

        Author rod=new Author("Rod","Johnson");
        Book noEJB=new Book("J2EE Development without EJB","39346464");

        rod.getBooks().add(noEJB);
        noEJB.getAuthors().add(rod);

        noEJB.setPublisher(publisher);
        publisher.getBooks().add(noEJB);

        authorRepository.save(rod);
        bookRepository.save(noEJB);
        publisherRepository.save(publisher);  // Again save but publisher count will be as they are same publisher
        // We implemented equals in publisher which checks on publisher id



        System.out.println("Started in Bootstrap");
        System.out.println("Number of Books: "+bookRepository.count());
        System.out.println("Number of Authors: "+authorRepository.count());
        System.out.println("Number of Publishers "+publisherRepository.count());
    }
}
