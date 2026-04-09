# Race

`data/assets/init/race`

| Key                                    | Required | Default              | Min    | Max                                                                                            | Description                                                                                                                                                                                                                                                                                                                     | Example                                                                                                      |
|----------------------------------------|----------|----------------------|--------|------------------------------------------------------------------------------------------------|---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|--------------------------------------------------------------------------------------------------------------|
| PLAYABLE                               | no       | false                | none   | none                                                                                           | Whether the race can be played by the player.                                                                                                                                                                                                                                                                                   |                                                                                                              |
| PROPERTIES.HEIGHT                      | yes      | none                 | 0      | 200                                                                                            | Height from ground level.                                                                                                                                                                                                                                                                                                       |                                                                                                              |
| PROPERTIES.WIDTH                       | yes      | none                 | 5      | 15                                                                                             | Size of the hit box.                                                                                                                                                                                                                                                                                                            |                                                                                                              |
| PROPERTIES.ADULT_AT_DAY                | yes      | none                 | 0      | [Integr.MAX_VALUE](https://docs.oracle.com/javase/8/docs/api/java/lang/Integer.html#MAX_VALUE) | Days until adulthood.                                                                                                                                                                                                                                                                                                           |                                                                                                              |
| PROPERTIES.CORPSE_DECAY                | no       | false                | none   | none                                                                                           | Whether the dead body decays.                                                                                                                                                                                                                                                                                                   |                                                                                                              |
| PROPERTIES.SLEEPS                      | no       | false                | none   | none                                                                                           | Whether the race needs to sleep.                                                                                                                                                                                                                                                                                                |                                                                                                              |
| PROPERTIES.SLAVE_PRICE                 | no       | ADULT_AT_DAY * 2 + 5 | 0      | [Integr.MAX_VALUE](https://docs.oracle.com/javase/8/docs/api/java/lang/Integer.html#MAX_VALUE) | Price of the race as slaves.                                                                                                                                                                                                                                                                                                    |                                                                                                              |
| PROPERTIES.RAID_MERCINARY              | yes      | none                 | 0.0    | 100000.0                                                                                       | Chance for the race to spawn in a group of mercenaries                                                                                                                                                                                                                                                                          |                                                                                                              |
| ICON_SMALL                             | no       | dummy icon           | none   | none                                                                                           | File path and sprite tile to the race small icon found in `assets/sprite/icon/`.                                                                                                                                                                                                                                                | `24->race->Human->0`<br/>The real path would be:<br/>`assets/init/icon/24/race/Human.png` first (0) tile.    |
| ICON_BIG                               | no       | dummy icon           | none   | none                                                                                           | File path and sprite tile to the race big icon found in `assets/sprite/icon/`.                                                                                                                                                                                                                                                  | `32->race->Human->0`<br/>The real path would be:<br/>`assets/init/icon/32/race/Human.png` first (0) tile.    |
| SPRITE_FILE                            | yes      | none                 | none   | none                                                                                           | File name (without file ending) of the race sprite sheet file found in `assets/sprite/race`.                                                                                                                                                                                                                                    | `Human`                                                                                                      |
| BIO_FILE                               | yes      | none                 | none   | none                                                                                           | File name (without file ending) of the general race bio file found in `assets/text/race/bio`.<br/>Used for the description when inspecting a citizen.                                                                                                                                                                           | `Dry`                                                                                                        |
| BIO_FILE_SPECIFIC                      | yes      | none                 | none   | none                                                                                           | File name (without file ending) of the specific race bio file found in `assets/text/race/bio/specific`<br/>Used for the race description when inspecting a citizen.                                                                                                                                                             | `Human`                                                                                                      |
| KING_FILE                              | yes      | none                 | none   | none                                                                                           | File name (without file ending) of the race' king file found in `assets/text/race/king`<br/>Used for AI kings when communicating with the player.                                                                                                                                                                               | `Normal`                                                                                                     |
| WORLD_NAME_FILE                        | yes      | none                 | none   | none                                                                                           | File name (without file ending) of the race' world name file found in `assets/text/names/world`<br/>Used for region names in the world.                                                                                                                                                                                         | `Misc`                                                                                                       |
| RAID_TEXT_FILE                         | yes      | none                 | none   | none                                                                                           | File name (without file ending) of the race' raid messages file found in `assets/text/race/raider/message`<br/>Used for raider messages.                                                                                                                                                                                        | `Normal`                                                                                                     |
| RAIDER_NAME_FILE                       | yes      | none                 | none   | none                                                                                           | File name (without file ending) of the race' raider names file found in `assets/text/race/raider/name`<br/>Used for naming the raider leader.                                                                                                                                                                                   | `Normal`                                                                                                     |
| HOME                                   | yes      | none                 | none   | none                                                                                           | File name (without file ending) of the race' home configuration found in `assets/init/race/home`<br/>Used for furnishing the homes.                                                                                                                                                                                             | `HUMAN`                                                                                                      |
| PREFERRED.FOOD.\<FOOD\>                | no       | none                 | none   | none                                                                                           | List of foods the race prefers.<br/>Possible \<FOOD\> names can be found in `assets/init/resource/edible`.                                                                                                                                                                                                                      | `[ MEAT, FISH, EGG ]`<br/>or `[ *, ]` for all                                                                |
| PREFERRED.DRINK.\<DRINK\>              | no       | none                 | none   | none                                                                                           | List of drinks the race prefers.<br/>Possible \<DRINK\> names can be found in `assets/init/resource/drinkable`.                                                                                                                                                                                                                 | `[ ALCO_BEER, ]`<br/>or `[ *, ]` for all                                                                     |
| PREFERRED.STRUCTURE.\<STRUCTURE\>      | no       | none                 | 0.0    | 1.0                                                                                            | List of wall structures and their "prefer values" the race prefers.<br/>Possible \<STRUCTURE\> names can be found in `assets/init/settlement/structure`.                                                                                                                                                                        | `{ _MUD: 0.1, WOOD: 0.5, STONE: 1.0, }`<br/>or `{ *: 0.5, }` for all                                         |
| PREFERRED.ROAD.\<FLOOR\>               | no       | none                 | 0.0    | 1.0                                                                                            | List of roads and their "prefer values" the race prefers.<br/>Possible \<FLOOR\> names can be found in `assets/init/settlement/floor`.                                                                                                                                                                                          | `{ *: 0.1, DARK1: 0.5, DARK2: 0.8, DARK3: 1.0, }`<br/>or `{ *: 0.5, }` for all                               |
| PREFERRED.WORK.\<ROOM\>                | no       | none                 | 0.0    | 1.0                                                                                            | List of rooms and their "prefer values" the race prefers to work in.<br/>Possible \<ROOM\> names can be found in `assets/init/room`.                                                                                                                                                                                            | `{ *: 0.3, MINE_CLAY: 0.1, BARRACKS_VANILLA: 1.0, _GUARD: 1.0, }`<br/>or `{ *: 0.5, }` for all               |
| PREFERRED.POOL.\<POOL_ROOM\>           | no       | none                 | 0.0    | 1.0                                                                                            | List of pool rooms and their "prefer values" the race prefers to bath in.<br/>Possible \<POOL_ROOM\> names can be found in `assets/init/room`. They start with `POOL_`.                                                                                                                                                         | `{ *: 0.3, POOL_POND: 0.1, }`<br/>or `{ *: 0.5, }` for all                                                   |
| PREFERRED.RESOURCE_RPICE_MUL           | no       | none                 | 0.0    | 100.0                                                                                          | Price multiplier for resources the AI sells.                                                                                                                                                                                                                                                                                    |                                                                                                              |
| PREFERRED.RESOURCE_RPICE_CAP           | no       | none                 | 0.0    | 1.0                                                                                            | Price multiplier for resources the AI buys.                                                                                                                                                                                                                                                                                     |                                                                                                              |
| PREFERRED.OTHER_RACES.\<RACE\>         | no       | none                 | 0.0    | 1.0                                                                                            | List of races and their "prefer values" the race prefers to be with.<br/>Possible \<RACE\> names can be found in `assets/init/race`.                                                                                                                                                                                            | `{ *: 0.3, GARTHIMI: 0.1, TILAPI: 0.5, _TILAPI: 1.0, }`<br/>or `{ *: 0.5, }` for all                         |
| PREFERRED.OTHER_RACES_REVERSE.\<RACE\> | no       | none                 | 0.0    | 1.0                                                                                            | List of modded races and their "prefer values" the race prefers to be with.<br/>Possible \<RACE\> names can be found in `assets/init/race`.                                                                                                                                                                                     | `{ DRAKEN: 0.3, }`<br/>or `{ *: 0.5, }` for all                                                              |
| TRAITS.\<TRAIT\>                       | no       | none                 | 0.0    | 1.0                                                                                            | List of traits with their chances the race can have.<br/>Possible race names can be found in `assets/init/race/trait`.                                                                                                                                                                                                          | `{ *: 0.1, COMPOTENT: 0.3, CRUEL: 0.2, }`<br/>or `{ *: 0.5, }` for all                                       |
| POPULATION.MAX                         | no       | 1.0                  | 0.0    | 1.0                                                                                            | Multiplier for determine whether the max population is reached (35000). If at `0.5`, the max population would be `17500`.                                                                                                                                                                                                       |                                                                                                              |
| POPULATION.GROWTH                      | no       | 0.0001               | 0.0001 | 1.0                                                                                            | Multiplier for determine how much the race will reproduce in a region.                                                                                                                                                                                                                                                          |                                                                                                              |
| POPULATION.IMMIGRATION_RATE            | no       | 0.0                  | 0.0    | 100000.0                                                                                       | Max amount of citizens, which can immigrate per day in your settlement.                                                                                                                                                                                                                                                         |                                                                                                              |
| POPULATION.CLIMATE.\<CLIMATE\>         | no       | 0.0                  | 0.0    | 100.0                                                                                          | List of climates and how likely it is for a race to spawn in it.<br/>Possible \<CLIMATE\> names can be found in `assets/init/config/CLIMATE.txt`<br/>Used for regions on the world map.                                                                                                                                         | `{ COLD: 0.8, TEMPERATE: 1.0, HOT: 0.0, }`</br>or `{ *: 1.0, }` for all                                      |
| POPULATION.TERRAIN.\<TERRAIN\>         | no       | 0.0                  | 0.0    | 100.0                                                                                          | List of terrains and how likely it is for a race to spawn in it.<br/>Possible \<TERRAIN\> names can be found in `assets/init/config/TERRAIN.txt`<br/>Used for regions on the world map.                                                                                                                                         | `{ FOREST: 0.5, WET: 0.5, NONE: 0.5, }`</br>or `{ *: 1.0, }` for all                                         |
| EQUIPMENT_NOT_ENABLED                  | no       | none                 | none   | none                                                                                           | List of weapons, armour and mounts the race is disallowed.<br/>Possible equipment names can be found in `assets/init/stats/equipment`.<br/>The names consist of the subfolder and the file name.<br/>So e.g.`BATTLE_WEAPON_HAMMER` for `battle/WEAPON_HAMMER.txt`.                                                              | `[ BATTLE_ARMOUR_LEATHER, BATTLE_MOUNT, CIVIC_JEWELERY, CIVIC__CLOTHES, RANGED_BOW, ]`<br/>or `[*,]` for all |
| EQUIPMENT_ENABLED                      | no       | none                 | none   | none                                                                                           | List of weapons, armour and mounts the race is allowed.<br/>Possible equipment names can be found in `assets/init/stats/equipment`.<br/>The names consist of the subfolder and the file name.<br/>So e.g.`BATTLE_WEAPON_HAMMER` for `battle/WEAPON_HAMMER.txt`.<br/>This will overwrite equipment from `EQUIPMENT_NOT_ENABLED`. | `[ BATTLE_ARMOUR_LEATHER, BATTLE_MOUNT, CIVIC_JEWELERY, CIVIC__CLOTHES, RANGED_BOW, ]`<br/>or `[*,]` for all |
| RESOURCE.\<RESOURCE\>                  | no       | none                 | 0      | 100000                                                                                         | List of resources with amounts the race produces when cannibalized.<br/>Possible \<RESOURCE\> names can be found in `assets/init/resource`.                                                                                                                                                                                     | `{ MEAT: 2ß, COTTON: 10, }`                                                                                  |
| RESOURCE_GROOMING.\<RESOURCE\>         | no       | none                 | 0      | [Integr.MAX_VALUE](https://docs.oracle.com/javase/8/docs/api/java/lang/Integer.html#MAX_VALUE) | List of resources with amounts the race produces when visiting a barber.<br/>Possible \<RESOURCE\> names can be found in `assets/init/resource`.                                                                                                                                                                                | `{ LEATHER: 2, COTTON: 1, }`                                                                                 |
| TOURIST.OCCURENCE                      | no       | none                 | 0.0    | 100000.0                                                                                       | How many tourists can occur in your settlement.                                                                                                                                                                                                                                                                                 |                                                                                                              |
| TOURIST.CREDITS                        | no       | none                 | 0.0    | 100000.0                                                                                       | How much money a tourist will give you when leaving a review.                                                                                                                                                                                                                                                                   |                                                                                                              |
| TOURIST.TOURIST_TEXT_FILE              | no       | none                 | none   | none                                                                                           | File name containing the review texts for tourists in `assets/text/race/tourist`.                                                                                                                                                                                                                                               | `NORMAL`                                                                                                     |
| STATS.\<KEY\>                          | no       | none                 | none   | none                                                                                           | See [race stats](#race-stats)                                                                                                                                                                                                                                                                                                   |                                                                                                              |
| BOOST.\<KEY\>                          | no       | none                 | none   | none                                                                                           | See [boosts](boosts.md)                                                                                                                                                                                                                                                                                                         |                                                                                                              |

## Race stats

The stats of a race are somewhat special. They influence how much **standing** a race can and will get.
They are created dynamically by the game code. 
There are a lot of them, and their keys have a semantic in them. This means they consist of multiple parts with special meanings.

Key syntax: `{CATEGORY}_{STAT_NAME}` e.g. `MONUMENTS_MONUMENT_STATUE` where `MONUMENTS_` is the category and `MONUMENT_STATUE` is a monument room name.
Keys can also count for multiple stats by using a wildcard. E.g. `ACCESS*` would count for all stat keys starting with `ACCESS`.
Some of them are hard coded or consist of loaded game assets like `STORED_MEAT`, which is a resource from `assets/init/resource/edible/MEAT.txt`.


Lists with all stats available in the vanilla game:

#### ACCESS
```
ACCESS_ACCESS_WATER
ACCESS_LIGHT
ACCESS_NOISE
ACCESS_SHAPE_ROUND
ACCESS_SHAPE_SQUARE
ACCESS_SPACE
ACCESS_URBANISATION
ACCESS_WATER_SALT
ACCESS_WATER_SWEET
```

#### APPEARANCE
```
APPEARANCE_DEAD
```

#### BATTLE
```
BATTLE_ARCHERY_VANILLA
BATTLE_BARRACKS_VANILLA
BATTLE_BESIEGED
BATTLE_CHIVALRY
BATTLE_COMBAT_EXPERIENCE
BATTLE_CRUELTY
BATTLE_ENEMY_KILLS
BATTLE_PROWESS
BATTLE_RECRUITS
BATTLE_ROUTING
BATTLE_SOLDIERS
BATTLE_SOLDIERS_TOTAL
BATTLE_WAR_TIME
```

#### BURIAL
```
BURIAL_DESECRATION
BURIAL_GRAVEYARD_NORMAL
BURIAL_TOMB_NORMAL
```

#### DISEASE
```
DISEASE_INCUBATE
DISEASE_INFECTED
```

#### EDUCATION
```
EDUCATION_EDUCATION
EDUCATION_INDOCTRINATION
```

#### ENVIRONMENT
```
ENVIRONMENT_BUILDING_PREF
ENVIRONMENT_CANNIBALISM
ENVIRONMENT_CLIMATE
ENVIRONMENT_OTHERS
ENVIRONMENT_POOL_PREF
ENVIRONMENT_ROAD_ACCESS
ENVIRONMENT_ROAD_PREF
ENVIRONMENT_UNBURRIED
```

#### EQUIP

Dynamically created via assets from: `assets/init/stats/equip`

```
EQUIP_BATTLE_ARMOUR_LEATHER
EQUIP_BATTLE_ARMOUR_PLATE
EQUIP_BATTLE_MOUNT
EQUIP_BATTLE_WEAPON_HAMMER
EQUIP_BATTLE_WEAPON_SHIELD
EQUIP_BATTLE_WEAPON_SHORT
EQUIP_BATTLE_WEAPON_SLASH
EQUIP_BATTLE_WEAPON_SPEAR
EQUIP_CIVIC_CLOTHES
EQUIP_CIVIC_JEWELRY
EQUIP_RANGED_BOW
```

#### EVENT
```
EVENT_DAY_OFF
EVENT_EVENT
EVENT_HANDOUT
EVENT_OVERTIME
EVENT_PROSECUTION
EVENT_SERIAL_KILLER
EVENT_SLAVES_FREED
```

#### FOOD
```
FOOD_DRINK_PREFFERENCE
FOOD_DRINK_RATIONS
FOOD_FOOD_DAYS
FOOD_FOOD_PREFFERENCE
FOOD_FOOD_RATIONS
FOOD_STARVATION
```

#### GOVERN
```
GOVERN_RICHES
GOVERN_TOURISM_ENEMY
GOVERN_TOURISM_FRIEND
```

#### HOME
```
HOME_FURNITURE
HOME_HOUSED
```

#### LAW
```
LAW_ARENA
LAW_ENSLAVED
LAW_EQUALITY
LAW_EXECUTION
LAW_EXILE
LAW_EX_CON
LAW_JUDGEMENT
LAW_LAW
LAW_NONE
LAW_PARDONED
LAW_PRISON
LAW_STOCKS
```

#### MONUMENTS

Dynamically created via assets from: `assets/init/room`. Via rooms beginning with `MONUMENT_`.

```
MONUMENTS_MONUMENT_BLOB
MONUMENTS_MONUMENT_FLOWER
MONUMENTS_MONUMENT_PILLAR
MONUMENTS_MONUMENT_STATUE
MONUMENTS_MONUMENT_TORCH
MONUMENTS_MONUMENT_TORCH_UPGRADED
MONUMENTS_MONUMENT_TREE
```

#### NEEDS
```
NEEDS_EXHAUSTION
NEEDS_EXPOSURE
NEEDS_HUNGER
NEEDS_INJURIES
NEEDS_SHOPPING
NEEDS_THIRST
```

#### POPULATION
```
POPULATION_AGE
POPULATION_EMIGRATING
POPULATION_FORMER_SLAVES
POPULATION_IMMIGRANTS
POPULATION_MAJORITY
POPULATION_NATIVES
POPULATION_NOBLES
POPULATION_SLAVES_OTHER
POPULATION_SLAVES_SELF
POPULATION_TRAPPED
POPULATION_WRONGFUL_DEATHS
```

#### RELIGION
```
RELIGION_RELIGION_OPPOSITION
RELIGION_SHRINE
RELIGION_TEMPLE
```

#### SERVICE

Dynamically created via assets from: `assets/init/room`. Via rooms containing a `SERVICE` section in their config.

```
SERVICE_ARENAG_NORMAL
SERVICE_BARBER_NORMAL
SERVICE_BATH_NORMAL
SERVICE_BENCH
SERVICE_CANTEEN_NORMAL
SERVICE_EATERY_NORMAL
SERVICE_FIGHTPIT_NORMAL
SERVICE_HEARTH
SERVICE_HOSPITAL
SERVICE_LAVATORY_NORMAL
SERVICE_MARKET_NORMAL
SERVICE_MISC_SKINNYDIP
SERVICE_PHYSICIAN_NORMAL
SERVICE_PLEASURE_NORMAL
SERVICE_SPEAKER_NORMAL
SERVICE_STAGE_NORMAL
SERVICE_TAVERN_NORMAL
SERVICE_WELL_NORMAL
```

#### STORED

Dynamically created via assets from: `assets/init/resource`.

```
STORED_ALCO_BEER
STORED_ALCO_WINE
STORED_ARMOUR_LEATHER
STORED_ARMOUR_PLATE
STORED_BOW
STORED_BREAD
STORED_CLAY
STORED_CLOTHES
STORED_COAL
STORED_COTTON
STORED_EGG
STORED_FABRIC
STORED_FISH
STORED_FRUIT
STORED_FURNITURE
STORED_GEM
STORED_GRAIN
STORED_HERB
STORED_JEWELRY
STORED_LEATHER
STORED_LIVESTOCK
STORED_MACHINERY
STORED_MEAT
STORED_METAL
STORED_MUSHROOM
STORED_OPIATES
STORED_ORE
STORED_PAPER
STORED_POTTERY
STORED_RATION
STORED_SITHILON
STORED_STONE
STORED_STONE_CUT
STORED_TOOL
STORED_VEGETABLE
STORED_WEAPON_HAMMER
STORED_WEAPON_MOUNT
STORED_WEAPON_SHIELD
STORED_WEAPON_SHORT
STORED_WEAPON_SLASH
STORED_WEAPON_SPEAR
STORED_WOOD
```

#### WORK
```
WORK_EMPLOYED
WORK_FULFILLMENT
WORK_INCAPACITATED
WORK_RETIREMENT
WORK_RETIREMENT_AGE
WORK_WORK_TIME
```

### A single stat

A single stat may look like e.g.:

```
STATS: {
	MONUMENTS_MONUMENT_FLOWER: {
		CITIZEN: 0.5,
		SLAVE: 0.5,
		PRIO: 10,
		MULTIPLIER: 16,
	},
},
```

These are all possible values of a race stat:

| Key        | Required | Default | Min  | Max       | Description                                                                 | 
|------------|----------|---------|------|-----------|-----------------------------------------------------------------------------|
| PRIO       | no       | 1.0     | 0.0  | 100000.0  | Used for displaying stats ordered by prio in the race bio.                  |
| INVERTED   | no       | false   | none | none      | Whether the stat shall have a negative effect.                              |
| MULTIPLIER | no       | 0.0     | 0.0  | 10000.0   | Multiplier for how much standing the stat generates.                        |
| EXPONENT   | no       | 1.0     | 0.01 | 100000.0  | Exponential factor for increasing the standing.                             |
| DISMISS    | no       | false   | none | none      | Whether the stat shall not count to the overall reachable maximum standing. |
| NOBLE      | no       | 0.0     | 0.0  | 1000000.0 | How much the stat affects nobles.                                           |
| CITIZEN    | no       | 0.0     | 0.0  | 1000000.0 | How much the stat affects normal citizens.                                  |
| SLAVE      | no       | 0.0     | 0.0  | 1000000.0 | How much the stat affects slaves.                                           |
| CHILD      | no       | 0.0     | 0.0  | 1000000.0 | How much the stat affects children.                                         |
| OTHER      | no       | 0.0     | 0.0  | 1000000.0 | How much the stat affects all other people living in your settlement.       |