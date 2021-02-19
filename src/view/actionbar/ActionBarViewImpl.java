package view.actionbar;

import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.craftbukkit.v1_15_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;

import net.minecraft.server.v1_15_R1.ChatMessageType;
import net.minecraft.server.v1_15_R1.IChatBaseComponent;
import net.minecraft.server.v1_15_R1.IChatBaseComponent.ChatSerializer;
import net.minecraft.server.v1_15_R1.PacketPlayOutChat;

public class ActionBarViewImpl implements ActionBarView {
	
	@Override
	public void displayMessage(UUID viewer, String message) {
		Player player = Bukkit.getPlayer(viewer);
		sendActionBar(player, message);
	}

	private void sendActionBar(Player player, String message) {
		final String newMessage = message.replace("_", " ");
		String s = ChatColor.translateAlternateColorCodes('&', newMessage);
		IChatBaseComponent icbc = ChatSerializer.a("{\"text\": \"" + s + "\"}");
		PacketPlayOutChat bar = new PacketPlayOutChat(icbc, ChatMessageType.GAME_INFO);
		((CraftPlayer) player).getHandle().playerConnection.sendPacket(bar);
	}

}
