package ru.danilov.quality.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.danilov.quality.model.LinkTag;

import java.util.Optional;

@Repository
public interface LinkTagRepository extends JpaRepository<LinkTag, Long> {
    Optional<LinkTag> findByRelAndHrefAndSizes(String rel, String href, String sizes);
}
