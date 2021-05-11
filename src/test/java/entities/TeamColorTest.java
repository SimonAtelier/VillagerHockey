package entities;

import org.junit.Assert;
import org.junit.Test;

public class TeamColorTest {
	
	@Test
	public void lengthOfValuesReturnsSixteen() {
		Assert.assertEquals(16, TeamColor.values().length);
	}

	@Test
	public void testBlackValues() {
		TeamColor color = TeamColor.BLACK;
		Assert.assertEquals(0, color.getRed());
		Assert.assertEquals(0, color.getGreen());
		Assert.assertEquals(0, color.getBlue());
		Assert.assertEquals(0, color.getRGB());
	}
	
	@Test
	public void testDarkBlueValues() {
		TeamColor color = TeamColor.DARK_BLUE;
		Assert.assertEquals(0, color.getRed());
		Assert.assertEquals(0, color.getGreen());
		Assert.assertEquals(170, color.getBlue());
		Assert.assertEquals(170, color.getRGB());
	}
	
	@Test
	public void testDarkGreenValues() {
		TeamColor color = TeamColor.DARK_GREEN;
		Assert.assertEquals(0, color.getRed());
		Assert.assertEquals(170, color.getGreen());
		Assert.assertEquals(0, color.getBlue());
		Assert.assertEquals(43520, color.getRGB());
	}
	
	@Test
	public void testDarkAquaValues() {
		TeamColor color = TeamColor.DARK_AQUA;
		Assert.assertEquals(0, color.getRed());
		Assert.assertEquals(170, color.getGreen());
		Assert.assertEquals(170, color.getBlue());
		Assert.assertEquals(43690, color.getRGB());
	}
	
	@Test
	public void testDarkRedValues() {
		TeamColor color = TeamColor.DARK_RED;
		Assert.assertEquals(170, color.getRed());
		Assert.assertEquals(0, color.getGreen());
		Assert.assertEquals(0, color.getBlue());
		Assert.assertEquals(11141120, color.getRGB());
	}
	
	@Test
	public void testDarkPurpleValues() {
		TeamColor color = TeamColor.DARK_PURPLE;
		Assert.assertEquals(170, color.getRed());
		Assert.assertEquals(0, color.getGreen());
		Assert.assertEquals(170, color.getBlue());
		Assert.assertEquals(11141290, color.getRGB());
	}
	
	@Test
	public void testGoldValues() {
		TeamColor color = TeamColor.GOLD;
		Assert.assertEquals(255, color.getRed());
		Assert.assertEquals(170, color.getGreen());
		Assert.assertEquals(0, color.getBlue());
		Assert.assertEquals(16755200, color.getRGB());
	}
	
	@Test
	public void testGrayValues() {
		TeamColor color = TeamColor.GRAY;
		Assert.assertEquals(170, color.getRed());
		Assert.assertEquals(170, color.getGreen());
		Assert.assertEquals(170, color.getBlue());
		Assert.assertEquals(11184810, color.getRGB());
	}
	
	@Test
	public void testDarkGrayValues() {
		TeamColor color = TeamColor.DARK_GRAY;
		Assert.assertEquals(85, color.getRed());
		Assert.assertEquals(85, color.getGreen());
		Assert.assertEquals(85, color.getBlue());
		Assert.assertEquals(5592405, color.getRGB());
	}
	
	@Test
	public void testBlueValues() {
		TeamColor color = TeamColor.BLUE;
		Assert.assertEquals(85, color.getRed());
		Assert.assertEquals(85, color.getGreen());
		Assert.assertEquals(255, color.getBlue());
		Assert.assertEquals(5592575, color.getRGB());
	}
	
	@Test
	public void testGreenValues() {
		TeamColor color = TeamColor.GREEN;
		Assert.assertEquals(85, color.getRed());
		Assert.assertEquals(255, color.getGreen());
		Assert.assertEquals(85, color.getBlue());
		Assert.assertEquals(5635925, color.getRGB());
	}
	
	@Test
	public void testAquaValues() {
		TeamColor color = TeamColor.AQUA;
		Assert.assertEquals(85, color.getRed());
		Assert.assertEquals(255, color.getGreen());
		Assert.assertEquals(255, color.getBlue());
		Assert.assertEquals(5636095, color.getRGB());
	}
	
