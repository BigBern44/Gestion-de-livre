package com.example.demo.infrastructure.repository

import com.example.demo.domain.model.Book
import com.example.demo.domain.port.BookRepository
import org.springframework.stereotype.Repository

@Repository
class JpaBookRepository(
    private val springRepo: SpringJpaBookRepository
) : BookRepository {
    override fun save(book: Book): Book = springRepo.save(book)
    override fun findAllSorted(): List<Book> = springRepo.findAllByOrderByTitleAsc()
}