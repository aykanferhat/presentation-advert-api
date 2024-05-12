package com.secondhand.presentationadvertapi.application.repository;

import com.secondhand.presentationadvertapi.domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, Long> {

    Optional<Category> findById(Long id);
}
