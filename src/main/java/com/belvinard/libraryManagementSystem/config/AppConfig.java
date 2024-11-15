package com.belvinard.libraryManagementSystem.config;

import com.belvinard.libraryManagementSystem.data.LibraryData;
import com.belvinard.libraryManagementSystem.service.BookService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = "com.belvinard.libraryManagementSystem")
public class AppConfig {

    @Bean
    public LibraryData libraryData(){
        return new LibraryData();
    }

    @Bean
    public BookService bookService() {
        return new BookService(libraryData());
    }



}
