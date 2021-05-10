# Villager-Hockey - Plugin

[![Codacy Badge](https://api.codacy.com/project/badge/Grade/b6ceaca0c2b141cfbe8b0ab792ecaaf2)](https://app.codacy.com/gh/SimonAtelier/VillagerHockey?utm_source=github.com&utm_medium=referral&utm_content=SimonAtelier/VillagerHockey&utm_campaign=Badge_Grade_Settings)

Villager Hockey is a minigame.

# TODO: Description

## Features
- Team Selection GUI
- Custom Amount of Players per Team
- Team Colors
	* 16 different colors
- Colored Team Armor
- Team Chat and Game Chat

- Achievement System
- Custom Achievements
- Special Rounds
	* Polo
	* Pig Pinata
	* Boat Boogie
	
# Custom Achievements
Custom achievements are specified in a file named achievements.json.

#### Example:
```json
{
	"Achievements List" : [
		{
			"Id" : "ez",
			"Name" : "EZ!",
			"Description" : "Join the game for the first time.",
			"Points" : 5,
			"Progress" : false,
			"Achieve Conditions" : [
				{
					"Key" : "games_played",
					"Rule" : ">=",
					"Value" : 0
				}
			]
		},
```

### Achievements Condition Keys
An achievement is unlocked by one or more 'Achieve Conditions'. An Achieve Condition references to a specific game statistic value defined by a key.

### List of Keys
- games_won
- games_lost
- games_draw
- games_played
- games_won_to_zero
- goals_scored_self
- puck_hits_current
- puck_hits_total
- unlocked_achievements
- pinatas_smashed
- bad_trades
- winning_streak
- winning_streak_last
- winning_streak_longest
