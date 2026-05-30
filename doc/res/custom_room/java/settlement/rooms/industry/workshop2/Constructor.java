package settlement.room.industry.workshop2;

import static settlement.main.SETT.ROOMS;

import java.io.IOException;

import init.constant.C;
import init.sprite.game.SheetPair;
import settlement.main.SETT;
import settlement.path.AVAILABILITY;
import settlement.room.main.Room;
import settlement.room.main.RoomBlueprintImp;
import settlement.room.main.TmpArea;
import settlement.room.main.furnisher.Furnisher;
import settlement.room.main.furnisher.FurnisherItem;
import settlement.room.main.furnisher.FurnisherItemTile;
import settlement.room.main.furnisher.FurnisherStat;
import settlement.room.main.util.RoomInit;
import settlement.room.main.util.RoomInitData;
import settlement.room.sprite.RoomSprite;
import settlement.room.sprite.RoomSprite1x1;
import settlement.room.sprite.RoomSpriteCombo;
import snake2d.SPRITE_RENDERER;
import snake2d.util.color.OPACITY;
import snake2d.util.datatypes.AREA;
import snake2d.util.datatypes.COORDINATE;
import snake2d.util.datatypes.DIR;
import snake2d.util.file.Json;
import snake2d.util.sprite.SPRITE;
import util.GUTIL;
import util.rendering.RenderData.RenderIterator;
import util.rendering.ShadowBatch;

final class Constructor extends Furnisher{

    private final ROOM_WORKSHOP2 blue;

    final FurnisherStat workers;
    final FurnisherStat efficiency;
    final FurnisherStat output;

    final static int B_STORAGE = 2;
    final static int B_FETCH = 3;
    final static int B_WORK = 4;

