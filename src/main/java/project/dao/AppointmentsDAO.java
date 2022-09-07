package project.dao;

import org.apache.log4j.Logger;
import project.Constants;
import project.appointments.Appointment;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AppointmentsDAO {
    private final static Logger logger = Logger.getLogger(AppointmentsDAO.class);
    private final DBManager dbManager = DBManager.getInstance();

    /* метод додавання нового призначення  */
    public void insertAppointment(Appointment appointment) {
        try (Connection connection = dbManager.getConnection();
             PreparedStatement preparedStatement
                     = connection.prepareStatement(Constants.INSERT_APPOINTMENT, PreparedStatement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, appointment.getName());
            preparedStatement.executeUpdate();
            try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                generatedKeys.next();
                appointment.setId(generatedKeys.getInt(1));
            }
        } catch (SQLException e) {
            logger.error(e);
            throw new RuntimeException(e);
        }
    }
    /* метод отримання списку всіх медичних призначень */
    public List<Appointment> findAllAppointments() {
        List<Appointment> appointmentList = new ArrayList<>();
        try (Connection connection = dbManager.getConnection();
             ResultSet resultSet = connection.prepareStatement(Constants.FROM_APPOINTMENTS).executeQuery()) {
            while (resultSet.next()) {
                Appointment appointment = new Appointment(resultSet.getString("name"));
                appointment.setId(resultSet.getInt("id"));
                appointmentList.add(appointment);
            }
        } catch (SQLException e) {
            logger.error(e);
            throw new RuntimeException(e);
        }
        return appointmentList;
    }
    /* метод пошуку медичного призначення по назві */
    public Appointment findAppointmentByName(String name) {
        Appointment appointment = null;
        try (Connection connection = dbManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(Constants.FIND_APPOINTMENT)) {
            preparedStatement.setString(1, name);
            preparedStatement.execute();
            try (ResultSet resultSet = preparedStatement.getResultSet()) {
                if (resultSet.next()) {
                    appointment = new Appointment(resultSet.getString("name"));
                    appointment.setId(resultSet.getInt("id"));
                }
            }
        } catch (SQLException e) {
            logger.error(e);
            throw new RuntimeException(e);
        }
        return appointment;
    }
}
