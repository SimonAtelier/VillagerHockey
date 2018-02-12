package view.impl;

import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.v1_10_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;

import net.minecraft.server.v1_10_R1.IChatBaseComponent.ChatSerializer;
import net.minecraft.server.v1_10_R1.PacketPlayOutTitle;
import net.minecraft.server.v1_10_R1.PacketPlayOutTitle.EnumTitleAction;
import view.TitleBarView;

public class TitleBarViewImpl implements TitleBarView {
	
    @Override
	public void displayTitle(UUID viewer, String title, String subtitle, int timeInSeconds) {
		Player player = Bukkit.getPlayer(viewer);
		displayTitle(player, title, subtitle, timeInSeconds);
	}

	private void displayTitle(Player player, String title, String subtitle, int time) {
        PacketPlayOutTitle titles = new PacketPlayOutTitle(
                EnumTitleAction.TITLE, ChatSerializer.a("{\"text\":\"" + title
                        + "\"}"), 20, time * 20, 20);
        ((CraftPlayer) player).getHandle().playerConnection.sendPacket(titles);
        PacketPlayOutTitle subtitles = new PacketPlayOutTitle(
                EnumTitleAction.SUBTITLE, ChatSerializer.a("{\"text\":\""
                        + subtitle + "\"}"), 20, time * 20, 20);
        ((CraftPlayer) player).getHandle().playerConnection.sendPacket(subtitles);
    }
	
}
