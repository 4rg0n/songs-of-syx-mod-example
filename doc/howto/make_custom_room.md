# Make a Custom Room

:heart: Made by **[Romping](https://github.com/Max61090)** from the SoS Discord community.

There are two ways of making your kind of room. 
One is to use the present [room configurations](../config/room.md), where you can make a new room with present features.
This how-to guide will make a new room by adding features though, which requires to write some code.

We will make a new `WORKSHOP`, which can produce more than one output item.

## Setting up room code

In your mod project in `src/main/java`, create a new Java package `settlement.rooms.industry.workshop2`.
There you have to add the following four files from [doc/res/custom_room/java/settlement/rooms/industry/workshop2](../res/custom_room/java/settlement/rooms/industry/workshop2):

* `Constructor.java` - Contains information of the furniture and how to construct this room.
* `Job.java` - Defines what workers have to do in the workshop.
* `ROOM_WORKSHOP.java` - Main class, which will handle initialization of the room with read configuration
* `Workshop2Instance.java` - The actual room instance, which contains all the data of and information of the room.

These files are essentially just copies of the original `WORKSHOP` code from `settlement/rooms/industry/workshop`, but with a some modifications in `Workshop2Instance.java`.
You can "[diff](https://www.jetbrains.com/help/idea/comparing-files-and-folders.html)" the content of the two files using Intellij IDEA.

## Registering the room into the actual game

We now have the code files for our new room, but the game still doesn't know anything about them.
Therefore, we have to register them into the game.

For this you have to add a `RoomsCreator` into your [MainScript](../../src/main/java/your/mod/MainScript.java) `initBeforeGameInited()` method.
Unfortunately a lot of the stuff we need isn't accessible from outside, so we need to use [Reflection](https://www.baeldung.com/java-reflection) to get the `init` config data.

```java
public final class MainScript implements SCRIPT {

    @Override
    public void initBeforeGameInited() {
        System.out.println("[EXAMPLE MOD] initBeforeGameInited");
        
        // get the read config data for all rooms from "init"
        RoomInitData init;
        try {
            Field initField = SETT.ROOMS().getClass().getDeclaredField("init");
            initField.setAccessible(true);
            init = (RoomInitData) initField.get(SETT.ROOMS());
        } catch (NoSuchFieldException e) {
            throw new RuntimeException("Could not find field 'init'", e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException("Could not access field 'init'", e);
        }
        
        // register new workshop rooms
        LIST<ROOM_WORKSHOP2> WORKSHOPS2;
        try {
            WORKSHOPS2 = new RoomsCreator<ROOM_WORKSHOP2>(init, ROOM_WORKSHOP2.type,
                SETT.ROOMS().CATS.CRAFTING) {

                @Override
                public ROOM_WORKSHOP2 create(String key, RoomInitData data, RoomCategorySub cat, int index) throws IOException {
                    return new ROOM_WORKSHOP2(index, data, key, cat);
                }

            }.all();
        } catch (IOException e) {
            throw new RuntimeException("Could not create room", e);
        }
    }
    
    //...
}
```

This will make the room(s) appear in the build menu in the `WORKSHOP` category.

## Registering the room into the work AI

This is required for you citizens so they consider working in this new room(s).
In your [MainScript](../../src/main/java/your/mod/MainScript.java) class, add this into your `initBeforeGameCreated()` method:

```java
public final class MainScript implements SCRIPT {
    
    //...

    @Override
    public void initBeforeGameCreated() {
        System.out.println("[EXAMPLE MOD] initBeforeGameCreated");

        // get the read config data for all rooms from "init"
        RoomInitData init;
        try {
            Field initField = SETT.ROOMS().getClass().getDeclaredField("init");
            initField.setAccessible(true);
            init = (RoomInitData) initField.get(SETT.ROOMS());
        } catch (NoSuchFieldException e) {
            throw new RuntimeException("Could not find field 'init'", e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException("Could not access field 'init'", e);
        }

        // register new workshop rooms
        LIST<ROOM_WORKSHOP2> WORKSHOPS2;
        try {
            WORKSHOPS2 = new RoomsCreator<ROOM_WORKSHOP2>(init, ROOM_WORKSHOP2.type,
                SETT.ROOMS().CATS.CRAFTING) {

                @Override
                public ROOM_WORKSHOP2 create(String key, RoomInitData data, RoomCategorySub cat, int index) throws IOException {
                    return new ROOM_WORKSHOP2(index, data, key, cat);
                }

            }.all();
        } catch (IOException e) {
            throw new RuntimeException("Could not create room", e);
        }

        // register work AI for new workshop2 rooms
        for (RoomBlueprintIns<?> p : WORKSHOPS2)
            new WorkAbs(this, p, map, w);
    }
    
    //...
}
```

## Registering the room to handle production rates

This gets a little bit trickier, because the game class `settlement.room.industry.module.FlatIndustries` is very closed form the outside.
We have to utilize [Reflection](https://www.baeldung.com/java-reflection) to [call the private method](https://www.baeldung.com/java-call-private-method) `make` for this.

```java
public final class MainScript implements SCRIPT {
    
    //...

    public void initBeforeGameCreated() {
        System.out.println("[EXAMPLE MOD] initBeforeGameCreated");

        // get the read config data for all rooms from "init"
        RoomInitData init;
        try {
            Field initField = SETT.ROOMS().getClass().getDeclaredField("init");
            initField.setAccessible(true);
            init = (RoomInitData) initField.get(SETT.ROOMS());
        } catch (NoSuchFieldException e) {
            throw new RuntimeException("Could not find field 'init'", e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException("Could not access field 'init'", e);
        }

        // register new workshop rooms
        LIST<ROOM_WORKSHOP2> WORKSHOPS2;
        try {
            WORKSHOPS2 = new RoomsCreator<ROOM_WORKSHOP2>(init, ROOM_WORKSHOP2.type,
                SETT.ROOMS().CATS.CRAFTING) {

                @Override
                public ROOM_WORKSHOP2 create(String key, RoomInitData data, RoomCategorySub cat, int index) throws IOException {
                    return new ROOM_WORKSHOP2(index, data, key, cat);
                }

            }.all();
        } catch (IOException e) {
            throw new RuntimeException("Could not create room", e);
        }

        // register work AI for new workshop2 rooms
        for (RoomBlueprintIns<?> p : WORKSHOPS2)
            new WorkAbs(this, p, map, w);

        // register room productions via private "make" method
        FlatIndustries flatIndustries = SETT.ROOMS().industries.flat;
        try {
            Method makeMethod = FlatIndustries.class.getDeclaredMethod("make", LIST.class);
            makeMethod.setAccessible(true);
            makeMethod.invoke(flatIndustries, WORKSHOPS2);
        } catch (NoSuchMethodException e) {
            throw new RuntimeException("Could not find 'make' method.", e);
        } catch (IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException("Could not call 'make' method.", e);
        }
    }
    
    //...
}
```

## Adding room init configuration

We still don't have a room yet. For this, we have to implement a room init configuration for it.
This will make the game create a room based on our code.
Add a file called `WORKSHOP2_STARVEIN.txt` into [src/main/resources/mod-files/assets/init/room](../../src/main/resources/mod-files/assets/init/room).
The prefix `WORKSHOP2_` tells the game to use our custom-made code for it.

### `WORKSHOP2_STARVEIN.txt`

```
ICON: 32->WORKSHOP->4,
RESOURCES: [WOOD,METAL,],
AREA_COSTS: [0,0,],
FLOOR: STONE_MEDIUM_DARK,
MINI_COLOR: 226_195_38,
ITEMS: [
	{
		COSTS: [1,0.5,0,],
		STATS: [0,0,0,],
	},
	{
		COSTS: [1,0.5,0,],
		STATS: [1,0,1,],
	},
	{
		COSTS: [1,0.5,0,],
		STATS: [0,2,0,],
	},
],
WORK: {
	SHIFT_OFFSET: 0.325,
	SOUND: impact->Mason*,
	ACCIDENTS_PER_YEAR: 0.025,
	FULFILLMENT: 1.0,
},
INDUSTRIES: [
	{
		INDUSTRY: {
			IN: {
				STONE: 0.9,
				COAL: 1.7,
			},
			OUT: {
				STONE_CUT: 0.2,
				MACHINERY: 0.4,
			},
		},
	},
	{
		INDUSTRY: {
			IN: {
				_WOOD: 0.9,
				COAL: 1.7,
			},
			OUT: {
				PAPER: 0.1,
				TOOL: 0.6,
			},
		},
	},
],
ENVIRONMENT_EMIT: {
	_NOISE: {
		VALUE: 1,
		RADIUS: 1,
	},
},
SPRITES: {
	CHAIR_1X1: [
		{
			SHADOW_HEIGHT: 4,
			FRAMES: [
				CHAIRS: 10,
				CHAIRS: 11,
			],
		},
	], 
	TABLE_COMBO: [
		{
			SHADOW_HEIGHT: 4,
			COLOR: {R:200,G:160,B:160,},
			FRAMES: [
				COMBO_TABLES: 4,
			],
		},
	],
	TABLE_TOP_COMBO: [
		{
			TINT: true,
			FRAMES: [
				ONTOP: 0,
			],
		},
	],
	WORK_BELOW_1X1: [
		{
			FRAMES: [ 
				
			],
		},
	],
	WORK_ABOVE_1X1: [
		{
			SHADOW_LENGTH: 3,
			TINT: true,
			FPS: 5,
			FRAMES: [
				WORK: 2,
				WORK: 3,
				WORK: 4,
				WORK: 5,
				WORK: 6,
				WORK: 7,
				WORK: 8,
				WORK: 9,
				WORK: 10,
			],
		},
	],
	TOOL_1X1: [
		{
			SHADOW_LENGTH: 2,
			TINT: true,
			ROTATES: false,
			FRAMES: [
				WORK: 11,
				WORK: 12,
				WORK: 13,
				WORK: 14,
				WORK: 15,
			],
		},
	],
	MISC_BELOW_1X1: [
		{
			SHADOW_LENGTH: 1,
			TINT: true,
			ROTATES: false,
			FRAMES: [
				STORAGE: 5,
				STORAGE: 6,
				STORAGE: 8,
			],
		},
	],
	MISC_ABOVE_1X1: [
		{
			SHADOW_LENGTH: 5,
			TINT: true,
			ROTATES: false,
			FRAMES: [
				STORAGE: 0,
				STORAGE: 1,
				STORAGE: 2,
				STORAGE: 10,
				WORK: 2,
				WORK: 3,
				WORK: 4,
				WORK: 5,
				WORK: 6,
				WORK: 7,
				WORK: 8,
				WORK: 9,
				WORK: 10,
				RESOURCE: 2,
				RESOURCE: 2,
				RESOURCE: 2,
				RESOURCE: 2,
			],
		},
	],
},
```

There we defined two recipes in the `INDUESTRIES` key. Both have two outputs in the `OUT` key:

```
INDUSTRIES: [
	{
		INDUSTRY: {
			IN: {
				STONE: 0.9,
				COAL: 1.7,
			},
			OUT: {
				STONE_CUT: 0.2,
				MACHINERY: 0.4,
			},
		},
	},
	{
		INDUSTRY: {
			IN: {
				_WOOD: 0.9,
				COAL: 1.7,
			},
			OUT: {
				PAPER: 0.1,
				TOOL: 0.6,
			},
		},
	},
],
```

