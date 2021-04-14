package usecases.performaction.shootpuck;

import java.util.UUID;

public interface ShootPuck {

	void execute(ShootPuckRequest request, ShootPuckResponse response);
	
	public interface ShootPuckRequest {
		
		UUID getPlayer();
		String getPuckName();
		
	}
	
	public interface ShootPuckResponse {
		
		void presentShootPuck();
		
	}
	
}
