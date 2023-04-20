package me.don1ns.shelterbot.service;


import me.don1ns.shelterbot.exception.CatNotFoundException;
import me.don1ns.shelterbot.model.Cat;
import me.don1ns.shelterbot.repository.CatRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class CatServiceTest {

    private final Long testId = 1L;
    Cat testCat = new Cat(testId, "CatTest1", "CatBreedTest1", 2015, "descriptionTest1");
    @Mock
    private CatRepository repository;
    @InjectMocks
    private CatService service;

    @Test
    public void testGetById() {
        Mockito.when(repository.findById(testId)).thenReturn(Optional.of(testCat));
        Cat result = service.getById(testId);
        Assertions.assertEquals(testCat, result);
    }

    @Test
    public void testGetByIdNotFound() throws CatNotFoundException {
        Mockito.when(repository.findById(testId)).thenReturn(Optional.empty());
        Assertions.assertThrows(CatNotFoundException.class, () -> service.getById(testId));
    }

    @Test
    public void testCreateCat() {
        Mockito.when(repository.save(testCat)).thenReturn(testCat);
        Cat createdCat = service.addCat(testCat);
        Mockito.verify(repository, Mockito.times(1)).save(testCat);
        Assertions.assertEquals(testCat.getId(), createdCat.getId());
        Assertions.assertEquals(testCat.getName(), createdCat.getName());
        Assertions.assertEquals(testCat.getBreed(), createdCat.getBreed());
        Assertions.assertEquals(testCat.getYearOfBirth(), createdCat.getYearOfBirth());
    }
}
