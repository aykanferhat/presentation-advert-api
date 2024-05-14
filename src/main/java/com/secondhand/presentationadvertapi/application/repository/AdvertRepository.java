package com.secondhand.presentationadvertapi.application.repository;

import com.secondhand.presentationadvertapi.domain.Advert;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AdvertRepository extends JpaRepository<Advert, Long> {

    Optional<Advert> findById(Long id);
}
