package prototype;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import achievements.AchieveCondition;
import achievements.Achievement;
import achievements.ActivationRule;

public class AchievementsConfig {

	public AchievementsConfig(File file) {
		
	}
	
	public List<Achievement> getAchievements() throws IOException {
		List<Achievement> achievements = new ArrayList<Achievement>();
		File file = new File("plugins/VillagerHockey/achievements.json");
		FileReader reader = new FileReader(file);
		JsonParser parser = new JsonParser();
		Object obj = parser.parse(reader);
		JsonObject jsonObject = (JsonObject) obj;
		JsonArray achievementsList = (JsonArray) jsonObject.get("achievements_list");
		Iterator<JsonElement> iterator = achievementsList.iterator();
		while (iterator.hasNext()) {
			JsonElement element = iterator.next();
			Achievement achievement = fromJsonObject(element.getAsJsonObject());
			JsonObject conditions = element.getAsJsonObject();
			conditions(achievement, conditions.getAsJsonArray("achieve_conditions"));
			achievements.add(achievement);
			
			
			try {
				JsonArray keys = (JsonArray) element.getAsJsonObject().getAsJsonArray("progress_keys");
				if (keys.size() > 0)
					achievement.setProgress(true);
				for (int i = 0; i < keys.size(); i++) {
					achievement.setProgress(keys.get(i).getAsString(), true);
				}
			} catch (Exception e) {

			}
			
		}
		return achievements;
	}
	
	

	private void conditions(Achievement achievement, JsonArray conditions) {
		Iterator<JsonElement> iterator = conditions.iterator();
		while (iterator.hasNext()) {
			JsonObject condition = iterator.next().getAsJsonObject();
			String key = condition.getAsJsonPrimitive("key").getAsString();
			String rule = condition.getAsJsonPrimitive("rule").getAsString();
			int value = condition.getAsJsonPrimitive("value").getAsInt();
			AchieveCondition achieveCondition = new AchieveCondition(key, getActivationRule(rule), value);
			achievement.addAchieveCondition(achieveCondition);
		}
	}

	private ActivationRule getActivationRule(String rule) {
		switch (rule) {
		case "==":
			return ActivationRule.EQUALS;
		case "<=":
			return ActivationRule.LESS_OR_EQUALS_TO;
		case "<":
			return ActivationRule.LESS_THAN;
		case ">=":
			return ActivationRule.GREATER_OR_EQUALS_TO;
		case ">":
			return ActivationRule.GREATER_THAN;
		default:
			return null;
		}
	}

	private Achievement fromJsonObject(JsonObject jsonObject) {
		String id = jsonObject.getAsJsonPrimitive("id").getAsString();
		String name = jsonObject.getAsJsonPrimitive("name").getAsString();
		String description = jsonObject.getAsJsonPrimitive("description").getAsString();
		int points = jsonObject.getAsJsonPrimitive("points").getAsInt();
		Achievement achievement = new Achievement(points, id, name, description);
		return achievement;
	}

}
