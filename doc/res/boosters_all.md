# All available vanilla game boosters ```v70```

:heart: Made by [Glorfy](https://github.com/hereto4) from the SoS Discord community.

- Note 1: 'Default' values are based on v68 and may have changed in v70.
- Note 2: All keys use their British spelling ('Honour', 'Behaviour', etc., except for 'Jewelry' which uses its American spelling), and some keys are intentionally misspelled (this will be noted next to the key).

<!-- TOC -->
* [All available vanilla game boosters ```v70```](#all-available-vanilla-game-boosters-v70)
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
    * [Loyalty](#loyalty)
    * [Other](#other-2)
    * [Population](#population)
    * [Resource Production](#resource-production)
    * [Resource Production (Yearly)](#resource-production-yearly)
<!-- TOC -->

### Table Format
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
- Note: See [Combat Mechanics](doc/explanation/combat_mechanics.md) for details on the exact breakdowns
| Key                      | Name      | Default | Description                                                                  |
|--------------------------|-----------|---------|------------------------------------------------------------------------------|
| `BATTLE_BLOCK`           | Block     | 1       | ability to block, reduces damage using block armour                          |
| `BATTLE_OFFENCE_SKILL`   | Offence   | 1       | ability to attack successfully                                               |
| `BATTLE_DEFENCE_SKILL`   | Defence   | 1       | chance to dodge, reduced effectiveness for non-frontal attacks               |
| `BATTLE_FORMATION_SKILL` | Formation | 0       | bonus defensive skill when attacked in formation from the front              |
| `BATTLE_RANGED_BOW`      | Bow Skill | 0.1     | proficiency with bows, fraction of progress from min to max stats of the bow |
| `BATTLE_CHARGE`          | Charge    | 1       | adds extra damage to charge attacks                                          |
| `BATTLE_DEXTERITY`       | Dexterity | 5       | chance to bypass block and chance to land charge attacks                     |
| `BATTLE_MORALE`          | Morale    | 4       | ability to stay in the fight                                                 |

### Damage Type: Force
| Key                        | Name             | Default | Description                                                                         |
|----------------------------|------------------|---------|-------------------------------------------------------------------------------------|
| `BATTLE_BLUNT_ATTACK`      | Force            | 50      | base damage applied by all attacks; multiplies other damage types; causes knockback |
| `BATTLE_BLUNT_DEFENCE`     | Force Absorption | 40      | reduction of force damage                                                           |
| `BATTLE_BLUNT_DEFENCE_DIR` | Force Block      | 1       | called 'Parry' in-game; amount of force damage reduced upon successful block        |

### Damage Type: Pierce
| Key                         | Name          | Default | Description                                                                   |
|-----------------------------|---------------|---------|-------------------------------------------------------------------------------|
| `BATTLE_PIERCE_ATTACK`      | Pierce Damage | 0       | type of damage applied by some attacks; typically better against armor than slash|
| `BATTLE_PIERCE_DEFENCE`     | Pierce Armour | 0       | reduction of pierce damage                                                    |
| `BATTLE_PIERCE_DEFENCE_DIR` | Pierce Block  | 0       | called 'Parry' in-game; amount of pierce damage reduced upon successful block |

### Damage Type: Slash
| Key                        | Name         | Default | Description                                                                  |
|----------------------------|--------------|---------|------------------------------------------------------------------------------|
| `BATTLE_SLASH_ATTACK`      | Slash Damage | 0       | type of damage applied by some attacks; typically higher than pierce         |
| `BATTLE_SLASH_DEFENCE`     | Slash Armour | 0       | reduction of slash damage                                                   |
| `BATTLE_SLASH_DEFENCE_DIR` | Slash Block  | 0       | called 'Parry' in-game; amount of slash damage reduced upon successful block |

## BEHAVIOUR
| Key                    | Name       | Default | Description                                                     |
|------------------------|------------|---------|-----------------------------------------------------------------|
| `BEHAVIOUR_HAPPINESS`  | Happiness  | 1       | affects final Happiness value after all other factors are applied|
| `BEHAVIOUR_LAWFULNESS` | Lawfulness | 1       | affects lawfulness; chance to commit crimes                     |
| `BEHAVIOUR_LOYALTY`    | Loyalty    | 1       | affects final Loyalty value after all other factors are applied |
| `BEHAVIOUR_SANITY`     | Sanity     | 1       | affects chance to go insane                                     |
| `BEHAVIOUR_SUBMISSION` | Submission | 1       | affects chance for slaves to revolt                             |

## CIVIC
- Note: despite the names being negative, almost all boostables have a positive effect when going up. 
| Key                      | Name              | Default | Description                                                                                                                                          |
|--------------------------|-------------------|---------|------------------------------------------------------------------------------------------------------------------------------------------------------|
| `CIVIC_ACCIDENT`         | Safety            | 1       | higher value = less accidents                                                                                                                        |
| `CIVIC_DEFLATION`        | Deflation         | 1       | higher value = less inflation                                                                                                                        |
| `CIVIC_DIPLOMACY`        | Emissary Points   | 0       | affects amount of emissary points                                                                                                                    |
| `CIVIC_FURNITURE`        | Furnishing        | 1       | furniture upkeep rate for citizen housing                                                                                                            |
| `CIVIC_GOV`              | Government Points | 5       | affects amount of gov points                                                                                                                         |
| `CIVIC_IMMIGRATION`      | Immigration Rate  | 1       | the immigration rate of this race to your city                                                                                                       |
| `CIVIC_INNOVATION`       | Innovation Points | 0       | affects amount of innovation                                                                                                                         |
| `CIVIC_KNOWLEDGE`        | Knowledge Points  | 0       | affects amount of knowledge                                                                                                                          |
| `CIVIC_LANDING`          | Settle            | 0       | affects starting population and resources upon placing your throne for the first time                                                                |
| `CIVIC_LAW`              | Law               | 0       | affects final effectiveness of arrest rates and punishments                                                                                          |
| `CIVIC_MAINTENANCE`      | Robustness        | 1       | higher value = less room maintenance                                                                                                                 |
| `CIVIC_NOBLES_MAX`       | Nobilities        | 0       | max number of nobles allowed                                                                                                                         |
| `CIVIC_NOBLES_RANKS_MAX` | Noble Promotions  | 0       | max number of ranks for nobles                                                                                                                       |
| `CIVIC_OPINION`          | Opinion           | 1.5     | base opinion modifier of foreign rulers towards you                                                                                                  |
| `CIVIC_PASIFISM`         | Pacifism          | 1       | makes foreign rulers want to attack you less / reduces your perceived threat level to rulers (not raiders)                                           |
| `CIVIC_RAIDING`          | Raid Security     | 1       | affects raider desire to demand/attack you; higher value = lower raider desire                                                                       |
| `CIVIC_SPOILAGE`         | Conservation      | 1       | affects stored resource decay; higher value = slower decay rate                                                                                      |
| `CIVIC_TRADE_FEE`        | Trade Tariff      | 1       | incorrectly labelled 'Pacifism' in game tooltips and Dic.txt; reduces fee when trading with other rulers; higher value = higher discount (lower fee) |

## CONSUMPTION
- affects consumption rate of resources by all rooms that consume that resource

| Key             | Name      | Default | Description |
|-----------------|-----------|---------|-------------|
| `CON_ALCO_WINE` | Shedeh    |         |             |
| `CON_BREAD`     | Bread     |         |             |
| `CON_CLAY`      | Clay      |         |             |
| `CON_COAL`      | Coal      |         |             |
| `CON_COTTON`    | Fibre     |         |             |
| `CON_FABRIC`    | Fabric    |         |             |
| `CON_FISH`      | Fish      |         |             |
| `CON_FRUIT`     | Fruit     |         |             |
| `CON_FURNITURE` | Furniture |         |             |
| `CON_GEM`       | Gem       |         |             |
| `CON_GRAIN`     | Grain     |         |             |
| `CON_HERB`      | Herb      |         |             |
| `CON_LEATHER`   | Leather   |         |             |
| `CON_MEAT`      | Meat      |         |             |
| `CON_METAL`     | Metal     |         |             |
| `CON_OPIATES`   | Opiate    |         |             |
| `CON_ORE`       | Ore       |         |             |
| `CON_PAPER`     | Paper     |         |             |
| `CON_POTTERY`   | Pottery   |         |             |
| `CON_RATION`    | Ration    |         |             |
| `CON_STONE`     | Stone     |         |             |
| `CON_VEGETABLE` | Vegetable |         |             |
| `CON_WOOD`      | Wood      |         |             |

## TOOL
- maximum number equippable of tools for a specific building
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

| Key                       | Name            | Default | Description                                                                                                                 |
|---------------------------|-----------------|---------|-----------------------------------------------------------------------------------------------------------------------------|
| `PHYSICS_ACCELERATION`    | Acceleration    | 3       | how fast a subject speeds up                                                                                                |
| `PHYSICS_DEATH_AGE`       | Lifespan        | 100     | max allowed age of a subject                                                                                                |
| `PHYSICS_HEALTH`          | Health          | 1       | likelihood of contracting disease and healing rate from injuries                                                            |
| `PHYSICS_MASS`            | Weight          | 80      | relevant for calculating knockback and charge impact in battle                                                              |
| `PHYSICS_RESISTANCE_COLD` | Cold Resistance | 0.5     | reduces cold exposure when indoors                                                                                          |
| `PHYSICS_RESISTANCE_HOT`  | Heat Resistance | 0.5     | reduces heat exposure when indoors                                                                                          |
| `PHYSICS_SOILING`         | Soiling         | 0.125   | rate at which citizens cause filth around them; higher value = faster filth creation                                        |
| `PHYSICS_SPEED`           | Speed           | 4.5     | measured in tiles per second                                                                                                |
| `PHYSICS_STAMINA`         | Stamina         | 1.0     | affects fatigue in battle; how far a subject can move and fight before tiring;                                              |

## SERVICE RATE
the frequency at which a citizen will attempt to find a service, per day

| Key                  | Name           | Default | Description                                  |
|----------------------|----------------|---------|----------------------------------------------|
| `RATES_ARENA`        | Bloodlust      | 0.25    | arena usage                                  |
| `RATES_ARENAG`       | Spectacle      | 0.25    | arena usage (non-violent)                    |
| `RATES_BATH`         | Bathing        | 0.25    | bath usage                                   |
| `RATES_CONSTIPATION` | Constipation   | 0.25    | bathroom usage                               |
| `RATES_DOCTOR`       | Health Care    | 0.1     | hospital usage                               |
| `RATES_GROOMING`     | Vanity         | 0.1     | barber usage                                 |
| `RATES_HEARTH`       | Loneliness     | 0.25    | hearth usage                                 |
| `RATES_HUNGER`       | Hunger         | 0.5     | the rate at which the hunger need increases  |
| `RATES_MASSAGE`      | 'Back Pain'    | 0.063   | massage parlor usage                         |
| `RATES_SHOPPING`     | Shopping       | 0.15    | market stall usage; acquiring furniture      |
| `RATES_SHRINE`       | Piety (Shrine) | 0.25    |                                              |
| `RATES_SKINNYDIP`    | Skinny-dip     | 0.1     | time spent swimming in natural water sources |
| `RATES_SPEAKER`      | News Craving   | 0.25    | speaker usage                                |
| `RATES_STAGE`        | Drama          | 0.2     | stage usage                                  |
| `RATES_TEMPLE`       | Piety (Temple) | 0.05    |                                              |
| `RATES_THIRST`       | Thirst         | 0.2     | thirst rate                                  |
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

| Key                      | Name                | Default | Description |
|--------------------------|---------------------|---------|-------------|
| `ROOM_NURSERY_AMEVIA`    | Amevian Hatcheries  |         |             |
| `ROOM_NURSERY_ARUAN`     | Aruan Nurseries     |         |             |
| `ROOM_NURSERY_CRETONIAN` | Cretonian Breeders  |         |             |
| `ROOM_NURSERY_GARTHIMI`  | Garthimi Hatcheries |         |             |
| `ROOM_NURSERY_HUMAN`     | Human Nurseries     |         |             |
| `ROOM_NURSERY_TILAPI`    | Tilapi Nurseries    |         |             |
| `ROOM_NURSERY_VARGEN`    | Vargen Nurseries    |         |             |

### Other
| Key                      | Name           | Default | Description |
|--------------------------|----------------|---------|-------------|
| `ROOM_STOCKPILE`         | Carry Capacity |         |             |
| `ROOM_UNIVERSITY_NORMAL` | University     |         |             |
| `ROOM_WOODCUTTER`        | Woodcutters    |         |             |

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
| Key                                      | Name              | Default | Description |
|------------------------------------------|-------------------|---------|-------------|
| `WORLD_BUILDING_RELIGION_TEMPLE_AMINION` | Shrine to Aminion |         |             |
| `WORLD_BUILDING_RELIGION_TEMPLE_ATHURI`  | Shrine to Athuri  |         |             |
| `WORLD_BUILDING_RELIGION_TEMPLE_CRATOR`  | Shrine to Crator  |         |             |
| `WORLD_BUILDING_RELIGION_TEMPLE_SHMALOR` | Shrine to Shmalor |         |             |

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

### Loyalty
| Key                       | Name                | Default | Description |
|---------------------------|---------------------|---------|-------------|
| `WORLD_LOYALTY_ARUAN`     | Loyalty: Aruans     |         |             |
| `WORLD_LOYALTY_CRETONIAN` | Loyalty: Cretonians |         |             |
| `WORLD_LOYALTY_DONDORIAN` | Loyalty: Dondorians |         |             |
| `WORLD_LOYALTY_GARTHIMI`  | Loyalty: Garthimis  |         |             |
| `WORLD_LOYALTY_HUMAN`     | Loyalty: Humans     |         |             |
| `WORLD_LOYALTY_Q_AMEVIA`  | Loyalty: Amevias    |         |             |
| `WORLD_LOYALTY_TILAPI`    | Loyalty: Tilapis    |         |             |

### Other
| Key                          | Name      | Default | Description                                                                                       |
|------------------------------|-----------|---------|---------------------------------------------------------------------------------------------------|
| `WORLD_POINT_WORKFORCE`      | Workforce |         | available workforce points in owned regions                                                       |
| `WORLD_TAX_INCOME`           | Taxes     |         | tax income from owned regions                                                                     |
| `WORLD_VISUAL_MINE`          |           |         | adds cosmetic mines in the region                                                                 |
| `WORLD_VISUAL_ROADS`         |           |         | adds cosmetic roads in the region                                                                 |
| `WORLD_VISUAL_WALL`          |           |         | adds cosmetic walls in the region                                                                 |
| `WORLD_FULFILLMENT_EXPONENT` |           |         | unknown; possibly affects the fulfillment equation but on a region level?                         |
| `WORLD_PROXIMITY`            | Proximity |         | affects proximity penalties on certain region stats and actions; higher value = lower penalties   |
| `WORLD_HEALTH`               | Health    |         | affects likelihood of disease outbreaks/spread in a region                                        |

### Population
| Key                                 | Name                    | Default | Description                |
|-------------------------------------|-------------------------|---------|----------------------------|
| `WORLD_MAX_CITY_POP`                |                         |         | 35,000, used to affect various population mechanics |
| `WORLD_POPULATION_CAPACITY`         | Region Capacity         |         |                            |
| `WORLD_POPULATION_GROWTH_ARUAN`     | Growth: Aruans          |         |                            |
| `WORLD_POPULATION_GROWTH_CRETONIAN` | Growth: Cretonians      |         |                            |
| `WORLD_POPULATION_GROWTH_DONDORIAN` | Growth: Dondorians      |         |                            |
| `WORLD_POPULATION_GROWTH_GARTHIMI`  | Growth: Garthimis       |         |                            |
| `WORLD_POPULATION_GROWTH_HUMAN`     | Growth: Humans          |         |                            |
| `WORLD_POPULATION_GROWTH_Q_AMEVIA`  | Growth: Amevias         |         |                            |
| `WORLD_POPULATION_GROWTH_TILAPI`    | Growth: Tilapis         |         |                            |
| `WORLD_POPULATION_GROWTH_Vargen`    | Growth: Vargen          |         |                            |
| `WORLD_POPULATION_TARGET_ARUAN`     | Pop. Target: Aruans     |         |                            |
| `WORLD_POPULATION_TARGET_CRETONIAN` | Pop. Target: Cretonians |         |                            |
| `WORLD_POPULATION_TARGET_DONDORIAN` | Pop. Target: Dondorians |         |                            |
| `WORLD_POPULATION_TARGET_GARTHIMI`  | Pop. Target: Garthimis  |         |                            |
| `WORLD_POPULATION_TARGET_HUMAN`     | Pop. Target: Humans     |         |                            |
| `WORLD_POPULATION_TARGET_Q_AMEVIA`  | Pop. Target: Amevias    |         |                            |
| `WORLD_POPULATION_TARGET_TILAPI`    | Pop. Target: Tilapis    |         |                            |

### Resource Production
| Key                                        | Name                        | Default | Description |
|--------------------------------------------|-----------------------------|---------|-------------|
| `WORLD_RESOURCE_PRODUCTION_ALCO_BEER`      | Production: Piva            |         |             |
| `WORLD_RESOURCE_PRODUCTION_ALCO_WINE`      | Production: Shedeh          |         |             |
| `WORLD_RESOURCE_PRODUCTION_ARMOUR_LEATHER` | Production: Leather Armours |         |             |
| `WORLD_RESOURCE_PRODUCTION_ARMOUR_PLATE`   | Production: Plate Armours   |         |             |
| `WORLD_RESOURCE_PRODUCTION_BOW`            | Production: Bows            |         |             |
| `WORLD_RESOURCE_PRODUCTION_BREAD`          | Production: Bread           |         |             |
| `WORLD_RESOURCE_PRODUCTION_CLAY`           | Production: Clay            |         |             |
| `WORLD_RESOURCE_PRODUCTION_CLOTHES`        | Production: Clothes         |         |             |
| `WORLD_RESOURCE_PRODUCTION_COAL`           | Production: Coal            |         |             |
| `WORLD_RESOURCE_PRODUCTION_COTTON`         | Production: Fibre           |         |             |
| `WORLD_RESOURCE_PRODUCTION_EGG`            | Production: Eggs            |         |             |
| `WORLD_RESOURCE_PRODUCTION_FABRIC`         | Production: Fabric          |         |             |
| `WORLD_RESOURCE_PRODUCTION_FISH`           | Production: Fish            |         |             |
| `WORLD_RESOURCE_PRODUCTION_FRUIT`          | Production: Fruit           |         |             |
| `WORLD_RESOURCE_PRODUCTION_FURNITURE`      | Production: Furniture       |         |             |
| `WORLD_RESOURCE_PRODUCTION_GEM`            | Production: Gems            |         |             |
| `WORLD_RESOURCE_PRODUCTION_GRAIN`          | Production: Grain           |         |             |
| `WORLD_RESOURCE_PRODUCTION_HERB`           | Production: Herbs           |         |             |
| `WORLD_RESOURCE_PRODUCTION_JEWELRY`        | Production: Jewelry         |         |             |
| `WORLD_RESOURCE_PRODUCTION_LEATHER`        | Production: Leather         |         |             |
| `WORLD_RESOURCE_PRODUCTION_MACHINERY`      | Production: Machinery       |         |             |
| `WORLD_RESOURCE_PRODUCTION_MEAT`           | Production: Meat            |         |             |
| `WORLD_RESOURCE_PRODUCTION_METAL`          | Production: Metal           |         |             |
| `WORLD_RESOURCE_PRODUCTION_MUSHROOM`       | Production: Mushrooms       |         |             |
| `WORLD_RESOURCE_PRODUCTION_OPIATES`        | Production: Opiates         |         |             |
| `WORLD_RESOURCE_PRODUCTION_ORE`            | Production: Ore             |         |             |
| `WORLD_RESOURCE_PRODUCTION_PAPER`          | Production: Paper           |         |             |
| `WORLD_RESOURCE_PRODUCTION_POTTERY`        | Production: Pottery         |         |             |
| `WORLD_RESOURCE_PRODUCTION_RATION`         | Production: Rations         |         |             |
| `WORLD_RESOURCE_PRODUCTION_SITHILON`       | Production: Sithilon ores   |         |             |
| `WORLD_RESOURCE_PRODUCTION_STONE_CUT`      | Production: Cut Stone       |         |             |
| `WORLD_RESOURCE_PRODUCTION_TOOL`           | Production: Tools           |         |             |
| `WORLD_RESOURCE_PRODUCTION_VEGETABLE`      | Production: Vegetables      |         |             |
| `WORLD_RESOURCE_PRODUCTION_WEAPON_HAMMER`  | Production: Warhammers      |         |             |
| `WORLD_RESOURCE_PRODUCTION_WEAPON_MOUNT`   | Production: War-Beast       |         |             |
| `WORLD_RESOURCE_PRODUCTION_WEAPON_SHIELD`  | Production: Shields         |         |             |
| `WORLD_RESOURCE_PRODUCTION_WEAPON_SHORT`   | Production: Falcatas        |         |             |
| `WORLD_RESOURCE_PRODUCTION_WEAPON_SLASH`   | Production: Flanxes         |         |             |
| `WORLD_RESOURCE_PRODUCTION_WEAPON_SPEAR`   | Production: Spears          |         |             |
| `WORLD_RESOURCE_PRODUCTION__LIVESTOCK`     | Production: Livestock       |         |             |
| `WORLD_RESOURCE_PRODUCTION__STONE`         | Production: Stone           |         |             |
| `WORLD_RESOURCE_PRODUCTION__WOOD`          | Production: Wood            |         |             |

### Resource Production (Yearly)
unknown how this differs from non-yearly keys; possibly refers to the yearly tribute vassals give you

| Key                                                     | Name                        | Default | Description |
|---------------------------------------------------------|-----------------------------|---------|-------------|
| `WORLD_WORLD_RESOURCE_PRODUCTION_ALCO_BEER_YEARLY`      | Production: Piva            |         |             |
| `WORLD_WORLD_RESOURCE_PRODUCTION_ALCO_WINE_YEARLY`      | Production: Shedeh          |         |             |
| `WORLD_WORLD_RESOURCE_PRODUCTION_ARMOUR_LEATHER_YEARLY` | Production: Leather Armours |         |             |
| `WORLD_WORLD_RESOURCE_PRODUCTION_ARMOUR_PLATE_YEARLY`   | Production: Plate Armours   |         |             |
| `WORLD_WORLD_RESOURCE_PRODUCTION_BOW_YEARLY`            | Production: Bows            |         |             |
| `WORLD_WORLD_RESOURCE_PRODUCTION_BREAD_YEARLY`          | Production: Bread           |         |             |
| `WORLD_WORLD_RESOURCE_PRODUCTION_CLAY_YEARLY`           | Production: Clay            |         |             |
| `WORLD_WORLD_RESOURCE_PRODUCTION_CLOTHES_YEARLY`        | Production: Clothes         |         |             |
| `WORLD_WORLD_RESOURCE_PRODUCTION_COAL_YEARLY`           | Production: Coal            |         |             |
| `WORLD_WORLD_RESOURCE_PRODUCTION_COTTON_YEARLY`         | Production: Fibre           |         |             |
| `WORLD_WORLD_RESOURCE_PRODUCTION_EGG_YEARLY`            | Production: Eggs            |         |             |
| `WORLD_WORLD_RESOURCE_PRODUCTION_FABRIC_YEARLY`         | Production: Fabric          |         |             |
| `WORLD_WORLD_RESOURCE_PRODUCTION_FISH_YEARLY`           | Production: Fish            |         |             |
| `WORLD_WORLD_RESOURCE_PRODUCTION_FRUIT_YEARLY`          | Production: Fruit           |         |             |
| `WORLD_WORLD_RESOURCE_PRODUCTION_FURNITURE_YEARLY`      | Production: Furniture       |         |             |
| `WORLD_WORLD_RESOURCE_PRODUCTION_GEM_YEARLY`            | Production: Gems            |         |             |
| `WORLD_WORLD_RESOURCE_PRODUCTION_GRAIN_YEARLY`          | Production: Grain           |         |             |
| `WORLD_WORLD_RESOURCE_PRODUCTION_HERB_YEARLY`           | Production: Herbs           |         |             |
| `WORLD_WORLD_RESOURCE_PRODUCTION_JEWELRY_YEARLY`        | Production: Jewelry         |         |             |
| `WORLD_WORLD_RESOURCE_PRODUCTION_LEATHER_YEARLY`        | Production: Leather         |         |             |
| `WORLD_WORLD_RESOURCE_PRODUCTION_MACHINERY_YEARLY`      | Production: Machinery       |         |             |
| `WORLD_WORLD_RESOURCE_PRODUCTION_MEAT_YEARLY`           | Production: Meat            |         |             |
| `WORLD_WORLD_RESOURCE_PRODUCTION_METAL_YEARLY`          | Production: Metal           |         |             |
| `WORLD_WORLD_RESOURCE_PRODUCTION_MUSHROOM_YEARLY`       | Production: Mushrooms       |         |             |
| `WORLD_WORLD_RESOURCE_PRODUCTION_OPIATES_YEARLY`        | Production: Opiates         |         |             |
| `WORLD_WORLD_RESOURCE_PRODUCTION_ORE_YEARLY`            | Production: Ore             |         |             |
| `WORLD_WORLD_RESOURCE_PRODUCTION_PAPER_YEARLY`          | Production: Paper           |         |             |
| `WORLD_WORLD_RESOURCE_PRODUCTION_POTTERY_YEARLY`        | Production: Pottery         |         |             |
| `WORLD_WORLD_RESOURCE_PRODUCTION_RATION_YEARLY`         | Production: Rations         |         |             |
| `WORLD_WORLD_RESOURCE_PRODUCTION_SITHILON_YEARLY`       | Production: Sithilon ores   |         |             |
| `WORLD_WORLD_RESOURCE_PRODUCTION_STONE_CUT_YEARLY`      | Production: Cut Stone       |         |             |
| `WORLD_WORLD_RESOURCE_PRODUCTION_TOOL_YEARLY`           | Production: Tools           |         |             |
| `WORLD_WORLD_RESOURCE_PRODUCTION_VEGETABLE_YEARLY`      | Production: Vegetables      |         |             |
| `WORLD_WORLD_RESOURCE_PRODUCTION_WEAPON_HAMMER_YEARLY`  | Production: Warhammers      |         |             |
| `WORLD_WORLD_RESOURCE_PRODUCTION_WEAPON_MOUNT_YEARLY`   | Production: War-Beast       |         |             |
| `WORLD_WORLD_RESOURCE_PRODUCTION_WEAPON_SHIELD_YEARLY`  | Production: Shields         |         |             |
| `WORLD_WORLD_RESOURCE_PRODUCTION_WEAPON_SHORT_YEARLY`   | Production: Falcatas        |         |             |
| `WORLD_WORLD_RESOURCE_PRODUCTION_WEAPON_SLASH_YEARLY`   | Production: Flanxes         |         |             |
| `WORLD_WORLD_RESOURCE_PRODUCTION_WEAPON_SPEAR_YEARLY`   | Production: Spears          |         |             |
| `WORLD_WORLD_RESOURCE_PRODUCTION__LIVESTOCK_YEARLY`     | Production: Livestock       |         |             |
| `WORLD_WORLD_RESOURCE_PRODUCTION__STONE_YEARLY`         | Production: Stone           |         |             |
| `WORLD_WORLD_RESOURCE_PRODUCTION__WOOD_YEARLY`          | Production: Wood            |         |             |
| `WORLD_WORLD_TAX_INCOME_YEARLY`                         | Taxes                       |         |             |
