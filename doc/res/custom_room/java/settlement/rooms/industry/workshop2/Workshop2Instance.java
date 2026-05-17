package settlement.room.industry.workshop2;

import static settlement.main.SETT.ROOMS;

import settlement.main.SETT;
import settlement.misc.job.JOBMANAGER_HASER;
import settlement.misc.job.JOB_MANAGER;
import settlement.misc.job.SETT_JOB;
import settlement.misc.util.RESOURCE_TILE;
import settlement.room.industry.module.Industry;
import settlement.room.industry.module.ROOM_PRODUCER_INSTANCE;
import settlement.room.main.RoomInstance;
import settlement.room.main.TmpArea;
import settlement.room.main.job.JobPositions;
import settlement.room.main.util.RoomInit;
import snake2d.Renderer;
import snake2d.util.datatypes.COORDINATE;
import util.rendering.RenderData;
import util.rendering.ShadowBatch;

final class Workshop2Instance extends RoomInstance implements JOBMANAGER_HASER, ROOM_PRODUCER_INSTANCE{

    JobPositions<Workshop2Instance> jobs;
    private static final long serialVersionUID = -3170637142258642320l;
    private long[] pData;

    boolean hasStorage0 = false;
    boolean hasStorage1 = false;

    short WI = 0;
    private short industry = -1;

    Workshop2Instance(ROOM_WORKSHOP2 b, TmpArea area, RoomInit init) {
        super(b, area, init);
        setIndustry(0);

        jobs = new Jobs(this);

        employees().maxSet(jobs.size());
        employees().neededSet(jobs.size());
        activate();

        recalcStorageFlags();
    }

    private void recalcStorageFlags() {
        boolean any0 = false;
        boolean any1 = false;

        for (COORDINATE c : body()) {
            if (!is(c))
                continue;
            if (SETT.ROOMS().fData.tileData.get(c) != Constructor.B_STORAGE)
                continue;

            if (((c.x() + c.y()) & 1) == 0) any0 = true;
            else any1 = true;
        }

        hasStorage0 = any0;
        hasStorage1 = any1;
    }

    @Override
    public Industry industry() {
        return blueprintI().indus.get(industry);
    }

    @Override
    public void setIndustry(int i) {

        if (i == industry)
            return;

        Industry in = blueprintI().industries().get(i);
        if (in == null)
            return;
        pData = in.makeData();

        if (industry != -1) {
            // cancel work/fetch jobs on switch (same pattern you already had)
            for (COORDINATE c : body()) {
                if (!is(c))
                    continue;
                if (SETT.ROOMS().fData.tileData.is(c.x(), c.y(), Constructor.B_WORK)) {
                    if (blueprintI().job.WORK.init(c.x(), c.y(), this).jobReservedIs(null)) {
                        blueprintI().job.WORK.jobReserveCancel(null);
                    }
                }
            }
            for (COORDINATE c : body()) {
                if (!is(c))
                    continue;
                if (SETT.ROOMS().fData.tileData.is(c.x(), c.y(), Constructor.B_FETCH)) {
                    if (blueprintI().job.FETCH.get(c.x(), c.y(), this) != null)
                        blueprintI().job.FETCH.dispose();
                }
            }

            // If outputs changed, clear storage jobs so old resource amounts don’t carry over
            Industry old = blueprintI().indus.get(industry);
            if (old != null) {
                boolean out0Changed = old.outs().size() > 0 && in.outs().size() > 0
                        && old.outs().get(0).resource != in.outs().get(0).resource;

                boolean out1Changed = (old.outs().size() > 1 || in.outs().size() > 1)
                        && (old.outs().size() <= 1 || in.outs().size() <= 1
                        || old.outs().get(1).resource != in.outs().get(1).resource);

                if (out0Changed || out1Changed) {
                    for (COORDINATE c : body()) {
                        if (!is(c))
                            continue;
                        if (SETT.ROOMS().fData.tileData.get(c) != Constructor.B_STORAGE)
                            continue;

                        // dispose whichever storage “owns” this tile
                        if (((c.x()+c.y()) & 1) == 0) {
                            if (blueprintI().job.storage0.get(c.x(), c.y(), this) != null)
                                blueprintI().job.storage0.dispose();
                        } else {
                            if (blueprintI().job.storage1.get(c.x(), c.y(), this) != null)
                                blueprintI().job.storage1.dispose();
                        }
                    }
                }
            }

            jobs.searchAgain();
        }

        WI = 0;
        industry = (byte) i;

        recalcStorageFlags();
    }

    @Override
    protected boolean render(Renderer r, ShadowBatch shadowBatch, RenderData.RenderIterator it) {
        it.lit();
        return super.render(r, shadowBatch, it);
    }

    @Override
    protected void activateAction() {
    }

    @Override
    protected void deactivateAction() {
    }

    @Override
    protected void updateAction(double updateInterval, boolean day) {

        industry().updateRoom(this);

        if (!active())
            return;
        jobs.searchAgain();
        updateIndustryLocks();
    }

    @Override
    protected void dispose() {

        for (COORDINATE c : body()) {
            if (!is(c))
                continue;

            if (blueprintI().job.storage0.get(c.x(), c.y(), this) != null)
                blueprintI().job.storage0.dispose();
            else if (blueprintI().job.storage1.get(c.x(), c.y(), this) != null)
                blueprintI().job.storage1.dispose();
            else if (blueprintI().job.FETCH.get(c.x(), c.y(), this) != null)
                blueprintI().job.FETCH.dispose();
        }
    }

    @Override
    public JOB_MANAGER getWork() {
        return jobs;
    }

    @Override
    public ROOM_WORKSHOP2 blueprintI() {
        return (ROOM_WORKSHOP2)blueprint();
    }

    @Override
    public RESOURCE_TILE resourceTile(int tx, int ty) {
        RESOURCE_TILE t = blueprintI().job.storage0.get(tx, ty, this);
        if (t != null)
            return t;
        return blueprintI().job.storage1.get(tx, ty, this);
    }

    @Override
    public long[] productionData() {
        return pData;
    }

    static class Jobs extends JobPositions<Workshop2Instance> {

        public Jobs(Workshop2Instance ins) {
            super(ins);
        }

        private static final long serialVersionUID = 8423260307910904017l;
        @Override
        protected boolean isAndInit(int tx, int ty) {
            return get(tx, ty) != null;
        }

        @Override
        protected SETT_JOB get(int tx, int ty) {
            return ins.blueprintI().job.init(tx, ty, ins);
        }
    }

    @Override
    public int industryI() {
        return industry;
    }

}
