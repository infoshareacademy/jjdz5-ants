public class TypeSelector {

    public String getTypes() {
        String types = "";
        int index = 1;
        for (PlaceOfInterestType poiType : PlaceOfInterestType.values()){
            types += "[" + index + "] - " + poiType.toString() + "\n";
            index++;
        }
        return types;
    }

    public PlaceOfInterestType typeChoice (int type) {
        switch (type) {
            case 1:
                return PlaceOfInterestType.CASTLE;
            case 2:
                return PlaceOfInterestType.CHURCH;
            case 3:
                return PlaceOfInterestType.MUSEUM;
            case 4:
                return PlaceOfInterestType.PARK;
            case 5:
                return PlaceOfInterestType.MONUMENT;
            case 6:
                return PlaceOfInterestType.WRONG;
        }
        return null;
    }

}
