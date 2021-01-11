package the.onlineservice.campusmart.Models;

public class recommendedListModel {
    public String txt;
    public String url;
    public String img;

    public recommendedListModel(){}

    public recommendedListModel(String txt, String url, String img) {
        this.txt = txt;
        this.url = url;
        this.img = img;
    }

    public String getTxt() {
        return txt;
    }

    public void setTxt(String txt) {
        this.txt = txt;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }
}
