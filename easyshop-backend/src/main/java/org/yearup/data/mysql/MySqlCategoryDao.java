package org.yearup.data.mysql;

import org.springframework.stereotype.Component;
import org.yearup.data.CategoryDao;
import org.yearup.models.Category;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Component
public class MySqlCategoryDao extends MySqlDaoBase implements CategoryDao
{
    public MySqlCategoryDao(DataSource dataSource)
    {
        super(dataSource);
    }

    @Override
    public List<Category> getAllCategories()
    {
        //TODO get all categories
        return null;
    }

    @Override
    public Category getById(int categoryId)
    {
        // TODO get category by id
        return null;
    }

    @Override
    public Category create(Category category)
    {
        // TODO create a new category
        return null;
    }

    @Override
    public void update(int categoryId, Category category)
    {
        // TODO update category
    }

    @Override
    public void delete(int categoryId)
    {
        // TODO delete category
    }

    private Category mapRow(ResultSet row) throws SQLException
    {
        int categoryId = row.getInt("category_id");
        String name = row.getString("name");
        String description = row.getString("description");

        Category category = new Category()
        {{
            setCategoryId(categoryId);
            setName(name);
            setDescription(description);
        }};

        return category;
    }

}
