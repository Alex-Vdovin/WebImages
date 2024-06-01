package com.avisto.webimages.repositories;

import com.avisto.webimages.model.Image;
import com.avisto.webimages.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImageRepository extends JpaRepository<Image, Long> {
    void deleteById(Long imageId);

    Page<Image> findByUser(User user, Pageable pageable);
}
