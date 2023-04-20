package me.don1ns.shelterbot.service;


import me.don1ns.shelterbot.model.Context;
import me.don1ns.shelterbot.repository.ContextRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Сервис класса Context
 *
 * @author Riyaz Karimullin
 */
@Service
public class ContextService {
    private final ContextRepository contextRepository;

    public ContextService(ContextRepository contextRepository) {
        this.contextRepository = contextRepository;
    }
    public Context saveContext(Context context) {
        return contextRepository.save(context);
    }

    public Optional<Context> getByChatId(Long chatId) {

        return contextRepository.findByChatId(chatId);
    }
}
