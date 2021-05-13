package achievements;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class CustomAchievementsTest {

	private List<Achievement> achievements;

	@Before
	public void setUp() throws IOException {
		File file = new File(getClass().getResource("/achievements.json").getFile());
		CustomAchievements customAchievements = new CustomAchievements(file);
		achievements = customAchievements.getAchievements();
	}

	@Test
	public void customAchievementsListIsNotEmptyByDefault() throws IOException {
		assertEquals(false, achievements.isEmpty());
	}

	@Test
	public void namesAreNotNull() throws IOException {
		for (Achievement achievement : achievements) {
			assertNotNull(achievement.getName());
		}
	}

	@Test
	public void descriptionsAreNotNull() throws IOException {
		for (Achievement achievement : achievements) {
			assertNotNull(achievement.getDescription());
		}
	}

	@Test
	public void idsAreNotNull() throws IOException {
		for (Achievement achievement : achievements) {
			assertNotNull(achievement.getId());
		}
	}

	@Test
	public void conditionsAreNotEmpty() throws IOException {
		for (Achievement achievement : achievements) {
			assertFalse(achievement.getAchieveConditions().isEmpty());
		}
	}

	@Test
	public void conditionPropertyKeysAreNotNull() throws IOException {
		for (Achievement achievement : achievements) {
			for (AchieveCondition condition : achievement.getAchieveConditions()) {
				assertNotNull(condition.getPropertyKey());
			}
		}
	}
	
	@Test
	public void conditionActionvationRulesAreNotNull() throws IOException {
		for (Achievement achievement : achievements) {
			for (AchieveCondition condition : achievement.getAchieveConditions()) {
				assertNotNull(condition.getActivationRule());
			}
		}
	}
	
	@Test
	public void achievementProgessIsTrueWhenAtLeasOneConditionProgress() throws IOException {
		for (Achievement achievement : achievements) {
			boolean progress = false;
			for (AchieveCondition condition : achievement.getAchieveConditions()) {
				progress |= condition.isProgress();
			}
			assertEquals(progress, achievement.isProgress());
		}
	}

}
