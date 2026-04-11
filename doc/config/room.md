# Rooms

`data/assets/init/room`

**Rooms** follow a special semantic.
Each room file starting with an `_` (e.g. `_ASYLUM.txt`) is a "unique" room.
These can't have new implementations without modifying the game code.

All other rooms can get a new implementation with your own configuration.
For example if you made a new weapon "Crossbow", you can have a `WORKSHOP` like `WORKSHOP_BOWYER.txt`,
but you would call it e.g.`WORKSHOP_CROSSBOW.txt` and configure it to produce crossbows instead.

## Overview

* [General](#general)
* [_ASYLUM](#_asylum-room)
* [_BENCH](#_bench-room)
* [_BUILDER](#_builder-room)
* [_CANNIBAL](#_cannibal-room)
* [_COURT](#_court-room)
* [_DUMP_CORPSE](#_dump_corpse-room)
* [_EMBASSY](#_embassy-room)
* [ADMIN](#admin-rooms)
* [ARCHERY](#archery-rooms)
* [ARENAG](#arenag-rooms)
* [ARTILLERY](#artillery-rooms)
* [BARBER](#barber-rooms)
* [BARRACKS](#barracks-rooms)
* [BATH](#bath-rooms)
* [CANTEEN](#canteen-rooms)
* [EATERY](#eatery-rooms)
* [FARM](#farm-rooms)
* [FIGHTPIT](#fightpit-rooms)
* [FISHERY](#fishery-rooms)
* [GATEHOUSE](#gatehouse-rooms)
* [HUNTER](#hunter-rooms)
* [LABORATORY](#laboratory-rooms)
* [LAVATORY](#lavatory-rooms)
* [LIBRARY](#library-rooms)
* [MARKET](#market-rooms)
* [MINE](#mine-rooms)
* [MONUMENT](#mine-rooms)
* [NURSERY](#nursery-rooms)
* [ORCHARD](#orchard-rooms)
* [PASTURE](#pasture-rooms)
* [PHYSICIAN](#physician-rooms)
* [PLEASURE](#pleasure-rooms)
* [POOL](#pool-rooms)
* [REFINER](#refiner-rooms)
* [RESTHOME](#resthome-rooms)
* [SCHOOL](#school-rooms)
* [SHRINE](#shrine-rooms)
* [SPEAKER](#speaker-rooms)
* [STAGE](#stage-rooms)
* [TAVERN](#tavern-rooms)
* [TEMPLE](#temple-rooms)
* [TOMB](#tomb-rooms)
* [UNIVERSITY](#university-rooms)
* [WELL](#well-rooms)
* [WORKSHOP](#workshop-rooms)

## General

For all kinds of rooms.

| Key                       | Required | Default       | Min  | Max    | Description                                                                                                                                                                                                               | Example                                                                                                            |
|---------------------------|----------|---------------|------|--------|---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|--------------------------------------------------------------------------------------------------------------------|
| ICON                      | no       | dummy icon    | none | none   | Path to the sprite tile used for the icon. The path will look into `assets/init/sprite/icon`.<br/>You can either have a single sprite as icon or have a more complex setup (see below).                                   | `32->INFRA->0`<br/>The real path would be:<br/>`assets/init/icon/32/INFRA.png` first (0) tile                      |
| ICON.BG                   | yes      | none          | none | none   | Path to the sprite tile used for the icon background. The path will look into `assets/init/sprite/icon`.                                                                                                                  | `32->BG->3`<br/>The real path would be:<br/>`assets/init/icon/32/BG.png` fourth (3) tile                           |
| ICON.FG                   | yes      | none          | none | none   | Path to the sprite tile used for the icon foreground. The path will look into `assets/init/sprite/icon`.                                                                                                                  | `24->resource->Cotton->0`<br/>The real path would be:<br/>`assets/init/icon/24/resource/Cotton.png` first (0) tile |
| ICON.SHADOW               | no       | 0             | -100 | 100    | How much of a shadow in pixels should be cast inside (negative) or outside (positive) of the icon.                                                                                                                        |                                                                                                                    |
| ICON.OFFX                 | no       | 0             | -100 | 100    | Offset in pixels on the X axis for casting the shadow.                                                                                                                                                                    |                                                                                                                    |
| ICON.OFFY                 | no       | 0             | -100 | 100    | Offset in pixels on the Y axis for casting the shadow.                                                                                                                                                                    |                                                                                                                    |
| TYPE                      | no       | none          | none | none   | Type of the room.                                                                                                                                                                                                         | `ARENA_G`                                                                                                          |
| MINI_COLOR                | no       | 127_127_127   | none | none   | For displaying the room on the minimap and when max zoomed out.                                                                                                                                                           | `255_255_255` or<br/> `{R: 255, G: 255, B: 255, }`                                                                 |
| MINI_COLOR_PATTERN        | no       | blank pattern | none | none   | Custom pattern when displayed in minimap or zoomed out.<br/>It is a list of Strings with numbers or a `-` (transparent) in it.<br/>All Strings must have the same length.<br/>There can be a maximum of 32 Strings in it. | `[<br/>  "44444444",<br/>  "99999999",<br/>  "00000000",<br/>  "44444444",<br/>]`                                  |
| RESOURCES                 | yes      | none          | none | none   | List with resources used for construction. A maximum of 4 resources can be defined.<br/>See file names in `assets/init/resource` and sub folders for possible values.                                                     | `[STONE, WOOD,],`                                                                                                  |
| AREA_COSTS                | yes      | none          | none | none   | List with resource costs per tile for furnishing the room.                                                                                                                                                                | `[1, 1,],`                                                                                                         |
| DEGRADE_RATE              | no       | 0.75          | 0.0  | 1.0    | Multiplier for how fast a room degrades.                                                                                                                                                                                  |                                                                                                                    |
| FLOOR                     | yes      | none          | none | none   | Floor used for this room. Can also be a list of floors.<br/>Possible floor names can be found in `assets/init/settlement/floor`.                                                                                          | `WOOD` or<br/> `[DIRT, WOOD, STONE2,]`                                                                             |
| REQUIRES.\<COMPARATOR\>   | no       | none          | none | none   | See [requires](require.md)                                                                                                                                                                                                | `GREATER: { WORKFORCE: 3500, },`                                                                                   |
| BONUS.CLIMATE.\<CLIMATE\> | no       | none          | 0.0  | 2000.0 | Room bonus multiplier when in a certain climate. <br/>Possible climates can be found in `assets/init/config/CLIMATE.txt`.                                                                                                 | `{ COLD: 0.8, TEMPERATE: 1.0, HOT: 0.0, }`</br>or `{ *: 0.5, }` for all climates                                   |
| ITEMS                     | no       | none          | none | none   | See [items](#items-key)                                                                                                                                                                                                   |                                                                                                                    |
| SPRITES                   | yes      | none          | none | none   | See [SPRITES key](#sprites-key)                                                                                                                                                                                           |                                                                                                                    |

## _ASYLUM room

For the `_ASYLUM` room only.

| Key                      | Required | Default | Min  | Max     | Description                                                                                                                                                  | Example                                                          |
|--------------------------|----------|---------|------|---------|--------------------------------------------------------------------------------------------------------------------------------------------------------------|------------------------------------------------------------------|
| FLOOR2                   | yes      | none    | none | none    | Floor used for the cells. Possible floor names can be found in `assets/init/settlement/floor`.                                                               | `STONE_MEDIUM_DARK`                                              |
| WORK                     | no       | none    | none | none    | See [WORK key](#work-key)                                                                                                                                    |                                                                  |
| INDUSTRY.IN.\<RESOURCE\> | yes      | none    | 0.0  | 10000.0 | Input resource consumed by inmates.<br/>Possible \<RESOURCE\>s can be found in `assets/init/resource`.<br/>See [INDUSTRY key](#industry-and-industries-keys) | See [INDUSTRY and INDUSTRIES key](#industry-and-industries-keys) |

### ITEMS

#### Furniture
1) Cells

#### Stats multipliers per furniture
1) Deranged
2) Wards

## _BENCH room

For the `_BENCH` room only.

| Key      | Required | Default | Min  | Max  | Description                       | Example |
|----------|----------|---------|------|------|-----------------------------------|---------|
| UPGRADES | no       | none    | none | none | See [UPGRADES key](#upgrades-key) |         |

### ITEMS

#### Furniture
1) Bench

#### Stats multipliers per furniture
none

## _BUILDER room

For the `_BUILDER` room only.

| Key                      | Required | Default | Min  | Max     | Description                                                                                                                                                  | Example                                                          |
|--------------------------|----------|---------|------|---------|--------------------------------------------------------------------------------------------------------------------------------------------------------------|------------------------------------------------------------------|
| WORK                     | no       | none    | none | none    | See [WORK key](#work-key)                                                                                                                                    |                                                                  |

### ITEMS

#### Furniture
none

#### Stats multipliers per furniture
none

## _CANNIBAL room

For the `_CANNIBAL` room only.

| Key                      | Required | Default | Min  | Max     | Description                                                                                                                                                  | Example                                                          |
|--------------------------|----------|---------|------|---------|--------------------------------------------------------------------------------------------------------------------------------------------------------------|------------------------------------------------------------------|
| WORK                     | no       | none    | none | none    | See [WORK key](#work-key)                                                                                                                                    |                                                                  |

### ITEMS

#### Furniture
1) Work Bench
2) Auxiliary Station

#### Stats multipliers per furniture
1) Butchers
2) Efficiency

## _COURT room

For the `_COURT` room only.

| Key     | Required | Default | Min  | Max  | Description                     | Example |
|---------|----------|---------|------|------|---------------------------------|---------|
| SERVICE | no       | none    | none | none | See [SERVICE key](#service-key) |         |
| WORK    | no       | none    | none | none | See [WORK key](#work-key)       |         |

### ITEMS

#### Furniture
1) Courts
2) Spectators

#### Stats multipliers per furniture
1) Clients
2) Judges
3) Spectators

## _DUMP_CORPSE room

For the `_DUMP_CORPSE` room only.

| Key     | Required | Default | Min  | Max  | Description                     | Example |
|---------|----------|---------|------|------|---------------------------------|---------|
| SERVICE | no       | none    | none | none | See [SERVICE key](#service-key) |         |

### ITEMS

#### Furniture
none

#### Stats multipliers per furniture
1) Graves

## _EMBASSY room

For the `_EMBASSY` room only.

| Key                            | Required | Default | Min  | Max      | Description                                                                                                                                                                                | Example                                                          |
|--------------------------------|----------|---------|------|----------|--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|------------------------------------------------------------------|
| WORK                           | no       | none    | none | none     | See [WORK key](#work-key)                                                                                                                                                                  |                                                                  |
| CONSUMPTION.\<RESOURCE\>.RATE  | no       | none    | 0.0  | 10000.0  | How much of the \<RESOURCE\> is consumed per performed job.<br/>See file names in `assets/init/resource` and sub folders for possible \<RESOURCE\>s.                                       |                                                                  |
| CONSUMPTION.\<RESOURCE\>.BONUS | no       | none    | 0.0  | 1000.0   | How much bonus to trust generation consuming the \<RESOURCE\> will give.<br/>Bonus will be added.<br/>See file names in `assets/init/resource` and sub folders for possible \<RESOURCE\>s. |                                                                  |
| VALUE_DEGRADE_PER_YEAR         | yes      | none    | 0.0  | 10.0     | How fast trust shall be lost.                                                                                                                                                              |                                                                  |
| VALUE_PER_WORKER               | no       | none    | 0.0  | 100000.0 | How much trust is generated per station.                                                                                                                                                   |                                                                  |
| VALUE_WORK_SPEED               | no       | none    | 0.0  | 1000.0   | How long it takes to perform the job.                                                                                                                                                      |                                                                  |
| INDUSTRY.IN.\<RESOURCE\>       | yes      | none    | 0.0  | 10000.0  | Input resource consumed when working.<br/>Possible \<RESOURCE\>s can be found in `assets/init/resource`.<br/>See [INDUSTRY key](#industry-and-industries-keys)                             | See [INDUSTRY and INDUSTRIES key](#industry-and-industries-keys) |


### ITEMS

#### Furniture
1) Workstation
2) Nicknacks
3) Carpets

#### Stats multipliers per furniture
1) Emissaries
2) Efficiency

## ADMIN rooms

For `ADMIN_` rooms only.

| Key                      | Required | Default | Min        | Max                                                                                             | Description                                                                                                                                                                                                               | Example                                                          |
|--------------------------|----------|---------|------------|-------------------------------------------------------------------------------------------------|---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|------------------------------------------------------------------|
| POP_MIN                  | yes      | none    | 0          | [Integer.MAX_VALUE](https://docs.oracle.com/javase/8/docs/api/java/lang/Integer.html#MAX_VALUE) | Minimum population count for the room to work.                                                                                                                                                                            |                                                                  |
| INCREASE_POW             | yes      | none    | 0.0        | 100.0                                                                                           | How big of an exponential increase of employees is needed for the admin rooms.<br/>The formula is: `((<ALL_EMPLOYEES> - <ROOM_EMPLOYEES>) - (POP_MIN - POP_MIN - (POP_MIN/BOOST_TO ^ 1.0/INCREASE_POW))) ^ INCREASE_POW`. |                                                                  |
| BOOST_FROM               | yes      | none    | 0.0        | 1.0                                                                                             | Minimum boost value used for boosters in `BOOSTING`.                                                                                                                                                                      |                                                                  |
| BOOST_TO                 | yes      | none    | BOOST_FROM | 1000.0                                                                                          | Maximum boost value used for boosters in `BOOSTING`.                                                                                                                                                                      |                                                                  |
| BOOSTING                 | no       | none    | none       | none                                                                                            | A list of booster keys for increasing various things when the admin room is working properly. The boosts will be multiplied.<br/>For possible booster keys see: [all boosters](../res/boosters_all.md)                    | `[ ROOM_FARM*, ROOM_MINE*, WORLD_WORLD_RESOURCE_PRODUCTION_*, ]` |
| WORK                     | no       | none    | none       | none                                                                                            | See [WORK key](#work-key)                                                                                                                                                                                                 |                                                                  |
| INDUSTRY.IN.\<RESOURCE\> | yes      | none    | 0.0        | 10000.0                                                                                         | Input resource consumed when working.<br/>Possible \<RESOURCE\>s can be found in `assets/init/resource`.<br/>See [INDUSTRY key](#industry-and-industries-keys)                                                            | See [INDUSTRY and INDUSTRIES key](#industry-and-industries-keys) |

### ITEMS

#### Furniture
1) Clerk Station
2) Shelf
3) Carpet

#### Stats multipliers per furniture
1) Workers
2) Capacity
3) Efficiency

## ARCHERY rooms

For `ARCHERY_` rooms only.

| Key                            | Required | Default     | Min  | Max                                                                                            | Description                                                                                                         | Example                                                                                            |
|--------------------------------|----------|-------------|------|------------------------------------------------------------------------------------------------|---------------------------------------------------------------------------------------------------------------------|----------------------------------------------------------------------------------------------------|
| DIV_SPRITE.ICON                | no       | dummy icon  | none | none                                                                                           | Path to the sprite tile used for the icon. The path will look into `assets/init/sprite/icon`.                       | `16->Misc->5`<br/>The real path would be:<br/>`assets/init/icon/16/Misc.png` sixth (5) tile        |
| DIV_SPRITE.COLOR               | no       | 127_127_127 | none | none                                                                                           | Will colorize the sprite in given color. Can also be a list of colors. You can either have TINT or COLOR, not both. | `255_255_255` or<br/>`{ R: 255, G: 255, B: 255, }` or as list<br/> `[ 111_111_111, 222_222_222, ]` |
| TRAINING.FULL_TRAINING_IN_DAYS | yes      | none        | 0    | [Integr.MAX_VALUE](https://docs.oracle.com/javase/8/docs/api/java/lang/Integer.html#MAX_VALUE) | Amount of days required to train to 100%                                                                            |                                                                                                    |
| TRAINING.BOOST.\<KEY\>         | yes      | none        | none | none                                                                                           | See [boosts](boost.md)                                                                                              |                                                                                                    |
| EMPLOYMENT                     | no       | none        | none | none                                                                                           | See [EMPLOYMENT key](#employment-key)                                                                               |                                                                                                    |
| ENVIRONMENT_EMIT               | no       | none        | none | none                                                                                           | See [ENVIRONMENT_EMIT key](#environment_emit-key)                                                                   |                                                                                                    |

### ITEMS

#### Furniture
1) Training Targets

#### Stats multipliers per furniture
1) Capacity

## ARENAG rooms

For `ARENAG_` rooms only.

| Key     | Required | Default | Min  | Max  | Description                     | Example |
|---------|----------|---------|------|------|---------------------------------|---------|
| WORK    | no       | none    | none | none | See [WORK key](#work-key)       |         |
| SERVICE | no       | none    | none | none | See [SERVICE key](#service-key) |         |

### ITEMS

#### Furniture
1) Entrance

#### Stats multipliers per furniture
1) Gladiators
2) Spectators

## ARTILLERY rooms

For `ARTILLERY_` rooms only.
Artillery Projectiles have a `FROM` and `TO` section. 
This is for calculating the ranges between the values defined there with some randomness.


| Key                                    | Required | Default                           | Min  | Max        | Description                                                                                                                                          | Example                                            |
|----------------------------------------|----------|-----------------------------------|------|------------|------------------------------------------------------------------------------------------------------------------------------------------------------|----------------------------------------------------|
| PROJECTILE_RESOURCE                    | yes      | none                              | none | none       | Resource used in the catapult.<br/>See file names in `assets/init/resource` and sub folders for possible values.                                     | `STONE`                                            |
| PROJECTILE.COLOR                       | no       | 127_127_127                       | none | none       | For colorizing the projectile.                                                                                                                       | `255_255_255` or<br/> `{R: 255, G: 255, B: 255, }` |
| PROJECTILE.SPRITE_FILE                 | no       | dummy projectile with given COLOR | none | none       | Sprite used for the projectile.<br/>Projectile sprites are read from `assets/sprite/settlement/projectile`.                                          | `_CATAPULT`                                        |
| PROJECTILE.FROM.MASS                   | yes      | none                              | 0.01 | 100000.0   | Weight of the projectile.                                                                                                                            |                                                    |
| PROJECTILE.FROM.TILE_SPEED             | yes      | none                              | 0.5  | 250.0      | Velocity of the projectile.                                                                                                                          |                                                    |
| PROJECTILE.FROM.RELOAD_SECONDS         | yes      | none                              | 0.01 | 10000.0    | Time between shots.                                                                                                                                  |                                                    |
| PROJECTILE.FROM.ACCURACY               | yes      | none                              | 0.01 | 1.0        | Chance to hit the target.                                                                                                                            |                                                    |
| PROJECTILE.FROM.DEXTERITY              | yes      | none                              | 0.0  | 10000000.0 | Chance to break block defense of the target.                                                                                                         |                                                    |
| PROJECTILE.FROM.TILE_RADIUS_DAMAGE     | no       | 0.0                               | 0.0  | 10000.0    | Area of effect for the impact.                                                                                                                       |                                                    |
| PROJECTILE.FROM.MAX_ARCH_ANGLE_DEGREES | yes      | none                              | 0.0  | 75.0       | Maximum launch angle of the projectile.                                                                                                              |                                                    |
| PROJECTILE.FROM.DAMAGE.\<DAMAGE_TYPE\> | yes      | none                              | 0.0  | 100000.0   | Which damage type and how much damage the projectile does on impact.<br/>See file names in `assets/init/stats/damage` for possible \<DAMAGE_TYPE\>s. | `PIERCE: 15`                                       |
| PROJECTILE.TO.MASS                     | yes      | none                              | 0.01 | 100000.0   | Weight of the projectile.                                                                                                                            |                                                    |
| PROJECTILE.TO.TILE_SPEED               | yes      | none                              | 0.5  | 250.0      | Velocity of the projectile.                                                                                                                          |                                                    |
| PROJECTILE.TO.RELOAD_SECONDS           | yes      | none                              | 0.01 | 10000.0    | Time between shots.                                                                                                                                  |                                                    |
| PROJECTILE.TO.ACCURACY                 | yes      | none                              | 0.01 | 1.0        | Chance to hit the target.                                                                                                                            |                                                    |
| PROJECTILE.TO.DEXTERITY                | yes      | none                              | 0.0  | 10000000.0 | Chance to break block defense of the target.                                                                                                         |                                                    |
| PROJECTILE.TO.TILE_RADIUS_DAMAGE       | no       | 0.0                               | 0.0  | 10000.0    | Area of effect for the impact.                                                                                                                       |                                                    |
| PROJECTILE.TO.MAX_ARCH_ANGLE_DEGREES   | yes      | none                              | 0.0  | 75.0       | Maximum launch angle of the projectile.                                                                                                              |                                                    |
| PROJECTILE.TO.DAMAGE.\<DAMAGE_TYPE\>   | yes      | none                              | 0.0  | 100000.0   | Which damage type and how much damage the projectile does on impact.<br/>See file names in `assets/init/stats/damage` for possible \<DAMAGE_TYPE\>s. | `PIERCE: 30`                                       |

### ITEMS

#### Furniture
1) Catapult

#### Stats multipliers per furniture
none

## BARBER rooms

For `BARBER_` rooms only.

| Key               | Required | Default | Min  | Max                                                                                           | Description                                                                           | Example |
|-------------------|----------|---------|------|-----------------------------------------------------------------------------------------------|---------------------------------------------------------------------------------------|---------|
| WORK_TIME_IN_DAYS | yes      | none    | 0.0  | [Double.MAX_VALUE](https://docs.oracle.com/javase/8/docs/api/java/lang/Double.html#MAX_VALUE) | How long it takes to perform the job.<br/>The actual work time will be divided by 16. |         |
| WORK              | no       | none    | none | none                                                                                          | See [WORK key](#work-key)                                                             |         |
| SERVICE           | no       | none    | none | none                                                                                          | See [SERVICE key](#service-key)                                                       |         |

### ITEMS

#### Furniture
1) Booths
2) Carpets

#### Stats multipliers per furniture
1) Services
2) Workers
3) Quality

## BARRACKS rooms

For `BARRACKS_` rooms only.

| Key                            | Required | Default     | Min  | Max                                                                                             | Description                                                                                                         | Example                                                                                            |
|--------------------------------|----------|-------------|------|-------------------------------------------------------------------------------------------------|---------------------------------------------------------------------------------------------------------------------|----------------------------------------------------------------------------------------------------|
| DIV_SPRITE.ICON                | no       | dummy icon  | none | none                                                                                            | Path to the sprite tile used for the icon. The path will look into `assets/init/sprite/icon`.                       | `16->Misc->5`<br/>The real path would be:<br/>`assets/init/icon/16/Misc.png` sixth (5) tile        |
| DIV_SPRITE.COLOR               | no       | 127_127_127 | none | none                                                                                            | Will colorize the sprite in given color. Can also be a list of colors. You can either have TINT or COLOR, not both. | `255_255_255` or<br/>`{ R: 255, G: 255, B: 255, }` or as list<br/> `[ 111_111_111, 222_222_222, ]` |
| TRAINING.FULL_TRAINING_IN_DAYS | yes      | none        | 0    | [Integer.MAX_VALUE](https://docs.oracle.com/javase/8/docs/api/java/lang/Integer.html#MAX_VALUE) | Amount of days required to train to 100%                                                                            |                                                                                                    |
| TRAINING.BOOST.\<KEY\>         | yes      | none        | none | none                                                                                            | See [boosts](boost.md)                                                                                              |                                                                                                    |
| EMPLOYMENT                     | no       | none        | none | none                                                                                            | See [EMPLOYMENT key](#employment-key)                                                                               |                                                                                                    |
| ENVIRONMENT_EMIT               | no       | none        | none | none                                                                                            | See [ENVIRONMENT_EMIT key](#environment_emit-key)                                                                   |                                                                                                    |
| UPGRADES                       | no       | none        | none | none                                                                                            | See [UPGRADES key](#upgrades-key)                                                                                   |                                                                                                    |

### ITEMS

#### Furniture
1) Training Dummies

#### Stats multipliers per furniture
1) Capacity

## BATH rooms

For `BATH_` rooms only.

| Key                      | Required | Default     | Min  | Max     | Description                                                                       | Example                                        |
|--------------------------|----------|-------------|------|---------|-----------------------------------------------------------------------------------|------------------------------------------------|
| WORK                     | no       | none        | none | none    | See [WORK key](#work-key)                                                         |                                                |
| SERVICE                  | no       | none        | none | none    | See [SERVICE key](#service-key)                                                   |                                                |
| INDUSTRY.IN.\<RESOURCE\> | no       | none        | 0.0  | 10000.0 | Resource for heating the water. See [INDUSTRY key](#industry-and-industries-keys) |                                                |
| WATER_COLOR_COLOR        | no       | 127_127_127 | none | none    | For tinting the water.                                                            | `20_40_100` or<br/> `{R: 20, G: 40, B: 100, }` |
| WATER_OPACITY            | yes      | none        | 0.0  | 1.0     | How transparent the water shall be.                                               |                                                |

### ITEMS

#### Furniture
1) Basin
2) Benches

#### Stats multipliers per furniture
1) Baths
2) Relaxation

## CANTEEN rooms

For `CANTEEN_` rooms only.

| Key                      | Required | Default     | Min  | Max     | Description                                                                         | Example                                        |
|--------------------------|----------|-------------|------|---------|-------------------------------------------------------------------------------------|------------------------------------------------|
| WORK                     | no       | none        | none | none    | See [WORK key](#work-key)                                                           |                                                |
| SERVICE                  | no       | none        | none | none    | See [SERVICE key](#service-key)                                                     |                                                |
| INDUSTRY.IN.\<RESOURCE\> | no       | none        | 0.0  | 10000.0 | Resource for flavouring the food. See [INDUSTRY key](#industry-and-industries-keys) |                                                |

### ITEMS

#### Furniture
1) Cooking station
2) Tables

#### Stats multipliers per furniture
1) Guests
2) Workers
3) Tables

## EATERY rooms

For `EATERY_` rooms only.

| Key      | Required | Default | Min  | Max  | Description                       | Example |
|----------|----------|---------|------|------|-----------------------------------|---------|
| WORK     | no       | none    | none | none | See [WORK key](#work-key)         |         |
| SERVICE  | no       | none    | none | none | See [SERVICE key](#service-key)   |         |
| UPGRADES | no       | none    | none | none | See [UPGRADES key](#upgrades-key) |         |

### ITEMS

#### Furniture
1) Stall

#### Stats multipliers per furniture
1) Storage
2) Workers

## FARM rooms

For `FARM_` rooms only.

| Key                       | Required | Default | Min  | Max     | Description                                                                                       | Example |
|---------------------------|----------|---------|------|---------|---------------------------------------------------------------------------------------------------|---------|
| WORK                      | no       | none    | none | none    | See [WORK key](#work-key)                                                                         |         |
| SERVICE                   | no       | none    | none | none    | See [SERVICE key](#service-key)                                                                   |         |
| INDOORS                   | no       | false   | none | none    | Whether the farm must be in an enclosed room with walls.                                          |         |
| GROWABLE                  | yes      | none    | none | none    | Which resource this farm produces.<br/>Possible resources can be found in `assets/init/resource`. |         |
| INDUSTRY.OUT.\<RESOURCE\> | no       | none    | 0.0  | 10000.0 | Produced resource with amount. See [INDUSTRY key](#industry-and-industries-keys)                  |         |

### ITEMS

#### Furniture
none

#### Stats multipliers per furniture
1) Soil
2) Farmers
3) Irrigation
4) Yearly Output

## FIGHTPIT rooms

For `FIGHTPIT_` rooms only.

| Key      | Required | Default | Min  | Max  | Description                       | Example |
|----------|----------|---------|------|------|-----------------------------------|---------|
| WORK     | no       | none    | none | none | See [WORK key](#work-key)         |         |
| SERVICE  | no       | none    | none | none | See [SERVICE key](#service-key)   |         |
| UPGRADES | no       | none    | none | none | See [UPGRADES key](#upgrades-key) |         |

### ITEMS

#### Furniture
1) Fight pit

#### Stats multipliers per furniture
1) Gladiators
2) Quality

## FISHERY rooms

For `FISHERY_` rooms only.

| Key                       | Required | Default | Min  | Max     | Description                                                                      | Example |
|---------------------------|----------|---------|------|---------|----------------------------------------------------------------------------------|---------|
| WORK                      | no       | none    | none | none    | See [WORK key](#work-key)                                                        |         |
| UPGRADES                  | no       | none    | none | none    | See [UPGRADES key](#upgrades-key)                                                |         |
| INDUSTRY.OUT.\<RESOURCE\> | no       | none    | 0.0  | 10000.0 | Produced resource with amount. See [INDUSTRY key](#industry-and-industries-keys) |         |

### ITEMS

#### Furniture
1) Gatehouse

#### Stats multipliers per furniture
none

## GATEHOUSE rooms

For `GATEHOUSE_` rooms only.

### ITEMS

#### Furniture
1) Gatehouse

#### Stats multipliers per furniture
none

## GRAVEYARD rooms

For `GRAVEYARD_` rooms only.

| Key      | Required | Default | Min  | Max  | Description                                                                                              | Example  |
|----------|----------|---------|------|------|----------------------------------------------------------------------------------------------------------|----------|
| WORK     | no       | none    | none | none | See [WORK key](#work-key)                                                                                |          |
| PATHWAY  | yes      | none    | none | none | Material used for the pathways.<br/>Possible floor names can be found in `assets/init/settlement/floor`. | `STONE1` |
| STANDING | no       | none    | none | none | See [STANDING key](#standing-key)                                                                        |          |

### ITEMS

#### Furniture
1) Grave
2) Tree
3) Flowers
4) Pathway

#### Stats multipliers per furniture
1) Workers
2) Graves
3) Respect

## HUNTER rooms

For `HUNTER_` rooms only.

| Key          | Required | Default | Min  | Max   | Description                                                                    | Example |
|--------------|----------|---------|------|-------|--------------------------------------------------------------------------------|---------|
| WORK         | no       | none    | none | none  | See [WORK key](#work-key)                                                      |         |
| MAX_EMPLOYED | yes      | none    | 1    | 10000 | How many hunters can work in this room.                                        |         |
| INDUSTRIES   | no       | none    | none | none  | Produces multiple outputs. See [INDUSTRIES key](#industry-and-industries-keys) |         |

### ITEMS

#### Furniture
1) Butcher Table
2) Utilities

#### Stats multipliers per furniture
1) Hunters
2) Efficiency
3) Output

## LABORATORY rooms

For `LABORATORY_` rooms only.

| Key                            | Required | Default | Min                                                                                            | Max                                                                                             | Description                                                                                                                                                                                    | Example |
|--------------------------------|----------|---------|------------------------------------------------------------------------------------------------|-------------------------------------------------------------------------------------------------|------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|---------|
| VALUE_DEGRADE_PER_YEAR         | yes      | none    | 0.0                                                                                            | 10.0                                                                                            | How fast innovation shall be lost.                                                                                                                                                             |         |
| VALUE_PER_WORKER               | no       | none    | 0.0                                                                                            | 100000.0                                                                                        | How much innovation is generated per station.                                                                                                                                                  |         |
| VALUE_WORK_SPEED               | no       | none    | 0.0                                                                                            | 1000.0                                                                                          | How long it takes to perform the job.                                                                                                                                                          |         |
| CONSUMPTION.\<RESOURCE\>.RATE  | no       | none    | 0.0                                                                                            | 10000.0                                                                                         | How much of the \<RESOURCE\> is consumed per performed job.<br/>See file names in `assets/init/resource` and sub folders for possible \<RESOURCE\>s.                                           |         |
| CONSUMPTION.\<RESOURCE\>.BONUS | no       | none    | 0.0                                                                                            | 1000.0                                                                                          | How much bonus to knowledge generation consuming the \<RESOURCE\> will give.<br/>Bonus will be added.<br/>See file names in `assets/init/resource` and sub folders for possible \<RESOURCE\>s. |         |
| EXPERIENCE_BONUS.BONUS         | no       | none    | -[Double.MAX_VALUE](https://docs.oracle.com/javase/8/docs/api/java/lang/Double.html#MAX_VALUE) | [Double.MAX_VALUE](https://docs.oracle.com/javase/8/docs/api/java/lang/Double.html#MAX_VALUE)   |                                                                                                                                                                                                |         |
| EXPERIENCE_BONUS.MAX_EMPLOYEES | no       | none    | 50                                                                                             | [Integer.MAX_VALUE](https://docs.oracle.com/javase/8/docs/api/java/lang/Integer.html#MAX_VALUE) |                                                                                                                                                                                                |         |
| WORK                           | no       | none    | none                                                                                           | none                                                                                            | See [WORK key](#work-key)                                                                                                                                                                      |         |
| UPGRADES                       | no       | none    | none                                                                                           | none                                                                                            | See [UPGRADES key](#upgrades-key)                                                                                                                                                              |         |

### ITEMS

#### Furniture
1) Station
2) Auxiliaries
3) Auxiliaries

#### Stats multipliers per furniture
1) Workers
2) Innovation

## LAVATORY rooms

For `LAVATORY_` rooms only.

| Key                         | Required | Default | Min  | Max       | Description                                                                     | Example |
|-----------------------------|----------|---------|------|-----------|---------------------------------------------------------------------------------|---------|
| WORK                        | no       | none    | none | none      | See [WORK key](#work-key)                                                       |         |
| SERVICE                     | no       | none    | none | none      | See [SERVICE key](#service-key)                                                 |         |
| UPGRADES                    | no       | none    | none | none      | See [UPGRADES key](#upgrades-key)                                               |         |

### ITEMS

#### Furniture
1) Latrine
2) Basins

#### Stats multipliers per furniture
1) Latrines
2) Workers
3) Basins

## LIBRARY rooms

For `LIBRARY_` rooms only.

| Key                            | Required | Default | Min                                                                                            | Max                                                                                             | Description                                                                                                                                                                                    | Example |
|--------------------------------|----------|---------|------------------------------------------------------------------------------------------------|-------------------------------------------------------------------------------------------------|------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|---------|
| VALUE_DEGRADE_PER_YEAR         | yes      | none    | 0.0                                                                                            | 10.0                                                                                            | How fast knowledge shall be lost.                                                                                                                                                              |         |
| VALUE_PER_WORKER               | no       | none    | 0.0                                                                                            | 100000.0                                                                                        | How much knowledge is generated per station.                                                                                                                                                   |         |
| VALUE_WORK_SPEED               | no       | none    | 0.0                                                                                            | 1000.0                                                                                          | How long it takes to perform the job.                                                                                                                                                          |         |
| CONSUMPTION.\<RESOURCE\>.RATE  | no       | none    | 0.0                                                                                            | 10000.0                                                                                         | How much of the \<RESOURCE\> is consumed per performed job.<br/>See file names in `assets/init/resource` and sub folders for possible \<RESOURCE\>s.                                           |         |
| CONSUMPTION.\<RESOURCE\>.BONUS | no       | none    | 0.0                                                                                            | 1000.0                                                                                          | How much bonus to knowledge generation consuming the \<RESOURCE\> will give.<br/>Bonus will be added.<br/>See file names in `assets/init/resource` and sub folders for possible \<RESOURCE\>s. |         |
| EXPERIENCE_BONUS.BONUS         | no       | none    | -[Double.MAX_VALUE](https://docs.oracle.com/javase/8/docs/api/java/lang/Double.html#MAX_VALUE) | [Double.MAX_VALUE](https://docs.oracle.com/javase/8/docs/api/java/lang/Double.html#MAX_VALUE)   |                                                                                                                                                                                                |         |
| EXPERIENCE_BONUS.MAX_EMPLOYEES | no       | none    | 50                                                                                             | [Integer.MAX_VALUE](https://docs.oracle.com/javase/8/docs/api/java/lang/Integer.html#MAX_VALUE) |                                                                                                                                                                                                |         |
| WORK                           | no       | none    | none                                                                                           | none                                                                                            | See [WORK key](#work-key)                                                                                                                                                                      |         |
| UPGRADES                       | no       | none    | none                                                                                           | none                                                                                            | See [UPGRADES key](#upgrades-key)                                                                                                                                                              |         |

### ITEMS

#### Furniture
1) Shelf
2) Carpet

#### Stats multipliers per furniture
1) Workers
2) Knowledge
3) Efficiency

## MARKET rooms

For `MARKET_` rooms only.

| Key                         | Required | Default | Min  | Max       | Description                                                                     | Example |
|-----------------------------|----------|---------|------|-----------|---------------------------------------------------------------------------------|---------|
| WORK                        | no       | none    | none | none      | See [WORK key](#work-key)                                                       |         |
| SERVICE                     | no       | none    | none | none      | See [SERVICE key](#service-key)                                                 |         |
| UPGRADES                    | no       | none    | none | none      | See [UPGRADES key](#upgrades-key)                                               |         |

### ITEMS

#### Furniture
1) Stall

#### Stats multipliers per furniture
1) Storage
2) Workers

## MINE rooms

For `MINE_` rooms only.

| Key                | Required | Default | Min  | Max    | Description                                                                                                                | Example |
|--------------------|----------|---------|------|--------|----------------------------------------------------------------------------------------------------------------------------|---------|
| MINABLE            | no       | none    | none | none   | The resource which can be mined with this mine.<br/>See file names in `assets/init/resource/mineable` for possible values. | `CLAY`  |
| YEILD_WORKER_DAILY | yes      | none    | 0.0  | 1000.0 | How much of the resource one worker can mine per day.                                                                      |         |
| STORAGE            | yes      | none    | 4    | 500    | How much of the resource can be stored per tile.                                                                           |         |
| DEGRADE_RATE       | no       | 0.75    | 0.0  | 1.0    | Multiplier for how fast stored resources in degrades.                                                                      |         |
| WORK               | no       | none    | none | none   | See [WORK key](#work-key)                                                                                                  |         |
| UPGRADES           | no       | none    | none | none   | See [UPGRADES key](#upgrades-key)                                                                                          |         |
| ENVIRONMENT_EMIT   | no       | none    | none | none   | See [ENVIRONMENT_EMIT key](#environment_emit-key)                                                                          |         |

### ITEMS

#### Furniture
1) Storage
2) Auxiliaries

#### Stats multipliers per furniture
1) Workers
2) Deposits
3) Efficiency
4) Output

## MONUMENT rooms

For `MONUMENT_` rooms only.

| Key                           | Required | Default | Min                                                                                              | Max                                                                                             | Description                                                                                                                                                                         | Example |
|-------------------------------|----------|---------|--------------------------------------------------------------------------------------------------|-------------------------------------------------------------------------------------------------|-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|---------|
| SOLID                         | no       | false   | none                                                                                             | none                                                                                            | Whether citizens can walk through the monument.                                                                                                                                     |         |
| MAX_VALUE                     | yes      | none    | -[Integer.MAX_VALUE](https://docs.oracle.com/javase/8/docs/api/java/lang/Integer.html#MAX_VALUE) | [Integer.MAX_VALUE](https://docs.oracle.com/javase/8/docs/api/java/lang/Integer.html#MAX_VALUE) | Maximum environment bonus the monument will produce.                                                                                                                                |         |
| STANDING                      | yes      | none    | none                                                                                             | none                                                                                            | See [STANDING key](#standing-key)                                                                                                                                                   |         |
| STANDING_UPGRADE.STANDING     | no       | none    | none                                                                                             | none                                                                                            | How much standing a bigger version of the monument will give (e.g. torches).<br/>The values will be added to the values defined in `STANDING`<br/>See [STANDING key](#standing-key) |         |
| FULFILLMENT_BONUS.\<BOOSTER\> | no       | none    | none                                                                                             | none                                                                                            | Bonus citizens will receive when in proximity of the monument. See [boosts](boost.md)                                                                                               |         |

### ITEMS

#### Furniture
1) The monument itself

#### Stats multipliers per furniture
none

## NURSERY rooms

For `NURSERY_` rooms only.

| Key                      | Required | Default | Min  | Max     | Description                                                                                                                                                            | Example                                                          |
|--------------------------|----------|---------|------|---------|------------------------------------------------------------------------------------------------------------------------------------------------------------------------|------------------------------------------------------------------|
| RACE                     | yes      | none    | none | none    | The race the nursery is for.<br/>Possible races can be found in `assets/init/race`.                                                                                    | `Q_AMEVIA`                                                       |
| INCUBATION_DAYS          | yes      | none    | 0    | 127     | How long it takes for a infant to be grown into a child.                                                                                                               |                                                                  |
| WORK                     | no       | none    | none | none    | See [WORK key](#work-key)                                                                                                                                              |                                                                  |
| INDUSTRY.IN.\<RESOURCE\> | yes      | none    | 0.0  | 10000.0 | Input resource consumed when nursing infants.<br/>Possible \<RESOURCE\>s can be found in `assets/init/resource`.<br/>See [INDUSTRY key](#industry-and-industries-keys) | See [INDUSTRY and INDUSTRIES key](#industry-and-industries-keys) |

### ITEMS

#### Furniture
1) Crib
2) Carpet

#### Stats multipliers per furniture
1) Clutches
2) Workers
3) Efficiency

## ORCHARD rooms

For `ORCHARD_` rooms only.

| Key                       | Required | Default | Min  | Max                                                                                             | Description                                                                                                                                                        | Example                                                          |
|---------------------------|----------|---------|------|-------------------------------------------------------------------------------------------------|--------------------------------------------------------------------------------------------------------------------------------------------------------------------|------------------------------------------------------------------|
| INDOORS                   | no       | false   | none | none                                                                                            | Whether the orchard must be in an enclosed room with walls.                                                                                                        |                                                                  |
| EXTRA_RESOURCE            | yes      | none    | none | none                                                                                            | Additional resource generated when working.<br/>See file names in `assets/init/resource` and sub folders for possible values.                                      |                                                                  |
| EXTRA_RESOURCE_AMOUNT     | yes      | none    | 0    | [Integer.MAX_VALUE](https://docs.oracle.com/javase/8/docs/api/java/lang/Integer.html#MAX_VALUE) | How much of the additional resource you will get per tile.                                                                                                         |                                                                  |
| DAYS_TILL_GROWTH          | yes      | none    | 8    | 1024                                                                                            | How long it will take for the trees to be fully grown and ready for producing resources.                                                                           |                                                                  |
| RIPE_AT_PART_OF_YEAR      | yes      | none    | 0.0  | 1.0                                                                                             | At which part of the year the resources can be harvested.                                                                                                          |                                                                  |
| WORK                      | no       | none    | none | none                                                                                            | See [WORK key](#work-key)                                                                                                                                          |                                                                  |
| INDUSTRY.OUT.\<RESOURCE\> | yes      | none    | 0.0  | 10000.0                                                                                         | Output resource produced when harvesting.<br/>Possible \<RESOURCE\>s can be found in `assets/init/resource`.<br/>See [INDUSTRY key](#industry-and-industries-keys) | See [INDUSTRY and INDUSTRIES key](#industry-and-industries-keys) |

### ITEMS

#### Furniture
1) Tree

#### Stats multipliers per furniture
1) Soil
2) Farmers
3) Irrigation
4) Yearly Output

## PASTURE rooms

For `PASTURE_` rooms only.

| Key                                  | Required | Default | Min  | Max  | Description                                                                                                                                                                                         | Example  |
|--------------------------------------|----------|---------|------|------|-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|----------|
| WORK                                 | no       | none    | none | none | See [WORK key](#work-key)                                                                                                                                                                           |          |
| ANIMAL                               | yes      | none    | none | none | Which animal the pasture will use.<br/>Possible animals can be found in `assets/init/animal`.                                                                                                       | `AUROCH` |
| FENCE                                | yes      | none    | none | none | Which kind of fence to use for the borders.<br/>Possible fences can be found in `assets/init/settlement/fence`.                                                                                     | `WOOD`   |
| INDUSTRIES.INDUSTRY.OUT.\<RESOURCE\> | yes      | none    | none | none | Which resources the pasture will produce when an animal is slaughtered. <br/>Possible \<RESOURCE\>s can be found in `assets/init/resource`.<br/>See [INDUSTRIES key](#industry-and-industries-keys) |          |

### ITEMS

#### Furniture
1) Gate
2) Hut

#### Stats multipliers per furniture
1) Workers
2) Fertility
3) Irrigation
4) Output

## PHYSICIAN rooms

For `PHYSICIAN_` rooms only.

| Key                         | Required | Default | Min  | Max       | Description                                                                     | Example |
|-----------------------------|----------|---------|------|-----------|---------------------------------------------------------------------------------|---------|
| WORK                        | no       | none    | none | none      | See [WORK key](#work-key)                                                       |         |
| SERVICE                     | no       | none    | none | none      | See [SERVICE key](#service-key)                                                 |         |

### ITEMS

#### Furniture
1) Workbench
2) Shelves

#### Stats multipliers per furniture
1) Physicians
2) Services
3) Quality

## PLEASURE rooms

For `PLEASURE_` rooms only.

| Key              | Required | Default     | Min  | Max  | Description                                                                                                  | Example                                            |
|------------------|----------|-------------|------|------|--------------------------------------------------------------------------------------------------------------|----------------------------------------------------|
| COLOR_PIXEL_BASE | no       | 127_127_127 | none | none | Used for blurring citizens having "pleasure"                                                                 | `255_255_255` or<br/> `{R: 255, G: 255, B: 255, }` |
| FLOOR2           | yes      | none        | none | none | Floor to be placed under furnisher.<br/>Possible floor names can be found in `assets/init/settlement/floor`. | `WOOD`                                             |
| WORK             | no       | none        | none | none | See [WORK key](#work-key)                                                                                    |                                                    |
| SERVICE          | no       | none        | none | none | See [SERVICE key](#service-key)                                                                              |                                                    |
| UPGRADES         | no       | none        | none | none | See [UPGRADES key](#upgrades-key)                                                                            |                                                    |

### ITEMS

#### Furniture
1) Booth
2) Decorations
3) Carpets

#### Stats multipliers per furniture
1) Clients
2) Coziness
3) Workers

## POOL rooms

For `POOL_` rooms only.

| Key                 | Required | Default     | Min  | Max                        | Description                                                                                                   | Example                                        |
|---------------------|----------|-------------|------|----------------------------|---------------------------------------------------------------------------------------------------------------|------------------------------------------------|
| WATER_COLOR         | no       | 127_127_127 | none | none                       | For tinting the water.                                                                                        | `20_40_100` or<br/> `{R: 20, G: 40, B: 100, }` |
| WATER_DEPTH         | yes      | none        | 0.0  | 1.0                        | Defines the opacity of the water.                                                                             |                                                |
| CLEARS_GRASS        | no       | false       | none | none                       | Whether the placed pool should remove fertility.                                                              |                                                |
| BOTTOM_TEXTURE.FILE | yes      | none        | none | none                       | Texture used for rendering underwater.<br/>Possible floor names can be found in `assets/sprite/game/texture`. | `ORGANIC`                                      |
| BOTTOM_TEXTURE.ROW  | yes      | none        | 0    | depends on the spritesheet | Number of the row in the spritesheet file to use. Starting at 0.                                              |                                                |

### ITEMS

#### Furniture
none

#### Stats multipliers per furniture
none

## REFINER rooms

For `REFINER_` rooms only.

| Key                                  | Required | Default | Min  | Max     | Description                                                                                                                                                    | Example                                                          |
|--------------------------------------|----------|---------|------|---------|----------------------------------------------------------------------------------------------------------------------------------------------------------------|------------------------------------------------------------------|
| STORAGE                              | yes      | none    | 4    | 500     | How much of the resource can be stored per tile.                                                                                                               |                                                                  |
| WORK                                 | no       | none    | none | none    | See [WORK key](#work-key)                                                                                                                                      |                                                                  |
| SERVICE                              | no       | none    | none | none    | See [SERVICE key](#service-key)                                                                                                                                |                                                                  |
| UPGRADES                             | no       | none    | none | none    | See [UPGRADES key](#upgrades-key)                                                                                                                              |                                                                  |
| INDUSTRIES.INDUSTRY.IN.\<RESOURCE\>  | yes      | none    | 0.0  | 10000.0 | Input resource required for refining.<br/>Possible \<RESOURCE\>s can be found in `assets/init/resource`.<br/>See [INDUSTRY key](#industry-and-industries-keys) | See [INDUSTRY and INDUSTRIES key](#industry-and-industries-keys) |
| INDUSTRIES.INDUSTRY.OUT.\<RESOURCE\> | yes      | none    | 0.0  | 10000.0 | Output resource produced.<br/>Possible \<RESOURCE\>s can be found in `assets/init/resource`.<br/>See [INDUSTRY key](#industry-and-industries-keys)             | See [INDUSTRY and INDUSTRIES key](#industry-and-industries-keys) |
| ENVIRONMENT_EMIT                     | no       | none    | none | none    | See [ENVIRONMENT_EMIT key](#environment_emit-key)                                                                                                              |                                                                  |

### ITEMS

#### Furniture
1) Furniture used for refining (e.g. Oven)
2) Storage
3) Auxiliary

#### Stats multipliers per furniture
1) Workers
2) Efficiency
3) Output

## RESTHOME rooms

For `RESTHOME_` rooms only.

| Key        | Required | Default | Min  | Max  | Description                           | Example |
|------------|----------|---------|------|------|---------------------------------------|---------|
| EMPLOYMENT | no       | none    | none | none | See [EMPLOYMENT key](#employment-key) |         |

### ITEMS

#### Furniture
1) Tables
2) Dance floor
3) Decorations
4) Carpets

#### Stats multipliers per furniture
1) Capacity
2) Coziness

