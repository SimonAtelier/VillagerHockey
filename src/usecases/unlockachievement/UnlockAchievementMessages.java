package usecases.unlockachievement;

public interface UnlockAchievementMessages {

	static final String UNLOCKED_SELF = ">> Achievement Unlocked: $name$ <<";
	
	static final String NO_ACHIEVEMENT_WITH_SUCH_ID = "Internal error: No achievement with such id '$id$'.";
	
	static final String ALREADY_UNLOCKED = "Internal error: Achievement already unlocked '$id$'.";
	
}
