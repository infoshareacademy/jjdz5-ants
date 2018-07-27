public class Main {

    private static Menu menu = new Menu();
    private static MenuText menuText = new MenuText();
    private static PlaceOfInterest place;
    private static TouristRoute route = new TouristRoute();

    public static void main(String[] args) {

        if (new ArraysAccess().getCorrectPlacesArray() != null) {
            place = new PlaceOfInterest();
        }
        else {
            System.out.println("\n---- KONIEC APLIKACJI ----");
            System.exit(0);
        }

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
                    menu.areYouSure("\nCzy jesteś pewien, że chcesz opuścić aplikację?");
                    if (!menu.getYesNoResult()) {
                        selection = -1;
                    }
            }
        }
        System.out.println("\n---- KONIEC APLIKACJI ----");
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
                    route.printAllRoutes();
                    break;
                case 2:
                    selection = -1;
                    route.printSimpleList();
                    break;
                case 3:
                    selection = -1;
                    route.printByID(menu.idTyping(IDType.ROUTE),true);
                    break;
                case 4:
                    selection = -1;
                    route.printByID(menu.idTyping(IDType.ROUTE),false);
                    break;
                case 5:
                    selection = -1;
                    menu.areYouSure("\nCzy napewno chcesz dodać nową trasę turystyczną do bazy danych?");
                    if (!menu.getYesNoResult()) { break; }
                    else {
                        new WriteData().writeRoute();
                        route = new TouristRoute();
                        break;
                    }
                case 0:
                    selection = 0;
                    menuText.mainMenuHead();
                    break;
            }
        }
    }

    private static void placeMenu(){

        int selection = -1;

        menuText.placeMenuHead();

        while (selection != 0) {
            menu.placeMenuOptions();
            selection = menu.getSelection();
            switch (selection) {
                case 1:
                    selection = -1;
                    place.printAllPlaces();
                    break;
                case 2:
                    selection = -1;
                    placeMenuTypeOption();
                    break;
                case 3:
                    selection = -1;
                    place.printSimpleList();
                    break;
                case 4:
                    selection = -1;
                    place.printByID(menu.idTyping(IDType.PLACEOFINTEREST));
                    break;
                case 5:
                    selection = -1;
                    placeMenuRatingOption();
                    break;
                case 6:
                    selection = -1;
                    menu.areYouSure("\nCzy napewno chcesz dodać nową atrakcję turystyczną do bazy danych?");
                    if (!menu.getYesNoResult()) { break; }
                    else {
                        new WriteData().writePlace();
                        place = new PlaceOfInterest();
                        break;
                    }
                case 0:
                    selection = 0;
                    menuText.mainMenuHead();
                    break;
            }
        }
    }

    private static void placeMenuTypeOption(){
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

    private static void placeMenuRatingOption(){
        int idSelection = menu.idTyping(IDType.PLACEOFINTEREST);
        try {
            place.printBasicInfo(idSelection);
            menu.areYouSure("\nCzy chcesz ocenić tą atrakcję turystyczną?");
            if (menu.getYesNoResult()) {
                menu.placeMenuRatingOption();
                new AverageRating().addSingleRating(idSelection, menu.getSelection());
                System.out.println("\nDziękujemy za oddanie oceny!");
                place = new PlaceOfInterest();
            }
        }
        catch (IndexOutOfBoundsException e) {
            System.out.println("Brak atrakcji turystycznej z podanym #ID.");
        }

    }

}
