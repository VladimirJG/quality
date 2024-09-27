package ru.danilov.quality.sheduler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import ru.danilov.quality.service.LinkTegService;

@Component
@Slf4j
public class DataUpdateScheduler {
    private final LinkTegService linkTegService;

    public DataUpdateScheduler(LinkTegService linkTegService) {
        this.linkTegService = linkTegService;
    }

    @Async
    @Scheduled(fixedRate = 3600000) // Запускать каждый час (3600000 миллисекунд)
    public void updateData() {
        try {
            log.info("Стартует процесс обновления данных linkTegService.addToDbAllLinkTags(),  DataUpdateScheduler.class");
            linkTegService.addToDbAllLinkTags();
            log.info("Процесс обновления данных завершен успешно");
        } catch (Exception e) {
            log.error("Ошибка во время процесса обновления данных: ", e);
        }
    }
}
