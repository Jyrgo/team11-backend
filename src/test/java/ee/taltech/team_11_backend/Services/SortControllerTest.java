package ee.taltech.team_11_backend.Services;

import ee.taltech.team_11_backend.Game;
import ee.taltech.team_11_backend.controllers.SortController;
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
public class SortControllerTest {

    @InjectMocks
    SortController controller;

    @Mock
    GameService service;

    @Test
    public void getAllGamesWithTagTest() {
        List<Game> testList = new ArrayList<Game>();
        Game game1 = new Game(1, 15, "a", "desc1", "MMORPG");
        Game game2 = new Game(2, 20, "b", "desc2", "RPG");
        Game game3 = new Game(3, 25, "c", "desc3", "SHOOTER");

        testList.add(game1);
        testList.add(game2);
        testList.add(game3);

        when(service.sortByTag("")).thenReturn(testList);

        List<Game> newList = controller.getAllGamesWithTag();

        Assert.assertEquals(testList.get(0).getTag(), newList.get(0).getTag());
    }


    @Test
    public void getAllGamesWithGivenTagTest() {
        List<Game> testList = new ArrayList<Game>();
        Game game1 = new Game(1, 15, "a", "desc1", "MMORPG");

        testList.add(game1);

        when(service.sortByTag("MMORPG")).thenReturn(testList);

        List<Game> newList = controller.getAllGamesWithGivenTag("MMORPG");

        Assert.assertEquals(testList.get(0).getTag(), newList.get(0).getTag());
    }


    @Test
    public void getAllGamesByPriceLowestTest() {
        List<Game> testList = new ArrayList<Game>();
        Game game1 = new Game(1, 15, "a", "desc1", "MMORPG");
        Game game2 = new Game(2, 20, "b", "desc2", "RPG");
        Game game3 = new Game(3, 25, "c", "desc3", "SHOOTER");

        testList.add(game1);
        testList.add(game2);
        testList.add(game3);

        when(service.sortByPriceOrTitle(true, false)).thenReturn(testList);

        List<Game> newList = controller.getAllGamesByPriceLowest();

        //because of precision problems with doubles the third param delta is used as an arbitrary number give a larger range for assertion.
        Assert.assertEquals(testList.get(0).getPrice(), newList.get(0).getPrice(), .2);
    }

    @Test
    public void getAllGamesByPriceHighestTest() {
        List<Game> testList = new ArrayList<Game>();
        Game game1 = new Game(1, 25, "a", "desc1", "MMORPG");
        Game game2 = new Game(2, 20, "b", "desc2", "RPG");
        Game game3 = new Game(3, 15, "c", "desc3", "SHOOTER");

        testList.add(game1);
        testList.add(game2);
        testList.add(game3);

        when(service.sortByPriceOrTitle(true, true)).thenReturn(testList);

        List<Game> newList = controller.getAllGamesByPriceHighest();

        //because of precision problems with doubles the third param delta is used as an arbitrary number give a larger range for assertion.
        Assert.assertEquals(testList.get(0).getPrice(), newList.get(0).getPrice(), .2);
    }

    @Test
    public void getAllGamesByABCTest() {
        List<Game> testList = new ArrayList<Game>();
        Game game1 = new Game(1, 25, "a", "desc1", "MMORPG");
        Game game2 = new Game(2, 20, "b", "desc2", "RPG");
        Game game3 = new Game(3, 15, "c", "desc3", "SHOOTER");

        testList.add(game1);
        testList.add(game2);
        testList.add(game3);

        when(service.sortByPriceOrTitle(false, false)).thenReturn(testList);

        List<Game> newList = controller.getAllGamesAlphabeticalOrder();

        Assert.assertEquals(testList.get(0).getTitle(), newList.get(0).getTitle());
    }

    @Test
    public void getAllGamesByABCReverseTest() {
        List<Game> testList = new ArrayList<Game>();
        Game game1 = new Game(1, 25, "c", "desc1", "MMORPG");
        Game game2 = new Game(2, 20, "b", "desc2", "RPG");
        Game game3 = new Game(3, 15, "a", "desc3", "SHOOTER");

        testList.add(game1);
        testList.add(game2);
        testList.add(game3);

        when(service.sortByPriceOrTitle(false, true)).thenReturn(testList);

        List<Game> newList = controller.getAllGamesAlphabeticalOrderReversed();

        Assert.assertEquals(testList.get(0).getTitle(), newList.get(0).getTitle());
    }

    @Test
    public void getAllGamesGroupedByFirstTagTest() {
        List<Game> testList = new ArrayList<Game>();
        Game game1 = new Game(1, 25, "c", "desc1", "MMORPG");
        Game game2 = new Game(2, 20, "b", "desc2", "RPG");
        Game game3 = new Game(3, 15, "a", "desc3", "SHOOTER");

        testList.add(game1);
        testList.add(game2);
        testList.add(game3);

        when(service.sortByTagOrDescription(false)).thenReturn(testList);

        List<Game> newList = controller.getAllGamesGroupedByFirstTag();

        Assert.assertEquals(testList.get(0).getTag(), newList.get(0).getTag());
    }

    @Test
    public void getAllGamesByDescLengthTest() {
        List<Game> testList = new ArrayList<Game>();
        Game game1 = new Game(1, 25, "c", "desc1", "MMORPG");
        Game game2 = new Game(2, 20, "b", "desc2", "RPG");
        Game game3 = new Game(3, 15, "a", "desc3", "SHOOTER");

        testList.add(game1);
        testList.add(game2);
        testList.add(game3);

        when(service.sortByTagOrDescription(true)).thenReturn(testList);

        List<Game> newList = controller.getAllGamesByDescLength();

        Assert.assertEquals(testList.get(0).getDescription(), newList.get(0).getDescription());
    }
}
