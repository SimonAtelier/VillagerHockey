package view.actionbar;

import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;

public class ActionBarViewImpl implements ActionBarView {
	
	@Override
	public void displayMessage(UUID viewer, String message) {
		Player player = Bukkit.getPlayer(viewer);
		sendActionBar(player, message);
	}

	private void sendActionBar(Player player, String message) {
		final String newMessage = message.replace("_", " ");
		String s = ChatColor.translateAlternateColorCodes('&', newMessage);

		player.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText("Test"));
		
//		IChatBaseComponent icbc = ChatSerializer.a("{\"text\": \"" + s + "\"}");
//		PacketPlayOutChat bar = new PacketPlayOutChat(icbc, ChatMessageType.GAME_INFO);
//		((CraftPlayer) player).getHandle().playerConnection.sendPacket(bar);
	}

}
