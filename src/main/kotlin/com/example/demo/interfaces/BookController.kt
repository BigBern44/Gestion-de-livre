package com.example.demo.interfaces

import com.example.demo.domain.model.Book
import com.example.demo.domain.usecase.AddBookUseCase
import com.example.demo.domain.usecase.ListBooksUseCase
import jakarta.validation.Valid
import jakarta.validation.constraints.NotBlank
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/books")
class BookController(
    private val addBookUseCase: AddBookUseCase,
    private val listBooksUseCase: ListBooksUseCase
) {

    data class BookRequest(val title: String, val author: String)

    @PostMapping
    fun addBook(@Valid @RequestBody request: BookRequest): Book {
        return addBookUseCase.execute(request.title, request.author)
    }

    @GetMapping
    fun listBooks(): List<Book> {
        return listBooksUseCase.execute()
    }
}

data class BookRequest(
    @field:NotBlank(message = "Le titre ne peut pas être vide")
    val title: String,

    @field:NotBlank(message = "L'auteur ne peut pas être vide")
    val author: String
)