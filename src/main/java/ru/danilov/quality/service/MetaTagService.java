package ru.danilov.quality.service;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.danilov.quality.dto.MetaTagDto;
import ru.danilov.quality.exception.MetaTagServiceException;
import ru.danilov.quality.model.MetaTag;
import ru.danilov.quality.repository.MetaTagRepository;

import java.util.List;
import java.util.Optional;

@Service
public class MetaTagService {
    private final ExternalServiceParsingService externalServiceParsingService;
    private final MetaTagRepository metaTagRepository;
    private final ModelMapper modelMapper;

    public MetaTagService(ExternalServiceParsingService parsingService, MetaTagRepository metaTagRepository, ModelMapper modelMapper) {
        this.externalServiceParsingService = parsingService;
        this.metaTagRepository = metaTagRepository;
        this.modelMapper = modelMapper;
    }

    @Transactional
    public void addAllMetaTagToDB() {
        List<MetaTagDto> metaTagDtos = externalServiceParsingService.fetchAndParseMetaTags().block();
        assert metaTagDtos != null;
        for (MetaTagDto metaTagDto : metaTagDtos) {
            MetaTag metaTag = convertToMetaTag(metaTagDto);
            if (isMetaTagNotExist(metaTag)) {
                metaTagRepository.save(metaTag);
            }
        }
    }

    @Transactional(readOnly = true)
    public MetaTag getMetaById(Long id) {
        Optional<MetaTag> metaTagOptional = metaTagRepository.findById(id);
        return metaTagOptional.orElse(null);
    }

    @Transactional
    public void createMetaTag(MetaTag metaTag) {
        if (metaTag == null) {
            throw new MetaTagServiceException("MetaTag не может быть null");
        }
        metaTagRepository.save(metaTag);
    }

    @Transactional
    public void deleteMetaById(Long id) {
        if (id == null) {
            throw new MetaTagServiceException("MetaTag ID не может быть null");
        }
        metaTagRepository.deleteById(id);
    }

    @Transactional
    public void deleteAllMetaTags() {
        metaTagRepository.deleteAll();
    }

    @Transactional
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

    private MetaTag convertToMetaTag(MetaTagDto metaTagDto) {
        return modelMapper.map(metaTagDto, MetaTag.class);
    }

    private boolean isMetaTagNotExist(MetaTag metaTag) {
        Optional<MetaTag> existingMetaTagOptional = metaTagRepository.findByNameAndContent(metaTag.getName(), metaTag.getContent());
        return existingMetaTagOptional.isEmpty();
    }
}
