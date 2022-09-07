package project.dao;

import org.apache.log4j.Logger;
import project.Constants;
import project.models.users.Nurse;
import project.models.users.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class NursesDAO {
    private final static Logger logger = Logger.getLogger(NursesDAO.class);
    private final DBManager dbManager = DBManager.getInstance();
    private final UsersDAO usersDAO = new UsersDAO();

    /* метод додавання медсестри  */
    public void insertNurse(User user) {
        dbManager.inTransaction(connection -> {
            try (PreparedStatement preparedStatement = connection.prepareStatement(Constants.INSERT_NURSE)) {
                if (user.getRolesId() == 0) {
                    preparedStatement.setInt(1, user.getId());
                    preparedStatement.executeUpdate();
                    usersDAO.updateUserRole(connection, 3, user.getId());
                }
            } catch (SQLException e) {
                logger.error(e);
                throw new RuntimeException(e);
            }
        });
    }
    /* метод отримання списку всіх мед сестр */
    public List<Nurse> findAllNurse() {
        List<Nurse> nurseList = new ArrayList<>();
        try (Connection connection = dbManager.getConnection();
             ResultSet resultSet = connection.prepareStatement(Constants.FROM_NURSE).executeQuery()) {
            while (resultSet.next()) {
                Nurse nurse = new Nurse(usersDAO.getUser(resultSet));
                nurseList.add(nurse);
            }
        } catch (SQLException e) {
            logger.error(e);
            throw new RuntimeException(e);
        }
        return nurseList;
    }
    /* метод пошуку медсестри по id */
    public Nurse findNurseById(int id) {
        Nurse nurse = null;
        try (Connection connection = dbManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(Constants.FIND_NURSE_BY_ID)) {
            preparedStatement.setInt(1, id);
            preparedStatement.execute();
            try (ResultSet resultSet = preparedStatement.getResultSet()) {
                if (resultSet.next()) {
                    nurse = new Nurse(usersDAO.getUser(resultSet));
                }
            }
        } catch (SQLException e) {
            logger.error(e);
            throw new RuntimeException(e);
        }
        return nurse;
    }
}
