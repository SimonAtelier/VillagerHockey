package main;

import entities.command.CommandProvider;
import entities.command.CommandRegistration;
import usecases.addgame.AddGameCommand;
import usecases.addteam.AddTeamCommand;
import usecases.addteamspawn.AddTeamSpawnCommand;
import usecases.joingame.JoinGameCommand;
import usecases.kickplayer.KickPlayerCommand;
import usecases.leavegame.LeaveGameCommand;
import usecases.listgames.ListGamesCommand;
import usecases.removegame.RemoveGameCommand;
import usecases.savegame.SaveGameCommand;
import usecases.setgoal.SetGoalCommand;
import usecases.setlobby.SetLobbyCommand;
import usecases.setminplayers.SetMinPlayersCommand;
import usecases.setplayingtime.SetPlayingTimeCommand;
import usecases.setvillagerspawn.SetVillagerSpawnCommand;
import usecases.showhelp.ShowHelpCommand;
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
		commandRegistration.registerCommand(new RemoveGameCommand());
		commandRegistration.registerCommand(new StartGameCommand());
		commandRegistration.registerCommand(new StopGameCommand());
	}

}
