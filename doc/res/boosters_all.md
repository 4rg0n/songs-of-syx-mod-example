# All available vanilla game boosters ```v71```

:heart: Made by [Glorfy](https://github.com/hereto4) from the SoS Discord community.

- Note 1: 'Default' values are based on v68/v70 and may have changed in v71; some have been re-verified against v71.19 source.
- Note 2: All keys use their British spelling ('Honour', 'Behaviour', etc., except for 'Jewelry' which uses its American spelling), and some keys are intentionally misspelled (this will be noted next to the key).

<!-- TOC -->
* [All available vanilla game boosters ```v71```](#all-available-vanilla-game-boosters-v71)
  * [v71 Changes (from v70)](#v71-changes-from-v70)
    * [Renamed / Restructured](#renamed--restructured)
    * [Dropped (no replacement)](#dropped-no-replacement)
    * [New in v71](#new-in-v71)
  * [Table Format](#table-format)
  * [ACTIVITY](#activity)
  * [BATTLE](#battle)
    * [Battle Skills](#battle-skills)
    * [Damage Type: Force](#damage-type-force)
    * [Damage Type: Pierce](#damage-type-pierce)
    * [Damage Type: Slash](#damage-type-slash)
  * [BEHAVIOUR](#behaviour)
  * [CIVIC](#civic)
  * [CONSUMPTION](#consumption)
  * [TOOL](#tool)
  * [NOBLE (AI RULERS)](#noble-ai-rulers)
  * [PHYSICS](#physics)
  * [SERVICE RATE](#service-rate)
  * [RELIGION](#religion)
  * [ROOM](#room)
    * [Government](#government)
    * [Technology](#technology)
    * [Military](#military)
    * [Farm](#farm)
    * [Food Other](#food-other)
    * [Mine](#mine)
    * [Refiner](#refiner)
    * [Workshop](#workshop)
    * [Pasture](#pasture)
    * [Nursery](#nursery)
    * [Other](#other)
  * [REGION BUILDING](#region-building)
    * [Agriculture](#agriculture)
    * [Civic](#civic-1)
    * [Global](#global)
    * [Infrastructure](#infrastructure)
    * [Military](#military-1)
    * [Mine](#mine-1)
    * [Other](#other-1)
    * [Pasture](#pasture-1)
    * [Religion](#religion-1)
  * [REGION PROPERTIES](#region-properties)
    * [Military](#military-2)
    * [Religion](#religion-2)
    * [Captives](#captives)
    * [Loyalty](#loyalty)
    * [Other](#other-2)
    * [Population](#population)
    * [Resource Production](#resource-production)
    * [Resource Production (Yearly)](#resource-production-yearly)
<!-- TOC -->

## v71 Changes (from v70)
Boost-key differences between v70 and v71.19, verified against the runtime boost-key registry dump (v70 = 355 keys, v71 = 402 keys). Most "dropped + new" pairs are the same function re-keyed — see Renamed below.

### Renamed / Restructured
| Function                            | v70 key                                        | v71 key                                                                       |
|-------------------------------------|------------------------------------------------|-------------------------------------------------------------------------------|
| Region resource production          | `WORLD_RESOURCE_PRODUCTION_<res>`              | `WORLD_PRODUCTION_RES_<res>`                                                  |
| Region resource production (yearly) | `WORLD_WORLD_RESOURCE_PRODUCTION_<res>_YEARLY` | `WORLD_PRODUCTION_RES_<res>_YEARLY`                                           |
| Tax income (yearly)                 | `WORLD_WORLD_TAX_INCOME_YEARLY`                | `WORLD_TAX_INCOME_YEARLY`                                                     |
| Resource consumption                | `CON_<res>` (per-resource, 23 keys)            | per-room `ROOM_CONSUMPTION_<room>` + per-recipe `ROOM_CONSUMPTION_<room>_<i>` |
| Nurseries                           | `ROOM_NURSERY_<race>` (5 races)                | `ROOM_NURSERY_NORMAL` + `ROOM_BREEDER_GARTHIMI`                               |
| Max city population                 | `WORLD_MAX_CITY_POP`                           | `WORLD_MAX_CITY_POPCITIZEN` + `WORLD_MAX_CITY_POPSLAVE`                       |
| Fulfillment exponent                | `WORLD_FULFILLMENT_EXPONENT`                   | `WORLD_FULFILLMENT_EXPONENT_CITIZEN` + `WORLD_FULFILLMENT_EXPONENT_SLAVE`     |

### Dropped (no replacement)
| Key               | Name (v70)   |
|-------------------|--------------|
| `CIVIC_PASIFISM`  | Pacifism     |
| `CIVIC_TRADE_FEE` | Trade Tariff |

### New in v71
| Key                                          | Name                   | Notes                                  |
|----------------------------------------------|------------------------|----------------------------------------|
| `BEHAVIOUR_HAPPINESS_SLAVES`                 | Happiness (Slaves)     |                                        |
| `CIVIC_ADMIN`                                | Administration         |                                        |
| `CIVIC_TRUST`                                | Trust                  |                                        |
| `CIVIC_EDUCATION_LIMIT_0`                    | Study Limit: Childhood |                                        |
| `CIVIC_EDUCATION_LIMIT_1`                    | Study Limit: Adulthood |                                        |
| `PHYSICS_REPRODUCTION_AGE`                   | Reproduction Age       | new breeding mechanic                  |
| `PHYSICS_REPRODUCTION_SPEED`                 | Natural Births /year   | new breeding mechanic                  |
| `RATES_COURT`                                | Justice                | court service rate                     |
| `RATES_STOCKS`                               | Punishment             | stocks service rate                    |
| `ROOM_SCHOOL_NORMAL`                         | Schools                |                                        |
| `SLAVE_PRODUCTION_<race>`                    | Captives: \<race\>     | 8 races (region property)              |
| `WORLD_PRODUCTION_SLAVE_<race>`              | Production: \<race\>   | 8 races (slave production)             |
| `WORLD_PRODUCTION_SLAVE_<race>_YEARLY`       | Production: \<race\>   | 8 races (yearly)                       |
| `WORLD_PROXIMITY_TOLL`                       | Proximity (Toll)       |                                        |

## Table Format
| Key                    | Name                                | Default                                            | Description                    |
|------------------------|-------------------------------------|----------------------------------------------------|--------------------------------|
| key referenced by code | flavor name displayed by in-game UI | default preset value; boostables modify this value | usage and function of this key |

## ACTIVITY
| Key                   | Name       | Default | Description                                                 |
|-----------------------|------------|---------|-------------------------------------------------------------|
| `ACTIVITY_JUDGE`      | Judgement  | 1       | How often a subject wants to visit a court                  |
| `ACTIVITY_MOURN`      | Mourning   | 1       | How often a subject wants to visit a graveyard/crypt        |
| `ACTIVITY_PUNISHMENT` | Punishment | 1       | How often a subject wants to observe a criminal be punished |
| `ACTIVITY_SOCIAL`     | Social     | 1       | How often a subject wants to socialize with another citizen |

## BATTLE
### Battle Skills
references to parrying are speculative; parry may not be implemented in game (confirm in code?)

| Key                      | Name       | Default | Description                                                                  |
|--------------------------|------------|---------|------------------------------------------------------------------------------|
| `BATTLE_BLOCK`           | Block      | 1       | ability to use parry attacks and reduce damage with block armour             |
| `BATTLE_OFFENCE_SKILL`   | Offence    | 1       | ability to attack; exact stat effects are not defined, need to search code   |
| `BATTLE_DEFENCE_SKILL`   | Defence    | 1       | chance to avoid damage entirely when attacked from the front; i.e. dodge     |
| `BATTLE_FORMATION_SKILL` | Formation  | 0       | defensive skill when attacked in formation from the front                    |
| `BATTLE_RANGED_BOW`      | Skill: Bow | 0.1     | ability to use bows; exact stat effects are not defined, need to search code |
| `BATTLE_CHARGE`          | Charge     | 1       | adds extra damage to charge attacks                                          |
| `BATTLE_DEXTERITY`       | Dexterity  | 5       | chance to ignore target's block armour when attacking                        |
| `BATTLE_MORALE`          | Morale     | 4       | prevents routing                                                             |

### Damage Type: Force
| Key                        | Name             | Default | Description                                                                         |
|----------------------------|------------------|---------|-------------------------------------------------------------------------------------|
| `BATTLE_BLUNT_ATTACK`      | Force            | 40      | base damage applied by all attacks; multiplies other damage types; causes knockback |
| `BATTLE_BLUNT_DEFENCE`     | Force Absorbtion | 40      | [intentional mispelling] reduction of force damage                                  |
| `BATTLE_BLUNT_DEFENCE_DIR` | Force Block      | 1       | called 'Parry' in-game; amount of force damage reduced upon successful block        |

### Damage Type: Pierce
| Key                         | Name          | Default | Description                                                                   |
|-----------------------------|---------------|---------|-------------------------------------------------------------------------------|
| `BATTLE_PIERCE_ATTACK`      | Pierce Damage | 0       | special damage applied by some attacks                                        |
| `BATTLE_PIERCE_DEFENCE`     | Pierce Armour | 0       | reduction of pierce damage                                                    |
| `BATTLE_PIERCE_DEFENCE_DIR` | Pierce Block  | 0       | called 'Parry' in-game; amount of pierce damage reduced upon successful block |

### Damage Type: Slash
| Key                        | Name         | Default | Description                                                                  |
|----------------------------|--------------|---------|------------------------------------------------------------------------------|
| `BATTLE_SLASH_ATTACK`      | Slash Damage | 0       | special damage applied by some attacks                                       |
| `BATTLE_SLASH_DEFENCE`     | Slash Armour | 0       | reduction of pierce damage                                                   |
| `BATTLE_SLASH_DEFENCE_DIR` | Slash Block  | 0       | called 'Parry' in-game; amount of slash damage reduced upon successful block |

## BEHAVIOUR
| Key                          | Name               | Default | Description                                                     |
|------------------------------|--------------------|---------|-----------------------------------------------------------------|
| `BEHAVIOUR_HAPPINESS`        | Happiness          | 1       |                                                                 |
| `BEHAVIOUR_HAPPINESS_SLAVES` | Happiness (Slaves) | 0.5     | happiness for slaves; boosts increase submission                |
| `BEHAVIOUR_LAWFULNESS`       | Lawfulness         | 1       |                                                                 |
| `BEHAVIOUR_LOYALTY`          | Loyalty            | 1       | affects final Loyalty value after all other factors are applied |
| `BEHAVIOUR_SANITY`           | Sanity             | 1       |                                                                 |
| `BEHAVIOUR_SUBMISSION`       | Submission         | 1       | for slaves                                                      |

## CIVIC
| Key                       | Name                   | Default | Description                                                                              |
|---------------------------|------------------------|---------|------------------------------------------------------------------------------------------|
| `CIVIC_ACCIDENT`          | Safety                 | 1       | higher value = less accidents                                                            |
| `CIVIC_ADMIN`             | Administration         | 0       | administration points; used for technologies / region building                           |
| `CIVIC_DEFLATION`         | Deflation              | 1       | higher value = less inflation                                                            |
| `CIVIC_DIPLOMACY`         | Emissary Points        | 0       | emissary points; used to manipulate opinions of factions                                 |
| `CIVIC_EDUCATION_LIMIT_0` | Study Limit: Childhood | 10      | education limit during childhood (per age-type, index 0)                                 |
| `CIVIC_EDUCATION_LIMIT_1` | Study Limit: Adulthood | 10      | education limit during adulthood (per age-type, index 1)                                 |
| `CIVIC_FURNITURE`         | Furnishing             | 1       | furniture upkeep rate for citizen housing                                                |
| `CIVIC_GOV`               | Gov Points             | 5       | main currency to build your realm with; gained by assigning nobles to government duties  |
| `CIVIC_IMMIGRATION`       | Immigration Speed      | 4.5     | increases the replenishment of the immigration pool                                      |
| `CIVIC_INNOVATION`        | Innovation             | 0       | innovation points; used for technologies                                                 |
| `CIVIC_KNOWLEDGE`         | Knowledge              | 0       | knowledge points; used for technologies                                                  |
| `CIVIC_LANDING`           | Settle                 | 0       | multiplies starting population and resources upon placing your throne for the first time |
| `CIVIC_LAW`               | Law                    | 0       | the law of your city                                                                     |
| `CIVIC_MAINTENANCE`       | Robustness             | 1       | higher value = less room maintenance                                                     |
| `CIVIC_NOBLES_MAX`        | Nobilities             | 0       | max number of nobles allowed                                                             |
| `CIVIC_NOBLES_RANKS_MAX`  | Noble Promotions       | 0       | max number of ranks for nobles                                                           |
| `CIVIC_OPINION`           | Opinion                | 1.5     | base opinion modifier of foreign rulers towards you                                      |
| `CIVIC_RAIDING`           | Raid Security          | 1       | affects raider desire to demand/attack you; higher value = lower raider desire           |
| `CIVIC_SPOILAGE`          | Conservation           | 1       | affects stored resource decay; higher value = slower decay rate                          |
| `CIVIC_TRUST`             | Trust                  | 0       | a faction's reliability in keeping treaties and not starting war against you             |

## CONSUMPTION
v71 no longer exposes per-resource `CON_<resource>` boostables. Consumption is now per-room and registered under the ROOM (`ROOM_`) category. Each consuming room gets `ROOM_CONSUMPTION_<roomkey>` (single-recipe consumption rooms), and industry rooms with multiple input+output recipes get `ROOM_CONSUMPTION_<roomkey>_<i>` (one per recipe, `i` starting at 0). Higher value = higher consumption rate.

| Key                                     | Name                             | Default | Description                                                                  |
|-----------------------------------------|----------------------------------|---------|------------------------------------------------------------------------------|
| `ROOM_CONSUMPTION_ADMIN_NORMAL`         | Consumption Rate: Administration | 1       | resource consumption rate of Administrations                                 |
| `ROOM_CONSUMPTION_LABORATORY_NORMAL`    | Consumption Rate: Laboratory     | 1       | resource consumption rate of Laboratories                                    |
| `ROOM_CONSUMPTION_LIBRARY_NORMAL`       | Consumption Rate: Library        | 1       | resource consumption rate of Libraries                                       |
| `ROOM_CONSUMPTION__EMBASSY`             | Consumption Rate: Embassy        | 1       | resource consumption rate of Embassies (room key `_EMBASSY`)                 |
| `ROOM_CONSUMPTION_REFINER_BAKERY_0`     | Bakery Input: I                  | 1       | per-recipe input consumption rate (`i` 0-based, one per input+output recipe) |
| `ROOM_CONSUMPTION_REFINER_BAKERY_1`     | Bakery Input: II                 | 1       |                                                                              |
| `ROOM_CONSUMPTION_REFINER_BREWERY_0`    | Brewery Input: I                 | 1       |                                                                              |
| `ROOM_CONSUMPTION_REFINER_BREWERY_1`    | Brewery Input: II                | 1       |                                                                              |
| `ROOM_CONSUMPTION_REFINER_COALER_0`     | Charcoaler Input: I              | 1       |                                                                              |
| `ROOM_CONSUMPTION_REFINER_SMELTER_0`    | Metal Smelter Input: I           | 1       |                                                                              |
| `ROOM_CONSUMPTION_REFINER_WEAVER_0`     | Weaver Input: I                  | 1       |                                                                              |
| `ROOM_CONSUMPTION_WORKSHOP_BOWYER_0`    | Bowyer Input: I                  | 1       |                                                                              |
| `ROOM_CONSUMPTION_WORKSHOP_CARPENTER_0` | Carpenter Input: I               | 1       |                                                                              |
| `ROOM_CONSUMPTION_WORKSHOP_CARPENTER_1` | Carpenter Input: II              | 1       |                                                                              |
| `ROOM_CONSUMPTION_WORKSHOP_CARPENTER_2` | Carpenter Input: III             | 1       |                                                                              |
| `ROOM_CONSUMPTION_WORKSHOP_CARPENTER_3` | Carpenter Input: IV              | 1       |                                                                              |
| `ROOM_CONSUMPTION_WORKSHOP_CARPENTER_4` | Carpenter Input: V               | 1       |                                                                              |
| `ROOM_CONSUMPTION_WORKSHOP_JEWELRY_0`   | Jeweller Input: I                | 1       |                                                                              |
| `ROOM_CONSUMPTION_WORKSHOP_MASON_0`     | Masonry Input: I                 | 1       |                                                                              |
| `ROOM_CONSUMPTION_WORKSHOP_MECHANIC_0`  | Mechanic Input: I                | 1       |                                                                              |
| `ROOM_CONSUMPTION_WORKSHOP_PAPER_0`     | Papermaker Input: I              | 1       |                                                                              |
| `ROOM_CONSUMPTION_WORKSHOP_POTTERY_0`   | Pottery Input: I                 | 1       |                                                                              |
| `ROOM_CONSUMPTION_WORKSHOP_RATION_0`    | Rationmaker Input: I             | 1       |                                                                              |
| `ROOM_CONSUMPTION_WORKSHOP_RATION_1`    | Rationmaker Input: II            | 1       |                                                                              |
| `ROOM_CONSUMPTION_WORKSHOP_RATION_2`    | Rationmaker Input: III           | 1       |                                                                              |
| `ROOM_CONSUMPTION_WORKSHOP_RATION_3`    | Rationmaker Input: IV            | 1       |                                                                              |
| `ROOM_CONSUMPTION_WORKSHOP_RATION_4`    | Rationmaker Input: V             | 1       |                                                                              |
| `ROOM_CONSUMPTION_WORKSHOP_RATION_5`    | Rationmaker Input: VI            | 1       |                                                                              |
| `ROOM_CONSUMPTION_WORKSHOP_SMITHY_0`    | Smithy Input: I                  | 1       |                                                                              |
| `ROOM_CONSUMPTION_WORKSHOP_SMITHY_1`    | Smithy Input: II                 | 1       |                                                                              |
| `ROOM_CONSUMPTION_WORKSHOP_SMITHY_2`    | Smithy Input: III                | 1       |                                                                              |
| `ROOM_CONSUMPTION_WORKSHOP_SMITHY_3`    | Smithy Input: IV                 | 1       |                                                                              |
| `ROOM_CONSUMPTION_WORKSHOP_SMITHY_4`    | Smithy Input: V                  | 1       |                                                                              |
| `ROOM_CONSUMPTION_WORKSHOP_TAILOR_0`    | Tailor Input: I                  | 1       |                                                                              |
| `ROOM_CONSUMPTION_WORKSHOP_TAILOR_1`    | Tailor Input: II                 | 1       |                                                                              |
| `ROOM_CONSUMPTION_WORKSHOP_TAILOR_2`    | Tailor Input: III                | 1       |                                                                              |

## TOOL
| Key                                   | Name                       | Default | Description |
|---------------------------------------|----------------------------|---------|-------------|
| `EQUIP_LEVEL_TOOL_ADMIN_NORMAL`       | Tools(Administrations)     |         |             |
| `EQUIP_LEVEL_TOOL_FARM_COTTON`        | Tools(Cotton Farms)        |         |             |
| `EQUIP_LEVEL_TOOL_FARM_FRUIT`         | Tools(Fruit Farms)         |         |             |
| `EQUIP_LEVEL_TOOL_FARM_GRAIN`         | Tools(Grain Farms)         |         |             |
| `EQUIP_LEVEL_TOOL_FARM_HERB`          | Tools(Herb Farms)          |         |             |
| `EQUIP_LEVEL_TOOL_FARM_MUSHROOM`      | Tools(Mushroom Farms)      |         |             |
| `EQUIP_LEVEL_TOOL_FARM_SPICES`        | Tools(Opiate Farms)        |         |             |
| `EQUIP_LEVEL_TOOL_FARM_VEG`           | Tools(Vegetable Farms)     |         |             |
| `EQUIP_LEVEL_TOOL_FISHERY_NORMAL`     | Tools(Fisheries)           |         |             |
| `EQUIP_LEVEL_TOOL_LABORATORY_NORMAL`  | Tools(Laboratories)        |         |             |
| `EQUIP_LEVEL_TOOL_MINE_CLAY`          | Tools(Claypits)            |         |             |
| `EQUIP_LEVEL_TOOL_MINE_COAL`          | Tools(Coal Mines)          |         |             |
| `EQUIP_LEVEL_TOOL_MINE_GEM`           | Tools(Gem Mines)           |         |             |
| `EQUIP_LEVEL_TOOL_MINE_ORE`           | Tools(Ore Mines)           |         |             |
| `EQUIP_LEVEL_TOOL_MINE_SITHILON`      | Tools(Sithilon Mines)      |         |             |
| `EQUIP_LEVEL_TOOL_MINE_STONE`         | Tools(Stone Mines)         |         |             |
| `EQUIP_LEVEL_TOOL_ORCHARD_FRUIT`      | Tools(Fruit Orchards)      |         |             |
| `EQUIP_LEVEL_TOOL_PASTURE_AUR`        | Tools(Auroch Pastures)     |         |             |
| `EQUIP_LEVEL_TOOL_PASTURE_ENT`        | Tools(Entelodont Pastures) |         |             |
| `EQUIP_LEVEL_TOOL_PASTURE_GLOBDIEN`   | Tools(Globdien Pastures)   |         |             |
| `EQUIP_LEVEL_TOOL_PASTURE_MOUNT`      | Tools(War-Beast Pastures)  |         |             |
| `EQUIP_LEVEL_TOOL_PASTURE_ONX`        | Tools(Onx Pastures)        |         |             |
| `EQUIP_LEVEL_TOOL_WORKSHOP_BOWYER`    | Tools(Bowyers)             |         |             |
| `EQUIP_LEVEL_TOOL_WORKSHOP_CARPENTER` | Tools(Carpenters)          |         |             |
| `EQUIP_LEVEL_TOOL_WORKSHOP_JEWELRY`   | Tools(Jewellers)           |         |             |
| `EQUIP_LEVEL_TOOL_WORKSHOP_MASON`     | Tools(Masonries)           |         |             |
| `EQUIP_LEVEL_TOOL_WORKSHOP_MECHANIC`  | Tools(Mechanics)           |         |             |
| `EQUIP_LEVEL_TOOL_WORKSHOP_PAPER`     | Tools(Papermakers)         |         |             |
| `EQUIP_LEVEL_TOOL_WORKSHOP_POTTERY`   | Tools(Potteries)           |         |             |
| `EQUIP_LEVEL_TOOL_WORKSHOP_RATION`    | Tools(Rationmakers)        |         |             |
| `EQUIP_LEVEL_TOOL_WORKSHOP_SMITHY`    | Tools(Smithies)            |         |             |
| `EQUIP_LEVEL_TOOL_WORKSHOP_TAILOR`    | Tools(Tailors)             |         |             |
| `EQUIP_LEVEL_TOOL__WOODCUTTER`        | Tools(Woodcutters)         |         |             |

## NOBLE (AI RULERS)
misleading name; affects AI rulers, not player Nobles

| Key                | Name       | Default | Description                                                                                                                                                                                                                                                                                                            |
|--------------------|------------|---------|------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| `NOBLE_AGRRESSION` | Aggression | 1       | [intentional mispelling] affects AI military and war-related opinions: decreases War Declaration penalty and Failed Protection penalty, increases Joint/Mutual Wars bonus; increases garrison strength bonus and desire to maintain army presence in regions; increases equiment used in armies (alongside Competence) |
| `NOBLE_COMPETENCE` | Competence | 1       | [java field is mispelled 'COMPETANCE'] affects overall AI power: increases initial realm size on worldgen and equipment used in armies (alongside Aggression); multiplies accumulated faction bonuses                                                                                                                  |
| `NOBLE_HONOUR`     | Honour     | 1       | affects AI opinions: reduces Nearness (player distance) penalty, increases Betrayal (broken deals) penalty                                                                                                                                                                                                             |
| `NOBLE_MERCY`      | Mercy      | 1       | affects AI post-battle opinions and slave economy: increases Chivarly (chivalrous post-battle actions) bonus and Cruelty (cruel post-battle actions) penalty; increases buy/sell price of slaves and reduces desire to acquire slaves                                                                                  |
| `NOBLE_PRIDE`      | Pride      | 1       | affects AI gift-related opinions and gift size: increases Generosity (receiving gifts) bonus and Extortion (demanding gifts) penalty, as well as expected gift size in diplomatic deals.                                                                                                                               |
| `NOBLE_TOLERANCE`  | Tolerance  | 1       | affects AI race/religion-related opinions and ruler succession: decreases Other Religion penalty and Other Race penalty; increases Kinship (same-race) bonus; reduces Kin Treatment (treatment of their race) bonus/penalty; increases chance for minority race to become ruler                                        |

## PHYSICS
applies to individual citizens/soldiers

| Key                          | Name                 | Default | Description                                                                                                                 |
|------------------------------|----------------------|---------|-----------------------------------------------------------------------------------------------------------------------------|
| `PHYSICS_ACCELERATION`       | Acceleration         | 3       | how fast a subject speeds up                                                                                                |
| `PHYSICS_DEATH_AGE`          | Lifespan             | 100     | max allowed age of a subject                                                                                                |
| `PHYSICS_HEALTH`             | Health               | 1       | likelihood of contracting disease; possibly also affects how much damage a soldier can take before death (confirm in code?) |
| `PHYSICS_MASS`               | Weight               | 80      | relevant for calculating knockback and charge impact in battle                                                              |
| `PHYSICS_RESISTANCE_COLD`    | Cold Resistance      | 0.5     | the ability for a subject to endure cold temperatures                                                                       |
| `PHYSICS_RESISTANCE_HOT`     | Heat Resistance      | 0.5     | the ability for a subject to endure hot temperatures                                                                        |
| `PHYSICS_REPRODUCTION_AGE`   | Reproduction Age     | 0.5     | the part of a subject's life it stays fertile                                                                               |
| `PHYSICS_REPRODUCTION_SPEED` | Natural Births /year | 0.1     | the speed at which a subject propagates naturally                                                                           |
| `PHYSICS_SOILING`            | Soiling              | 0.125   | rate at which subjects become dirty; higher value = faster filth creation                                                   |
| `PHYSICS_SPEED`              | Speed                | 4.5     | measured in tiles per second                                                                                                |
| `PHYSICS_STAMINA`            | Stamina              | 1.0     | how long a subject can walk or run before needing to rest                                                                   |

## SERVICE RATE
the frequency at which a citizen will attempt to find a service, per day

| Key                  | Name           | Default | Description                                  |
|----------------------|----------------|---------|----------------------------------------------|
| `RATES_ARENA`        | Blood lust     | 0.25    | arena usage                                  |
| `RATES_ARENAG`       | Spectacle      | 0.25    | arena usage (non-violent)                    |
| `RATES_BATH`         | Bathing        | 0.25    | bath usage                                   |
| `RATES_CONSTIPATION` | Constipation   | 0.25    | bathroom usage                               |
| `RATES_COURT`        | Justice        | 0.0625  | court usage (new in v71)                     |
| `RATES_DOCTOR`       | Health Care    | 0.1     | hospital usage                               |
| `RATES_GROOMING`     | Vanity         | 0.1     | barber usage                                 |
| `RATES_HEARTH`       | Loneliness     | 0.25    | hearth usage                                 |
| `RATES_HUNGER`       | Hunger         | 0.5     | the rate at which the hunger need increases  |
| `RATES_MASSAGE`      | 'Back Pain'    | 0.0625  | massage parlor usage                         |
| `RATES_SHOPPING`     | Shopping       | 0.15    | market stall usage                           |
| `RATES_SHRINE`       | Piety (Shrine) | 0.25    |                                              |
| `RATES_SKINNYDIP`    | Skinny dip     | 0.1     | time spent swimming in natural water sources |
| `RATES_SPEAKER`      | News Craving   | 0.25    | speaker usage                                |
| `RATES_STAGE`        | Drama          | 0.2     | stage usage                                  |
| `RATES_STOCKS`       | Punishment     | 0.1     | stocks/punishment usage (new in v71)         |
| `RATES_TEMPLE`       | Piety (Temple) | 0.05    |                                              |
| `RATES_THIRST`       | Thirst         | 0.2     | tavern usage                                 |
| `RATES_WELL`         | Dirtiness      | 0.25    | well usage                                   |

## RELIGION
| Key                     | Name       | Default | Description                                                      |
|-------------------------|------------|---------|------------------------------------------------------------------|
| `RELIGION_AMINION_CITY` | Aminionism | 1       | Chance a subject of this race will worship Aminion (Chaos God)   |
| `RELIGION_ATHURI_CITY`  | Athurism   | 1       | Chance a subject of this race will worship Athuri (Good God)     |
| `RELIGION_CRATOR_CITY`  | Cratorism  | 1       | Chance a subject of this race will worship Aminion (Farming God) |
| `RELIGION_SHMALOR_CITY` | Shmalorism | 1       | Chance a subject of this race will worship Shmalor (Minion God)  |

## ROOM
affects production output for rooms with a modifiable resource output amount

### Government
| Key                 | Name            | Default | Description |
|---------------------|-----------------|---------|-------------|
| `ROOM_ADMIN_NORMAL` | Administrations |         |             |
| `ROOM_EMBASSY`      | Embassies       |         |             |

### Technology
| Key                      | Name         | Default | Description |
|--------------------------|--------------|---------|-------------|
| `ROOM_LABORATORY_NORMAL` | Laboratories |         |             |
| `ROOM_LIBRARY_NORMAL`    | Libraries    |         |             |
| `ROOM_SCHOOL_NORMAL`     | Schools      |         |             |

### Military
| Key                       | Name            | Default | Description            |
|---------------------------|-----------------|---------|------------------------|
| `ROOM_ARCHERY_VANILLA`    | Archery Range   |         | affects training speed |
| `ROOM_ARTILLERY_CATAPULT` | Catapults       |         |                        |
| `ROOM_BARRACKS_VANILLA`   | Training Ground |         | affects training speed |

### Farm
| Key                  | Name            | Default | Description |
|----------------------|-----------------|---------|-------------|
| `ROOM_FARM_COTTON`   | Cotton Farms    |         |             |
| `ROOM_FARM_FRUIT`    | Fruit Farms     |         |             |
| `ROOM_FARM_GRAIN`    | Grain Farms     |         |             |
| `ROOM_FARM_HERB`     | Herb Farms      |         |             |
| `ROOM_FARM_MUSHROOM` | Mushroom Farms  |         |             |
| `ROOM_FARM_SPICES`   | Opiate Farms    |         |             |
| `ROOM_FARM_VEG`      | Vegetable Farms |         |             |

### Food Other
| Key                   | Name           | Default | Description |
|-----------------------|----------------|---------|-------------|
| `ROOM_FISHERY_NORMAL` | Fisheries      |         |             |
| `ROOM_HUNTER_NORMAL`  | Hunters        |         |             |
| `ROOM_ORCHARD_FRUIT`  | Fruit Orchards |         |             |

### Mine
| Key                  | Name           | Default | Description |
|----------------------|----------------|---------|-------------|
| `ROOM_MINE_CLAY`     | Claypits       |         |             |
| `ROOM_MINE_COAL`     | Coal Mines     |         |             |
| `ROOM_MINE_GEM`      | Gem Mines      |         |             |
| `ROOM_MINE_ORE`      | Ore Mines      |         |             |
| `ROOM_MINE_SITHILON` | Sithilon Mines |         |             |
| `ROOM_MINE_STONE`    | Stone Mines    |         |             |

### Refiner
| Key                    | Name           | Default | Description |
|------------------------|----------------|---------|-------------|
| `ROOM_REFINER_BAKERY`  | Bakeries       |         |             |
| `ROOM_REFINER_BREWERY` | Breweries      |         |             |
| `ROOM_REFINER_COALER`  | Charcoalers    |         |             |
| `ROOM_REFINER_SMELTER` | Metal Smelters |         |             |
| `ROOM_REFINER_WEAVER`  | Weavers        |         |             |

### Workshop
| Key                       | Name         | Default | Description |
|---------------------------|--------------|---------|-------------|
| `ROOM_WORKSHOP_BOWYER`    | Bowyers      |         |             |
| `ROOM_WORKSHOP_CARPENTER` | Carpenters   |         |             |
| `ROOM_WORKSHOP_JEWELRY`   | Jewellers    |         |             |
| `ROOM_WORKSHOP_MASON`     | Masonries    |         |             |
| `ROOM_WORKSHOP_MECHANIC`  | Mechanics    |         |             |
| `ROOM_WORKSHOP_PAPER`     | Papermakers  |         |             |
| `ROOM_WORKSHOP_POTTERY`   | Potteries    |         |             |
| `ROOM_WORKSHOP_RATION`    | Rationmakers |         |             |
| `ROOM_WORKSHOP_SMITHY`    | Smithies     |         |             |
| `ROOM_WORKSHOP_TAILOR`    | Tailors      |         |             |

### Pasture
possibly only affects resource upkeep instead of production rate

| Key                     | Name                 | Default | Description |
|-------------------------|----------------------|---------|-------------|
| `ROOM_PASTURE_AUR`      | Auroch Pastures      |         |             |
| `ROOM_PASTURE_BALTI`    | Balticrawler Breeder |         |             |
| `ROOM_PASTURE_ENT`      | Entelodont Pastures  |         |             |
| `ROOM_PASTURE_GLOBDIEN` | Globdien Pastures    |         |             |
| `ROOM_PASTURE_MOUNT`    | War-Beast Pastures   |         |             |
| `ROOM_PASTURE_ONX`      | Onx Pastures         |         |             |
### Nursery
affects the room's 'coziness' score, which affects food upkeep; possibly useless as nurseries may be locked to consuming 1 food per day regardless

| Key                     | Name              | Default | Description                                             |
|-------------------------|-------------------|---------|---------------------------------------------------------|
| `ROOM_NURSERY_NORMAL`   | Nurseries         |         | v71 collapsed the per-race nurseries into a single room |
| `ROOM_BREEDER_GARTHIMI` | Garthimi Breeders |         | Garthimi-specific breeder room                          |
### Other
| Key                      | Name           | Default | Description                                                |
|--------------------------|----------------|---------|------------------------------------------------------------|
| `ROOM_STOCKPILE`         | Carry Capacity |         |                                                            |
| `ROOM_UNIVERSITY_NORMAL` | University     |         |                                                            |
| `ROOM_WOODCUTTER`        | Woodcutters    |         |                                                            |

## REGION BUILDING

### Agriculture
| Key                                        | Name           | Default | Description |
|--------------------------------------------|----------------|---------|-------------|
| `WORLD_BUILDING_AGRICULTURE_FARM_COTTON`   | Cotton Farm    |         |             |
| `WORLD_BUILDING_AGRICULTURE_FARM_FRUIT`    | Fruit Farm     |         |             |
| `WORLD_BUILDING_AGRICULTURE_FARM_GRAIN`    | Grain Farm     |         |             |
| `WORLD_BUILDING_AGRICULTURE_FARM_HERB`     | Herb Farm      |         |             |
| `WORLD_BUILDING_AGRICULTURE_FARM_MUSHROOM` | Mushroom Farm  |         |             |
| `WORLD_BUILDING_AGRICULTURE_FARM_SPICES`   | Opiate Farm    |         |             |
| `WORLD_BUILDING_AGRICULTURE_FARM_VEG`      | Vegetable Farm |         |             |
| `WORLD_BUILDING_AGRICULTURE_ORCHARD_FRUIT` | Fruit Orchard  |         |             |

### Civic
| Key                             | Name      | Default | Description |
|---------------------------------|-----------|---------|-------------|
| `WORLD_BUILDING_CIVIC_CENTRE`   | City Hall |         |             |
| `WORLD_BUILDING_CIVIC_GROWTH`   | Growth    |         |             |
| `WORLD_BUILDING_CIVIC_HYGINE`   | Hygiene   |         |             |
| `WORLD_BUILDING_CIVIC_L_ARENA`  | Fight Pit |         |             |
| `WORLD_BUILDING_CIVIC_L_STANDS` | Stands    |         |             |
| `WORLD_BUILDING_CIVIC_L_TAVERN` | Tavern    |         |             |

### Global
| Key                              | Name          | Default | Description |
|----------------------------------|---------------|---------|-------------|
| `WORLD_BUILDING_GLOBAL_ACADEMY`  | School        |         |             |
| `WORLD_BUILDING_GLOBAL_A_POLICE` | Police        |         |             |
| `WORLD_BUILDING_GLOBAL_HYGINE`   | Physician     |         |             |
| `WORLD_BUILDING_GLOBAL_TAX`      | Tax Office    |         |             |
| `WORLD_BUILDING_GLOBAL_WGUILD`   | Workers Guild |         |             |

### Infrastructure
| Key                               | Name       | Default | Description |
|-----------------------------------|------------|---------|-------------|
| `WORLD_BUILDING_INFRA_BANK`       | Bank       |         |             |
| `WORLD_BUILDING_INFRA_GALLOWS`    | Law        |         |             |
| `WORLD_BUILDING_INFRA_GARTHIMI`   | Humidifier |         |             |
| `WORLD_BUILDING_INFRA_HYGINE`     | Quarantine |         |             |
| `WORLD_BUILDING_INFRA_IRRIGATION` | Irrigation |         |             |
| `WORLD_BUILDING_INFRA_MILL`       | Windmill   |         |             |
| `WORLD_BUILDING_INFRA_ROAD`       | Roads      |         |             |

### Military
| Key                                   | Name     | Default | Description |
|---------------------------------------|----------|---------|-------------|
| `WORLD_BUILDING_MILITARY_01_GARRISON` | Garrison |         |             |
| `WORLD_BUILDING_MILITARY_02_BARRACKS` | Barracks |         |             |
| `WORLD_BUILDING_MILITARY_03_WALLS`    | Walls    |         |             |

### Mine
| Key                                 | Name          | Default | Description |
|-------------------------------------|---------------|---------|-------------|
| `WORLD_BUILDING_MINE_MINE_CLAY`     | Claypit       |         |             |
| `WORLD_BUILDING_MINE_MINE_COAL`     | Coal Mine     |         |             |
| `WORLD_BUILDING_MINE_MINE_GEM`      | Gem Mine      |         |             |
| `WORLD_BUILDING_MINE_MINE_ORE`      | Ore Mine      |         |             |
| `WORLD_BUILDING_MINE_MINE_SITHILON` | Sithilon Mine |         |             |
| `WORLD_BUILDING_MINE_MINE_STONE`    | Stone Mine    |         |             |

### Other
| Key                                     | Name       | Default | Description |
|-----------------------------------------|------------|---------|-------------|
| `WORLD_BUILDING_MINE_WOODCUTTER`        | Woodcutter |         |             |
| `WORLD_BUILDING_PASTURE_FISHERY_NORMAL` | Fishery    |         |             |
### Pasture
| Key                                       | Name                 | Default | Description |
|-------------------------------------------|----------------------|---------|-------------|
| `WORLD_BUILDING_PASTURE_PASTURE_AUR`      | Auroch Pasture       |         |             |
| `WORLD_BUILDING_PASTURE_PASTURE_BALTI`    | Balticrawler Breeder |         |             |
| `WORLD_BUILDING_PASTURE_PASTURE_ENT`      | Entelodont Pasture   |         |             |
| `WORLD_BUILDING_PASTURE_PASTURE_GLOBDIEN` | Globdien Pasture     |         |             |
| `WORLD_BUILDING_PASTURE_PASTURE_MOUNT`    | War-Beast Pasture    |         |             |
| `WORLD_BUILDING_PASTURE_PASTURE_ONX`      | Onx Pasture          |         |             |

### Religion
| Key                                      | Name              | Default | Description                                                            |
|------------------------------------------|-------------------|---------|------------------------------------------------------------------------|
| `WORLD_BUILDING_RELIGION_TEMPLE_AMINION` | Shrine to Aminion |         | generated from the temple/shrine of each religion (key = religion key) |
| `WORLD_BUILDING_RELIGION_TEMPLE_ATHURI`  | Shrine to Athuri  |         |                                                                        |
| `WORLD_BUILDING_RELIGION_TEMPLE_CRATOR`  | Shrine to Crator  |         |                                                                        |
| `WORLD_BUILDING_RELIGION_TEMPLE_SHMALOR` | Shrine to Shmalor |         |                                                                        |

## REGION PROPERTIES

### Military
| Key                          | Name           | Default | Description |
|------------------------------|----------------|---------|-------------|
| `WORLD_CONSCRIPTABLE_TARGET` | Conscripts     |         |             |
| `WORLD_FORTIFICATION`        | Fortifications |         |             |
| `WORLD_GARRISON`             | garrison       |         |             |

### Religion
| Key                        | Name       | Default | Description |
|----------------------------|------------|---------|-------------|
| `WORLD_CONVERSION_AMINION` | Aminionism |         |             |
| `WORLD_CONVERSION_ATHURI`  | Athurism   |         |             |
| `WORLD_CONVERSION_CRATOR`  | Cratorism  |         |             |
| `WORLD_CONVERSION_SHMALOR` | Shmalorism |         |             |

### Captives
Per-region slave/captive output target, one key per race (`SLAVE_PRODUCTION_` + race key). Distinct from the daily/yearly `WORLD_PRODUCTION_SLAVE_<race>` production boostables.

| Key                          | Name                 | Default | Description |
|------------------------------|----------------------|---------|-------------|
| `SLAVE_PRODUCTION_ARGONOSH`  | Captives: Argonosh   |         |             |
| `SLAVE_PRODUCTION_CANTOR`    | Captives: Cantors    |         |             |
| `SLAVE_PRODUCTION_CRETONIAN` | Captives: Cretonians |         |             |
| `SLAVE_PRODUCTION_DONDORIAN` | Captives: Dondorians |         |             |
| `SLAVE_PRODUCTION_GARTHIMI`  | Captives: Garthimis  |         |             |
| `SLAVE_PRODUCTION_HUMAN`     | Captives: Humans     |         |             |
| `SLAVE_PRODUCTION_Q_AMEVIA`  | Captives: Amevias    |         |             |
| `SLAVE_PRODUCTION_TILAPI`    | Captives: Tilapis    |         |             |

### Loyalty
| Key                       | Name                | Default | Description |
|---------------------------|---------------------|---------|-------------|
| `WORLD_LOYALTY_CRETONIAN` | Loyalty: Cretonians |         |             |
| `WORLD_LOYALTY_DONDORIAN` | Loyalty: Dondorians |         |             |
| `WORLD_LOYALTY_GARTHIMI`  | Loyalty: Garthimis  |         |             |
| `WORLD_LOYALTY_HUMAN`     | Loyalty: Humans     |         |             |
| `WORLD_LOYALTY_Q_AMEVIA`  | Loyalty: Amevias    |         |             |
| `WORLD_LOYALTY_TILAPI`    | Loyalty: Tilapis    |         |             |

### Other
| Key                                  | Name             | Default | Description                                                                                                  |
|--------------------------------------|------------------|---------|--------------------------------------------------------------------------------------------------------------|
| `WORLD_POINT_WORKFORCE`              | Workforce        |         | available workforce points in owned regions; used to construct and run buildings, excess used for industries |
| `WORLD_TAX_INCOME`                   | Taxes            |         | tax income from owned regions                                                                                |
| `WORLD_VISUAL_MINE`                  |                  |         | unknown (visual cache)                                                                                       |
| `WORLD_VISUAL_ROADS`                 |                  |         | unknown (visual cache)                                                                                       |
| `WORLD_VISUAL_WALL`                  |                  |         | unknown (visual cache)                                                                                       |
| `WORLD_FULFILLMENT_EXPONENT_CITIZEN` |                  |         | per-class fulfillment/happiness exponent (citizens); replaces v70's single `WORLD_FULFILLMENT_EXPONENT`      |
| `WORLD_FULFILLMENT_EXPONENT_SLAVE`   |                  |         | per-class fulfillment/happiness exponent (slaves)                                                            |
| `WORLD_PROXIMITY`                    | Proximity        |         | physical distance from a region to your capital; affects tribute and loyalty                                 |
| `WORLD_PROXIMITY_TOLL`               | Proximity (Toll) |         | proximity factor applied to tolls/trade                                                                      |
| `WORLD_HEALTH`                       | Health           |         | affects likelihood of disease outbreaks/spread in a region                                                   |

### Population
| Key                                 | Name                    | Default | Description                                                                                                           |
|-------------------------------------|-------------------------|---------|-----------------------------------------------------------------------------------------------------------------------|
| `WORLD_MAX_CITY_POPCITIZEN`         |                         |         | per-class max city pop (citizens); note: no underscore between `POP` and class key; replaces v70 `WORLD_MAX_CITY_POP` |
| `WORLD_MAX_CITY_POPSLAVE`           |                         |         | per-class max city pop (slaves)                                                                                       |
| `WORLD_POPULATION_CAPACITY`         | Region Capacity         |         |                                                                                                                       |
| `WORLD_POPULATION_GROWTH_CRETONIAN` | Growth: Cretonians      |         |                                                                                                                       |
| `WORLD_POPULATION_GROWTH_DONDORIAN` | Growth: Dondorians      |         |                                                                                                                       |
| `WORLD_POPULATION_GROWTH_GARTHIMI`  | Growth: Garthimis       |         |                                                                                                                       |
| `WORLD_POPULATION_GROWTH_HUMAN`     | Growth: Humans          |         |                                                                                                                       |
| `WORLD_POPULATION_GROWTH_Q_AMEVIA`  | Growth: Amevias         |         |                                                                                                                       |
| `WORLD_POPULATION_GROWTH_TILAPI`    | Growth: Tilapis         |         |                                                                                                                       |
| `WORLD_POPULATION_TARGET_CRETONIAN` | Pop. Target: Cretonians |         |                                                                                                                       |
| `WORLD_POPULATION_TARGET_DONDORIAN` | Pop. Target: Dondorians |         |                                                                                                                       |
| `WORLD_POPULATION_TARGET_GARTHIMI`  | Pop. Target: Garthimis  |         |                                                                                                                       |
| `WORLD_POPULATION_TARGET_HUMAN`     | Pop. Target: Humans     |         |                                                                                                                       |
| `WORLD_POPULATION_TARGET_Q_AMEVIA`  | Pop. Target: Amevias    |         |                                                                                                                       |
| `WORLD_POPULATION_TARGET_TILAPI`    | Pop. Target: Tilapis    |         |                                                                                                                       |

### Resource Production
v71 renamed these from `WORLD_RESOURCE_PRODUCTION_<res>` to `WORLD_PRODUCTION_<tradable>`, where `<tradable>` is the trade key from `init/trade/TR.java`: `RES_<res>` (raw resource key with any leading underscore stripped, e.g. `_LIVESTOCK`→`RES_LIVESTOCK`, `_STONE`→`RES_STONE`, `_WOOD`→`RES_WOOD`) **and** `SLAVE_<race>` (one per race, slaves are tradables too). So there are two key shapes: `WORLD_PRODUCTION_RES_<res>` and `WORLD_PRODUCTION_SLAVE_<race>`. This is the per-region daily production amount; each also has a `_YEARLY` variant (see next section). Verified against the runtime boost-key registry dump.

| Key                                   | Name                        | Default | Description                                                                     |
|---------------------------------------|-----------------------------|---------|---------------------------------------------------------------------------------|
| `WORLD_PRODUCTION_RES_ALCO_BEER`      | Production: Piva            |         |                                                                                 |
| `WORLD_PRODUCTION_RES_ALCO_WINE`      | Production: Shedeh          |         |                                                                                 |
| `WORLD_PRODUCTION_RES_ARMOUR_LEATHER` | Production: Leather Armours |         |                                                                                 |
| `WORLD_PRODUCTION_RES_ARMOUR_PLATE`   | Production: Plate Armours   |         |                                                                                 |
| `WORLD_PRODUCTION_RES_BOW`            | Production: Bows            |         |                                                                                 |
| `WORLD_PRODUCTION_RES_BREAD`          | Production: Bread           |         |                                                                                 |
| `WORLD_PRODUCTION_RES_CLAY`           | Production: Clay            |         |                                                                                 |
| `WORLD_PRODUCTION_RES_CLOTHES`        | Production: Clothes         |         |                                                                                 |
| `WORLD_PRODUCTION_RES_COAL`           | Production: Coal            |         |                                                                                 |
| `WORLD_PRODUCTION_RES_COTTON`         | Production: Fibre           |         |                                                                                 |
| `WORLD_PRODUCTION_RES_EGG`            | Production: Eggs            |         |                                                                                 |
| `WORLD_PRODUCTION_RES_FABRIC`         | Production: Fabric          |         |                                                                                 |
| `WORLD_PRODUCTION_RES_FISH`           | Production: Fish            |         |                                                                                 |
| `WORLD_PRODUCTION_RES_FRUIT`          | Production: Fruit           |         |                                                                                 |
| `WORLD_PRODUCTION_RES_FURNITURE`      | Production: Furniture       |         |                                                                                 |
| `WORLD_PRODUCTION_RES_GEM`            | Production: Gems            |         |                                                                                 |
| `WORLD_PRODUCTION_RES_GRAIN`          | Production: Grain           |         |                                                                                 |
| `WORLD_PRODUCTION_RES_HERB`           | Production: Herbs           |         |                                                                                 |
| `WORLD_PRODUCTION_RES_JEWELRY`        | Production: Jewelry         |         |                                                                                 |
| `WORLD_PRODUCTION_RES_LEATHER`        | Production: Leather         |         |                                                                                 |
| `WORLD_PRODUCTION_RES_MACHINERY`      | Production: Machinery       |         |                                                                                 |
| `WORLD_PRODUCTION_RES_MEAT`           | Production: Meat            |         |                                                                                 |
| `WORLD_PRODUCTION_RES_METAL`          | Production: Metal           |         |                                                                                 |
| `WORLD_PRODUCTION_RES_MUSHROOM`       | Production: Mushrooms       |         |                                                                                 |
| `WORLD_PRODUCTION_RES_OPIATES`        | Production: Opiates         |         |                                                                                 |
| `WORLD_PRODUCTION_RES_ORE`            | Production: Ore             |         |                                                                                 |
| `WORLD_PRODUCTION_RES_PAPER`          | Production: Paper           |         |                                                                                 |
| `WORLD_PRODUCTION_RES_POTTERY`        | Production: Pottery         |         |                                                                                 |
| `WORLD_PRODUCTION_RES_RATION`         | Production: Rations         |         |                                                                                 |
| `WORLD_PRODUCTION_RES_SITHILON`       | Production: Sithilon ores   |         |                                                                                 |
| `WORLD_PRODUCTION_RES_STONE_CUT`      | Production: Cut Stone       |         |                                                                                 |
| `WORLD_PRODUCTION_RES_TOOL`           | Production: Tools           |         |                                                                                 |
| `WORLD_PRODUCTION_RES_VEGETABLE`      | Production: Vegetables      |         |                                                                                 |
| `WORLD_PRODUCTION_RES_WEAPON_HAMMER`  | Production: Warhammers      |         |                                                                                 |
| `WORLD_PRODUCTION_RES_WEAPON_MOUNT`   | Production: War-Beast       |         |                                                                                 |
| `WORLD_PRODUCTION_RES_WEAPON_SHIELD`  | Production: Shields         |         |                                                                                 |
| `WORLD_PRODUCTION_RES_WEAPON_SHORT`   | Production: Falcatas        |         |                                                                                 |
| `WORLD_PRODUCTION_RES_WEAPON_SLASH`   | Production: Flanxes         |         |                                                                                 |
| `WORLD_PRODUCTION_RES_WEAPON_SPEAR`   | Production: Spears          |         |                                                                                 |
| `WORLD_PRODUCTION_RES_LIVESTOCK`      | Production: Livestock       |         |                                                                                 |
| `WORLD_PRODUCTION_RES_STONE`          | Production: Stone           |         |                                                                                 |
| `WORLD_PRODUCTION_RES_WOOD`           | Production: Wood            |         |                                                                                 |
| `WORLD_PRODUCTION_SLAVE_ARGONOSH`     | Production: Argonosh        |         | per-region daily slave (captive) output; one key per race (`SLAVE_` + race key) |
| `WORLD_PRODUCTION_SLAVE_CANTOR`       | Production: Cantors         |         |                                                                                 |
| `WORLD_PRODUCTION_SLAVE_CRETONIAN`    | Production: Cretonians      |         |                                                                                 |
| `WORLD_PRODUCTION_SLAVE_DONDORIAN`    | Production: Dondorians      |         |                                                                                 |
| `WORLD_PRODUCTION_SLAVE_GARTHIMI`     | Production: Garthimis       |         |                                                                                 |
| `WORLD_PRODUCTION_SLAVE_HUMAN`        | Production: Humans          |         |                                                                                 |
| `WORLD_PRODUCTION_SLAVE_Q_AMEVIA`     | Production: Amevias         |         |                                                                                 |
| `WORLD_PRODUCTION_SLAVE_TILAPI`       | Production: Tilapis         |         |                                                                                 |

### Resource Production (Yearly)
v71 renamed these from `WORLD_WORLD_RESOURCE_PRODUCTION_<res>_YEARLY` to `WORLD_PRODUCTION_RES_<res>_YEARLY`. This is the yearly-accumulated delivery portion (seasonal/once-a-year output, e.g. farm harvests and tribute) as opposed to the daily `WORLD_PRODUCTION_RES_<res>`.

| Key                                          | Name                        | Default | Description |
|----------------------------------------------|-----------------------------|---------|-------------|
| `WORLD_PRODUCTION_RES_ALCO_BEER_YEARLY`      | Production: Piva            |         |             |
| `WORLD_PRODUCTION_RES_ALCO_WINE_YEARLY`      | Production: Shedeh          |         |             |
| `WORLD_PRODUCTION_RES_ARMOUR_LEATHER_YEARLY` | Production: Leather Armours |         |             |
| `WORLD_PRODUCTION_RES_ARMOUR_PLATE_YEARLY`   | Production: Plate Armours   |         |             |
| `WORLD_PRODUCTION_RES_BOW_YEARLY`            | Production: Bows            |         |             |
| `WORLD_PRODUCTION_RES_BREAD_YEARLY`          | Production: Bread           |         |             |
| `WORLD_PRODUCTION_RES_CLAY_YEARLY`           | Production: Clay            |         |             |
| `WORLD_PRODUCTION_RES_CLOTHES_YEARLY`        | Production: Clothes         |         |             |
| `WORLD_PRODUCTION_RES_COAL_YEARLY`           | Production: Coal            |         |             |
| `WORLD_PRODUCTION_RES_COTTON_YEARLY`         | Production: Fibre           |         |             |
| `WORLD_PRODUCTION_RES_EGG_YEARLY`            | Production: Eggs            |         |             |
| `WORLD_PRODUCTION_RES_FABRIC_YEARLY`         | Production: Fabric          |         |             |
| `WORLD_PRODUCTION_RES_FISH_YEARLY`           | Production: Fish            |         |             |
| `WORLD_PRODUCTION_RES_FRUIT_YEARLY`          | Production: Fruit           |         |             |
| `WORLD_PRODUCTION_RES_FURNITURE_YEARLY`      | Production: Furniture       |         |             |
| `WORLD_PRODUCTION_RES_GEM_YEARLY`            | Production: Gems            |         |             |
| `WORLD_PRODUCTION_RES_GRAIN_YEARLY`          | Production: Grain           |         |             |
| `WORLD_PRODUCTION_RES_HERB_YEARLY`           | Production: Herbs           |         |             |
| `WORLD_PRODUCTION_RES_JEWELRY_YEARLY`        | Production: Jewelry         |         |             |
| `WORLD_PRODUCTION_RES_LEATHER_YEARLY`        | Production: Leather         |         |             |
| `WORLD_PRODUCTION_RES_MACHINERY_YEARLY`      | Production: Machinery       |         |             |
| `WORLD_PRODUCTION_RES_MEAT_YEARLY`           | Production: Meat            |         |             |
| `WORLD_PRODUCTION_RES_METAL_YEARLY`          | Production: Metal           |         |             |
| `WORLD_PRODUCTION_RES_MUSHROOM_YEARLY`       | Production: Mushrooms       |         |             |
| `WORLD_PRODUCTION_RES_OPIATES_YEARLY`        | Production: Opiates         |         |             |
| `WORLD_PRODUCTION_RES_ORE_YEARLY`            | Production: Ore             |         |             |
| `WORLD_PRODUCTION_RES_PAPER_YEARLY`          | Production: Paper           |         |             |
| `WORLD_PRODUCTION_RES_POTTERY_YEARLY`        | Production: Pottery         |         |             |
| `WORLD_PRODUCTION_RES_RATION_YEARLY`         | Production: Rations         |         |             |
| `WORLD_PRODUCTION_RES_SITHILON_YEARLY`       | Production: Sithilon ores   |         |             |
| `WORLD_PRODUCTION_RES_STONE_CUT_YEARLY`      | Production: Cut Stone       |         |             |
| `WORLD_PRODUCTION_RES_TOOL_YEARLY`           | Production: Tools           |         |             |
| `WORLD_PRODUCTION_RES_VEGETABLE_YEARLY`      | Production: Vegetables      |         |             |
| `WORLD_PRODUCTION_RES_WEAPON_HAMMER_YEARLY`  | Production: Warhammers      |         |             |
| `WORLD_PRODUCTION_RES_WEAPON_MOUNT_YEARLY`   | Production: War-Beast       |         |             |
| `WORLD_PRODUCTION_RES_WEAPON_SHIELD_YEARLY`  | Production: Shields         |         |             |
| `WORLD_PRODUCTION_RES_WEAPON_SHORT_YEARLY`   | Production: Falcatas        |         |             |
| `WORLD_PRODUCTION_RES_WEAPON_SLASH_YEARLY`   | Production: Flanxes         |         |             |
| `WORLD_PRODUCTION_RES_WEAPON_SPEAR_YEARLY`   | Production: Spears          |         |             |
| `WORLD_PRODUCTION_RES_LIVESTOCK_YEARLY`      | Production: Livestock       |         |             |
| `WORLD_PRODUCTION_RES_STONE_YEARLY`          | Production: Stone           |         |             |
| `WORLD_PRODUCTION_RES_WOOD_YEARLY`           | Production: Wood            |         |             |
| `WORLD_PRODUCTION_SLAVE_ARGONOSH_YEARLY`     | Production: Argonosh        |         |             |
| `WORLD_PRODUCTION_SLAVE_CANTOR_YEARLY`       | Production: Cantors         |         |             |
| `WORLD_PRODUCTION_SLAVE_CRETONIAN_YEARLY`    | Production: Cretonians      |         |             |
| `WORLD_PRODUCTION_SLAVE_DONDORIAN_YEARLY`    | Production: Dondorians      |         |             |
| `WORLD_PRODUCTION_SLAVE_GARTHIMI_YEARLY`     | Production: Garthimis       |         |             |
| `WORLD_PRODUCTION_SLAVE_HUMAN_YEARLY`        | Production: Humans          |         |             |
| `WORLD_PRODUCTION_SLAVE_Q_AMEVIA_YEARLY`     | Production: Amevias         |         |             |
| `WORLD_PRODUCTION_SLAVE_TILAPI_YEARLY`       | Production: Tilapis         |         |             |
| `WORLD_TAX_INCOME_YEARLY`                    | Taxes                       |         |             |

<!-- v71.19 update: race-derived region keys (loyalty/growth/target) removed AMEVIA→Q_AMEVIA. CON_<res> removed → per-room ROOM_CONSUMPTION_<room>[_i]. FULLY reconciled against the runtime boost-key registry dump (402 keys). Production keys use TR.java tradable keys: WORLD_PRODUCTION_RES_<res> (leading underscore stripped) + WORLD_PRODUCTION_SLAVE_<race>, both with _YEARLY variants. Added SLAVE_PRODUCTION_<race> (Captives) region family. WORLD_BUILDING_RELIGION_TEMPLE_<rel> retained. Per-race nurseries→ROOM_NURSERY_NORMAL + ROOM_BREEDER_GARTHIMI. Added ROOM_SCHOOL_NORMAL. CON_<res> removed → per-room ROOM_CONSUMPTION_<room> + per-recipe ROOM_CONSUMPTION_<room>_<i> (full list) + ROOM_CONSUMPTION__EMBASSY. CIVIC dropped PASIFISM/TRADE_FEE, added ADMIN/TRUST/EDUCATION_LIMIT_0/1. PHYSICS added REPRODUCTION_AGE/SPEED (RESISTANCE_HOT 0.8→0.5, SOILING 0.125). BEHAVIOUR added HAPPINESS_SLAVES. SERVICE RATE added RATES_COURT/RATES_STOCKS. WORLD_FULFILLMENT_EXPONENT & WORLD_MAX_CITY_POP became per-HCLASS (CITIZEN/SLAVE). Added WORLD_PROXIMITY_TOLL. BATTLE_BLUNT_ATTACK default 50→40. -->
