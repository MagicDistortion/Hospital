package project.users;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;


class UserTest {
    User user = new User("Baranets", "Artem", "admin"
            , "9582", "0662252039", LocalDate.of(1993, 3, 10));

    @Test
    void setId() {
        int oldId = user.getId();
        user.setId(1);
        Assertions.assertNotEquals(oldId, user.getId());
    }

    @Test
    void getId() {
        Assertions.assertEquals(0, user.getId());
    }

    @Test
    void getLogin() {
        Assertions.assertEquals("admin", user.getLogin());
    }

    @Test
    void setLogin() {
        String oldLogin = user.getLogin();
        user.setLogin("noob");
        Assertions.assertNotEquals(oldLogin, user.getLogin());
    }

    @Test
    void getPassword() {
        Assertions.assertEquals("9582", user.getPassword());
    }

    @Test
    void setPassword() {
        String oldPassword = user.getPassword();
        user.setPassword("noob");
        Assertions.assertNotEquals(oldPassword, user.getPassword());
    }

    @Test
    void getTel() {
        Assertions.assertEquals("380662252039", user.getTel());
    }

    @Test
    void setTel() {
        user.setTel("0509920454");
        Assertions.assertEquals("380509920454", user.getTel());
    }

    @Test
    void getSurname() {
        Assertions.assertEquals("Baranets", user.getSurname());
    }

    @Test
    void setSurname() {
        String oldSurname = user.getSurname();
        user.setSurname("noob");
        Assertions.assertNotEquals(oldSurname, user.getSurname());
    }

    @Test
    void getName() {
        Assertions.assertEquals("Artem", user.getName());
    }

    @Test
    void setName() {
        String oldName = user.getName();
        user.setName("noob");
        Assertions.assertNotEquals(oldName, user.getName());
    }

    @Test
    void getRolesId() {
        Assertions.assertEquals(0, user.getRolesId());
    }

    @Test
    void setRolesId() {
        int oldRole = user.getRolesId();
        user.setRolesId(2);
        Assertions.assertNotEquals(oldRole, user.getRolesId());
        Assertions.assertEquals(2, user.getRolesId());
    }

    @Test
    void getDateOfBirth() {
        Assertions.assertEquals(LocalDate.of(1993, 3, 10), user.getDateOfBirth());
    }

    @Test
    void setDateOfBirth() {
        LocalDate oldDate = user.getDateOfBirth();
        user.setDateOfBirth(LocalDate.of(1994, 10, 11));
        Assertions.assertNotEquals(oldDate, user.getDateOfBirth());
    }

    @Test
    void testToString() {
        System.out.println(user.toString());
        Assertions.assertEquals("User Baranets Artem admin 380662252039 1993-03-10", user.toString());
    }
}