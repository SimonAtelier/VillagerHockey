package view.message;

import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class MessageViewImpl implements MessageView {

	private String prefix;
	
	@Override
	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}

	@Override
	public void displayMessage(UUID uniquePlayerId, String message) {
		Player player = Bukkit.getPlayer(uniquePlayerId);
		String translatedMessage = ChatColor.translateAlternateColorCodes('&', message);
		String translatedPrefix = ChatColor.translateAlternateColorCodes('&', prefix);
		player.sendMessage(translatedPrefix + " " +  translatedMessage);
	}
	
}
