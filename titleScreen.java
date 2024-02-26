import java.util.Scanner;
public class titleScreen{

    private static int startMenu(){
        int choice;
        Scanner sc = new Scanner(System.in);
        do{
            System.out.println("Welcome to Elden Rogue");
            System.out.println("[1] Start");
            System.out.println("[2] Exit");
            System.out.print("Enter your choice:");
            choice = sc.nextInt(); 

            switch (choice) {
                case 1:
                    charMenu();
                    break;
                case 2:
                System.out.println("You have exited Elden Rogue");
                    break;
                default:
                System.out.println("Invalid Input!!");
                    break;
            }
        } while (choice != 2);
        sc.close();
        return 0;
    }

    private static int jobClassMenu(){
        int choice;
        Scanner sc = new Scanner(System.in);

        do{
            System.out.println("[1] Vagabond");
            System.out.println("[2] Samurai");
            System.out.println("[3] Warrior");
            System.out.println("[4] Hero");
            System.out.println("[5] Astrologer");
            System.out.println("[6] Prophet");
            System.out.println("[7] Back");
            System.out.print("Enter your choice:");

            choice = sc.nextInt();
            switch(choice){
                case 1:
                    break;
                case 2:
                    break;
                case 3:
                    break;
                case 4:
                    break;
                case 5:
                    break;
                case 6:
                    break;
                case 7:
                    break;    
                default:
                    System.out.println("Invalid Input!!");
                    break;
            }
        }while(choice 7);
        sc.close();

        return 0;
    }

    private static int charMenu(){
        int choice;
        Scanner sc = new Scanner(System.in);
        Scanner stringScanner = new Scanner(System.in);
        do {
            System.out.println("[1] Input Name");
            System.out.println("[2] Select Job Class");
            System.out.println("[3] Confirm");
            System.out.println("[4] Back");
            System.out.print("Enter your choice:");
            
            choice = sc.nextInt();

            switch(choice){
                case 1:
                String charName;
                charName = stringScanner.nextLine();
                if(charName.length() > 25) /*Character name should be less than or equal 25 */
                {
                    charName = charName.substring(0, 25);
                    System.out.println("Name: " + charName);
                }
                else if(charName.length() == 0) /* Character name cant be 1 */
                    System.out.println("Invalid character length");
                else
                    System.out.println("Name: " + charName);
                    break;
                case 2:
                    jobClassMenu();
                    break;
                case 3:
                    break;
                case 4:
                    System.out.println("Going back to title screen");
                    break;
                default:
                    System.out.println("Invalid input!");
                    break;
            }

        } while (choice != 4);            
        sc.close();
        stringScanner.close();

        return 0;
    }
    public static void main(String[] args){
        String charName;

        startMenu();

        

    }
}
