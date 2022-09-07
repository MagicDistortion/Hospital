package project.users;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import project.models.users.SysAdmin;
import project.models.users.User;

import java.time.LocalDate;

class SysAdminTest {
    User user = new User("Baranets", "Artem", "admin"
            , "9582", "0662252039", LocalDate.of(1993, 3, 10));
    SysAdmin sysAdmin = new SysAdmin(user);

    @Test
    void getId() {
        Assertions.assertEquals(0,sysAdmin.getId());
    }

    @Test
    void setId() {
        int oldId = sysAdmin.getId();
        sysAdmin.setId(3);
        Assertions.assertNotEquals(oldId,sysAdmin.getId());
        Assertions.assertEquals(3,sysAdmin.getId());
    }

    @Test
    void getSurname() {
        Assertions.assertEquals("Baranets",sysAdmin.getSurname());
    }

    @Test
    void setSurname() {
        String oldSurname = sysAdmin.getSurname();
        sysAdmin.setSurname("Newman");
        Assertions.assertNotEquals(oldSurname,sysAdmin.getSurname());
        Assertions.assertEquals("Newman",sysAdmin.getSurname());
    }

    @Test
    void getName() {Assertions.assertEquals("Artem",sysAdmin.getName());
    }

    @Test
    void setName() {
        String oldName = sysAdmin.getName();
        sysAdmin.setName("Temik");
        Assertions.assertNotEquals(oldName,sysAdmin.getName());
        Assertions.assertEquals("Temik",sysAdmin.getName());
    }

    @Test
    void testToString() {Assertions.assertEquals("SysAdmin Baranets Artem",sysAdmin.toString());
    }
}