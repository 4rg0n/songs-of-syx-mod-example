package settlement.room.industry.workshop2;

import static settlement.main.SETT.ROOMS;

import game.audio.SoundRace;
import init.resources.RBIT;
import init.resources.RESOURCE;
import settlement.entity.humanoid.Humanoid;
import settlement.main.SETT;
import settlement.misc.job.SETT_JOB;
import settlement.room.industry.module.IndustryResource;
import settlement.room.industry.module.ROOM_PRODUCER_INSTANCE;
import settlement.room.main.ROOMA;
import settlement.room.main.job.RoomResDeposit;
import settlement.room.main.job.RoomResStorage;
import snake2d.util.datatypes.COORDINATE;
import snake2d.util.datatypes.Coo;
import snake2d.util.datatypes.DIR;

class Job {

    private final ROOM_WORKSHOP2 print;
    final Work WORK = new Work();
    final RoomResDeposit FETCH;

    private static boolean isOut0Tile(int tx, int ty) {
        return ((tx + ty) & 1) == 0;
    }

    private static int outsCount(ROOMA ins) {
        if (ins instanceof ROOM_PRODUCER_INSTANCE)
            return ((ROOM_PRODUCER_INSTANCE) ins).industry().outs().size();
        return 0;
    }

    final RoomResStorage storage0 = new RoomResStorage(0b011111) {

        @Override
        public RESOURCE resource() {
            ROOM_PRODUCER_INSTANCE ins = (ROOM_PRODUCER_INSTANCE) SETT.ROOMS().map.get(this);
            if (ins == null || ins.industry().outs().size() == 0)
                return null;
            return ins.industry().outs().get(0).resource;
        }

        @Override
        protected boolean is(int tx, int ty) {
            if (SETT.ROOMS().fData.tileData.get(tx, ty) != Constructor.B_STORAGE)
                return false;

            int oc = outsCount(ins);

            if (oc <= 1)
                return true;

            return isOut0Tile(tx, ty);
        }

        @Override
        protected void changed(int tx, int ty) {
            if (hasRoom()) {
                Workshop2Instance m = print.get(tx, ty);
                if (m != null) {
                    m.hasStorage0 = true;
                    m.jobs.searchAgain();
                }
            }
        }
    };

    final RoomResStorage storage1 = new RoomResStorage(0b011111) {

        @Override
        public RESOURCE resource() {
            ROOM_PRODUCER_INSTANCE ins = (ROOM_PRODUCER_INSTANCE) SETT.ROOMS().map.get(this);
            if (ins == null || ins.industry().outs().size() <= 1)
                return null;
            return ins.industry().outs().get(1).resource;
        }

        @Override
        protected boolean is(int tx, int ty) {
            int oc = outsCount(ins);
            if (oc <= 1)
                return false;

            return SETT.ROOMS().fData.tileData.get(tx, ty) == Constructor.B_STORAGE && !isOut0Tile(tx, ty);
        }

        @Override
        protected void changed(int tx, int ty) {
            if (hasRoom()) {
                Workshop2Instance m = print.get(tx, ty);
                if (m != null) {
                    m.hasStorage1 = true;
                    m.jobs.searchAgain();
                }
            }
        }
    };

    Job(ROOM_WORKSHOP2 print) {
        this.print = print;
        FETCH = new RoomResDeposit(print) {

            @Override
            protected boolean is(int tx, int ty) {
                return SETT.ROOMS().fData.tileData.get(tx, ty) == Constructor.B_FETCH;
            }

            @Override
            protected void hasCallback() {
            }

            @Override
            protected boolean regularJobCanBeReserved(COORDINATE coo) {
                Workshop2Instance ins = print.get(coo.x(), coo.y());
                if (ins == null)
                    return false;

                if (!ins.hasStorage0)
                    return false;

                if (ins.industry().outs().size() > 1 && !ins.hasStorage1)
                    return false;

                return true;
            }

            @Override
            protected void regularJobStore(COORDINATE coo, int am) {
                Workshop2Instance ins = print.get(coo.x(), coo.y());
                if (ins == null)
                    return;

                am = depositToStorage(storage0, ins, am);
                if (am > 0)
                    ins.hasStorage0 = false;
            }

        };
    }

