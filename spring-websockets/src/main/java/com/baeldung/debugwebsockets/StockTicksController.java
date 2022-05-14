package com.baeldung.debugwebsockets;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.UnmodifiableView;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ThreadLocalRandom;


@Controller
public class StockTicksController {
    private final SimpMessageSendingOperations simpMessagingTemplate;

    public StockTicksController(SimpMessageSendingOperations simpMessagingTemplate) {
        this.simpMessagingTemplate = simpMessagingTemplate;
    }

    @Scheduled(fixedRate = 3000)
    public void sendTicks() {
        simpMessagingTemplate.convertAndSend("/topic/ticks", getStockTicks());
    }

    private @NotNull @UnmodifiableView Map<String, Integer> getStockTicks() {

        final Map<String, Integer> map = new ConcurrentHashMap<>();
        for (final String s : List.of("AAPL", "GOOGL", "MSFT", "TSLA", "AMZN", "HPE")) {
            map.put(s, getRandomTick());
        }
        return Collections.unmodifiableMap(map);
    }

    private int getRandomTick() {
        return ThreadLocalRandom.current().nextInt(-100, 100 + 1);
    }
}