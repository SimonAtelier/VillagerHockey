package achievements;

public class AchieveCondition {

	private int activationValue;
	private String propertyKey;
	private ActivationRule activationRule;

	public AchieveCondition(String propertyKey, ActivationRule activationRule, int activationValue) {
		this.activationValue = activationValue;
		this.propertyKey = propertyKey;
		this.activationRule = activationRule;
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
