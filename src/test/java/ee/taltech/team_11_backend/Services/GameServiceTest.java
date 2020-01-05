package ee.taltech.team_11_backend.Services;

import ee.taltech.team_11_backend.Game;
import ee.taltech.team_11_backend.pojo.GameDTO;
import ee.taltech.team_11_backend.repository.GameRepository;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.data.domain.Sort;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class GameServiceTest {
    @InjectMocks
    GameService service;

    @Mock
    GameRepository repository;

    @Test
    public void getAllGamesTest() {
        List<Game> testList = new ArrayList<Game>();
        Game game1 = new Game(1, 15, "a", "desc1", "MMORPG");
        Game game2 = new Game(2, 20, "b", "desc2", "RPG");
        Game game3 = new Game(3, 25, "c", "desc3", "SHOOTER");

        testList.add(game1);
        testList.add(game2);
        testList.add(game3);

        List<GameDTO> gameDTOList = new ArrayList<GameDTO>();
        GameDTO one = new GameDTO(1, 15, "a", "desc1", "MMORPG");
        GameDTO two = new GameDTO(2, 20, "b", "desc2", "RPG");
        GameDTO three = new GameDTO(3, 25, "c", "desc3", "SHOOTER");

        gameDTOList.add(one);
        gameDTOList.add(two);
        gameDTOList.add(three);

        Sort.Direction type = Sort.Direction.ASC;
        String by = "title";

        Sort sort = new Sort(type, by);

        when(repository.findAll(sort)).thenReturn(testList);

        String sortType = "asc";
        String sortBy = "alphabetically";
        List<GameDTO> newList = service.getAllGames(sortBy, sortType);

        Assert.assertEquals(newList.get(0).getTitle(), gameDTOList.get(0).getTitle());
    }

    @Ignore
    @Test
    public void createNewDTOTest() {

        GameDTO gameDTO = new GameDTO(1, 15, "a", "desc1", "MMORPG");
        Game game = new Game(1, 15, "a", "desc1", "MMORPG");

        when(repository.save(Mockito.any())).thenReturn(game);

        GameDTO newGameDTO = service.createnewDTO(gameDTO);

        Assert.assertEquals(gameDTO.getTitle(), newGameDTO.getTitle());
    }


    @Ignore
    @Test
    public void updateGameTest() {
        GameDTO gameDTO = new GameDTO(1, 15, "a", "desc1", "MMORPG");
        Game game = new Game(1, 15, "a", "desc1", "MMORPG");

        when(repository.save(Mockito.any())).thenReturn(game);

        GameDTO newDTO = service.updateGame(gameDTO, game.getId());

        Assert.assertEquals(gameDTO.getTitle(), newDTO.getTitle());
    }

    @Test
    public void sortByPriceTest() {
        List<Game> testList = new ArrayList<Game>();
        Game game1 = new Game(1, 15, "a", "desc1", "MMORPG");
        Game game2 = new Game(2, 20, "b", "desc2", "RPG");
        Game game3 = new Game(3, 25, "c", "desc3", "SHOOTER");

        testList.add(game1);
        testList.add(game2);
        testList.add(game3);

        when(repository.findAll()).thenReturn(testList);

        List<Game> getList = service.sortByPriceOrTitle(true, false);

        Assert.assertEquals(testList.get(0).getTitle(), getList.get(0).getTitle());
    }

    @Test
    public void sortByTitleTest() {
        List<Game> testList = new ArrayList<Game>();
        Game game1 = new Game(1, 15, "a", "desc1", "MMORPG");
        Game game2 = new Game(2, 20, "b", "desc2", "RPG");
        Game game3 = new Game(3, 25, "c", "desc3", "SHOOTER");

        testList.add(game1);
        testList.add(game2);
        testList.add(game3);

        when(repository.findAll()).thenReturn(testList);

        List<Game> getList = service.sortByPriceOrTitle(false, false);

        Assert.assertEquals(testList.get(0).getTitle(), getList.get(0).getTitle());
    }

    @Test
    public void sortByDescriptionTest() {
        List<Game> testList = new ArrayList<Game>();
        Game game1 = new Game(1, 15, "a", "desc1", "MMORPG");
        Game game2 = new Game(2, 20, "b", "desc2", "RPG");
        Game game3 = new Game(3, 25, "c", "desc3", "SHOOTER");

        testList.add(game1);
        testList.add(game2);
        testList.add(game3);

        when(repository.findAll()).thenReturn(testList);

        List<Game> getList = service.sortByTagOrDescription(true);

        Assert.assertEquals(testList.get(0).getTitle(), getList.get(0).getTitle());
    }

    @Test
    public void sortByTagTest() {
        List<Game> testList = new ArrayList<Game>();
        Game game1 = new Game(1, 15, "a", "desc1", "MMORPG");
        Game game2 = new Game(2, 20, "b", "desc2", "RPG");
        Game game3 = new Game(3, 25, "c", "desc3", "SHOOTER");

        testList.add(game1);
        testList.add(game2);
        testList.add(game3);

        when(repository.findAll()).thenReturn(testList);

        List<Game> getList = service.sortByTagOrDescription(false);

        Assert.assertEquals(testList.get(0).getTitle(), getList.get(0).getTitle());
    }

    @Test
    public void sortByTagTest2() {
        List<Game> testList = new ArrayList<Game>();
        Game game1 = new Game(1, 15, "a", "desc1", "MMORPG");
        Game game2 = new Game(2, 20, "b", "desc2", "RPG");
        Game game3 = new Game(3, 25, "c", "desc3", "SHOOTER");

        testList.add(game1);
        testList.add(game2);
        testList.add(game3);

        when(repository.findAll()).thenReturn(testList);

        List<Game> getList = service.sortByTag("MMORPG");

        Assert.assertEquals(testList.get(1).getTitle(), getList.get(0).getTitle());
    }

}
