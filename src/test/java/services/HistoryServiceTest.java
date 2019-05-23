
package services;

import java.util.Collection;
import java.util.HashSet;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import utilities.AbstractTest;
import domain.History;
import domain.PersonalData;
import domain.Player;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
	"classpath:spring/junit.xml"
})
@Transactional
public class HistoryServiceTest extends AbstractTest {

	//The SUT----------------------------------------------------
	@Autowired
	private HistoryService		historyService;

	@Autowired
	private PersonalDataService	personalDataService;

	@Autowired
	private PlayerService		playerService;


	/*
	 * ----CALCULATE COVERAGE----
	 * The previous delivery, we calculate it manually. In this one instead we are using the plugin called EclEmma,
	 * with which we can automatically calculate the percentage.
	 * 
	 * Each of the test have their result just before them, and the coverage of the complete test is shown at the end of the document.
	 */

	@Test
	public void driverCreateHistory() {
		final Object testingData[][] = {
			{
				"player2", null
			},//1. All fine
			{
				"manager1", IllegalArgumentException.class
			},//2. Invalid authority
		};

		for (int i = 0; i < testingData.length; i++)
			this.templateCreateHistory((String) testingData[i][0], (Class<?>) testingData[i][1]);
	}
	protected void templateCreateHistory(final String username, final Class<?> expected) {

		Class<?> caught;

		caught = null;
		try {

			this.startTransaction();

			super.authenticate(username);

			final Player player = this.playerService.findOne(super.getEntityId(username));

			final History h = this.historyService.create();

			final PersonalData pd = this.personalDataService.create();
			final Collection<String> photos = new HashSet<>();
			photos.add("http://photo.com/");
			pd.setPhotos(photos);
			pd.setSocialNetworkProfilelink("http://example.com/");
			this.personalDataService.save(pd);
			this.personalDataService.flush();

			h.setPlayer(player);
			h.setPersonalData(pd);

			this.historyService.save(h);
			this.historyService.flush();

		} catch (final Throwable oops) {
			caught = oops.getClass();
		}

		this.unauthenticate();

		this.rollbackTransaction();

		super.checkExceptions(expected, caught);

	}

	@Test
	public void driverDeleteHistory() {
		final Object testingData[][] = {

			{
				"history1", null
			},//1. All fine
			{
				"player2", IllegalArgumentException.class
			},//2. Not history

		};

		for (int i = 0; i < testingData.length; i++)
			this.templateDeleteHistory((String) testingData[i][0], (Class<?>) testingData[i][1]);

	}

	protected void templateDeleteHistory(final String historyBean, final Class<?> expected) {

		Class<?> caught;

		caught = null;
		try {

			this.startTransaction();

			this.historyService.delete(this.historyService.findOne(super.getEntityId(historyBean)));

		} catch (final Throwable oops) {
			caught = oops.getClass();
		}

		this.rollbackTransaction();

		super.checkExceptions(expected, caught);

	}
}
