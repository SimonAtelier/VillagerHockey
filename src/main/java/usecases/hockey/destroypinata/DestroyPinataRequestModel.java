package usecases.hockey.destroypinata;

import java.util.UUID;

import usecases.hockey.destroypinata.DestroyPinata.DestroyPinataRequest;

public class DestroyPinataRequestModel implements DestroyPinataRequest {

	private UUID destroyer;

	public UUID getDestroyer() {
		return destroyer;
	}

	public void setDestroyer(UUID destroyer) {
		this.destroyer = destroyer;
	}
	
}
