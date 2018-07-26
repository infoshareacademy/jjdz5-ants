public class MenuText {

//  HEAD's.

    public void mainMenuHead(){
        System.out.println("\n-------------------------");
        System.out.println("| APLIKACJA TURYSTYCZNA |");
        System.out.println("|      MENU GŁÓWNE      |");
        System.out.println("-------------------------\n");
    }

    public void routeMenuHead(){
        System.out.println("\n-------------------------");
        System.out.println("|     WYBÓR TRAS        |");
        System.out.println("-------------------------\n");
    }

    public void placeMenuHead(){
        System.out.println("\n-------------------------");
        System.out.println("| ATRAKCJE  TURYSTYCZNE |");
        System.out.println("-------------------------\n");
    }

//  OPTIONS DESCRIPTION.

    public String getMainMenuInfo(){
        return  "-------------------------------------\n" +
                "[1] - Przegląd tras\n" +
                "[2] - Przegląd atrakcji turystycznych\n" +
                "-------------------------------------\n" +
                "[0] - Wyjście\n" +
                "-------------------------------------\n\n" +
                "Wybór opcji: ";
    }

    public String getRouteMenuInfo(){
        return  "------------------------------------------------------\n" +
                "[1] - Wyświetl spis predefiniowanych tras\n" +
                "------------------------------------------------------\n" +
                "[0] - Powrót do menu głównego\n" +
                "------------------------------------------------------\n\n" +
                "Wybór opcji: ";
    }

    public String getPlaceMenuInfo(){
        return  "----------------------------------------------------------------------\n" +
                "[1] - Wyświetl wszystkie informacje dla KAŻDEJ atrakcji\n" +
                "[2] - Wyświetl wszystkie atrakcje DANEGO TYPU\n\n" +
                "[3] - Wyświetl prostą listę \"#ID - NAZWA\"\n" +
                "[4] - Wyświetl konkretną atrakcję (należy podać #ID wybranej atrakcji)\n" +
                "[5] - Oceń atrakcję (należy podać #ID wybranej atrakcji)\n\n" +
                "[6] - Dodawanie nowej atrakcji turystycznej\n" +
                "      ( OPCJA TYLKO DLA ADMINISTRATORÓW :) )\n" +
                "----------------------------------------------------------------------\n" +
                "[0] - Powrót do menu głównego\n" +
                "----------------------------------------------------------------------\n\n" +
                "Wybór opcji: ";
    }

    public String getPlaceMenuTypeInfo() {
        return  "\n------------------------------------------------------------\n" +
                "Dostępne kategorie:\n" + (new TypeSelector().getTypes()) +
                "------------------------------------------------------------\n" +
                "[0] - Powrót do menu atrakcji turystycznych\n" +
                "------------------------------------------------------------\n\n" +
                "Wybór opcji: ";
    }

    public String getPlaceMenuRatingInfo() {
        return  "Oceń atrakcję! Podaj ocenę w skali od 1 do 5: ";
    }

}
