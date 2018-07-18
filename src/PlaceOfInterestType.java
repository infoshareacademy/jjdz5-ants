public enum PlaceOfInterestType {

    CASTLE,
    CHURCH,
    MUSEUM,
    PARK,
    MONUMENT,
    WRONG;

    @Override
    public String toString(){
        switch (this){
            case CASTLE:
                return "Zamek";
            case CHURCH:
                return "Kościół";
            case MUSEUM:
                return "Muzeum";
            case PARK:
                return "Park";
            case MONUMENT:
                return "Pomnik";
            case WRONG:
                return "Inny";
        }
        return null;
    }

}
