package com.company.simpleSim;

import com.company.soldierClasses.Soldier;

import java.util.*;

public class Army {


//    public final int armySize;
    public final ArrayList<Soldier> battalion;
    public final String armyName;


    public Army(int armySize) {
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
            Soldier currentDefender  =  T.next();
            Soldier currentAttacker = D.next();
            currentDefender.setHitPoints(currentDefender.getHitPoints() - calculateDamage(currentAttacker,currentDefender));
        }
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
        attackDamage = attackDamage - ((attackDamage * negateDamage(currentDefender.getDefenceStat()))/100 );

        if((currentAttacker.getClassCode() == 0 && currentDefender.getClassCode()==2) ||
                (currentAttacker.getClassCode()==1 && currentDefender.getClassCode()==0) ||
                (currentAttacker.getClassCode()==2 && currentDefender.getClassCode()==1)) {
            attackDamage += (30 * attackDamage)/100;
        }

        return attackDamage;
    }

    public int negateDamage(int defense)
    {
        if( checkDeferred(defense)){
            return 99;//check if the small percentage that
        }
        return scaleDefensiveNumberics(2,30,defense);
    }

    public boolean checkDeferred (int defensiveStat){
        return statRNG(scaleDefensiveNumberics(0,5,defensiveStat));
    }
    public int scaleDefensiveNumberics(int newMax, int newMin, int defense){
        int oldMin = 1;
        int oldMax = 22;
        int oldRange = oldMax - oldMin;
        int newRange = newMax - newMin;
        return (defense - oldMin) * (newRange / oldRange) + newMin;

    }
    public boolean statRNG (int stat)
    {
        Random rand = new Random();
        double d = rand.nextDouble() * 100;
        if (d <= stat)
        {
            return true;
        }
        return false;
    }
}

