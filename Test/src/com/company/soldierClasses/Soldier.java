package com.company.soldierClasses;

import java.util.Random;

public class Soldier {

    public String battleClass;
    public int classCode;
    public int attackStat;
    public int defenceStat;
    public int attackSpeed;
    public int hitPoints;
    public boolean isAlive;
    public double criticalStrikeChance = 2.0;

    public Soldier(int attackStat, int defenceStat, int attackSpeed, int hitPoints, double criticalStrikeChance) {
        this.attackStat = attackStat;
        this.defenceStat = defenceStat;
        this.attackSpeed = attackSpeed;
        this.hitPoints = hitPoints;
        this.criticalStrikeChance = criticalStrikeChance;
        isAlive = true;
    }

    public Soldier() {
    }

    public void createSoldier(){
        Random rand = new Random();
        switch (rand.nextInt(3)){
            case (0): battleClass = ("MAGE");
                setHitPoints(rand.nextInt(1000) + 50);
                setAttackStat(rand.nextInt(100)+1);
                setDefenceStat(rand.nextInt(10)+1);
                setAttackSpeed(rand.nextInt(12)+2);
                setCriticalStrikeChance(rand.nextDouble()*25);
            classCode = 0;
            break;
            case (1): battleClass = ("RANGER");
                setHitPoints(rand.nextInt(1000) + 50);
                setAttackStat(rand.nextInt(100)+1);
                setDefenceStat(rand.nextInt(10)+1);
                setAttackSpeed(rand.nextInt(20)+2);
                setCriticalStrikeChance(rand.nextDouble()*10);
            classCode = 1;
            break;
            case (2): battleClass = ("WARRIOR");
                setHitPoints(rand.nextInt(1000) + 50);
                setAttackStat(rand.nextInt(100)+1);
                setDefenceStat(rand.nextInt(22)+1);
                setAttackSpeed(rand.nextInt(12)+2);
                setCriticalStrikeChance(rand.nextDouble()*10);
            classCode = 2;
            break;
        }
    }


    public void increaseAttackStat(int percent){
         this.attackStat = attackStat + ((percent * attackStat)/100);
    }
    public void increaseHitPoint(int percent){
        this.hitPoints = hitPoints + (((percent * hitPoints)/100));
    }
    public void decreaseAttackStat(int percent){
        this.attackStat = attackStat - ((((percent * attackStat)/100)));
    }
    public void decreaseHitPointStat(int percent){
        this.hitPoints = hitPoints - (((percent * hitPoints)/100));
    }



    public int getAttackStat() {
        return attackStat;
    }

    public void setAttackStat(int attackStat) {
        this.attackStat = attackStat;
    }

    public int getDefenceStat() {
        return defenceStat;
    }

    public void setDefenceStat(int defenceStat) {
        this.defenceStat = defenceStat;
    }


    public void setAttackSpeed(int attackSpeed) {
        this.attackSpeed = attackSpeed;
    }

    public int getHitPoints() {
        return hitPoints;
    }

    public void setHitPoints(int hitPoints) {
        this.hitPoints = hitPoints;
    }

    public int getClassCode() {
        return classCode;
    }


    public void setCriticalStrikeChance(double criticalStrikeChance) {
        this.criticalStrikeChance = criticalStrikeChance;
    }
}
