package ru.danilov.quality.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.danilov.quality.model.MetaTag;

import java.util.Optional;

@Repository
public interface MetaTagRepository extends JpaRepository<MetaTag, Long> {
    Optional<MetaTag> findByNameAndContent(String name, String content);
}