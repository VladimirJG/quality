package ru.danilov.quality.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.danilov.quality.model.LinkTag;
import ru.danilov.quality.service.LinkTegService;

import java.util.List;

@RestController
@RequestMapping("/links")
public class LinkTagController {
    private final LinkTegService linkTegService;

    public LinkTagController(LinkTegService linkTegService) {
        this.linkTegService = linkTegService;
    }

    @GetMapping
    public ResponseEntity<List<LinkTag>> getAllLinks() {
        List<LinkTag> allLinkTags = linkTegService.getAllLinkTags();
        return new ResponseEntity<>(allLinkTags, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<LinkTag> getLinkById(@PathVariable Long id) {
        LinkTag linkTagById = linkTegService.getLinkTagById(id);
        if (linkTagById != null) {
            return new ResponseEntity<>(linkTagById, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/delAll")
    public ResponseEntity<Void> deleteAllLinks() {
        linkTegService.deleteAllLinksOnDB();
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
