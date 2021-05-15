package achievements;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class ConditionTest {

	@Test
	public void activationRuleEquals() {
		Condition condition = new Condition("key", ActivationRule.EQUALS, 0);
		assertEquals(ActivationRule.EQUALS, condition.getActivationRule());
	}
	
	@Test
	public void activationRuleGreaterOrEqualsTo() {
		Condition condition = new Condition("key", ActivationRule.GREATER_OR_EQUALS_TO, 0);
		assertEquals(ActivationRule.GREATER_OR_EQUALS_TO, condition.getActivationRule());
	}
	
	@Test
	public void activationRuleGreaterThan() {
		Condition condition = new Condition("key", ActivationRule.GREATER_THAN, 0);
		assertEquals(ActivationRule.GREATER_THAN, condition.getActivationRule());
	}
	
	@Test
	public void activationRuleLessOrEqualsTo() {
		Condition condition = new Condition("key", ActivationRule.LESS_OR_EQUALS_TO, 0);
		assertEquals(ActivationRule.LESS_OR_EQUALS_TO, condition.getActivationRule());
	}
	
	@Test
	public void activationRuleLessThan() {
		Condition condition = new Condition("key", ActivationRule.LESS_THAN, 0);
		assertEquals(ActivationRule.LESS_THAN, condition.getActivationRule());
	}
	
	@Test
	public void getPropertyKey() {
		Condition condition = new Condition("my-key", ActivationRule.EQUALS, 0);
		assertEquals("my-key", condition.getPropertyKey());
	}
	
	@Test
	public void getActivationValue() {
		Condition condition = new Condition("a-key", ActivationRule.LESS_OR_EQUALS_TO, 211);
		assertEquals(211, condition.getActivationValue());
	}
	
	@Test
	public void isActiveEquals() {
		Condition condition = new Condition("key", ActivationRule.EQUALS, 200);
		assertTrue(condition.isActive(200));
	}
	
	@Test
	public void isNotActiveEquals() {
		Condition condition = new Condition("key", ActivationRule.EQUALS, 334);
		assertFalse(condition.isActive(12));
	}
	
	@Test
	public void isActiveGreaterOrEqualsWhenGreater() {
		Condition condition = new Condition("key", ActivationRule.GREATER_OR_EQUALS_TO, 10);
		assertTrue(condition.isActive(20));
	}
	
	@Test
	public void isNotActiveGreaterOrEqualsWhenLess() {
		Condition condition = new Condition("key", ActivationRule.GREATER_OR_EQUALS_TO, 10);
		assertFalse(condition.isActive(9));
	}
	
	@Test
	public void isActiveGreaterOrEqualsWhenEquals() {
		Condition condition = new Condition("key", ActivationRule.GREATER_OR_EQUALS_TO, 10);
		assertTrue(condition.isActive(10));
	}
	
	@Test
	public void isActiveGreaterWhenGreater() {
		Condition condition = new Condition("key", ActivationRule.GREATER_THAN, 213);
		assertTrue(condition.isActive(214));
	}
	
	@Test
	public void isNotActiveGreaterWhenEquals() {
		Condition condition = new Condition("key", ActivationRule.GREATER_THAN, 213);
		assertFalse(condition.isActive(213));
	}
	
	@Test
	public void isNotActiveGreaterWhenLess() {
		Condition condition = new Condition("key", ActivationRule.GREATER_THAN, 213);
		assertFalse(condition.isActive(212));
	}
	
	@Test
	public void isActiveLessOrEqualsToWhenEquals() {
		Condition condition = new Condition("key", ActivationRule.LESS_OR_EQUALS_TO, 567);
		assertTrue(condition.isActive(567));
	}
	
	@Test
	public void isActiveLessOrEqualsToWhenLess() {
		Condition condition = new Condition("key", ActivationRule.LESS_OR_EQUALS_TO, 567);
		assertTrue(condition.isActive(566));
	}
	
	@Test
	public void isNotActiveLessOrEqualsToWhenGreater() {
		Condition condition = new Condition("key", ActivationRule.LESS_OR_EQUALS_TO, 567);
		assertFalse(condition.isActive(568));
	}
	
	@Test
	public void isActiveLessThanWhenLess() {
		Condition condition = new Condition("key", ActivationRule.LESS_THAN, 567);
		assertTrue(condition.isActive(566));
	}
	
	@Test
	public void isNotActiveLessThanWhenEquals() {
		Condition condition = new Condition("key", ActivationRule.LESS_THAN, 567);
		assertFalse(condition.isActive(567));
	}
	
	@Test
	public void isNotActiveLessThanWhenGreater() {
		Condition condition = new Condition("key", ActivationRule.LESS_THAN, 567);
		assertFalse(condition.isActive(568));
	}
	
}
