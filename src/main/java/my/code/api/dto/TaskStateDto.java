package my.code.api.dto;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.Column;
import java.time.Instant;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TaskStateDto {

    @NonNull
    Long id;

    @NonNull
    String name;

    @NonNull
    Long ordinal;

    @NonNull
    @JsonProperty("created_at")
    Instant createdAt;
}