## SCHOOL rooms

For `SCHOOL_` rooms only.

| Key                      | Required | Default | Min  | Max     | Description                                                                                                                                                     | Example                                                          |
|--------------------------|----------|---------|------|---------|-----------------------------------------------------------------------------------------------------------------------------------------------------------------|------------------------------------------------------------------|
| LEARNING_SPEED           | yes      | 0.0     | 1.0  | none    | How fast citizens will learn. Formula is `1 / LEARNING_SPEED = DAYS_TO_EDUCATE`                                                                                 |                                                                  |
| WORK                     | no       | none    | none | none    | See [WORK key](#work-key)                                                                                                                                       |                                                                  |
| SERVICE                  | no       | none    | none | none    | See [SERVICE key](#service-key)                                                                                                                                 |                                                                  |
| INDUSTRY.IN.\<RESOURCE\> | yes      | none    | 0.0  | 10000.0 | Input resource consumed when learning.<br/>Possible \<RESOURCE\>s can be found in `assets/init/resource`.<br/>See [INDUSTRY key](#industry-and-industries-keys) | See [INDUSTRY and INDUSTRIES key](#industry-and-industries-keys) |

### ITEMS

#### Furniture
1) Bench
2) Shelf
3) Carpets

#### Stats multipliers per furniture
1) Seats
2) Quality

