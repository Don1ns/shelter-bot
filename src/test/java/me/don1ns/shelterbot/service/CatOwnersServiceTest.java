package me.don1ns.shelterbot.service;
import me.don1ns.shelterbot.exception.CatOwnersNotFoundException;
import me.don1ns.shelterbot.model.CatOwners;
import me.don1ns.shelterbot.repository.CatOwnersRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.Optional;
/**
 * Класс Тестов сервисов для владельцев котиков
 * @author Королёв Артем
 **/
@ExtendWith(MockitoExtension.class)
public class CatOwnersServiceTest {
    @Mock
    private CatOwnersRepository repository;
    @InjectMocks
    private CatOwnersService service;
    @Test
    public void testGetById() {
        Long testId = 1L;
        CatOwners testCatOwner = new CatOwners("John", "102", testId);
        Mockito.when(repository.findById(testId)).thenReturn(Optional.of(testCatOwner));
        CatOwners result = service.getById(testId);
        Assertions.assertEquals(testCatOwner, result);
    }
    @Test
    public void testGetByIdNotFound() throws CatOwnersNotFoundException {
        Long testId = 1L;
        Mockito.when(repository.findById(testId)).thenReturn(Optional.empty());
        Assertions.assertThrows(CatOwnersNotFoundException.class, () -> service.getById(testId));
    }
    @Test
    public void testGetByIdCallsFindById() {
        Long testId = 1L;
        CatOwners testCatOwner = new CatOwners("John", "102", testId);
        Mockito.when(repository.findById(testId)).thenReturn(Optional.of(testCatOwner));
        service.getById(testId);
        Mockito.verify(repository.getById(testId));
    }
    @Test
    public void testCreateCatOwners() {
        Long testId = 1L;
        CatOwners catOwners = new CatOwners("John", "102", testId);
        Mockito.when(repository.save(catOwners)).thenReturn(catOwners);
        CatOwners createdCatOwners = service.create(catOwners);
        Mockito.verify(repository, Mockito.times(1)).save(catOwners);
        Assertions.assertEquals(catOwners.getName(), createdCatOwners.getName());
        Assertions.assertEquals(catOwners.getPhone(), createdCatOwners.getPhone());
        Assertions.assertEquals(catOwners.getId(), createdCatOwners.getId());
    }
    @Test
    public void testUpdateCatOwners() {
        CatOwners catOwners1 = new CatOwners();
        catOwners1.setName("John");
        catOwners1.setAddress("123 Main Street");
        catOwners1.setId(1L);
        CatOwners catOwners2 = new CatOwners();
        catOwners2.setName("Mary");
        catOwners2.setAddress("456 Elm Street");
        catOwners2.setId(2L);
        CatOwners catOwners3 = new CatOwners();
        catOwners3.setName("Bob");
        catOwners3.setAddress("789 Oak Street");
        catOwners3.setId(3L);
        Mockito.when(repository.getById(1L)).thenReturn(catOwners1);
        CatOwners updatedCatOwners1 = service.update(catOwners1);
        CatOwners updatedCatOwners2 = service.update(catOwners2);
        CatOwners updatedCatOwners3 = service.update(catOwners3);
        Assertions.assertEquals(updatedCatOwners1.getName(), "John");
        Assertions.assertEquals(updatedCatOwners1.getAddress(), "123 Main Street");
        Assertions.assertEquals(updatedCatOwners1.getId(), Long.valueOf(1L));
        Assertions.assertEquals(updatedCatOwners2.getName(), "Mary");
        Assertions.assertEquals(updatedCatOwners2.getAddress(), "456 Elm Street");
        Assertions.assertEquals(updatedCatOwners2.getId(), Long.valueOf(2L));
        Assertions.assertEquals(updatedCatOwners3.getName(), "Bob");
        Assertions.assertEquals(updatedCatOwners3.getAddress(), "789 Oak Street");
        Assertions.assertEquals(updatedCatOwners3.getId(), Long.valueOf(3L));
        CatOwners catOwners4 = new CatOwners();
        Assertions.assertThrows(CatOwnersNotFoundException.class, () -> {
            service.update(catOwners4);
        });
    }

}

