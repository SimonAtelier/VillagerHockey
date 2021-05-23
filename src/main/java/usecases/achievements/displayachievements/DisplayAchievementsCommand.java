package usecases.achievements.displayachievements;

import java.util.List;
import java.util.UUID;

import entities.command.AbstractCommand;

public class DisplayAchievementsCommand extends AbstractCommand {

	public DisplayAchievementsCommand(String name) {
		super(name);
	}

	@Override
	public void execute(UUID player, List<String> arguments) {
		new DisplayAchievementsController().onDisplayAchievements(player);
	}
	
	@Override
	public String[] getArgumentLabels() {
		return new String[] {};
	}

}