## SHRINE rooms

For `SHRINE_` rooms only.

| Key      | Required | Default | Min  | Max  | Description                                                                               | Example   |
|----------|----------|---------|------|------|-------------------------------------------------------------------------------------------|-----------|
| RELIGION | no       | none    | none | none | Which religion to worship.<br/>Possible religions can be found in `assets/init/religion`. | `AMINION` |
| SERVICE  | no       | none    | none | none | See [SERVICE key](#service-key)                                                           |           |
| UPGRADES | no       | none    | none | none | See [UPGRADES key](#upgrades-key)                                                         |           |

### ITEMS

#### Furniture
1) Shrine

#### Stats multipliers per furniture
1) Worshippers

## SPEAKER rooms

For `SPEAKER_` rooms only.

| Key                         | Required | Default | Min  | Max       | Description                                                                     | Example |
|-----------------------------|----------|---------|------|-----------|---------------------------------------------------------------------------------|---------|
| WORK                        | no       | none    | none | none      | See [WORK key](#work-key)                                                       |         |
| SERVICE                     | no       | none    | none | none      | See [SERVICE key](#service-key)                                                 |         |
| UPGRADES                    | no       | none    | none | none      | See [UPGRADES key](#upgrades-key)                                               |         |

### ITEMS

#### Furniture
1) Speaker

#### Stats multipliers per furniture
1) Workers
2) Spectators

