package project.categories;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import project.models.categories.Categories;

class CategoriesTest {
    Categories category = new Categories("Травмотолог");

    @Test
    void getId() {
        Assertions.assertEquals(0, category.getId());
    }

    @Test
    void setId() {
        int oldId = category.getId();
        category.setId(4);
        Assertions.assertEquals(4, category.getId());
        Assertions.assertNotEquals(oldId, category.getId());
    }

    @Test
    void getName() {
        Assertions.assertEquals("Травмотолог", category.getName());
    }

    @Test
    void setName() {
        String oldName = category.getName();
        category.setName("Окуліст");
        Assertions.assertEquals("Окуліст", category.getName());
        Assertions.assertNotEquals(oldName, category.getName());
    }

    @Test
    void testToString() {
        Assertions.assertEquals("Category Травмотолог", category.toString());
    }
}