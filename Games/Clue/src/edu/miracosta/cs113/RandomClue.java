package edu.miracosta.cs113;

/**
 * RandomClue.java : Your job is to ask your AssistantJack and get the correct
 * answer in <= 20 tries.  RandomClue is ONE solution to the problem,
 * where a set of random numbers is generated every attempt until all three
 * random numbers match the solution from the AssistantJack object.
 * <p>
 * This is a sample solution, a driver using random number implementation.
 * You can use this file as a guide to create your own SEPARATE driver for
 * your implementation that can solve it in <= 20 times consistently.
 *
 * @author Nery Chapeton-Lamas (material from Kevin Lewis)
 * @version 1.0
 */

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

import model.Theory;
import model.AssistantJack;

public class RandomClue {

    /*
     * ALGORITHM:
     *
     * PROMPT "Which theory to test? (1, 2, 3[random]): "
     * READ answerSet
     * INSTANTIATE jack = new AssistantJack(answerSet)
     * DO
     *      weapon = random int between 1 and 6
     *      location = random int between 1 and 10
     *      murder = random int between 1 and 6
     *      solution = jack.checkAnswer(weapon, location, murder)
     * WHILE solution != 0
     *
     * OUTPUT "Total checks = " + jack.getTimesAsked()
     * IF jack.getTimesAsked() is greater than 20 THEN
     *      OUTPUT "FAILED"
     * ELSE
     *      OUTPUT "PASSED"
     * END IF
     */

    /**
     * Driver method for random guessing approach
     *
     * @param args not used for driver
     */
    public static void main(String[] args) {


        //declaration of 3 arraylists that will hold the possible guesses to be sent to assistantJack
        ArrayList<Integer> weapons = new ArrayList<>(5);
        ArrayList<Integer> locations = new ArrayList<>(9);
        ArrayList<Integer> murderers = new ArrayList<>(5);

        //for loops to build the 3 arrayLists that were declared above

        for (int i = 1; i < 7; i++) {
            weapons.add(i);
            murderers.add(i);
        }

        for (int i = 1; i < 11; i++) {
            locations.add(i);
        }



        // DECLARATION + INITIALIZATION
        int answerSet, solution, murder, weapon, location;
        Theory answer;
        AssistantJack jack;
        Scanner keyboard = new Scanner(System.in);
        Random random = new Random();


        // INPUT
        System.out.print("Which theory would like you like to test? (1, 2, 3[random]): ");
        answerSet = keyboard.nextInt();
        keyboard.close();

        // PROCESSING
        jack = new AssistantJack(answerSet);


        //arrayList elements will be sent as guesses to AssistanJack
        //if a room, weapon, or murderer is shown to be wrong then it
        //will be deleted from the list and not used on any further guesses


        do {
            weapon = random.nextInt(weapons.size()) + 1;
            location = random.nextInt(locations.size()) + 1;
            murder = random.nextInt(murderers.size()) + 1;
            solution = jack.checkAnswer(weapons.get(weapon-1), locations.get(location-1), murderers.get(murder-1));

            if(solution == 1){
                weapons.remove(weapon-1);
                weapons.trimToSize();
            }else if(solution == 2){
                locations.remove(location-1);
                locations.trimToSize();
            }else if(solution == 3){
                murderers.remove(murder-1);
                murderers.trimToSize();
            }else{

            }

        } while (solution != 0);


        answer = new Theory(weapon, location, murder);

        // OUTPUT
        System.out.println("Total Checks = " + jack.getTimesAsked() + ", Solution " + answer);

        if (jack.getTimesAsked() > 20) {
            System.out.println("FAILED!! You're a horrible Detective...");
        } else {
            System.out.println("WOW! You might as well be called Batman!");
        }

    }

}