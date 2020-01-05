package ee.taltech.team_11_backend.Services;

import ee.taltech.team_11_backend.controllers.GameController;
import ee.taltech.team_11_backend.pojo.GameDTO;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class GameControllerTest {

    @InjectMocks
    GameController controller;

    @Mock
    GameService service;

    @Test
    public void getAllGamesTest() {
        List<GameDTO> gameDTOList = new ArrayList<GameDTO>();
        GameDTO game1 = new GameDTO(1, 15, "test1", "desc1", "MMORPG");
        GameDTO game2 = new GameDTO(2, 20, "test2", "desc2", "RPG");
        GameDTO game3 = new GameDTO(3, 25, "test3", "desc3", "SHOOTER");

        gameDTOList.add(game1);
        gameDTOList.add(game2);
        gameDTOList.add(game3);

        when(service.getAllGames("", "")).thenReturn(gameDTOList);

        List<GameDTO> empList = controller.games("", "");
        Assert.assertEquals(3, empList.size());
    }

    @Test
    public void getByIdTest() {
        List<GameDTO> gameDTOList = new ArrayList<GameDTO>();
        GameDTO game = new GameDTO(1, 15, "test1", "desc1", "MMORPG");

        gameDTOList.add(game);

        when(service.getById((long) 0)).thenReturn(gameDTOList.get(0));

        GameDTO newGame = controller.getGame((long) 0);
        Assert.assertEquals(gameDTOList.get(0), newGame);
    }

    @Test
    public void saveGameTest() {
        GameDTO game = new GameDTO(1, 15, "test1", "desc1", "MMORPG");

        when(service.createnewDTO(game)).thenReturn(game);

        GameDTO newGame = controller.saveGame(game);

        Assert.assertEquals(game, newGame);
    }


}