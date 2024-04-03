import java.util.Scanner;
import java.util.Random;

public class Area2 {
    private Tile2[][] floor1;
    private Tile2[][] floor2;
    private Tile2[][] floor3;
    private Tile2[][] floor4;
    private Tile2[][] floor5;
    private int playerX;
    private int playerY;
    private int currentFloor;
    private boolean bossDefeated;
    private Random random;
    private boolean interacted;
    private boolean leaveFloor;
    private CharacterCreation character;

    public Area2(CharacterCreation character) {
        initializeFloors();
        playerX = 0; // Initial player position
        playerY = 2; // Initial player position
        currentFloor = 1; // Start at floor 1
        bossDefeated = false; // Boss is not defeated initially
        random = new Random();
        interacted = false;
        leaveFloor = false;
        this.character = character;
    }

    private void initializeFloors() {
        // Initialize floor 1
        floor1 = new Tile2[5][5];
        initializeFloor(floor1);
        // Initialize floor 2
        floor2 = new Tile2[7][3];
        initializeFloor(floor2);
        // Initialize floor 3
        floor3 = new Tile2[7][5];
        initializeFloor(floor3);
        // Initialize floor 4
        floor4 = new Tile2[3][6];
        initializeFloor(floor4);
        // Initialize floor 4
        floor5 = new Tile2[8][7];
        initializeFloor(floor5);
        

        // Set up special tiles
        setupSpecialTiles();
    }

    private void initializeFloor(Tile2[][] floor) {
        for (int i = 0; i < floor.length; i++) {
            for (int j = 0; j < floor[0].length; j++) {
                floor[i][j] = new Tile2(); // Initialize all tiles
            }
        }
        // Mark door tiles as unblocked
        if (floor == floor1) {
            floor[4][2].setDoor(true); // Door tile to floor 2
        } else if (floor == floor2) {
            floor[0][1].setDoor(true); // Door tile to floor 1
            floor[3][2].setDoor(true); // Door tile to floor 3
        } else if (floor == floor3) {
            floor[3][0].setDoor(true); // Door tile to floor 2
            floor[0][2].setDoor(true); // Door tile to floor 4
            floor[3][4].setDoor(true); // Door tile to floor 5
        }  else if(floor == floor4){
            floor[1][0].setDoor(true); // Door tile to floor 3
        }  else if(floor == floor5){
            floor[7][3].setDoor(true); // Door tile to floor 2
        }
    }

    private void setupSpecialTiles() {
        // Floor 1
        floor1[0][2].setFastTravel(true); // Fast travel tile
        floor1[3][1].setSpawn(true); // Spawn tile
        floor1[3][3].setSpawn(true); // Spawn tile
        floor1[4][2].setAscendingDoor(true); // Door tile to floor 2
        // Floor 2
        floor2[0][1].setDescendingDoor(true); // Door tile to floor 1
        floor2[3][2].setAscendingDoor(true); // Door tile to floor 3
        floor2[1][0].setSpawn(true); // Spawn tile
        floor2[3][0].setSpawn(true); // Spawn tile
        floor2[5][0].setSpawn(true); // Spawn tile
        // Floor 3
        floor3[3][0].setDescendingDoor(true); // Door tile to floor 2
        floor3[3][4].setAscendingDoor(true); // Door tile to floor 4
        floor3[0][2].setAscendingDoor(true); // Door tile to floor 5
        floor3[1][2].setSpawn(true); // Spawn tile
        floor3[5][2].setSpawn(true); // Spawn tile
        floor3[0][0].setBlockedTile(true);
        floor3[1][0].setBlockedTile(true);
        floor3[5][0].setBlockedTile(true);
        floor3[6][0].setBlockedTile(true);
        floor3[0][4].setBlockedTile(true);
        floor3[1][4].setBlockedTile(true);
        floor3[5][4].setBlockedTile(true);
        floor3[6][4].setBlockedTile(true);
        
        // Floor 4
        floor4[1][0].setDescendingDoor(true); // Door tile to floor 3
        floor4[0][2].setSpawn(true); // Spawn tile
        floor4[0][4].setSpawn(true); // Spawn tile
        floor4[2][2].setSpawn(true); // Spawn tile
        floor4[2][4].setSpawn(true); // Spawn tile
        //Floor 5
        floor5[0][3].setFastTravel(true); // Fast travel tile
        floor5[7][3].setDescendingDoor(true); // Door tile to floor 4
        floor5[4][3].setBoss(true); //boss tile
        floor5[2][1].setSpawn(true); // Spawn tile
        floor5[2][3].setSpawn(true); // Spawn tile
        floor5[2][5].setSpawn(true); // Spawn tile
        floor5[4][1].setSpawn(true); // Spawn tile
        floor5[4][5].setSpawn(true); // Spawn tile
        floor5[6][1].setSpawn(true); // Spawn tile
        floor5[6][5].setSpawn(true); // Spawn tile
        floor5[0][0].setBlockedTile(true);
        floor5[0][1].setBlockedTile(true);
        floor5[0][5].setBlockedTile(true);
        floor5[0][6].setBlockedTile(true);
    }

