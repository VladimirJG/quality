package ru.danilov.quality.service;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import ru.danilov.quality.dto.LinkTagDto;
import ru.danilov.quality.dto.MetaTagDto;

import java.util.ArrayList;
import java.util.List;

@Service
public class ExternalServiceParsingService {
    private final WebClient webClient;
    private final String URL = "https://502502.ru/catalog/";

    public ExternalServiceParsingService(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.build();
    }
    @Cacheable(value = "linkTagCache", key = "'linkTags'")
    public Mono<List<LinkTagDto>> fetchAndParseLinkTags() {
        return webClient.get()
                .uri(URL)
                .retrieve()
                .bodyToMono(String.class)
                .map(this::parseLinkTags);
    }
    @Cacheable(value = "metaTagCache", key = "'metaTags'")
    public Mono<List<MetaTagDto>> fetchAndParseMetaTags() {
        return webClient.get()
                .uri(URL)
                .retrieve()
                .bodyToMono(String.class)
                .map(this::parseMetaTags);
    }

    private List<LinkTagDto> parseLinkTags(String html) {
        Document document = Jsoup.parse(html);
        Elements linkTags = document.select("link");

        List<LinkTagDto> linkTagList = new ArrayList<>();
        for (Element linkTag : linkTags) {
            String rel = linkTag.attr("rel");
            String type = linkTag.attr("type");
            String href = linkTag.attr("href");
            String sizes = linkTag.attr("sizes");

            linkTagList.add(new LinkTagDto(rel, type, href, sizes));
        }

        return linkTagList;
    }

    private List<MetaTagDto> parseMetaTags(String html) {
        Document document = Jsoup.parse(html);
        Elements metaTags = document.select("meta");

        List<MetaTagDto> metaTagList = new ArrayList<>();
        for (Element metaTag : metaTags) {
            String name = metaTag.attr("name");
            String content = metaTag.attr("content");

            metaTagList.add(new MetaTagDto(name, content));
        }

        return metaTagList;
    }
}
