package ee.taltech.team_11_backend.controllers;


import ee.taltech.team_11_backend.Services.GameService;
import ee.taltech.team_11_backend.pojo.GameDTO;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/game")
@AllArgsConstructor
public class GameController {

    private GameService gameService;

    @GetMapping
    public List<GameDTO> games(@RequestParam(required = false) String sortedBy,
                               @RequestParam(required = false) String sortedType) {
        return gameService.getAllGames(sortedBy, sortedType);
    }

    @GetMapping("/{id}")
    public GameDTO getGame(@PathVariable Long id) {
        return gameService.getById(id);
    }

    @PostMapping("/addGame")
    public GameDTO saveGame(@RequestBody GameDTO game){
        return gameService.createnewDTO(game);
    }

    @PutMapping("/{id}")
    public GameDTO updateGame(@RequestBody GameDTO game, @PathVariable Long id){
        return gameService.updateGame(game, id);
    }

    @DeleteMapping("/{id}")
    public void deleteGame(@PathVariable Long id){
        gameService.deleteGame(id);
    }

    @PostMapping("/{id}")
    public boolean checkOwnership(@PathVariable Long id) {return gameService.checkOwnershipById(id);}

}

