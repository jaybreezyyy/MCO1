import java.util.Scanner;

public class GameLobby {
    private String playerName;
    private String jobClass;
    private int level;
    private int runes;

    public GameLobby(String playerName, String jobClass, int level, int runes) {
        this.playerName = playerName;
        this.jobClass = jobClass;
        this.level = level;
        this.runes = runes;
    }

    public void showGameLobby() {
        // Start the game lobby loop
        startGameLoop();
    }

    private void startGameLoop() {
        Scanner scanner = new Scanner(System.in);
        int choice = 0;
        do {
            System.out.println("GAME LOBBY");
            System.out.println("[1] FAST TRAVEL");
            System.out.println("[2] LEVEL UP");
            System.out.println("[3] INVENTORY");
            System.out.println("[4] SHOP");
            System.out.println("[5] QUIT GAME");
    
            // Show character details
            System.out.println("DETAILS:");
            System.out.println("Name: " + playerName);
            System.out.println("Job Class: " + jobClass);
            System.out.println("Level: " + level);
            System.out.println("Runes: " + runes);
            System.out.println("System Messages: ");
            System.out.println("Enter your choice:");

            while (!scanner.hasNextInt()) {
                System.out.println("Invalid input. Please enter a number between 1 and 5.");
                scanner.next();
                 // Consume the non-integer input
            }
            choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character
            switch (choice) {
                case 1:
                    fastTravel();
                    break;
                case 2:
                    levelUp();
                    break;
                case 3:
                    openInventory();
                    break;
                case 4:
                    openShop();
                    break;
                case 5:
                    quitGame();
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 5); // Exit loop when user chooses to quit
    }

    public void fastTravel() {
        System.out.println("Select destination:");
        System.out.println("[1] Stormveil Castle");
        System.out.println("[2] Raya Lucaria Academy [LOCKED]");
        System.out.println("[3] The Elden Throne [LOCKED]");

        Scanner scanner = new Scanner(System.in);
        int destination = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character

        switch (destination) {
            case 1:
                System.out.println("Fast traveling to Stormveil Castle...");
                break;
            case 2:
                System.out.println("Raya Lucaria Academy is currently locked.");
                break;
            case 3:
                System.out.println("The Elden Throne is currently locked.");
                break;
            default:
                System.out.println("Invalid destination. Please try again.");
        }

        // Call the next Java code, Area.java, to continue the game
        Area area = new Area();
        area.play();
    }

    public void levelUp() {
        // Implementation for level up
        System.out.println("Implementing level up feature...");
    }

    public void openInventory() {
        // Implementation for opening inventory
        System.out.println("Implementing inventory feature...");
    }

    public void openShop() {
        // Implementation for opening shop
        System.out.println("Implementing shop feature...");
    }

    public void quitGame() {
        System.out.println("Exiting the game...");
        TitleScreen titleScreen = new TitleScreen();
        titleScreen.showTitleScreen();
    }
}
