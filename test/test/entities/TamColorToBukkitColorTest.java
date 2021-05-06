package test.entities;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.bukkit.Color;
import org.junit.Test;

import entities.TeamColor;

public class TamColorToBukkitColorTest {
	
	@Test
	public void teamColorsToBukkitColors() {
		for (TeamColor teamColor : TeamColor.values()) {
			Color color = Color.fromBGR(teamColor.getRGB());
			assertNotNull(color);
		}
	}
	
}
