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

	private int fadeInTimeInSeconds;
	private int fadeOutTimeInSeconds;
	private int stayTimeInSeconds;
	private UUID viewer;
	private String title;
	private String subtitle;

	@Override
	public void display() {
		displayTitle();
	}

	@Override
	public void setViewer(UUID viewer) {
		this.viewer = viewer;
	}

	@Override
	public void setTitle(String title) {
		this.title = title;
	}

	@Override
	public void setSubtitle(String subtitle) {
		this.subtitle = subtitle;
	}

	@Override
	public void setFadeInTimeInSeconds(int fadeInTimeInSeconds) {
		this.fadeInTimeInSeconds = fadeInTimeInSeconds;
	}

	@Override
	public void setFadeOutTimeInSeconds(int fadeOutTimeInSeconds) {
		this.fadeInTimeInSeconds = fadeOutTimeInSeconds;
	}

	@Override
	public void setStayTimeInSeconds(int stayTimeInSeconds) {
		this.stayTimeInSeconds = stayTimeInSeconds;
	}

	private void displayTitle() {
		Player player = Bukkit.getPlayer(viewer);
		PacketPlayOutTitle titles = new PacketPlayOutTitle(EnumTitleAction.TITLE,
				ChatSerializer.a("{\"text\":\"" + title + "\"}"), fadeInTimeInSeconds * 20, stayTimeInSeconds * 20,
				fadeOutTimeInSeconds * 20);
		((CraftPlayer) player).getHandle().playerConnection.sendPacket(titles);
		PacketPlayOutTitle subtitles = new PacketPlayOutTitle(EnumTitleAction.SUBTITLE,
				ChatSerializer.a("{\"text\":\"" + subtitle + "\"}"), fadeInTimeInSeconds * 20, stayTimeInSeconds * 20,
				fadeOutTimeInSeconds * 20);
		((CraftPlayer) player).getHandle().playerConnection.sendPacket(subtitles);
	}

}
