package project.models.users;

import java.time.LocalDate;

/* Клас пацієнтів */

public class Patient {

    private int id;
    private String surname;
    private String name;
    private LocalDate dateOfBirth;
    private int currentDoctorId;

    public Patient(User user) {
        this.id = user.getId();
        this.surname = user.getSurname();
        this.name = user.getName();
        this.dateOfBirth = user.getDateOfBirth();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public int getCurrentDoctorId() {
        return currentDoctorId;
    }

    public void setCurrentDoctorId(int currentDoctor) {
        this.currentDoctorId = currentDoctor;
    }

    @Override
    public String toString() {
        return "Patient " + surname + " " + name + " " + dateOfBirth;
    }
}
