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
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
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
    //Не знаю что не так с этим тестом мозг уже сломал
    @Test
    public void testUpdateCatOwners() {
        CatOwners catOwners1 = new CatOwners();
        catOwners1.setName("John");
        catOwners1.setPhone("123");
        catOwners1.setId(1L);
        CatOwners catOwners2 = new CatOwners();
        catOwners2.setName("Mary");
        catOwners2.setPhone("456");
        catOwners2.setId(2L);
        CatOwners catOwners3 = new CatOwners();
        catOwners3.setName("Bob");
        catOwners3.setPhone("789");
        catOwners3.setId(3L);
        Mockito.when(repository.getById(1L)).thenReturn(catOwners1);
        CatOwners updatedCatOwners1 = service.update(catOwners1);
        CatOwners updatedCatOwners2 = service.update(catOwners2);
        CatOwners updatedCatOwners3 = service.update(catOwners3);
        Assertions.assertEquals(updatedCatOwners1.getName(), "John");
        Assertions.assertEquals(updatedCatOwners1.getPhone(), "123");
        Assertions.assertEquals(updatedCatOwners1.getId(), Long.valueOf(1L));
        Assertions.assertEquals(updatedCatOwners2.getName(), "Mary");
        Assertions.assertEquals(updatedCatOwners2.getPhone(), "456");
        Assertions.assertEquals(updatedCatOwners2.getId(), Long.valueOf(2L));
        Assertions.assertEquals(updatedCatOwners3.getName(), "Bob");
        Assertions.assertEquals(updatedCatOwners3.getPhone(), "789");
        Assertions.assertEquals(updatedCatOwners3.getId(), Long.valueOf(3L));
    }
    @Test
    public void testRemoveById() {
        Long id = 1L;
        CatOwnersRepository mockRepository = mock(CatOwnersRepository.class);
        doNothing().when(mockRepository).deleteById(id);
        CatOwnersService service = new CatOwnersService(mockRepository);
        service.removeById(id);
        Mockito.verify(mockRepository, Mockito.times(1)).deleteById(id);
    }
    @Test
    public void testGetAll() {
        List<CatOwners> catOwnersList =  new ArrayList<>();
        CatOwners catOwners1 = new CatOwners("John", "102", 1L);
        catOwnersList.add(catOwners1);
        CatOwners catOwners2 = new CatOwners("Johny", "103", 2L);
        catOwnersList.add(catOwners2);
        Mockito.when(repository.findAll()).thenReturn(catOwnersList);
        CatOwnersService service = new CatOwnersService(repository);
        Collection<CatOwners> result = service.getAll();
        Assertions.assertEquals(catOwnersList, result);
        Mockito.verify(repository, Mockito.times(1)).findAll();
    }
    //Тоже есть проблема (возможно не правильно указываю service, а нужен репозиторий или что-то в этом роде)
    @Test
    public void testGetByChatId() {
        CatOwners catOwners1 = new CatOwners("John", "102", 1L);
        repository.save(catOwners1);
        CatOwners catOwners2 = new CatOwners("Johny", "103", 2L);
        repository.save(catOwners2);
        Collection<CatOwners> result = service.getByChatId(2L);
        org.assertj.core.api.Assertions.assertThat(result).hasSize(1);
        org.assertj.core.api.Assertions.assertThat(result).extracting(CatOwners::getName).containsExactly("Johny");
    }
}

