package project.hospitalcard;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

class HospitalCardTest {

    HospitalCard hospitalCard = new HospitalCard(8);

    {
        hospitalCard.setStatus("лікується");
        hospitalCard.setCurrentDoctorSurname("Сергієнко");
        hospitalCard.setCurrentDoctorName("Дмитро");
        hospitalCard.setPatientsSurname("Дімідов");
        hospitalCard.setPatientsName("Сашко");
        hospitalCard.setDiagnosis("недостатність сердечного клапану");
        hospitalCard.setCreateTime(LocalDate.of(2022, 3, 9));
        hospitalCard.setDoctorsId(1);
    }

    @Test
    void getId() {
        Assertions.assertEquals(8, hospitalCard.getId());
    }

    @Test
    void setId() {
        int oldId = hospitalCard.getId();
        hospitalCard.setId(9);
        Assertions.assertEquals(9, hospitalCard.getId());
        Assertions.assertNotEquals(oldId, hospitalCard.getId());
    }

    @Test
    void getDiagnosis() {
        Assertions.assertEquals("недостатність сердечного клапану",hospitalCard.getDiagnosis());
    }

    @Test
    void setDiagnosis() {
        hospitalCard.setDiagnosis("слабке серце");
        Assertions.assertEquals("слабке серце", hospitalCard.getDiagnosis());
    }

    @Test
    void getDoctorsId() {
        Assertions.assertEquals(1, hospitalCard.getDoctorsId());
    }

    @Test
    void setDoctorsId() {
        int oldId = hospitalCard.getDoctorsId();
        hospitalCard.setDoctorsId(9);
        Assertions.assertEquals(9, hospitalCard.getDoctorsId());
        Assertions.assertNotEquals(oldId, hospitalCard.getDoctorsId());
    }

    @Test
    void getPatientsSurname() {
        Assertions.assertEquals("Дімідов",hospitalCard.getPatientsSurname());
    }

    @Test
    void setPatientsSurname() {
        String oldSurname = hospitalCard.getPatientsSurname();
        hospitalCard.setPatientsSurname("Олексіїв");
        Assertions.assertNotEquals(oldSurname,hospitalCard.getPatientsSurname());
        Assertions.assertEquals("Олексіїв",hospitalCard.getPatientsSurname());
    }

    @Test
    void getPatientsName() {
        Assertions.assertEquals("Сашко",hospitalCard.getPatientsName());
    }

    @Test
    void setPatientsName() {
        String oldName = hospitalCard.getPatientsName();
        hospitalCard.setPatientsName("Андрій");
        Assertions.assertNotEquals(oldName,hospitalCard.getPatientsName());
        Assertions.assertEquals("Андрій",hospitalCard.getPatientsName());
    }

    @Test
    void getCreateTime() {Assertions.assertEquals(LocalDate.of(2022, 3, 9),hospitalCard.getCreateTime());
    }

    @Test
    void setCreateTime() {
        LocalDate oldDate = hospitalCard.getCreateTime();
        hospitalCard.setCreateTime(LocalDate.of(2022,4,8));
        Assertions.assertNotEquals(oldDate,hospitalCard.getCreateTime());
        Assertions.assertEquals(LocalDate.of(2022,4,8),hospitalCard.getCreateTime());
    }

    @Test
    void getCurrentDoctorSurname() {Assertions.assertEquals("Сергієнко",hospitalCard.getCurrentDoctorSurname());
    }

    @Test
    void setCurrentDoctorSurname() {
        String oldSurname = hospitalCard.getCurrentDoctorSurname();
        hospitalCard.setCurrentDoctorSurname("Петренко");
        Assertions.assertNotEquals(oldSurname,hospitalCard.getCurrentDoctorSurname());
        Assertions.assertEquals("Петренко",hospitalCard.getCurrentDoctorSurname());
    }

    @Test
    void getStatus() {Assertions.assertEquals("лікується",hospitalCard.getStatus());
    }

    @Test
    void setStatus() {
        String oldStatus = hospitalCard.getStatus();
        hospitalCard.setStatus("вилікувано");
        Assertions.assertNotEquals(oldStatus,hospitalCard.getStatus());
        Assertions.assertEquals("вилікувано", hospitalCard.getStatus());
    }

    @Test
    void getCurrentDoctorName() {
        Assertions.assertEquals(("Дмитро"),hospitalCard.getCurrentDoctorName());
    }

    @Test
    void setCurrentDoctorName() {
        String oldName = hospitalCard.getCurrentDoctorName();
        hospitalCard.setCurrentDoctorName("Андрій");
        Assertions.assertNotEquals(oldName,hospitalCard.getCurrentDoctorName());
        Assertions.assertEquals("Андрій",hospitalCard.getCurrentDoctorName());
    }

    @Test
    void testToString() {
        Assertions.assertEquals("\n HospitalCard{, patientsSurname='Дімідов', patientsName='Сашко'" +
                ", diagnosis='недостатність сердечного клапану', createTime=2022-03-09" +
                ", currentDoctorSurname='Сергієнко', currentDoctorName='Дмитро'}",hospitalCard.toString());
    }
}