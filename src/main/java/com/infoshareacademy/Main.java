package com.infoshareacademy;

public class Main {

    private static Menu menu = new Menu();
    private static MenuText menuText = new MenuText();
    private static PlaceOfInterest place;
    private static TouristRoute route;
    private static Integer selection;

    public static void main(String[] args) {

        if (new ArraysAccess().getCorrectPlacesArray() != null && new ArraysAccess().getCorrectRoutesArray() != null) {
            place = new PlaceOfInterest();
            route = new TouristRoute();
        }
        else {
            System.out.println("\n---- KONIEC APLIKACJI ----");
            System.exit(0);
        }

        mainMenu();

    }

    private static void mainMenu() {

        selection = -1;
        menuText.mainMenuHead();

        while (selection !=0) {
            menu.mainMenuOptions();
            mainMenuSwitch();
        }

        System.out.println("\n---- KONIEC APLIKACJI ----");

    }

    private static void routeMenu(){

        selection = -1;
        menuText.routeMenuHead();

        while (selection !=0) {
            menu.routeMenuOptions();
            routeMenuSwitch();
        }

        mainMenu();

    }

    private static void placeMenu(){

        selection = -1;

        menuText.placeMenuHead();

        while (selection != 0) {
            menu.placeMenuOptions();
            placeMenuSwitch();
        }

        mainMenu();

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

    private static void mainMenuSwitch() {
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

    private static void routeMenuSwitch() {

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
                if (menu.isAdmin()) {
                    menu.areYouSure("\nCzy napewno chcesz dodać nową trasę turystyczną do bazy danych?");
                    if (menu.getYesNoResult()) {
                        new WriteData().writeRoute();
                        route = new TouristRoute();
                        break;
                    }
                } else {
                    break;
                }
            case 0:
                selection = 0;
                break;
        }
    }

    private static void placeMenuSwitch() {

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
                if (menu.isAdmin()) {
                    menu.areYouSure("\nCzy napewno chcesz dodać nową atrakcję turystyczną do bazy danych?");
                    if (menu.getYesNoResult()) {
                        new WriteData().writePlace();
                        place = new PlaceOfInterest();
                        break;
                    }
                } else {
                    break;
                }
            case 0:
                selection = 0;
                break;
        }
    }

}
