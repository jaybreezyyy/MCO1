import java.util.Scanner;

import javax.sound.midi.Soundbank;

public class GameLobby {
    private String playerName;
    private String jobClass;
    private int level;
    private int runes;
    private CharacterCreation character;
    private String weaponName;
    private int weaponCost;
    private int weaponDex;
    private int weaponHp;
    private int weaponEnd;
    private int weaponStr;
    private int weaponInt;
    private int weaponFth;


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
        Scanner scanner = new Scanner(System.in);
        int choice = 0;
        boolean isRunning = true;
        boolean innerLoop;

        while (isRunning)
        {
        System.out.println("Current Runes: " + String.valueOf(character.getRuneCount()));
        System.out.println("SHOP");
        System.out.println("Select The Type of Weapon");
        System.out.println("[1]Sword");
        System.out.println("[2]Katana");
        System.out.println("[3]Whips");
        System.out.println("[4]GreatSword");
        System.out.println("[5]Staves");
        System.out.println("[6]Seals");
        System.out.println("[7]Back");
        
        choice = scanner.nextInt();
        scanner.nextLine();

        switch(choice)
            {
                case 1:
                    innerLoop = true;
                        while(innerLoop){
                            System.out.println("Current Runes: " + String.valueOf(character.getRuneCount()));
                            System.out.println("SWORDS [Price]");
                            System.out.println("[1]Short Sword [1,000]");
                            System.out.println("[2]Rogier's Rapier [2,000]");
                            System.out.println("[3]Coded Sword [4,000]");
                            System.out.println("[4]Sword of Night and Flame [8,000]");
                            System.out.println("[5]Back");

                            System.out.println("Enter Your Choice Here:");

                            choice = scanner.nextInt();
                            scanner.nextLine();
                            switch(choice)
                            {
                                case 1:
                                    if(character.getRuneCount() >= 1000){
                                        character.subtractRuneCount(1000);
                                        weaponName = "Short Sword";
                                        setWeaponStat(0, 13, 15, 15, 15, 15);
                                        
                                        System.out.println("You've purchased Short Sword");
                                    }
                                    else
                                        System.out.println("Insufficient Runes :(");
                                break; 
                                case 2:
                                    if(character.getRuneCount() >= 2000){
                                        character.subtractRuneCount(2000);
                                        weaponName = "Rogier's Rapier";
                                        setWeaponStat(10, 18, 25, 35, 35, 35);
                                        System.out.println("You've purchased Rogier's Rapier");
                                    }
                                    else
                                        System.out.println("Insufficient Runes :(");
                                break;
                                case 3:
                                    if(character.getRuneCount() >= 4000){
                                        character.subtractRuneCount(4000);
                                        weaponName = "Coded Sword";
                                        setWeaponStat(20, 21, 30, 40, 40, 40);
                                        System.out.println("You've purchased Coded Sword");
                                    }
                                    else
                                        System.out.println("Insufficient Runes :(");
                                break;
                                case 4:
                                    if(character.getRuneCount() >= 8000){
                                        character.subtractRuneCount(8000);
                                        weaponName = "Sword of Night and Flame";
                                        setWeaponStat(30, 25, 45, 55, 55, 55);
                                        System.out.println("You've purchased Sword of Night and Flame");
                                    }
                                    else
                                        System.out.println("Insufficient Runes :(");
                                break;
                                case 5:
                                System.out.println("Exiting This Menu");
                                innerLoop = false;
                                break;
                                default:
                                System.out.println("Invalid Input");
                                break;

                            }
                        }
                    break;
                case 2:
                    innerLoop = true;
                        while(innerLoop){
                            System.out.println("Current Runes: " + String.valueOf(character.getRuneCount()));
                            System.out.println("Katana [Price]");
                            System.out.println("[1]Uchigatana [1,875]");
                            System.out.println("[2]Moonveil [3,750]");
                            System.out.println("[3]River of Blood [7,500]");
                            System.out.println("[4]Hand of Malenia [15,000]");
                            System.out.println("[5]Back");
                            
                            System.out.println("Enter Your Choice Here:");

                            choice = scanner.nextInt();
                            scanner.nextLine();
                            switch(choice)
                            {
                                case 1:
                                    if(character.getRuneCount() >= 1875){
                                        character.subtractRuneCount(1875); 
                                        weaponName = "Uchigatana";
                                        setWeaponStat(20, 15, 30, 0, 0, 30);
                                        System.out.println("You've purchased Uchigatana");
                                    }
                                    else
                                        System.out.println("Insufficient Runes :(");
                                break; 
                                case 2:
                                    if(character.getRuneCount() >= 3750){
                                        character.subtractRuneCount(3750);
                                        weaponName = "Moonveil";
                                        setWeaponStat(30, 18, 40, 0, 0, 45);
                                        System.out.println("You've purchased Moonveil");
                                    }
                                    else
                                        System.out.println("Insufficient Runes :(");
                                break;
                                case 3:
                                    if(character.getRuneCount() >= 7500){
                                        character.subtractRuneCount(7500);
                                        weaponName = "River of Blood";
                                        setWeaponStat(40, 25, 45, 0, 0, 60);
                                        System.out.println("You've purchased River of Blood");
                                    }
                                    else
                                        System.out.println("Insufficient Runes :(");
                                break;
                                case 4:
                                    if(character.getRuneCount() >= 15000){
                                        character.subtractRuneCount(15000);
                                        weaponName = "Hand of Malenia";
                                        setWeaponStat(50, 30, 50, 0, 0, 75);
                                        System.out.println("You've purchased Hand of Malenia");
                                    }
                                    else
                                        System.out.println("Insufficient Runes :(");
                                break;
                                case 5:
                                System.out.println("Exiting This Menu");
                                innerLoop = false;
                                break;
                                default:
                                System.out.println("Invalid Input");
                                break;
                            }
                        }
                    break;
                case 3:     
                    innerLoop = true;
                    while(innerLoop){
                        System.out.println("Current Runes: " + String.valueOf(character.getRuneCount()));
                        System.out.println("Whips [Price]");
                        System.out.println("[1]Whip [1,500]");
                        System.out.println("[2]Urumi [3,000]");
                        System.out.println("[3]Thorned Whip [5,000]");
                        System.out.println("[4]Hollow's Petal Whip [10,000]");
                        System.out.println("[5]Back");
                        
                        System.out.println("Enter Your Choice Here:");
                        choice = scanner.nextInt();
                        scanner.nextLine();
                            switch(choice)
                            {
                                case 1:
                                    if(character.getRuneCount() >= 1500){
                                        character.subtractRuneCount(1500);
                                        weaponName = "Whip";
                                        setWeaponStat(15, 20, 60, 0, 0, 20);
                                        System.out.println("You've purchased Whip");
                                    }
                                    else
                                        System.out.println("Insufficient Runes :(");
                                break; 
                                case 2:
                                    if(character.getRuneCount() >= 3000){
                                        character.subtractRuneCount(3000);
                                        weaponName = "Urumi";
                                        setWeaponStat(20, 25, 70, 0, 10, 40);
                                        System.out.println("You've purchased Urumi");
                                    }
                                    else
                                        System.out.println("Insufficient Runes :(");
                                break;
                                case 3:
                                    if(character.getRuneCount() >= 5000){
                                        character.subtractRuneCount(5000);
                                        weaponName = "Thorned Whip";
                                        setWeaponStat(30, 30, 80, 40, 0, 50);
                                        System.out.println("You've purchased Thorned Whip");
                                    }
                                    else
                                        System.out.println("Insufficient Runes :(");
                                break;
                                case 4:
                                    if(character.getRuneCount() >= 10000){
                                        character.subtractRuneCount(10000);
                                        weaponName = "Hollow's Petal Whip";
                                        setWeaponStat(35, 35, 90, 20, 20, 55);
                                        System.out.println("You've purchased Hollow's Petal Whip");
                                    }
                                    else
                                        System.out.println("Insufficient Runes :(");
                                break;
                                case 5:
                                System.out.println("Exiting This Menu");
                                innerLoop = false;
                                break;
                                default:
                                System.out.println("Invalid Input");
                                break;

                            }
                    break;
                    }
                case 4:
                    innerLoop = true;
                    while(innerLoop){
                        System.out.println("Current Runes: " + String.valueOf(character.getRuneCount()));
                        System.out.println("Greatswords [Price]");
                        System.out.println("[1]Claymore [3,000]");
                        System.out.println("[2]Starscourage Greatsword [6,000]");
                        System.out.println("[3]Inseprable Sword [12,000]");
                        System.out.println("[4]Maliketh's Black Blade [24,000]");
                        System.out.println("[5]Back");
                        
                        System.out.println("Enter Your Choice Here:");
                        choice = scanner.nextInt();
                        scanner.nextLine();
                            switch(choice)
                            {
                                case 1:
                                    if(character.getRuneCount() >= 3000){
                                        character.subtractRuneCount(3000);
                                        weaponName = "Claymore";
                                        setWeaponStat(15, 9, 10, 0, 0, 20);
                                        System.out.println("You've purchased Claymore");
                                    }
                                    else
                                        System.out.println("Insufficient Runes :(");
                                break; 
                                case 2:
                                    if(character.getRuneCount() >= 6000){
                                        character.subtractRuneCount(6000);
                                        weaponName = "Starscourage Greatsword";
                                        setWeaponStat(20, 14, 15, 20, 0, 40);
                                        System.out.println("You've purchased Starscourage Greatsword");
                                    }
                                    else
                                        System.out.println("Insufficient Runes :(");
                                break;
                                case 3:
                                    if(character.getRuneCount() >= 12000){
                                        character.subtractRuneCount(12000);
                                        weaponName = "Inseprable Sword";
                                        setWeaponStat(25, 19, 20, 60, 60, 70);
                                        System.out.println("You've purchased Inseprable Sword");
                                    }
                                    else
                                        System.out.println("Insufficient Runes :(");
                                break;
                                case 4:
                                    if(character.getRuneCount() >= 24000){
                                        character.subtractRuneCount(24000);
                                        weaponName = "Maliketh's Black Blade";
                                        setWeaponStat(30, 24, 25, 60, 40, 80);
                                        System.out.println("You've purchased Maliketh's Black Blade");
                                    }
                                    else
                                        System.out.println("Insufficient Runes :(");
                                break;
                                case 5:
                                System.out.println("Exiting This Menu");
                                innerLoop = false;
                                break;
                                default:
                                System.out.println("Invalid Input");
                                break;

                            }
                    break;
                    }
                case 5:
                    innerLoop = true;
                    while(innerLoop){
                        System.out.println("Current Runes: " + String.valueOf(character.getRuneCount()));
                        System.out.println("Staves [Price]");
                        System.out.println("[1]Astrologer's Staff [2,000]");
                        System.out.println("[2]Albinauric Staff [4,000]");
                        System.out.println("[3]Staff of The Guilty [8,000]");
                        System.out.println("[4]Carian Regal Scepter [16,000]");
                        System.out.println("[5]Back");
                        
                        System.out.println("Enter Your Choice Here:");
                        choice = scanner.nextInt();
                        scanner.nextLine();
                            switch(choice)
                            {
                                case 1:
                                    if(character.getRuneCount() >= 2000){
                                        character.subtractRuneCount(2000);
                                        weaponName = "Astrologer's Staff";
                                        setWeaponStat(5,12, 20, 15, 25, 5);
                                        System.out.println("You've purchased Astrologer's Staff");
                                    }
                                    else
                                        System.out.println("Insufficient Runes :(");
                                break; 
                                case 2:
                                    if(character.getRuneCount() >= 4000){
                                        character.subtractRuneCount(4000);
                                        weaponName = "Albinauric Staff";
                                        setWeaponStat(10, 14, 30, 35, 45, 10);
                                        System.out.println("You've purchased Albinauric Staff");
                                    }
                                    else
                                        System.out.println("Insufficient Runes :(");
                                break;
                                case 3:
                                    if(character.getRuneCount() >= 8000){
                                        character.subtractRuneCount(8000);
                                        weaponName = "Staff of The Guilty";
                                        setWeaponStat(15, 16, 40, 60, 65, 15);
                                        System.out.println("You've purchased Staff of The Guilty");
                                    }
                                    else
                                        System.out.println("Insufficient Runes :(");
                                break;
                                case 4:
                                    if(character.getRuneCount() >= 16000){
                                        character.subtractRuneCount(16000);
                                        weaponName = "Carian Regal Scepter";
                                        setWeaponStat(25, 18, 50, 75, 85, 20);
                                        System.out.println("You've purchased Carian Regal Scepter");
                                    }
                                    else
                                        System.out.println("Insufficient Runes :(");
                                break;
                                case 5:
                                System.out.println("Exiting This Menu");
                                innerLoop = false;
                                break;
                                default:
                                System.out.println("Invalid Input");
                                break;
                            }
                        }
                    break;
                case 6:
                    innerLoop = true;
                    while(innerLoop){
                        System.out.println("Current Runes: " + String.valueOf(character.getRuneCount()));
                        System.out.println("Seals [Price]");
                        System.out.println("[1]Finger Seal [2,500]");
                        System.out.println("[2]Godslayer's Seal [5,000]");
                        System.out.println("[3]Golden order Seal [10,000]");
                        System.out.println("[4]Dragon Communion Seal [15,000]");
                        System.out.println("[5]Back");
                        
                        System.out.println("Enter Your Choice Here:");
                        choice = scanner.nextInt();
                        scanner.nextLine();
                            switch(choice)
                            {
                                case 1:
                                    if(character.getRuneCount() >= 2500){
                                        character.subtractRuneCount(2500);
                                        weaponName = "Finger Seal";
                                        setWeaponStat(10,10, 45, 20, 15, 0);
                                        System.out.println("You've purchased Finger Seal");
                                    }
                                    else
                                        System.out.println("Insufficient Runes :(");
                                break; 
                                case 2:
                                    if(character.getRuneCount() >= 5000){
                                        character.subtractRuneCount(5000);
                                        weaponName = "Godslayer's Seal";
                                        setWeaponStat(15, 12, 50, 40, 35, 0);
                                        System.out.println("You've purchased Godslayer's Seal");
                                    }
                                    else
                                        System.out.println("Insufficient Runes :(");
                                break;
                                case 3:
                                    if(character.getRuneCount() >= 10000){
                                        character.subtractRuneCount(10000);
                                        weaponName = "Golden order Seal";
                                        setWeaponStat(20, 14, 55, 65, 65, 0);
                                        System.out.println("You've purchased Golden order Seal");
                                    }
                                    else
                                        System.out.println("Insufficient Runes :(");
                                break;
                                case 4:
                                    if(character.getRuneCount() >= 15000){
                                        character.subtractRuneCount(15000); 
                                        weaponName = "Dragon Communion Seal";
                                        setWeaponStat(25, 18, 60, 80, 75, 0);
                                        System.out.println("You've purchased Dragon Communion Seal");
                                    }
                                    else
                                        System.out.println("Insufficient Runes :(");
                                break;
                                case 5:
                                System.out.println("Exiting This Menu");
                                innerLoop = false;
                                break;
                                default:
                                System.out.println("Invalid Input");
                                break;
                            }
                    break;
                    }
                case 7:
                    System.out.println("Exiting this menu");
                    isRunning = false;
                    break; 
                default:
                    System.out.println("Invalid Input!");
                    break;   
                }         
            }
        }    

    public void setWeaponStat(int weaponHp, int weaponDex, int weaponEnd, int weaponFth, int weaponInt, int weaponStr){
        this.weaponHp = weaponHp;
        this.weaponDex = weaponDex;
        this.weaponEnd = weaponEnd;
        this.weaponFth = weaponFth;
        this.weaponInt = weaponInt;
        this.weaponStr = weaponStr;
    }

    public void setWeaponCost(int weaponCost){
        this.weaponCost = weaponCost;
    }

    public void setWeaponName(String weaponName){
        this.weaponName = weaponName;
    }


    public String getWeaponName(){
        return this.weaponName;
    }

    public int getWeaponCost(){
        return this.weaponCost;
    }
    public int getWeaponHp(){
        return this.weaponHp;
    }

    public int getweaponDex(){
        return this.weaponDex;
    }
    
    public int getweaponEnd(){
        return this.weaponEnd;
    }

    public int getweaponFth(){
        return this.weaponFth;
    }
    
    public int getweaponInt(){
        return this.weaponInt;
    }
    
    public int getweaponStr(){
        return this.weaponStr;
    }

    public void quitGame() {
        System.out.println("Exiting the game...");
        TitleScreen titleScreen = new TitleScreen();
        titleScreen.showTitleScreen();
    }
}
