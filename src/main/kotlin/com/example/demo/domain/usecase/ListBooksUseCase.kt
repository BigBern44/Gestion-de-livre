package com.example.demo.domain.usecase

import com.example.demo.domain.model.Book
import com.example.demo.domain.port.BookRepository
import org.springframework.stereotype.Service

@Service
class ListBooksUseCase(private val repository: BookRepository) {
    fun execute(): List<Book> = repository.findAllSorted()
}