## STAGE rooms

For `STAGE_` rooms only.

| Key                         | Required | Default | Min  | Max       | Description                                                                     | Example |
|-----------------------------|----------|---------|------|-----------|---------------------------------------------------------------------------------|---------|
| WORK                        | no       | none    | none | none      | See [WORK key](#work-key)                                                       |         |
| SERVICE                     | no       | none    | none | none      | See [SERVICE key](#service-key)                                                 |         |
| UPGRADES                    | no       | none    | none | none      | See [UPGRADES key](#upgrades-key)                                               |         |

### ITEMS

#### Furniture
1) Stage

#### Stats multipliers per furniture
1) Workers
2) Spectators

## TAVERN rooms

For `TAVERN_` rooms only.

| Key              | Required | Default | Min  | Max  | Description                                       | Example |
|------------------|----------|---------|------|------|---------------------------------------------------|---------|
| WORK             | no       | none    | none | none | See [WORK key](#work-key)                         |         |
| SERVICE          | no       | none    | none | none | See [SERVICE key](#service-key)                   |         |
| UPGRADES         | no       | none    | none | none | See [UPGRADES key](#upgrades-key)                 |         |
| ENVIRONMENT_EMIT | no       | none    | none | none | See [ENVIRONMENT_EMIT key](#environment_emit-key) |         |

### ITEMS

#### Furniture
1) Table
2) Nicknacks
3) Carpets

#### Stats multipliers per furniture
1) Tables
2) Coziness

