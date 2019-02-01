import java.util.Scanner;

public class ConsolePlayer extends Player {

    
    private Scanner scnr;
    
    public ConsolePlayer(Scanner scnr, String name) {
        super(name);
        this.scnr = scnr;
    }
    public ConsolePlayer() {}

    
    public ConsolePlayer(String name) {super(name);}
    
    
    @Override
    public Roshambo generateRoshambo() {
        
       String input = Validator.getString(scnr, "Pick Rock, Paper, or Scissors: ");
       
       if (input.toLowerCase().startsWith("r")) {
           return Roshambo.ROCK;
       } else if (input.toLowerCase().startsWith("p")) {
           return Roshambo.PAPER;
       } else if (input.toLowerCase().startsWith("s")) {
           return Roshambo.SCISSORS;
           
       } else {
           System.out.println("That was invalid input. Try again.");
           return generateRoshambo();
       }
    }

}
