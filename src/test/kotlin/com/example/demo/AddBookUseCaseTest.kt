package com.example.demo

import com.example.demo.domain.model.Book
import com.example.demo.domain.port.BookRepository
import com.example.demo.domain.usecase.AddBookUseCase

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe
import io.kotest.assertions.throwables.shouldThrow

class AddBookUseCaseTest : StringSpec({

    val fakeRepository = object : BookRepository {
        private val books = mutableListOf<Book>()
        override fun save(book: Book): Book {
            val saved = book.copy(id = books.size.toLong() + 1)
            books.add(saved)
            return saved
        }
        override fun findAllSorted(): List<Book> = books.sortedBy { it.title }
    }

    val useCase = AddBookUseCase(fakeRepository)

    "should add a book with valid title and author" {
        val book = useCase.execute("1984", "George Orwell")
        book.title shouldBe "1984"
        book.author shouldBe "George Orwell"
        book.id shouldBe 1
    }

    "should throw error if title is blank" {
        shouldThrow<IllegalArgumentException> {
            useCase.execute("", "Author")
        }
    }

    "should throw error if author is blank" {
        shouldThrow<IllegalArgumentException> {
            useCase.execute("Title", "")
        }
    }
})