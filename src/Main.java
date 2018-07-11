public class Main {

    private static Menu menu = new Menu();
    private static MenuText menuText = new MenuText();

    public static void main(String[] args) {

        int selection = menu.getSelection();
        menuText.mainMenuHead();

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
                case 0:
                    System.out.println();
                    menu.areYouSure("Czy jesteś pewien, że chcesz opuścić aplikację?");
                    if (!menu.getYesNoResult()) {
                        selection = -1;
                    }
            }
        }
        System.out.println();
        System.out.println("---- KONIEC APLIKACJI ----");
    }

    private static void routeMenu(){

        int selection = menu.getSelection();
        menuText.routeMenuHead();

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
                case 0:
                    selection = 0;
                    menuText.mainMenuHead();
                    break;
            }
        }
    }

    private static void placeMenu(){

        int selection = menu.getSelection();
        menuText.placeMenuHead();

        while (selection !=0) {
            menu.placeMenuOptions();
            selection = menu.getSelection();
            switch (selection) {
                case 1:
                    selection = -1;
                    System.out.println();
                    System.out.println("---| TUTAJ BĘDZIE WYŚWIETLANA LISTA WSZYSTKICH ATRAKCJI TURYSTYCZNYCH |---");
                    System.out.println();
                    break;
                case 2:
                    selection = -1;
                    System.out.println();
                    System.out.println("---| TUTAJ BĘDĄ WYŚWIETLANE WSZYSTKIE ATRAKCJE DANEGO TYPU |---");
                    System.out.println();
                    break;
                case 3:
                    selection = -1;
                    System.out.println();
                    System.out.println("---| TUTAJ BĘDZIE MOŻLIWOŚĆ WYBRANIA ATRAKCJI ZA POMOCĄ #ID |---");
                    System.out.println();
                    break;
                case 0:
                    selection = 0;
                    menuText.mainMenuHead();
                    break;
            }
        }
    }

//    private static void userMenu(){
//
//        int selection = menu.getSelection();
//
//        menuText.userMenuHead();
//
//        while (selection !=0) {
//            menu.userMenuOptions();
//            selection = menu.getSelection();
//            switch (selection) {
//                case 1:
//                    selection = -1;
//                    System.out.println();
//                    System.out.println("---| MENU USTAWIEŃ TRAS |---");
//                    System.out.println();
//                    break;
//                case 2:
//                    selection = -1;
//                    System.out.println();
//                    System.out.println("---| MENU USTAWIEŃ WYSZUKIWANIA |---");
//                    System.out.println();
//                    break;
//                case 3:
//                    selection = -1;
//                    System.out.println();
//                    System.out.println("---| MENU USTAWIEŃ KONTA UŻYTKOWNIKA |---");
//                    System.out.println();
//                    break;
//                case 4:
//                    selection = -1;
//                    System.out.println();
//                    System.out.println("---| MENU USTAWIEŃ APLIKACJI |---");
//                    System.out.println();
//                    break;
//                case 5:
//                    selection = -1;
//                    System.out.println();
//                    menu.areYouSure("Czy napewno chcesz przywrócić ustawienia domyślne?");
//                    if (menu.getYesNoResult() == true) {
//                        System.out.println("Przywrócono ustawienia domyślne.");
//                        break;
//                    }
//                    else{
//                        System.out.println("Nie przywrócono ustawień domyślnych.");
//                        break;
//                    }
//                case 0:
//                    selection = 0;
//                    menuText.mainMenuHead();
//                    break;
//
//            }
//        }
//
//    }

}
