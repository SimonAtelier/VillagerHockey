package test.entities;

import org.junit.Assert;
import org.junit.Test;

import entities.Location;

public class LocationTest {

	@Test
	public void setXgetXReturnsValue() {
		double value = 10;
		Location location = new Location();
		location.setX(value);
		Assert.assertEquals(value, location.getX(), 0);
	}
	
	@Test
	public void setYgetYReturnsValue() {
		double value = 20;
		Location location = new Location();
		location.setY(value);
		Assert.assertEquals(value, location.getY(), 0);
	}
	
	@Test
	public void setZgetZReturnsValue() {
		double value = 30;
		Location location = new Location();
		location.setZ(value);
		Assert.assertEquals(value, location.getZ(), 0);
	}
	
	@Test
	public void setPitchGetPitchReturnsValue() {
		double value = 40;
		Location location = new Location();
		location.setPitch(value);
		Assert.assertEquals(value, location.getPitch(), 0);
	}
	
	@Test
	public void setYawGetYawReturnsValue() {
		double value = 50;
		Location location = new Location();
		location.setYaw(value);
		Assert.assertEquals(value, location.getYaw(), 0);
	}
	
	@Test
	public void setWorldgetWorldReturnsValue() {
		String value = "World";
		Location location = new Location();
		location.setWorld(value);
		Assert.assertEquals(value, location.getWorld());
	}
	
}
