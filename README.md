[wiki-link]: https://github.com/SimonAtelier/VillagerHockey/wiki

# Villager-Hockey - Spigot Minigame Plugin
[![wiki](https://img.shields.io/badge/go%20to-wiki-blue.svg)][wiki-link]
[![Codacy Badge](https://api.codacy.com/project/badge/Grade/b6ceaca0c2b141cfbe8b0ab792ecaaf2)](https://app.codacy.com/gh/SimonAtelier/VillagerHockey?utm_source=github.com&utm_medium=referral&utm_content=SimonAtelier/VillagerHockey&utm_campaign=Badge_Grade_Settings)

<img src="https://i.imgur.com/8f4UNds.png">

Villager Hockey is a spigot minigame plugin.
This minigame is mainly developed for the community server of 'Redaktion Holtex'.

**TODO:** Description

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

# Arena Structure
- Villager Spawner
- Goals
- Team Spawns

# Predefined Custom Achievements (Work In Progress)
This table shows the currently predefined achievements. This collection is work in progress and might change heavily.
It's used to keep track of the current WIP.

|id|name|description|points|notes|
|--|--|--|--|--|
|ez|EZ!|Join the game for the first time.|5||
|first_contact|First Contact|Hit the puck for the first time.|5||
|heavy_hitter|Heavy Hitter|Hit the puck 100 times in a single game.|5||
|winner_winner|Winner Winner Chicken Dinner!|Win your first game.|5||
|wooden_spoon|Wooden Spoon|Lose a total of 50 games.|10||
|no_chance|They had no chance at all!|Win a game to zero.|5||
|strong_hold_tier_one|Strong Hold I|Win 20 games to zero.|5||
|strong_hold_tier_two|Strong Hold II|Win 40 games to zero.|5||
|strong_hold_tier_three|Strong Hold III|Win 80 games to zero.|5||
|achievement_goal_one|Achievement Goal I|Unlock 5 achievements.|5||
|achievement_goal_two|Achievement Goal II|Unlock 10 achievements.|5||
|achievement_goal_three|Achievement Goal III|Unlock 20 achievements.|5||
|legend|Legend|Play a total of 10000 games.|50||
|expert|Expert|Play a total of 500 games.|15||
|beginner|Beginner|Play a total of 100 games.|10||
|poor_pig|Poor Pig|Smash the 'Pig Pinata' during a special round.|5||
|pinata_king|Pinata King|Smash the 'Pig Pinata' 10 times in total.|10||
|bad_trade|Bad Trade|Try to trade.|5||
|anniversary|Anniversary|Win a total of 25 games.|5||
|winning_streak_one|Winning Streak I|Win a total of 5 games in a row.|5||
|win_first_five|High five!|Win the first 5 games.|10||
|money_money_money|Money, Money, Money|Earn 500 Achievement Points.|10||

# Custom Achievements
Custom achievements are specified in a file named achievements.json.
The following example shows an achievements list with one custom achievement.
#### Example:
```json
{
   "achievements_list":[
      {
         "id": "ez",
         "name": "EZ!",
         "description": "Easiest achievement ever! Just join.",
         "points": 5,
	 "progress_keys": [],
         "achieve_conditions": [
            {
               "key": "games_played",
               "rule": ">=",
               "value": 0
            }
         ]
      }
   ]
}
```
### Achievement Values
- **id** Unique Identifier for the achievement. Internally used to adress the achievement. 
- **name** Displayable name of the achievement.
- **description** Displayable description of the achievement.
- **points** The Amount of points given to the player by unlocking the achievement.
- **progress_keys** Determines the type of the achievement.
	- Whenever more steps are needed to fulfill the condition, winning a certain amount of games for example. Keys define the displayed goal amount.
- **achive_conditions** One or more conditions which must be fulfilled to unlock the specific achievement.

### Achieve Condition Values
- **key** The referenced statistic key.
- **rule** A rule is not more than just a comparison operator.
	- Possible values are:
		- **"=="**
		- **">="**
		- **"<="**
		- **">"**
		- **"<"**
- **value** The value to be campared by the rule. (The example above means: if (games_played >= 0) then unlock)
		
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
The number of times a player has hit the puck in his current game.

#### puck_hits_total
References times a player hittet the puck in all played games.

#### unlocked_achievements
Amount of unlocked achievements per player.

#### pinatas_smashed
The number of times a player smashed the 'Pig Pinata'.

#### bad_trades
The number of times a player tried to trade with the puck (Villager).

#### winning_streak
The current nunber of games won in a row per player. Resets to zero when the player ends this streak by loosing a game.

#### winning_streak_last
The length of the last winning streak in won games per player (after ending a streak).

#### winning_streak_longest
The longest personal winning streak achieved by a player.

#### achievement_points_earned
The total amount of earned achievement points per player. (Including already spent ones).

### Example
Now we went on with the fun stuff! Let's asume we want unlock an achievement if a player wins his first five games.
In this case we need to ensure that the player won five games and didn't play more than five games in total.
We use the keys **games_won** and **games_played** for the achive conditions. In addition we want to show the player his progress by using
the **games_won** key as 'Progress Key'.
Overall this is a bad example, cause limited time achievements are generally bad. Players might have a negative gameplay experience. But it is possible to describe such types of achievements.
```json
{
  "id": "win_first_five",
  "name": "High five!",
  "description": "Win the first 5 games.",
  "points": 10,
  "progress_keys": ["games_won"],
  "achieve_conditions": [
    {
      "key": "games_played",
      "rule": "==",
      "value": 5
    },
    {
      "key": "games_won",
      "rule": "==",
      "value": 5
    }
  ]
}
```

 
