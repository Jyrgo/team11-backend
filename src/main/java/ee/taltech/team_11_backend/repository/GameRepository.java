package ee.taltech.team_11_backend.repository;

import ee.taltech.team_11_backend.Game;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GameRepository extends JpaRepository<Game, Long>, GameCustomRepository {
    /*
    @Query("select g from games g where upper(title) like concat('%', upper(:title),'%') ")
    List<Game> findByTitle(@Param(value = "title") String name); */
}
