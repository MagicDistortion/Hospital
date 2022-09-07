package project.roles;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import project.models.roles.Role;

class RoleTest {
    Role role=new Role("guest");

    @Test
    void getId() {
        Assertions.assertEquals(0,role.getId());
    }

    @Test
    void setId() {
        int oldId = role.getId();
        role.setId(4);
        Assertions.assertEquals(4,role.getId());
        Assertions.assertNotEquals(oldId,role.getId());
    }

    @Test
    void getName() {Assertions.assertEquals("guest", role.getName());
    }

    @Test
    void setName() {
        String oldName = role.getName();
        role.setName("usyk");
        Assertions.assertNotEquals(oldName, role.getName());
        Assertions.assertEquals("usyk", role.getName());
    }
}