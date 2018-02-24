package usecases.chatwithteam;

public interface ChatWithTeamViewMessages {

	static final String CHAT_WITH_TEAM_NO_PERMISSION = "Du hast keine Berechtigung Teamnachrichten zu senden.";
	
	static final String CHAT_WITH_TEAM_NO_TEAM = "Du bist keinem Team beigetreten. Verwende die 'SHOUT' Funktion um Nachrichten an alle zu senden.";

	static final String CHAT_WITH_TEAM_TEAM_MESSAGE = "[TEAM] <$player$> $message$";
	
}
