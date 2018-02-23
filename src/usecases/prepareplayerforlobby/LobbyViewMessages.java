package usecases.prepareplayerforlobby;

import view.message.MessageCodes;

public interface LobbyViewMessages {

	static final String LOBBY_SELECT_TEAM_DISPLAY_NAME = MessageCodes.YELLOW + "Teamauswahl (Rechtsklick)";
	
	static final String LOBBY_LEAVE_GAME_DISPLAY_NAME =  MessageCodes.YELLOW + "Spiel verlassen (Rechtsklick)";
	
	static final String LOBBY_ACHIEVEMENTS_DISPLAY_NAME =  MessageCodes.YELLOW + "Achievements (Rechtsklick)";
	
	static final String LOBBY_FORCE_START_DISPLAY_NAME =  MessageCodes.YELLOW + "Start erzwingen (Rechtsklick)";
	
}