## TEMPLE rooms

For `TEMPLE_` rooms only.

| Key            | Required | Default | Min  | Max  | Description                                                                                                                      | Example                                |
|----------------|----------|---------|------|------|----------------------------------------------------------------------------------------------------------------------------------|----------------------------------------|
| RELIGION       | no       | none    | none | none | Which religion to worship.<br/>Possible religions can be found in `assets/init/religion`.                                        | `AMINION`                              |
| SACRIFICE_TYPE | yes      | none    | none | none | Which type of sacrifice the religion wants.<br/>Possible values are `RESOURCE`, `ANIMAL`, `HUMAN`.                               | `HUMAN`                                |
| SACRIFICE_TYPE | yes      | none    | none | none | Which type of sacrifice the religion wants.<br/>Possible values are `RESOURCE`, `ANIMAL`, `HUMAN`.                               | `HUMAN`                                |
| FLOOR          | yes      | none    | none | none | Floor used for this room. Can also be a list of floors.<br/>Possible floor names can be found in `assets/init/settlement/floor`. | `WOOD` or<br/> `[DIRT, WOOD, STONE2,]` |
| FLOOR_PATH     | no       | none    | none | none | Which floor to use for the pathways.<br/>Possible floor names can be found in `assets/init/settlement/floor`.                    | `STONE_LARGE_DARK`                     |
| WORK           | no       | none    | none | none | See [WORK key](#work-key)                                                                                                        |                                        |
| SERVICE        | no       | none    | none | none | See [SERVICE key](#service-key)                                                                                                  |                                        |

### ITEMS

#### Furniture
1) Altar
2) Decorations
3) Torch
4) Relief
5) Pathway

