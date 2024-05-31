package com.avisto.webimages.repositories;

import com.avisto.webimages.model.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImageRepository extends JpaRepository<Image, Long> {
    void deleteById(Long imageId);
}
