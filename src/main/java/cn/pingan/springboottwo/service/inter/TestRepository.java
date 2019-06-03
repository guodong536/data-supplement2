package cn.pingan.springboottwo.service.inter;

import cn.pingan.springboottwo.model.vo.TestDemo;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface TestRepository extends MongoRepository<TestDemo, ObjectId> {

    TestDemo findByTitle(String title);

    @Override
    List<TestDemo> findAll();

}
