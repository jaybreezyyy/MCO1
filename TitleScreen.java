import java.util.Scanner;

public class TitleScreen {
    public void showTitleScreen() {
        clearScreen();
        
        // Call the static method from the TitleScreen class
        printTitle();

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

    // Modifier method for printing ASCII art
    public static void printTitle() {
        // ANSI escape code for yellow text
        String ANSI_YELLOW = "\u001B[33m";
        // ANSI escape code to reset text formatting
        String ANSI_RESET = "\u001B[0m";
    
        System.out.println(ANSI_YELLOW + 
            "___________.__       .___                                          ___________ \n" +
            "\\_   _____/|  |    __| _/____   ____   _______  ____   ____  __ _. \\____    _/ \n" +
            " |    __)_ |  |   / __ |/ __ \\ /    \\  \\_  __ \\/  _ \\ / ___\\|  |  \\ _(__    | \n" +
            " |        \\|  |__/ /_/ \\  ___/|   |  \\  |  | \\(  <_> ) /_/  >  |  //        | \n" +
            "/_______  /|____/\\____ |\\___  >___|  /  |__|   \\____/\\___  /|____/ \\  _______/ \n" +
            "        \\/            \\/    \\/     \\/               /_____/         \\/ " + ANSI_RESET);
    }

    // FOR WINDOWS (SYSTEM CLEAR SCREEN)
    /*public static void clearScreen() { 
        try {
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
        } catch (Exception e) {
            System.out.println(e);
        }
    }*/

    // FOR MAC/UNIX/LINUX OPERATION SYSTEMS (SYSTEM CLEAR SCREEN)
    public static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}
