package com.example.springbootdemo2.repositories;

import com.example.springbootdemo2.model.Book;
import org.springframework.data.repository.CrudRepository;

public interface BookRepository extends CrudRepository<Book,Long> {
}
