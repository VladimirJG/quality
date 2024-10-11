package ru.danilov.quality.deserializer;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.apache.kafka.common.serialization.Deserializer;
import ru.danilov.quality.dto.LinkTagDto;

import java.util.Map;

public class LinkTagDtoDeserializer implements Deserializer<LinkTagDto> {
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void configure(Map<String, ?> configs, boolean isKey) {
        Deserializer.super.configure(configs, isKey);
    }

    @Override
    @SneakyThrows
    public LinkTagDto deserialize(String s, byte[] bytes) {
        return objectMapper.readValue(bytes, LinkTagDto.class);
    }
}
