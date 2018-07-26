import java.lang.reflect.Array;

public class PlaceOfInterest {
    private Integer id;
    private String type;
    private String name;
    private Rating rating;
    private Location location;
    private float price;

    public PlaceOfInterest(Integer id, String name, String type, Location location, float price) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.location = location;
        this.price = price;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Rating getRating() {
        return rating;
    }

    public void setRating(Rating rating) {
        this.rating = rating;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }
}
