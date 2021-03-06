package plugin;

import entities.command.CommandProvider;
import entities.command.CommandRegistration;
import usecases.achievements.displayachievements.DisplayAchievementsCommand;
import usecases.api.addgame.AddGameCommand;
import usecases.api.addteam.AddTeamCommand;
import usecases.api.joingame.JoinGameCommand;
import usecases.api.kickplayer.KickPlayerCommand;
import usecases.api.leavegame.LeaveGameCommand;
import usecases.api.listgames.ListGamesCommand;
import usecases.api.setlobby.SetLobbyCommand;
import usecases.api.setminplayers.SetMinPlayersCommand;
import usecases.api.setplayingtime.SetPlayingTimeCommand;
import usecases.api.showhelp.ShowHelpCommand;
import usecases.api.startgame.StartGameCommand;
import usecases.api.stopgame.StopGameCommand;
import usecases.encaps.addteamspawn.AddTeamSpawnCommand;
import usecases.encaps.showstatistics.ShowStatisticsCommand;
import usecases.hockey.savegame.SaveGameCommand;
import usecases.hockey.setgoal.SetGoalCommand;
import usecases.hockey.setvillagerspawn.SetVillagerSpawnCommand;

public class CommandProviderImpl implements CommandProvider {

	@Override
	public void registerCommands(CommandRegistration commandRegistration) {
		commandRegistration.registerCommand(new AddGameCommand());
		commandRegistration.registerCommand(new AddTeamSpawnCommand());
		commandRegistration.registerCommand(new JoinGameCommand());
		commandRegistration.registerCommand(new KickPlayerCommand());
		commandRegistration.registerCommand(new LeaveGameCommand());
		commandRegistration.registerCommand(new ListGamesCommand());
		commandRegistration.registerCommand(new SaveGameCommand());
		commandRegistration.registerCommand(new SetGoalCommand());
		commandRegistration.registerCommand(new SetLobbyCommand());
		commandRegistration.registerCommand(new SetVillagerSpawnCommand());
		commandRegistration.registerCommand(new ShowHelpCommand());
		commandRegistration.registerCommand(new SetMinPlayersCommand());
		commandRegistration.registerCommand(new SetPlayingTimeCommand());
		commandRegistration.registerCommand(new AddTeamCommand());
		commandRegistration.registerCommand(new StartGameCommand());
		commandRegistration.registerCommand(new StopGameCommand());
		commandRegistration.registerCommand(new ShowStatisticsCommand());
		commandRegistration.registerCommand(new DisplayAchievementsCommand());
	}

}
