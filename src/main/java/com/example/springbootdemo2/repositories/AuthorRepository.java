package com.example.springbootdemo2.repositories;

import com.example.springbootdemo2.model.Author;
import org.springframework.data.repository.CrudRepository;

public interface AuthorRepository extends CrudRepository<Author,Long> {
}
