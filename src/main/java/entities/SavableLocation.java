package entities;

import java.io.IOException;

import io.github.simonatelier.save.Exporter;
import io.github.simonatelier.save.Importer;
import io.github.simonatelier.save.Input;
import io.github.simonatelier.save.Output;
import io.github.simonatelier.save.Savable;

public class SavableLocation implements Savable {

	private Location location;
	
	public SavableLocation(Location location) {
		this.location = location;
	}

	@Override
	public void write(Exporter exporter) throws IOException {
		Output output = exporter.getOutput(location);
		output.write(location.getX(), "x");
		output.write(location.getY(), "y");
		output.write(location.getZ(), "z");
		output.write(location.getPitch(), "pitch");
		output.write(location.getYaw(), "yaw");
		output.write(location.getWorld(), "world");
	}

	@Override
	public void read(Importer importer) throws IOException {
		Input input = importer.getInput(location);
		location.setX(input.readDouble("x"));
		location.setY(input.readDouble("y"));
		location.setZ(input.readDouble("z"));
		location.setPitch(input.readDouble("pitch"));
		location.setYaw(input.readDouble("yaw"));
		location.setWorld(input.readString("world"));
	}

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}
	
}
