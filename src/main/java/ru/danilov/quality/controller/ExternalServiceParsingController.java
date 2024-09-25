package ru.danilov.quality.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;
import ru.danilov.quality.model.LinkTag;
import ru.danilov.quality.model.MetaTag;
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
    public Mono<List<LinkTag>> parseLinkTags() {
        return externalServiceParsingService.fetchAndParseLinkTags("https://502502.ru/catalog/");
    }

    @GetMapping("/meta")
    public Mono<List<MetaTag>> parseMetaTags() {
        return externalServiceParsingService.fetchAndParseMetaTags("https://502502.ru/catalog/");
    }
}
