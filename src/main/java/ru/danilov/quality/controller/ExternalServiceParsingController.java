package ru.danilov.quality.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;
import ru.danilov.quality.dto.LinkTagDto;
import ru.danilov.quality.dto.MetaTagDto;
import ru.danilov.quality.service.ExternalServiceParsingService;

import java.util.List;

@RestController
@RequestMapping("/parse")
public class ExternalServiceParsingController {
    private final ExternalServiceParsingService externalServiceParsingService;

    @Autowired
    public ExternalServiceParsingController(ExternalServiceParsingService externalServiceParsingService) {
        this.externalServiceParsingService = externalServiceParsingService;
    }

    @GetMapping("/links")
    public Mono<List<LinkTagDto>> parseLinkTags() {
        return externalServiceParsingService.fetchAndParseLinkTags("https://502502.ru/catalog/");
    }

    @GetMapping("/meta")
    public Mono<List<MetaTagDto>> parseMetaTags() {
        return externalServiceParsingService.fetchAndParseMetaTags("https://502502.ru/catalog/");
    }
}
