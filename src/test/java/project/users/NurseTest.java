package project.users;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import project.models.users.Nurse;
import project.models.users.User;

import java.time.LocalDate;

class NurseTest {
    User user = new User("Baranets", "Artem", "admin"
            , "9582", "0662252039", LocalDate.of(1993, 3, 10));
    Nurse nurse = new Nurse(user);

    @Test
    void getId() {
        Assertions.assertEquals(0, nurse.getId());
    }

    @Test
    void setId() {
        int oldId = nurse.getId();
        nurse.setId(8);
        Assertions.assertNotEquals(oldId, nurse.getId());
        Assertions.assertEquals(8, nurse.getId());
    }

    @Test
    void getSurname() {
        Assertions.assertEquals("Baranets", nurse.getSurname());
    }

    @Test
    void setSurname() {
        String oldSurname = nurse.getSurname();
        nurse.setSurname("Maxno");
        Assertions.assertNotEquals(oldSurname, nurse.getSurname());
        Assertions.assertEquals("Maxno", nurse.getSurname());
    }

    @Test
    void getName() {
        Assertions.assertEquals("Artem", nurse.getName());
        Assertions.assertEquals(user.getName(), nurse.getName());
    }

    @Test
    void setName() {
        String oldName = nurse.getName();
        nurse.setName("Tema");
        Assertions.assertNotEquals(oldName, nurse.getName());
        Assertions.assertEquals("Tema", nurse.getName());
        Assertions.assertNotEquals(oldName, nurse.getName());
    }

    @Test
    void testToString() {
        Assertions.assertEquals("Nurse Baranets Artem", nurse.toString());
    }
}