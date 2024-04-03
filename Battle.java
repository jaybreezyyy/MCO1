import java.util.Scanner;


import java.util.Random;

public class Battle {
    private CharacterCreation character;
    private int areaIndex;
    private String enemyName;
    private int enemyIndex;
    private int enemyAttack;
    private int enemyHealth;
    private double enemyPhysicalDef;
    private double enemySorceryDef;
    private double enemyIncantationDef;
    

    public Battle(CharacterCreation character, int areaIndex){
        this.character = character;
        this.areaIndex = areaIndex;
        generateEnemy();
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

    public boolean display(){
        while(true){
            character.setMaxHp(100 * (character.getHealth() + character.getSelectedWeapon().getHp()) / 2);
            if(character.getCurrentHp() == 0)
            {
                System.out.println(character.getName() + " has died!");
                return false;
            }

            if(this.enemyHealth <= 0)
            {
                System.out.println(character.getName() + " has slain " + this.enemyName + "!");
                return true;
            }
            
            boolean hasDodged = false;
            System.out.println("Player Name:" + character.getName());
            System.out.println("Player Health:" + character.getCurrentHp());
            System.out.println("Enemy Name: " + this.enemyName);
            System.out.println("Enemy Health:" + this.enemyHealth);

            System.out.println("[1]Attack");
            System.out.println("[2]Dodge");

            System.out.println("Enter your choice here:");
            Scanner scanner = new Scanner(System.in);
            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    attackMenu();
                    break;
                case 2:
                    int dodge_rate = 20 + (character.getEndurance() + character.getSelectedWeapon().getEnd()) / 2;
                    int dodge_success = (int) Math.floor(Math.random() * 100);
                    if(dodge_success <= dodge_rate)
                        hasDodged = true;
                    break;
                default:
                    System.out.println("Input is Invalid");
                    break;
            }
            if(hasDodged)
                System.out.println(character.getName() + " has dodged the attack!");
            else 
                enemyTurn();
        }
    }

    public void attackMenu(){
        boolean isRunning = true;
        while (isRunning) {
            System.out.println("[1]Physical");
            System.out.println("[2]Sorcery");
            System.out.println("[3]Incantation");
            System.out.println("[4]Back");

            System.out.println("Enter your choice Here:");
            Scanner scanner = new Scanner(System.in);
            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    int physicalDmgDealt = attackPhysical();
                    System.out.println(character.getName() + " did " + String.valueOf(physicalDmgDealt) + " damage!");
                    isRunning = false;
                    break;
                case 2:
                    int sorceryDmgDealt = attackSorcery();
                    System.out.println(character.getName() + " did " + String.valueOf(sorceryDmgDealt) + " damage!");
                    isRunning = false;
                    break;
                case 3:
                    int incantationDmgDealt = attackIncantation();
                    System.out.println(character.getName() + " did " + String.valueOf(incantationDmgDealt) + " damage!");
                    isRunning = false;
                    break;
                case 4:
                    isRunning = false;
                    break;
                default:
                    System.out.println("Input is Invalid");
                    break;
            }
        }
    }

    public int attackPhysical() {
        this.enemyHealth -= (int) (((double) (character.getStrength() + character.getSelectedWeapon().getStr())) * (1.0 - this.enemyPhysicalDef));
        return (int) (((double) (character.getStrength() + character.getSelectedWeapon().getStr())) * (1.0 - this.enemyPhysicalDef));
    }

    public int attackSorcery() {
        this.enemyHealth -= (int) (((double) (character.getIntelligence() + character.getSelectedWeapon().getIntel())) * (1.0 - this.enemySorceryDef));
        return (int) (((double) (character.getIntelligence() + character.getSelectedWeapon().getIntel())) * (1.0 - this.enemySorceryDef));
    }

    public int attackIncantation() {
        this.enemyHealth -= (int) (((double) (character.getFaith() + character.getSelectedWeapon().getFth())) * (1.0 - this.enemyIncantationDef));
        return (int) (((double) (character.getFaith() + character.getSelectedWeapon().getFth())) * (1.0 - this.enemyIncantationDef));
    }

    public void enemyTurn() {
        if(character.subtractHp(this.enemyAttack))
            System.out.println(this.enemyName + " dealt " + this.enemyAttack + " damage!");
        else
            character.subtractHp(character.getCurrentHp());
    }

    public void generateEnemy(){
        int randomType = (int)Math.floor(Math.random() * 3 + 1);
        this.enemyIndex = randomType;
        if(randomType == 1) {
            if(this.areaIndex == 1){
                this.enemyName = "GodrickSolider";

            }else if(this.areaIndex == 2){
                this.enemyName = "Living Jar";
            }
            this.enemyAttack = (int)Math.floor(Math.random() * 11 + 70);
            this.enemyHealth = (int)Math.floor(Math.random() * 11 + 20);
            this.enemyPhysicalDef = .2;
            this.enemySorceryDef = .15;
            this.enemyIncantationDef = .1;
        }else if(randomType == 2){
            if(this.areaIndex == 1){
                this.enemyName = "Godrick Archer";

            }else if(this.areaIndex == 2){
                this.enemyName = "Glint Stone sorcerer";
            }
            this.enemyAttack = (int)Math.floor(Math.random() * 11 + 110);
            this.enemyHealth = (int)Math.floor(Math.random() * 11 + 25);
            this.enemyPhysicalDef = .5;
            this.enemySorceryDef = .15;
            this.enemyIncantationDef = .2;
        }else{
            if(this.areaIndex == 1){
                this.enemyName = "Godrick Knight";

            }else if(this.areaIndex == 2){
                this.enemyName = "Battlemage";
            }
            this.enemyAttack = (int)Math.floor(Math.random() * 11 + 120);
            this.enemyHealth = (int)Math.floor(Math.random() * 11 + 70);
            this.enemyPhysicalDef = .25;
            this.enemySorceryDef = .25;
            this.enemyIncantationDef = .2;
        }
    }

    
}
