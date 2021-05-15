package achievements;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class AchievementTest {

	Achievement achievement;

	@Before
	public void setUp() {
		achievement = new Achievement(5, "achievement-1", "Achievement 1", "Nice Test Achievement!");
	}
	
	@Test
	public void getId() {
		assertEquals("achievement-1", achievement.getId());
	}
 	
	@Test
	public void getDescription() {
		assertEquals("Nice Test Achievement!", achievement.getDescription());
	}
	
	@Test
	public void getName() {
		assertEquals("Achievement 1", achievement.getName());
	}

	@Test
	public void getPoints() {
		assertEquals(5, achievement.getPoints());
	}

	@Test
	public void getActivationValueSumWithTwoKeys() {
		achievement.addAchieveCondition(new AchieveCondition("key-1", ActivationRule.EQUALS, 10));
		achievement.addAchieveCondition(new AchieveCondition("key-2", ActivationRule.EQUALS, 311));
		achievement.setProgress("key-1", true);
		achievement.setProgress("key-2", true);
		assertEquals(321, achievement.getActivationValuesSum());
	}
	
	@Test
	public void getActivationValueSumWithOneKey() {
		achievement.addAchieveCondition(new AchieveCondition("key-1", ActivationRule.EQUALS, 10));
		achievement.addAchieveCondition(new AchieveCondition("key-2", ActivationRule.EQUALS, 311));
		achievement.setProgress("key-1", true);
		achievement.setProgress("key-2", false);
		assertEquals(10, achievement.getActivationValuesSum());
	}

}
