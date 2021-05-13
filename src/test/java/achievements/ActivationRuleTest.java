package achievements;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import achievements.ActivationRule.UnknownActivationRuleException;

public class ActivationRuleTest {

	@Test
	public void equalsActive() {
		ActivationRule activationRule = ActivationRule.EQUALS;
		assertTrue(activationRule.isActive(10, 10));
	}
	
	@Test
	public void equalsNotActive() {
		ActivationRule activationRule = ActivationRule.EQUALS;
		assertFalse(activationRule.isActive(10, 20));
	}
	
	@Test
	public void greaterOrEqualsActiveWhenGreater() {
		ActivationRule activationRule = ActivationRule.GREATER_OR_EQUALS_TO;
		assertTrue(activationRule.isActive(20, 10));
	}
	
	@Test
	public void greaterOrEqualsNotActiveWhenNotGreater() {
		ActivationRule activationRule = ActivationRule.GREATER_OR_EQUALS_TO;
		assertFalse(activationRule.isActive(10, 20));
	}
	
	@Test
	public void greaterOrEqualsActiveWhenEquals() {
		ActivationRule activationRule = ActivationRule.GREATER_OR_EQUALS_TO;
		assertTrue(activationRule.isActive(20, 20));
	}
	
	@Test
	public void greaterNotActiveWhenEquals() {
		ActivationRule activationRule = ActivationRule.GREATER_THAN;
		assertFalse(activationRule.isActive(300, 300));
	}
	
	@Test
	public void greaterActiveWhenGreater() {
		ActivationRule activationRule = ActivationRule.GREATER_THAN;
		assertTrue(activationRule.isActive(300, 3));
	}
	
	@Test
	public void greaterNotActiveWhenLess() {
		ActivationRule activationRule = ActivationRule.GREATER_THAN;
		assertFalse(activationRule.isActive(100, 300));
	}
	
	@Test
	public void lessOrEqualsActiveWhenEquals() {
		ActivationRule activationRule = ActivationRule.LESS_OR_EQUALS_TO;
		assertTrue(activationRule.isActive(156, 156));
	}
	
	@Test
	public void lessOrEqualsActiveWhenLess() {
		ActivationRule activationRule = ActivationRule.LESS_OR_EQUALS_TO;
		assertTrue(activationRule.isActive(100, 156));
	}
	
	@Test
	public void lessOrEqualsNotActiveWhenGreater() {
		ActivationRule activationRule = ActivationRule.LESS_OR_EQUALS_TO;
		assertFalse(activationRule.isActive(200, 156));
	}
	
	@Test
	public void lessThanNotActiveWhenEquals() {
		ActivationRule activationRule = ActivationRule.LESS_THAN;
		assertFalse(activationRule.isActive(123, 123));
	}
	
	@Test
	public void lessThanNotActiveWhenGreater() {
		ActivationRule activationRule = ActivationRule.LESS_THAN;
		assertFalse(activationRule.isActive(200, 123));
	}
	
	@Test
	public void lessThanActiveWhenLess() {
		ActivationRule activationRule = ActivationRule.LESS_THAN;
		assertTrue(activationRule.isActive(77, 78));
	}
	
	@Test
	public void getEqualsByString() {
		ActivationRule activationRule = ActivationRule.getByString("==");
		assertEquals(ActivationRule.EQUALS, activationRule);
	}
	
	@Test
	public void getGreaterThanByString() {
		ActivationRule activationRule = ActivationRule.getByString(">");
		assertEquals(ActivationRule.GREATER_THAN, activationRule);
	}
	
	@Test
	public void getLessThanByString() {
		ActivationRule activationRule = ActivationRule.getByString("<");
		assertEquals(ActivationRule.LESS_THAN, activationRule);
	}
	
	@Test
	public void getLessOrEqualsByString() {
		ActivationRule activationRule = ActivationRule.getByString("<=");
		assertEquals(ActivationRule.LESS_OR_EQUALS_TO, activationRule);
	}
	
	@Test
	public void getGreaterOrEqualsTo() {
		ActivationRule activationRule = ActivationRule.getByString(">=");
		assertEquals(ActivationRule.GREATER_OR_EQUALS_TO, activationRule);
	}
	
	@Test (expected = UnknownActivationRuleException.class)
	public void nullThrowsException() {
		ActivationRule.getByString(null);
	}
	
	@Test (expected = UnknownActivationRuleException.class)
	public void emptyStringThrowsException() {
		ActivationRule.getByString("");
	}
	
	@Test (expected = UnknownActivationRuleException.class)
	public void blankThrowsException() {
		ActivationRule.getByString(" ");
	}
	
}
