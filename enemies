import java.util.Random;

public class Enemy {
    // Enum for enemy types
    public enum Type {
        TYPE1, TYPE2, TYPE3
    }

    // Enemy stats
    private Type type;
    private String name;
    private int health;
    private int attack;
    private double physicalDefense;
    private double sorceryDefense;
    private double incantationDefense;
    private boolean isDefeated;

    // Constants for stats ranges based on enemy type
    private static final int[][] HEALTH_RANGES = {{20, 30}, {25, 35}, {70, 80}};
    private static final int[][] ATTACK_RANGES = {{70, 80}, {110, 120}, {120, 130}};
    private static final double[][] DEFENSE_RANGES = {
        {0.20, 0.50, 0.25}, // Physical Defense
        {0.15, 0.15, 0.25}, // Sorcery Defense
        {0.10, 0.20, 0.20}  // Incantation Defense
    };

    // Random number generator
    private static final Random random = new Random();

    // Constructor
    public Enemy(Type type, String name, int areaIndex) {
        this.type = type;
        this.name = name;
        initializeStats(areaIndex);
        this.isDefeated = false;
    }

    // Initialize enemy stats based on type and area index
    private void initializeStats(int areaIndex) {
        int typeIndex = type.ordinal();

        health = randomValue(HEALTH_RANGES[typeIndex]) * areaIndex;
        attack = randomValue(ATTACK_RANGES[typeIndex]) * areaIndex;
        physicalDefense = DEFENSE_RANGES[0][typeIndex];
        sorceryDefense = DEFENSE_RANGES[1][typeIndex];
        incantationDefense = DEFENSE_RANGES[2][typeIndex];
    }

    // Helper method to get a random value within a given range
    private int randomValue(int[] range) {
        return random.nextInt(range[1] - range[0] + 1) + range[0];
    }

    // Methods to interact with the enemy
    public boolean isDefeated() {
        return isDefeated;
    }

    public void defeat() {
        isDefeated = true;
    }

    // Getters
    public Type getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    public int getHealth() {
        return health;
    }

    public int getAttack() {
        return attack;
    }

    public double getPhysicalDefense() {
        return physicalDefense;
    }

    public double getSorceryDefense() {
        return sorceryDefense;
    }

    public double getIncantationDefense() {
        return incantationDefense;
    }

    // Setter for health
    public void setHealth(int health) {
        this.health = health;
        if (this.health <= 0) {
            defeat();
        }
    }

    // This method would be used when an enemy spawns
    public static Enemy spawnEnemy(int areaIndex) {
        // Randomly select the enemy type
        Type type = Type.values()[random.nextInt(Type.values().length)];

        // Enemy names and other characteristics would be retrieved from an appendix or defined elsewhere
        String name = "Enemy"; // Placeholder, real name should come from an appendix

        return new Enemy(type, name, areaIndex);
    }
}

