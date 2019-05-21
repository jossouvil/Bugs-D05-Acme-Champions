
package controllers;

import java.util.Collection;
import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import services.ConfigurationService;
import services.ManagerService;
import services.PlayerService;
import services.PresidentService;
import services.TeamService;
import domain.Manager;
import domain.Player;
import domain.President;
import domain.Team;

@Controller
@RequestMapping("/team/president,manager")
public class TeamPresidentManagerController {

	@Autowired
	private PresidentService		presidentService;

	@Autowired
	private TeamService				teamService;

	@Autowired
	private ConfigurationService	configurationService;

	@Autowired
	private ManagerService			managerService;

	@Autowired
	private PlayerService			playerService;


	//Members Team--------------------------------------------------------------------------------------

	@RequestMapping(value = "/listByPresident", method = RequestMethod.GET)
	public ModelAndView listByPresident() {
		final ModelAndView result;
		final String banner = this.configurationService.findConfiguration().getBanner();

		final President president = this.presidentService.findByPrincipal();
		Manager manager;
		final Team team;
		final Collection<Player> players;
		final Collection<Manager> managers = new HashSet<Manager>();

		team = this.teamService.findTeamByPresidentId(president.getId());

		if (team != null) {

			players = this.playerService.findPlayersOfTeam(team.getId());
			manager = this.managerService.findManagerByTeamId(team.getId());
			managers.add(manager);

			result = new ModelAndView("actor/listPlayerManager");
			result.addObject("players", players);
			result.addObject("managers", managers);
			result.addObject("requestURI", "team/president&manager/listByPresident.do");
			result.addObject("pagesize", 5);
			result.addObject("banner", banner);
			result.addObject("language", LocaleContextHolder.getLocale().getLanguage());
			result.addObject("AmInFinder", false);

		} else {
			result = new ModelAndView("misc/notExist");
			result.addObject("banner", banner);
		}

		return result;

	}

	@RequestMapping(value = "/listByManager", method = RequestMethod.GET)
	public ModelAndView listByManager() {
		final ModelAndView result;
		final String banner = this.configurationService.findConfiguration().getBanner();

		final Manager manager = this.managerService.findByPrincipal();
		final Team team;
		final Collection<Player> players;
		final Collection<Manager> managers = new HashSet<Manager>();

		team = manager.getTeam();

		if (team != null) {

			players = this.playerService.findPlayersOfTeam(team.getId());
			managers.add(manager);

			result = new ModelAndView("actor/listPlayerManager");
			result.addObject("players", players);
			result.addObject("managers", managers);
			result.addObject("requestURI", "team/president&manager/listByPresident.do");
			result.addObject("pagesize", 5);
			result.addObject("banner", banner);
			result.addObject("language", LocaleContextHolder.getLocale().getLanguage());
			result.addObject("AmInFinder", false);

		} else {
			result = new ModelAndView("misc/notExist");
			result.addObject("banner", banner);
		}

		return result;

	}
}
