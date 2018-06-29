public class Main {

    public static void main(String[] args) {

        Menu menu = new Menu();
        int selection = menu.getSelection();

        mainMenuHead();

        while (selection !=0) {
            menu.mainMenuOptions();
            selection = menu.getSelection();
            switch (selection) {
                case 1:
                    selection = -1;
                    routeMenu();
                    break;
                case 2:
                    selection = -1;
                    placeMenu();
                    break;
                case 3:
                    selection = -1;
                    userMenu();
                    break;
                case 0:
                    System.out.println();
                    menu.areYouSure("Czy jesteś pewien, że chcesz opuścić aplikację?");
                    if (menu.getYesNoResult() == false) {
                        selection = -1;

                    }

            }

        }

        System.out.println();
        System.out.println("---- KONIEC APLIKACJI ----");

    }

    private static void mainMenuHead(){

        System.out.println();
        System.out.println("-------------------------");
        System.out.println("| APLIKACJA TURYSTYCZNA |");
        System.out.println("|      MENU GŁÓWNE      |");
        System.out.println("-------------------------");
        System.out.println();

    }

    private static void routeMenu(){

        System.out.println();
        System.out.println("-------------------------");
        System.out.println("|     WYBÓR TRAS        |");
        System.out.println("-------------------------");
        System.out.println();

        Menu menu = new Menu();
        int selection = menu.getSelection();

        while (selection !=0) {
            menu.routeMenuOptions();
            selection = menu.getSelection();
            switch (selection) {
                case 1:
                    selection = -1;
                    System.out.println();
                    System.out.println("---| TUTAJ BĘDZIE WYŚWIETLANA LISTA PREDEFINIOWANYCH TRAS |---");
                    System.out.println();
                    break;
                case 2:
                    selection = -1;
                    System.out.println();
                    System.out.println("---| TUTAJ BĘDZIE MOŻLIWOŚĆ WYBRANIA PREDEFINIOWANEJ TRASY ZA POMOCĄ #ID |---");
                    System.out.println();
                    break;
                case 3:
                    selection = -1;
                    System.out.println();
                    System.out.println("---| TUTAJ BĘDZIE WYŚWIETLANA LISTA TRAS UŻYTKOWNIKA |---");
                    System.out.println();
                    break;
                case 4:
                    selection = -1;
                    System.out.println();
                    System.out.println("---| TUTAJ BĘDZIE MOŻLIWOŚĆ WYBRANIA TRASY UŻYTKOWNIKA ZA POMOCĄ #ID |---");
                    System.out.println();
                    break;
                case 5:
                    selection = -1;
                    System.out.println();
                    System.out.println("---| TUTAJ BĘDZIE MOŻLIWOŚĆ DODANIA TRASY PRZEZ UŻYTKOWNIKA |---");
                    System.out.println();
                    break;
                case 0:
                    selection = 0;
                    mainMenuHead();
                    break;

            }
        }

    }

    private static void placeMenu(){

        System.out.println();
        System.out.println("-------------------------");
        System.out.println("| ATRAKCJE  TURYSTYCZNE |");
        System.out.println("-------------------------");
        System.out.println();

        Menu menu = new Menu();
        int selection = menu.getSelection();

        while (selection !=0) {
            menu.placeMenuOptions();
            selection = menu.getSelection();
            switch (selection) {
                case 1:
                    selection = -1;
                    System.out.println();
                    System.out.println("---| TUTAJ BĘDZIE WYŚWIETLANA LISTA ATRAKCJI TURYSTYCZNYCH |---");
                    System.out.println();
                    break;
                case 2:
                    selection = -1;
                    System.out.println();
                    System.out.println("---| TUTAJ BĘDZIE MOŻLIWOŚĆ WYBRANIA ATRAKCJI ZA POMOCĄ #ID |---");
                    System.out.println();
                    break;
                case 0:
                    selection = 0;
                    mainMenuHead();
                    break;

            }
        }

    }

    private static void userMenu(){

        System.out.println();
        System.out.println("--------------------------");
        System.out.println("| USTAWIENIA UŻYTKOWNIKA |");
        System.out.println("--------------------------");
        System.out.println();

        Menu menu = new Menu();
        int selection = menu.getSelection();

        while (selection !=0) {
            menu.userMenuOptions();
            selection = menu.getSelection();
            switch (selection) {
                case 1:
                    selection = -1;
                    System.out.println();
                    System.out.println("---| MENU USTAWIEŃ TRAS |---");
                    System.out.println();
                    break;
                case 2:
                    selection = -1;
                    System.out.println();
                    System.out.println("---| MENU USTAWIEŃ WYSZUKIWANIA |---");
                    System.out.println();
                    break;
                case 3:
                    selection = -1;
                    System.out.println();
                    System.out.println("---| MENU USTAWIEŃ KONTA UŻYTKOWNIKA |---");
                    System.out.println();
                    break;
                case 4:
                    selection = -1;
                    System.out.println();
                    System.out.println("---| MENU USTAWIEŃ APLIKACJI |---");
                    System.out.println();
                    break;
                case 5:
                    selection = -1;
                    System.out.println();
                    menu.areYouSure("Czy napewno chcesz przywrócić ustawienia domyślne?");
                    if (menu.getYesNoResult() == true) {
                        System.out.println("Przywrócono ustawienia domyślne.");
                        break;
                    }
                    else{
                        System.out.println("Nie przywrócono ustawień domyślnych.");
                        break;
                    }
                case 0:
                    selection = 0;
                    mainMenuHead();
                    break;

            }
        }

    }

}
