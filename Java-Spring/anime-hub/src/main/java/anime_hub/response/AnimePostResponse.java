package anime_hub.response;

import lombok.Getter;
import lombok.Builder;
import lombok.Setter;

@Setter
@Getter
@Builder
public class AnimePostResponse {
    private Long id;
    private String name;
}