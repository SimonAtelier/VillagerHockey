package entities;

import static org.junit.Assert.assertNotNull;

import org.bukkit.Color;
import org.junit.Test;

public class TamColorToBukkitColorTest {
	
	@Test
	public void teamColorsToBukkitColors() {
		for (TeamColor teamColor : TeamColor.values()) {
			Color color = Color.fromBGR(teamColor.getRGB());
			assertNotNull(color);
		}
	}
	
}
