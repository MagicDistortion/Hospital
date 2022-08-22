package project.hospitalcard;

import java.time.LocalDate;

public class HospitalCard {
    private int id;
    private int doctorsId;
    private String diagnosis;
    private String patientsSurname;
    private String patientsName;
    private LocalDate createTime;
    private String currentDoctorSurname;
    private String currentDoctorName;
    public HospitalCard(int id) {
    this.id = id;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDiagnosis() {
        return diagnosis;
    }

    public void setDiagnosis(String diagnosis) {
        this.diagnosis = diagnosis;
    }

    public int getDoctorsId() {
        return doctorsId;
    }

    public void setDoctorsId(int doctorsId) {
        this.doctorsId = doctorsId;
    }

    public String getPatientsSurname() {
        return patientsSurname;
    }

    public void setPatientsSurname(String patientsSurname) {
        this.patientsSurname = patientsSurname;
    }

    public String getPatientsName() {
        return patientsName;
    }

    public void setPatientsName(String patientsName) {
        this.patientsName = patientsName;
    }

    public LocalDate getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDate createTime) {
        this.createTime = createTime;
    }

    public String getCurrentDoctorSurname() {
        return currentDoctorSurname;
    }

    public void setCurrentDoctorSurname(String currentDoctorSurname) {
        this.currentDoctorSurname = currentDoctorSurname;
    }

    public String getCurrentDoctorName() {
        return currentDoctorName;
    }

    public void setCurrentDoctorName(String currentDoctorName) {
        this.currentDoctorName = currentDoctorName;
    }

    @Override
    public String toString() {
        return "\n HospitalCard{" +
                ", patientsSurname='" + patientsSurname + '\'' +
                ", patientsName='" + patientsName + '\'' +
                ", diagnosis='" + diagnosis + '\'' +
                ", createTime=" + createTime +
                ", currentDoctorSurname='" + currentDoctorSurname + '\'' +
                ", currentDoctorName='" + currentDoctorName + '\'' +
                '}';
    }
}
