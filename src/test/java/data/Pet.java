package data;

import lombok.Builder;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Builder
@Data
public class Pet {
    @Builder.Default
    private long id = 0;
    @Builder.Default
    private Category category = Category.builder().build();
    @Builder.Default
    private String name = "varan";
    @Builder.Default
    private List<String> photoUrls = new ArrayList<>();
    @Builder.Default
    private List<Tag> tags = new ArrayList<>();
    @Builder.Default
    private Status status = Status.pending;

}
