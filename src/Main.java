import org.json.simple.JSONArray;

public class Main {

    private static Menu menu = new Menu();
    private static MenuText menuText = new MenuText();
    private static PlaceOfInterest place = new PlaceOfInterest();

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
        JSONArray array = new PlacesAccess().getCorrectPlacesArray();

        if (array != null) {
            place.setArray(array);
            menuText.placeMenuHead();

            while (selection != 0) {
                menu.placeMenuOptions();
                selection = menu.getSelection();
                switch (selection) {
                    case 1:
                        selection = -1;
                        System.out.println();
                        place.printAllPlaces();
                        break;
                    case 2:
                        selection = -1;
                        System.out.println();
                        placeMenuTypeOption();
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
    }

    public static void placeMenuTypeOption(){

       int selection = menu.getSelection();
       while(selection != 0){
           menu.placeMenuTypeOption();
           selection = menu.getSelection();
           if(selection == 0) {
               menuText.placeMenuHead();
               break;
           }
           else if (selection > 0 && selection < 7) {
               place.printAllOfType(new TypeSelector().typeChoice(selection));
               menuText.placeMenuHead();
               break;
           }
       }
    }

}
