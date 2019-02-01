import java.util.Random;
import java.util.*;


public class RandomPlayer extends Player{

    public RandomPlayer() {}
      
        public RandomPlayer(String name) {super(name);}
        
        
        @Override
        public Roshambo generateRoshambo() {
            Random random = new Random();
            
            int n = random.nextInt(3);

            if (n == 0) {
                return Roshambo.PAPER;
            } else if (n == 1) {
                return Roshambo.ROCK;
            } else if (n == 2) {
                return Roshambo.SCISSORS;
            }
            
            return Roshambo.PAPER;
    }



}
    

