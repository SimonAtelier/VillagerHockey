package view.title;

public class TitleViewModelImpl implements TitleViewModel {
	
	private boolean enabled;
	private int titleFadeInTimeInSeconds;
	private int titleStayTimeInSeconds;
	private int titleFadeOutTimeInSeconds;
	private int subtitleFadeInTimeInSeconds;
	private int subtitleStayTimeInSeconds;
	private int subtitleFadeOutTimeInSeconds;
	private String title;
	private String subtitle;
	
	@Override
	public boolean isEnabled() {
		return enabled;
	}
	
	@Override
	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	@Override
	public int getTitleFadeInTimeInSeconds() {
		return titleFadeInTimeInSeconds;
	}
	
	@Override
	public void setTitleFadeInTimeInSeconds(int titleFadeInTimeInSeconds) {
		this.titleFadeInTimeInSeconds = titleFadeInTimeInSeconds;
	}
	
	@Override
	public int getTitleStayTimeInSeconds() {
		return titleStayTimeInSeconds;
	}
	
	@Override
	public void setTitleStayTimeInSeconds(int titleStayTimeInSeconds) {
		this.titleStayTimeInSeconds = titleStayTimeInSeconds;
	}
	
	@Override
	public int getTitleFadeOutTimeInSeconds() {
		return titleFadeOutTimeInSeconds;
	}
	
	@Override
	public void setTitleFadeOutTimeInSeconds(int titleFadeOutTimeInSeconds) {
		this.titleFadeOutTimeInSeconds = titleFadeOutTimeInSeconds;
	}
	
	@Override
	public int getSubtitleFadeInTimeInSeconds() {
		return subtitleFadeInTimeInSeconds;
	}
	
	@Override
	public void setSubtitleFadeInTimeInSeconds(int subtitleFadeInTimeInSeconds) {
		this.subtitleFadeInTimeInSeconds = subtitleFadeInTimeInSeconds;
	}
	
	@Override
	public int getSubtitleStayTimeInSeconds() {
		return subtitleStayTimeInSeconds;
	}
	
	@Override
	public void setSubtitleStayTimeInSeconds(int subtitleStayTimeInSeconds) {
		this.subtitleStayTimeInSeconds = subtitleStayTimeInSeconds;
	}
	
	@Override
	public int getSubtitleFadeOutTimeInSeconds() {
		return subtitleFadeOutTimeInSeconds;
	}
	
	@Override
	public void setSubtitleFadeOutTimeInSeconds(int subtitleFadeOutTimeInSeconds) {
		this.subtitleFadeOutTimeInSeconds = subtitleFadeOutTimeInSeconds;
	}
	
	@Override
	public String getTitle() {
		return title;
	}
	
	@Override
	public void setTitle(String title) {
		this.title = title;
	}
	
	@Override
	public String getSubtitle() {
		return subtitle;
	}
	
	@Override
	public void setSubtitle(String subtitle) {
		this.subtitle = subtitle;
	}
	
}
