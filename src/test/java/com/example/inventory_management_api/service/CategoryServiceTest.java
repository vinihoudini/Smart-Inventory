package com.example.inventory_management_api.service;

import com.example.inventory_management_api.model.Category;
import com.example.inventory_management_api.repository.CategoryRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class CategoryServiceTest {

    @Mock
    private CategoryRepository categoryRepository;

    @InjectMocks
    private CategoryService categoryService;

    @Test
    public void testCreateCategory() {
        Category category = new Category();
        category.setName("Clothing");
        category.setDescription("Apparel items");

        // Altere para o método correto
        Mockito.when(categoryRepository.save(category)).thenReturn(category);

        // Altere para o método correto: createCategory()
        Category savedCategory = categoryService.createCategory(category);
        assertEquals(category.getName(), savedCategory.getName());
    }

    @Test
    public void testFindCategoryById() {
        Category category = new Category();
        category.setId(1L);
        category.setName("Appliances");

        // Simula a busca da categoria
        Mockito.when(categoryRepository.findById(1L)).thenReturn(java.util.Optional.of(category));

        // Crie o método getCategoryById() no serviço
        Category foundCategory = categoryService.getCategoryById(1L);
        assertNotNull(foundCategory);
        assertEquals("Appliances", foundCategory.getName());
    }
}
