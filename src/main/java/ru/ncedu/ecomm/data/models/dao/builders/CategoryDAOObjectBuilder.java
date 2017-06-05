package ru.ncedu.ecomm.data.models.dao.builders;
import ru.ncedu.ecomm.data.models.dao.CategoryDAOObject;
public class CategoryDAOObjectBuilder {

    private long categoryId;
    private Long parentId;
    private String name;
    private String description;

    public CategoryDAOObjectBuilder(){
    }

    public CategoryDAOObjectBuilder(long categoryId) {
        this.categoryId = categoryId;
    }

    public CategoryDAOObjectBuilder setCategoryId(long categoryId) {
      this.categoryId = categoryId;

        return this;
    }

    public CategoryDAOObjectBuilder setParentId(Long parentId){
      this.parentId = parentId;

        return this;
    }

    public CategoryDAOObjectBuilder setName(String name ){
       this.name = name;

        return this;
    }

    public CategoryDAOObjectBuilder setDescription(String description) {
        this.description = description;

        return this;
    }

    public CategoryDAOObject build(){

        return new CategoryDAOObject(
                categoryId,
                parentId,
                name,
                description
        );
    }
}
