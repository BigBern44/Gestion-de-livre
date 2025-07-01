package com.example.demo.infrastructure.repository

import com.example.demo.domain.model.Book
import org.springframework.data.jpa.repository.JpaRepository

interface SpringJpaBookRepository : JpaRepository<Book, Long> {
    fun findAllByOrderByTitleAsc(): List<Book>
}