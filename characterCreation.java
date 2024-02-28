import java.util.Scanner;

public class CharacterCreation {
    private String name;
    private String jobClass;
    private int hp;
    private int end;
    private int dex;
    private int str;
    private int intell;
    private int fth;

    public void createCharacter() {
        System.out.println("Character Creation");

        Scanner scanner = new Scanner(System.in);

        System.out.println("[1] Input Name");
        System.out.println("[2] Select Job Class");
        System.out.println("[3] Confirm");
        System.out.println("[4] Back");

        int choice = scanner.nextInt();
        switch (choice) {
            case 1:
                System.out.println("Enter your name:");
                name = scanner.next();
                createCharacter(); // After inputting the name, continue with the character creation process
                break;
            case 2:
                selectJobClass(scanner);
                break;
            case 3:
                if (name != null && jobClass != null) {
                    System.out.println("Confirming character creation...");
                    // Handle confirmation
                    printCharacterDetails();
                } else {
                    System.out.println("Please input name and select job class before confirming.");
                    createCharacter(); // Go back to character creation
                }
                break;
            case 4:
                TitleScreen titleScreen = new TitleScreen();
                titleScreen.showTitleScreen();
                break;
            default:
                System.out.println("Invalid choice. Please try again.");
                createCharacter();
        }
    }

    private void selectJobClass(Scanner scanner) {
        System.out.println("Select your job class:");
        System.out.println("[1] Vagabond");
        System.out.println("[2] Samurai");
        System.out.println("[3] Warrior");
        System.out.println("[4] Hero");
        System.out.println("[5] Astrologer");
        System.out.println("[6] Prophet");

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
                selectJobClass(scanner);
        }
        System.out.println("Selected job class: " + jobClass);
    }

    private void setJobStats(int hp, int end, int dex, int str, int intell, int fth) {
        this.hp = hp;
        this.end = end;
        this.dex = dex;
        this.str = str;
        this.intell = intell;
        this.fth = fth;
    }

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
}
