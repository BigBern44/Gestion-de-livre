package com.example.demo

import com.example.demo.domain.model.Book
import com.example.demo.domain.port.BookRepository
import com.example.demo.domain.usecase.ListBooksUseCase

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class ListBooksUseCaseTest : StringSpec({

    val fakeRepository = object : BookRepository {
        private val books = listOf(
            Book(id = 1, title = "Zebra", author = "A"),
            Book(id = 2, title = "Apple", author = "B"),
            Book(id = 3, title = "Mango", author = "C")
        )

        override fun save(book: Book): Book = book

        override fun findAllSorted(): List<Book> = books.sortedBy { it.title }
    }

    val useCase = ListBooksUseCase(fakeRepository)

    "should return books sorted by title alphabetically" {
        val result = useCase.execute()
        result.map { it.title } shouldBe listOf("Apple", "Mango", "Zebra")
    }
})