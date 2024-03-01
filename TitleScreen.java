import java.util.Scanner;

public class TitleScreen {
    public void showTitleScreen() {
        // ANSI escape code for yellow text
        final String ANSI_YELLOW = "\u001B[33m";
        // ANSI escape code to reset text formatting
        final String ANSI_RESET = "\u001B[0m";
        
        // New ASCII Art for the title screen in yellow
        System.out.println(ANSI_YELLOW + 
            "___________.__       .___                                          ___________ \n" +
            "\\_   _____/|  |    __| _/____   ____   _______  ____   ____  __ _. \\____    _/ \n" +
            " |    __)_ |  |   / __ |/ __ \\ /    \\  \\_  __ \\/  _ \\ / ___\\|  |  \\ _(__    | \n" +
            " |        \\|  |__/ /_/ \\  ___/|   |  \\  |  | \\(  <_> ) /_/  >  |  //        | \n" +
            "/_______  /|____/\\____ |\\___  >___|  /  |__|   \\____/\\___  /|____/ \\  _______/ \n" +
            "        \\/            \\/    \\/     \\/               /_____/         \\/ " + ANSI_RESET);
        
        // Welcome message and menu
        System.out.println("Welcome to Elden Rogue!");
        System.out.println("[1] START");
        System.out.println("[2] EXIT");
        System.out.print("Your Choice: ");

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
