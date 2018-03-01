package usecases.showachievements;

import java.util.List;
import java.util.UUID;

import achievement.AchievementGatewayImpl;
import entities.command.AbstractCommand;
import usecases.showachievements.ShowAchievements.ShowAchievementsRequest;
import usecases.showachievements.ShowAchievements.ShowAchievementsResponse;

public class ShowAchievementsCommand extends AbstractCommand {

	@Override
	public void execute(UUID player, List<String> arguments) {
		ShowAchievementsViewImpl view = new ShowAchievementsViewImpl(player);
		ShowAchievementsResponse presenter = new ShowAchievementsPresenter(view);
		ShowAchievements useCase = new ShowAchievementsUseCase();
		useCase.setAchievementGateway(new AchievementGatewayImpl());
		useCase.execute(createRequest(player), presenter);
	}
	
	private ShowAchievementsRequest createRequest(UUID player) {
		ShowAchievementsRequestModel requestModel = new ShowAchievementsRequestModel();
		requestModel.setPlayer(player);
		return requestModel;
	}

	@Override
	public String getName() {
		return "achievements";
	}

	@Override
	public String[] getArgumentLabels() {
		return new String[] {};
	}
	
}
