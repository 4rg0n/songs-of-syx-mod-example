# RACE `data/assets/init/race`

<!-- TOC -->
* [RACE `data/assets/init/race`](#race-dataassetsinitrace)
  * [RACE KEYS](#race-keys)
    * [FILES](#files)
    * [POPULATION](#population)
    * [TOURISM](#tourism)
    * [PROPERTIES](#properties)
    * [EQUIPMENT](#equipment)
    * [RESOURCES](#resources)
    * [PREFERENCES](#preferences)
  * [RACE STATS](#race-stats)
    * [ACCESS](#access)
    * [APPEARANCE](#appearance)
    * [BATTLE](#battle)
    * [BURIAL](#burial)
    * [DISEASE](#disease)
    * [EDUCATION](#education)
    * [ENVIRONMENT](#environment)
    * [EQUIP](#equip)
    * [EVENT](#event)
    * [FOOD](#food)
    * [GOVERN](#govern)
    * [HOME](#home)
    * [LAW](#law)
    * [MONUMENTS](#monuments)
    * [NEEDS](#needs)
    * [POPULATION](#population-1)
    * [RELIGION](#religion)
    * [SERVICE](#service)
    * [STORED](#stored)
    * [WORK](#work)
  * [EXAMPLE: A SINGLE STAT](#example-a-single-stat)
  * [ACCEPTED STAT VALUES](#accepted-stat-values)
    * [STAT VALUE BALANCING](#stat-value-balancing)
<!-- TOC -->

*A period in a key represents an open bracket in the actual implementation; i.e. POPULATION.CLIMATE.COLD would be written as ```POPULATION:{ CLIMATE:{ COLD: 1.0, }, },```

## RACE KEYS
Info: [Integr.MAX_VALUE](https://docs.oracle.com/javase/8/docs/api/java/lang/Integer.html#MAX_VALUE)

| Key              | Type          | Required | Default | Min    | Max  | Description                                                                                                                                                                                                                                                                                                                     | Example                                                                                                      |
|------------------|---------------|----------|---------|--------|------|---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|--------------------------------------------------------------------------------------------------------------|
| PLAYABLE         | boolean       | no       | false   | none   | none | Whether the race can be played by the player.                                                                                                                                                                                                                                                                                   |                                                                                                              |
| TRAITS.\<TRAIT\> | keylist:float | no       | none    | 0.0    | 1.0  | List of traits with their chances the race can have.<br/>Possible race names can be found in `assets/init/race/trait`.                                                                                                                                                                                                          | `{ *: 0.1, COMPOTENT: 0.3, CRUEL: 0.2, }`<br/>or `{ *: 0.5, }` for all                                       |
| BOOST.\<KEY\>    |               | no       | none    | none   | none | See [boosts](boost.md)                                                                                                                                                                                                                                                                                                          |                                                                                                              |
| STATS.\<KEY\>    |               | no       | none    | none   | none | See [race stats](#race-stats)                                                                                                                                                                                                                                                                                                   |                                                                                                              |

### FILES
| Keys                      | Type     | Required | Default    | Min  | Max  | Description                                                                                                                                                            | Example                                                                                                      |
|---------------------------|----------|----------|------------|------|------|------------------------------------------------------------------------------------------------------------------------------------------------------------------------|--------------------------------------------------------------------------------------------------------------|
| BIO_FILE                  | filename | yes      | none       | none | none | File name (without file extension) of the general race bio file found in `assets/text/race/bio`.<br/>Used for the description when inspecting a citizen.               | `Dry`                                                                                                        |
| BIO_FILE_SPECIFIC         | filename | yes      | none       | none | none | File name (without file extension) of the specific race bio file found in `assets/text/race/bio/specific`<br/>Used for the race description when inspecting a citizen. | `Human`                                                                                                      |
| SPRITE_FILE               | filename | yes      | none       | none | none | File name (without file extension) of the race sprite sheet file found in `assets/sprite/race`.                                                                        | `Human`                                                                                                      |
| ICON_BIG                  | path     | no       | dummy icon | none | none | File path and sprite tile to the race big icon found in `assets/sprite/icon/`.                                                                                         | `32->race->Human->0`<br/>The real path would be:<br/>`assets/init/icon/32/race/Human.png` first (0) tile.    |
| ICON_SMALL                | path     | no       | dummy icon | none | none | File path and sprite tile to the race small icon found in `assets/sprite/icon/`.                                                                                       |
| HOME                      | filename | yes      | none       | none | none | File name (without file extension) of the race' home configuration found in `assets/init/race/home`<br/>Used for furnishing the homes.                                 | `HUMAN`                                                                                                      |
| KING_FILE                 | filename | yes      | none       | none | none | File name (without file extension) of the race' king file found in `assets/text/race/king`<br/>Used for AI kings when communicating with the player.                   | `Normal`                                                                                                     |
| RAID_TEXT_FILE            | filename | yes      | none       | none | none | File name (without file extension) of the race' raid messages file found in `assets/text/race/raider/message`<br/>Used for raider messages.                            | `Normal`                                                                                                     |
| RAIDER_NAME_FILE          | filename | yes      | none       | none | none | File name (without file extension) of the race' raider names file found in `assets/text/race/raider/name`<br/>Used for naming the raider leader.                       | `Normal`                                                                                                     |
| WORLD_NAME_FILE           | filename | yes      | none       | none | none | File name (without file extension) of the race' world name file found in `assets/text/names/world`<br/>Used for region names in the world.                             | `Misc`                                                                                                       |

### POPULATION
| Key                            | Type  | Required | Default | Min    | Max      | Description                                                                                                                                                                             | Example                                                                                                      |
|--------------------------------|-------|----------|---------|--------|----------|-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|--------------------------------------------------------------------------------------------------------------|
| POPULATION.CLIMATE.\<CLIMATE\> | float | no       | 0.0     | 0.0    | 100.0    | List of climates and how likely it is for a race to spawn in it.<br/>Possible \<CLIMATE\> names can be found in `assets/init/config/CLIMATE.txt`<br/>Used for regions on the world map. | `{ COLD: 0.8, TEMPERATE: 1.0, HOT: 0.0, }`</br>or `{ *: 1.0, }` for all                                      |
| POPULATION.GROWTH              | float | no       | 0.0001  | 0.0001 | 1.0      | Multiplier for determine how much the race will reproduce in a region.                                                                                                                  |                                                                                                              |
| POPULATION.IMMIGRATION_RATE    | float | no       | 0.0     | 0.0    | 100000.0 | Max amount of citizens, which can immigrate per day in your settlement.                                                                                                                 |                                                                                                              |
| POPULATION.MAX                 | float | no       | 1.0     | 0.0    | 1.0      | Multiplier for determine whether the max population is reached (35000). If at `0.5`, the max population would be `17500`.                                                               |                                                                                                              |
| POPULATION.TERRAIN.\<TERRAIN\> | float | no       | 0.0     | 0.0    | 100.0    | List of terrains and how likely it is for a race to spawn in it.<br/>Possible \<TERRAIN\> names can be found in `assets/init/config/TERRAIN.txt`<br/>Used for regions on the world map. | `{ FOREST: 0.5, WET: 0.5, NONE: 0.5, }`</br>or `{ *: 1.0, }` for all                                         |

### TOURISM
| Key                       | Type     | Required | Default | Min  | Max      | Description                                                                       | Example |
|---------------------------|----------|----------|---------|------|----------|-----------------------------------------------------------------------------------|---------|
| TOURIST.CREDITS           | float    | no       | none    | 0.0  | 100000.0 | How much money a tourist will give you when leaving a review.                     |         |
| TOURIST.OCCURENCE         | float    | no       | none    | 0.0  | 100000.0 | How many tourists can occur in your settlement.                                   |         |
| TOURIST.TOURIST_TEXT_FILE | filename | no       | none    | none | none     | File name containing the review texts for tourists in `assets/text/race/tourist`. |         |

### PROPERTIES
| Key                       | Type    | Required | Default          | Min  | Max              | Description                                            | Example |
|---------------------------|---------|----------|------------------|------|------------------|--------------------------------------------------------|---------|
| PROPERTIES.HEIGHT         | integer | yes      | none             | 0    | 200              | Height from ground level. [?] Used for...?             |         |
| PROPERTIES.WIDTH          | integer | yes      | none             | 5    | 15               | Size of the hit box.                                   |         |
| PROPERTIES.ADULT_AT_DAY   | integer | yes      | none             | 0    | Integr.MAX_VALUE | Days until adulthood.                                  |         |
| PROPERTIES.RAID_MERCINARY | float   | yes      | none             | 0.0  | 100000.0         | Chance for the race to spawn in a group of mercenaries |         |
| PROPERTIES.SLAVE_PRICE    | integer | no       | ADULT_AT_DAY*2+5 | 0    | Integr.MAX_VALUE | Price of the race as slaves.                           |         |
| PROPERTIES.SLEEPS         | boolean | no       | false            | none | none             | Whether the race needs to sleep.                       |         |                                                                                                              
| PROPERTIES.CORPSE_DECAY   | boolean | no       | false            | none | none             | Whether the dead body decays.                          |         |

### EQUIPMENT
| Key                     | Type    | Required | Default | Min    | Max   | Description                                                                                                                                                                                                                                                                                                                     | Example                                                                                                      |
|-------------------------|---------|----------|---------|--------|-------|---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|--------------------------------------------------------------------------------------------------------------|
| EQUIPMENT_ENABLED       | keylist | no       | none    | none   | none  | List of weapons, armour and mounts the race is allowed.<br/>Possible equipment names can be found in `assets/init/stats/equipment`.<br/>The names consist of the subfolder and the file name.<br/>So e.g.`BATTLE_WEAPON_HAMMER` for `battle/WEAPON_HAMMER.txt`.<br/>This will overwrite equipment from `EQUIPMENT_NOT_ENABLED`. | `[ BATTLE_ARMOUR_LEATHER, BATTLE_MOUNT, CIVIC_JEWELERY, CIVIC__CLOTHES, RANGED_BOW, ]`<br/>or `[*,]` for all |
| EQUIPMENT_NOT_ENABLED   | keylist | no       | none    | none   | none  | List of weapons, armour and mounts the race is disallowed.<br/>Possible equipment names can be found in `assets/init/stats/equipment`.<br/>The names consist of the subfolder and the file name.<br/>So e.g.`BATTLE_WEAPON_HAMMER` for `battle/WEAPON_HAMMER.txt`.                                                              | `[ BATTLE_ARMOUR_LEATHER, BATTLE_MOUNT, CIVIC_JEWELERY, CIVIC__CLOTHES, RANGED_BOW, ]`<br/>or `[*,]` for all |

### RESOURCES
| Key                            | Type             | Required | Default | Min | Max              | Description                                                                                                                                      | Example                      |
|--------------------------------|------------------|----------|---------|-----|------------------|--------------------------------------------------------------------------------------------------------------------------------------------------|------------------------------|
| RESOURCE_GROOMING.\<RESOURCE\> | keylist: integer | no       | none    | 0   | Integr.MAX_VALUE | List of resources with amounts the race produces when visiting a barber.<br/>Possible \<RESOURCE\> names can be found in `assets/init/resource`. | `{ LEATHER: 2, COTTON: 1, }` |
| RESOURCE.\<RESOURCE\>          | keylist:integer  | no       | none    | 0   | 100000           | List of resources with amounts the race produces when cannibalized.<br/>Possible \<RESOURCE\> names can be found in `assets/init/resource`.      | `{ MEAT: 2ß, COTTON: 10, }`  |

### PREFERENCES
| Key                                    | Type          | Required | Default | Min    | Max   | Description                                                                                                                                                                                                                                                                                                                     | Example                                                                                                      |
|----------------------------------------|---------------|----------|---------|--------|-------|---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|--------------------------------------------------------------------------------------------------------------|
| PREFERRED.FOOD.\<FOOD\>                | keylist       | no       | none    | none   | none  | List of foods the race prefers.<br/>Possible \<FOOD\> names can be found in `assets/init/resource/edible`.                                                                                                                                                                                                                      | `[ MEAT, FISH, EGG ]`<br/>or `[ *, ]` for all                                                                |
| PREFERRED.DRINK.\<DRINK\>              | keylist:float | no       | none    | none   | none  | List of drinks the race prefers.<br/>Possible \<DRINK\> names can be found in `assets/init/resource/drinkable`.                                                                                                                                                                                                                 | `[ ALCO_BEER, ]`<br/>or `[ *, ]` for all                                                                     |
| PREFERRED.WORK.\<ROOM\>                | keylist:float | no       | none    | 0.0    | 1.0   | List of rooms and their "prefer values" the race prefers to work in.<br/>Possible \<ROOM\> names can be found in `assets/init/room`.                                                                                                                                                                                            | `{ *: 0.3, MINE_CLAY: 0.1, BARRACKS_VANILLA: 1.0, _GUARD: 1.0, }`<br/>or `{ *: 0.5, }` for all               |
| PREFERRED.STRUCTURE.\<STRUCTURE\>      | keylist:float | no       | none    | 0.0    | 1.0   | List of wall structures and their "prefer values" the race prefers.<br/>Possible \<STRUCTURE\> names can be found in `assets/init/settlement/structure`.                                                                                                                                                                        | `{ _MUD: 0.1, WOOD: 0.5, STONE: 1.0, }`<br/>or `{ *: 0.5, }` for all                                         |
| PREFERRED.ROAD.\<FLOOR\>               | keylist:float | no       | none    | 0.0    | 1.0   | List of roads and their "prefer values" the race prefers.<br/>Possible \<FLOOR\> names can be found in `assets/init/settlement/floor`.                                                                                                                                                                                          | `{ *: 0.1, DARK1: 0.5, DARK2: 0.8, DARK3: 1.0, }`<br/>or `{ *: 0.5, }` for all                               |
| PREFERRED.POOL.\<POOL_ROOM\>           | keylist:float | no       | none    | 0.0    | 1.0   | List of pool rooms and their "prefer values" the race prefers to bath in.<br/>Possible \<POOL_ROOM\> names can be found in `assets/init/room`. They start with `POOL_`.                                                                                                                                                         | `{ *: 0.3, POOL_POND: 0.1, }`<br/>or `{ *: 0.5, }` for all                                                   |
| PREFERRED.OTHER_RACES.\<RACE\>         | keylist:float | no       | none    | 0.0    | 1.0   | List of races and their "prefer values" the race prefers to be with.<br/>Possible \<RACE\> names can be found in `assets/init/race`.                                                                                                                                                                                            | `{ *: 0.3, GARTHIMI: 0.1, TILAPI: 0.5, _TILAPI: 1.0, }`<br/>or `{ *: 0.5, }` for all                         |
| PREFERRED.OTHER_RACES_REVERSE.\<RACE\> | keylist:float | no       | none    | 0.0    | 1.0   | List of modded races and their "prefer values" the race prefers to be with.<br/>Possible \<RACE\> names can be found in `assets/init/race`.                                                                                                                                                                                     | `{ DRAKEN: 0.3, }`<br/>or `{ *: 0.5, }` for all                                                              |
| PREFERRED.RESOURCE_RPICE_MUL           | float         | no       | none    | 0.0    | 100.0 | Price multiplier for resources the AI sells.                                                                                                                                                                                                                                                                                    |                                                                                                              |
| PREFERRED.RESOURCE_RPICE_CAP           | float         | no       | none    | 0.0    | 1.0   | Price multiplier for resources the AI buys.                                                                                                                                                                                                                                                                                     |                                                                                                              | | `24->race->Human->0`<br/>The real path would be:<br/>`assets/init/icon/24/race/Human.png` first (0) tile.    |

## RACE STATS

The stats of a race are somewhat special. They influence how much **standing** a race can and will get.
They are created dynamically by the game code.
There are a lot of them, and their keys have a semantic in them. This means they consist of multiple parts with special meanings.

Key syntax: `{CATEGORY}_{STAT_NAME}` e.g. `MONUMENTS_MONUMENT_STATUE` where `MONUMENTS_` is the category and `MONUMENT_STATUE` is a monument room name.
Keys can also count for multiple stats by using a wildcard. E.g. `ACCESS*` would count for all stat keys starting with `ACCESS`.
Some of them are hard coded or consist of loaded game assets like `STORED_MEAT`, which is a resource from `assets/init/resource/edible/MEAT.txt`.

Lists with all stats available in the vanilla game is below. '[Invert]' in the Description column indicates that existing race files use INVERTED = true for that key to indicate it has a negative effect (such as the presence of corpses in your city), and is thus suggested you do the same if you want to keep the intended effect of that key.

### ACCESS
| Key                 | Description                                   |
|---------------------|-----------------------------------------------|
| ACCESS_LIGHT        | Access to torches and other light sources.    |
| ACCESS_NOISE        | [Invert] Access to noise; [?] inverted.       |
| ACCESS_SHAPE_ROUND  | Access to round buildings.                    |
| ACCESS_SHAPE_SQUARE | Access to square buildings.                   |
| ACCESS_SPACE        | Access to open spaces; [?] deprecated in v70. |
| ACCESS_URBANISATION | [?] Access to tight/cluttered spaces.         |
| ACCESS_ACCESS_WATER | [?] Access to any source of water.            |
| ACCESS_WATER_SALT   | Access to salt water (oceans).                |
| ACCESS_WATER_SWEET  | Access to fresh water (rivers, lakes, wells). |

### APPEARANCE
| Key             | Description                                           |
|-----------------|-------------------------------------------------------|
| APPEARANCE_DEAD | [Invert] Presence and visibility of unburied corpses. |

### BATTLE
| Key                      | Description                                                      |
|--------------------------|------------------------------------------------------------------|
| BATTLE_ARCHERY_VANILLA   | Training in an archery building.                                 |
| BATTLE_BARRACKS_VANILLA  | Training in a barracks building.                                 |
| BATTLE_BESIEGED          | [Invert] How long your city has been under siege.                |
| BATTLE_CHIVALRY          | Benevolent post-battle actions taken by you.                     |
| BATTLE_CRUELTY           | Cruel post-battle actions taken by you.                          |
| BATTLE_COMBAT_EXPERIENCE | Total amount of experience gained in battle.                     |
| BATTLE_ENEMY_KILLS       | Amount of enemy soldiers killed.                                 |
| BATTLE_PROWESS           | [?] Amount of battles won (and to what extent).                  |
| BATTLE_RECRUITS          | Amount of citizens currently undergoing battle training.         |
| BATTLE_ROUTING           | Amount of friendly soldiers currently routing.                   |
| BATTLE_SOLDIERS          | [?] Amount of friendly soldiers, of a certain type, in an army.  |
| BATTLE_SOLDIERS_TOTAL    | Amount of friendly soldiers in an army.                          |
| BATTLE_WAR_TIME          | Amount of time your faction has spent at war with other nations. |

### BURIAL
| Key                      | Description                                                              |
|--------------------------|--------------------------------------------------------------------------|
| BURIAL_DESECRATION       | Damage or early removal of burial rooms (graveyards, tombs, mounds, etc. |
| BURIAL_GRAVEYARD_NORMAL  | Presence and quality of graveyards.                                      |
| BURIAL_TOMB_NORMAL       | Presence and quality of tombs/crypts.                                    |

### DISEASE
| Key              | Description                                                                    |
|------------------|--------------------------------------------------------------------------------|
| DISEASE_INCUBATE | [Invert?] Presence of incubated subjects carrying a disease, but not yet sick. |
| DISEASE_INFECTED | [Invert?] Presence of diseased subjects who are sick.                          |

### EDUCATION
| Key                      | Description                                |
|--------------------------|--------------------------------------------|
| EDUCATION_EDUCATION      | Average education level in your city.      |
| EDUCATION_INDOCTRINATION | Average indoctrination level in your city. |

### ENVIRONMENT
| Key                        | Description                                               |
|----------------------------|-----------------------------------------------------------|
| ENVIRONMENT_BUILDING_PREF  | [?] Presence of preferred building structure types.       |
| ENVIRONMENT_CANNIBALISM    | Eating other races.                                       |
| ENVIRONMENT_CLIMATE        | [?] Presence of preferred climates.                       |
| ENVIRONMENT_OTHERS         | [?]                                                       |
| ENVIRONMENT_POOL_PREF      | [?] Presence of preferred pool rooms.                     |
| ENVIRONMENT_ROAD_ACCESS    | [?]                                                       |
| ENVIRONMENT_ROAD_PREF      | [?] Presence of preferred road floors.                    |
| ENVIRONMENT_UNBURRIED      | [?] Presence of dead subjects contained in burial mounds. |

### EQUIP
Dynamically created via assets from: `assets/init/stats/equip`

| Key                         | Description                     |
|-----------------------------|---------------------------------|
| EQUIP_BATTLE_ARMOUR_LEATHER | Having leather armour equipped. |
| EQUIP_BATTLE_ARMOUR_PLATE   | Having plate armour equipped.   |
| EQUIP_BATTLE_MOUNT          | Being mounted on cavalry.       |
| EQUIP_BATTLE_WEAPON_HAMMER  | Having a warhammer equipped.    |
| EQUIP_BATTLE_WEAPON_SHIELD  | Having a shield equipped.       |
| EQUIP_BATTLE_WEAPON_SHORT   | Having a short sword equipped.  |
| EQUIP_BATTLE_WEAPON_SLASH   | Having a falcon equipped.       |
| EQUIP_BATTLE_WEAPON_SPEAR   | Having a spear equipped.        |
| EQUIP_RANGED_BOW            | Having a bow equipped.          |
| EQUIP_CIVIC_CLOTHES         | Having clothes equipped.        |
| EQUIP_CIVIC_JEWELRY         | Having jewelry equipped.        |

### EVENT
| Key                 | Description                              |
|---------------------|------------------------------------------|
| EVENT_DAY_OFF       | Being given days off via decree.         |
| EVENT_EVENT         | [?] Occurance of any event.              |
| EVENT_SERIAL_KILLER | [?] Occurence of serial killer events.   |
| EVENT_HANDOUT       | Being given a handout via decree.        |
| EVENT_OVERTIME      | Working overtime via decree.             |
| EVENT_PROSECUTION   | Being prosecuted via decree.             |
| EVENT_SLAVES_FREED  | [?] Being freed from slavery via decree. |

### FOOD
| Key                    | Description                                      |
|------------------------|--------------------------------------------------|
| FOOD_DRINK_PREFFERENCE | [?] Presence of preferred drinks.                |
| FOOD_DRINK_RATIONS     | [?]                                              |
| FOOD_FOOD_DAYS         | Amount of time current food stockpile will last. |
| FOOD_FOOD_PREFFERENCE  | [?] Presence of preferred food.                  |
| FOOD_FOOD_RATIONS      | [?] Presence of rations.                         |
| FOOD_STARVATION        | [?] Amount of time spent starving.               |

### GOVERN
| Key                   | Description                                         |
|-----------------------|-----------------------------------------------------|
| GOVERN_RICHES         | Amount of denarii currently possessed by your city. |
| GOVERN_TOURISM_ENEMY  | [?] Presence of tourists of hated race.             |
| GOVERN_TOURISM_FRIEND | [?] Amount of tourists of non-hated races.          |

### HOME
| Key            | Description                                      |
|----------------|--------------------------------------------------|
| HOME_FURNITURE | Amount of furniture present in a subject's home. |
| HOME_HOUSED    | Whether a subject is housed or homeless.         |

### LAW
| Key           | Description                                             |
|---------------|---------------------------------------------------------|
| LAW_ARENA     | Criminals punished by fighting in the arena.            |
| LAW_ENSLAVED  | Criminals punished by enslavement.                      |
| LAW_EQUALITY  | Fair treatment of all races.                            |
| LAW_EXECUTION | Criminals punished by execution.                        |
| LAW_EXILE     | Criminals punished by exile.                            |
| LAW_EX_CON    | Punished criminals who are no longer imprisoned.        |
| LAW_JUDGEMENT | Criminals processed in court.                           |
| LAW_LAW       | [?]                                                     |
| LAW_NONE      | Criminals kept in the stockages without any punishment. |
| LAW_PARDONED  | Criminals pardoned by you.                              |
| LAW_PRISON    | Criminals punished by imprisonment.                     |
| LAW_STOCKS    | Criminals punished by the stocks.                       |

### MONUMENTS
Dynamically created via assets from: `assets/init/room`. Via rooms beginning with `MONUMENT_`.

| Key                               | Description                                                       |
|-----------------------------------|-------------------------------------------------------------------|
| MONUMENTS_MONUMENT_BLOB           | Access to humidifiers.                                            |
| MONUMENTS_MONUMENT_FLOWER         | Access to flower decorations.                                     |
| MONUMENTS_MONUMENT_PILLAR         | Access to pillar decorations.                                     |
| MONUMENTS_MONUMENT_STATUE         | Access to statue decorations.                                     |
| MONUMENTS_MONUMENT_TORCH          | Access to torches.                                                |
| MONUMENTS_MONUMENT_TORCH_UPGRADED | Access to upgraded torches.                                       |
| MONUMENTS_MONUMENT_TREE           | Access to tree decorations. NOT natural trees; player-built only. |

### NEEDS
| Key              | Description |
|------------------|-------------|
| NEEDS_EXHAUSTION | [?]         |
| NEEDS_EXPOSURE   | [?]         |
| NEEDS_HUNGER     | [?]         |
| NEEDS_INJURIES   | [?]         |
| NEEDS_SHOPPING   | [?]         |
| NEEDS_THIRST     | [?]         |

### POPULATION
| Key                        | Description                                                                     |
|----------------------------|---------------------------------------------------------------------------------|
| POPULATION_AGE             | [?] Average age of subjects in your city.                                       |
| POPULATION_EMIGRATING      | Amount of subjects leaving your city.                                           |
| POPULATION_FORMER_SLAVES   | Amount of subjects that are former slaves.                                      |
| POPULATION_IMMIGRANTS      | Amount of subjects who are immigrants.                                          |
| POPULATION_MAJORITY        | Whether or not this subject's race is the majority in your city's population.   |
| POPULATION_NATIVES         | Amount of subjects who are natives (born via nurseries).                        |
| POPULATION_NOBLES          | Amount of nobles who share this subject's race. [?] Is it really race-specific? |
| POPULATION_SLAVES_OTHER    | Amount of slaves who aren't this subject's race.                                |
| POPULATION_SLAVES_SELF     | Amount of slaves who are this subject's race.                                   |
| POPULATION_TRAPPED         | Amount of subjects cut off from the throne room.                                |
| POPULATION_WRONGFUL_DEATHS | Amount of wrongful deaths.                                                      |

### RELIGION
Access to religion rooms.

| Key                          | Description                                    |
|------------------------------|------------------------------------------------|
| RELIGION_RELIGION_OPPOSITION | Tolerance of other religions.                  |
| RELIGION_SHRINE              | Access to a shrine of this subject's religion. |
| RELIGION_TEMPLE              | Access to a temple of this subject's religion. |

### SERVICE
Access to service rooms. Dynamically created via assets from: `assets/init/room`. Via rooms containing a `SERVICE` section in their config.

| Key                      | Description                        |
|--------------------------|------------------------------------|
| SERVICE_ARENAG_NORMAL    | Access to a Grand Arena.           |
| SERVICE_BARBER_NORMAL    | Access to a Barber.                |
| SERVICE_BATH_NORMAL      | Access to a Bathhouse.             |
| SERVICE_BENCH            | Access to benches.                 |
| SERVICE_CANTEEN_NORMAL   | Access to a Canteen.               |
| SERVICE_EATERY_NORMAL    | Access to an Eatery.               |
| SERVICE_FIGHTPIT_NORMAL  | Access to a Fight Pit.             |
| SERVICE_HEARTH           | Access to a Hearth.                |
| SERVICE_HOSPITAL         | Access to a Hospital.              |
| SERVICE_LAVATORY_NORMAL  | Access to a Lavatory               |
| SERVICE_MARKET_NORMAL    | Access to a Market                 |
| SERVICE_MISC_SKINNYDIP   | Access to any skinny-dip location. |
| SERVICE_PHYSICIAN_NORMAL | Access to a Physician.             |
| SERVICE_PLEASURE_NORMAL  | Access to a Massage Parlor.        |
| SERVICE_SPEAKER_NORMAL   | Access to a Speaker.               |
| SERVICE_STAGE_NORMAL     | Access to a Stage.                 |
| SERVICE_TAVERN_NORMAL    | Access to a Tavern.                |
| SERVICE_WELL_NORMAL      | Access to a Well.                  |

### STORED
Resources stored in your stockpiles. Dynamically created via assets from: `assets/init/resource`.

| Key                   | Description                                          | 
|-----------------------|------------------------------------------------------|
| STORED_ALCO_BEER      | Amount of Beer present in your stockpiles.           |
| STORED_ALCO_WINE      | Amount of Wine present in your stockpiles.           |
| STORED_ARMOUR_LEATHER | Amount of Leather Armour present in your stockpiles. |
| STORED_ARMOUR_PLATE   | Amount of Plate Armour present in your stockpiles.   |
| STORED_BOW            | Amount of Bows present in your stockpiles.           |
| STORED_BREAD          | Amount of Bread present in your stockpiles.          |
| STORED_CLAY           | Amount of Clay present in your stockpiles.           |
| STORED_CLOTHES        | Amount of Clothes present in your stockpiles.        |
| STORED_COAL           | Amount of Coal present in your stockpiles.           |
| STORED_COTTON         | Amount of Cotton present in your stockpiles.         |
| STORED_EGG            | Amount of Eggs present in your stockpiles.           |
| STORED_FABRIC         | Amount of Fabric present in your stockpiles.         |
| STORED_FISH           | Amount of Fish present in your stockpiles.           |
| STORED_FRUIT          | Amount of Fruit present in your stockpiles.          |
| STORED_FURNITURE      | Amount of Furniture present in your stockpiles.      |
| STORED_GEM            | Amount of Gems present in your stockpiles.           |
| STORED_GRAIN          | Amount of Grain present in your stockpiles.          |
| STORED_HERB           | Amount of Herbs present in your stockpiles.          |
| STORED_JEWELRY        | Amount of Jewelry present in your stockpiles.        |
| STORED_LEATHER        | Amount of Leather present in your stockpiles.        |
| STORED_LIVESTOCK      | Amount of Livestock present in your stockpiles.      |
| STORED_MACHINERY      | Amount of Machinery present in your stockpiles.      |
| STORED_MEAT           | Amount of Meat present in your stockpiles.           |
| STORED_METAL          | Amount of Metal present in your stockpiles.          |
| STORED_MUSHROOM       | Amount of Mushroom present in your stockpiles.       |
| STORED_OPIATES        | Amount of Opiates present in your stockpiles.        |
| STORED_ORE            | Amount of Ore present in your stockpiles.            |
| STORED_PAPER          | Amount of Paper present in your stockpiles.          |
| STORED_POTTERY        | Amount of Pottery present in your stockpiles.        |
| STORED_RATION         | Amount of Rations present in your stockpiles.        |
| STORED_SITHILON       | Amount of Sithilon present in your stockpiles.       |
| STORED_STONE          | Amount of Stone present in your stockpiles.          |
| STORED_STONE_CUT      | Amount of Cut Stone present in your stockpiles.      |
| STORED_TOOL           | Amount of Tools present in your stockpiles.          |
| STORED_VEGETABLE      | Amount of Vegetables present in your stockpiles.     |
| STORED_WEAPON_HAMMER  | Amount of Warhammers present in your stockpiles.     |
| STORED_WEAPON_MOUNT   | Amount of Warmounts present in your stockpiles.      |
| STORED_WEAPON_SHIELD  | Amount of Shields present in your stockpiles.        |
| STORED_WEAPON_SHORT   | Amount of Shortswords present in your stockpiles.    |
| STORED_WEAPON_SLASH   | Amount of Falcons present in your stockpiles.        |
| STORED_WEAPON_SPEAR   | Amount of Spears present in your stockpiles.         |
| STORED_WOOD           | Amount of Wood present in your stockpiles.           |

### WORK
| Key                 | Description                                                         |
|---------------------|---------------------------------------------------------------------|
| WORK_EMPLOYED       | Amount of subjects currently employed.                              |
| WORK_FULFILLMENT    | How this subject feels about its current job (aka job fulfillment). |
| WORK_INCAPACITATED  | How this subject feels about being incapacitated.                   |
| WORK_RETIREMENT     | Amount of subjects currently retired.                               |
| WORK_RETIREMENT_AGE | The age at which a subject retires (lower = more fulfillment).      |
| WORK_WORK_TIME      | [?] The amount of time spent working.                               |

## EXAMPLE: A SINGLE STAT
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

## ACCEPTED STAT VALUES
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

### STAT VALUE BALANCING
Referencing values used in base-game race files, the game tends to reuse certain values in a consistent way. Sticking to these values may assist in keeping your mod's values balanced with vanilla. They are:

- 3 (subject cares a lot)
- 2
- 1 (subject somewhat cares)
- 0.5
- 0.25
- 0 (subject does not care)

Values indicate desire (positive effect) by default; if key INVERTED = true, then values indicate dislike instead (negative effect).