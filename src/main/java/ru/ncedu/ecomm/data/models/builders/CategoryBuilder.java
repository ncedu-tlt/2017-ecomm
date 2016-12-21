package ru.ncedu.ecomm.data.models.builders;
import ru.ncedu.ecomm.data.models.Category;
import java.sql.SQLException;

public class CategoryBuilder {

    private long categoryId;
    private long parentId;
    private String name;
    private String description;

    public CategoryBuilder(){
    }


    public CategoryBuilder setCategoryId(long categoryId) {
      this.categoryId = categoryId;

        return this;
    }

    public CategoryBuilder setParentId(long parentId) throws SQLException {
      this.parentId = parentId;

        return this;
    }

    public CategoryBuilder setName(String name ){
       this.name = name;

        return this;
    }

    public CategoryBuilder setDescription(String description) {
        this.description = description;

        return this;
    }

    public Category build(){

        return new Category(
                categoryId,
                parentId,
                name,
                description
        );
    }
}