    private static int depositToStorage(RoomResStorage storage, Workshop2Instance ins, int am) {
        if (am <= 0)
            return 0;

        for (COORDINATE c : ins.body()) {
            if (!ins.is(c))
                continue;

            RoomResStorage ss = storage.get(c.x(), c.y(), ins);
            if (ss == null)
                continue;

            while (am > 0 && ss.hasRoom()) {
                ss.deposit();
                am--;
            }
            if (am <= 0)
                return 0;
        }
        return am;
    }

    SETT_JOB init(int tx, int ty, Workshop2Instance ins) {
        if (!ins.is(tx, ty))
            return null;
        if (SETT.ROOMS().fData.tileData.is(tx, ty, Constructor.B_WORK))
            return WORK.init(tx, ty, ins);
        return FETCH.get(tx, ty, ins);
    }

    static DIR find(COORDINATE coo, int data, Workshop2Instance ins) {
        for (DIR d : DIR.ORTHO) {
            if (ins.is(coo, d) && SETT.ROOMS().fData.tileData.get(coo, d) == data)
                return d;
        }
        throw new RuntimeException();
    }

    static DIR find(int tx, int ty, int data, Workshop2Instance ins) {
        for (DIR d : DIR.ORTHO) {
            if (ins.is(tx, ty, d) && SETT.ROOMS().fData.tileData.get(tx, ty, d) == data)
                return d;
        }
        throw new RuntimeException();
    }

    final class Work implements SETT_JOB {

        private static final int BITRESERVED = 0b1000000000000000;
        private final static int BIT_STORAGE_FULL = BITRESERVED >> 1;
        private final static int BIT_HAS_RAW = BIT_STORAGE_FULL >> 1;
        private final static int IS_WORKING = BIT_HAS_RAW >> 1;

        // Remainder for OUT1 minting, stored in low bits of `data`.
        // NOTE: This assumes nothing else uses low bits on WORK tiles for this room.
        private static final int REM_BITS = 12;
        private static final int REM_MASK = (1 << REM_BITS) - 1; // 4095
        private static final double REM_SCALE = (double) (1 << REM_BITS);

        private double rem1Get() {
            return (data & REM_MASK) / REM_SCALE;
        }

        private void rem1Set(double r) {
            if (r <= 0) {
                data &= ~REM_MASK;
                return;
            }
            if (r >= 0.999999)
                r = 0.999999;

            int v = (int) (r * REM_SCALE);
            if (v < 0) v = 0;
            if (v > REM_MASK) v = REM_MASK;

            data = (data & ~REM_MASK) | v;
        }

        private final Coo coo = new Coo();
        Workshop2Instance ins;
        int data;

        Work() {
        }

        @Override
        public boolean jobReserveCanBe() {
            if (jobReservedIs(null))
                return false;

            if (!ins.hasStorage0)
                return false;
            if (ins.industry().outs().size() > 1 && !ins.hasStorage1)
                return false;

            DIR d = find(coo.x(), coo.y(), Constructor.B_FETCH, ins);
            if (!FETCH.get(coo.x() + d.x(), coo.y() + d.y(), ins).hasOneOfEach())
                return false;

            return true;
        }

        Work init(int tx, int ty, Workshop2Instance ins) {
            data = ROOMS().data.get(tx, ty);
            coo.set(tx, ty);
            this.ins = ins;
            return this;
        }

        void save() {
            int d = ROOMS().data.get(coo);

            if ((d & IS_WORKING) != 0) {
                ins.WI--;
            }
            if ((data & IS_WORKING) != 0) {
                ins.WI++;
            }

            ROOMS().data.set(ins, coo, data);
        }

        @Override
        public COORDINATE jobCoo() {
            return coo;
        }

        @Override
        public CharSequence jobName() {
            return ins.blueprintI().employment().verb;
        }

        @Override
        public boolean jobUseTool() {
            return false;
        }

        boolean working(int data) {
            return (data & IS_WORKING) != 0;
        }

        @Override
        public RBIT jobResourceBitToFetch() {
            return null;
        }

