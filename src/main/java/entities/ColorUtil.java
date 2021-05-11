package entities;

import java.util.HashMap;

import view.message.MessageCodes;

public class ColorUtil {
	
	private static HashMap<TeamColor, MessageCodes> mapping;
	
	static {
		mapping = new HashMap<TeamColor, MessageCodes>();
		mapping.put(TeamColor.BLACK, MessageCodes.BLACK);
		mapping.put(TeamColor.DARK_BLUE, MessageCodes.DARK_BLUE);
		mapping.put(TeamColor.DARK_GREEN, MessageCodes.DARK_GREEN);
		mapping.put(TeamColor.DARK_AQUA, MessageCodes.DARK_AQUA);
		mapping.put(TeamColor.DARK_RED, MessageCodes.DARK_RED);
		mapping.put(TeamColor.DARK_PURPLE, MessageCodes.DARK_PURPLE);
		mapping.put(TeamColor.GOLD, MessageCodes.GOLD);
		mapping.put(TeamColor.GRAY, MessageCodes.GRAY);
		mapping.put(TeamColor.DARK_GRAY, MessageCodes.DARK_GRAY);
		mapping.put(TeamColor.BLUE, MessageCodes.BLUE);
		mapping.put(TeamColor.GREEN, MessageCodes.GREEN);
		mapping.put(TeamColor.AQUA, MessageCodes.AQUA);
		mapping.put(TeamColor.RED, MessageCodes.RED);
		mapping.put(TeamColor.LIGHT_PURPLE, MessageCodes.LIGHT_PURPLE);
		mapping.put(TeamColor.YELLOW, MessageCodes.YELLOW);
		mapping.put(TeamColor.WHITE, MessageCodes.WHITE);
	}

	public static MessageCodes toMessageCode(TeamColor teamColor) {
		return mapping.get(teamColor);
	}
	
}
