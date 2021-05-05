package view.title;

public interface TitleViewModel {

	String getTitle();
	
	void setTitle(String title);
	
	String getSubtitle();
	
	void setSubtitle(String subtitle);
	
	int getTitleFadeInTimeInSeconds();
	
	void setTitleFadeInTimeInSeconds(int titleFadeInTimeInSeconds);
	
	int getTitleStayTimeInSeconds();
	
	void setTitleStayTimeInSeconds(int titleStayTimeInSeconds);
	
	int getTitleFadeOutTimeInSeconds();
	
	void setTitleFadeOutTimeInSeconds(int titleFadeOutTimeInSeconds);
	
}
