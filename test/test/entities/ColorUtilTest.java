package test.entities;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Test;

import entities.ColorUtil;
import entities.TeamColor;
import view.message.MessageCodes;

public class ColorUtilTest {

	@Test
	public void blackToMessageCode() {
		assertEquals(MessageCodes.BLACK, ColorUtil.toMessageCode(TeamColor.BLACK));
	}
	
	@Test
	public void darkBlueToMessageCode() {
		assertEquals(MessageCodes.DARK_BLUE, ColorUtil.toMessageCode(TeamColor.DARK_BLUE));
	}
	
	@Test
	public void darkGreenToMessageCode() {
		assertEquals(MessageCodes.DARK_GREEN, ColorUtil.toMessageCode(TeamColor.DARK_GREEN));
	}
	
	@Test
	public void darkAquaToMessageCode() {
		assertEquals(MessageCodes.DARK_AQUA, ColorUtil.toMessageCode(TeamColor.DARK_AQUA));
	}
	
	@Test
	public void darkRedToMessageCode() {
		assertEquals(MessageCodes.DARK_RED, ColorUtil.toMessageCode(TeamColor.DARK_RED));
	}
	
	@Test
	public void darkPurpleToMessageCode() {
		assertEquals(MessageCodes.DARK_PURPLE, ColorUtil.toMessageCode(TeamColor.DARK_PURPLE));
	}
	
	@Test
	public void goldToMessageCode() {
		assertEquals(MessageCodes.GOLD, ColorUtil.toMessageCode(TeamColor.GOLD));
	}
	
	@Test
	public void grayToMessageCode() {
		assertEquals(MessageCodes.GRAY, ColorUtil.toMessageCode(TeamColor.GRAY));
	}
	
	@Test
	public void darkGrayToMessageCode() {
		assertEquals(MessageCodes.DARK_GRAY, ColorUtil.toMessageCode(TeamColor.DARK_GRAY));
	}
	
	@Test
	public void blueToMessageCode() {
		assertEquals(MessageCodes.BLUE, ColorUtil.toMessageCode(TeamColor.BLUE));
	}
	
	@Test
	public void greenToMessageCode() {
		assertEquals(MessageCodes.GREEN, ColorUtil.toMessageCode(TeamColor.GREEN));
	}
	
	@Test
	public void aquaToMessageCode() {
		assertEquals(MessageCodes.AQUA, ColorUtil.toMessageCode(TeamColor.AQUA));
	}
	
	@Test
	public void redToMessageCode() {
		assertEquals(MessageCodes.RED, ColorUtil.toMessageCode(TeamColor.RED));
	}
	
	@Test
	public void lightPurpleToMessageCode() {
		assertEquals(MessageCodes.LIGHT_PURPLE, ColorUtil.toMessageCode(TeamColor.LIGHT_PURPLE));
	}
	
	@Test
	public void yellowPurpleToMessageCode() {
		assertEquals(MessageCodes.YELLOW, ColorUtil.toMessageCode(TeamColor.YELLOW));
	}

	@Test
	public void whitePurpleToMessageCode() {
		assertEquals(MessageCodes.WHITE, ColorUtil.toMessageCode(TeamColor.WHITE));
	}
	
	@Test
	public void all() {
		for (TeamColor teamColor : TeamColor.values()) {
			assertNotNull(ColorUtil.toMessageCode(teamColor));
		}
	}
	
}
