package project.appointments;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class AppointmentDetailsTest {
    AppointmentDetails appointmentDetails = new AppointmentDetails("бути обережними", LocalDate.of(2022, 9, 11));

    @Test
    void getId() {
        Assertions.assertEquals(0, appointmentDetails.getId());
    }

    @Test
    void setId() {
        int oldId = appointmentDetails.getId();
        appointmentDetails.setId(1);
        Assertions.assertEquals(1, appointmentDetails.getId());
        Assertions.assertNotEquals(oldId, appointmentDetails.getId());
    }

    @Test
    void getText() {
        Assertions.assertEquals("бути обережними", appointmentDetails.getText());
    }

    @Test
    void setText() {
        String oldText = appointmentDetails.getText();
        appointmentDetails.setText("дуже обережно");
        Assertions.assertNotEquals(oldText, appointmentDetails.getText());
        Assertions.assertEquals("дуже обережно", appointmentDetails.getText());
    }

    @Test
    void getNurseId() {
        Assertions.assertEquals(0, appointmentDetails.getNurseId());
    }

    @Test
    void setNurseId() {
        int oldId = appointmentDetails.getNurseId();
        appointmentDetails.setNurseId(1);
        Assertions.assertEquals(1, appointmentDetails.getNurseId());
        Assertions.assertNotEquals(oldId, appointmentDetails.getNurseId());
    }

    @Test
    void getAppointmentId() {
        Assertions.assertEquals(0, appointmentDetails.getAppointmentId());
    }

    @Test
    void setAppointmentId() {
        int oldId = appointmentDetails.getAppointmentId();
        appointmentDetails.setAppointmentId(1);
        Assertions.assertEquals(1, appointmentDetails.getAppointmentId());
        Assertions.assertNotEquals(oldId, appointmentDetails.getAppointmentId());
    }

    @Test
    void getDoctorsId() {
        Assertions.assertEquals(0, appointmentDetails.getDoctorsId());
    }

    @Test
    void setDoctorsId() {
        int oldId = appointmentDetails.getDoctorsId();
        appointmentDetails.setDoctorsId(9);
        Assertions.assertEquals(9, appointmentDetails.getDoctorsId());
        Assertions.assertNotEquals(oldId, appointmentDetails.getDoctorsId());
    }

    @Test
    void getHospitalCardId() {
        Assertions.assertEquals(0, appointmentDetails.getHospitalCardId());
    }

    @Test
    void setHospitalCardId() {
        int oldId = appointmentDetails.getHospitalCardId();
        appointmentDetails.setHospitalCardId(3);
        Assertions.assertEquals(3, appointmentDetails.getHospitalCardId());
        Assertions.assertNotEquals(oldId, appointmentDetails.getHospitalCardId());
    }

    @Test
    void getDoctorFullName() {
        Assertions.assertNull(appointmentDetails.getDoctorFullName());
    }

    @Test
    void setDoctorFullName() {
        String oldFullName = appointmentDetails.getDoctorFullName();
        appointmentDetails.setDoctorFullName("Коваленко Сергій");
        Assertions.assertEquals("Коваленко Сергій", appointmentDetails.getDoctorFullName());
        Assertions.assertNotEquals(oldFullName, appointmentDetails.getDoctorFullName());
    }

    @Test
    void getAppointment() {
        Assertions.assertNull(appointmentDetails.getAppointment());
    }

    @Test
    void setAppointment() {
        Appointment appointment = new Appointment("Коррекція зору");
        appointmentDetails.setAppointment(appointment.getName());
        Assertions.assertEquals(appointment.getName(), appointmentDetails.getAppointment());
    }

    @Test
    void getNurseFullName() {
        Assertions.assertNull(appointmentDetails.getNurseFullName());
    }

    @Test
    void setNurseFullName() {
        String oldFullName = appointmentDetails.getNurseFullName();
        appointmentDetails.setNurseFullName("Коваленко Олександра");
        Assertions.assertEquals("Коваленко Олександра", appointmentDetails.getNurseFullName());
        Assertions.assertNotEquals(oldFullName, appointmentDetails.getNurseFullName());
    }

    @Test
    void getPatientFullName() {
        Assertions.assertNull(appointmentDetails.getPatientFullName());
    }

    @Test
    void setPatientFullName() {
        String oldFullName = appointmentDetails.getPatientFullName();
        appointmentDetails.setPatientFullName("Дмитренко Сергій");
        Assertions.assertEquals("Дмитренко Сергій", appointmentDetails.getPatientFullName());
        Assertions.assertNotEquals(oldFullName, appointmentDetails.getPatientFullName());
    }

    @Test
    void getStatus() {
        Assertions.assertNull(appointmentDetails.getStatus());
    }

    @Test
    void setStatus() {
        appointmentDetails.setStatus("лікується");
        Assertions.assertEquals("лікується", appointmentDetails.getStatus());
    }

    @Test
    void getDate() {
        Assertions.assertEquals(LocalDate.of(2022, 9, 11), appointmentDetails.getDate());
    }

    @Test
    void setDate() {
        LocalDate oldDate = appointmentDetails.getDate();
        appointmentDetails.setDate(LocalDate.of(2022, 10, 11));
        Assertions.assertNotEquals(oldDate, appointmentDetails.getDate());
        Assertions.assertEquals(LocalDate.of(2022, 10, 11), appointmentDetails.getDate());
    }

    @Test
    void testToString() {
        Assertions.assertEquals("Appointment бути обережними", appointmentDetails.toString());
    }
}