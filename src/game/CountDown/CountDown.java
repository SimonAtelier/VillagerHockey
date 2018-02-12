package game.CountDown;

public interface CountDown {

	void start();
	
	void stop();
	
	void pause();
	
	void resume();

	void setTimeToCountDownInSeconds(int countDownTimeInSeconds);
	
	void setCountDownListener(CountDownListener listener);
	
}
