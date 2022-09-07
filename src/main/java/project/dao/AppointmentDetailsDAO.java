package project.dao;

import org.apache.log4j.Logger;
import project.Constants;
import project.models.appointments.AppointmentDetails;
import project.models.users.Doctor;
import project.models.users.Nurse;
import project.models.users.Patient;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class AppointmentDetailsDAO {
    private final static Logger logger = Logger.getLogger(AppointmentDetailsDAO.class);
    private final DBManager dbManager = DBManager.getInstance();
    private final DoctorsDAO doctorsDAO = new DoctorsDAO();
    private final NursesDAO nursesDAO = new NursesDAO();
    private final PatientsDAO patientsDAO = new PatientsDAO();

    /* метод додавання додаткової інформації для поточного призначення  */
    public void insertAppointmentDetails(AppointmentDetails appointmentDetails) {
        try (Connection connection = dbManager.getConnection();
             PreparedStatement preparedStatement
                     = connection.prepareStatement(Constants.INSERT_APPOINTMENT_DETAIL, PreparedStatement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, appointmentDetails.getText());
            preparedStatement.setInt(2, appointmentDetails.getNurseId());
            preparedStatement.setInt(3, appointmentDetails.getAppointmentId());
            preparedStatement.setInt(4, appointmentDetails.getDoctorsId());
            preparedStatement.setInt(5, appointmentDetails.getHospitalCardId());
            preparedStatement.setObject(6, appointmentDetails.getDate());
            preparedStatement.executeUpdate();
            try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                generatedKeys.next();
                appointmentDetails.setId(generatedKeys.getInt(1));
            }
        } catch (SQLException e) {
            logger.error(e);
            throw new RuntimeException(e);
        }
    }

    /* метод оновлення статусу призначення  */
    public void updateAppointmentStatus(String status, int id) {
        try (Connection connection = dbManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(Constants.UPDATE_APPOINTMENT_STATUS)) {
            preparedStatement.setString(1, status);
            preparedStatement.setInt(2, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            logger.error(e);
            throw new RuntimeException(e);
        }
    }
    /* метод отримання списку невиконаних та не просрочених за датою призначень у лікарняній картці по id картки  */
    public List<AppointmentDetails> findAllAppointmentDetailsByID(int hospitalCardId) {
        List<AppointmentDetails> appointmentDetailsList = new ArrayList<>();
        try (Connection connection = dbManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(Constants.FROM_APPOINTMENT_DETAIL)) {
            preparedStatement.setInt(1, hospitalCardId);
            preparedStatement.execute();
            try (ResultSet resultSet = preparedStatement.getResultSet()) {
                while (resultSet.next()) {
                    LocalDate date = resultSet.getDate("date").toLocalDate();
                    if (date.equals(LocalDate.now()) || date.isAfter(LocalDate.now())) {
                        Doctor doctor = doctorsDAO.findDoctorById(resultSet.getInt("doctors_id"));
                        Nurse nurse = nursesDAO.findNurseById(resultSet.getInt("nurse_id"));

                        AppointmentDetails appointmentDetails
                                = new AppointmentDetails(resultSet.getString("text"), date);
                        appointmentDetails.setId(resultSet.getInt("id"));
                        appointmentDetails.setNurseId(resultSet.getInt("nurse_id"));
                        appointmentDetails.setAppointmentId(resultSet.getInt("appointment_id"));
                        appointmentDetails.setDoctorsId(resultSet.getInt("doctors_id"));
                        appointmentDetails.setHospitalCardId(resultSet.getInt("hospital_card_id"));
                        appointmentDetails.setAppointment(resultSet.getString("name"));
                        appointmentDetails.setStatus(resultSet.getString("status"));
                        appointmentDetails.setDoctorFullName(doctor.getSurname() + " " + doctor.getName());
                        appointmentDetails.setNurseFullName(nurse.getSurname() + " " + nurse.getName());
                        appointmentDetailsList.add(appointmentDetails);
                    }
                }
            }
        } catch (SQLException e) {
            logger.error(e);
            throw new RuntimeException(e);
        }
        return appointmentDetailsList;
    }
    /* метод отримання списку невиконаних та не просрочених за датою призначень назначених для поточної медсестри  */
    public List<AppointmentDetails> findAllAppointmentsForNurse(int nurseId) {
        List<AppointmentDetails> appointmentDetailsList = new ArrayList<>();
        try (Connection connection = dbManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(Constants.FROM_APPOINTMENTS_FOR_NURSE)) {
            preparedStatement.setInt(1, nurseId);
            preparedStatement.execute();
            try (ResultSet resultSet = preparedStatement.getResultSet()) {
                while (resultSet.next()) {
                    String status = resultSet.getString("status");
                    LocalDate date = resultSet.getDate("date").toLocalDate();
                    if (!status.equals("done"))
                        if (date.equals(LocalDate.now()) || date.isAfter(LocalDate.now())) {
                            Doctor doctor = doctorsDAO.findDoctorById(resultSet.getInt("doctors_id"));
                            Patient patient = patientsDAO.findPatientById(resultSet.getInt("hospital_card_id"));

                            AppointmentDetails appointmentDetails
                                    = new AppointmentDetails(resultSet.getString("text"), date);
                            appointmentDetails.setId(resultSet.getInt("id"));
                            appointmentDetails.setNurseId(resultSet.getInt("nurse_id"));
                            appointmentDetails.setAppointmentId(resultSet.getInt("appointment_id"));
                            appointmentDetails.setDoctorsId(resultSet.getInt("doctors_id"));
                            appointmentDetails.setHospitalCardId(resultSet.getInt("hospital_card_id"));
                            appointmentDetails.setAppointment(resultSet.getString("name"));
                            appointmentDetails.setStatus(status);
                            appointmentDetails.setDoctorFullName(doctor.getSurname() + " " + doctor.getName());
                            appointmentDetails.setPatientFullName(patient.getSurname() + " " + patient.getName());
                            appointmentDetailsList.add(appointmentDetails);
                        }
                }
            }
        } catch (SQLException e) {
            logger.error(e);
            throw new RuntimeException(e);
        }
        return appointmentDetailsList;
    }
}
