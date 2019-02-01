import java.util.Scanner;
import java.util.Random;

public class RoshamboApp {

    static Scanner scnr = new Scanner(System.in);
    
    public static void main(String[] args) {

        // declare and initialize variables for use in main
        int win = 0;
        int loss = 0;
        int tie = 0;
        int other = 0;
        String again = "";
        int winResult = 0;
        int pickOpponent = 0;
        
        // create the player objects for the game
        Player opponent = new RockPlayer("Dwayne");
        Player human = new ConsolePlayer(scnr, "Jason");
        Player randomOpponent = new RandomPlayer("ThatGuy");

        // get the user to choose their opponent with input validation
        do { // big do-while loops for whether or not the player wants to keep playing

            do { // do-while loop for validation of opponent
                pickOpponent = choosePlayer(scnr);
            } while ((pickOpponent > 3 && pickOpponent < 1));

            // with a valid choice made, we can play the game. Time to process the computer
            // opponent's hands
            Roshambo oppHand = throwHand(pickOpponent, opponent, randomOpponent);
            Roshambo huHand = humanHand(pickOpponent, human);

            displayHand(scnr, pickOpponent, opponent, randomOpponent, oppHand);
            winResult = determineWinner(scnr, pickOpponent, oppHand, huHand);
            if (winResult == 1) {
                win++;
            }
            if (winResult == 2) {
                loss++;
            }
            if (winResult == 3) {
                tie++;
            }
            if (winResult == 4) {
                other++;
            }

            System.out.println("wins: " + win);
            System.out.println("Loss: " + loss);
            System.out.println("Tie: " + tie);
            
            if (other >0) {System.out.println("??? Wins: " + other);}
            do { // do-while loop to validate input on whether to continue or not
                again = playAgain(scnr);
            } while (!(again.equalsIgnoreCase("y")) && (!again.equalsIgnoreCase("n")));

        } while (again.equalsIgnoreCase("y"));

        System.out.println("Thanks for playing!");
    }// end main

    // takes input from user to determine which player they'll play against
    private static int choosePlayer(Scanner scan) {
        String in = "";

        System.out.println("Please select a player: ");
        System.out.println("1) CPU Player 1");
        System.out.println("2) CPU Player 2");
        in = scan.nextLine();
        if (in.equals("1")) {
            return 1;
        } else if (in.equals("2")) {
            return 2;
        } else if (in.equals("3")) {
            return -1;
        } else if (in.equals("Mariah")) {
            return 3;
        } else {
            System.out.println("Please pick 1 or 2. ");
            return 0;
        }
    } // end choosePlayer

    // processes the computer opponent's hand
    private static Roshambo throwHand(int oppHand, Player opponent, Player randomOpponent) {
        if (oppHand == 1) {
            Roshambo opponentChoice = opponent.generateRoshambo();
            return opponentChoice;
        } else if (oppHand == 2) {
            Roshambo randomChoice = randomOpponent.generateRoshambo();
            return randomChoice;
        } else if (oppHand == 3) {
            return null;
        }
        return null;
    } // end throwHand

    // displays computer opponent's hand
    private static void displayHand(Scanner scan, int oppHand, Player opponent, Player randomOpponent,
            Roshambo opChoice) {
        if (oppHand == 1) {
            System.out.println(opponent.getName() + " played " + opChoice);
        } else if (oppHand == 2) {
            System.out.println(randomOpponent.getName() + " played " + opChoice);
        } else if (oppHand == 3) {
            System.out.println("*GASP!!!* You've found the secret player! I hope you know what you're doing...");
            scan.nextLine();
            System.out.println("Mariah suddenly appears before you!!!...");
            scan.nextLine();
        }

    } // end displayHand

    // takes and displays the user's choice
    private static Roshambo humanHand(int opp, Player human) {
        if (opp != 3) {
        Roshambo humanChoice = human.generateRoshambo();
        
            System.out.println(human.getName() + " played " + humanChoice);
            return humanChoice;
        }
        return null;
    }

    // determines who won and returns the result. Win = 1, Loss = 2 and Tie = 3
    private static int determineWinner(Scanner scan, int opp, Roshambo opHand, Roshambo huHand) {

        if (opp == 1) { // if user is up against Dwayne then process results
            if (huHand == Roshambo.ROCK) {
                System.out.println("It's a tie!!!");
                return 3;
            } else if (huHand == Roshambo.PAPER) {
                System.out.println("You win!!!");
                return 1;
            } else {
                System.out.println("You lose.... :(");
                return 2;
            }
        }
        if (opp == 2) { // process results of Random player
            if (opHand == Roshambo.ROCK && huHand == Roshambo.ROCK) { // process results for opp picking ROCK
                System.out.println("It's a tie!!!");
                return 3;
            } else if (opHand == Roshambo.ROCK && huHand == Roshambo.PAPER) {
                System.out.println("You win!!!");
                return 1;
            } else if (opHand == Roshambo.ROCK && huHand == Roshambo.SCISSORS) {
                System.out.println("You lose.... :(");
                return 2;
            }

            if (opHand == Roshambo.PAPER && huHand == Roshambo.ROCK) { // process results for opp picking PAPER
                System.out.println("You lose.... :(");
                return 2;
            } else if (opHand == Roshambo.PAPER && huHand == Roshambo.PAPER) {
                System.out.println("It's a tie!!!");
                return 3;
            } else if (opHand == Roshambo.PAPER && huHand == Roshambo.SCISSORS) {
                System.out.println("You win!!!");
                return 1;
            }

            if (opHand == Roshambo.SCISSORS && huHand == Roshambo.ROCK) { // process results for opp picking SCISSORS
                System.out.println("You win!!!");
                return 1;
            } else if (opHand == Roshambo.SCISSORS && huHand == Roshambo.PAPER) {
                System.out.println("You lose.... :(");
                return 2;
            } else if (opHand == Roshambo.SCISSORS && huHand == Roshambo.SCISSORS) {
                System.out.println("It's a tie!!!");
                return 3;
            }

        }
        if (opp == 3) {
            System.out.println(
                    "As Mariah shows her hand a bunch of vines erupt from the ground and in front of you!!!...");
            scan.nextLine();
            System.out.println(
                    "Your hands are completely covered by the vines!!! Unable to play, you're disqualified!!!...");
            scan.nextLine();
            System.out.println(
                    "Mariah wins!!! :) If she finds this, she should find a way to use the word \"AwesomeSauce\"");
            System.out.println("in the comments of the grade of this assignment. :)");
            return 4;
        }

        return 0;
    } // end determineWinner

    // tracks and displays the score for the session
    

    private static String playAgain(Scanner scan) {

        String again = "";
        System.out.println("Would you like to play again? (y/n): ");
        again = scan.nextLine();
        if (!(again.equalsIgnoreCase("y")) && (!again.equalsIgnoreCase("n"))) {
            System.out.println("Invalid input. Please enter y or n");
            return again;
        }
        return again;
    }

} // end class
