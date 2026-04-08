# Boosts / Bonuses

Boosts or bonuses are something special too. They also follow a special semantic and are partially hardcoded and partially generated from game assets (e.g. room boosters).
Key syntax: `{BOOSTER_KEY}>{ADD|MUL}` e.g. `ROOM_ADMIN_NORMAL>MUL`, which would be a bonus for the admin room.
Booster keys can also count for multiple bonuses by using a wildcard. E.g. `ROOM_FARM*` would count for all rooms starting with `ROOM_FARM`.
You can find a list with all available vanilla boosters [here](../booster/boosters_all.md).

Example boost section:
```
BOOST: {
	PHYSICS_RESISTANCE_COLD>ADD: -0.15,
	PHYSICS_RESISTANCE_HOT>ADD: -0.15,
	PHYSICS_DEATH_AGE>MUL: 0.8,
	BATTLE_BLUNT_ATTACK>ADD: 10,
	
	ROOM_UNIVERSITY*>MUL: 2.0,
	BEHAVIOUR_LAWFULNESS>MUL: 0.75,
	BEHAVIOUR_SANITY>MUL: 0.8,
	
	ROOM_FARM*>MUL: 1.1,
	ROOM_ORCHARD*>MUL: 1.1,
	ROOM_LIBRARY_NORMAL>MUL: 1.25,
	ROOM_ADMIN_NORMAL>MUL: 1.25,
	ROOM_LABORATORY_NORMAL>MUL: 1.25,
},
```