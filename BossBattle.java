import java.util.Scanner;

public class BossBattle {
    private CharacterCreation character;
    private String enemyName;
    private int enemyAttack;
    private int enemyHealth;
    private double enemyPhysicalDef;
    private double enemySorceryDef;
    private double enemyIncantationDef;
    private boolean bossDefeated;

    public BossBattle(CharacterCreation character, int bossIndex) {
        this.character = character;
        this.bossDefeated = false;
        initBoss(bossIndex);
    }

    public boolean display() {
        while (true) {
            int weaponHp = 0;
            if (character.getSelectedWeapon() != null) {
                weaponHp = character.getSelectedWeapon().getHp();
            }
            character.setMaxHp(100 * (character.getHealth() + weaponHp) / 2);
            if (character.getCurrentHp() <= 0) {
                System.out.println(character.getName() + " has died!");
                return false;
            }

            if (this.enemyHealth <= 0) {
                System.out.println(character.getName() + " has slain " + this.enemyName + "!");
                this.bossDefeated = true;
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
                    int weaponEnd = 0;
                    if (character.getSelectedWeapon() != null) {
                        weaponEnd = character.getSelectedWeapon().getEnd();
                    }
                    int dodge_rate = 20 + (character.getEndurance() + weaponEnd) / 2;
                    int dodge_success = (int) Math.floor(Math.random() * 100);
                    if (dodge_success <= dodge_rate)
                        hasDodged = true;
                    break;
                default:
                    System.out.println("Input is Invalid");
                    break;
            }
            if (hasDodged)
                System.out.println(character.getName() + " has dodged the attack!");
            else
                enemyTurn();
        }
    }

    public void attackMenu() {
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
        int damage = (int) (((double) (character.getStrength() + character.getSelectedWeapon().getStr())) * (1.0 - this.enemyPhysicalDef));
        this.enemyHealth -= damage;
        return damage;
    }

    public int attackSorcery() {
        int damage = (int) (((double) (character.getIntelligence() + character.getSelectedWeapon().getIntel())) * (1.0 - this.enemySorceryDef));
        this.enemyHealth -= damage;
        return damage;
    }

    public int attackIncantation() {
        int damage = (int) (((double) (character.getFaith() + character.getSelectedWeapon().getFth())) * (1.0 - this.enemyIncantationDef));
        this.enemyHealth -= damage;
        return damage;
    }

    public void enemyTurn() {
        if (character.subtractHp(this.enemyAttack))
            System.out.println(this.enemyName + " dealt " + this.enemyAttack + " damage!");
        else
            character.subtractHp(character.getCurrentHp());
    }

    public boolean isBossDefeated() {
        return bossDefeated;
    }

    private void initBoss(int bossIndex) {
        switch (bossIndex) {
            case 1:
                this.enemyName = "Godrick the Grafted";
                this.enemyAttack = (int) Math.floor(Math.random() * 151 + 150); // Random damage between 150 and 300
                this.enemyHealth = 200;
                this.enemyPhysicalDef = 0.35;
                this.enemySorceryDef = 0.20;
                this.enemyIncantationDef = 0.15;
                break;
            case 2:
                this.enemyName = "Renala, Queen of the Full Moon";
                this.enemyAttack = (int) Math.floor(Math.random() * 101 + 200); // Random damage between 200 and 300
                this.enemyHealth = 400;
                this.enemyPhysicalDef = 0.15;
                this.enemySorceryDef = 0.35;
                this.enemyIncantationDef = 0.25;
                break;
            case 3:
                this.enemyName = "The Elden Beast";
                this.enemyAttack = (int) Math.floor(Math.random() * 251 + 250); // Random damage between 250 and 500
                this.enemyHealth = 800;
                this.enemyPhysicalDef = 0.25;
                this.enemySorceryDef = 0.50;
                this.enemyIncantationDef = 0.40;
                break;
            default:
                System.out.println("Invalid boss index!");
                break;
        }
    }
}
