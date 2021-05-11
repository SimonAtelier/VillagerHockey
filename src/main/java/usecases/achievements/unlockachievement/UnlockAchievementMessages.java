package usecases.achievements.unlockachievement;

public interface UnlockAchievementMessages {

	static final String UNLOCKED_SELF = "§e§kM§r§e>> Achievement Unlocked: §6$name$ §e<<§kM§r";
	
	static final String NO_ACHIEVEMENT_WITH_SUCH_ID = "Internal error: No achievement with such id '$id$'.";
	
	static final String ALREADY_UNLOCKED = "Internal error: Achievement already unlocked '$id$'.";
	
}
