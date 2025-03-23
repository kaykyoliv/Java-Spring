package anime_hub.request;

import lombok.Getter;
import lombok.Setter;
import lombok.Builder;
import lombok.ToString;

@Setter
@Getter
@Builder
@ToString
public class AnimePutRequest {
    private Long id;
    private String name;
}