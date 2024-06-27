import java.util.Random;
import java.util.Scanner;

public class NumberGame {
    private static final int MIN_RANGE = 1;
    private static final int MAX_RANGE = 100;
    private static final int MAX_ATTEMPTS = 7;

    private int targetNumber;
    private int attempts;
    private int score;
    private int roundsPlayed;
    private Scanner scanner;
    private Random random;

    public NumberGame() {
        scanner = new Scanner(System.in);
        random = new Random();
        score = 0;
        roundsPlayed = 0;
    }

    public void startGame() {
        System.out.println("Welcome to the Number Game!");
        do {
            playRound();
            System.out.print("Play another round? (y/n): ");
        } while (scanner.next().toLowerCase().startsWith("y"));

        displayFinalScore();
    }

    private void playRound() {
        targetNumber = generateRandomNumber();
        attempts = 0;
        roundsPlayed++;

        System.out.println("\nRound " + roundsPlayed + " - Guess the number between " + MIN_RANGE + " and " + MAX_RANGE);

        while (attempts < MAX_ATTEMPTS) {
            int guess = getUserGuess();
            attempts++;

            if (guess == targetNumber) {
                System.out.println("Congratulations! You guessed the correct number in " + attempts + " attempts.");
                updateScore();
                return;
            } else {
                provideFeedback(guess);
            }
        }

        System.out.println("Sorry, you've run out of attempts. The number was " + targetNumber + ".");
    }

    private int generateRandomNumber() {
        return random.nextInt(MAX_RANGE - MIN_RANGE + 1) + MIN_RANGE;
    }

    private int getUserGuess() {
        System.out.print("Enter your guess (Attempt " + (attempts + 1) + "/" + MAX_ATTEMPTS + "): ");
        while (!scanner.hasNextInt()) {
            System.out.print("Invalid input. Please enter a number: ");
            scanner.next();
        }
        return scanner.nextInt();
    }

    private void provideFeedback(int guess) {
        if (guess < targetNumber) {
            System.out.println("Too low. Try again.");
        } else {
            System.out.println("Too high. Try again.");
        }
    }

    private void updateScore() {
        int roundScore = MAX_ATTEMPTS - attempts + 1;
        score += roundScore;
        System.out.println("Round score: " + roundScore + " points");
    }

    private void displayFinalScore() {
        System.out.println("\nGame Over!");
        System.out.println("Rounds played: " + roundsPlayed);
        System.out.println("Total score: " + score + " points");
    }

    public static void main(String[] args) {
        NumberGame game = new NumberGame();
        game.startGame();
    }
}