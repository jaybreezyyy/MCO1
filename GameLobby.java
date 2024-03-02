import java.util.Scanner;

public class GameLobby {
    private String playerName;
    private String jobClass;
    private int level;
    private int runes;
    private CharacterCreation character;

    public GameLobby(String playerName, String jobClass, int level, int runes, CharacterCreation character) {
        this.playerName = playerName;
        this.jobClass = jobClass;
        this.level = level;
        this.runes = runes;
        this.character = character;
    }

    public void showGameLobby() {
        // Start the game lobby loop
        startGameLoop();
    }

    private void startGameLoop() {
        Scanner scanner = new Scanner(System.in);
        int choice = 0;
        do {
            clearScreen();
            // Show character details
            System.out.println("DETAILS:");
            System.out.println("Name: " + playerName);
            System.out.println("Job Class: " + jobClass);
            System.out.println("Level: " + level);
            System.out.println("Runes: " + character.getRuneCount());
            System.out.println("System Messages: \n\n");
            // Show GameLobby options 
            System.out.println("GAME LOBBY");
            System.out.println("[1] FAST TRAVEL");
            System.out.println("[2] LEVEL UP");
            System.out.println("[3] INVENTORY");
            System.out.println("[4] SHOP");
            System.out.println("[5] QUIT GAME");
            System.out.print("Your Choice: ");
            /* 
            while (!scanner.hasNextInt()) {
                System.out.println("Invalid input. Please enter a number between 1 and 5.");
                if(hasJustExitedMap)
                    hasJustExitedMap = false;
                else
                    scanner.next();
                 // Consume the non-integer input
            }
            */
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
                    System.out.println("Invalid input. Please enter a number between 1 and 5.");
            }
        } while (choice != 5); // Exit loop when user chooses to quitq
    }

    public void fastTravel() {
        Scanner scanner = new Scanner(System.in);
        boolean continueTraveling = true;
        do{
        System.out.println("Select destination:");
        System.out.println("[1] Stormveil Castle");
        System.out.println("[2] Raya Lucaria Academy [LOCKED]");
        System.out.println("[3] The Elden Throne [LOCKED]");
        System.out.print("Your Choice: ");
        int destination = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character

        switch (destination) {
            case 1:
                System.out.println("Fast traveling to Stormveil Castle...");
                continueTraveling = false;
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
        } while (continueTraveling);
        // Call the next Java code, Area.java, to continue the game
        Area area = new Area(character);
        area.play();
    }

    public void levelUp() {
        // Implementation for level up
        Scanner scanner = new Scanner(System.in);
        int choice = 0;
        boolean isRunning = true;
        boolean innerLoop;
        while(isRunning)
        {
            System.out.println("LEVEL UP");
            System.out.println("[1] Level Health");
            System.out.println("[2] Level Endurance");
            System.out.println("[3] Level Dexterity");
            System.out.println("[4] Level Strength");
            System.out.println("[5] Level Intelligence");
            System.out.println("[6] Level Faith");
            System.out.println("[7] Add Runes");
            System.out.println("[8] Back\n\n");

            System.out.println("DETAILS");
            System.out.println("Level: " + String.valueOf(level));
            System.out.println("Rune Cost: " + String.valueOf((level * 100) / 2));
            System.out.println("Runes: " + String.valueOf(character.getRuneCount()));
            System.out.println("Health: " + String.valueOf(character.getHealth()));
            System.out.println("Endurance: " + String.valueOf(character.getEndurance()));
            System.out.println("Dexterity: " + String.valueOf(character.getDexterity()));
            System.out.println("Strength: " + String.valueOf(character.getStrength()));
            System.out.println("Intelligence: " + String.valueOf(character.getIntelligence()));
            System.out.println("Faith: " + String.valueOf(character.getFaith()));
            System.out.println("System Messages: ");
            
            System.out.println("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    innerLoop = true;
                    while(innerLoop)
                    {
                        System.out.println("LEVEL HEALTH");
                        System.out.println("[1] Add Level to Health");
                        System.out.println("[2] Back\n");

                        System.out.println("Level: " + String.valueOf(level));
                        System.out.println("Health: " + String.valueOf(character.getHealth()));
                        System.out.println("Rune Cost: " + String.valueOf((level * 100) / 2));
                        System.out.println("Runes: " + String.valueOf(character.getRuneCount()));
                        
                        choice = scanner.nextInt();
                        scanner.nextLine();
                        switch(choice)
                        {
                            case 1:
                                if(character.getRuneCount() >= (level * 100) / 2)
                                {
                                    character.subtractRuneCount((level * 100) / 2);
                                    character.setHealth(1);
                                    level += 1;
                                    System.out.println("Your Health is now " + String.valueOf(character.getHealth() + "!"));
                                    System.out.println("You are now level " + String.valueOf(level) + "!");
                                }
                                else
                                    System.out.println("Insufficient RUNES :(");
                                break;
                            case 2:
                                System.out.println("Exiting this menu.");
                                innerLoop = false;
                                break;
                            default:
                                System.out.println("Invalid input.");
                                break;
                        }
                    }
                    break;
                case 2:
                    innerLoop = true;
                    while(innerLoop)
                    {
                        System.out.println("LEVEL ENDURANCE");
                        System.out.println("[1] Add Level Endurance");
                        System.out.println("[2] Back\n");

                        System.out.println("Level: " + String.valueOf(level));
                        System.out.println("Endurance: " + String.valueOf(character.getEndurance()));
                        System.out.println("Rune Cost: " + String.valueOf((level * 100) / 2));
                        System.out.println("Runes: " + String.valueOf(character.getRuneCount()));
                    
                        choice = scanner.nextInt();
                        scanner.nextLine();
                        switch(choice)
                        {
                            case 1:
                                if(character.getRuneCount() >= (level * 100) / 2)
                                {
                                    character.subtractRuneCount((level * 100) / 2);
                                    character.setEndurance(1);
                                    level += 1;
                                    System.out.println("Your Health is now " + String.valueOf(character.getEndurance() + "!"));
                                    System.out.println("You are now level " + String.valueOf(level) + "!");
                                }
                                else
                                    System.out.println("Insufficient RUNES :(");
                                break;
                            case 2:
                                System.out.println("Exiting this menu.");
                                innerLoop = false;
                                break;
                            default:
                                System.out.println("Invalid input.");
                                break;
                        }
                    }
                    break;
                case 3:
                    innerLoop = true;
                    while(innerLoop)
                    {
                        System.out.println("LEVEL DEXTERITY");
                        System.out.println("[1] Add Level Dexterity");
                        System.out.println("[2] Back\n");

                        System.out.println("Level: " + String.valueOf(level));
                        System.out.println("Dexterity: " + String.valueOf(character.getDexterity()));
                        System.out.println("Rune Cost: " + String.valueOf((level * 100) / 2));
                        System.out.println("Runes: " + String.valueOf(character.getRuneCount()));
                    
                        choice = scanner.nextInt();
                        scanner.nextLine();
                        switch(choice)
                        {
                            case 1:
                                if(character.getRuneCount() >= (level * 100) / 2)
                                {
                                    character.subtractRuneCount((level * 100) / 2);
                                    character.setDexterity(1);
                                    level += 1;
                                    System.out.println("Your Dexterity is now " + String.valueOf(character.getDexterity() + "!"));
                                    System.out.println("You are now level " + String.valueOf(level) + "!");
                                }
                                else
                                    System.out.println("Insufficient RUNES :(");
                                break;
                            case 2:
                                System.out.println("Exiting this menu.");
                                innerLoop = false;
                                break;
                            default:
                                System.out.println("Invalid input.");
                                break;
                        }
                    }
                    break;
                case 4:
                    innerLoop = true;
                    while(innerLoop)
                    {
                        System.out.println("LEVEL STRENGTH");
                        System.out.println("[1] Add Level Strength");
                        System.out.println("[2] Back\n");

                        System.out.println("Level: " + String.valueOf(level));
                        System.out.println("Strength: " + String.valueOf(character.getStrength()));
                        System.out.println("Rune Cost: " + String.valueOf((level * 100) / 2));
                        System.out.println("Runes: " + String.valueOf(character.getRuneCount()));
                    
                        choice = scanner.nextInt();
                        scanner.nextLine();
                        switch(choice)
                        {
                            case 1:
                                if(character.getRuneCount() >= (level * 100) / 2)
                                {
                                    character.subtractRuneCount((level * 100) / 2);
                                    character.setStrength(1);
                                    level += 1;
                                    System.out.println("Your Strength is now " + String.valueOf(character.getStrength() + "!"));
                                    System.out.println("You are now level " + String.valueOf(level) + "!");
                                }
                                else
                                    System.out.println("Insufficient RUNES :(");
                                break;
                            case 2:
                                System.out.println("Exiting this menu.");
                                innerLoop = false;
                                break;
                            default:
                                System.out.println("Invalid input.");
                                break;
                        }
                    }
                    break;
                case 5:
                innerLoop = true;
                while(innerLoop)
                {
                    System.out.println("LEVEL INTELLIGENCE");
                    System.out.println("[1] Add Level Intelligence");
                    System.out.println("[2] Back\n");

                    System.out.println("Level: " + String.valueOf(level));
                    System.out.println("Endurance: " + String.valueOf(character.getIntelligence()));
                    System.out.println("Rune Cost: " + String.valueOf((level * 100) / 2));
                    System.out.println("Runes: " + String.valueOf(character.getRuneCount()));
                    
                    choice = scanner.nextInt();
                    scanner.nextLine();
                    switch(choice)
                    {
                        case 1:
                            if(character.getRuneCount() >= (level * 100) / 2)
                            {
                                character.subtractRuneCount((level * 100) / 2);
                                character.setIntelligence(1);
                                level += 1;
                                System.out.println("Your Intelligence is now " + String.valueOf(character.getIntelligence() + "!"));
                                System.out.println("You are now level " + String.valueOf(level) + "!");
                            }
                            else
                                System.out.println("Insufficient RUNES :(");
                            break;
                        case 2:
                            System.out.println("Exiting this menu.");
                            innerLoop = false;
                            break;
                        default:
                            System.out.println("Invalid input.");
                            break;
                    }
                }
                break;
                case 6:
                innerLoop = true;
                    while(innerLoop)
                    {
                        System.out.println("LEVEL FAITH");
                        System.out.println("[1] Add Level to Faith");
                        System.out.println("[2] Back\n");

                        System.out.println("Level: " + String.valueOf(level));
                        System.out.println("Faith: " + String.valueOf(character.getFaith()));
                        System.out.println("Rune Cost: " + String.valueOf((level * 100) / 2));
                        System.out.println("Runes: " + String.valueOf(character.getRuneCount()));
                        
                        choice = scanner.nextInt();
                        scanner.nextLine();
                        switch(choice)
                        {
                            case 1:
                                if(character.getRuneCount() >= (level * 100) / 2)
                                {
                                    character.subtractRuneCount((level * 100) / 2);
                                    character.setFaith(1);
                                    level += 1;
                                    System.out.println("Your Faith is now " + String.valueOf(character.getFaith() + "!"));
                                    System.out.println("You are now level " + String.valueOf(level) + "!");
                                }
                                else
                                    System.out.println("Insufficient RUNES :(");
                                break;
                            case 2:
                                System.out.println("Exiting this menu.");
                                innerLoop = false;
                                break;
                            default:
                                System.out.println("Invalid input.");
                                break;
                        }
                    }
                    break;
                case 7:
                    System.out.println("ADD RUNES");
                    System.out.println("Enter amount of runes to add: ");
                    int amount = scanner.nextInt();
                    scanner.nextLine();
                    character.setRuneCount(amount);
                    break;
                case 8:
                    System.out.println("Exiting this menu.");
                    isRunning = false;
                    break;
                default:
                    System.out.println("Invalid input. Please enter a number between 1 and 8.");
                    break;
            }
        }
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
