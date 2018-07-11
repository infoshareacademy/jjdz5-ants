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

//    public void userMenuHead(){
//        System.out.println();
//        System.out.println("--------------------------");
//        System.out.println("| USTAWIENIA UŻYTKOWNIKA |");
//        System.out.println("--------------------------");
//        System.out.println();
//    }

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

//    public String getUserMenuInfo(){
//        return  "----------------------------------\n" +
//                "[1] - Ustawienia tras\n" +
//                "[2] - Ustawienia wyszukiwania\n\n" +
//                "[3] - Ustawienia konta\n" +
//                "[4] - Ustawienia aplikacji\n\n" +
//                "[5] - Przywróć ustawienia domyślne\n" +
//                "----------------------------------\n" +
//                "[0] - Powrót do menu głównego\n" +
//                "----------------------------------\n\n" +
//                "Wybór opcji: ";
//    }

}