#### Stats multipliers per furniture
1) Priests
2) Worshippers
3) Decoration
4) Grandeur
5) Space

## TOMB rooms

For `TOMB_` rooms only.

| Key      | Required | Default | Min  | Max  | Description                                                                                                                      | Example |
|----------|----------|---------|------|------|----------------------------------------------------------------------------------------------------------------------------------|---------|
| MONUMENT     | no       | none    | none | none | Which monument to be used as statues.<br/>Possible monuments can be found in `assets/init/rooms`.<br/>Starting with `MONUMENT_`. | `MONUMENT_STATUE`        |
| WORK     | no       | none    | none | none | See [WORK key](#work-key)                                                                                                        |         |
| STANDING | no       | none    | none | none | See [STANDING key](#standing-key)                                                                                                |         |

### ITEMS

#### Furniture
1) Grave
2) Statue

#### Stats multipliers per furniture
1) Workers
2) Graves
3) Respect

## UNIVERSITY rooms

For `UNIVERSITY_` rooms only.

| Key            | Required | Default | Min  | Max  | Description                                                                     | Example |
|----------------|----------|---------|------|------|---------------------------------------------------------------------------------|---------|
| LEARNING_SPEED | yes      | 0.0     | 1.0  | none | How fast citizens will learn. Formula is `1 / LEARNING_SPEED = DAYS_TO_EDUCATE` |         |
| EMPLOYMENT     | no       | none    | none | none | See [EMPLOYMENT key](#employment-key)                                           |         |

### ITEMS

#### Furniture
1) Podium
2) Shelf

