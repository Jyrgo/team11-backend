package ee.taltech.team_11_backend.model;

import ee.taltech.team_11_backend.Game;
import ee.taltech.team_11_backend.security.MyUser;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name = "ownership")
public class Ownership {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long user;
    private Long game;

    public Ownership(MyUser user, Game game) {
        this.user = user.getId();
        this.game = game.getId();
    }
}
