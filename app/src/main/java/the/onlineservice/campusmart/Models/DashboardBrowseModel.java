package the.onlineservice.campusmart.Models;

public class DashboardBrowseModel {
    private String title,image;
    private String price;
    private String rating;

    public DashboardBrowseModel(){}

    public DashboardBrowseModel(String title, String image, String price, String rating) {
        this.title = title;
        this.image = image;
        this.price = price;
        this.rating = rating;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }
}
