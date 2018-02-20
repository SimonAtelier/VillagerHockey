package usecases.performaction.shootpuck;

public class ShootPuckUseCase implements ShootPuck {
	
	@Override
	public void execute(ShootPuckRequest request, ShootPuckResponse response) {
		response.presentShootPuck();
	}

}
