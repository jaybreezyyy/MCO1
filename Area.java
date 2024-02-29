import java.util.Scanner;
import java.util.Random;

public class Area {
    private Tile[][] floor1;
    private Tile[][] floor2;
    private Tile[][] floor3;
    private int playerX;
    private int playerY;
    private int currentFloor;
    private boolean bossDefeated;
    private Random random;
    private boolean interacted;
    private boolean leaveFloor;

    public Area() {
        initializeFloors();
        playerX = 6; // Initial player position
        playerY = 1; // Initial player position
        currentFloor = 1; // Start at floor 1
        bossDefeated = false; // Boss is not defeated initially
        random = new Random();
        interacted = false;
        leaveFloor = false;
    }

    private void initializeFloors() {
        // Initialize floor 1
        floor1 = new Tile[7][3];
        initializeFloor(floor1);
        // Initialize floor 2
        floor2 = new Tile[7][7];
        initializeFloor(floor2);
        // Initialize floor 3
        floor3 = new Tile[7][5];
        initializeFloor(floor3);
        // Set up special tiles
        setupSpecialTiles();
    }

    private void initializeFloor(Tile[][] floor) {
        for (int i = 0; i < floor.length; i++) {
            for (int j = 0; j < floor[0].length; j++) {
                floor[i][j] = new Tile(); // Initialize all tiles
            }
        }
        // Mark door tiles as unblocked
        if (floor == floor1) {
            floor[0][1].setDoor(true); // Door tile to floor 2
        } else if (floor == floor2) {
            floor[6][3].setDoor(true); // Door tile to floor 1
            floor[0][3].setDoor(true); // Door tile to floor 3
        } else if (floor == floor3) {
            floor[6][2].setDoor(true); // Door tile to floor 2
        }
    }

    private void setupSpecialTiles() {
        // Floor 1
        floor1[6][1].setFastTravel(true); // Fast travel tile
        floor1[1][0].setSpawn(true); // Spawn tile
        floor1[1][2].setSpawn(true); // Spawn tile
        floor1[0][1].setAscendingDoor(true); // Door tile to floor 2
        // Floor 2
        floor2[6][4].setDescendingDoor(true); // Door tile to floor 1
        floor2[1][3].setSpawn(true); // Spawn tile
        floor2[3][0].setSpawn(true); // Spawn tile
        floor2[3][2].setSpawn(true); // Spawn tile
        floor2[3][3].setSpawn(true); // Spawn tile
        floor2[3][4].setSpawn(true); // Spawn tile
        floor2[3][6].setSpawn(true); // Spawn tile
        floor2[5][2].setSpawn(true); // Spawn tile
        floor2[5][4].setSpawn(true); // Spawn tile
        floor2[0][4].setAscendingDoor(true); // Door tile to floor 3
        // Floor 3
        floor3[6][2].setDescendingDoor(true); // Door tile to floor 2
        floor3[3][2].setBoss(true); // Boss tile
        floor3[0][2].setFastTravel(true); // Fast travel tile
    }

    public void play() {
        Scanner scanner = new Scanner(System.in);
        char action;
        do {
            displayArea();
            System.out.println("Enter your move (W/A/S/D to move, E to interact, Q to quit): ");
            action = scanner.next().charAt(0);
            interacted = interactWithTile(action); // Check if the player interacted with a special tile

        } while (action != 'Q' && !leaveFloor);
        scanner.close();
    }    

    private void displayArea() {
        Tile[][] currentFloorTiles = getCurrentFloor();
        for (int i = 0; i < currentFloorTiles.length; i++) {
            // Draw the upper part of each tileW
            for (int j = 0; j < currentFloorTiles[i].length; j++) {
                System.out.print(" __________ ");
            }
            System.out.println();

            // Draw the middle part of each tile
            for (int j = 0; j < currentFloorTiles[i].length; j++) {
                if (i == playerX && j == playerY) {
                    System.out.print("|    P     |");
                } else if (currentFloorTiles[i][j].isSpawn()) {
                    System.out.print("|    S     |"); // Spawn tile
                } else if (currentFloorTiles[i][j].isDoor()) {
                    System.out.print("|    D     |"); // Door tile
                } else if(currentFloorTiles[i][j].isBoss()){
                    System.out.print("|    B     |"); //Boss tile
                } else if(currentFloorTiles[i][j].isFastTravel()){
                    System.out.print("|    F     |");   //Fast Travel
                }
                else {
                    System.out.print("|          |"); // Empty tile
                }
            }
            System.out.println();

            // Draw the lower part of each tile
            for (int j = 0; j < currentFloorTiles[i].length; j++) {
                System.out.print("|__________|");
            }
            System.out.println();
        }
    }

