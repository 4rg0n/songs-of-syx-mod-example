# Combat Mechanics Guide and Definition

:heart: Made by **[Romping](https://github.com/Max61090)** from the SoS Discord community.

This guide explains the process of how damage is dealt, calculating, and avoided in Songs of Syx **V70**. 
It describes the exact order of operations and provides some reference statistics for players to understand (**Updated as of Feb 2nd, 2026**)

Relevant files are: `FightingUtil.java` and `Humanoid.java`

## Introduction: Core Components

### Core Stats for all Races

* `OFFENCE_SKILL` - the **attack skill** in melee dodge checks.
* `DEFENCE_SKILL` (`DEFENCE_AGILITY`) - the **defence skill** (used to dodge) that fully negates against frontal attacks when successful
* `BATTLE_BLOCK` (or `PARRY`) - a block skill which reduces damage when successful.
* `DEXTERITY` - used to:
  * **Bypass blocks for both melee and ranged.** Note: in the case of ranged, **the dexterity that matters is the weapon’s dexterity not the users**! (e.g. catapults have dexterity)
  * Make impact/charge attacks harder to dodge (added to speed).
  
* `BLUNT_ATTACK` (Force in game) - base strength for melee damage and impact damage.
* `BLUNT_DEFENCE` (Force absorption in game) - reduces total damage taken.
* `BLUNT_DEFENCE_DIR` (Block Armor) - reduces damage on a successful block for specific directions.
* `FORMATION_SKILL` - formation bonus to defence/block if in a division.
* `CHARGE` - directly multipliers charge damage! Multiplied by speed. Powerful modifier for aggressive unit archetypes (e.g. lance cavalry).


* For each damage type in `DAMAGES`:
  * `DAMAGES[i].attack` - base damage of that type.
  * `DAMAGES[i].defence` - reduces that type in getDamageDone.
  * `DAMAGES[i].defenceDir` - reduces that type of damage on a block.


#### Geometry / direction
  * Direction matters because `DEFENCE_SKILL` or `FORMATION_SKILL` only applies to frontal attacks


#### Terrain
  * Tile height around attacker/defender affects both **damage output** and **damage received**. (Unsure how this is usable by players in a 2D map)
  

#### Formation
  * If a unit belongs to a Div (division), its **formation facing** can give a bonus to defence and parry when attacked from the front. (for example, Dondorians have 1.25x bonus to `FORMATION_SKILL` like the classic Dwarves)


#### Configs
  * Two values are used to affect combat formulae:
    * `CHANCE_MIN = 1 / DAMAGE_REDUCTION`
    * `CHANCE_SPAN = DAMAGE_REDUCTION - CHANCE_MIN`
  * These are used as random multipliers in dodge/block and damage calculations. Lower `DAMAGE_REDUCTION` = more damage funnily enough.
  * `Config.battle().DAMAGE_REDUCTION` (assets/init/config/Battle.txt) defines `CHANCE_MIN` and `CHANCE_SPAN`. Default values are:
    * `DAMAGE_REDUCTION` = 100.
    * `MORALE_HOLDOUT` = 1 (Have not found where this is used)
    * So, `CHANCE_MIN = 0.01` and `CHANCE_SPAN = 99.99`

## Calculating Damage Received: How does dodge, block, armor, and force absorption work? 

**For melee attacks, the following calculations are performed in order!**

### Compute geometry

* Direction vector attacker → target. (Where attack is coming from). Some things like dodging will be much less likely during rear or flank attacks.

#### Dodge check

* **What is Dodge?** Uses attacker’s offence vs defender’s defense skill (which includes DEFENSE and formation) to **try to completely negate damage**.

With a bit of math or just some quick testing (link to test the code [here](https://onecompiler.com/java/4478ps8fc)).

```java
import java.util.*;

public class Main {
    public static void main(String[] args) {
      double hits = 0;
      double NumberOfTries = 10000;
      double DamageReduction = 20;
      double CHANCE_MIN = 1 / DamageReduction;
      double CHANCE_SPAN = DamageReduction - CHANCE_MIN;
      double OffenceSkill = 0.5;
      double DefenceSkill = 1;
      
      double q = OffenceSkill / DefenceSkill;
    
      for (int i = 0; i < NumberOfTries; i++) {
          double X  = CHANCE_MIN + Math.random()  * CHANCE_SPAN;
          double result  = q / X;
          double RandomNumber = Math.random();
          
          if (result > RandomNumber)
              hits++;
      }
      double hitChance = hits / NumberOfTries;
      
      System.out.print("hitChance is " + hitChance);
    }
}
```

This means:

| Offence/Defence Skill Ratio (q) | Hit Chance (Vanilla Damage Reduction of 100) |
|---------------------------------|----------------------------------------------|
| 0.5                             | 3.1%                                         |
| 1                               | 5.6%                                         |
| 2                               | 9.8%                                         |
| 5                               | 20%                                          |
| 10                              | 33%                                          |

##### Direction Multiplier

* `1x` = hit from the front (incoming direction opposite their facing)
* `0.5x` = attack from flank
* `0x` = attack from back

This is fed in the equation of `0.9 * multiplier + 0.1`.

**So defence skill from the back is 10% of the original value and from the flank is 55%.**

**Raw Code:**

```java
private boolean dodge(double attackSpeed, double defenceAgility) {
    if (defenceAgility <= (double)0.0F) {
        return false;
    } else if (attackSpeed <= (double)0.0F) {
        return true;
    } else {
        double r = attackSpeed / (defenceAgility * (this.CHANCE_MIN + (double)RND.rFloat() * this.CHANCE_SPAN));
        return !(r > (double)RND.rFloat());
    }
}
```

* `defenceAgility` refers to `DEFENCE_SKILL`.
* `attackSpeed` refers to `OFFENCE_SKILL` and **not** `ATTACK_RATE` for normal attacks.
* `RND.rFLOAT()` returns a random number from `0.0` to `1.0`

Thus, plugging in the vanilla value of `CHANCE_MIN = 0.01` and `CHANCE_SPAN = 99.99`. We can approximate:

* **If dodged → no damage.** Nice and easy
* **If you do not dodge, blocking is checked next.**

**What is block?** Block reduces the damage based on your blocking stats
**Ultimately, the formula is the EXACT same as for Dodging.** 

| Dexterity/Block Ratio (q)              | Bypass Block Chance (Default `DAMAGE_REDUCTION` = 100) |
|----------------------------------------|------------------------------------------------------|
| 0.5                                    | 3.1%                                                 |
| 1                                      | 5.6%                                                 |
| 2                                      | 9.8%                                                 |
| 5                                      | 20%                                                  |
| 10                                     | 33%                                                  |
| 20 (the dexterity value of catapults!) | 52%                                                  |

| Dexterity/Block Ratio (q)              | Bypass Block Chance (`DAMAGE_REDUCTION` = 50) |
|----------------------------------------|-----------------------------------------------|
| 0.5                                    | 5.5%                                          |
| 1                                      | 9.2%                                          |
| 2                                      | 17%                                           |
| 5                                      | 33%                                           |
| 10                                     | 52%                                           |
| 20 (the dexterity value of catapults!) | 76%                                           |

* If the target is humanoid:
  * **Block/parry check** using attacker `DEXTERITY` vs target parry + formation.
  * On success → `blockDamage` reduces damage.
* Apply **knockback** (momentum) to target.

##### Direction Multiplier

* `1x` = hit from the front (incoming direction opposite their facing)
* `0.5x` = attack from flank
* `0x` = attack from back

This is fed in the equation of `1 * multiplier`.

**So block skill from the back is 0% of the original value and from the flank is 50%.**

**NOTE:** The funny thing about the code is that it checks if `DEXTERITY = 0` first. 
So, if someone with 0 block was attacked by someone with 0 dexterity, the hit will always be blocked. Aka shields are omnidirectional against bows.

**Raw Code:**

```java
private boolean doesNotBlock(double attackSkill, Humanoid enemy, double dot, double adx, double ady) {
    if (attackSkill <= (double)0.0F) {
        return false;
    } else {
        double def = Handler.parrySkill(enemy, dot, adx, ady);
        if (def <= (double)0.0F) {
            return true;
        } else {
            if (enemy.division() == null) {
                def *= (double)0.25F;
            }

            double r = attackSkill / (def * (this.CHANCE_MIN + (double)RND.rFloat() * this.CHANCE_SPAN));
            return r > (double)RND.rFloat();
        }
    }
}
```

* again, excuse the usage of negatives and the naming of variables in Jake’s code. Notably, this is the chance **an attack is NOT blocked**.
* `attackSkill` here refers to `DEXTERITY`
* `def` here refers to `BLOCK` or Parry skill

**If Block is successful, mitigate some damage.**

**So what is the basic math?**

* All damage is divided by `1 + BLUNT_DEFENCE_DIR` (A /= B means divide A by B and set A to equal the result)
* Then each individual damage is reduced by their respect block defence. For example:

This is a pretty hefty reduction in damage! Especially if you stack `BLUNT_DEFENCE_DIR + SLASH_DEFENCE_DIR`.

* In vanilla a shield gives `+4` Directional Force Absorption, so **a blocked hit reduces damage by 80%!**

**Raw Code:**

```java
private void blockDamage(ECollision e, Humanoid blocker) {
    e.damagetileStrength /= BOOSTABLES.BATTLE().BLUNT_DEFENCE_DIR.get(blocker.indu());

    for (int i = 0; i < e.damage.length; ++i) {
        double[] damage = e.damage;
        damage[i] /= (double) 1.0F + ((BOOSTABLES.BDamage) BOOSTABLES.BATTLE().DAMAGES.get(i)).defenceDir.get(blocker.indu());
    }
}
```

**Finally, Calculate Damage Reduction (Armour) and Damage Done**

**Basic Math**

* Height can reduce damage! 
* Each damage type is further reduced by armour! 
* Damage is multiplied by `damagetileStength` (This is why `BLUNT_DAMAGE` is crucial for dealing damage)

Damage is reduced further by `BLUNT_DEFENCE`  

* If the final damage is greater than `RND.rFLOAT()` (random number from `0.0` to `1.0`) it deals damage otherwise it deals nothing. 

This final damage value is then passed on and dealt to the defender

##### Notes:
###### Wait, what is “blunt” damage?
In the game, each weapon has a stat for flat damage. 
For example, a spear has some pierce damage or a flanx has lots of slash. 
This is obvious in the stats. 
However, you will notice **there is no blunt damage added in the game**. 
That is because there is nothing in the game that adds blunt damage. 
The closest we got is Force which, as shown above, **multiplies all damage which also increases that base 1 damage**. 
Likewise, _Force Absorption_ reduces all damage and acts almost like _Health Points_. 

###### Why does blunt matter?
Simple, it is not reduced by armor. 
That `+1` base damage is only affected by _Force Absorption_ as it is never added into the damage types of slash or pierce which are then reduced by slash or pierce armor. 
Thus, warhammers which increase Force do well against heavily armored enemies because they increase that flat 1 armor ignoring damage.

**Raw Code:**

```java
public double getDamageDone(ECollision coll, Humanoid a) {
    double dam = (double)1.0F;
    dam /= (double)1.0F + (double)((Terrain.TerrainTile)SETT.TERRAIN().get(a.tc())).heightEnt(a.tc().x(), a.tc().y()) / (double)10.0F;

    for(int i = 0; i < BOOSTABLES.BATTLE().DAMAGES.size(); ++i) {
        dam += coll.damage[i] / ((double)1.0F + ((BOOSTABLES.BDamage)BOOSTABLES.BATTLE().DAMAGES.get(i)).defence.get(a.indu()));
    }

    dam *= coll.damagetileStrength;
    dam /= (this.CHANCE_MIN + (double)RND.rFloat() * this.CHANCE_SPAN) * BOOSTABLES.BATTLE().BLUNT_DEFENCE.get(a.indu());
    double ch = (double)RND.rFloat();
    if (a.division() == null) {
        ch *= (double)0.25F;
    }

    return dam > ch ? dam : (double)0.0F;
}
```

## Calculating Base Damage for an Attack 

### Calculating Hit Damage Dealt

Hit Damage, fortunately, is a bit simpler to calculate.
* Blunt attack serves as a multiplier of all damage which increases all your slash and pierce damage. 
  **Note that this is where `damagetileStrength` is given a value. If you examine the various defensive layers, `damagetileStrength` is always being reduced!**
* You store each type of damage based on your `SLASH_DAMAGE` or `PIERCE_DAMAGE` to be calculated separately later as shown above
* Your physical momentum (knockback not damage!) is defined here under `tileMomentum`.
* **Run the damage through all the defensive layers mentioned above**.

**Raw Code:**

```java
private void setDamage(ECollision e, Humanoid a) {
    double h = (double)1.0F + (double)((Terrain.TerrainTile)SETT.TERRAIN().get(a.tc())).heightEnt(a.tc().x(), a.tc().y()) / (double)10.0F;
    e.damagetileStrength = BOOSTABLES.BATTLE().BLUNT_ATTACK.get(a.indu()) * h;

    for(int i = 0; i < e.damage.length; ++i) {
        double da = ((BOOSTABLES.BDamage)BOOSTABLES.BATTLE().DAMAGES.get(i)).attack.get(a.indu());
        e.damage[i] = da;
    }

    this.coll.tileMomentum = (double)64.0F * h * BOOSTABLES.BATTLE().BLUNT_ATTACK.get(a.indu()) * ((double)0.5F + (double)(RND.rFloat() * 2.0F));
}
```
### Calculating Impact Damage Dealt

* **Impact Damage can be considered a second hit during combat!**
  This hit can be completely negated by `DEFENCE_SKILL` through the dodge mechanic

**NOTE:** Impact Damage rolls your `DEXTERITY + SPEED` instead of `OFFENCE_SKILL` when calculating dodge chance. 
  So a poorly trained person running at max speed is still quite difficult to block even with 0 offence skill

* A bonus momentum multiplier is calculated (speed is affected by exhaustion)
* `BLOCK` chance is rolled using **OFFENCE_SKILL**. 

**NOTE:** `BLOCK` is normally dexterity vs block. 
In this case, it's offence skill which is typically smaller than dexterity (base of 5). 
Consequently, shields are good help against absorbing charges

**Raw Code:**

```java
public void setImpactDamage(Humanoid a, ECollision coll, ECollision damage) {
    double speed = a.speed.magnitude();
    damage.damagetileStrength = (double)0.0F;
    if (!this.dodge(BOOSTABLES.BATTLE().DEXTERITY.get(a.indu()) + speed, coll.other.getDefenceSkill(coll.dirDotOther, coll.norX, coll.norY))) {
        this.setDamage(damage, a);
        speed = speed * (double)0.015625F - (double)2.0F;
        if (speed < (double)0.0F) {
            speed = (double)0.0F;
        }

        STATS.NEEDS().EXHASTION.indu().incD(a.indu(), -speed * (double)0.25F);
        double bonus = speed * coll.dirDot;
        bonus *= BOOSTABLES.BATTLE().CHARGE.get(a.indu());
        damage.damagetileStrength *= bonus;
        if (coll.other instanceof Humanoid) {
            Humanoid e = (Humanoid)coll.other;
            if (this.doesNotBlock(BOOSTABLES.BATTLE().OFFENCE.get(a.indu()), e, coll.dirDotOther, coll.norX, coll.norY)) {
                this.blockDamage(damage, e);
            }
        }

    }
}
```

## Projectiles

### Calculating Hit Chance

**Raw Code:**

```java
public boolean projectileAttack(ENTITY e, double angleX, double angleY, double speed, Projectile type, double ref) {
    int sp = (int)speed;
    DIR od = e.speed.dir();
    double dot = ((double)1.0F - od.xN() * angleX - od.yN() * angleY) * (double)0.5F;
    if (this.dodge((double)sp, e.getDefenceSkill(dot, angleX, angleY))) {
        return false;
    } else {
        this.coll.norX = angleX;
        this.coll.norY = angleY;
        this.coll.dirDot = dot;
        this.coll.dirDotOther = dot;
        this.coll.leave = CAUSE_LEAVES.SLAYED();
        this.coll.other = null;
        this.coll.damagetileStrength = type.mass(ref) * (double)sp * (double)0.015625F;

        for(int i = 0; i < this.coll.damage.length; ++i) {
            this.coll.damage[i] = type.damage(i, ref);
        }

        double mom = type.mass(ref) * speed;
        mom *= (double)(1.0F + RND.rFloat((double)8.0F));
        this.coll.tileMomentum = mom;
        mom *= e.physics.getMassI();
        double nX = angleX * mom;
        double nY = angleY * mom;
        e.speed.setRaw(e.speed.x() + nX, e.speed.y() + nY);
        ++this.am;
        if (e instanceof Humanoid) {
            Humanoid eh = (Humanoid)e;
            if (!this.doesNotBlock(type.skill(ref), eh, dot, angleX, angleY)) {
                ++this.bb;
                this.blockDamage(this.coll, eh);
            }
        }

        e.collide(this.coll);
        return true;
    }
}
```

#### Analysis and Important Parts

This code is rather long. But all you need to know is this:

* Projectiles roll their **speed value** (typically at least 20+) against the defender’s dodge chance (`BATTLE_DEFENCE_SKILL`). 
  This means that dodging is very unlikely.
* It rolls its `type.skill(ref)` against the defenders block attack. 
  `type.skill(ref)` is the skill with that particular ranged weapon. This is calculated like so:

```java
public double skill(double ref) {
    return this.from.dexterity + this.delta.dexterity * ref;
}
```

For `ref`, this **base value is capped at** `1.0` as the only way to increase it is by training at the archery barracks which at 100% training gives `+1 BOW_SKILL`. 
**Note, this dexterity is the dexterity of the weapon and NOT the humanoid.** 
As of V70, the vanilla weapons that have dexterity are only catapults. 
Bows have 0 dexterity at all skill levels. 
Therefore, **skill always returns 0 for bows** (range of dexterity is 0 to 0 which means `this.from.dexterity = 0` and this.delta.dexterity = 0. 

**NOTE:** `this.delta.dexterity` is the dexterity difference of the weapon between 0-1 skill. 

**Therefore, block damage reduction is always applied to bows.**

Interestingly, this means block skill doesn’t matter if it is not 0 (base value is 1 so it’s never 0) because block always occurs if the game detects 0 for dexterity. 
It’s the **quality of the block which you should be focusing on** (in other words, `BATTLE_BLUNT_DEFENCE_DIR` which plate armor and shields increase).

#### Shown below, the projectile stats of a bow: 

```
PROJECTILE: {
	COLOR: 30_20_8,
	**SPRITE_FILE: sprites/settlement/projectile
	** ^^ if not present, then a special arrow is drawn
	
	FROM: {
		TILE_SPEED: 35,
		RELOAD_SECONDS: 30,
		ACCURACY: 0.8,
		MAX_ARCH_ANGLE_DEGREES: 50,
		DEXTERITY: 0,
		DAMAGE: {
			PIERCE: 4, 
		},
		MASS: 0.4,
		TILE_RADIUS_DAMAGE: 0,
	},
	
	TO: {
		TILE_SPEED: 45,
		RELOAD_SECONDS: 3,
		ACCURACY: 0.98,
		MAX_ARCH_ANGLE_DEGREES: 75,
		DEXTERITY: 0,
		DAMAGE: {
			PIERCE: 12,
		},
		MASS: 0.4,
		TILE_RADIUS_DAMAGE: 0,
	},
},
```

**NOTE:** There is also `BATTLE_SLASH_DEFENCE_DIR` and `BATTLE_PIERCE_DEFENCE_DIR` which only negate their specific damage types as seen above. 
However, nothing in vanilla uses it. (very useful to make equipment particularly strong against ranged which is usually pierce!).

## Health Points (HP) in Songs of Syx 

### Analysis and Important Parts

* If damage is between `0.0` and `1.0` it increases injuries by that proportion of max injuries (256). 
  Aka, a hit of 0.5 would add 0.5 * 256 injuries or 128 injuries. 
  **Thus, SoS entities can be approximated as having 1.0 hit points each.**
* **Above 128 injuries, the entity will begin gaining injuries, bleeding out slowly.**

**Raw Code:**

```java
public boolean inflictDamage(double d, CAUSE_LEAVE cause) {
    this.leaveCause = (byte)cause.index();
    if (d <= (double)0.0F) {
        return false;
    } else {
        if (d > 0.1) {
            SETT.THINGS().gore.bleed(this, this.race().appearance().colors.blood);
        }

        if (d > 0.2) {
            SETT.THINGS().gore.cloud(this, this.race().appearance().colors.blood);
        }

        if (d * (double)RND.rFloat() > (double)1.0F) {
            SETT.HUMANOIDS().sound.rnd(this);
            SETT.THINGS().gore.explode(this, this.race().appearance().colors.blood);
            STATS.NEEDS().INJURIES.COUNT.indu().setD(this.induvidual, (double)1.0F);
            if (this.division() != null) {
                GAME.ARMIES().factors.reportCasulty(this.division());
            }

            this.kill(true, (CAUSE_LEAVE)CAUSE_LEAVES.ALL().get(this.leaveCause));
            return false;
        } else {
            int m = STATS.NEEDS().INJURIES.COUNT.indu().max(this.induvidual);
            d *= (double)m;
            int ii = (int)d;
            if ((double)RND.rFloat() < d - (double)ii) {
                ++ii;
            }

            int am = STATS.NEEDS().INJURIES.COUNT.indu().get(this.induvidual) + ii;
            if (am >= m) {
                STATS.NEEDS().INJURIES.COUNT.indu().inc(this.induvidual, (int)(d * (double)RND.rFloat()));
                if (this.division() != null) {
                    GAME.ARMIES().factors.reportCasulty(this.division());
                }

                this.kill(true, (CAUSE_LEAVE)CAUSE_LEAVES.ALL().get(this.leaveCause));
                return false;
            } else {
                STATS.NEEDS().INJURIES.COUNT.indu().set(this.induvidual, am);
                return true;
            }
        }
    }
}
```

