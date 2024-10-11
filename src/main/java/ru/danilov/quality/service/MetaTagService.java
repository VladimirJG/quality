package ru.danilov.quality.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.danilov.quality.exception.MetaTagServiceException;
import ru.danilov.quality.model.MetaTag;
import ru.danilov.quality.repository.MetaTagRepository;

import java.util.Optional;

@Service
@Transactional
public class MetaTagService {
    private final MetaTagRepository metaTagRepository;

    public MetaTagService(MetaTagRepository metaTagRepository) {
        this.metaTagRepository = metaTagRepository;
    }

    @Transactional(readOnly = true)
    public MetaTag getMetaById(Long id) {
        Optional<MetaTag> metaTagOptional = metaTagRepository.findById(id);
        return metaTagOptional.orElse(null);
    }

    public void createMetaTag(MetaTag metaTag) {
        if (metaTag == null) {
            throw new MetaTagServiceException("MetaTag не может быть null");
        }
        metaTagRepository.save(metaTag);
    }

    public void deleteMetaById(Long id) {
        if (id == null) {
            throw new MetaTagServiceException("MetaTag ID не может быть null");
        }
        metaTagRepository.deleteById(id);
    }

    public void deleteAllMetaTags() {
        metaTagRepository.deleteAll();
    }

    public MetaTag updateMetaTag(MetaTag metaTag) {
        if (metaTag == null || metaTag.getId() == null) {
            throw new MetaTagServiceException("MetaTag или metaTag.getId() не может быть null");
        }
        Optional<MetaTag> existingMetaTagOptional = metaTagRepository.findById(metaTag.getId());
        if (existingMetaTagOptional.isEmpty()) {
            throw new MetaTagServiceException("MetaTag с таким id не существует");
        }
        return metaTagRepository.save(metaTag);
    }
}
