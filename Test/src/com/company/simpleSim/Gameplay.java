package com.company.simpleSim;

import com.company.soldierClasses.Soldier;
import org.jetbrains.annotations.NotNull;

import java.util.*;

public class Gameplay {

    public static int armySize;
    Army army1;
    Army army2;
    public Army winner;
    public String terrain;
    int[] army1Count;
    int[] army2Count;

    public void Start(){
        requestArmySize();
        queueBattle();
        collectArmyData();
        printDialogue();
        fightTillTheDeath();
    }

    public void requestArmySize(){
        System.out.println("Army Size? : ");
        Scanner sc = new Scanner(System.in);

        while (armySize == 0)
        {
            if (sc.hasNext())
            {
                try
                {
                    armySize = Integer.parseInt(sc.next());
                }
                catch (NumberFormatException e)
                {
                    System.out.println("Army Size Must Be a Number: ");
                }
            }
        }
        sc.close();
    }

    public void collectArmyData(){
       army1Count = computeArmyStatistics(army1);
       army2Count = computeArmyStatistics(army2);
    }
    public int [] computeArmyStatistics(Army army){
        Iterator <Soldier> e = army.battalion.iterator();
        int mageCount = 0;
        int rangerCount = 0;
        int warriorCount = 0;
        int [] tempcount = new int[3];

        while(e.hasNext()){
            Soldier temp = e.next();
            if(temp.getClassCode() == 0){
                mageCount++;
            } else if (temp.getClassCode() == 1){
                rangerCount++;
            } else if (temp.getClassCode() == 2){
                warriorCount++;
            }
        }
        tempcount[0] = mageCount;
        tempcount[1] = rangerCount;
        tempcount[2] = warriorCount;
        return tempcount;
    }

    public void queueBattle() {
        army1 = new Army(armySize);
        army2 = new Army(armySize);
        setTerrain();
        applyBuffs();
        //remember to add printDialuge
    }

    //helper method to increment the stats by a certeain percentage
    private void applyBuffs() {
        buffArmy(army1);
        buffArmy(army2);
    }
    public void buffArmy(@NotNull Army army){
        for (Soldier temp : army.battalion ) {
            switch (terrain) {
                case "WOODLAND":
                    switch (temp.getClassCode()) {
                        case (0):
                            temp.increaseHitPoint(10);
                            break;
                        case (1):
                            temp.increaseAttackStat(20);
                            break;
                        case (2):
                            temp.decreaseAttackStat(20);
                    }
                    break;
                case "MOUNTAIN":
                    switch (temp.getClassCode()) {
                        case (0):
                            temp.decreaseAttackStat(20);
                            break;
                        case (1):
                            temp.increaseHitPoint(10);
                            break;
                        case (2):
                            temp.increaseAttackStat(20);
                            break;
                    }
                    break;
                case "FIELD":
                    switch (temp.getClassCode()) {
                        case (0):
                            temp.increaseAttackStat(10);
                            temp.decreaseHitPointStat(10);
                            break;
                        case (1):
                            temp.decreaseHitPointStat(20);
                            break;
                        case (2):
                            temp.increaseAttackStat(20);
                    }
                    break;
            }
        }
    }

    public void fightTillTheDeath() {
        BattlePhase(army1,army2);
    }

    public void BattlePhase(@NotNull Army army1, @NotNull Army army2){

        ArrayList<Soldier> attackingArmy = army1.battalion;
        ArrayList<Soldier> defendingArmy = army2.battalion;

        army1.Attacks(army2.battalion);
        TrimArmies(attackingArmy,defendingArmy);
        BattlePhase(army2,army1);

    }
    public void TrimArmies(@NotNull ArrayList<Soldier> attackingArmy, @NotNull ArrayList<Soldier> defendingArmy) {

        attackingArmy.removeIf(g -> g.getHitPoints() <= 0);
        defendingArmy.removeIf(b -> b.getHitPoints() <= 0);
        attackingArmy.trimToSize();
        defendingArmy.trimToSize();

        if (attackingArmy.isEmpty())
        {
            declareWinner(army2);
        }
        if (defendingArmy.isEmpty())
        {
            declareWinner(army1);
        }
    }

        private void declareWinner (Army army) {
            winner = army;
            int casualties = armySize - army.battalion.size();
            System.out.println("______________________________");
            System.out.println(army.armyName + " is the Winner. " +
                    '\n' + "Casualties:  " +  casualties);
            System.exit(0);
        }



    public void printDialogue()
    {
        System.out.println("Battle Will Begin Now"
                + '\n' + army1.armyName + " versus " +army2.armyName
                + '\n' + "Terrain: " + terrain
                + '\n' + army1.armyName + " Statistics: "
                + '\n' + army1Count[0] + " Mages"
                + '\n' + army1Count[1] + " Rangers"
                + '\n' + army1Count[2] + " Warriors"
                + '\n' + army2.armyName + " Statistics: "
                + '\n' + army2Count[0] + " Mages"
                + '\n' + army2Count[1] + " Rangers"
                + '\n' + army2Count[2] + " Warriors");
    }

    public String getTerrain() {
        return terrain;
    }

    public void setTerrain() {
        Random rand = new Random();
        switch (rand.nextInt(2)){
            case (0) : terrain = ("WOODLAND");
            break;
            case (1) : terrain = ("MOUNTAIN");
            break;
            case (2) : terrain = ("FIELD");
        }
    }
}
