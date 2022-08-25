package project.users;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

class PatientTest {
    User user = new User("Baranets", "Artem", "admin"
            , "9582", "0662252039", LocalDate.of(1993, 3, 10));
    Patient patient = new Patient(user);

    @Test
    void getId() {
        Assertions.assertEquals(0, patient.getId());
    }

    @Test
    void setId() {
        int oldId = patient.getId();
        patient.setId(5);
        Assertions.assertNotEquals(oldId, patient.getId());
        Assertions.assertEquals(5, patient.getId());
    }

    @Test
    void getSurname() {
        Assertions.assertEquals("Baranets", patient.getSurname());
        Assertions.assertEquals(user.getSurname(), patient.getSurname());
    }

    @Test
    void setSurname() {
        String oldSurname = patient.getSurname();
        patient.setSurname("Kozlenko");
        Assertions.assertNotEquals(oldSurname, patient.getSurname());
        Assertions.assertEquals("Kozlenko", patient.getSurname());
    }

    @Test
    void getName() {
        Assertions.assertEquals("Artem", patient.getName());
        Assertions.assertEquals(user.getName(), patient.getName());
    }

    @Test
    void setName() {
        String oldName = patient.getName();
        patient.setName("Tema");
        Assertions.assertNotEquals(oldName, patient.getName());
        Assertions.assertEquals("Tema", patient.getName());
        Assertions.assertNotEquals(oldName, patient.getName());
    }

    @Test
    void getDateOfBitrth() {
        Assertions.assertEquals(LocalDate.of(1993, 3, 10), user.getDateOfBirth());
    }

    @Test
    void setDateOfBitrth() {
        LocalDate oldDate = patient.getDateOfBirth();
        patient.setDateOfBirth(LocalDate.of(1994, 10, 11));
        Assertions.assertNotEquals(oldDate, patient.getDateOfBirth());
    }

    @Test
    void getCurrentDoctorId() {
        Assertions.assertEquals(0, patient.getCurrentDoctorId());
    }

    @Test
    void setCurrentDoctorId() {
        int oldId = patient.getCurrentDoctorId();
        patient.setCurrentDoctorId(9);
        Assertions.assertEquals(9, patient.getCurrentDoctorId());
        Assertions.assertNotEquals(oldId, patient.getCurrentDoctorId());
    }

    @Test
    void testToString() {
        Assertions.assertEquals("Patient Baranets Artem 1993-03-10",patient.toString());
    }
}