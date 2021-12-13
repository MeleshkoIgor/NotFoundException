package ru.netology.repository;

import org.junit.jupiter.api.Test;
import ru.netology.domain.Book;
import ru.netology.domain.NotFoundException;
import ru.netology.domain.Product;
import ru.netology.domain.TShirt;


import static org.junit.jupiter.api.Assertions.*;

class ProductRepositoryTest {
    private ProductRepository repository = new ProductRepository();
    private Book Java = new Book();

    @Test
    public void shouldSaveOneItem() {
        repository.save(Java);

        Product[] expected = new Product[]{Java};
        Product[] actual = repository.findAll();
        assertArrayEquals(expected, actual);
    }

    @Test
    void removeByNotFoundId() {
        Book java1 = new Book(1, "Java1", 500, "Ivanov", 321, 2015);
        Book java2 = new Book(5, "Java2", 1000, "Ivanov", 123, 2020);


        repository.save(java1);
        repository.save(java2);

        assertThrows(NotFoundException.class, () -> {
            repository.removeById(3);
        });
    }

    @Test
    void removeById() {
        Book java1 = new Book(1, "Java1", 500, "Ivanov", 321, 2015);
        Book java2 = new Book(5, "Java2", 1000, "Ivanov", 123, 2020);
        Book java3 = new Book(7, "Java3", 750, "Ivanov", 432, 2021);

        repository.save(java1);
        repository.save(java2);
        repository.save(java3);

        repository.removeById(1);

        Product[] expected = new Product[]{java2, java3};
        Product[] actual = repository.findAll();
        assertArrayEquals(expected, actual);
    }
}