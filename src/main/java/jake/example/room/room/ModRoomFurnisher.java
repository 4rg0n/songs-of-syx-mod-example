package jake.example.room.room;

import java.io.IOException;

import settlement.path.AVAILABILITY;
import settlement.room.main.*;
import settlement.room.main.furnisher.*;
import settlement.room.main.util.RoomInit;
import settlement.room.main.util.RoomInitData;
import settlement.room.sprite.RoomSprite;
import settlement.room.sprite.RoomSprite1x1;
import snake2d.util.file.Json;

/**
 * The most cumbersome part... Deals with the whole furnishing and construction process of a new room.
 * @author Jake
 */
class ModRoomFurnisher extends Furnisher {

	private final ModRoomBlueprint blueprint;
	
	protected ModRoomFurnisher(ModRoomBlueprint blueprint, RoomInitData init) throws IOException {
		/*
		 * pass along init, and state hw many items this room has, and how many "stats". A stat can be "workers", "efficiency" or "services".
		 * These must match the actual case. We'll skip stats for now, and just do one item.
		 */
		super(init, 1, 0, 88, 44);
		this.blueprint = blueprint;
		
		//now we must construct the item. We will start with

		/*
		 * SPRITES
		 */
		
		//We will use the new sprite system, which is json based. The actual visuals are in the .txt init file.
		Json sjson = init.data().json("SPRITES");
		
		//rooms use different kind of sprites. The easiest one is just simply a 1x1 tile. We'll use that.
		RoomSprite sprite = new RoomSprite1x1(sjson, "PRIMARY_1X1");
		
		/*
		 * TILES
		 * Now we'll create a tile with the sprite and give it some properties
		 */
		
		FurnisherItemTile tt = new FurnisherItemTile(this, sprite, AVAILABILITY.SOLID, false);
		
		/*
		 * ITEM from tiles
		 */
		
		FurnisherItemTile [][] itTiles = new FurnisherItemTile [][] {
			{tt},
		};
		
		/*
		 * The '1' is a multiplier that will be applied to the stats and resource cost of this item.
		 */
		new FurnisherItem(itTiles, 1);
		
		//let's make another item
		
		itTiles = new FurnisherItemTile [][] {
			{tt,tt,tt},
			{tt,tt,tt},
		};
		
		new FurnisherItem(itTiles, 6);
		
		// now me must do this:
		flush(3);
		
	}

	/**
	 * If the rooms is a single item, or if you paint an area and place several.
	 */
	@Override
	public boolean usesArea() {
		return false;
	}

	@Override
	public Room create(TmpArea area, RoomInit init) {
		return new ModRoomInstance(blueprint, area, init);
	}
	
	@Override
	public boolean mustBeIndoors() {
		return false;
	}

	@Override
	public RoomBlueprintImp blue() {
		return blueprint;
	}
}