    public void play() {
        Scanner scanner = new Scanner(System.in);
        char action;
        do {
            displayArea();
            System.out.println("Enter your move (W/A/S/D to move, E to interact, Q to quit): ");
            action = scanner.next().charAt(0);
            clearScreen();
            interacted = interactWithTile(action); // Check if the player interacted with a special tile
        } while (action != 'Q' && !leaveFloor);
        
    }    

    private void displayArea() {
        Tile2[][] currentFloorTiles = getCurrentFloor();
        for (int i = 0; i < currentFloorTiles.length; i++) {
            for (int j = 0; j < currentFloorTiles[i].length; j++) {
                System.out.print(" __________ ");
            }
            System.out.println();
            for (int j = 0; j < currentFloorTiles[i].length; j++) {
                if (i == playerX && j == playerY) {
                    System.out.print("|    P     |");
                } else if (currentFloorTiles[i][j].isSpawn() && !currentFloorTiles[i][j].isInteracted()) {
                    System.out.print("|    S     |"); // Show "S" only if not interacted
                } else if (currentFloorTiles[i][j].isDoor()) {
                    System.out.print("|    D     |");
                } else if (currentFloorTiles[i][j].isBoss() && !bossDefeated && !currentFloorTiles[i][j].isBossInteracted()) {
                    System.out.print("|    B     |");
                } else if (currentFloorTiles[i][j].isFastTravel()) {
                    System.out.print("|    F     |");
                }else if(currentFloorTiles[i][j].isBlockedTile()){
                    System.out.print("|▓▓▓▓▓▓▓▓▓▓|");
                } else {
                    System.out.print("|          |");
                }
            }
            System.out.println();
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
                checkSpecialTile();
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
            Tile2 currentTile = getCurrentFloor()[playerX][playerY];
            if(currentTile.isBoss() || currentTile.isDoor() || currentTile.isSpawn() || currentTile.isFastTravel())
                System.out.println("You have stepped on a special tile! Press E to interact with it.");
        } else {
            System.out.println("Cannot move there.");
        }
    }

    private boolean isValidMove(int x, int y) {
        Tile2[][] currentFloorTiles = getCurrentFloor();
        if (currentFloorTiles == null || x < 0 || x >= currentFloorTiles.length || y < 0 || y >= currentFloorTiles[0].length || currentFloorTiles[x][y].isBlockedTile()) {
            return false; // Invalid floor or out of bounds
        }
        Tile2 destinationTile = currentFloorTiles[x][y];
        return !destinationTile.isOccupied();
    }

    private void checkSpecialTile() {
        Tile2 currentTile = getCurrentFloor()[playerX][playerY];
        if (currentTile.isBoss()) {
            if (!bossDefeated) {
                System.out.println("You've encountered the boss!");
                // Implement boss battle
                bossDefeated = true; // Mark the boss as defeated
                currentTile.setBossInteracted(true); // Mark the boss tile as interacted
                currentTile.setBoss(false); // Remove the boss tile
            } else {
                System.out.println("Boss already defeated. Tile has no effect.");
            }
        } else if (currentTile.isSpawn()) {
            System.out.println("You've encountered a spawn tile!");
            interactWithSpawnTile();
        } else if (currentTile.isFastTravel()) {
            if (currentFloor == 3 && !bossDefeated) {
                System.out.println("Fast travel tile is locked until the boss is defeated.");
            } else {
                System.out.println("You've encountered a fast travel tile!");
                leaveFloor = true;
                // Implement fast travel tile behavior
            }
        } else if (currentTile.isDoor()) {
            if (currentTile.isAscendingDoor()) {
                ascendFloor();
            } else if (currentTile.isDescendingDoor()) {
                descendFloor();
            }
        }
    }

