package com.company.simpleSim;

import com.company.soldierClasses.Soldier;

import java.util.*;

public class Army {
    
//    public final int armySize;
    public final ArrayList<Soldier> battalion;
    public final String armyName;

    public Army(int armySize) {
//        this.armySize = armySize;
        this.battalion = generateBattalion(armySize);
        this.armyName = generateArmyName();
    }

    @SuppressWarnings("Convert2Diamond")
    public ArrayList<Soldier> generateBattalion(int armySize){
        ArrayList<Soldier> army = new ArrayList<Soldier>(armySize);
        int i = 0;
        while (i < armySize) {
            Soldier temp = new Soldier();
            temp.createSoldier();
            army.add(temp);
            i++;
        }
        return army;
    }


    //Randomly Creates a Name for Each Army using the constants we created
    public String generateArmyName(){
        Random rand = new Random();
        List<String> str = Arrays.asList(Constants.armyFirstNames);
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(str.get(rand.nextInt(9)));
        str = Arrays.asList(Constants.armyLastNames);
        stringBuilder.append(str.get(rand.nextInt(2)));
        return stringBuilder.toString();
    }

    public void Attacks (ArrayList<Soldier> army){
        Iterator<Soldier> T = army.iterator();
        Iterator<Soldier> D = battalion.iterator();

        while (D.hasNext() && T.hasNext()){
            Soldier currentDefender;
            Soldier currentAttacker;
            currentDefender = T.next();
            currentAttacker = D.next();

            //30% buff for classes
            if((currentAttacker.getClassCode() == 0 && currentDefender.getClassCode()==2) ||
                    (currentAttacker.getClassCode()==1 && currentDefender.getClassCode()==0) ||
                    (currentAttacker.getClassCode()==2 && currentDefender.getClassCode()==1))
            {
                performBuffedAttack(currentAttacker,currentDefender);

            } else currentDefender.setHitPoints(currentDefender.getHitPoints() - currentAttacker.getAttackStat());
        }
    }

    public void performBuffedAttack(Soldier stronger, Soldier weaker){ //save damage stat, increase, return damage back to normal.
        int attackerStat = stronger.getAttackStat();
        int strengthenedAttack = attackerStat + ((30 * attackerStat)/100);
        weaker.setHitPoints(weaker.getHitPoints() - strengthenedAttack);
    }

    public int calculateDamage(Soldier currentAttacker, Soldier currentDefender){

        boolean isAvoided;
        boolean isCritStrike;
        boolean isNegated;
        int attackDamage = currentAttacker.attackStat;


        if (statRNG(currentAttacker.attackSpeed)) {
            attackDamage *= 2;
        }
        if(statRNG((int)currentAttacker.criticalStrikeChance)){
            attackDamage = attackDamage + (int)(2.5 * attackDamage);
        }
//        attackDamage = negateDamage(currentDefender.getDefenceStat());

        return attackDamage;
    }

//    public int negateDamage(int defense){ ();
//
//    }
    public boolean statRNG (int stat) {
        Random rand = new Random();
        double d = rand.nextDouble() * 100;
        if (d <= stat) {
            return true;
        }
        return false;
    }
}

