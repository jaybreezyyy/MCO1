import java.util.Scanner;

public class Area {
    private Tile[][] floor1;
    private Tile[][] floor2;
    private Tile[][] floor3;
    private int playerX;
    private int playerY;
    private int currentFloor;
    private boolean bossDefeated;

    public Area() {
        initializeFloors();
        playerX = 1; // Initial player position
        playerY = 6; // Initial player position
        currentFloor = 1; // Start at floor 1
        bossDefeated = false; // Boss is not defeated initially
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
                floor[i][j] = new Tile(false, false); // Initialize all tiles as unoccupied and not blocked
            }
        }
    }

    private void setupSpecialTiles() {
        // Floor 1
        floor1[6][1].setFastTravel(true); // Fast travel tile
        floor1[1][0].setSpawn(true); // Spawn tile
        floor1[1][2].setSpawn(true); // Spawn tile
        floor1[1][0].setAscendingDoor(true);; // Door tile to floor 2
        // Floor 2
        floor2[3][6].setDescendingDoor(true);; // Door tile to floor 1
        floor2[1][3].setSpawn(true); // Spawn tile
        floor2[1][3].setSpawn(true); //Spawn tile
        floor2[3][0].setSpawn(true); //Spawn tile
        floor2[3][2].setSpawn(true); //Spawn tile
        floor2[3][3].setSpawn(true); //Spawn tile
        floor2[3][4].setSpawn(true); //Spawn tile
        floor2[3][6].setSpawn(true); //Spawn tile
        floor2[5][2].setSpawn(true); //Spawn tile
        floor2[5][4].setSpawn(true); //Spawn tile
        floor2[3][0].setAscendingDoor(true); //Door tile to floor 3
        // Implement other spawn tiles
        // Floor 3
        floor3[6][2].setDescendingDoor(true); // Door tile to floor 2
        floor3[3][2].setBoss(true); // Boss tile
        floor3[0][3].setFastTravel(true); // fast travel tile
    }

    public void play() {
        Scanner scanner = new Scanner(System.in);
        char action;
        do {
            displayArea();
            System.out.println("Enter your move (W/A/S/D to move, E to interact, Q to quit): ");
            action = scanner.next().charAt(0);
            interactWithTile(action);
        } while (action != 'Q');
        scanner.close();
    }

    private void displayArea() {
        Tile[][] currentFloorTiles = getCurrentFloor();
        for (int i = 0; i < currentFloorTiles[0].length; i++) {
            for (int j = 0; j < currentFloorTiles.length; j++) {
                if (i == playerX && j == playerY) {
                    System.out.print(" P ");
                } else if (currentFloorTiles[i][j].isOccupied()) {
                    System.out.print("[ ]");
                } else {
                    System.out.print("   ");
                }
            }
            System.out.println();
        }
    }

    private void interactWithTile(char action) {
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
        if (x < 0 || x >= getCurrentFloor().length || y < 0 || y >= getCurrentFloor()[0].length) {
            return false; // Out of bounds
        }
        return !getCurrentFloor()[x][y].isBlocked();
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
            // Implement spawn tile behavior
        } else if (currentTile.isFastTravel()) {
            System.out.println("You've encountered a fast travel tile!");
            // Implement fast travel tile behavior
        } else if (currentTile.isDoor()) {
            System.out.println("You've encountered a door tile!");
            currentFloor += 1;
            
            // Implement door tile behavior
        }
    }

    private void interact() {
        // Interaction with tile handled in checkSpecialTile method
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

    private void ifDoorTile(){

    }
    

    public static void main(String[] args) {
        Area area = new Area();
        area.play();
    }
}

class Tile {
    private boolean occupied;
    private boolean blocked;
    private boolean spawn;
    private boolean fastTravel;
    private boolean door;
    private boolean boss;

    public Tile(boolean occupied, boolean blocked) {
        this.occupied = occupied;
        this.blocked = blocked;
    }

    public boolean isOccupied() {
        return occupied;
    }

    public void setOccupied(boolean occupied) {
        this.occupied = occupied;
    }

    public boolean isBlocked() {
        return blocked;
    }

    public void setBlocked(boolean blocked) {
        this.blocked = blocked;
    }

    public boolean isSpawn() {
        return spawn;
    }

    public void setSpawn(boolean spawn) {
        this.spawn = spawn;
    }

    public boolean isFastTravel() {
        return fastTravel;
    }

    public void setFastTravel(boolean fastTravel) {
        this.fastTravel = fastTravel;
    }

    public boolean isDoor() {
        return door;
    }
    
    public void setDoor(boolean door) {
        this.door = door;
    }

    public void setAscendingDoor(boolean door){
        this.door = door;
    }

    public void setDescendingDoor(boolean door){
        this.door = door;
    }

    public boolean isBoss() {
        return boss;
    }

    public void setBoss(boolean boss) {
        this.boss = boss;
    }
}
