package game.hockey;

import java.io.IOException;

import entities.Location;
import entities.SavableLocation;
import io.github.simonatelier.save.Exporter;
import io.github.simonatelier.save.Importer;
import io.github.simonatelier.save.Input;
import io.github.simonatelier.save.Output;
import io.github.simonatelier.save.Savable;

public class SavableGoal implements Savable {
	
	private Goal goal;

	public SavableGoal(Goal goal) {
		this.goal = goal;
	}

	@Override
	public void write(Exporter exporter) throws IOException {
		Output output = exporter.getOutput(goal);
		Location locationOne = goal.getLocationOne();
		Location locationTwo = goal.getLocationTwo();
		output.write(goal.getTeam(), "team");
		new SavableLocation(locationOne).write(exporter);
	}

	@Override
	public void read(Importer importer) throws IOException {
		Input input = importer.getInput(goal);
		input.readString("team");
	}

	public Goal getGoal() {
		return goal;
	}

	public void setGoal(Goal goal) {
		this.goal = goal;
	}

}