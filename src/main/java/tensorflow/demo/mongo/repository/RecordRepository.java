package tensorflow.demo.mongo.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import tensorflow.demo.mongo.model.Record;

import java.util.List;

@Repository
public interface RecordRepository extends MongoRepository<Record, Double> {
    List<Record> findAllBySize(Double size);
}
