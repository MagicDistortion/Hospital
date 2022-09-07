package project.dao;

import org.apache.log4j.Logger;
import project.Constants;
import project.models.users.Doctor;
import project.models.users.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DoctorsDAO {
    private final static Logger logger = Logger.getLogger(DoctorsDAO.class);
    private final DBManager dbManager = DBManager.getInstance();
    private final UsersDAO usersDAO = new UsersDAO();

    /* метод додавання лікаря */
    public void insertDoctor(User user) {
        dbManager.inTransaction(connection -> {
            try (PreparedStatement preparedStatement = connection.prepareStatement(Constants.INSERT_DOCTORS)) {
                if (user.getRolesId() == 0) {
                    preparedStatement.setInt(1, user.getId());
                    preparedStatement.executeUpdate();
                    usersDAO.updateUserRole(connection, 2, user.getId());
                }
            } catch (SQLException e) {
                logger.error(e);
                throw new RuntimeException(e);
            }
        });
    }

    /* метод оновлення категорії лікаря  */
    public void updateDoctorCategory(int categoryId, int id) {
        try (Connection connection = dbManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(Constants.UPDATE_DOCTOR_CATEGORY)) {
            preparedStatement.setInt(1, categoryId);
            preparedStatement.setInt(2, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            logger.error(e);
            throw new RuntimeException(e);
        }
    }
    /* метод оновлення кількості пацієнтів лікаря  */
    public void updateNumberOfPatients(int patients, int docId) {
        try (Connection connection = dbManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(Constants.UPDATE_NUMBER_OF_PATIENTS)) {
            preparedStatement.setInt(1, patients);
            preparedStatement.setInt(2, docId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            logger.error(e);
            throw new RuntimeException(e);
        }
    }
    /* метод отримання списку всіх лікарів за сортуванням */
    public List<Doctor> findAllDoctors(String sorted) {
        List<Doctor> DoctorsList = new ArrayList<>();
        try (Connection connection = dbManager.getConnection();
             ResultSet resultSet = connection.prepareStatement(Constants.FROM_DOCTORS + sorted).executeQuery()) {
            while (resultSet.next()) {
                Doctor doctor = new Doctor(usersDAO.getUser(resultSet));
                doctor.setCategoryId(resultSet.getInt("category_id"));
                doctor.setNumberOfPatients(resultSet.getInt("number_of_patients"));
                doctor.setCategory(resultSet.getString("category.name"));
                DoctorsList.add(doctor);
            }
        } catch (SQLException e) {
            logger.error(e);
            throw new RuntimeException(e);
        }
        return DoctorsList;
    }
    /* метод отримання списку лікарів за сортуванням та пагінацією */
    public List<Doctor> findDoctorsWithLimit(String sorted, int start, int total) {
        start = (start - 1) * total;
        String limit = " limit " + start + "," + total;
        List<Doctor> DoctorsList = new ArrayList<>();
        try (Connection connection = dbManager.getConnection();
             ResultSet resultSet = connection.prepareStatement(Constants.FROM_DOCTORS + sorted + limit).executeQuery()) {
            while (resultSet.next()) {
                Doctor doctor = new Doctor(usersDAO.getUser(resultSet));
                doctor.setCategoryId(resultSet.getInt("category_id"));
                doctor.setNumberOfPatients(resultSet.getInt("number_of_patients"));
                doctor.setCategory(resultSet.getString("category.name"));
                DoctorsList.add(doctor);
            }
        } catch (SQLException e) {
            logger.error(e);
            throw new RuntimeException(e);
        }
        return DoctorsList;
    }
    /* метод пошуку лікаря по id */

    public Doctor findDoctorById(int id) {
        Doctor doctor = null;
        try (Connection connection = dbManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(Constants.FIND_DOCTOR_BY_ID)) {
            preparedStatement.setInt(1, id);
            preparedStatement.execute();
            try (ResultSet resultSet = preparedStatement.getResultSet()) {
                if (resultSet.next()) {
                    doctor = new Doctor(usersDAO.getUser(resultSet));
                    doctor.setCategoryId(resultSet.getInt("category_id"));
                    doctor.setNumberOfPatients(resultSet.getInt("number_of_patients"));
                    doctor.setCategory(resultSet.getString("category.name"));
                }
            }
        } catch (SQLException e) {
            logger.error(e);
            throw new RuntimeException(e);
        }
        return doctor;
    }
}