#### Stats multipliers per furniture
1) Students
2) Quality

## WELL rooms

For `WELL_` rooms only.

| Key                         | Required | Default | Min  | Max       | Description                                                                     | Example |
|-----------------------------|----------|---------|------|-----------|---------------------------------------------------------------------------------|---------|
| SERVICE                     | no       | none    | none | none      | See [SERVICE key](#service-key)                                                 |         |
| UPGRADES                    | no       | none    | none | none      | See [UPGRADES key](#upgrades-key)                                               |         |

### ITEMS

#### Furniture
1) Well

#### Stats multipliers per furniture
1) Spots

## WORKSHOP rooms

For `WORKSHOP_` rooms only.

| Key                                  | Required | Default | Min  | Max  | Description                                                                                  | Example |
|--------------------------------------|----------|---------|------|------|----------------------------------------------------------------------------------------------|---------|
| STORAGE                              | yes      | none    | 4    | 500  | How much of the resource can be stored per tile.                                             |         |
| WORK                                 | no       | none    | none | none | See [WORK key](#work-key)                                                                    |         |
| ENVIRONMENT_EMIT                     | no       | none    | none | none | See [ENVIRONMENT_EMIT key](#environment_emit-key)                                            |         |
| INDUSTRIES.INDUSTRY.IN.\<RESOURCE\>  | no       | none    | none | none | Input resource consumed for the product. See [INDUSTRIES key](#industry-and-industries-keys) |         |
| INDUSTRIES.INDUSTRY.OUT.\<RESOURCE\> | no       | none    | none | none | The output product. See [INDUSTRIES key](#industry-and-industries-keys)                      |         |

### ITEMS

#### Furniture
1) Storage
2) Work Bench
3) Auxiliary Station

#### Stats multipliers per furniture
1) Workers
2) Efficiency

## STANDING key

| Key        | Required | Default | Min  | Max       | Description                                                                     | Example |
|------------|----------|---------|------|-----------|---------------------------------------------------------------------------------|---------|
| PRIO       | no       | 1.0     | 0.0  | 100000.0  | Used for displaying standings ordered by prio.                                  |         |
| INVERTED   | no       | false   | none | none      | Whether the standing shall have a negative effect.                              |         |
| MULTIPLIER | no       | 0.0     | 0.0  | 10000.0   | Multiplier for how much standing it generates.                                  |         |
| EXPONENT   | no       | 1.0     | 0.01 | 100000.0  | Exponential factor for increasing the standing.                                 |         |
| DISMISS    | no       | false   | none | none      | Whether the standing shall not count to the overall reachable maximum standing. |         |
| NOBLE      | no       | 0.0     | 0.0  | 1000000.0 | How much the standing affects nobles.                                           |         |
| CITIZEN    | no       | 0.0     | 0.0  | 1000000.0 | How much the standing affects normal citizens.                                  |         |
| SLAVE      | no       | 0.0     | 0.0  | 1000000.0 | How much the standing affects slaves.                                           |         |
| CHILD      | no       | 0.0     | 0.0  | 1000000.0 | How much the standing affects children.                                         |         |


## UPGRADES key

Upgrades are possible room improvements. 
They are defined as a list where the first entry is the first upgrade level.
Example:
```
UPGRADES: [
	{
		RESOURCE_MASK: [1,1.25,0,0,],
		BOOST: 0,
		AI: 0,
	},
	{
		RESOURCE_MASK: [1,1.25,2,0,],
		BOOST: 0.5,
		AI: 0,
	},
	{
		RESOURCE_MASK: [1,1.25,3,0.75,],
		BOOST: 1.0,
		AI: 0,
	},
],
```

