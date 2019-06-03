package cn.pingan.springboottwo.model.vo;

import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="guodong")
@Data
public class TestDemo {

    @Id
    private ObjectId objectId;
    private String title;
    private String description;
    private Integer likes;
    private String by;

}
