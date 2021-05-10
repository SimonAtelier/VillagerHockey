package achievements;

public class AchieveCondition {

	private boolean progress;
	private int activationValue;
	private String propertyKey;
	private ActivationRule activationRule;

	public AchieveCondition(String propertyKey, ActivationRule activationRule, int activationValue) {
		this.activationValue = activationValue;
		this.propertyKey = propertyKey;
		this.activationRule = activationRule;
	}
	
	public boolean isProgress() {
		return progress;
	}
	
	public void setProgress(boolean progress) {
		this.progress = progress;
	}

	public boolean isActive(int value) {
		return activationRule.isActive(value, activationValue);
	}

	public int getActivationValue() {
		return activationValue;
	}

	public String getPropertyKey() {
		return propertyKey;
	}

	public ActivationRule getActivationRule() {
		return activationRule;
	}

}
