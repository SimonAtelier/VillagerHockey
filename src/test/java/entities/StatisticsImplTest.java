package entities;

import org.junit.Assert;
import org.junit.Test;

public class StatisticsImplTest {

	@Test
	public void setGamesWonGetGamesWonReturnsValue() {
		StatisticsImpl statistics = new StatisticsImpl();
		statistics.setGamesWon(10);
		Assert.assertEquals(10, statistics.getGamesWon());
	}
	
	@Test
	public void setGamesPlayedGetGamesPlayedReturnsValue() {
		StatisticsImpl statistics = new StatisticsImpl();
		statistics.setGamesPlayed(20);
		Assert.assertEquals(20, statistics.getGamesPlayed());
	}
	
	@Test
	public void setGamesDrawGetGamesDrawReturnsValue() {
		StatisticsImpl statistics = new StatisticsImpl();
		statistics.setGamesDraw(10);
		Assert.assertEquals(10, statistics.getGamesDraw());
	}
	
}
