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

    public void routeMenuAddingPoiOptions(){
        selection = -1;
        optionSelector(3,menuText.getRouteMenuAddingPoiInfo());
    }

    public void placeMenuOptions(){
        selection = -1;
        optionSelector(6, menuText.getPlaceMenuInfo());
    }

    public void placeMenuTypeOption(){
        selection = -1;
        optionSelector(6, menuText.getPlaceMenuTypeInfo());
    }

    public void placeMenuRatingOption(){
        selection = -1;
        optionSelector(5, menuText.getPlaceMenuRatingInfo());
    }

// OPTION SELECTOR MECHANICS

    private static void optionSelector(int max, String info){       // Option "0" always goes back / quit.

        boolean correctInput = false;

        while(!correctInput){

            System.out.print(info);
            try{ selection = readInteger(); }
            catch(InputMismatchException e){
                System.out.println("\nWYBRANO NIEPRAWIDŁOWĄ OPCJĘ! Spróbuj ponownie.\n");
                continue;
            }
            if (selection < 0 || selection > max){
                System.out.println("\nWYBRANO NIEPRAWIDŁOWĄ OPCJĘ! Spróbuj ponownie.\n");
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
                System.out.println("\nWYBRANO NIEPRAWIDŁOWĄ OPCJĘ! Spróbuj ponownie.\n");
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
                System.out.println("\nWYBRANO NIEPRAWIDŁOWĄ OPCJĘ! Spróbuj ponownie.\n");
            }
        }
    }

    public int idTyping(IDType idType){
        int idTyped = -1;
        while(idTyped < 0) {
            System.out.print("\nPodaj numer #ID " + idType.toString() + " (liczbę całkowitą dodatnią): ");
            try { idTyped = readInteger(); }
            catch (InputMismatchException e) {
                System.out.println("\nWprowadzono nieprawidłowy format!");
                continue;
            }
            if (idTyped < 0){
                System.out.println("\nProszę wprowadzić liczbę całkowitą DODATNIĄ.");
            }
        }
        return idTyped;
    }

}
