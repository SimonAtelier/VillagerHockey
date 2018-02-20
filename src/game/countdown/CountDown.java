package game.countdown;

public interface CountDown {

	void tick();
	
	void start();
	
	void stop();
	
	void pause();
	
	void resume();

	void setTimeToCountDownInSeconds(int countDownTimeInSeconds);
	
	void setCountDownListener(CountDownListener listener);
	
	boolean isFinished();
	
}
