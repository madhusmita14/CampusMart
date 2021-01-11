package the.onlineservice.campusmart.Models;

public class NotificationModel {
    private String title,timing,address,rating,year,plot;
    private boolean expanded;

    public boolean isExpanded() {
        return expanded;
    }

    public void setExpanded(boolean expanded) {
        this.expanded = expanded;
    }

    public NotificationModel(String title, String timing, String address, String rating, String year, String plot) {
        this.title = title;
        this.timing = timing;
        this.address = address;
        this.rating = rating;
        this.year = year;
        this.plot = plot;
        this.expanded = false;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTiming() {
        return timing;
    }

    public void setTiming(String timing) {
        this.timing = timing;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getPlot() {
        return plot;
    }

    public void setPlot(String plot) {
        this.plot = plot;
    }

    @Override
    public String toString() {
        return "NotificationModel{" +
                "title='" + title + '\'' +
                ", timing='" + timing + '\'' +
                ", address='" + address + '\'' +
                ", rating='" + rating + '\'' +
                ", year='" + year + '\'' +
                ", plot='" + plot + '\'' +
                ", expanded=" + expanded +
                '}';
    }
}