    private void ascendFloor() {
        if (currentFloor == 1) {
            currentFloor = 2;
            playerX = 0;
            playerY = 1;
        } else if (currentFloor == 2) {
            currentFloor = 3;
            playerX = 3; 
            playerY = 0; 
        }else if (currentFloor == 3){
             if(playerX == 3 && playerY == 4){
                currentFloor = 4;
                playerX = 1;
                playerY = 0;
            }else if(playerX == 0 && playerY == 2){
                currentFloor = 5;
                playerX = 7;
                playerY = 3;
            }
        }
    }
    
    private void descendFloor() {
        if (currentFloor == 2) {
            currentFloor = 1;
            playerX = 4;
            playerY = 2;
        } else if (currentFloor == 3) {
            currentFloor = 2;
            playerX = 3;
            playerY = 2;
        }else if(currentFloor == 4){
            currentFloor = 3;
            playerX = 3;
            playerY = 4;
        }else if(currentFloor == 5){
            currentFloor = 3;
            playerX = 0;
            playerY = 2;

        }
    }

    private void interact() {
        // Interaction with tile handled in checkSpecialTile method
        
    }

    private void interactWithSpawnTile() {
        Tile2 currentTile = getCurrentFloor()[playerX][playerY];
        int chance = random.nextInt(100) + 1;
        int runeMin = 50;
        int runeMax = 150;
        int randomRune = (int)Math.floor(Math.random() * (runeMax - runeMin + 1) + runeMin); //Generates a random number from 50 - 150 for rune tiles
        if (chance <= 75) {
            System.out.println("You've encountered a Monster!");
        } else {
            System.out.println("You've encountered a Rune!");
            System.out.println("You've obtained " + randomRune + " Runes");
            character.setRuneCount(randomRune);
        }
        currentTile.setInteracted(true); // Mark the tile as interacted
        currentTile.disableSpawn(); // Disable spawn functionality
    }
    
    private void quitGame() {
        System.out.println("Quitting game...");
        TitleScreen titleScreen = new TitleScreen();
        titleScreen.showTitleScreen();
        // Implement quitting game, perhaps return to main menu or exit
    }

    private Tile2[][] getCurrentFloor() {
        switch (currentFloor) {
            case 1:
                return floor1;
            case 2:
                return floor2;
            case 3:
                return floor3;
            case 4:
                return floor4;
            case 5:
                return floor5;
            default:
                return null; // Invalid floor
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


}

class Tile2 {
    private boolean occupied;
    private boolean spawn;
    private boolean interacted; // Added flag to track interaction
    private boolean bossInteracted; // Flag to track if the boss tile has been interacted with
    private boolean door;
    private boolean boss;
    private boolean fastTravel;
    private boolean ascendingDoor;
    private boolean descendingDoor;
    private boolean blockedTile;
    

    public Tile2() {
        this.occupied = false;
        this.spawn = false;
        this.interacted = false; // Initialize as not interacted
        this.bossInteracted = false;
        this.door = false;
        this.boss = false;
        this.fastTravel = false;
        this.ascendingDoor = false;
        this.descendingDoor = false;
        this.blockedTile = false;

    }

    public boolean isBlockedTile(){
        return blockedTile;
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

    public boolean isInteracted() {
        return interacted;
    }

    public boolean isBossInteracted() {
        return bossInteracted;
    }

    public void setBlockedTile(boolean blockedTile){
        this.blockedTile = blockedTile;
    }

    public void setBossInteracted(boolean bossInteracted) {
        this.bossInteracted = bossInteracted;
    }

    public void setInteracted(boolean interacted) {
        this.interacted = interacted;
    }

    public void disableSpawn() {
        this.spawn = false; // Disable spawn functionality
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