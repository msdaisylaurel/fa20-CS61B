public class HelloNumbers {
    public static void main(String[] args) {
      int x = 0;
      while (x < 45) { //control loop
        for (int i = 1; i <= 10; i++) {
          System.out.print(x + " ");
          x = x + i; //cumulative addition
        }
      }
      System.out.println(); //newline for aesthetics
    }
}
