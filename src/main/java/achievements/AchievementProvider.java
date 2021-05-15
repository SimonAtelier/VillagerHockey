package achievements;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class AchievementProvider {
	
	private AchievementSystem achievementSystem;
	private String pluginFolderPath = "plugins/VillagerHockey/achievements.json";
	
	public void registerAchievements(AchievementSystem achievementSystem) {
		setAchievementSystem(achievementSystem);
		createOrUpdateAchievements();
		try {
			File file = new File(pluginFolderPath);
			CustomAchievements achievementConfig = new CustomAchievements(file);
			for (Achievement achievement : achievementConfig.getAchievements()) {
				registerAchievement(achievement);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void createOrUpdateAchievements() {
		File file = new File(pluginFolderPath);
		if (file.exists())
			return;
		try {
			createAchievementsFromResource();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void createAchievementsFromResource() throws IOException {
		String resourcePath = "/achievements.json";
		File file = new File(pluginFolderPath);
		file.createNewFile();
		InputStream is = null;
		FileOutputStream os = null;
		try {
			is = getClass().getResource(resourcePath).openStream();
			os = new FileOutputStream(file);
			byte[] buffer = new byte[1024];
			int length;
			while ((length = is.read(buffer)) > 0) {
				os.write(buffer, 0, length);
			}
		} finally {
			is.close();
			os.close();
		}
	}
	
	private void registerAchievement(Achievement achievement) {
		achievementSystem.registerAchievement(achievement);
	}
	
	private void setAchievementSystem(AchievementSystem achievementSystem) {
		this.achievementSystem = achievementSystem;
	}

}
