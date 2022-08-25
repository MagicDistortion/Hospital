package project.appointments;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AppointmentTest {
    Appointment appointment =new Appointment("Лазерна коррекція");

    @Test
    void getId() {
        Assertions.assertEquals(0,appointment.getId());
    }

    @Test
    void setId() {
        int oldId = appointment.getId();
        appointment.setId(3);
        Assertions.assertEquals(3,appointment.getId());
        Assertions.assertNotEquals(oldId,appointment.getId());
    }

    @Test
    void getName() {Assertions.assertEquals("Лазерна коррекція",appointment.getName());
    }

    @Test
    void setName() {
        String oldName = appointment.getName();
        appointment.setName("Коррекція зору");
        Assertions.assertNotEquals(oldName,appointment.getName());
        Assertions.assertEquals("Коррекція зору",appointment.getName());

        }

    @Test
    void testToString() {
        Assertions.assertEquals("AppointmentЛазерна коррекція",appointment.toString());
    }
}