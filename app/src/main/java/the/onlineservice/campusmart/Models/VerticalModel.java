package the.onlineservice.campusmart.Models;

import java.util.ArrayList;

public class VerticalModel {
    String title;
    ArrayList<HorizontalModel> arrayList;

    public VerticalModel(){}

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public ArrayList<HorizontalModel> getArrayList() {
        return arrayList;
    }

    public void setArrayList(ArrayList<HorizontalModel> arrayList) {
        this.arrayList = arrayList;
    }
}
