package ee.taltech.team_11_backend;

import ee.taltech.team_11_backend.pojo.GameDTO;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "games")
public class Game {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;
    double price;
    String title;
    public String description;
    public String tag;

    Game(double price, String title, String description, String tag) {
        this.price = price;
        this.title = title;
        this.description = description;
        this.tag = tag;
    }

    public Game(long id, double price, String title, String description, String tag) {
        this.id = id;
        this.price = price;
        this.title = title;
        this.description = description;
        this.tag = tag;
    }

    public Game() {
        this.price = 0;
        this.title = "";
        this.description = "";
        this.tag = "";
    }

    public Game(GameDTO dto) {
        this.price = dto.getPrice();
        this.title = dto.getTitle();
        this.description = dto.getDescription();
        this.tag = dto.getTag();
    }

}
