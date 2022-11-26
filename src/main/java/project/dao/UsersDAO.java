package project.dao;

import org.apache.log4j.Logger;
import project.Constants;
import project.models.users.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class UsersDAO {
    private final static Logger logger = Logger.getLogger(UsersDAO.class);
    private final DBManager dbManager = DBManager.getInstance();

    /* метод отримання користувача з різалтсету */
    public User getUser(ResultSet resultSet) throws SQLException {
        User user = new User(resultSet.getString("surname")
                , resultSet.getString("name")
                , resultSet.getString("login")
                , resultSet.getString("password")
                , resultSet.getString("tel")
                , resultSet.getDate("date_of_birth").toLocalDate()
                );
        user.setAge(LocalDate.now().getYear() - user.getDateOfBirth().getYear());
        user.setId(resultSet.getInt("id_users"));
        user.setRolesId(resultSet.getInt("id_roles"));
        return user;
    }

    /* метод додавання користувача  */
    public void insertUser(User user) {
        try (Connection connection = dbManager.getConnection();
             PreparedStatement preparedStatement
                     = connection.prepareStatement(Constants.INSERT_USERS, PreparedStatement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, user.getSurname());
            preparedStatement.setString(2, user.getName());
            preparedStatement.setString(3, user.getLogin());
            preparedStatement.setString(4, String.valueOf(user.getPassword().hashCode()));
            preparedStatement.setString(5, user.getTel());
            preparedStatement.setObject(6, user.getDateOfBirth());
            preparedStatement.setInt(7, user.getAge());
            preparedStatement.executeUpdate();
            try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                generatedKeys.next();
                user.setId(generatedKeys.getInt(1));
            }
        } catch (SQLException e) {
            logger.error(e);
            throw new RuntimeException(e);
        }
    }

    /* метод оновлення ролі користувача  */
    public void updateUserRole(Connection connection, int role, int id) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(Constants.UPDATE_USER_ROLE)) {
            preparedStatement.setInt(1, role);
            preparedStatement.setInt(2, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            logger.error(e);
            throw new RuntimeException(e);
        }
    }

    /* метод оновлення Прізвища користувача  */
    public void updateUserSurname(String surname, int id) {
        try (Connection connection = dbManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(Constants.UPDATE_USER_SURNAME)) {
            preparedStatement.setString(1, surname);
            preparedStatement.setInt(2, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            logger.error(e);
            throw new RuntimeException(e);
        }
    }

    /* метод оновлення Імені користувача */
    public void updateUserName(String name, int id) {
        try (Connection connection = dbManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(Constants.UPDATE_USER_NAME)) {
            preparedStatement.setString(1, name);
            preparedStatement.setInt(2, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            logger.error(e);
            throw new RuntimeException(e);
        }
    }

    /* метод оновлення Логіну користувача */
    public void updateUserLogin(String login, int id) {
        try (Connection connection = dbManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(Constants.UPDATE_USER_LOGIN)) {
            preparedStatement.setString(1, login);
            preparedStatement.setInt(2, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            logger.error(e);
            throw new RuntimeException(e);
        }
    }

    /* метод оновлення Паролю користувача */
    public void updateUserPassword(String password, int id) {
        try (Connection connection = dbManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(Constants.UPDATE_USER_PASSWORD)) {
            preparedStatement.setString(1, String.valueOf(password.hashCode()));
            preparedStatement.setInt(2, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            logger.error(e);
            throw new RuntimeException(e);
        }
    }

    /* метод оновлення Номера Телефона користувача */
    public void updateUserTel(String tel, int id) {
        try (Connection connection = dbManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(Constants.UPDATE_USER_TEL)) {
            preparedStatement.setString(1, tel);
            preparedStatement.setInt(2, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            logger.error(e);
            throw new RuntimeException(e);
        }
    }

    /* метод оновлення Дати народження користувача */
    public void updateUserDateOfBirth(LocalDate dateOfBirth, int id) {
        try (Connection connection = dbManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(Constants.UPDATE_USER_DATE_OF_BIRTH)) {
            preparedStatement.setObject(1, dateOfBirth);
            preparedStatement.setInt(2, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            logger.error(e);
            throw new RuntimeException(e);
        }
    }

    /* метод отримання списку всіх користувачів */
    public List<User> findAllUsers() {
        List<User> userList = new ArrayList<>();
        try (Connection connection = dbManager.getConnection();
             ResultSet resultSet = connection.prepareStatement(Constants.FROM_USERS).executeQuery()) {
            while (resultSet.next()) {
                User user = getUser(resultSet);
                userList.add(user);
            }
        } catch (SQLException e) {
            logger.error(e);
            throw new RuntimeException(e);
        }
        return userList;
    }

    /* метод отримання списку користувачів без ролі */
    public List<User> findUsersWitchOutRole() {
        List<User> userList = new ArrayList<>();
        try (Connection connection = dbManager.getConnection();
             ResultSet resultSet = connection.prepareStatement(Constants.FROM_USERS_WITHOUT_ROLE).executeQuery()) {
            while (resultSet.next()) {
                User user = getUser(resultSet);
                userList.add(user);
            }
        } catch (SQLException e) {
            logger.error(e);
            throw new RuntimeException(e);
        }
        return userList;
    }

    /* метод пошуку користувача по логіну */
    public User findUserByLogin(String login) {
        User user = null;
        try (Connection connection = dbManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(Constants.FIND_BY_LOGIN)) {
            preparedStatement.setString(1, login);
            preparedStatement.execute();
            try (ResultSet resultSet = preparedStatement.getResultSet()) {
                if (resultSet.next()) {
                    user = getUser(resultSet);
                }
            }
        } catch (SQLException e) {
            logger.error(e);
            throw new RuntimeException(e);
        }
        return user;
    }

    /* метод пошуку користувача по id */
    public User findUserByID(int id) {
        User user = null;
        try (Connection connection = dbManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(Constants.FIND_BY_ID)) {
            preparedStatement.setInt(1, id);
            preparedStatement.execute();
            try (ResultSet resultSet = preparedStatement.getResultSet()) {
                if (resultSet.next()) {
                    user = getUser(resultSet);
                }
            }
        } catch (SQLException e) {
            logger.error(e);
            throw new RuntimeException(e);
        }
        return user;
    }
}
