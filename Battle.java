import java.util.Scanner;
import java.util.Random;

public class Battle {
    private CharacterCreation player;
    private GameLobby weapon;
    private Enemy enemy;
    private boolean playerTurn;
    private int playerMaxHealth;
    private int playerCurrentHealth;
    private Random random = new Random();

    public Battle(CharacterCreation player, Enemy enemy, Weapon selectedWeapon) {
        this.player = player;
        this.enemy = enemy;
        this.selectedWeapon = selectedWeapon; // Add this line
        this.playerTurn = true;
        calculatePlayerMaxHealth();
        this.playerCurrentHealth = playerMaxHealth;
    }

    private void calculatePlayerMaxHealth() {
        this.playerMaxHealth = 100 * (player.getHealth() + weapon.getSelectedWeapon.getHp()) / 2;
    }

    public void start() {
        System.out.println("A battle has begun!");
        while (playerCurrentHealth > 0 && !enemy.isDefeated()) {
            if (playerTurn) {
                playerTurn();
            } else {
                enemyTurn();
            }
            playerTurn = !playerTurn;
        }
        displayBattleResult();
    }

    private void playerTurn() {
        System.out.println("Your turn! Choose an action:");
        System.out.println("[1] ATTACK");
        System.out.println("[2] DODGE");
        System.out.println("Incoming enemy damage: " + calculateIncomingEnemyDamage());

        Scanner scanner = new Scanner(System.in);
        int action = scanner.nextInt();

        switch (action) {
            case 1:
                attack();
                break;
            case 2:
                if (attemptDodge()) {
                    System.out.println("You dodged the attack!");
                } else {
                    System.out.println("Dodge failed!");
                }
                break;
            default:
                System.out.println("Invalid action. Please choose [1] ATTACK or [2] DODGE.");
                playerTurn(); // Recursive call to retry the turn
                break;
        }
    }

    private void attack() {
        system.out.println("Choose an attack type: ");
        system.out.println("[1] PHYSICAL");
        system.out.println("[2] MAGICAL");
        system.out.println("[1] INCANTATION");
    }

    private boolean attemptDodge() {
        int dodgeRate = 20 + (player.getEndurance() + weapon.getSelectedWeapon.getEnd()) / 2;
        return random.nextInt(100) < dodgeRate;
    }

    private int calculateIncomingEnemyDamage() {
        // Incoming enemy damage is calculated here
        // This should take into account the enemy's attack, type, and area index
        return enemy.getAttack();
    }

    private void enemyTurn() {
        // Enemy attack logic goes here
        // If player dodged during their turn, it should be checked here
    }

    private void displayBattleResult() {
        // Calculate runes gained and display the appropriate message
        // This could use the character's setRuneCount method to update rune count
    }

    // Other methods...
}
