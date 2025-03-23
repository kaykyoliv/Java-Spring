package anime_hub.response;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;import lombok.Builder;

@Setter
@Getter
@Builder
public class ProducerGetResponse {
    private Long id;
    private String name;
    private LocalDateTime createdAt;
}