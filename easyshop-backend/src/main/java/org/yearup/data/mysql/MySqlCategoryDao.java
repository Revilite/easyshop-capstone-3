package org.yearup.data.mysql;

import org.springframework.stereotype.Component;
import org.yearup.data.CategoryDao;
import org.yearup.models.Category;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Component
public class MySqlCategoryDao extends MySqlDaoBase implements CategoryDao {
    public MySqlCategoryDao(DataSource dataSource) {
        super(dataSource);
    }


    @Override
    public List<Category> getAllCategories() {
        List<Category> categories = new ArrayList<>();
        try (Connection connection = getConnection()) {
            PreparedStatement statement = connection.prepareStatement("""
                    SELECT * FROM categories;
                    """);

            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                categories.add(mapRow(rs));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return categories;
    }

    @Override
    public Category getById(int categoryId) {
        try (Connection connection = getConnection()) {
            PreparedStatement statement = connection.prepareStatement("""
                    SELECT * FROM categories
                    WHERE category_id = ?;
                    """);
            statement.setInt(1, categoryId);
            ResultSet rs = statement.executeQuery();

            rs.next();
            return mapRow(rs);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Category create(Category category) {
        try (Connection connection = getConnection()) {
            PreparedStatement statement = connection.prepareStatement("""
                    INSERT INTO categories(name, description)
                    VALUES( ?, ?);
                    """, PreparedStatement.RETURN_GENERATED_KEYS);
            statement.setString(1, category.getName());
            statement.setString(2, category.getDescription());
            statement.executeUpdate();

            ResultSet rs = statement.getGeneratedKeys();

            if (rs.next()) {
                int returnedRows = rs.getInt(1);
                return new Category(returnedRows, category.getName(), category.getDescription());
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public void update(int categoryId, Category category) {
        try (Connection connection = getConnection()) {
            PreparedStatement statement = connection.prepareStatement("""
                    UPDATE categories
                    SET category_id = ?, name = ?, description = ?
                    WHERE category_id = ?;
                    """);
            statement.setInt(1, category.getCategoryId());
            statement.setString(2, category.getName());
            statement.setString(3, category.getDescription());
            statement.setInt(4, categoryId);

            statement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(int categoryId) {
        try (Connection connection = getConnection()) {
            PreparedStatement deleteCategory = connection.prepareStatement("""
                    DELETE FROM categories 
                    WHERE category_id = ?;
                    """);
            deleteCategory.setInt(1, categoryId);
            deleteCategory.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // Creates the category and returns it
    private Category mapRow(ResultSet row) throws SQLException {
        //Tries to get information from selection
        try {
            int categoryId = row.getInt("category_id");
            String name = row.getString("name");
            String description = row.getString("description");

            Category category = new Category() {{
                setCategoryId(categoryId);
                setName(name);
                setDescription(description);
            }};
            return category;
        }
        //If selection is empty return null to cause a 404 not found exception
        catch (SQLException e) {
            return null;
        }

    }

}
