import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;
import javax.sound.midi.Soundbank;

public class GameLobby {
    private List<Weapon> inventory = new ArrayList<>();
    private String playerName;
    private String jobClass;
    private int level;
    private int runes;
    private CharacterCreation character;
    private Weapon selectedWeapon;
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
            System.out.println("Health: " + String.valueOf(character.getHealth() + (selectedWeapon != null ? selectedWeapon.getHp() : 0)));
            System.out.println("Endurance: " + String.valueOf(character.getEndurance() + (selectedWeapon != null ? selectedWeapon.getEnd() : 0)));
            System.out.println("Dexterity: " + String.valueOf(character.getDexterity() + (selectedWeapon != null ? selectedWeapon.getDex() : 0)));
            System.out.println("Strength: " + String.valueOf(character.getStrength() + (selectedWeapon != null ? selectedWeapon.getStr() : 0)));
            System.out.println("Intelligence: " + String.valueOf(character.getIntelligence() + (selectedWeapon != null ? selectedWeapon.getIntel() : 0)));
            System.out.println("Faith: " + String.valueOf(character.getFaith() + (selectedWeapon != null ? selectedWeapon.getFth() : 0)));
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

    public void openInventory() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("INVENTORY:");
        for (int i = 0; i < inventory.size(); i++) {
            Weapon weapon = inventory.get(i);
            // Display each weapon with its dexterity requirement
            System.out.println("[" + (i+1) + "] " + weapon.getName() + " (Dex Req: " + weapon.getDexReq() + ")");
        }
        System.out.println("[" + (inventory.size() + 1) + "] Back");
    
        System.out.print("Select a weapon or go back: ");
        int choice = scanner.nextInt();
        if (choice > 0 && choice <= inventory.size()) {
            Weapon selectedWeapon = inventory.get(choice - 1);
            if (character.getDexterity() >= selectedWeapon.getDexReq()) {
                this.selectedWeapon = selectedWeapon; // Assign the selected weapon
                System.out.println(selectedWeapon.getName() + " equipped!");
            } else {
                System.out.println("Cannot equip " + selectedWeapon.getName() + ". Dexterity requirement not met.");
            }
        } else if (choice == inventory.size() + 1) {
            System.out.println("Returning to game lobby...");
        } else {
            System.out.println("Invalid selection. Please try again.");
        }
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
                            System.out.println("[Dexterity:13][Health:0][Endurance:15][Strength:15][Intelligence:15][Faith:15]");
                            System.out.println("[2]Rogier's Rapier [2,000]");
                            System.out.println("[Dexterity:18][Health:10][Endurance:25][Strength:35][Intelligence:35][Faith:35]");
                            System.out.println("[3]Coded Sword [4,000]");
                            System.out.println("[Dexterity:21][Health:20][Endurance:30][Strength:40][Intelligence:40][Faith:40]");
                            System.out.println("[4]Sword of Night and Flame [8,000]");
                            System.out.println("[Dexterity:25][Health:30][Endurance:45][Strength:55][Intelligence:55][Faith:55]");
                            System.out.println("[5]Back");

                            System.out.println("Enter Your Choice Here:");

