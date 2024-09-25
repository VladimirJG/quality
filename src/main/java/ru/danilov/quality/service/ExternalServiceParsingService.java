package ru.danilov.quality.service;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import ru.danilov.quality.model.LinkTag;
import ru.danilov.quality.model.MetaTag;

import java.util.ArrayList;
import java.util.List;

@Service
public class ExternalServiceParsingService {
    private final WebClient webClient;

    public ExternalServiceParsingService(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.build();
    }

    public Mono<List<LinkTag>> fetchAndParseLinkTags(String url) {
        return webClient.get()
                .uri(url)
                .retrieve()
                .bodyToMono(String.class)
                .map(this::parseLinkTags);
    }

    public Mono<List<MetaTag>> fetchAndParseMetaTags(String url) {
        return webClient.get()
                .uri(url)
                .retrieve()
                .bodyToMono(String.class)
                .map(this::parseMetaTags);
    }

    private List<LinkTag> parseLinkTags(String html) {
        Document document = Jsoup.parse(html);
        Elements linkTags = document.select("link");

        List<LinkTag> linkTagList = new ArrayList<>();
        for (Element linkTag : linkTags) {
            String rel = linkTag.attr("rel");
            String type = linkTag.attr("type");
            String href = linkTag.attr("href");
            String sizes = linkTag.attr("sizes");

            linkTagList.add(new LinkTag(rel, type, href, sizes));
        }

        return linkTagList;
    }

    private List<MetaTag> parseMetaTags(String html) {
        Document document = Jsoup.parse(html);
        Elements metaTags = document.select("meta");

        List<MetaTag> metaTagList = new ArrayList<>();
        for (Element metaTag : metaTags) {
            String name = metaTag.attr("name");
            String content = metaTag.attr("content");

            metaTagList.add(new MetaTag(name, content));
        }

        return metaTagList;
    }
}
