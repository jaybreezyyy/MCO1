import java.util.Scanner;

public class TitleScreen {
    public void showTitleScreen() {
        System.out.println("Welcome to Elden Rogue!");
        System.out.println("[1] START");
        System.out.println("[2] EXIT");

        Scanner scanner = new Scanner(System.in);
        int choice = scanner.nextInt();
        switch (choice) {
            case 1:
                CharacterCreation characterCreation = new CharacterCreation();
                characterCreation.createCharacter();
                break;
            case 2:
                System.out.println("Exiting the game...");
                break;
            default:
                System.out.println("Invalid choice. Please try again.");
                showTitleScreen();
        }
    }
}
