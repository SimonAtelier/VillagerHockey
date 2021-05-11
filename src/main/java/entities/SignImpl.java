package entities;

public class SignImpl implements Sign {

	private String firstLine;
	private String secondLine;
	private String thirdLine;
	private String fourthLine;
	private Location location;
		
	public SignImpl(Location location) {
		this.location = location;
	}
	
	@Override
	public String getFirstLine() {
		return firstLine;
	}

	@Override
	public void setFirstLine(String firstLine) {
		this.firstLine = firstLine;
	}

	@Override
	public String getSecondLine() {
		return secondLine;
	}

	@Override
	public void setSecondLine(String secondLine) {
		this.secondLine = secondLine;
	}

	@Override
	public String getThirdLine() {
		return thirdLine;
	}

	@Override
	public void setThirdLine(String thirdLine) {
		this.thirdLine = thirdLine;
	}

	@Override
	public String getFourthLine() {
		return fourthLine;
	}

	@Override
	public void setFourthLine(String fourthLine) {
		this.fourthLine = fourthLine;
	}

	@Override
	public Location getLocation() {
		return location;
	}

	@Override
	public void setLocation(Location location) {
		this.location = location;
	}
	
}
