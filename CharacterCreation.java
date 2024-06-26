import java.util.Scanner;

/**
 * Handles character creation for the player, including setting the character's name, job class, and initial stats.
 * This class is responsible for prompting the user through the character creation process,
 * including selecting a job class which determines the character's starting stats.
 */
public class CharacterCreation {
    private String name;
    private String jobClass;
    private int hp;
    private int maxHp;
    private int baseHp;
    private int end;
    private int dex;
    private int str;
    private int intell;
    private int fth;
    private int runeCount = 0;
    private int currentHp;
    private Weapon selectedWeapon;

    /**
     * Initiates the character creation process. Allows the player to input a name, select a job class, and confirm their choices.
     * This method utilizes a loop to continuously prompt the player until they confirm their character creation or choose to go back.
     */
    public void createCharacter() {
        Scanner scanner = new Scanner(System.in);
        int choice = 0;
        do {
            clearScreen();
            System.out.println("Character Creation");
            System.out.println("[1] Input Name");
            System.out.println("[2] Select Job Class");
            System.out.println("[3] Confirm");
            System.out.println("[4] Back");
            System.out.print("Your Choice: ");
            while (!scanner.hasNextInt()) {
                System.out.println("Invalid input. Please enter a number between 1 and 4.");
                scanner.next(); // Consume the non-integer input
            }
            choice = scanner.nextInt();
            scanner.nextLine(); // Added to consume the newline character
            switch (choice) {
                case 1:
                    inputName(scanner);
                    break;
                case 2:
                    selectJobClass(scanner);
                    break;
                case 3:
                    if (name != null && !name.isEmpty() && jobClass != null && !jobClass.isEmpty()) {
                        System.out.println("Confirming character creation...");
                        printCharacterDetails();
                        GameLobby gameLobby = new GameLobby(name, jobClass, 1, 0, this); // Assuming initial level is 1 and initial runes is 0
                        gameLobby.showGameLobby();
                        return; // Exit the method after transitioning to the GameLobby
                    } else {
                        System.out.println("Please input name and select job class before confirming.");
                    }
                    break;
                case 4:
                    // Assuming TitleScreen class has a method to show itself
                    TitleScreen titleScreen = new TitleScreen();
                    titleScreen.showTitleScreen();
                    return; // Exit the character creation process
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        } while (true); // Changed to ensure the loop only exits on explicit conditions
    }

    /**
     * Prompts the player to input their character's name.
     * Ensures the name does not exceed a maximum length of 25 characters.
     *
     * @param scanner The Scanner object for input.
     */
    private void inputName(Scanner scanner) {
        System.out.println("Enter your name (Maximum 25 characters):");
        String inputName = scanner.nextLine(); // Changed to nextLine to allow spaces in names
        if (inputName.length() > 25) {
            inputName = inputName.substring(0, 25); // Truncate to 25 characters
        }
        name = inputName;
    }

    /**
     * Displays job class options and prompts the player to select one.
     * Each job class has different starting stats that are displayed to the player.
     *
     * @param scanner The Scanner object for input.
     */
    private void selectJobClass(Scanner scanner) {
        clearScreen();
        System.out.println("Select your job class:");
        System.out.println("_____________________________________________________________________________________________");
        System.out.println("| Option |   Job Class  |  HP  |  Endurance | Dexterity |  Strength  | Intelligence | Faith |");
        System.out.println("|-------------------------------------------------------------------------------------------|");
        System.out.println("|   [1]  |   Vagabond   |  15  |     11     |    13     |     14     |      9       |   9   |");
        System.out.println("|   [2]  |   Samurai    |  12  |     13     |    15     |     12     |      9       |   8   |");
        System.out.println("|   [3]  |   Warrior    |  11  |     15     |    16     |     10     |     10       |   8   |");
        System.out.println("|   [4]  |   Hero       |  14  |     12     |    9      |     16     |      7       |   8   |");
        System.out.println("|   [5]  |   Astrologer |  9   |     9      |    12     |     8      |     16       |   7   |");
        System.out.println("|   [6]  |   Prophet    |  10  |     8      |    10     |     11     |      7       |  16   |");
        System.out.println("|___________________________________________________________________________________________|");
        System.out.print("Your Option: ");
    
        int jobChoice = scanner.nextInt();
        switch (jobChoice) {
            case 1:
                jobClass = "Vagabond";
                setJobStats(15, 11, 13, 14, 9, 9);
                break;
            case 2:
                jobClass = "Samurai";
                setJobStats(12, 13, 15, 12, 9, 8);
                break;
            case 3:
                jobClass = "Warrior";
                setJobStats(11, 15, 16, 10, 10, 8);
                break;
            case 4:
                jobClass = "Hero";
                setJobStats(14, 12, 9, 16, 7, 8);
                break;
            case 5:
                jobClass = "Astrologer";
                setJobStats(9, 9, 12, 8, 16, 7);
                break;
            case 6:
                jobClass = "Prophet";
                setJobStats(10, 8, 10, 11, 7, 16);
                break;
            default:
                System.out.println("Invalid choice. Please try again.");
        }
        System.out.println("Selected job class: " + jobClass);
    }

    /**
     * Sets the job class stats based on the player's selection.
     * These stats include HP, Endurance, Dexterity, Strength, Intelligence, and Faith.
     *
     * @param hp The health points of the character.
     * @param end The endurance stat of the character.
     * @param dex The dexterity stat of the character.
     * @param str The strength stat of the character.
     * @param intell The intelligence stat of the character.
     * @param fth The faith stat of the character.
     */
    private void setJobStats(int hp, int end, int dex, int str, int intell, int fth) {
        this.hp = hp;
        this.end = end;
        this.dex = dex;
        this.str = str;
        this.intell = intell;
        this.fth = fth;
    }

    public void setCurrentHp(int currentHp){
        this.currentHp = currentHp;
    }

    // Additional setters and getters follow...

    /**
     * Calculates and sets the maximum health of the character based on the base health and any health bonuses from the equipped weapon.
     */
    public void calcMaxHp(){
        int weaponHp = this.selectedWeapon != null ? this.selectedWeapon.getHp() : 0;
        this.maxHp = 100 * (this.baseHp + weaponHp) / 2;
        this.hp = this.maxHp; // Set hp to maxHp
        this.currentHp = this.maxHp; // Initialize currentHp to maxHp
    }

    public void setMaxHp(int maxHp){
        this.maxHp = maxHp;
    }

    public void setHealth(int amount) {
        this.hp += amount;
    }

    public void setEndurance(int amount) {
        this.end += amount;
    }

    public void setDexterity(int amount) {
        this.dex += amount;
    }
    
    public void setStrength(int amount) {
        this.str += amount;
    }

    public void setIntelligence(int amount) {
        this.intell += amount;
    }

    public void setFaith(int amount) {
        this.fth += amount;
    }

    public void setSelectedWeapon(Weapon selectedWeapon){
        this.selectedWeapon = selectedWeapon;
    }

    public boolean addHp(int amount){
        if(currentHp + amount <= hp)
        {
            currentHp += amount;
            return true;
        }
        return false;
        
    }
    
    public boolean subtractHp(int amount){
        if(currentHp - amount >= 0)
        {
            currentHp -= amount;
            return true;
        }
        return false;
        
    }

    public Weapon getSelectedWeapon(){
        return this.selectedWeapon;
    }

    public String getName(){
        return this.name;
    }

    public int getHealth()
    {
        return this.hp;
    }

    public int getEndurance()
    {
        return this.end;
    }

    public int getDexterity()
    {
        return this.dex;
    }

    public int getStrength()
    {
        return this.str;
    }

    public int getIntelligence()
    {
        return this.intell;
    }

    public int getFaith()
    {
        return this.fth;
    }

    public int getCurrentHp(){
        return this.currentHp;
    }

    public int getMaxHp(){
        // Recalculate maxHp based on current attributes
        int weaponHp = this.selectedWeapon != null ? this.selectedWeapon.getHp() : 0;
        return 100 * (this.baseHp + weaponHp) / 2;
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
    

    /**
     * Prints the details of the character, including name, job class, and stats.
     * This method is called after confirming character creation to summarize the character's attributes.
     */
    private void printCharacterDetails() {
        System.out.println("Character Details:");
        System.out.println("Name: " + name);
        System.out.println("Job Class: " + jobClass);
        System.out.println("HP: " + hp);
        System.out.println("Endurance: " + end);
        System.out.println("Dexterity: " + dex);
        System.out.println("Strength: " + str);
        System.out.println("Intelligence: " + intell);
        System.out.println("Faith: " + fth);
    }

    public int getRuneCount() {
        return this.runeCount;
    }

    public void setRuneCount(int amount) {
        this.runeCount += amount;
    }

    public void subtractRuneCount(int amount) {
        this.runeCount -= amount;
    }
}
