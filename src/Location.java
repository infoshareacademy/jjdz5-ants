public class Location {
    private double[] coordinates;
    private String country;
    private String city;
    private String district;
    private String streetName;
    private String buildingNumber;
    private String apartmentNumber;

    public Location(double[] coordinates, String country, String city, String district) {
        this.coordinates = coordinates;
        this.country = country;
        this.city = city;
        this.district = district;
    }

    public Location(double[] coordinates, String country, String city, String district, String streetName) {
        this.coordinates = coordinates;
        this.country = country;
        this.city = city;
        this.district = district;
        this.streetName = streetName;
    }

    public Location(double[] coordinates, String country, String city, String district, String streetName, String buildingNumber) {
        this.coordinates = coordinates;
        this.country = country;
        this.city = city;
        this.district = district;
        this.streetName = streetName;
        this.buildingNumber = buildingNumber;
    }

    public Location(double[] coordinates, String country, String city, String district, String streetName, String buildingNumber, String apartmentNumber) {
        this.coordinates = coordinates;
        this.country = country;
        this.city = city;
        this.district = district;
        this.streetName = streetName;
        this.buildingNumber = buildingNumber;
        this.apartmentNumber = apartmentNumber;
    }

    public double[] getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(double[] coordinates) {
        this.coordinates = coordinates;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getStreetName() {
        return streetName;
    }

    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

    public String getBuildingNumber() {
        return buildingNumber;
    }

    public void setBuildingNumber(String buildingNumber) {
        this.buildingNumber = buildingNumber;
    }

    public String getApartmentNumber() {
        return apartmentNumber;
    }

    public void setApartmentNumber(String apartmentNumber) {
        this.apartmentNumber = apartmentNumber;
    }
}
