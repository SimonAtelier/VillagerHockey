package usecases.encaps.showstatistics;

import java.util.List;
import java.util.UUID;

import context.Context;
import entities.command.AbstractCommand;
import usecases.encaps.showstatistics.ShowStatistics.ShowStatisticsRequest;
import usecases.encaps.showstatistics.ShowStatistics.ShowStatisticsResponse;

public class ShowStatisticsCommand extends AbstractCommand {

	@Override
	public void execute(UUID player, List<String> arguments) {
		ShowStatisticsView view = new ShowStatisticsViewImpl(player);
		ShowStatisticsResponse presenter = new ShowStatisticsPresenter(view);
		ShowStatistics useCase = new ShowStatisticsUseCase();
		useCase.setPermissionGateway(Context.permissionGateway);
		useCase.setPlayerGateway(Context.playerGateway);
		useCase.setStatisticsGateway(Context.statisticsGateway);
		useCase.execute(createRequest(player), presenter);
	}
	
	private ShowStatisticsRequest createRequest(UUID player) {
		ShowStatisticsRequestModel requestModel = new ShowStatisticsRequestModel();
		requestModel.setPlayer(player);
		return requestModel;
	}

	@Override
	public String getName() {
		return "stats";
	}

	@Override
	public String[] getArgumentLabels() {
		return new String[] {};
	}

}