    private boolean interactWithTile(char action) {
        switch (action) {
            case 'W':
                movePlayer(-1, 0);
                break;
            case 'S':
                movePlayer(1, 0);
                break;
            case 'A':
                movePlayer(0, -1);
                break;
            case 'D':
                movePlayer(0, 1);
                break;
            case 'E':
                interact();
                break;
            case 'Q':
                quitGame();
                break;
            default:
                System.out.println("Invalid action.");
        }
        return false; // Player did not interact with a special tile
    }

    private void movePlayer(int dx, int dy) {
        int newX = playerX + dx;
        int newY = playerY + dy;
        if (isValidMove(newX, newY)) {
            playerX = newX;
            playerY = newY;
            System.out.println("Moved to: (" + playerX + ", " + playerY + ")");
            checkSpecialTile();
        } else {
            System.out.println("Cannot move there.");
        }
    }

    private boolean isValidMove(int x, int y) {
        Tile[][] currentFloorTiles = getCurrentFloor();
        if (currentFloorTiles == null || x < 0 || x >= currentFloorTiles.length || y < 0 || y >= currentFloorTiles[0].length) {
            return false; // Invalid floor or out of bounds
        }
        Tile destinationTile = currentFloorTiles[x][y];
        return !destinationTile.isOccupied();
    }

    private void checkSpecialTile() {
        Tile currentTile = getCurrentFloor()[playerX][playerY];
        if (currentTile.isBoss()) {
            if (!bossDefeated) {
                System.out.println("You've encountered the boss!");
                // Implement boss battle
            } else {
                System.out.println("Boss already defeated. Tile has no effect.");
            }
        } else if (currentTile.isSpawn()) {
            System.out.println("You've encountered a spawn tile!");
            interactWithSpawnTile();
        } else if (currentTile.isFastTravel()) {
            System.out.println("You've encountered a fast travel tile!");
            leaveFloor = true;
            // Implement fast travel tile behavior
        } else if (currentTile.isDoor()) {
            System.out.println("You've encountered a door tile!");
            if (currentFloor == 1) {
                currentFloor = 2; // Move to floor 2
                playerX = 6; // Reset player position
                playerY = 3; // Reset player position
            } else if (currentFloor == 2) {
                currentFloor = 3; // Move to floor 3
                playerX = 6; // Reset player position
                playerY = 2; // Reset player position
            }
        }
    }

    private void interact() {
        // Interaction with tile handled in checkSpecialTile method
        
    }

    private void interactWithSpawnTile() {
        // 75% chance to encounter a Monster, and 25% chance to encounter a Rune
        int chance = random.nextInt(100) + 1;
        if (chance <= 75) {
            System.out.println("You've encountered a Monster!");
            // Implement encounter with a Monster
        } else {
            System.out.println("You've encountered a Rune!");
            // Implement encounter with a Rune
        }
    }

    private void quitGame() {
        System.out.println("Quitting game...");
        // Implement quitting game, perhaps return to main menu or exit
    }

    private Tile[][] getCurrentFloor() {
        switch (currentFloor) {
            case 1:
                return floor1;
            case 2:
                return floor2;
            case 3:
                return floor3;
            default:
                return null; // Invalid floor
        }
    }


}

class Tile {
    private boolean occupied;
    private boolean spawn;
    private boolean door;
    private boolean boss;
    private boolean fastTravel;
    private boolean ascendingDoor;
    private boolean descendingDoor;

    public Tile() {
        this.occupied = false;
        this.spawn = false;
        this.door = false;
        this.boss = false;
        this.fastTravel = false;
        this.ascendingDoor = false;
        this.descendingDoor = false;
    }

    public boolean isOccupied() {
        return occupied;
    }

    public void setOccupied(boolean occupied) {
        this.occupied = occupied;
    }

    public boolean isSpawn() {
        return spawn;
    }

    public void setSpawn(boolean spawn) {
        this.spawn = spawn;
    }

    public boolean isDoor() {
        return door;
    }

    public void setDoor(boolean door) {
        this.door = door;
    }

    public boolean isBoss() {
        return boss;
    }

    public void setBoss(boolean boss) {
        this.boss = boss;
    }

    public boolean isFastTravel() {
        return fastTravel;
    }

    public void setFastTravel(boolean fastTravel) {
        this.fastTravel = fastTravel;
    }

    public boolean isAscendingDoor() {
        return ascendingDoor;
    }

    public void setAscendingDoor(boolean ascendingDoor) {
        this.ascendingDoor = ascendingDoor;
    }

    public boolean isDescendingDoor() {
        return descendingDoor;
    }

    public void setDescendingDoor(boolean descendingDoor) {
        this.descendingDoor = descendingDoor;
    }
}
