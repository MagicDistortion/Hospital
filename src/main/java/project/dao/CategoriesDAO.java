package project.dao;

import org.apache.log4j.Logger;
import project.Constants;
import project.categories.Categories;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CategoriesDAO {
    private final static Logger logger = Logger.getLogger(CategoriesDAO.class);
    private final DBManager dbManager = DBManager.getInstance();

    /* метод додавання нової категорії  */
    public void insertCategory(Categories category) {
        try (Connection connection = dbManager.getConnection();
             PreparedStatement preparedStatement
                     = connection.prepareStatement(Constants.INSERT_CATEGORY, PreparedStatement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, category.getName());
            preparedStatement.executeUpdate();
            try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                generatedKeys.next();
                category.setId(generatedKeys.getInt(1));
            }
        } catch (SQLException e) {
            logger.error(e);
            throw new RuntimeException(e);
        }
    }
    /* метод отримання списку всіх категорій лікарів */
    public List<Categories> findAllCategories() {
        List<Categories> categoriesList = new ArrayList<>();
        try (Connection connection = dbManager.getConnection();
             ResultSet resultSet = connection.prepareStatement(Constants.FROM_CATEGORIES).executeQuery()) {
            while (resultSet.next()) {
                Categories category = new Categories(resultSet.getString("name"));
                category.setId(resultSet.getInt("id"));
                categoriesList.add(category);
            }
        } catch (SQLException e) {
            logger.error(e);
            throw new RuntimeException(e);
        }
        return categoriesList;
    }
    /* метод пошуку лікарської категорії за назвою */
    public Categories findCategory(String name) {
        Categories category = null;
        try (Connection connection = dbManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(Constants.FIND_CATEGORY)) {
            preparedStatement.setString(1, name);
            preparedStatement.execute();
            try (ResultSet resultSet = preparedStatement.getResultSet()) {
                while (resultSet.next()) {
                    category = new Categories(resultSet.getString("name"));
                    category.setId(resultSet.getInt("id"));
                }
            }
        } catch (SQLException e) {
            logger.error(e);
            throw new RuntimeException(e);
        }
        return category;
    }
}
