package the.onlineservice.campusmart.Activities;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import org.json.JSONObject;
import java.io.ByteArrayOutputStream;
import java.util.HashMap;
import java.util.Map;
import the.onlineservice.campusmart.HttpsTrustManager;
import the.onlineservice.campusmart.R;

public class Selfie extends AppCompatActivity {

    ImageView imageView5;
    Button button;
    ImageButton imageButton3;
    Bitmap bitmap;
    String encode;
    String username,mobileno,email,collegename,rollno,spinnerName,password,imageFetch;
    String SubmitUrls = "https://rentopool.com/olx/api/register";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selfie);

        imageView5 = findViewById(R.id.imageView5);

        button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(i,0);
            }
        });

        imageButton3 = findViewById(R.id.imageButton3);
        imageButton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                submitData();
                //startActivity(new Intent(getApplicationContext(), SignIn.class));
            }
        });

        Intent i = getIntent();
        username = i.getStringExtra("username");
        mobileno = i.getStringExtra("mobileno");
        email = i.getStringExtra("email");
        collegename = i.getStringExtra("collegename");
        rollno = i.getStringExtra("rollno");
        spinnerName = i.getStringExtra("spinnerName");
        password = i.getStringExtra("password");
        //imageFetch = i.getStringExtra("image");

        Toast.makeText(getApplicationContext(),imageFetch,Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==0) {
            Bundle extras=data.getExtras();
            if(extras!=null)
            {
                bitmap = (Bitmap)extras.get("data");
                imageView5.setImageBitmap(bitmap);
            }
        }
        else
        {
            Toast.makeText(getApplicationContext(),"Please choose image",Toast.LENGTH_SHORT).show();
        }
    }

    public String getStringImage(Bitmap bm){
        ByteArrayOutputStream ba = new ByteArrayOutputStream();
        bm.compress(Bitmap.CompressFormat.JPEG, 70, ba);
        byte[] imagebyte = ba.toByteArray();
        encode = Base64.encodeToString(imagebyte, Base64.DEFAULT);
        return encode;
    }

    private void submitData(){
        try {
            HttpsTrustManager.allowAllSSL();
            String image = getStringImage(bitmap);
            final Map<String, String> params = new HashMap();

            params.put("name", username);
            params.put("mobilenumber", "+91"+mobileno);
            params.put("email_id", email);
            params.put("college_name", collegename);
            params.put("college_id_number", rollno);
            params.put("profession", spinnerName);
            params.put("password", password);
            params.put("avatar", "image/jpeg;base64," + image);
            //params.put("college_id_details","image/jpeg;base64,"+imageFetch);

            JSONObject parameters = new JSONObject(params);

            JsonObjectRequest jsonRequest = new JsonObjectRequest(Request.Method.POST, SubmitUrls, parameters, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    //Toast.makeText(getApplicationContext(),""+response,Toast.LENGTH_LONG).show();
                    //Toast.makeText(Selfie.this, "Registration successful", Toast.LENGTH_LONG).show();

                    Log.d("api response",""+response);

                    Intent intent = new Intent(Selfie.this, SignIn.class);
                    //intent.putExtra("collegename", collegename);
                    //intent.putExtra("password", password);
                    startActivity(intent);
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    error.printStackTrace();
                    Toast.makeText(getApplicationContext(), "" + error, Toast.LENGTH_SHORT).show();
                }
            });
            Volley.newRequestQueue(Selfie.this).add(jsonRequest);
        }
        catch (Exception e){
            Toast.makeText(getApplicationContext(),""+e,Toast.LENGTH_SHORT).show();
        }
    }
}