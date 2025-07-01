package com.example.demo.domain.usecase

import com.example.demo.domain.model.Book
import com.example.demo.domain.port.BookRepository
import org.springframework.stereotype.Service

@Service
class AddBookUseCase(private val repository: BookRepository) {
    fun execute(title: String, author: String): Book {
        require(title.isNotBlank()) { "Le titre est obligatoire" }
        require(author.isNotBlank()) { "L'auteur est obligatoire" }

        return repository.save(Book(title = title, author = author))
    }
}