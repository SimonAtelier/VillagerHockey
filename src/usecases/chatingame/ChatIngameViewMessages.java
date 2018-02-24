package usecases.chatingame;

public interface ChatIngameViewMessages {

	static final String CHAT_INGAME_NO_PERMISSION = "Du hast keine Berechtigung Teamnachrichten zu senden.";
	
	static final String CHAT_INGAME_NO_TEAM = "Du bist keinem Team beigetreten. Beginne deine Nachricht mit '$label$' um sie an alle zu senden.";

	static final String CHAT_INGAME_TEAM_CHAT_MESSAGE_FORMAT = "[TEAM] <$player$> $message$";
	
	static final String CHAT_INGAME_ALL_CHAT_MESSAGE_FORMAT = "[ALL] <$player$> $message$";
	
}
