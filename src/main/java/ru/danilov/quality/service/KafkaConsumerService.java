package ru.danilov.quality.service;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import ru.danilov.quality.dto.LinkTagDto;
import ru.danilov.quality.model.LinkTag;
import ru.danilov.quality.repository.LinkTagRepository;

import java.util.Optional;

@Service
public class KafkaConsumerService {

    private final LinkTagRepository linkTagRepository;
    int count = 0;

    public KafkaConsumerService(LinkTagRepository linkTagRepository) {
        this.linkTagRepository = linkTagRepository;
    }

    @KafkaListener(topics = "out-quality", groupId = "consumer-group-external-in")
    public void listen(LinkTagDto linkTagDto) {
        Optional<LinkTag> optionalLinkTag = linkTagRepository.findByRelAndHrefAndSizes(
                linkTagDto.getRel(),
                linkTagDto.getHref(),
                linkTagDto.getSizes()
        );
        if (optionalLinkTag.isEmpty()) {
            LinkTag linkTag = new LinkTag();
            linkTag.setRel(linkTagDto.getRel());
            linkTag.setType(linkTagDto.getType());
            linkTag.setHref(linkTagDto.getHref());
            linkTag.setSizes(linkTagDto.getSizes());

            linkTagRepository.save(linkTag);
        } else {
            count++;
            System.out.println("Повтор" + count);
        }
    }
}
