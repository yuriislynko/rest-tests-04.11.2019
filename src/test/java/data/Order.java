package data;

import lombok.Builder;
import lombok.Data;

import java.text.SimpleDateFormat;

@Builder
@Data
public class Order {
    @Builder.Default
    private int id = 0;
    @Builder.Default
    private long petId = 0;
    @Builder.Default
    private int quantity = 0;
    @Builder.Default
    private String shipDate = "2020-02-08T12:52:20.886Z";
    @Builder.Default
    private Status status = Status.placed;
    @Builder.Default
    private boolean complete = true;
}
