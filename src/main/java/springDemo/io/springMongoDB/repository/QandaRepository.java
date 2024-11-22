package springDemo.io.springMongoDB.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import org.springframework.stereotype.Repository;

import springDemo.io.springMongoDB.model.QandaModel;

@Repository
public interface QandaRepository extends MongoRepository<QandaModel, String> {
}
