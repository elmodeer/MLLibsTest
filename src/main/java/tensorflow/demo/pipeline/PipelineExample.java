package tensorflow.demo.pipeline;

import org.apache.beam.sdk.Pipeline;
import org.apache.beam.sdk.io.mongodb.MongoDbIO;
import org.apache.beam.sdk.options.PipelineOptions;
import org.apache.beam.sdk.options.PipelineOptionsFactory;
import org.apache.beam.sdk.transforms.DoFn;
import org.apache.beam.sdk.transforms.FlatMapElements;
import org.apache.beam.sdk.transforms.MapElements;
import org.apache.beam.sdk.transforms.ParDo;
import org.apache.beam.sdk.values.KV;
import org.apache.beam.sdk.values.PCollection;
import org.apache.beam.sdk.values.TypeDescriptor;
import org.apache.beam.sdk.values.TypeDescriptors;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import tensorflow.demo.mongo.model.Record;
import tensorflow.demo.mongo.repository.RecordRepository;

import java.util.Arrays;
import java.util.List;

public class PipelineExample {

    @Autowired
    private RecordRepository repository;

    public void createPipeline() {
        // Start by defining the options for the pipeline.
        PipelineOptions options = PipelineOptionsFactory.create();

        // Then create the pipeline.
        Pipeline p = Pipeline.create(options);

        PCollection<Document> docs = p.apply(MongoDbIO.read().withUri("mongodb://localhost:27017")
                                                                  .withDatabase("tensor-flow-mongo")
                                                                  .withCollection("record"));
//        PCollection<KV<String, Long>> sentences = docs.apply("BuildSentence",
//                                                    FlatMapElements
//                                                            .into(TypeDescriptors.strings())
//                                                            .via(record -> Arrays.asList(record.toString()));

    }

    static class BuildSentences extends DoFn<Record, String> {

//        @ProcessElement
//        public void processElement(ProcessContext c, @Element Record r) {
//            c.output("size: " + r.getSize());
//
//        }
    }

}
