package view.title;

public class TitleViewModelImpl implements TitleViewModel {
	
	private int titleFadeInTimeInSeconds;
	private int titleStayTimeInSeconds;
	private int titleFadeOutTimeInSeconds;
	private String title;
	private String subtitle;
	
	@Override
	public int getFadeInTimeInSeconds() {
		return titleFadeInTimeInSeconds;
	}
	
	@Override
	public void setFadeInTimeInSeconds(int titleFadeInTimeInSeconds) {
		this.titleFadeInTimeInSeconds = titleFadeInTimeInSeconds;
	}
	
	@Override
	public int getStayTimeInSeconds() {
		return titleStayTimeInSeconds;
	}
	
	@Override
	public void setStayTimeInSeconds(int titleStayTimeInSeconds) {
		this.titleStayTimeInSeconds = titleStayTimeInSeconds;
	}
	
	@Override
	public int getFadeOutTimeInSeconds() {
		return titleFadeOutTimeInSeconds;
	}
	
	@Override
	public void setFadeOutTimeInSeconds(int titleFadeOutTimeInSeconds) {
		this.titleFadeOutTimeInSeconds = titleFadeOutTimeInSeconds;
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
