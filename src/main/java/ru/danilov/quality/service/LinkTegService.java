package ru.danilov.quality.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.danilov.quality.dto.LinkTagDto;
import ru.danilov.quality.exception.LinkTagNotFoundException;
import ru.danilov.quality.model.LinkTag;
import ru.danilov.quality.repository.LinkTagRepository;

import java.util.List;
import java.util.Optional;

@Service
public class LinkTegService {
    private final LinkTagRepository linkTagRepository;
    private final ExternalServiceParsingService externalServiceParsingService;

    public LinkTegService(LinkTagRepository linkTagRepository, ExternalServiceParsingService externalServiceParsingService) {
        this.linkTagRepository = linkTagRepository;
        this.externalServiceParsingService = externalServiceParsingService;
    }

    @Transactional
    public void addToDbAllLinkTags() {
        Mono<List<LinkTagDto>> listMono = externalServiceParsingService.fetchAndParseLinkTags();
        listMono.flatMapMany(linkTags -> {
            return Flux.fromIterable(linkTags)
                    .map(linkTagDto -> {
                        LinkTag linkTag = new LinkTag();
                        linkTag.setRel(linkTagDto.rel());
                        linkTag.setType(linkTagDto.type());
                        linkTag.setHref(linkTagDto.href());
                        linkTag.setSizes(linkTagDto.sizes());
                        return linkTag;
                    });
        }).doOnNext(linkTagRepository::save).then().block();
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
