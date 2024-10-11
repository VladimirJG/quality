package ru.danilov.quality.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.danilov.quality.exception.LinkTagNotFoundException;
import ru.danilov.quality.model.LinkTag;
import ru.danilov.quality.repository.LinkTagRepository;

import java.util.List;
import java.util.Optional;

@Service
public class LinkTegService {
    private final LinkTagRepository linkTagRepository;

    public LinkTegService(LinkTagRepository linkTagRepository) {
        this.linkTagRepository = linkTagRepository;
    }

    @Transactional(readOnly = true)
    public List<LinkTag> getAllLinkTags() {
        return linkTagRepository.findAll();
    }

    @Transactional(readOnly = true)
    public LinkTag getLinkTagById(Long id) {
        Optional<LinkTag> LT = linkTagRepository.findById(id);
        return LT.orElseThrow(() -> new LinkTagNotFoundException("LinkTag with id " + id + " not found"));
    }

    @Transactional
    public void deleteAllLinksOnDB() {
        linkTagRepository.deleteAll();
    }
}
