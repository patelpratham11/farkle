/*
Main
    Create Player
        Name
Roll Dice Method
    Parameter (int dice)
    Returns arrayList
Calculations Method
    returns scoresheet
    returns
 */
import java.util.Arrays;
import java.util.Scanner;
import java.util.ArrayList;
public class Farkle {
    private static ArrayList<Integer> scoreSheet;
    private static ArrayList<Integer> diceRoll;
    private static ArrayList<Integer>dice;

    public static void main(String[] args) {



        scoreSheet = new ArrayList<Integer>();
        diceRoll = new ArrayList<Integer>();
        dice = new ArrayList<Integer>();


        for (int i = 0; i < 6; i ++){
            diceRoll.add(0);
        }
        scoreSheet.add(0, 0);
        dice.add(0, 6);
        start();
    }

    public static void start(){
        int farkleOne = 0;
        Scanner input = new Scanner(System.in);
        int dice_left = dice.get(0);
        if (dice_left == 0 || dice_left == 6){
            RollDice(6);
        }
        if (dice_left<6 && dice_left > 0) {
            System.out.println("You have " + dice_left + " Dice, would you like to roll? (y/n)");
            String roller = input.nextLine();
            roller = roller.toLowerCase();
            if(roller.equals("y")){
                RollDice(dice_left);
            }
            else{
                System.out.println("Okay!");
            }
        }
        else{
            System.out.println("Okay!");
        }
        order();
        int points = calculate();
        if(points == 0){
            farkleOne++;
        }

        if(farkleOne == 3){
            int score = scoreSheet.get(0);
            score = score-1000;
            scoreSheet.set(0, score);
        }
        System.out.println(farkleOne);
    }
    private static void RollDice(int dice){
        System.out.println("dice left "+dice);
        for (int i = 0; i < 6; i ++){
            diceRoll.set(i, 0);
        }
        for (int i = 0; i <  dice; i ++){
            int holder = (int)((Math.random()*6)+1);
            diceRoll.set(i, holder);
        }
    }//end of rolldice

    private static void order () {
        System.out.println(diceRoll);
        for (int i = 0; i < diceRoll.size(); i++) {
            int one = diceRoll.get(i);
            for (int j = i; j < diceRoll.size(); j++) {
                if (one >= diceRoll.get(j)) {
                    int hold = diceRoll.get(j);
                    diceRoll.remove(j);
                    diceRoll.add(i, hold);
                }//end of if
            }//for 2
        }//for 1
        System.out.println(diceRoll);
    }
    public static int calculate(){
        int points = 0;

        int one = 0;
        int two = 0;
        int three = 0;
        int four = 0;
        int five = 0;
        int six = 0;

        for (int i = 0; i < diceRoll.size(); i++){
            int value = diceRoll.get(i);
            if (value == 1){
                one++;
            }
            if (value == 2){
                two++;
            }
            if (value == 3){
                three++;
            }
            if (value == 4){
                four++;
            }
            if (value == 5){
                five++;
            }
            if (value == 6){
                six++;
            }
        }//end of for


        int [] freq = {
        one, two, three, four, five, six
        };
        System.out.println(Arrays.toString(freq));

        int seqCount = 0;
        for (int i = 0; i < freq.length; i ++){
            int sequence = freq[i];
            if (sequence == 1){
                seqCount++;
            }
            if (sequence >2){
                System.out.println("You had "+sequence+ " of the number "+ (i+1));
                if (sequence == 3){
                    points = ((i+1)*100);
                    int current = scoreSheet.get(0);
                    scoreSheet.set(0, current+points);
                    System.out.println(scoreSheet);
                    int currentDice = dice.get(0);
                    currentDice = currentDice - 3;
                    dice.set(0, currentDice);
                    System.out.println("You now have "+dice.get(0)+ " Dice remaining");
                }//seq = = 3

                if (sequence == 4){
                    points = 1000;
                    int current = scoreSheet.get(0);
                    scoreSheet.set(0, current+points);
                    int currentDice = dice.get(0);
                    currentDice = currentDice - 4;
                    dice.set(0, currentDice);
                    System.out.println("You now have "+dice.get(0)+ " Dice remaining");
                }//seq == 4

                if (sequence == 5){
                    points = 2000;
                    int current = scoreSheet.get(0);
                    scoreSheet.set(0, current+points);
                    int currentDice = dice.get(0);
                    currentDice = currentDice - 5;
                    dice.set(0, currentDice);
                    System.out.println("You now have "+dice.get(0)+ " Dice remaining");
                }//seq == 5

                if (sequence == 6){
                    points = 3000;
                    int current = scoreSheet.get(0);
                    scoreSheet.set(0, current+points);
                    dice.set(6, 0);
                    System.out.println("You now have "+dice.get(0)+ " Dice remaining");
                }//seq == 5
            }

        }

        if (seqCount == 6){
            points = 1500;
            System.out.println("Wow, you just earned 1500 points");
            int current = scoreSheet.get(0);
            scoreSheet.set(0, current+points);
            dice.set(0, 6);
            System.out.println(scoreSheet);
            return points;
        }

        if (freq[0] != 0 && freq[0]<3){
            points = (freq[0] * 100);
            int current = scoreSheet.get(0);
            scoreSheet.set(0, current+points);


            int currentDice = dice.get(0);
            currentDice = currentDice - freq[0];
            dice.set(0, currentDice);
            System.out.println("You now have "+dice.get(0)+ " Dice remaining");
        }
        if (freq[4] != 0 && freq[4]<3){
            points = (freq[4] * 50);
            int current = scoreSheet.get(0);
            scoreSheet.set(0, current+points);

            int currentDice = dice.get(0);
            currentDice = currentDice - freq[4];
            dice.set(0, currentDice);
            System.out.println("You now have "+dice.get(0)+ " Dice remaining");
        }

        System.out.println("You now have "+dice.get(0)+ " Dice remaining");
        System.out.println("Your current score is "+scoreSheet.get(0));
        start();

        return points;
    }// end of order
}
