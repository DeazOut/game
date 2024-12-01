import java.util.ArrayList;
import java.util.List;

class HangmanGame extends WordManager implements LetterChecker {
    private final List<Character> guessedLetters = new ArrayList<>();
    private final List<Character> incorrectLetters = new ArrayList<>();
    private final HangmanDrawer drawer = new HangmanDrawer();
    private int remainingAttempts = 7;

    public HangmanGame(String word) {
        super(word);
    }

    @Override
    public boolean checkLetter(char letter) {
        if (guessedLetters.contains(letter) || incorrectLetters.contains(letter)) {
            System.out.println("Вы уже угадывали эту букву.");
            return false;
        }

        if (getWordToGuess().indexOf(letter) >= 0) {
            guessedLetters.add(letter);
            updateCorrectGuesses(letter);
            return true;
        } else {
            incorrectLetters.add(letter);
            remainingAttempts--;
            return false;
        }
    }

    private void updateCorrectGuesses(char letter) {
        for (int i = 0; i < getWordToGuess().length(); i++) {
            if (getWordToGuess().charAt(i) == letter) {
                updateCurrentGuess(i, letter);
            }
        }
    }

    public void displayState() {
        System.out.println("Слово: " + String.valueOf(getCurrentGuess()));
        System.out.println("Ошибочные буквы: " + incorrectLetters);
        System.out.println("Осталось попыток: " + remainingAttempts);
        drawer.display(remainingAttempts);
    }

    public boolean isGameOver() {
        return remainingAttempts <= 0 || isWordGuessed();
    }

    public boolean isWordGuessed() {
        return String.valueOf(getCurrentGuess()).equals(getWordToGuess());
    }
}
