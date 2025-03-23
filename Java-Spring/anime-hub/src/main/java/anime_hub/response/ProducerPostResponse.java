package anime_hub.response;

import lombok.Getter;
import lombok.Setter;
import lombok.Builder;

@Setter
@Getter
@Builder
public class ProducerPostResponse {
    private Long id;
    private String name;
}