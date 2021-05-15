package achievements;

import java.util.ArrayList;
import java.util.List;

public class Achievement {

	private int points;
	private String id;
	private String name;
	private String description;
	private List<AchieveCondition> achieveConditions;
	
	public Achievement(int points, String id, String name, String description) {
		this.points = points;
		this.id = id;
		this.name = name;
		this.description = description;
		this.achieveConditions = new ArrayList<AchieveCondition>();
	}
	
	public void addAchieveCondition(AchieveCondition achieveCondition) {
		achieveConditions.add(achieveCondition);
	}
	
	public List<AchieveCondition> getAchieveConditions() {
		return new ArrayList<AchieveCondition>(achieveConditions);
	}
	
	public boolean isProgress() {
		boolean progress = false;
		for (AchieveCondition condition : achieveConditions) {
			progress |= condition.isProgress();
		}
		return progress;
	}
	
	public int getPoints() {
		return points;
	}

	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}

	public void setProgress(String key, boolean progress) {
		for (AchieveCondition condition : achieveConditions) {
			if (condition.getPropertyKey().equals(key))
				condition.setProgress(progress);
		}
	}
	
	public int getActivationValuesSum() {
		int activationValuesSum = 0;
		for (AchieveCondition achieveCondition : achieveConditions)
			activationValuesSum += achieveCondition.isProgress() ? achieveCondition.getActivationValue() : 0;
		return activationValuesSum;
	}
	
}
