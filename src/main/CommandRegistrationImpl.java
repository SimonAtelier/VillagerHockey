package main;

import gateways.CommandGateway;
import usecases.AddGame.AddGameCommand;
import usecases.AddTeam.AddTeamCommand;
import usecases.AddTeamSpawn.AddTeamSpawnCommand;
import usecases.JoinGame.JoinGameCommand;
import usecases.KickPlayer.KickPlayerCommand;
import usecases.LeaveGame.LeaveGameCommand;
import usecases.ListGames.ListGamesCommand;
import usecases.RemoveGame.RemoveGameCommand;
import usecases.SaveGame.SaveGameCommand;
import usecases.SetGoal.SetGoalCommand;
import usecases.SetLobby.SetLobbyCommand;
import usecases.SetMinPlayers.SetMinPlayersCommand;
import usecases.SetPlayingTime.SetPlayingTimeCommand;
import usecases.SetVillagerSpawn.SetVillagerSpawnCommand;
import usecases.ShowHelp.ShowHelpCommand;
import usecases.StartGame.StartGameCommand;
import usecases.StopGame.StopGameCommand;

public class CommandRegistrationImpl implements CommandRegistration {

	@Override
	public void registerCommands(CommandGateway commandGateway) {
		commandGateway.registerCommand(new AddGameCommand());
		commandGateway.registerCommand(new AddTeamSpawnCommand());
		commandGateway.registerCommand(new JoinGameCommand());
		commandGateway.registerCommand(new KickPlayerCommand());
		commandGateway.registerCommand(new LeaveGameCommand());
		commandGateway.registerCommand(new ListGamesCommand());
		commandGateway.registerCommand(new SaveGameCommand());
		commandGateway.registerCommand(new SetGoalCommand());
		commandGateway.registerCommand(new SetLobbyCommand());
		commandGateway.registerCommand(new SetVillagerSpawnCommand());
		commandGateway.registerCommand(new ShowHelpCommand());
		commandGateway.registerCommand(new SetMinPlayersCommand());
		commandGateway.registerCommand(new SetPlayingTimeCommand());
		commandGateway.registerCommand(new AddTeamCommand());
		commandGateway.registerCommand(new RemoveGameCommand());
		commandGateway.registerCommand(new StartGameCommand());
		commandGateway.registerCommand(new StopGameCommand());
	}

}
