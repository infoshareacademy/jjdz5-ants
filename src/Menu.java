import java.util.InputMismatchException;
import java.util.Scanner;

public class Menu {

    private static String readString(){
        return new Scanner(System.in).nextLine();
    }

    private static Integer readInteger(){
        return new Scanner(System.in).nextInt();
    }

    public static int selection = -1;

    public int getSelection() {
        return selection;
    }

    public void mainMenuOptions(){

        selection = -1;
        String info =   "-------------------------------------\n" +
                        "[1] - Wybór trasy\n" +
                        "[2] - Przegląd atrakcji turystycznych\n" +
                        "[3] - Preferencje użytkownika\n\n" +
                        "[0] - Wyjście\n" +
                        "-------------------------------------\n\n" +
                        "Wybór opcji: ";

        optionSelector(1,3, info);

    }

    public void routeMenuOptions(){

        selection = -1;
        String info =   "------------------------------------------------------\n" +
                        "---------------| PREDEFINIOWANE TRASY |---------------\n" +
                        "[1] - Wyświetl spis predefiniowanych tras\n" +
                        "[2] - Wybierz trasę! (należy podać #ID wybranej trasy)\n" +
                        "------------------------------------------------------\n" +
                        "--------------------| MOJE TRASY |--------------------\n" +
                        "[3] - Wyświetl spis moich tras\n" +
                        "[4] - Wybierz trasę! (należy podać #ID wybranej trasy)\n" +
                        "[5] - Dodaj nową trasę\n" +
                        "------------------------------------------------------\n" +
                        "[0] - Powrót do menu głównego\n" +
                        "------------------------------------------------------\n\n" +
                        "Wybór opcji: ";

        optionSelector(1,5, info);

    }

    public void placeMenuOptions(){

        selection = -1;
        String info =   "------------------------------------------------------------\n" +
                        "[1] - Wyświetl listę atrakcji\n" +
                        "[2] - Wyszukaj atrakcję (należy podać #ID wybranej atrakcji)\n" +
                        "------------------------------------------------------------\n" +
                        "[0] - Powrót do menu głównego\n" +
                        "------------------------------------------------------------\n\n" +
                        "Wybór opcji: ";

        optionSelector(1,2, info);

    }

    public void userMenuOptions(){

        selection = -1;
        String info =   "----------------------------------\n" +
                        "[1] - Ustawienia tras\n" +
                        "[2] - Ustawienia wyszukiwania\n\n" +
                        "[3] - Ustawienia konta\n" +
                        "[4] - Ustawienia aplikacji\n\n" +
                        "[5] - Przywróć ustawienia domyślne\n" +
                        "----------------------------------\n" +
                        "[0] - Powrót do menu głównego\n" +
                        "----------------------------------\n\n" +
                        "Wybór opcji: ";

        optionSelector(1,5, info);

    }

//--------------------------
// OPTION SELECTOR MECHANICS
//--------------------------

    private static void optionSelector(int min, int max, String info){       // Option "0" always goes back / quit.

        boolean correctInput = false;

        while(!correctInput){

            System.out.print(info);
            try{ selection = readInteger(); }
            catch(InputMismatchException e){
                System.out.println("WYBRANO NIEPRAWIDŁOWĄ OPCJĘ! Spróbuj ponownie.");
                continue;
            }

            if (selection == 0){ correctInput = true; }
            else if (selection < min || selection > max){
                System.out.println("WYBRANO NIEPRAWIDŁOWĄ OPCJĘ! Spróbuj ponownie.");
                continue;
            }
            else { correctInput = true; }

        }

    }

//-------------------------
// "ARE YOU SURE" MECHANICS
//-------------------------

    public String yesNo;

    public boolean yesNoResult;

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
                yesNo = "";
                correctInput = true;
            }
            else if (yesNo.equalsIgnoreCase("n")){
                yesNoResult = false;
                yesNo = "";
                correctInput = true;
            }
            else {
                System.out.println();
                System.out.println("WYBRANO NIEPRAWIDŁOWĄ OPCJĘ! Spróbuj ponownie.");
                System.out.println();
                continue;
            }
        }
    }

}