                            choice = scanner.nextInt();
                            scanner.nextLine();
                            switch(choice)
                            {
                                case 1:
                                    if(character.getRuneCount() >= 1000){
                                        character.subtractRuneCount(1000);
                                        Weapon ShortSword = new Weapon("Short Sword", 0, 0, 15, 15, 15, 15, 13);
                                        inventory.add(ShortSword);
                                        
                                        System.out.println("You've purchased Short Sword");
                                    }
                                    else
                                        System.out.println("Insufficient Runes :(");
                                break; 
                                case 2:
                                    if(character.getRuneCount() >= 2000){
                                        character.subtractRuneCount(2000);
                                        Weapon RogerRapier = new Weapon("Rogier's Rapier", 10, 0, 25, 35, 35, 35, 18);
                                        inventory.add(RogerRapier);

                                        System.out.println("You've purchased Rogier's Rapier");
                                    }
                                    else
                                        System.out.println("Insufficient Runes :(");
                                break;
                                case 3:
                                    if(character.getRuneCount() >= 4000){
                                        character.subtractRuneCount(4000);
                                        Weapon CodedSword = new Weapon("Coded Sword", 20, 0, 35, 40, 40, 40, 21);
                                        inventory.add(CodedSword);
                                        System.out.println("You've purchased Coded Sword");
                                    }
                                    else
                                        System.out.println("Insufficient Runes :(");
                                break;
                                case 4:
                                    if(character.getRuneCount() >= 8000){
                                        character.subtractRuneCount(8000);
                                        Weapon NightFlame = new Weapon("Sword of Night and Flame", 30, 0, 45, 55, 55, 55, 25);
                                        inventory.add(NightFlame);
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
                            System.out.println("[Dexterity:15][Health:20][Endurance:35][Strength:30][Intelligence:0][Faith:0]");
                            System.out.println("[2]Moonveil [3,750]");
                            System.out.println("[Dexterity:20][Health:30][Endurance:40][Strength:45][Intelligence:0][Faith:0]");
                            System.out.println("[3]River of Blood [7,500]");
                            System.out.println("[Dexterity:25][Health:40][Endurance:45][Strength:60][Intelligence:0][Faith:0]");
                            System.out.println("[4]Hand of Malenia [15,000]");
                            System.out.println("[Dexterity:30][Health:50][Endurance:50][Strength:75][Intelligence:0][Faith:0]");
                            System.out.println("[5]Back");
                            
                            System.out.println("Enter Your Choice Here:");

                            choice = scanner.nextInt();
                            scanner.nextLine();
                            switch(choice)
                            {
                                case 1:
                                    if(character.getRuneCount() >= 1875){
                                        character.subtractRuneCount(1875); 
                                        Weapon uchigatana = new Weapon("Uchigattana", 20, 0, 35, 30, 0, 0, 15);
                                        inventory.add(uchigatana);
                                        System.out.println("You've purchased Uchigatana");
                                    }
                                    else
                                        System.out.println("Insufficient Runes :(");
                                break; 
                                case 2:
                                    if(character.getRuneCount() >= 3750){
                                        character.subtractRuneCount(3750);
                                        Weapon moonveil = new Weapon("Moonveil", 30, 0, 40, 45, 0, 0, 20);
                                        inventory.add(moonveil);
                                        System.out.println("You've purchased Moonveil");
                                    }
                                    else
                                        System.out.println("Insufficient Runes :(");
                                break;
                                case 3:
                                    if(character.getRuneCount() >= 7500){
                                        character.subtractRuneCount(7500);
                                        Weapon riverBlood = new Weapon("River of Blood", 40, 0, 45, 60, 0, 0, 25);
                                        inventory.add(riverBlood);
                                        System.out.println("You've purchased River of Blood");
                                    }
                                    else
                                        System.out.println("Insufficient Runes :(");
                                break;
                                case 4:
                                    if(character.getRuneCount() >= 15000){
                                        character.subtractRuneCount(15000);
                                        Weapon malenia = new Weapon("Hand of Malenia", 50, 0, 50, 75, 0, 0, 30);
                                        inventory.add(malenia);
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
                        System.out.println("[Dexterity:20][Health:15][Endurance:60][Strength:20][Intelligence:0][Faith:0]");
                        System.out.println("[2]Urumi [3,000]");
                        System.out.println("[Dexterity:25][Health:20][Endurance:70][Strength:40][Intelligence:10][Faith:0]");
                        System.out.println("[3]Thorned Whip [5,000]");
                        System.out.println("[Dexterity:30][Health:30][Endurance:80][Strength:50][Intelligence:0][Faith:40]");
                        System.out.println("[4]Hollow's Petal Whip [10,000]");
                        System.out.println("[Dexterity:35][Health:35][Endurance:90][Strength:55][Intelligence:20][Faith:20]");
                        System.out.println("[5]Back");
                        
                        System.out.println("Enter Your Choice Here:");
                        choice = scanner.nextInt();
                        scanner.nextLine();
                            switch(choice)
                            {
                                case 1:
                                    if(character.getRuneCount() >= 1500){
                                        character.subtractRuneCount(1500);
                                        Weapon whip = new Weapon("Whip", 15, 0, 60, 20, 0, 0, 20);
                                        inventory.add(whip);
                                        System.out.println("You've purchased Whip");
                                    }
                                    else
                                        System.out.println("Insufficient Runes :(");
                                break; 
                                case 2:
                                    if(character.getRuneCount() >= 3000){
                                        character.subtractRuneCount(3000);
                                        Weapon urumi = new Weapon("Urumi", 20, 0, 70, 40, 10, 0, 25);
                                        inventory.add(urumi);
                                        System.out.println("You've purchased Urumi");
                                    }
                                    else
                                        System.out.println("Insufficient Runes :(");
                                break;
                                case 3:
                                    if(character.getRuneCount() >= 5000){
                                        character.subtractRuneCount(5000);
                                        Weapon thorned = new Weapon("Thorned Whip", 30, 0, 80, 50, 0, 40, 30);
                                        inventory.add(thorned);
                                        System.out.println("You've purchased Thorned Whip");
                                    }
                                    else
                                        System.out.println("Insufficient Runes :(");
                                break;
                                case 4:
                                    if(character.getRuneCount() >= 10000){
                                        character.subtractRuneCount(10000);
                                        Weapon petalWhip = new Weapon("Hollow's Petal Whip", 35, 0, 90, 55, 20, 20, 35);
                                        inventory.add(petalWhip);
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
                        System.out.println("[Dexterity:9][Health:15][Endurance:10][Strength:20][Intelligence:0][Faith:0]");
                        System.out.println("[2]Starscourage Greatsword [6,000]");
                        System.out.println("[Dexterity:14][Health:20][Endurance:15][Strength:40][Intelligence:0][Faith:20]");
                        System.out.println("[3]Inseprable Sword [12,000]");
                        System.out.println("[Dexterity:19][Health:25][Endurance:20][Strength:70][Intelligence:60][Faith:60]");
                        System.out.println("[4]Maliketh's Black Blade [24,000]");
                        System.out.println("[Dexterity:24][Health:30][Endurance:25][Strength:80][Intelligence:40][Faith:60]");
                        System.out.println("[5]Back");
                        
                        System.out.println("Enter Your Choice Here:");
                        choice = scanner.nextInt();
                        scanner.nextLine();
                            switch(choice)
                            {
                                case 1:
                                    if(character.getRuneCount() >= 3000){
                                        character.subtractRuneCount(3000);
                                        Weapon claymore = new Weapon("Claymore", 15, 0, 10, 20, 0, 0, 9);
                                        inventory.add(claymore);
                                        System.out.println("You've purchased Claymore");
                                    }
                                    else
                                        System.out.println("Insufficient Runes :(");
                                break; 
                                case 2:
                                    if(character.getRuneCount() >= 6000){
                                        character.subtractRuneCount(6000);
                                        Weapon stars = new Weapon("Starscouraage Greatsword", 20, 0, 15, 40, 0, 20, 14);
                                        inventory.add(stars);
                                        System.out.println("You've purchased Starscourage Greatsword");
                                    }
                                    else
                                        System.out.println("Insufficient Runes :(");
                                break;
                                case 3:
                                    if(character.getRuneCount() >= 12000){
                                        character.subtractRuneCount(12000);
                                        Weapon insperable = new Weapon("Inseprable Sword", 25, 0, 20, 70, 60, 60, 19);
                                        inventory.add(insperable);
                                        System.out.println("You've purchased Inseprable Sword");
                                    }
                                    else
                                        System.out.println("Insufficient Runes :(");
                                break;
                                case 4:
                                    if(character.getRuneCount() >= 24000){
                                        character.subtractRuneCount(24000);
                                        Weapon maliketh = new Weapon("Maliketh's Black Blade", 30, 0, 25, 80, 40, 60, 24);
                                        inventory.add(maliketh);
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
                        System.out.println("[Dexterity:12][Health:5][Endurance:20][Strength:5][Intelligence:25][Faith:15]");
                        System.out.println("[2]Albinauric Staff [4,000]");
                        System.out.println("[Dexterity:14][Health:10][Endurance:30][Strength:10][Intelligence:45][Faith:35]");
                        System.out.println("[3]Staff of The Guilty [8,000]");
                        System.out.println("[Dexterity:16][Health:15][Endurance:40][Strength:15][Intelligence:65][Faith:60]");
                        System.out.println("[4]Carian Regal Scepter [16,000]");
                        System.out.println("[Dexterity:18][Health:25][Endurance:50][Strength:20][Intelligence:85][Faith:75]");
                        System.out.println("[5]Back");
                        
                        System.out.println("Enter Your Choice Here:");
                        choice = scanner.nextInt();
                        scanner.nextLine();
                            switch(choice)
                            {
                                case 1:
                                    if(character.getRuneCount() >= 2000){
                                        character.subtractRuneCount(2000);
                                        Weapon astro = new Weapon("Astrologer's Staff", 5, 0, 20, 5, 25, 15, 12);
                                        inventory.add(astro);
                                        System.out.println("You've purchased Astrologer's Staff");
                                    }
                                    else
                                        System.out.println("Insufficient Runes :(");
                                break; 
                                case 2:
                                    if(character.getRuneCount() >= 4000){
                                        character.subtractRuneCount(4000);
                                        Weapon albi = new Weapon("Albinauric Staff", 10, 0, 30, 10, 45, 35, 12);
                                        inventory.add(albi);
                                        System.out.println("You've purchased Albinauric Staff");
                                    }
                                    else
                                        System.out.println("Insufficient Runes :(");
                                break;
                                case 3:
                                    if(character.getRuneCount() >= 8000){
                                        character.subtractRuneCount(8000);
                                        Weapon guilty = new Weapon("Staff of the Guilty", 15, 0, 40, 15, 65, 60, 16);
                                        inventory.add(guilty);
                                        System.out.println("You've purchased Staff of The Guilty");
                                    }
                                    else
                                        System.out.println("Insufficient Runes :(");
                                break;
                                case 4:
                                    if(character.getRuneCount() >= 16000){
                                        character.subtractRuneCount(16000);
                                        Weapon carian = new Weapon("Crian Regal Scepter", 25, 0, 50, 20, 85, 75, 18);
                                        inventory.add(carian);
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
                        System.out.println("[Dexterity:10][Health:10][Endurance:45][Strength:0][Intelligence:15][Faith:20]");
                        System.out.println("[2]Godslayer's Seal [5,000]");
                        System.out.println("[Dexterity:12][Health:15][Endurance:50][Strength:0][Intelligence:35][Faith:40]");
                        System.out.println("[3]Golden order Seal [10,000]");
                        System.out.println("[Dexterity:14][Health:20][Endurance:55][Strength:0][Intelligence:65][Faith:65]");
                        System.out.println("[4]Dragon Communion Seal [15,000]");
                        System.out.println("[Dexterity:18][Health:25][Endurance:60][Strength:0][Intelligence:75][Faith:80]");
                        System.out.println("[5]Back");
                        
                        System.out.println("Enter Your Choice Here:");
                        choice = scanner.nextInt();
                        scanner.nextLine();
                            switch(choice)
                            {
                                case 1:
                                    if(character.getRuneCount() >= 2500){
                                        character.subtractRuneCount(2500);
                                        Weapon finger = new Weapon("Finger Seal", 10, 0, 45, 0, 15, 20, 10);
                                        inventory.add(finger);
                                        System.out.println("You've purchased Finger Seal");
                                    }
                                    else
                                        System.out.println("Insufficient Runes :(");
                                break; 
                                case 2:
                                    if(character.getRuneCount() >= 5000){
                                        character.subtractRuneCount(5000);
                                        Weapon god = new Weapon("Godslayer's Seal", 15, 0, 50, 0, 35, 40, 12);
                                        inventory.add(god);
                                        System.out.println("You've purchased Godslayer's Seal");
                                    }
                                    else
                                        System.out.println("Insufficient Runes :(");
                                break;
                                case 3:
                                    if(character.getRuneCount() >= 10000){
                                        character.subtractRuneCount(10000);
                                        Weapon gold = new Weapon("Golden order Seal", 20, 0, 55, 0, 65, 65, 14);
                                        inventory.add(gold);
                                        System.out.println("You've purchased Golden order Seal");
                                    }
                                    else
                                        System.out.println("Insufficient Runes :(");
                                break;
                                case 4:
                                    if(character.getRuneCount() >= 15000){
                                        character.subtractRuneCount(15000); 
                                        Weapon dragon = new Weapon("Dragon Communion Seal", 25, 0, 60, 0, 75, 80, 18);
                                        inventory.add(dragon);
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

    public void setWeaponStat(int weaponHp, int weaponDex, int weaponEnd, int weaponFth, int weaponInt, int weaponStr, int dexReq){
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

    public int getWeaponDex(){
        return this.weaponDex;
    }
    
    public int getWeaponEnd(){
        return this.weaponEnd;
    }

    public int getWeaponFth(){
        return this.weaponFth;
    }
    
    public int getWeaponInt(){
        return this.weaponInt;
    }
    
    public int getWeaponStr(){
        return this.weaponStr;
    }

    public void quitGame() {
        System.out.println("Exiting the game...");
        TitleScreen titleScreen = new TitleScreen();
        titleScreen.showTitleScreen();
    }
}

class Weapon {
    private String name;
    private int hp, dex, end, str, intel, fth, dexReq;

    public Weapon(String name, int hp, int dex, int end, int str, int intel, int fth, int dexReq) {
        this.name = name;
        this.hp = hp;
        this.dex = dex;
        this.end = end;
        this.str = str;
        this.intel = intel;
        this.fth = fth;
        this.dexReq = dexReq;
    }

    // Getters
    public Weapon getSelectedWeapon() {
        return this.selectedWeapon;
    }
    
    public String getName() {
        return name;
    }

    public int getHp() {
        return hp;
    }

    public int getDex() {
        return dex;
    }

    public int getEnd() {
        return end;
    }

    public int getStr() {
        return str;
    }

    public int getIntel() {
        return intel;
    }

    public int getFth() {
        return fth;
    }

    public int getDexReq(){
        return dexReq;
    }
}
