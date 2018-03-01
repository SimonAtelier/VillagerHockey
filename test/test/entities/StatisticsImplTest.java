package test.entities;

import org.junit.Assert;
import org.junit.Test;

import entities.StatisticsImpl;

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
	
	@Test
	public void getGamesLostReturnsDifference() {
		StatisticsImpl statistics = new StatisticsImpl();
		statistics.setGamesPlayed(40);
		statistics.setGamesWon(10);
		statistics.setGamesDraw(20);
		Assert.assertEquals(10, statistics.getGamesLost());
	}
	
}
