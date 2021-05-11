package achievements;

public class AchieveCondition extends Condition {

	private boolean progress;
	
	public AchieveCondition(String propertyKey, ActivationRule activationRule, int activationValue) {
		super(propertyKey, activationRule, activationValue);
	}
	
	public boolean isProgress() {
		return progress;
	}
	
	public void setProgress(boolean progress) {
		this.progress = progress;
	}

}