| Key           | Required | Default   | Min                                                                                            | Max                                                                                           | Description                                                                                                                          | Example         |
|---------------|----------|-----------|------------------------------------------------------------------------------------------------|-----------------------------------------------------------------------------------------------|--------------------------------------------------------------------------------------------------------------------------------------|-----------------|
| RESOURCE_MASK | yes      | none      | none                                                                                           | none                                                                                          | Upgrade costs for each level<br/>It contains amount a list of multipliers for the resources defined in the `RESOURCES` list.         | `[1,1.25,0,0,]` |
| BOOST         | yes      | none      | -[Double.MAX_VALUE](https://docs.oracle.com/javase/8/docs/api/java/lang/Double.html#MAX_VALUE) | [Double.MAX_VALUE](https://docs.oracle.com/javase/8/docs/api/java/lang/Double.html#MAX_VALUE) | The actual improvement. It is added to the actual room bonus.<br/>It can be negative. What it actually improves depends on the room. |                 |
| AI            | no       | BOOST / 2 | 0.0                                                                                            | 10000.0                                                                                       | The bonus value applied to NPCs                                                                                                      |                 |

## SERVICE key

| Key           | Required | Default | Min  | Max   | Description                                                                               | Example  |
|---------------|----------|---------|------|-------|-------------------------------------------------------------------------------------------|----------|
| RADIUS        | no       | 150     | 0    | 50000 | How far the services reaches.                                                             |          |
| STANDING      | no       | none    | none | none  | See [STANDING key](#standing-key)                                                         |          |
| USAGE         | no       | 1.0     | 0.0  | 1.0   | How much of a service need will be fulfilled when visiting the room.                      |          |
| NEED          | yes      | none    | none | none  | Which need the room will fulfill. Possible needs can be found in `assets/init/stats/need` | `ARENAG` |
| BOOST.\<KEY\> | no       | none    | none | none  | See [boosts](boost.md)                                                                    |          |

## EMPLOYMENT key

For rooms with employees.

| Key                | Required | Default | Min  | Max     | Description                                                                                | Example |
|--------------------|----------|---------|------|---------|--------------------------------------------------------------------------------------------|---------|
| SHIFT_OFFSET       | yes      | none    | 0.0  | 0.99    | At which percentage of a day length the work shift starts.                                 |         |
| NIGHT_SHIFT        | no       | false   | none | none    | Whether employees should work at night.                                                    |         |
| FULLFILLMENT       | no       | 0.5     | 0.0  | 1.0     | How much fulfillment an employee will get when working in this room.                       |         |
| ACCIDENTS_PER_YEAR | no       | 0.0     | 0.0  | 10000.0 | Maximum numbers of work accidents which can happen in a year. Actual value will be halved. |         |
| HEALTH_FACTOR      | no       | 1.0     | 0.0  | 1.0     | How much health will decrease (1.0 means no decrease) when working in this room.           |         |

## WORK key

For rooms with employees.

| Key                | Required | Default | Min  | Max     | Description                                                                                | Example |
|--------------------|----------|---------|------|---------|--------------------------------------------------------------------------------------------|---------|
| SHIFT_OFFSET       | yes      | none    | 0.0  | 0.99    | At which percentage of a day length the work shift starts.                                 |         |
| NIGHT_SHIFT        | no       | false   | none | none    | Whether employees should work at night.                                                    |         |
| FULLFILLMENT       | no       | 0.5     | 0.0  | 1.0     | How much fulfillment an employee will get when working in this room.                       |         |
| ACCIDENTS_PER_YEAR | no       | 0.0     | 0.0  | 10000.0 | Maximum numbers of work accidents which can happen in a year. Actual value will be halved. |         |
| HEALTH_FACTOR      | no       | 1.0     | 0.0  | 1.0     | How much health will decrease (1.0 means no decrease) when working in this room.           |         |

## ENVIRONMENT_EMIT key

Used for rooms emitting something like noise or light.
Possible \<EMIT\> values:

* _NOISE
* _LIGHT
* _SPACE
* _URBANISATION
* _WATER_SWEET
* _WATER_SALT

| Key             | Required | Default | Min | Max | Description               | Example |
|-----------------|----------|---------|-----|-----|---------------------------|---------|
| \<EMIT\>.VALUE  | yes      | none    | 0.0 | 1.0 | How much it emits.        |         |
| \<EMIT\>.RADIUS | yes      | none    | 0.0 | 1.0 | How far the emit reaches. |         |

## ITEMS key

This is the section for how much resources furniture for this room will cost.
Each entry is a single piece of furniture.

### COSTS

Example from `assets/init/room/ARENAG_NORMAL.txt`:
```
ITEMS: [
	{
		COSTS: [0,0,],
		STATS: [0,0,],
	},
],
```

The `COSTS` refer to the defined `RESOURCES` of the room and are a multiplier.

### STATS

Example with name and description from `assets/text/room/ARENAG_NORMAL.txt`:
```
ITEMS: [
	{
		NAME: "Entrance",
		DESC: "Placed at the edge of the room to allow spectators to enter.",
	},
],
STATS: [
	{
		NAME: "Gladiators",
		DESC: "How many gladiators that will be needed.",
	},
	{
		NAME: "Spectators",
		DESC: "The amount of people that can enjoy the show.",
	},
],
```

The `STATS` are unique for each room type and mean different things.
The `ITEMS` here are furniture and refer to each entry in `ITEMS` from the `init` config.
So if we look at the first entry in the same room `text` config, it tells us that it is an `Entrance`. 

The `STATS` refer to each `STATS` array from the `init` config. 
So if we look at the first furniture item, which has `[0,0,]` as stats, it tells us the first number (0) are `Gladiators` and the second (0) is `Spectators`.
So the `Entrance` furniture will give you 0 `Gladiators` and 0 `Spectators` when placed.

There can be exceptions where a stat entry has an empty `NAME` and `DESC` like this (from `assets/text/room/ADMIN_NORMAL.txt`):
```
STATS: [
	{
		NAME: "",
		DESC: "",
	},
	{
		NAME: "Capacity",
		DESC: "A rough estimate of how much administration that can be produced and stored.",
	},
	{
		NAME: "Efficiency",
		DESC: "The efficiency greatly improves administration production.",
	},
],
```

In this case, this is a hard coded stat. Unfortunately, it is impossible to find without looking into the game source code.
If you can read the source code though, go into the `settlement.room` package to find the code for all rooms. 
For our `ADMIN_NORMAL` room example the code is in `settlement.room.infra.admin`. Look into the `Constructor` class.
This thing defines all the furniture. In there, look for `FurnisherStat`:

```java
final class Constructor extends Furnisher {

    public final FurnisherStat workers = new FurnisherStat.FurnisherStatEmployees(this);
    public final FurnisherStat stations = new FurnisherStat(this) {

        @Override
        public double get(AREA area, double fromItems) {
            return fromItems;
        }

        @Override
        public GText format(GText t, double value) {
            return GFORMAT.i(t, (int) (value * blue.data.knowledgePerStation));
        }
    };
    public final FurnisherStat efficiency = new FurnisherStat.FurnisherStatEfficiency(this, workers);
}
```

The first defined `public final FurnisherStat workers = new FurnisherStat.FurnisherStatEmployees(this);` in this case is for the employees and refers to our empty stat, because it is the first instantiated.

## INDUSTRY and INDUSTRIES keys

Industries come in different flavors depending on the room.
Possible in and output resources can be found in `assets/init/resource`.

Only output example from: `assets/init/room/FARM_FRUIT.txt`
```
INDUSTRY: {
	OUT: {
		FRUIT: 2.1,
	},
},
```

Only input example from: `assets/init/room/ADMIN_NORMAL.txt`
```
INDUSTRY: {
	IN: {
		PAPER: 2.0,
	},
},
```

Multiple industries (recipes) with in and output example from: `assets/init/room/WORKSHOP_CARPENTER.txt`
```
INDUSTRIES: [
	{
		INDUSTRY: {
			IN: {
				WOOD: 2.0,
			},
			OUT: {
				FURNITURE: 0.5,
			},
		},
	},
	{
		INDUSTRY: {
			IN: {
				WOOD: 2.0,
				STONE: 2.5,
			},
			OUT: {
				WEAPON_SPEAR: 0.5,
			},
		},
	},
	{
		INDUSTRY: {
			IN: {
				WOOD: 1.0,
				METAL: 0.4,
			},
			OUT: {
				WEAPON_SPEAR: 1.0,
			},
		},
	},
	{
		INDUSTRY: {
			IN: {
				WOOD: 2.0,
				STONE: 2.5,
			},
			OUT: {
				WEAPON_HAMMER: 0.25,
			},
		},
	},
	{
		INDUSTRY: {
			IN: {
				WOOD: 6.0,
				LEATHER: 2.0,
			},
			OUT: {
				WEAPON_SHIELD: 1.0,
			},
		},
	},
],
```

Industry with multiple products having special AI settings example from `assets/init/room/PASTURE_AUR.txt`:
```
INDUSTRIES: [
	{
		INDUSTRY: {
			OUT: {
				MEAT: {
					PLAYER: 0.56,
					AI: 1.12,
					AI_SPEED: 0.5,
				},
				LEATHER: {
					PLAYER: 0.56,
					AI: 1.12,
					AI_SPEED: 0.5,
				}, 
				LIVESTOCK: {
					PLAYER: 0.0875,
					AI: 0.875,
					AI_SPEED: 0.1,
				},
			},
		},
	},
],
```

### SPRITES key

Sprites can be found in the subfolders of `assets/sprite/game`.
The \<SPRITE_KEY\>s are unfortunately defined in the source code per room.

| Key                          | Required | Default     | Min  | Max      | Description                                                                                                                                                                                                 | Example                                                                                            |
|------------------------------|----------|-------------|------|----------|-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|----------------------------------------------------------------------------------------------------|
| \<SPRITE_KEY\>.TINT          | no       | true        | none | none     | Will "shade" the sprite randomly when enabled. You can either have TINT or COLOR, not both.                                                                                                                 |                                                                                                    |
| \<SPRITE_KEY\>.COLOR         | no       | 127_127_127 | none | none     | Will colorize the sprite in given color. Can also be a list of colors. You can either have TINT or COLOR, not both.                                                                                         | `255_255_255` or<br/>`{ R: 255, G: 255, B: 255, }` or as list<br/> `[ 111_111_111, 222_222_222, ]` |
| \<SPRITE_KEY\>.ROTATES       | no       | false       | none | none     | Whether the sprite can be rotated.                                                                                                                                                                          |                                                                                                    |
| \<SPRITE_KEY\>.SHADOW_LENGTH | no       | 0.0         | 0.0  | 100.0    | Length of the shadow the sprite shall produce.                                                                                                                                                              |                                                                                                    |
| \<SPRITE_KEY\>.SHADOW_HEIGHT | no       | 0.0         | 0.0  | 100.0    | Height of the shadow the sprite shall produce.                                                                                                                                                              |                                                                                                    |
| \<SPRITE_KEY\>.FPS           | no       | 0.0         | 0.0  | 100000.0 | Animation speed in frames per second. Used when the sprite is animated.                                                                                                                                     |                                                                                                    |
| \<SPRITE_KEY\>.FPS_INTERVAL  | no       | 1.0         | 0.0  | 1.0      | Animation consistency.                                                                                                                                                                                      |                                                                                                    |
| \<SPRITE_KEY\>.CIRCULAR      | no       | false       | none | none     | Whether the animation repeats.                                                                                                                                                                              |                                                                                                    |
| \<SPRITE_KEY\>.FRAMES        | no       | none        | none | none     | Key value pairs. Consisting of a file name from a subfolder in `assets/sprite/game`<br/>and the number of the tile in given sprite sheet file.<br/>A `-` can be used instead to place an empty dummy frame. | `[ WORK: 0, WORK: 1, BASIN: 1, -, ]`                                                               |
| \<SPRITE_KEY\>.OVERWRITE     | no       | none        | none | none     | Here you can overwrite properties for each defined frame in `FRAMES`.                                                                                                                                       | `[ {}, {}, {FPS: 15, COLOR: {R:100,G:100,B:100,}, }, ]`                                            |