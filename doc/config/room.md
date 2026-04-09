# Rooms

`data/assets/init/room`

**Rooms** follow a special semantic.
Each room file starting with an `_` (e.g. `_ASYLUM.txt`) is a "unique" room.
These can't have new implementations without modifying the game code.

All other rooms can get a new implementation with your own configuration.
For example if you made a new weapon "Crossbow", you can have a `WORKSHOP` like `WORKSHOP_BOWYER.txt`,
but you would call it e.g.`WORKSHOP_CROSSBOW.txt` and configure it to produce crossbows instead.

## General

For all kinds of rooms.

| Key                     | Required | Default     | Min  | Max  | Description                                                                                                                                                           | Example                                                                                       |
|-------------------------|----------|-------------|------|------|-----------------------------------------------------------------------------------------------------------------------------------------------------------------------|-----------------------------------------------------------------------------------------------|
| ICON                    | no       | dummy icon  | none | none | Path to the sprite tile used for the icon. The path will look into `assets/init/sprite/icon`.                                                                         | `32->INFRA->0`<br/>The real path would be:<br/>`assets/init/icon/32/INFRA.png` first (0) tile |
| TYPE                    | no       | none        | none | none | Type of the room.                                                                                                                                                     | `ARENA_G`                                                                                     |
| MINI _COLOR             | no       | 127_127_127 | none | none | For displaying the room on the minimap and when max zoomed out.                                                                                                       | `255_255_255` or<br/> `{R: 255, G: 255, B: 255, }`                                            |
| RESOURCES               | yes      | none        | none | none | List with resources used for construction. A maximum of 4 resources can be defined.<br/>See file names in `assets/init/resource` and sub folders for possible values. | `[STONE, WOOD,],`                                                                             |
| AREA_COSTS              | yes      | none        | none | none | List with resource costs per tile for furnishing the room.                                                                                                            | `[1, 1,],`                                                                                    |
| FLOOR                   | yes      | none        | none | none | Floor used for this room.<br/>Possible floor names can be found in `assets/init/settlement/floor`.                                                                    | `WOOD`                                                                                        |
| REQUIRES.\<COMPARATOR\> | no       | none        | none | none | See [requires](require.md)                                                                                                                                            | `GREATER: { WORKFORCE: 3500, },`                                                              |
| ITEMS                   | no       | none        | none | none | See [items](#items-key)                                                                                                                                               | `GREATER: { WORKFORCE: 3500, },`                                                              |
| SPRITES                 | yes      | none        | none | none | See [SPRITES key](#sprites-key)                                                                                                                                       |                                                                                               |


## ADMIN rooms

For `ADMIN_` rooms only.

| Key                      | Required | Default | Min        | Max                                                                                            | Description                                                                                                                                                                                                               | Example                                                          |
|--------------------------|----------|---------|------------|------------------------------------------------------------------------------------------------|---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|------------------------------------------------------------------|
| POP_MIN                  | yes      | none    | 0          | [Integr.MAX_VALUE](https://docs.oracle.com/javase/8/docs/api/java/lang/Integer.html#MAX_VALUE) | Minimum population count for the room to work.                                                                                                                                                                            |                                                                  |
| INCREASE_POW             | yes      | none    | 0.0        | 100.0                                                                                          | How big of an exponential increase of employees is needed for the admin rooms.<br/>The formula is: `((<ALL_EMPLOYEES> - <ROOM_EMPLOYEES>) - (POP_MIN - POP_MIN - (POP_MIN/BOOST_TO ^ 1.0/INCREASE_POW))) ^ INCREASE_POW`. |                                                                  |
| BOOST_FROM               | yes      | none    | 0.0        | 1.0                                                                                            | Minimum boost value used for boosters in `BOOSTING`.                                                                                                                                                                      |                                                                  |
| BOOST_TO                 | yes      | none    | BOOST_FROM | 1000.0                                                                                         | Maximum boost value used for boosters in `BOOSTING`.                                                                                                                                                                      |                                                                  |
| BOOSTING                 | no       | none    | none       | none                                                                                           | A list of booster keys for increasing various things when the admin room is working properly. The boosts will be multiplied.<br/>For possible booster keys see: [all boosters](../res/boosters_all.md)                    | `[ ROOM_FARM*, ROOM_MINE*, WORLD_WORLD_RESOURCE_PRODUCTION_*, ]` |
| WORK                     | no       | none    | none       | none                                                                                           | See [WORK key](#work-key)                                                                                                                                                                                                 |                                                                  |
| INDUSTRY.IN.\<RESOURCE\> | yes      | none    | 0.0        | 10000.0                                                                                        | Input resource consumed when working.<br/>Possible \<RESOURCE\>s can be found in `assets/init/resource`.                                                                                                                  | See [INDUSTRY and INDUSTRIES key](#industry-and-industries-keys) |

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

## SERVICE key

| Key                         | Required | Default | Min  | Max       | Description                                                                               | Example  |
|-----------------------------|----------|---------|------|-----------|-------------------------------------------------------------------------------------------|----------|
| SERVICE.RADIUS              | no       | 150     | 0    | 50000     | How far the services reaches.                                                             |          |
| SERVICE.STANDING.PRIO       | no       | 1.0     | 0.0  | 100000.0  | Used for displaying standings ordered by prio.                                            |          |
| SERVICE.STANDING.INVERTED   | no       | false   | none | none      | Whether the standing shall have a negative effect.                                        |          |
| SERVICE.STANDING.MULTIPLIER | no       | 0.0     | 0.0  | 10000.0   | Multiplier for how much standing it generates.                                            |          |
| SERVICE.STANDING.EXPONENT   | no       | 1.0     | 0.01 | 100000.0  | Exponential factor for increasing the standing.                                           |          |
| SERVICE.STANDING.DISMISS    | no       | false   | none | none      | Whether the standing shall not count to the overall reachable maximum standing.           |          |
| SERVICE.STANDING.NOBLE      | no       | 0.0     | 0.0  | 1000000.0 | How much the standing affects nobles.                                                     |          |
| SERVICE.STANDING.CITIZEN    | no       | 0.0     | 0.0  | 1000000.0 | How much the standing affects normal citizens.                                            |          |
| SERVICE.STANDING.SLAVE      | no       | 0.0     | 0.0  | 1000000.0 | How much the standing affects slaves.                                                     |          |
| SERVICE.STANDING.CHILD      | no       | 0.0     | 0.0  | 1000000.0 | How much the standing affects children.                                                   |          |
| SERVICE.STANDING.OTHER      | no       | 0.0     | 0.0  | 1000000.0 | How much the standing affects all other people living in your settlement.                 |          |
| SERVICE.USAGE               | no       | 1.0     | 0.0  | 1.0       | How much of a service need will be fulfilled when visiting the room.                      |          |
| SERVICE.NEED                | yes      | none    | none | none      | Which need the room will fulfill. Possible needs can be found in `assets/init/stats/need` | `ARENAG` |
| SERVICE.BOOST.\<KEY\>       | no       | none    | none | none      | See [boosts](boost.md)                                                                    |          |

## EMPLOYMENT key

For rooms with employees.

| Key                           | Required | Default | Min  | Max     | Description                                                                                | Example |
|-------------------------------|----------|---------|------|---------|--------------------------------------------------------------------------------------------|---------|
| EMPLOYMENT.SHIFT_OFFSET       | yes      | none    | 0.0  | 0.99    | At which percentage of a day length the work shift starts.                                 |         |
| EMPLOYMENT.NIGHT_SHIFT        | no       | false   | none | none    | Whether employees should work at night.                                                    |         |
| EMPLOYMENT.FULLFILLMENT       | no       | 0.5     | 0.0  | 1.0     | How much fulfillment an employee will get when working in this room.                       |         |
| EMPLOYMENT.ACCIDENTS_PER_YEAR | no       | 0.0     | 0.0  | 10000.0 | Maximum numbers of work accidents which can happen in a year. Actual value will be halved. |         |
| EMPLOYMENT.HEALTH_FACTOR      | no       | 1.0     | 0.0  | 1.0     | How much health will decrease (1.0 means no decrease) when working in this room.           |         |


## WORK key

For rooms with employees.

| Key                     | Required | Default | Min  | Max     | Description                                                                                | Example |
|-------------------------|----------|---------|------|---------|--------------------------------------------------------------------------------------------|---------|
| WORK.SHIFT_OFFSET       | yes      | none    | 0.0  | 0.99    | At which percentage of a day length the work shift starts.                                 |         |
| WORK.NIGHT_SHIFT        | no       | false   | none | none    | Whether employees should work at night.                                                    |         |
| WORK.FULLFILLMENT       | no       | 0.5     | 0.0  | 1.0     | How much fulfillment an employee will get when working in this room.                       |         |
| WORK.ACCIDENTS_PER_YEAR | no       | 0.0     | 0.0  | 10000.0 | Maximum numbers of work accidents which can happen in a year. Actual value will be halved. |         |
| WORK.HEALTH_FACTOR      | no       | 1.0     | 0.0  | 1.0     | How much health will decrease (1.0 means no decrease) when working in this room.           |         |

## ENVIRONMENT_EMIT key

Used for rooms emitting something like noise or light.
Possible \<EMIT\> values:

* _NOISE
* _LIGHT
* _SPACE
* _URBANISATION
* _WATER_SWEET
* _WATER_SALT

| Key                              | Required | Default | Min | Max | Description               | Example |
|----------------------------------|----------|---------|-----|-----|---------------------------|---------|
| ENVIRONMENT_EMIT.\<EMIT\>.VALUE  | yes      | none    | 0.0 | 1.0 | How much it emits.        |         |
| ENVIRONMENT_EMIT.\<EMIT\>.RADIUS | yes      | none    | 0.0 | 1.0 | How far the emit reaches. |         |



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

Industry products with special settings example from `assets/init/room/PASTURE_AUR.txt`:
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
The `SPRITE_KEY`s are unfortunately defined in the source code.

| Key                                  | Required | Default     | Min        | Max      | Description                                                                                                                                                                                                 | Example                                                                                            |
|--------------------------------------|----------|-------------|------------|----------|-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|----------------------------------------------------------------------------------------------------|
| SPRITES.\<SPRITE_KEY\>.TINT          | no       | true        | none       | none     | Will "shade" the sprite randomly when enabled. You can either have TINT or COLOR, not both.                                                                                                                 |                                                                                                    |
| SPRITES.\<SPRITE_KEY\>.COLOR         | no       | 127_127_127 | none       | none     | Will colorize the sprite in given color. Can also be a list of colors. You can either have TINT or COLOR, not both.                                                                                         | `255_255_255` or<br/>`{ R: 255, G: 255, B: 255, }` or as list<br/> `[ 111_111_111, 222_222_222, ]` |
| SPRITES.\<SPRITE_KEY\>.ROTATES       | no       | false       | none       | none     | Whether the sprite can be rotated.                                                                                                                                                                          |                                                                                                    |
| SPRITES.\<SPRITE_KEY\>.SHADOW_LENGTH | no       | 0.0         | 0.0        | 100.0    | Length of the shadow the sprite shall produce.                                                                                                                                                              |                                                                                                    |
| SPRITES.\<SPRITE_KEY\>.SHADOW_HEIGHT | no       | 0.0         | 0.0        | 100.0    | Height of the shadow the sprite shall produce.                                                                                                                                                              |                                                                                                    |
| SPRITES.\<SPRITE_KEY\>.FPS           | no       | 0.0         | 0.0        | 100000.0 | Animation speed in frames per second. Used when the sprite is animated.                                                                                                                                     |                                                                                                    |
| SPRITES.\<SPRITE_KEY\>.FPS_INTERVAL  | no       | 1.0         | 0.0        | 1.0      | Animation consistency.                                                                                                                                                                                      |                                                                                                    |
| SPRITES.\<SPRITE_KEY\>.CIRCULAR      | no       | false       | none       | none     | Whether the animation repeats.                                                                                                                                                                              |                                                                                                    |
| SPRITES.\<SPRITE_KEY\>.FRAMES        | no       | none        | none       | none     | Key value pairs. Consisting of a file name from a subfolder in `assets/sprite/game`<br/>and the number of the tile in given sprite sheet file.<br/>A `-` can be used instead to place an empty dummy frame. | `[ WORK: 0, WORK: 1, BASIN: 1, -, ]`                                                               |
| SPRITES.\<SPRITE_KEY\>.OVERWRITE     | no       | none        | none       | none     | Here you can overwrite properties for each defined frame in `FRAMES`.                                                                                                                                       | `[ {}, {}, {FPS: 15, COLOR: {R:100,G:100,B:100,}, }, ]`                                            |


