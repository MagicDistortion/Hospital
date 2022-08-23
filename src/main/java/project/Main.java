package project;

import project.appointments.AppointmentDetails;
import project.methods.DBManager;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


public class Main {
    public static void main(String[] args) {
        DBManager dbManager = DBManager.getInstance();
    }
}
