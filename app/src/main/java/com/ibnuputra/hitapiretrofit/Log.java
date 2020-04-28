package com.ibnuputra.hitapiretrofit;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.ibnuputra.hitapiretrofit.model.Berita;
import com.ibnuputra.hitapiretrofit.model.LoginCredentials;
import com.ibnuputra.hitapiretrofit.service.ApiClient;
import com.ibnuputra.hitapiretrofit.service.GetService;

import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.io.IOException;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
public class Log extends AppCompatActivity {
    private Button btn;
    private EditText user;
    private EditText pass;
    private TextView alert;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log);
        btn =  (Button)findViewById(R.id.btn_login);
        user = (EditText)findViewById(R.id.et_username);
        pass = (EditText)findViewById(R.id.et_password);
        alert = (TextView)findViewById(R.id.alert);
        btn.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                String username = user.getText().toString();
                android.util.Log.i("USERNAME", "a"+username);
                String password = pass.getText().toString();
                GetService service2 = ApiClient.getRetrofitInstance().create(GetService.class);
                Call<ResponseBody> call2 = service2.validate(username,password);
                android.util.Log.i("DATA", "onCreate:"+call2);
                call2.enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        try {
                            JSONObject json = new JSONObject(response.body().string());
                            String res = json.getString("login");
                            android.util.Log.i("RESPONSE", res);

                            if(res.equals("1")){
                                Intent intent = new Intent(Log.this,MainActivity.class);
                                startActivity(intent);
                            }else{
                                alert.setText("Username/Password salah!");
                                android.util.Log.i("RESPONSE", "Gagal");

                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        Toast.makeText(Log.this, "Gagal Memuat", Toast.LENGTH_SHORT).show();

                    }
                });
            }
        });

    }


}
