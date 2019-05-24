
package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Game;

@Repository
public interface GameRepository extends JpaRepository<Game, Integer> {

	@Query("select g from Game g where (g.homeTeam.id=?1 or g.visitorTeam=?1) and g.gameDate > current_date")
	Collection<Game> findNextGamesOfTeam(int teamId);

	@Query("select g from Game g order by g.gameDate desc")
	Collection<Game> findAllGamesOrdered();

	@Query("select g from Game g where g.homeTeam.id=?1 and g.gameDate < current_date")
	Collection<Game> localGamesByTeamId(int teamId);

	@Query("select g from Game g where g.visitorTeam.id=?1 and g.gameDate < current_date")
	Collection<Game> visitorGamesByTeamId(int teamId);

	@Query("select g from Game g where ((select count(m) from Minutes m where m.game.id=g.id)=0) and (g.gameDate<CURRENT_TIMESTAMP)")
	Collection<Game> findAllEndedGamesWithoutMinutes();

}