	@Test
	public void testRedValues() {
		TeamColor color = TeamColor.RED;
		Assert.assertEquals(255, color.getRed());
		Assert.assertEquals(85, color.getGreen());
		Assert.assertEquals(85, color.getBlue());
		Assert.assertEquals(16733525, color.getRGB());
	}
	
	@Test
	public void testLightPurpleValues() {
		TeamColor color = TeamColor.LIGHT_PURPLE;
		Assert.assertEquals(255, color.getRed());
		Assert.assertEquals(85, color.getGreen());
		Assert.assertEquals(255, color.getBlue());
		Assert.assertEquals(16733695, color.getRGB());
	}
	
	@Test
	public void testYellowValues() {
		TeamColor color = TeamColor.YELLOW;
		Assert.assertEquals(255, color.getRed());
		Assert.assertEquals(255, color.getGreen());
		Assert.assertEquals(85, color.getBlue());
		Assert.assertEquals(16777045, color.getRGB());
	}
	
	@Test
	public void testWhiteValues() {
		TeamColor color = TeamColor.WHITE;
		Assert.assertEquals(255, color.getRed());
		Assert.assertEquals(255, color.getGreen());
		Assert.assertEquals(255, color.getBlue());
		Assert.assertEquals(16777215, color.getRGB());
	}
	
	@Test
	public void testValueOfBlack() {
		TeamColor color = TeamColor.valueOf("BLACK");
		Assert.assertEquals(color, TeamColor.BLACK);
	}
	
	@Test
	public void testValueOfDarkBlue() {
		TeamColor color = TeamColor.valueOf("DARK_BLUE");
		Assert.assertEquals(color, TeamColor.DARK_BLUE);
	}
	
	@Test
	public void testValueOfDarkGreen() {
		TeamColor color = TeamColor.valueOf("DARK_GREEN");
		Assert.assertEquals(color, TeamColor.DARK_GREEN);
	}
	
	@Test
	public void testValueOfDarkAqua() {
		TeamColor color = TeamColor.valueOf("DARK_AQUA");
		Assert.assertEquals(color, TeamColor.DARK_AQUA);
	}
	
	@Test
	public void testValueOfDarkRed() {
		TeamColor color = TeamColor.valueOf("DARK_RED");
		Assert.assertEquals(color, TeamColor.DARK_RED);
	}
	
	@Test
	public void testValueOfDarkPurple() {
		TeamColor color = TeamColor.valueOf("DARK_PURPLE");
		Assert.assertEquals(color, TeamColor.DARK_PURPLE);
	}
	
	@Test
	public void testValueOfGold() {
		TeamColor color = TeamColor.valueOf("GOLD");
		Assert.assertEquals(color, TeamColor.GOLD);
	}
	
	@Test
	public void testValueOfGray() {
		TeamColor color = TeamColor.valueOf("GRAY");
		Assert.assertEquals(color, TeamColor.GRAY);
	}
	
	@Test
	public void testValueOfDarkGray() {
		TeamColor color = TeamColor.valueOf("DARK_GRAY");
		Assert.assertEquals(color, TeamColor.DARK_GRAY);
	}
	
	@Test
	public void testValueOfBlue() {
		TeamColor color = TeamColor.valueOf("BLUE");
		Assert.assertEquals(color, TeamColor.BLUE);
	}
	
	@Test
	public void testValueOfGreen() {
		TeamColor color = TeamColor.valueOf("GREEN");
		Assert.assertEquals(color, TeamColor.GREEN);
	}
	
	@Test
	public void testValueOfAqua() {
		TeamColor color = TeamColor.valueOf("AQUA");
		Assert.assertEquals(color, TeamColor.AQUA);
	}
	
	@Test
	public void testValueOfRed() {
		TeamColor color = TeamColor.valueOf("RED");
		Assert.assertEquals(color, TeamColor.RED);
	}
	
	@Test
	public void testValueOfLightPurple() {
		TeamColor color = TeamColor.valueOf("LIGHT_PURPLE");
		Assert.assertEquals(color, TeamColor.LIGHT_PURPLE);
	}
	
	@Test
	public void testValueOfYellow() {
		TeamColor color = TeamColor.valueOf("YELLOW");
		Assert.assertEquals(color, TeamColor.YELLOW);
	}
	
	@Test
	public void testValueOfWhite() {
		TeamColor color = TeamColor.valueOf("WHITE");
		Assert.assertEquals(color, TeamColor.WHITE);
	}
	
}
