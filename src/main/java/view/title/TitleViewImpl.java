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
		int fadeIn = titleViewModel.getFadeInTimeInSeconds() * 20;
		int fadeOut = titleViewModel.getFadeOutTimeInSeconds() * 20;
		int stay = titleViewModel.getStayTimeInSeconds() * 20;
		Bukkit.getPlayer(viewer).sendTitle(title, subtitle, fadeIn, stay, fadeOut);
	}

}
