public class MenuText {

//  HEAD's.

    public void mainMenuHead(){
        System.out.println();
        System.out.println("-------------------------");
        System.out.println("| APLIKACJA TURYSTYCZNA |");
        System.out.println("|      MENU GŁÓWNE      |");
        System.out.println("-------------------------");
        System.out.println();
    }

    public void routeMenuHead(){
        System.out.println();
        System.out.println("-------------------------");
        System.out.println("|     WYBÓR TRAS        |");
        System.out.println("-------------------------");
        System.out.println();
    }

    public void placeMenuHead(){
        System.out.println();
        System.out.println("-------------------------");
        System.out.println("| ATRAKCJE  TURYSTYCZNE |");
        System.out.println("-------------------------");
        System.out.println();
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
        return  "------------------------------------------------------------\n" +
                "[1] - Wyświetl listę WSZYSTKICH atrakcji\n" +
                "[2] - Wyświetl wszystkie atrakcje DANEGO TYPU\n" +
                "[3] - Wyszukaj atrakcję (należy podać #ID wybranej atrakcji)\n" +
                "------------------------------------------------------------\n" +
                "[0] - Powrót do menu głównego\n" +
                "------------------------------------------------------------\n\n" +
                "Wybór opcji: ";
    }

    public String getPlaceMenuTypeInfo() {
        return  "------------------------------------------------------------\n" +
                "Dostępne kategorie:\n" + (new TypeSelector().getTypes()) +
                "------------------------------------------------------------\n" +
                "[0] - Powrót do menu atrakcji turystycznych\n" +
                "------------------------------------------------------------\n\n" +
                "Wybór opcji: ";
    }

}
