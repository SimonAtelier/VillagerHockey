package usecases.performaction.shootpuck;

import java.util.UUID;

public interface ShootPuck {

	void execute(ShootPuckRequest request, ShootPuckResponse response);
	
	public interface ShootPuckRequest {
		
		UUID getPlayer();
		
	}
	
	public interface ShootPuckResponse {
		
		void presentShootPuck();
		
	}
	
}
