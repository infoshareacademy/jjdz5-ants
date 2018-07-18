import java.util.InputMismatchException;
import java.util.Scanner;

public class Menu {

    private static String readString(){
        return new Scanner(System.in).nextLine();
    }
    private static Integer readInteger(){
        return new Scanner(System.in).nextInt();
    }
    private MenuText menuText = new MenuText();
    private static int selection = -1;

    public int getSelection() {
        return selection;
    }

    public void mainMenuOptions(){
        selection = -1;
        optionSelector(2, menuText.getMainMenuInfo());
    }

    public void routeMenuOptions(){
        selection = -1;
        optionSelector(1, menuText.getRouteMenuInfo());
    }

    public void placeMenuOptions(){
        selection = -1;
        optionSelector(3, menuText.getPlaceMenuInfo());
    }

    public void placeMenuTypeOption(){
        selection = -1;
        optionSelector(6, menuText.getPlaceMenuTypeInfo());
    }

// OPTION SELECTOR MECHANICS

    private static void optionSelector(int max, String info){       // Option "0" always goes back / quit.

        boolean correctInput = false;

        while(!correctInput){

            System.out.print(info);
            try{ selection = readInteger(); }
            catch(InputMismatchException e){
                System.out.println();
                System.out.println("WYBRANO NIEPRAWIDŁOWĄ OPCJĘ! Spróbuj ponownie.");
                System.out.println();
                continue;
            }
            if (selection < 0 || selection > max){
                System.out.println();
                System.out.println("WYBRANO NIEPRAWIDŁOWĄ OPCJĘ! Spróbuj ponownie.");
                System.out.println();
            }
            else { correctInput = true; }
        }
    }

// "ARE YOU SURE" MECHANICS

    private String yesNo;
    private boolean yesNoResult;

    public boolean getYesNoResult() {
        return yesNoResult;
    }

    public void areYouSure(String info){

        boolean correctInput = false;

        while(!correctInput){

            System.out.println(info);
            System.out.println("\"Y\" - Tak      \"N\" - Nie");

            try{ yesNo = readString(); }
            catch(InputMismatchException e){
                System.out.println();
                System.out.println("WYBRANO NIEPRAWIDŁOWĄ OPCJĘ! Spróbuj ponownie.");
                System.out.println();
                continue;
            }

            if (yesNo.equalsIgnoreCase("y")){
                yesNoResult = true;
                correctInput = true;
            }
            else if (yesNo.equalsIgnoreCase("n")){
                yesNoResult = false;
                correctInput = true;
            }
            else {
                System.out.println();
                System.out.println("WYBRANO NIEPRAWIDŁOWĄ OPCJĘ! Spróbuj ponownie.");
                System.out.println();
            }
        }
    }

}
