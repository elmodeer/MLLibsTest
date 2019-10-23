package tensorflow.demo.mongo.model;

import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;

@Data
@RequiredArgsConstructor
@Document
public class Record {

    @Id
    private String id;

    @CreatedDate
    private Instant createdAt;

    // in square feet
    @NonNull
    private Double size;

    @NonNull
    private Integer bedrooms;

    @NonNull
    private Double price;

    @Override
    public String toString() {
        return "Record{" +
                "size=" + size +
                ", bedrooms=" + bedrooms +
                ", price=" + price +
                '}';
    }
}
