package ru.danilov.quality.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.danilov.quality.model.MetaTag;
import ru.danilov.quality.service.MetaTagService;

@RestController
@RequestMapping("/meta-tags")
public class MetaTagController {

    private final MetaTagService metaTagService;

    public MetaTagController(MetaTagService metaTagService) {
        this.metaTagService = metaTagService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<MetaTag> getMetaTagById(@PathVariable Long id) {
        MetaTag metaTag = metaTagService.getMetaById(id);
        if (metaTag != null) {
            return ResponseEntity.ok(metaTag);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Void> createMetaTag(@RequestBody MetaTag metaTag) {
        metaTagService.createMetaTag(metaTag);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMetaTagById(@PathVariable Long id) {
        metaTagService.deleteMetaById(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteAllMetaTags() {
        metaTagService.deleteAllMetaTags();
        return ResponseEntity.noContent().build();
    }

    @PutMapping
    public ResponseEntity<MetaTag> updateMetaTag(@RequestBody MetaTag metaTag) {
        MetaTag updatedMetaTag = metaTagService.updateMetaTag(metaTag);
        return ResponseEntity.ok(updatedMetaTag);
    }
}
