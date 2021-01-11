package the.onlineservice.campusmart.Apis;

import com.android.volley.VolleyError;

/**
 * Created by Mithlesh Kumar Sharma on 18,December,2019
 * NTCS Company
 */
public interface CustomeListener {
    void onVollyResponce(String responce);
    void onErrorResponce(VolleyError errorResponce);
}
