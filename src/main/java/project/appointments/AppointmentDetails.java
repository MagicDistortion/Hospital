package project.appointments;

import java.time.LocalDate;

public class AppointmentDetails {
    int id;
    String text;
    int nurseId;
    int appointmentId;
    int doctorsId;
    int hospitalCardId;
    String doctorFullName;
    String nurseFullName;
    String patientFullName;
    String appointment;
    LocalDate date;


    public AppointmentDetails(String text, LocalDate date) {
        this.date = date;
        this.text = text;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getNurseId() {
        return nurseId;
    }

    public void setNurseId(int nurseId) {
        this.nurseId = nurseId;
    }

    public int getAppointmentId() {
        return appointmentId;
    }

    public void setAppointmentId(int appointmentId) {
        this.appointmentId = appointmentId;
    }

    public int getDoctorsId() {
        return doctorsId;
    }

    public void setDoctorsId(int doctorsId) {
        this.doctorsId = doctorsId;
    }

    public int getHospitalCardId() {
        return hospitalCardId;
    }

    public void setHospitalCardId(int hospitalCardId) {
        this.hospitalCardId = hospitalCardId;
    }

    public String getDoctorFullName() {
        return doctorFullName;
    }

    public String getAppointment() {
        return appointment;
    }

    public void setAppointment(String appointment) {
        this.appointment = appointment;
    }

    public void setDoctorFullName(String doctorFullName) {
        this.doctorFullName = doctorFullName;
    }

    public String getNurseFullName() {
        return nurseFullName;
    }

    public void setNurseFullName(String nurseFullName) {
        this.nurseFullName = nurseFullName;
    }

    public String getPatientFullName() {
        return patientFullName;
    }

    public void setPatientFullName(String patientFullName) {
        this.patientFullName = patientFullName;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Appointment{" + text + '}';
    }
}