    protected Constructor(ROOM_WORKSHOP2 blue, RoomInitData init)
            throws IOException {
        super(init, 3, 3);
        this.blue = blue;

        workers = new FurnisherStat.FurnisherStatEmployees(this) {
            @Override
            public double get(AREA area, double acc) {
                int am = 0;
                for (COORDINATE c : area.body()) {
                    if (area.is(c))
                        if (SETT.ROOMS().fData.tileData.get(c) == B_WORK || SETT.ROOMS().fData.tileData.get(c) == B_FETCH)
                            am++;
                }
                return am;
            }
        };
        efficiency = new FurnisherStat.FurnisherStatEfficiency(this, workers);
        output = new FurnisherStat.FurnisherStatProduction2(this, blue) {
            @Override
            protected double getBase(AREA area, double[] fromItems) {
                return workers.get(area, fromItems)*efficiency.get(area, fromItems);
            }
        };

        Json js = init.data().json("SPRITES");

        final RoomSprite sChair = new RoomSprite1x1(js, "CHAIR_1X1") {

            @Override
            protected boolean joins(int tx, int ty, int rx, int ry, DIR d, FurnisherItem item) {
                return item.sprite(rx, ry) instanceof RoomSpriteCombo;
            }
        };

        final RoomSprite sStorageTop = new STable(js) {

            @Override
            public boolean render(SPRITE_RENDERER r, ShadowBatch s, int data, RenderIterator it, double degrade,
                                  boolean isCandle) {
                super.render(r, s, data, it, degrade, isCandle);
                Workshop2Instance ins = blue.get(it.tx(), it.ty());
                if (!isCandle && ins != null && ins.industry().outs().size() > 0) {
                    SPRITE i = ins.industry().outs().get(0).resource.icon();
                    OPACITY.O99.bind();
                    i.render(r, it.x()+8, it.x()+C.TILE_SIZE-8, it.y()+8, it.y()+C.TILE_SIZE-8);
                    OPACITY.unbind();
                }
                return false;
            }
        };

        final RoomSprite sStorage = new STable(js) {

            @Override
            public void renderAbove(SPRITE_RENDERER r, ShadowBatch s, int data, RenderIterator it, double degrade) {
                if (!blue.is(it.tile()))
                    return;

                // Each storage renderer only draws on its own subset of tiles.
                blue.job.storage0.render(r, s, it.tx(), it.ty(), it.x(), it.y(), it.ran());
                blue.job.storage1.render(r, s, it.tx(), it.ty(), it.x(), it.y(), it.ran());
            }

        };

        RoomSprite spriteWork = new STable(js) {

            final RoomSprite1x1 under = new RoomSprite1x1(js, "WORK_BELOW_1X1") {

                @Override
                public int frame(SheetPair a, RenderIterator it) {
                    animationSpeed = 0;
                    if (blue.is(it.tile())) {
                        int d = ROOMS().data.get(it.tx(), it.ty());
                        if (blue.job.WORK.working(d)) {
                            animationSpeed = 1.0;
                        }
                    }
                    return super.frame(a, it);
                }

            };
            final RoomSprite1x1 above = new RoomSprite1x1(js, "WORK_ABOVE_1X1") {

                @Override
                public int frame(SheetPair a, RenderIterator it) {
                    animationSpeed = 0;
                    if (blue.is(it.tile())) {
                        int d = ROOMS().data.get(it.tx(), it.ty());
                        if (blue.job.WORK.working(d)) {
                            animationSpeed = 1.0;
                        }
                    }
                    return super.frame(a, it);
                }

                @Override
                protected boolean joins(int tx, int ty, int rx, int ry, DIR d, FurnisherItem item) {
                    rx -= d.x();
                    ry -= d.y();
                    if ((GUTIL.ran2().get(tx, ty) & 0b11) == 0) {
                        d = d.next(2);
                    }else if ((GUTIL.ran2().get(tx, ty) & 0b11) == 1) {
                        d = d.next(-2);
                    }
                    rx += d.x();
                    ry += d.y();
                    return item.sprite(rx, ry) == sChair;
                }

            };

            @Override
            public boolean render(SPRITE_RENDERER r, ShadowBatch s, int data, RenderIterator it, double degrade,
                                  boolean isCandle) {

                super.render(r, s, data, it, degrade, isCandle);
                under.render(r, s, getData2(it), it, degrade, isCandle);
                if (blue.is(it.tile())) {
                    int d = ROOMS().data.get(it.tx(), it.ty());
                    if (blue.job.WORK.working(d)) {
                        Workshop2Instance ins = (Workshop2Instance) blue.get(it.tile());
                        DIR dir = DIR.ORTHO.get(SETT.ROOMS().fData.spriteData2.get(it.tile())&0b11);

                        ins.industry().outs().get(0).resource.renderOne(r, it.x()+dir.x()*8, it.y()+dir.y()*8, it.ran());
                    }
                }
                return false;
            }

            @Override
            public void renderAbove(SPRITE_RENDERER r, ShadowBatch s, int data, RenderIterator it, double degrade) {
                above.render(r, s, getData2(it), it, degrade, false);
            }

            @Override
            public byte getData2(int tx, int ty, int rx, int ry, FurnisherItem item, int itemRan) {
                return above.getData(tx, ty, rx, ry, item, itemRan);
            }

        };

        RoomSprite spriteFetch = new STable(js) {
            @Override
            public boolean render(SPRITE_RENDERER r, ShadowBatch s, int data, RenderIterator it, double degrade,
                                  boolean isCandle) {
                super.render(r, s, data, it, degrade, isCandle);

                if (blue.job.FETCH.get(it.tx(), it.ty(), SETT.ROOMS().map.instance.get(it.tile()))!= null)
                    blue.job.FETCH.render(r, s, it.x(), it.y(), it.ran());
                return false;
            }
        };

        RoomSprite spriteMisc = new STable(js) {

            RoomSprite top = new RoomSprite1x1(js, "TOOL_1X1") {
                @Override
                protected boolean joins(int tx, int ty, int rx, int ry, DIR d, FurnisherItem item) {
                    return d.orthoID() == (GUTIL.ran2().get(tx, ty)&0b11);
                }
            };

            @Override
            public void renderAbove(SPRITE_RENDERER r, ShadowBatch s, int data, RenderIterator it, double degrade) {
                if (!SETT.ROOMS().fData.candle.is(it.tile())) {
                    top.render(r, s, getData2(it), it, degrade, rotates);
                }
            }

            @Override
            public byte getData2(int tx, int ty, int rx, int ry, FurnisherItem item, int itemRan) {
                return top.getData(tx, ty, rx, ry, item, itemRan);
            }

        };

        RoomSprite spriteMisc2 = new RoomSprite1x1(js, "MISC_BELOW_1X1") {

            RoomSprite top = new RoomSprite1x1(js, "MISC_ABOVE_1X1") {

                @Override
                protected boolean joins(int tx, int ty, int rx, int ry, DIR d, FurnisherItem item) {
                    return d.orthoID() == (GUTIL.ran2().get(tx, ty)&0b11);
                }
            };



            @Override
            public boolean render(SPRITE_RENDERER r, ShadowBatch s, int data, RenderIterator it, double degrade,
                                  boolean isCandle) {
                if ((GUTIL.ran2().get(it.tile()) & 0b1) == 1)
                    return false;
                return super.render(r, s, data, it, degrade, isCandle);
            }

            @Override
            public void renderAbove(SPRITE_RENDERER r, ShadowBatch s, int data, RenderIterator it, double degrade) {

                top.render(r, s, getData2(it), it, degrade, false);
            }

            @Override
            public byte getData2(int tx, int ty, int rx, int ry, FurnisherItem item, int itemRan) {
                return top.getData(tx, ty, rx, ry, item, itemRan);
            }

            @Override
            protected boolean joins(int tx, int ty, int rx, int ry, DIR d, FurnisherItem item) {
                return d.orthoID() == (GUTIL.ran2().get(tx, ty)&0b11);
            }

        };

        final FurnisherItemTile ff = new FurnisherItemTile(this, true,spriteFetch, AVAILABILITY.ROOM_SOLID, false).setData(B_FETCH);
        final FurnisherItemTile ww = new FurnisherItemTile(this, false,spriteWork, AVAILABILITY.ROOM_SOLID, false).setData(B_WORK);
        final FurnisherItemTile cc = new FurnisherItemTile(this, false,spriteMisc, AVAILABILITY.ROOM_SOLID, true);
        final FurnisherItemTile mm = new FurnisherItemTile(this, false,spriteMisc2, AVAILABILITY.ROOM_SOLID, false);
        final FurnisherItemTile ss = new FurnisherItemTile(this, true,sChair, AVAILABILITY.AVOID_PASS, false);
        final FurnisherItemTile __ = null;

        final FurnisherItemTile s1 = new FurnisherItemTile(this, false, sStorageTop, AVAILABILITY.ROOM_SOLID, true);
        final FurnisherItemTile s2 = new FurnisherItemTile(this, true, sStorage, AVAILABILITY.ROOM_SOLID, false).setData(B_STORAGE);

        {
            int sw = 4;
            int ew = 10;

            for (int height = 1; height <= 2; height++) {
                for (int width = sw; width <= ew; width++) {
                    FurnisherItemTile[][] tiles = new FurnisherItemTile[height][width];
                    for (int y = 0; y < height; y++) {
                        for (int x = 0; x < width; x++) {
                            tiles[y][x] = x == 0 ? s1 : s2;
                        }
                    }
                    new FurnisherItem(tiles, width*height);
                }
            }
            flush(1, 1, 3);
        }

        new FurnisherItem(new FurnisherItemTile[][] {
                {ff,ww,cc},
                {__,ss,mm},
        }, 2);

        new FurnisherItem(new FurnisherItemTile[][] {
                {ww,ff,ww,cc,},
                {ss,__,ss,mm,},
        }, 3);

        new FurnisherItem(new FurnisherItemTile[][] {
                {ww,ff,ww,cc,ww,ff},
                {ss,__,ss,mm,ss,__},
        }, 5);

        new FurnisherItem(new FurnisherItemTile[][] {
                {ww,ff,ww,cc,ww,ff,ww},
                {ss,__,ss,mm,ss,__,ss},
        }, 6);

        new FurnisherItem(new FurnisherItemTile[][] {
                {ww,ff,ww,cc,ww,ff,ww,cc,ww,ff,},
                {ss,__,ss,mm,ss,__,ss,mm,ss,__},
        }, 8);

        new FurnisherItem(new FurnisherItemTile[][] {
                {ww,ff,ww,cc,ww,ff,ww,cc,ww,ff,ww},
                {ss,__,ss,mm,ss,__,ss,mm,ss,__,ss},
        }, 9);

        new FurnisherItem(new FurnisherItemTile[][] {
                {ww,ff,ww,cc,ww,ff,ww,cc,ww,ff,ww,cc,ww,ff},
                {ss,__,ss,mm,ss,__,ss,mm,ss,__,ss,mm,ss,__},
        }, 11);

        new FurnisherItem(new FurnisherItemTile[][] {
                {ww,ff,ww,cc,ww,ff,ww,cc,ww,ff,ww,cc,ww,ff,ww},
                {ss,__,ss,mm,ss,__,ss,mm,ss,__,ss,mm,ss,__,ss},
        }, 12);

        new FurnisherItem(new FurnisherItemTile[][] {
                {mm,cc,cc,mm},
                {ss,ww,ww,ss},
                {__,ff,ff,__},
        }, 4);

        new FurnisherItem(new FurnisherItemTile[][] {
                {mm,cc,cc,mm},
                {ss,ww,ww,ss},
                {__,ff,ff,__},
                {ss,ww,ww,ss},
        }, 6);

        new FurnisherItem(new FurnisherItemTile[][] {
                {mm,cc,cc,mm},
                {ss,ww,ww,ss},
                {__,ff,ff,__},
                {ss,ww,ww,ss},
                {mm,cc,cc,mm},
                {ss,ww,ww,ss},
                {__,ff,ff,__},
        }, 10);

        new FurnisherItem(new FurnisherItemTile[][] {
                {mm,cc,cc,mm},
                {ss,ww,ww,ss},
                {__,ff,ff,__},
                {ss,ww,ww,ss},
                {mm,cc,cc,mm},
                {ss,ww,ww,ss},
                {__,ff,ff,__},
                {ss,ww,ww,ss},
        }, 12);

        new FurnisherItem(new FurnisherItemTile[][] {
                {mm,cc,cc,mm},
                {ss,ww,ww,ss},
                {__,ff,ff,__},
                {ss,ww,ww,ss},
                {mm,cc,cc,mm},
                {ss,ww,ww,ss},
                {__,ff,ff,__},
                {ss,ww,ww,ss},
                {mm,cc,cc,mm},
                {ss,ww,ww,ss},
                {__,ff,ff,__},
        }, 14);

        new FurnisherItem(new FurnisherItemTile[][] {
                {mm,cc,cc,mm},
                {ss,ww,ww,ss},
                {__,ff,ff,__},
                {ss,ww,ww,ss},
                {mm,cc,cc,mm},
                {ss,ww,ww,ss},
                {__,ff,ff,__},
                {ss,ww,ww,ss},
                {mm,cc,cc,mm},
                {ss,ww,ww,ss},
                {__,ff,ff,__},
                {ss,ww,ww,ss},
        }, 16);

        flush(1, 3);

        new FurnisherItem(new FurnisherItemTile[][] {
                {cc},
        }, 1);

        new FurnisherItem(new FurnisherItemTile[][] {
                {cc,mm},
        }, 2);

        new FurnisherItem(new FurnisherItemTile[][] {
                {mm,cc,mm},
        }, 3);

        new FurnisherItem(new FurnisherItemTile[][] {
                {mm,cc,mm,mm},
        }, 4);

        new FurnisherItem(new FurnisherItemTile[][] {
                {mm,cc,mm,cc,mm},
        }, 5);

        new FurnisherItem(new FurnisherItemTile[][] {
                {mm,cc,mm,mm,cc,mm},
        }, 6);

        new FurnisherItem(new FurnisherItemTile[][] {
                {cc,cc},
                {mm,mm},
        }, 4);

        new FurnisherItem(new FurnisherItemTile[][] {
                {mm,mm},
                {cc,cc},
                {mm,mm},
        }, 6);

        new FurnisherItem(new FurnisherItemTile[][] {
                {mm,mm},
                {cc,cc},
                {cc,cc},
                {mm,mm},
        }, 8);

        new FurnisherItem(new FurnisherItemTile[][] {
                {mm,mm},
                {mm,mm},
                {cc,cc},
                {cc,cc},
                {mm,mm},
        }, 10);

        new FurnisherItem(new FurnisherItemTile[][] {
                {mm,mm},
                {mm,mm},
                {cc,cc},
                {cc,cc},
                {mm,mm},
                {mm,mm},
        }, 12);

        flush(3);
    }

    private static class STable extends RoomSpriteCombo {

        private final RoomSprite top;

        public STable(Json json) throws IOException {
            super(json, "TABLE_COMBO");
            top = new RoomSpriteCombo(json, "TABLE_TOP_COMBO");
        }

        @Override
        public boolean render(SPRITE_RENDERER r, ShadowBatch s, int data, RenderIterator it, double degrade,
                              boolean isCandle) {
            super.render(r, s, data, it, degrade, isCandle);
            top.render(r, s, data, it, degrade, isCandle);
            return false;
        }

    }

    @Override
    public boolean usesArea() {
        return true;
    }

    @Override
    public boolean mustBeIndoors() {
        return true;
    }

    @Override
    public Room create(TmpArea area, RoomInit init) {
        return new Workshop2Instance(blue, area, init);
    }

    @Override
    public RoomBlueprintImp blue() {
        return blue;
    }
    @Override
    public boolean isHeavy() {
        return true;
    }
}
