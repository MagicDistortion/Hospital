package project.dao;

import org.apache.log4j.Logger;
import project.Constants;
import project.users.User;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SysAdminsDAO {
    private final static Logger logger = Logger.getLogger(SysAdminsDAO.class);
    private final DBManager dbManager = DBManager.getInstance();
    private final UsersDAO usersDAO = new UsersDAO();


    /* метод додавання сіс адміна  */
    public void insertSysAdmin(User user) {
        dbManager.inTransaction(connection -> {
            try (PreparedStatement preparedStatement = connection.prepareStatement(Constants.INSERT_SYS_ADMIN)) {
                if (user.getRolesId() == 0) {
                    preparedStatement.setInt(1, user.getId());
                    preparedStatement.executeUpdate();
                    usersDAO.updateUserRole(connection, 1, user.getId());
                }
            } catch (SQLException e) {
                logger.error(e);
                throw new RuntimeException(e);
            }
        });
    }
}
