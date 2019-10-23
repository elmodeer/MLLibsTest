package tensorflow.demo;

import org.apache.beam.sdk.Pipeline;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tensorflow.demo.mongo.model.Record;
import tensorflow.demo.mongo.repository.RecordRepository;
import tensorflow.demo.pipeline.PipelineExample;

@SpringBootApplication
public class TensorFlowTestApplication implements CommandLineRunner {

    @Autowired
    private RecordRepository repository;

    public static void main(String[] args) {
        SpringApplication.run(TensorFlowTestApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        // repository.deleteAll();

        // fetch all reords
        System.out.println("Records found with findAll():");
        System.out.println("-------------------------------");
        for (Record record : repository.findAll()) {
            System.out.println(record);
        }
        System.out.println();
        PipelineExample  p = new PipelineExample();
        p.createPipeline();

    }

}
