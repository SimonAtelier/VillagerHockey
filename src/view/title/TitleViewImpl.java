package view.title;

import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.v1_10_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;

import net.minecraft.server.v1_10_R1.IChatBaseComponent.ChatSerializer;
import net.minecraft.server.v1_10_R1.PacketPlayOutTitle;
import net.minecraft.server.v1_10_R1.PacketPlayOutTitle.EnumTitleAction;

public class TitleViewImpl implements TitleView {

	private TitleViewModel titleViewModel;

	@Override
	public void display(UUID viewer) {
		displayTitle(viewer);
	}

	@Override
	public TitleViewModel getTitleViewModel() {
		return titleViewModel;
	}

	@Override
	public void setTitleViewModel(TitleViewModel titleViewModel) {
		this.titleViewModel = titleViewModel;
	}

	private PacketPlayOutTitle createPacketPlayOutTitleForTitle() {
		int fadeInTicks = getTitleViewModel().getTitleFadeInTimeInSeconds() * 20;
		int stayTicks = getTitleViewModel().getTitleStayTimeInSeconds() * 20;
		int fadeOutTicks = getTitleViewModel().getTitleFadeOutTimeInSeconds() * 20;
		String title = getTitleViewModel().getTitle();
		return new PacketPlayOutTitle(EnumTitleAction.TITLE, ChatSerializer.a("{\"text\":\"" + title + "\"}"),
				fadeInTicks, stayTicks, fadeOutTicks);
	}

	private PacketPlayOutTitle createPacketPlayOutTitleForSubtitle() {
		int fadeInTicks = getTitleViewModel().getSubtitleFadeInTimeInSeconds() * 20;
		int stayTicks = getTitleViewModel().getSubtitleStayTimeInSeconds() * 20;
		int fadeOutTicks = getTitleViewModel().getSubtitleFadeOutTimeInSeconds() * 20;
		String title = getTitleViewModel().getTitle();
		return new PacketPlayOutTitle(EnumTitleAction.SUBTITLE, ChatSerializer.a("{\"text\":\"" + title + "\"}"),
				fadeInTicks, stayTicks, fadeOutTicks);
	}

	private void displayTitle(UUID viewer) {
		Player player = Bukkit.getPlayer(viewer);
		((CraftPlayer) player).getHandle().playerConnection.sendPacket(createPacketPlayOutTitleForTitle());
		((CraftPlayer) player).getHandle().playerConnection.sendPacket(createPacketPlayOutTitleForSubtitle());
	}

}
