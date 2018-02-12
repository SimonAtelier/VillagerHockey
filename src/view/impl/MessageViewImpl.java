package view.impl;

import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import view.MessageView;

public class MessageViewImpl implements MessageView {

	@Override
	public void displayMessage(UUID uniquePlayerId, String message) {
		Player player = Bukkit.getPlayer(uniquePlayerId);
		player.sendMessage(ChatColor.WHITE + "[VillagerHockey] " + ChatColor.GRAY +  message);
	}

}