        @Override
        public double jobPerformTime(Humanoid skill) {
            return wv;
        }

        @Override
        public void jobReserve(RESOURCE r) {
            if (jobReservedIs(null))
                throw new RuntimeException();
            data |= BITRESERVED;
            save();
            DIR d = find(coo.x(), coo.y(), Constructor.B_FETCH, ins);
            FETCH.get(coo.x() + d.x(), coo.y() + d.y(), ins).withdrawOneOfEach();
        }

        @Override
        public boolean jobReservedIs(RESOURCE r) {
            return (data & BITRESERVED) == BITRESERVED;
        }

        @Override
        public void jobReserveCancel(RESOURCE r) {
            data &= ~BITRESERVED;
            data &= ~IS_WORKING;
            save();
            DIR d = find(coo.x(), coo.y(), Constructor.B_FETCH, ins);
            FETCH.get(coo.x() + d.x(), coo.y() + d.y(), ins).depositOneOfEach();
            ins.jobs.searchAgainButDontReset();
        }

        @Override
        public void jobStartPerforming() {
            if ((data & IS_WORKING) == 0) {
                ins.WI++;
            }
            data |= IS_WORKING;
            save();
        }

        @Override
        public SoundRace jobSound() {
            return ins.blueprintI().employment().sound();
        }

        private final int wv = 45;

        @Override
        public RESOURCE jobPerform(Humanoid s, RESOURCE res, int ram) {

            double w = ins.employees().fetchBonus(wv);

            DIR d = find(coo, Constructor.B_FETCH, ins);
            RoomResDeposit dep = FETCH.get(coo.x() + d.x(), coo.y() + d.y(), ins);
            FETCH.get(coo.x() + d.x(), coo.y() + d.y(), ins).depositOneOfEach();

            int outs = ins.industry().outs().size();
            if (!ins.hasStorage0 || (outs > 1 && !ins.hasStorage1)) {
                data &= ~BITRESERVED;
                data &= ~IS_WORKING;
                save();
                return null;
            }

            int ri = 0;
            for (IndustryResource r : ins.industry().ins()) {
                int a = r.work(s, ins, w);
                if (a > 0) {
                    int dd = dep.amount(ri);
                    if (dd < a) {
                        double www = w * dd / a;
                        if (www < w)
                            w = www;
                        r.inc(ins, -(a - dd));
                        a = dd;
                    }
                    dep.withDraw(ri, a);
                }
                ri++;
            }

            if (outs <= 0) {
                data &= ~BITRESERVED;
                data &= ~IS_WORKING;
                save();
                return null;
            }

            // Produce OUT0 normally (stateful work() called once)
            IndustryResource out0 = ins.industry().outs().get(0);
            int am0 = out0.work(s, ins, w);

            int left0 = depositToStorage(storage0, ins, am0);
            if (left0 > 0)
                ins.hasStorage0 = false;

            // Option A: derive OUT1 from OUT0 using rate ratio + carried remainder
            if (outs > 1) {
                IndustryResource out1 = ins.industry().outs().get(1);

                double r0 = out0.rate;
                double r1 = out1.rate;

                if (am0 > 0 && r0 > 0 && r1 > 0) {
                    double ratio = r1 / r0;

                    double carry = rem1Get();
//                    System.out.println("carryBefore=" + rem1Get() + " data=" + data);
                    /// i guess its fine now???. Appears to take a bit but it does eventualyl stabilize
                    double want = am0 * ratio + carry;

                    int am1 = (want > 0) ? (int) want : 0;   // floor for positive
                    carry = want - am1;

                    rem1Set(carry);

                    if (am1 > 0) {
                        int left1 = depositToStorage(storage1, ins, am1);
                        int deposited1 = am1 - left1;

                        // Since we did NOT call out1.work(), we must account manually for what actually landed.
                        if (deposited1 > 0)
                            out1.inc(ins, deposited1);

                        if (left1 > 0)
                            ins.hasStorage1 = false;
                    }
                }
            }

            data &= ~BITRESERVED;
            data &= ~IS_WORKING;

            save();
            return null;
        }
    }
}
