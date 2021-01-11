package the.onlineservice.campusmart.Models;

public class SpinnerModel {
    private String spinnerId,spinnerName;

    public SpinnerModel(){}

    public SpinnerModel(String spinnerId, String spinnerName) {
        this.spinnerId = spinnerId;
        this.spinnerName = spinnerName;
    }

    public String getSpinnerId() {
        return spinnerId;
    }

    public void setSpinnerId(String spinnerId) {
        this.spinnerId = spinnerId;
    }

    public String getSpinnerName() {
        return spinnerName;
    }

    public void setSpinnerName(String spinnerName) {
        this.spinnerName = spinnerName;
    }
}
