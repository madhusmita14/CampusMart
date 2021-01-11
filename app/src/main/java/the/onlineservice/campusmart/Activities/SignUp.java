package the.onlineservice.campusmart.Activities;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.util.Base64;
import android.util.Patterns;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import the.onlineservice.campusmart.Adapters.SpinnerAdapter;
import the.onlineservice.campusmart.HttpsTrustManager;
import the.onlineservice.campusmart.Models.SpinnerModel;
import the.onlineservice.campusmart.R;

public class SignUp extends AppCompatActivity  {
    TextView uploadRoll_tv,go_login;
    ImageView uploadRoll_img,imageView3_signup;
    EditText username,mobileno,email,collegename,rollno,password;
    AwesomeValidation awesomeValidation;
    ArrayList<SpinnerModel> spinnerList;
    Spinner spinner_StateId;
    String Urls = "https://www.json-generator.com/api/json/get/bGNqEockCW?indent=2"; //"https://rentopool.com/olx/api/college";
    String SubmitUrls = "https://rentopool.com/olx/api/register";
    String spinnerName;
    private static final int PICK_IMAGE = 100;
    Uri imageUri;
    Bitmap bitmap;
    String encode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        spinnerList = new ArrayList<>();

        spinner_StateId = findViewById(R.id.spinner_StateId);

        uploadRoll_tv = findViewById(R.id.uploadRoll_tv);
        go_login = findViewById(R.id.go_login);
        uploadRoll_img = findViewById(R.id.uploadRoll_img);
        imageView3_signup = findViewById(R.id.imageView3_signup);

        username = findViewById(R.id.username);
        mobileno = findViewById(R.id.mobileno);
        email = findViewById(R.id.email);
        collegename = findViewById(R.id.collegename);
        rollno = findViewById(R.id.rollno);
        password = findViewById(R.id.password);

        imageView3_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validate();
                //Toast.makeText(getApplicationContext(),"btn clicked",Toast.LENGTH_SHORT).show();
            }
        });

        go_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),SignIn.class));
            }
        });

        uploadRoll_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("image/*");
                startActivityForResult(intent,1);
            }
        });

        loadSpinner();

        awesomeValidation = new AwesomeValidation(ValidationStyle.BASIC);
        awesomeValidation.addValidation(this,R.id.username,"^[A-Za-zs]{1,}[.]{0,1}[A-Za-zs]{0,}$",R.string.usernameerror);
        awesomeValidation.addValidation(this, R.id.email, Patterns.EMAIL_ADDRESS, R.string.emailerror);
        awesomeValidation.addValidation(this, R.id.mobileno, "^[2-9]{2}[0-9]{8}$", R.string.mobilenoerror);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //Bundle extras=data.getExtras();
        switch (requestCode){
            case 1:
                if (resultCode == RESULT_OK){
                    //bitmap = (Bitmap)extras.get("data");

                    String path = data.getData().getPath();
                    uploadRoll_tv.setText(path);
                    //Toast.makeText(getApplicationContext(),""+path,Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }

//    public String getStringImage(Bitmap bm){
//        ByteArrayOutputStream ba = new ByteArrayOutputStream();
//        bm.compress(Bitmap.CompressFormat.JPEG, 70, ba);
//        byte[] imagebyte = ba.toByteArray();
//        encode = Base64.encodeToString(imagebyte, Base64.DEFAULT);
//        return encode;
//    }

    private void loadSpinner() {
        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
        StringRequest stringRequest = new StringRequest(Request.Method.GET, Urls, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray jsonArray = new JSONArray(response);
                    JSONObject jsonObject = jsonArray.getJSONObject(0);

                    for (int i = 0 ; i< jsonArray.length(); i++){
                        JSONObject o = jsonArray.getJSONObject(i);

                        SpinnerModel user = new SpinnerModel();
                        user.setSpinnerId(o.getString("spinnerId"));
                        user.setSpinnerName(o.getString("spinnerName"));

                        spinnerList.add(user);

                        final SpinnerAdapter adapter = new SpinnerAdapter(SignUp.this, android.R.layout.simple_spinner_dropdown_item,spinnerList);
                        spinner_StateId.setAdapter(adapter);
                        spinner_StateId.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                            @Override
                            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                                String selected=spinner_StateId.getSelectedItem().toString();
                                ((TextView)adapterView.getChildAt(0)).setTextColor(Color.WHITE);

                                SpinnerModel model = (SpinnerModel)adapterView.getSelectedItem();
                                spinnerName = model.getSpinnerName();

                                //Toast.makeText(getApplicationContext(),spinnerName,Toast.LENGTH_SHORT).show();
                            }
                            @Override
                            public void onNothingSelected(AdapterView<?> adapterView) {

                            }
                        });
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                   // btn_personalinfo.setEnabled(false);
                }
//                spinner_StateId.setSelection(-1,true);
            }
        },new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //btn_personalinfo.setEnabled(false);
                Toast.makeText(SignUp.this, "Error", Toast.LENGTH_SHORT).show();
            }
        });
        queue.add(stringRequest);
    }

//    private void submitData(){
//        HttpsTrustManager.allowAllSSL();
//        final Map<String, String> params = new HashMap();
//
//        params.put("name", username.getText().toString());
//        params.put("mobilenumber", mobileno.getText().toString());
//        params.put("email_id", email.getText().toString());
//        params.put("college_name", collegename.getText().toString());
//        params.put("college_id_number", rollno.getText().toString());
//        params.put("profession", spinnerName);
//        params.put("password", password.getText().toString());
//
//        JSONObject parameters = new JSONObject(params);
//
//        JsonObjectRequest jsonRequest = new JsonObjectRequest(Request.Method.POST,SubmitUrls, parameters, new Response.Listener<JSONObject>() {
//            @Override
//            public void onResponse(JSONObject response) {
//                //Toast.makeText(getApplicationContext(),""+response,Toast.LENGTH_SHORT).show();
//                Toast.makeText(SignUp.this, "Registration successful", Toast.LENGTH_LONG).show();
//
//                Intent intent = new Intent(SignUp.this, Selfie.class);
//                intent.putExtra("collegename", collegename.getText().toString());
//                intent.putExtra("password", password.getText().toString());
//                startActivity(intent);
//            }
//        }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                error.printStackTrace();
//            }
//        });
//        Volley.newRequestQueue(SignUp.this).add(jsonRequest);
//    }

    private void validate() {
        //String image = getStringImage(bitmap);
        if(awesomeValidation.validate()){
            //Toast.makeText(this, "Validation Successfull", Toast.LENGTH_LONG).show();
            //submitData();
            Intent i = new Intent(getApplicationContext(),Selfie.class);
            i.putExtra("username",username.getText().toString());
            i.putExtra("mobileno",mobileno.getText().toString());
            i.putExtra("email",email.getText().toString());
            i.putExtra("collegename",collegename.getText().toString());
            i.putExtra("rollno",rollno.getText().toString());
            i.putExtra("spinnerName",spinnerName);
            i.putExtra("password",password.getText().toString());
            //i.putExtra("image",image);
            startActivity(i);
        }
    }
}