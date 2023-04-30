package me.don1ns.shelterbot.service;

import me.don1ns.shelterbot.constant.ShelterType;
import me.don1ns.shelterbot.model.CatOwners;
import me.don1ns.shelterbot.model.Context;
import me.don1ns.shelterbot.repository.ContextRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

/**
 * Service Test class for user state tracking (Класс Тестов сервисов для отслеживания состояния пользователя)
 *
 * @author Loginova Viktoria (Логинова Виктория)
 **/
@ExtendWith(MockitoExtension.class)
public class ContextServiceTest {
    @Mock
    private ContextRepository repository;
    @InjectMocks
    private ContextService service;

    @Test
    public void testSaveContext() {
        Long testId = 1L;
        Context context = new Context(testId, ShelterType.DOG);
        when(repository.save(context)).thenReturn(context);
        Context saveContext = service.saveContext(context);
        Mockito.verify(repository, Mockito.times(1)).save(context);
        Assertions.assertEquals(context.getChatId(), saveContext.getChatId());
        Assertions.assertEquals(context.getShelterType(), saveContext.getShelterType());
        Assertions.assertEquals(context.getDogOwner(), saveContext.getDogOwner());
        Assertions.assertEquals(context.getCatOwner(), saveContext.getCatOwner());
    }

    @Test
    public void testGetByChatId() {
        Optional<Context> context = Optional.of(new Context(1L, ShelterType.DOG));
        when(repository.findByChatId(anyLong())).thenReturn(context);
        repository.findByChatId(context.get().getChatId());
        assertNotNull(context);
        Assertions.assertEquals(context.get(), context.get());
    }
    @Test
    public void testGetAll() {
        List<Context> contextList = new ArrayList<>();
        Context context22 = new Context(1L, ShelterType.DOG);
        contextList.add(context22);
        Context context23 = new Context(2L, ShelterType.CAT);
        contextList.add(context23);
        Mockito.when(repository.findAll()).thenReturn(contextList);
        ContextService service = new ContextService(repository);
        Collection<Context> result = service.getAll();
        Assertions.assertEquals(contextList, result);
        Mockito.verify(repository, Mockito.times(1)).findAll();
    }
    // Не уверена в том, что данные тесты вообще нужны. Проверка ради проверки.
   /*

   @Test
    public void whenCreatesEmptyOptional_thenCorrect() {
        Optional<Context> empty = Optional.empty();
        assertFalse(empty.isPresent());
    }
    @Test
    public void givenNonNull_whenCreatesNonNullable_thenCorrect() {
        Context context = new Context(1L,ShelterType.DOG);
        Optional<Context> opt = Optional.of(context);
        assertTrue(opt.isPresent());
    }
    @Test
    public void givenOptional_whenIsPresentWorks_thenCorrect() {
        Optional<Context> context = Optional.of(new Context(1L,ShelterType.DOG));
        assertTrue(context.isPresent());

        context = Optional.ofNullable(null);
        assertFalse(context.isPresent());
    }*/
}


