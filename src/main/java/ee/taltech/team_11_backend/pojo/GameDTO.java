package ee.taltech.team_11_backend.pojo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class GameDTO {
    int id;
    double price;
    String title;
    public String description;
    public String tag;

    public GameDTO(int id, double price, String title, String description, String tag) {
        this.id = id;
        this.price = price;
        this.title = title;
        this.description = description;
        this.tag = tag;
    }
}
