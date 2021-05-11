package prototype;

import java.io.File;
import java.util.List;

import achievements.Achievement;

public class Test {

	public static void main(String[] args) throws Exception {
		File file = new File(AchievementsListToMarkdownTable.class.getResource("achievements.json").getFile());
		AchievementsConfig config = new AchievementsConfig(file);
		List<Achievement> achievements = config.getAchievements();
		
		System.out.println("|id|name|description|points|");
		
		for (Achievement achievement : achievements) {
			System.out.println("|" + achievement.getId() + "|" + achievement.getName() + "|" + achievement.getDescription() + "|" + achievement.getPoints() + "|");
		}
	}

}
