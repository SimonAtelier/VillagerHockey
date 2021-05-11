package entities;

public enum TeamColor {

	BLACK(0, 0, 0),
	DARK_BLUE(0, 0, 170),
	DARK_GREEN(0, 170, 0),
	DARK_AQUA(0, 170, 170),
	DARK_RED(170, 0, 0),
	DARK_PURPLE(170, 0, 170),
	GOLD(255, 170, 0),
	GRAY(170, 170, 170),
	DARK_GRAY(85, 85, 85),
	BLUE(85, 85, 255),
	GREEN(85, 255, 85),
	AQUA(85, 255, 255),
	RED(255, 85, 85),
	LIGHT_PURPLE(255, 85, 255),
	YELLOW(255, 255, 85),
	WHITE(255, 255, 255);
	
	private int red;
	private int green;
	private int blue;
	
	private TeamColor(int red, int green, int blue) {
		this.red = red;
		this.green = green;
		this.blue = blue;
	}

	public int getRed() {
		return red;
	}

	public int getGreen() {
		return green;
	}

	public int getBlue() {
		return blue;
	}
	
	public int getRGB() {
		return ((0 & 0xFF) << 24) | ((red & 0xFF) << 16) | ((green & 0xFF) << 8) | ((blue & 0xFF) << 0);
	}
	
}
