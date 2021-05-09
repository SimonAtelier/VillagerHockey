package usecases.displayachievements;

public class AchievementResponseItem {

	private int points;
	private int activationValuesSum;
	private int currentProgress;
	private boolean progress;
	private boolean unlocked;
	private String name;
	private String description;
	
	public int getCurrentProgress() {
		return currentProgress;
	}

	public void setCurrentProgress(int currentProgress) {
		this.currentProgress = currentProgress;
	}

	public int getPoints() {
		return points;
	}

	public void setPoints(int points) {
		this.points = points;
	}
	
	public boolean isProgress() {
		return progress;
	}

	public void setProgress(boolean progress) {
		this.progress = progress;
	}

	public int getActivationValuesSum() {
		return activationValuesSum;
	}

	public void setActivationValuesSum(int activationValuesSum) {
		this.activationValuesSum = activationValuesSum;
	}

	public boolean isUnlocked() {
		return unlocked;
	}

	public void setUnlocked(boolean unlocked) {
		this.unlocked = unlocked;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
