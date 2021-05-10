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
The following example shows an achievements list with one custom achievement.
#### Example:
```json
{
   "Achievements List":[
      {
         "Id": "ez",
         "Name": "EZ!",
         "Description": "Easiest achievement ever! Just join.",
         "Points": 5,
	 "Progress Keys": [],
         "Achieve Conditions": [
            {
               "Key": "games_played",
               "Rule": ">=",
               "Value": 0
            }
         ]
      }
   ]
}
```
### Achievement Values
- **Id** Unique Identifier for the achievement. Internally used to adress the achievement. 
- **Name** Displayable name of the achievement.
- **Description** Displayable description of the achievement.
- **Points** The Amount of points given to the player by unlocking the achievement.
- **Progress Keys** Determines the type of the achievement.
	- Whenever more steps are needed to fulfill the condition, winning a certain amount of games for example. Keys define the displayed goal amount.
- **Achive Conditions** One or more conditions which must be fulfilled to unlock the specific achievement.

### Achieve Condition Values
- **Key** The referenced statistic key.
- **Rule** A rule is not more than just a comparison operator.
	- Possible values are:
		- **"=="**
		- **">="**
		- **"<="**
		- **">"**
		- **"<"**
- **Value** The value to be campared by the rule. (The example above means: if (games_played >= 0) then unlock)
		
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
- achievement_points_earned

#### games_won
References the total amount of won games per player.

#### games_lost
References the total amount of lost games per player.

#### games_draw
References the total amount of games played in a draw per player.

#### games_played
References the total amount of played games per player.

#### games_won_to_zero
References the total amount of games won to zero per player.

#### goals_scored_self
References the total amount of goals scored by a player.

#### puck_hits_current
The number of times a player hittet the puck in his current game.

#### puck_hits_total
References times a player hittet the puck in all played games.

#### unlocked_achievements
Amount of unlocked achievements per player.

#### pinatas_smashed
The number of times a player smashed the 'Pig Pinata'.

#### bad_trades
The number of times a player tried to trade with the puck (Villager).

#### winning_streak
TODO

#### winning_streak_last
TODO

#### winning_streak_longest
TODO

#### achievement_points_earned
The total amount of earned achievement points per player. (Including already spent ones).

### Example
Now we went on with the fun stuff! Let's asume we want unlock an achievement if a player wins his first five games.
In this case we need to ensure that the player won five games and didn't play more than five games in total.
We use the keys **games_won** and **games_played** for the achive conditions. In addition we want to show the player his progress by using
the **games_won** key as 'Progress Key'.
```json
{
  "Id": "win_first_five",
  "Name": "High five!",
  "Description": "Win the first 5 games.",
  "Points": 10,
  "Progress Keys": ["games_won"],
  "Achieve Conditions": [
    {
      "Key": "games_played",
      "Rule": "==",
      "Value": 5
    },
    {
      "Key": "games_won",
      "Rule": "==",
      "Value": 5
    }
  ]
}
```



