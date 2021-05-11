package main;

import entities.command.CommandProvider;
import entities.command.CommandRegistration;
import usecases.addteamspawn.AddTeamSpawnCommand;
import usecases.api.addgame.AddGameCommand;
import usecases.api.addteam.AddTeamCommand;
import usecases.api.joingame.JoinGameCommand;
import usecases.api.setminplayers.SetMinPlayersCommand;
import usecases.displayachievements.DisplayAchievementsCommand;
import usecases.kickplayer.KickPlayerCommand;
import usecases.leavegame.LeaveGameCommand;
import usecases.listgames.ListGamesCommand;
import usecases.savegame.SaveGameCommand;
import usecases.setgoal.SetGoalCommand;
import usecases.setlobby.SetLobbyCommand;
import usecases.setplayingtime.SetPlayingTimeCommand;
import usecases.setvillagerspawn.SetVillagerSpawnCommand;
import usecases.showhelp.ShowHelpCommand;
import usecases.showstatistics.ShowStatisticsCommand;
import usecases.startgame.StartGameCommand;
import usecases.stopgame.StopGameCommand;

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
