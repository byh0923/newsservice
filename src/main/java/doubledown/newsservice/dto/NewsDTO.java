package doubledown.newsservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@AllArgsConstructor
@Data
public class NewsDTO {
    private String title;
    private String originallink;
    private String link;
    private String description;
    private String pubDate;
    private String site;
    private LocalDateTime registerDate;
    private String keyword;

}
