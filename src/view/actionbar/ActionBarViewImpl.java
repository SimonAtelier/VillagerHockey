package view.actionbar;

import net.minecraft.server.v1_10_R1.IChatBaseComponent;
import net.minecraft.server.v1_10_R1.IChatBaseComponent.ChatSerializer;
import net.minecraft.server.v1_10_R1.PacketPlayOutChat;

import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.craftbukkit.v1_10_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;

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
		PacketPlayOutChat bar = new PacketPlayOutChat(icbc, (byte) 2);
		((CraftPlayer) player).getHandle().playerConnection.sendPacket(bar);
	}

}
