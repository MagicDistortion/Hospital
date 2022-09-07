package project.dao;

import org.apache.log4j.Logger;
import project.Constants;
import project.users.Patient;
import project.users.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PatientsDAO {
    private final static Logger logger = Logger.getLogger(PatientsDAO.class);
    private final DBManager dbManager = DBManager.getInstance();
    private final UsersDAO usersDAO = new UsersDAO();
    private final HospitalCardDAO hospitalCardDAO = new HospitalCardDAO();

    /* метод додавання пацієнта  */
    public void insertPatient(User user) {
        dbManager.inTransaction(connection -> {
            try (PreparedStatement preparedStatement = connection.prepareStatement(Constants.INSERT_PATIENTS)) {
                if (user.getRolesId() == 0) {
                    preparedStatement.setInt(1, user.getId());
                    preparedStatement.executeUpdate();
                    usersDAO.updateUserRole(connection, 4, user.getId());
                    hospitalCardDAO.insertHospitalCard(connection, user.getId());
                }
            } catch (SQLException e) {
                logger.error(e);
                throw new RuntimeException(e);
            }
        });
    }
    /* метод пошуку пацієнта по id */
    public Patient findPatientById(int id) {
        Patient patient = null;
        try (Connection connection = dbManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(Constants.FIND_PATIENT_BY_ID)) {
            preparedStatement.setInt(1, id);
            preparedStatement.execute();
            try (ResultSet resultSet = preparedStatement.getResultSet()) {
                if (resultSet.next()) {
                    patient = new Patient(usersDAO.getUser(resultSet));
                }
            }
        } catch (SQLException e) {
            logger.error(e);
            throw new RuntimeException(e);
        }
        return patient;
    }
    /* метод отримання списку всіх пацієнтів за сортуванням */
    public List<Patient> findAllPatients(String sorted) {
        List<Patient> patientList = new ArrayList<>();
        try (Connection connection = dbManager.getConnection();
             ResultSet resultSet = connection.prepareStatement(Constants.FROM_PATIENTS + sorted).executeQuery()) {
            while (resultSet.next()) {
                Patient patient = new Patient(usersDAO.getUser(resultSet));
                patient.setCurrentDoctorId(resultSet.getInt("current_doctor_id"));
                patientList.add(patient);
            }
        } catch (SQLException e) {
            logger.error(e);
            throw new RuntimeException(e);
        }
        return patientList;
    }
    /* метод отримання списку пацієнтів за сортуванням та пагінацією */
    public List<Patient> findPatientsWithLimit(String sorted, int start, int total) {
        start = (start - 1) * total;
        String limit = " limit " + start + "," + total;
        List<Patient> patientList = new ArrayList<>();
        try (Connection connection = dbManager.getConnection();
             ResultSet resultSet = connection.prepareStatement(Constants.FROM_PATIENTS + sorted + limit).executeQuery()) {
            while (resultSet.next()) {
                Patient patient = new Patient(usersDAO.getUser(resultSet));
                patient.setCurrentDoctorId(resultSet.getInt("current_doctor_id"));
                patientList.add(patient);
            }
        } catch (SQLException e) {
            logger.error(e);
            throw new RuntimeException(e);
        }
        return patientList;
    }
}
