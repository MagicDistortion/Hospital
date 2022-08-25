package project.users;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import project.categories.Categories;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class DoctorTest {
    User user = new User("Baranets", "Artem", "admin"
            , "9582", "0662252039", LocalDate.of(1993, 3, 10));
    Doctor doctor = new Doctor(user);

    @Test
    void getCategoryId() {
        Assertions.assertEquals(0, doctor.getCategoryId());
    }

    @Test
    void setCategoryId() {
        int oldCategoryId = doctor.getCategoryId();
        doctor.setCategoryId(3);
        Assertions.assertNotEquals(oldCategoryId, doctor.getCategoryId());
        assertEquals(3, doctor.getCategoryId());
    }

    @Test
    void getCategory() {
        Categories category = new Categories("Окуліст");
        doctor.setCategory(category.getName());
        Assertions.assertEquals(category.getName(), doctor.getCategory());
    }

    @Test
    void setCategory() {
        String oldCategory = doctor.getCategory();
        doctor.setCategory("Терапевт");
        Assertions.assertNotEquals(oldCategory, doctor.getCategory());
        assertEquals("Терапевт", doctor.getCategory());
    }

    @Test
    void getId() {
        Assertions.assertEquals(0, doctor.getId());
    }

    @Test
    void setId() {
        int oldId = doctor.getId();
        doctor.setId(5);
        Assertions.assertNotEquals(oldId, doctor.getId());
        Assertions.assertEquals(5, doctor.getId());
    }

    @Test
    void getSurname() {
        Assertions.assertEquals("Baranets", doctor.getSurname());
        Assertions.assertEquals(user.getSurname(), doctor.getSurname());
    }

    @Test
    void setSurname() {
        String oldSurname = doctor.getSurname();
        doctor.setSurname("Kozlenko");
        Assertions.assertNotEquals(oldSurname, doctor.getSurname());
        Assertions.assertEquals("Kozlenko", doctor.getSurname());
    }

    @Test
    void getName() {
        Assertions.assertEquals("Artem", doctor.getName());
        Assertions.assertEquals(user.getName(), doctor.getName());
    }

    @Test
    void setName() {
        String oldName = doctor.getName();
        doctor.setName("Tema");
        Assertions.assertNotEquals(oldName, doctor.getName());
        Assertions.assertEquals("Tema", doctor.getName());
        Assertions.assertNotEquals(oldName, doctor.getName());
    }

    @Test
    void getNumberOfPatients() {
        Assertions.assertEquals(0, doctor.getNumberOfPatients());
    }

    @Test
    void setNumberOfPatients() {
        int oldNumber = doctor.getNumberOfPatients();
        doctor.setNumberOfPatients(3);
        Assertions.assertNotEquals(oldNumber, doctor.getNumberOfPatients());
        Assertions.assertEquals(3, doctor.getNumberOfPatients());
    }

    @Test
    void testToString() {
        Categories category = new Categories("ЛОР");
        category.setName("лор");
        category.setId(2);
        doctor.setCategory(category.getName());
        doctor.setCategoryId(category.getId());
        doctor.setNumberOfPatients(3);
        Assertions.assertEquals("Doctor Baranets Artem " + category.getName() + " " + category.getId() + " 3", doctor.toString());
    }
}