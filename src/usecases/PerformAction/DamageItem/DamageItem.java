package usecases.PerformAction.DamageItem;

import java.util.UUID;

public interface DamageItem {

	boolean canDamageItem(UUID uniquePlayerId);
	
}
