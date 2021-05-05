package view.title;

import java.util.UUID;

import org.bukkit.Bukkit;

public class TitleViewImpl implements TitleView {

	private TitleViewModel titleViewModel;
	
	public TitleViewImpl() {
		titleViewModel = new TitleViewModelImpl();
	}

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
	
	private void displayTitle(UUID viewer) {
		String title = titleViewModel.getTitle();
		String subtitle = titleViewModel.getSubtitle();
		int fadeIn = titleViewModel.getTitleFadeInTimeInSeconds() * 20;
		int fadeOut = titleViewModel.getTitleFadeOutTimeInSeconds() * 20;
		int stay = titleViewModel.getTitleStayTimeInSeconds() * 20;
		Bukkit.getPlayer(viewer).sendTitle(title, subtitle, fadeIn, stay, fadeOut);
	}

//	private PacketPlayOutTitle createPacketPlayOutTitleForTitle() {
//		int fadeInTicks = getTitleViewModel().getTitleFadeInTimeInSeconds() * 20;
//		int stayTicks = getTitleViewModel().getTitleStayTimeInSeconds() * 20;
//		int fadeOutTicks = getTitleViewModel().getTitleFadeOutTimeInSeconds() * 20;
//		String title = getTitleViewModel().getTitle();
//		return new PacketPlayOutTitle(EnumTitleAction.TITLE, ChatSerializer.a("{\"text\":\"" + title + "\"}"),
//				fadeInTicks, stayTicks, fadeOutTicks);
//	}
//
//	private PacketPlayOutTitle createPacketPlayOutTitleForSubtitle() {
//		int fadeInTicks = getTitleViewModel().getSubtitleFadeInTimeInSeconds() * 20;
//		int stayTicks = getTitleViewModel().getSubtitleStayTimeInSeconds() * 20;
//		int fadeOutTicks = getTitleViewModel().getSubtitleFadeOutTimeInSeconds() * 20;
//		String title = getTitleViewModel().getSubtitle();
//		return new PacketPlayOutTitle(EnumTitleAction.SUBTITLE, ChatSerializer.a("{\"text\":\"" + title + "\"}"),
//				fadeInTicks, stayTicks, fadeOutTicks);
//	}
//
//	private void displayTitle(UUID viewer) {
//		Player player = Bukkit.getPlayer(viewer);
//		((CraftPlayer) player).getHandle().playerConnection.sendPacket(createPacketPlayOutTitleForTitle());
//		((CraftPlayer) player).getHandle().playerConnection.sendPacket(createPacketPlayOutTitleForSubtitle());
//	}

}
