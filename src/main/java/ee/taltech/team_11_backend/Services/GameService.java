package ee.taltech.team_11_backend.Services;

import ee.taltech.team_11_backend.Game;
import ee.taltech.team_11_backend.exception.MyBadRequestException;
import ee.taltech.team_11_backend.model.Ownership;
import ee.taltech.team_11_backend.model.User;
import ee.taltech.team_11_backend.pojo.GameDTO;
import ee.taltech.team_11_backend.repository.GameRepository;
import ee.taltech.team_11_backend.repository.OwnershipRepository;
import ee.taltech.team_11_backend.security.MyUser;
import ee.taltech.team_11_backend.security.UserSessionHolder;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class GameService {

    private final GameRepository gameRepository;
    private final OwnershipRepository ownershipRepository;

    public List<GameDTO> getAllGames(String sortBy, String sortType) {
        Sort sort = new Sort(getDirection(sortType), getSortedBy(sortBy));
        return convertGames(gameRepository.findAll(sort));
    }

    private Sort.Direction getDirection(String sortType) {
        return StringUtils.isNotBlank(sortType) && sortType.equals("asc") ? Sort.Direction.ASC : Sort.Direction.DESC;
    }

    private String getSortedBy(String sortedBy) {
        return StringUtils.isNotBlank(sortedBy) && sortedBy.equals("alphabetically") ? "title" : "price";
    }

    public GameDTO getById(Long id) {
        Game game = getGameById(id);
        return convert(game);
    }

    private Game getGameById(Long id) {
        if (gameRepository.findById(id).isPresent()) {
            System.out.println("Found one!");
            return gameRepository.findById(id).get();
        }
        return new Game();
    }

    public GameDTO createnewDTO(GameDTO gameDTO) {
        Game game = new Game(gameDTO);
        MyUser user = UserSessionHolder.getLoggedInUser();
        assert user != null;
        game = gameRepository.save(game);

        System.out.println("Saving a game. The Id is " + game.getId());

        Ownership ownership = new Ownership(user, game);
        ownershipRepository.save(ownership);
        return convert(game);
    }

    public GameDTO updateGame(GameDTO gameDTO, Long id) {
        Game currentGame = getGameById(id);
        MyUser user = UserSessionHolder.getLoggedInUser();

        if (checkOwnership(currentGame)) {
            Game game = new Game();
            game.setTitle(gameDTO.getTitle());
            game.setDescription(gameDTO.getDescription());
            game.setPrice(gameDTO.getPrice());
            game.setTag(gameDTO.getTag());
            gameRepository.delete(currentGame);
            game = gameRepository.save(game);

            assert user != null;
            System.out.println("USER ID: " + user.getId());
            Ownership ownership = new Ownership(user, game);
            ownershipRepository.save(ownership);

            return convert(game);
        } else return null;
    }

    public void deleteGame(Long id) {
        Game game = getGameById(id);
        if (checkOwnership(game)) {
            gameRepository.delete(game);
        }
    }

    private boolean checkOwnership(Game game) {
        List<Ownership> owners = ownershipRepository.findAll();
        MyUser currentUser = UserSessionHolder.getLoggedInUser();
        for (Ownership owner: owners) {
            System.out.println(owner.getUser());

            assert currentUser != null;
            System.out.println(owner.getGame());
            if (owner.getUser().equals(currentUser.getId()) && owner.getGame().equals(game.getId())) return true;
        }
        return false;
    }

    public boolean checkOwnershipById(Long id) {
        List<Ownership> owners = ownershipRepository.findAll();
        MyUser currentUser = UserSessionHolder.getLoggedInUser();

        for (Ownership owner: owners) {
            assert currentUser != null;
            if (owner.getUser().equals(currentUser.getId()) && owner.getGame().equals(id)) return true;
        }
        return false;
    }

    private List<GameDTO> convertGames(List<Game> byNameContaining) {
        return byNameContaining.stream()
                .map(this::convert)
                .collect(Collectors.toList());
    }


    private GameDTO convert(Game game) {
        GameDTO dto = new GameDTO();
        dto.setId((int) game.getId());
        dto.setTitle(game.getTitle());
        dto.setDescription(game.getDescription());
        dto.setPrice(game.getPrice());
        dto.setTag(game.getTag());
        return dto;
    }


    public List<Game> sortByPriceOrTitle(boolean price, boolean reverse) {
        List<Game> games;
        if (price) {
            games = gameRepository.findAll().stream()
                    .sorted(Comparator.comparing(Game::getPrice))
                    .collect(Collectors.toList());
        } else {
            games = gameRepository.findAll().stream()
                    .sorted(Comparator.comparing(Game::getTitle))
                    .collect(Collectors.toList());
        }
        if (reverse) Collections.reverse(games);
        return games;
    }
    public List<Game> sortByTagOrDescription(boolean description) {
        List<Game> games;
        if (description) {
            games = gameRepository.findAll().stream()
                    .sorted(Comparator.comparing(game -> game.description.length()))
                    .collect(Collectors.toList());
        } else {
            games = gameRepository.findAll().stream()
                    .sorted(Comparator.comparing(game -> game.tag))
                    .collect(Collectors.toList());
        }
        return games;
    }

    public List<Game> sortByTag(String sortingVariable) {
        return gameRepository.findAll().stream()
                .filter(game -> !game.tag.equals(sortingVariable))
                .collect(Collectors.toList());
    }


}
