
package ee.taltech.team_11_backend.repository.impl;

import ee.taltech.team_11_backend.Game;
import ee.taltech.team_11_backend.repository.GameCustomRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

public class GameRepositoryImpl implements GameCustomRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Game> findByName2(String title) {
        List resultList = entityManager.createNativeQuery("select *\n" +
                "from games\n" +
                "where upper(TITLE) like :title")
                .setParameter(title, "%" + title.toUpperCase() + "%")
                .getResultList();
        return resultList;
    }
}
