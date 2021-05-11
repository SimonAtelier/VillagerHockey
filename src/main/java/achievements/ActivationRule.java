package achievements;

public abstract class ActivationRule {

	public static final ActivationRule LESS_THAN = new LessThanActivationRule();
	public static final ActivationRule GREATER_THAN = new GreaterThanActivationRule();
	public static final ActivationRule GREATER_OR_EQUALS_TO = new GreaterOrEqualsToActivationRule();
	public static final ActivationRule LESS_OR_EQUALS_TO = new LessOrEqualsToActivationRule();
	public static final ActivationRule EQUALS = new EqualsToActivationRule();
	
	public abstract boolean isActive(int value, int activationValue);
	
	public static class LessThanActivationRule extends ActivationRule {

		@Override
		public boolean isActive(int value, int activationValue) {
			return value < activationValue;
		}
		
	}
	
	public static class GreaterThanActivationRule extends ActivationRule {

		@Override
		public boolean isActive(int value, int activationValue) {
			return value > activationValue;
		}
		
	}
	
	public static class GreaterOrEqualsToActivationRule extends ActivationRule {

		@Override
		public boolean isActive(int value, int activationValue) {
			return value >= activationValue;
		}
		
	}
	
	public static class LessOrEqualsToActivationRule extends ActivationRule {

		@Override
		public boolean isActive(int value, int activationValue) {
			return value <= activationValue;
		}
		
	}
	
	public static class EqualsToActivationRule extends ActivationRule {

		@Override
		public boolean isActive(int value, int activationValue) {
			return value == activationValue;
		}
		
	}
	
}
