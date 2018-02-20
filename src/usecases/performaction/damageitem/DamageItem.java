package usecases.performaction.damageitem;

import java.util.UUID;

public interface DamageItem {

	boolean canDamageItem(UUID uniquePlayerId);
	
}
