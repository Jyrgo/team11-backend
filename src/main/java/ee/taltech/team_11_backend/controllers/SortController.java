package ee.taltech.team_11_backend.controllers;

import ee.taltech.team_11_backend.Game;
import ee.taltech.team_11_backend.Services.GameService;
import ee.taltech.team_11_backend.repository.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/sort")
public class SortController {

    private final GameRepository gameRepository;
    private final GameService gameService;

    @Autowired
    public SortController(GameRepository gameRepository, GameService gameService) {
        this.gameRepository = gameRepository;
        this.gameService = gameService;
    }

    @GetMapping("/getAllGamesWithTag")
    public List<Game> getAllGamesWithTag() {
        /* Return games that contain a tag. */
        return gameService.sortByTag("");
    }

    @PostMapping("/getAllGamesWithGivenTag")
    public List<Game> getAllGamesWithGivenTag(@RequestBody String tag) {
        /* Return games that contain given tag. */
        return gameService.sortByTag(tag);
    }

    @GetMapping("/getAllGamesByPriceLowest")
    public List<Game> getAllGamesByPriceLowest() {
        /* Return games sorted by price, starting with the lowest. */
        return gameService.sortByPriceOrTitle(true, false);
    }

    @GetMapping("/getAllGamesByPriceHighest")
    public List<Game> getAllGamesByPriceHighest() {
        /* Return games sorted by price, starting with the highest. */
        return gameService.sortByPriceOrTitle(true, true);
    }

    @GetMapping("/getAllGamesAbc")
    public List<Game> getAllGamesAlphabeticalOrder() {
        /* Return games in alphabetical order. */
        return gameService.sortByPriceOrTitle(false, false);
    }

    @GetMapping("/getAllGamesAbcReverse")
    public List<Game> getAllGamesAlphabeticalOrderReversed() {
        /* Return games in reversed alphabetical order. */
        return gameService.sortByPriceOrTitle(false, true);
    }

    @GetMapping("/getAllGamesGroupedByFirstTag")
    public List<Game> getAllGamesGroupedByFirstTag() {
        /* Return games grouped by tag. */
        return gameService.sortByTagOrDescription(false);
    }

    @GetMapping("/getAllGamesByDescriptionLength")
    public List<Game> getAllGamesByDescLength() {
        /* Return games by Description Length. */
        return gameService.sortByTagOrDescription(true);
    }
}
