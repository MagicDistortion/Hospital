package project.dao;

import org.apache.log4j.Logger;
import project.Constants;
import project.hospitalcard.HospitalCard;
import project.users.Doctor;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class HospitalCardDAO {
    private final static Logger logger = Logger.getLogger(HospitalCardDAO.class);
    private final DBManager dbManager = DBManager.getInstance();
    private final DoctorsDAO doctorsDAO = new DoctorsDAO();

    /* метод створення лікарняної карти для пацієнта  */
    public void insertHospitalCard(Connection connection, int patientId) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(Constants.INSERT_HOSPITAL_CARD)) {
            preparedStatement.setInt(1, patientId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            logger.error(e);
            throw new RuntimeException(e);
        }
    }

    /* метод оновлення діагнозу пацієнта у лікарняній картці  */
    public void updateDiagnosis(String diagnosis, int id) {
        try (Connection connection = dbManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(Constants.UPDATE_DIAGNOSIS)) {
            preparedStatement.setString(1, diagnosis);
            preparedStatement.setInt(2, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            logger.error(e);
            throw new RuntimeException(e);
        }
    }

    /* метод оновлення статусу лікарняної карти пацієнта  */
    public void updateHospitalCardStatus(String status, int idCard) {
        try (Connection connection = dbManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(Constants.UPDATE_HOSPITAL_CARD_STATUS)) {
            preparedStatement.setString(1, status);
            preparedStatement.setInt(2, idCard);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            logger.error(e);
            throw new RuntimeException(e);
        }
    }

    /* метод призначення поточного лікаря пацієнту  */
    public void appointADoctor(Doctor doctor, int id) {
        try (Connection connection = dbManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(Constants.APPOINT_A_DOCTOR)) {
            preparedStatement.setInt(1, doctor.getId());
            preparedStatement.setInt(2, doctor.getNumberOfPatients() + 1);
            preparedStatement.setInt(3, id);
            preparedStatement.setInt(4, doctor.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            logger.error(e);
            throw new RuntimeException(e);
        }
    }

    /* метод виписки пацієнта з лікарні */
    public void dischargePatient(int id) {
        try (Connection connection = dbManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(Constants.DISCHARGE_PATIENT)) {
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            logger.error(e);
            throw new RuntimeException(e);
        }
    }
    /* метод отримання лікарняної карти по id */
    public HospitalCard getHospitalCard(int id) {
        HospitalCard hospitalCard = null;
        try (Connection connection = dbManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(Constants.GET_A_HOSPITAL_CARD)) {
            preparedStatement.setInt(1, id);
            preparedStatement.execute();
            try (ResultSet resultSet = preparedStatement.getResultSet()) {
                if (resultSet.next()) {
                    Doctor doctor = doctorsDAO.findDoctorById(resultSet.getInt("current_doctor_id"));

                    hospitalCard = new HospitalCard(resultSet.getInt("id_card"));
                    hospitalCard.setDiagnosis(resultSet.getString("diagnosis"));
                    hospitalCard.setDoctorsId(resultSet.getInt("current_doctor_id"));
                    hospitalCard.setCreateTime(resultSet.getDate("create_time").toLocalDate());
                    hospitalCard.setPatientsName(resultSet.getString("name"));
                    hospitalCard.setPatientsSurname(resultSet.getString("surname"));
                    hospitalCard.setStatus(resultSet.getString("status"));
                    if (doctor != null) {
                        hospitalCard.setCurrentDoctorName(doctor.getName());
                        hospitalCard.setCurrentDoctorSurname(doctor.getSurname());
                    }
                }
            }
        } catch (SQLException e) {
            logger.error(e);
            throw new RuntimeException(e);
        }
        return hospitalCard;
    }
    /* метод отримання списку лікарняних карт без статусу "виписано"*/
    public List<HospitalCard> getAllHospitalCards() {
        List<HospitalCard> hospitalCardList = new ArrayList<>();
        try (Connection connection = dbManager.getConnection();
             ResultSet resultSet = connection.prepareStatement(Constants.GET_ALL_HOSPITAL_CARDS).executeQuery()) {
            while (resultSet.next()) {
                Doctor doctor = doctorsDAO.findDoctorById(resultSet.getInt("current_doctor_id"));

                HospitalCard hospitalCard = new HospitalCard(resultSet.getInt("id_card"));
                hospitalCard.setDiagnosis(resultSet.getString("diagnosis"));
                hospitalCard.setDoctorsId(resultSet.getInt("current_doctor_id"));
                hospitalCard.setCreateTime(resultSet.getDate("create_time").toLocalDate());
                hospitalCard.setPatientsName(resultSet.getString("name"));
                hospitalCard.setPatientsSurname(resultSet.getString("surname"));
                hospitalCard.setStatus(resultSet.getString("status"));
                if (doctor != null) {
                    hospitalCard.setCurrentDoctorName(doctor.getName());
                    hospitalCard.setCurrentDoctorSurname(doctor.getSurname());
                }
                hospitalCardList.add(hospitalCard);
            }
        } catch (SQLException e) {
            logger.error(e);
            throw new RuntimeException(e);
        }
        return hospitalCardList;
    }
    /* метод отримання списку лікарняних карт з пагінацією */

    public List<HospitalCard> getHospitalCardsWithLimit(int start, int total) {
        start = (start - 1) * total;
        String limit = " limit " + start + "," + total;
        List<HospitalCard> hospitalCardList = new ArrayList<>();
        try (Connection connection = dbManager.getConnection();
             ResultSet resultSet = connection.prepareStatement(Constants.GET_ALL_HOSPITAL_CARDS + limit).executeQuery()) {
            while (resultSet.next()) {
                Doctor doctor = doctorsDAO.findDoctorById(resultSet.getInt("current_doctor_id"));

                HospitalCard hospitalCard = new HospitalCard(resultSet.getInt("id_card"));
                hospitalCard.setDiagnosis(resultSet.getString("diagnosis"));
                hospitalCard.setDoctorsId(resultSet.getInt("current_doctor_id"));
                hospitalCard.setCreateTime(resultSet.getDate("create_time").toLocalDate());
                hospitalCard.setPatientsSurname(resultSet.getString("surname"));
                hospitalCard.setPatientsName(resultSet.getString("name"));
                hospitalCard.setStatus(resultSet.getString("status"));
                if (doctor != null) {
                    hospitalCard.setCurrentDoctorName(doctor.getName());
                    hospitalCard.setCurrentDoctorSurname(doctor.getSurname());
                }
                hospitalCardList.add(hospitalCard);
            }
        } catch (SQLException e) {
            logger.error(e);
            throw new RuntimeException(e);
        }
        return hospitalCardList;
    }
}
