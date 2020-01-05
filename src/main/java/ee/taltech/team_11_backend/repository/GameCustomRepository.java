package ee.taltech.team_11_backend.repository;
import ee.taltech.team_11_backend.Game;
import java.util.List;

public interface GameCustomRepository {

    List<Game> findByName2(String name);

}