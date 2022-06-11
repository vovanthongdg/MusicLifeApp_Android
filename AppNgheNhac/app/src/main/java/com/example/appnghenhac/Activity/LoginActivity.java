package com.example.appnghenhac.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.appnghenhac.Other.PreferenceHelper;
import com.example.appnghenhac.Other.SetKieuChu;
import com.example.appnghenhac.R;
import com.example.appnghenhac.Service.APIService;
import com.example.appnghenhac.Service.DataService;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class LoginActivity extends AppCompatActivity {

    private EditText etName, etPass;
    private Button btnLogin;
    private TextView txtForget;
    private PreferenceHelper preferenceHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        preferenceHelper = new PreferenceHelper(this);
        if(preferenceHelper.getIsLogin()){
            Intent intent = new Intent(LoginActivity.this,MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            this.finish();
        }
        init();
        setKieuChu();
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginUser();
            }
        });

    }

    private void init() {
        etName = findViewById(R.id.edtnamelg);
        etPass = findViewById(R.id.edtpasslg);
        btnLogin = findViewById(R.id.btnlogin);
        txtForget = findViewById(R.id.txvforgetPass);
    }

    private void loginUser() {
        final String username = etName.getText().toString().trim();
        final String password = etPass.getText().toString().trim();

        DataService dataService = APIService.getService();
        Call<String> callback = dataService.UserLogin(username,password);
        callback.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                Log.i("Responsestring", response.body().toString());
                //Toast.makeText()
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        Log.i("onSuccess", response.body().toString());

                        String jsonresponse = response.body().toString();
                        parseLoginData(jsonresponse);

                    } else {
                        Log.i("onEmptyResponse", "Returned empty response");//Toast.makeText(getContext(),"Nothing returned",Toast.LENGTH_LONG).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {

            }
        });
    }

    private void parseLoginData(String response) {
        try {
            JSONObject jsonObject = new JSONObject(response);
            if (jsonObject.getString("status").equals("true")) {

                saveInfo(response);

                Toast.makeText(LoginActivity.this, "Đăng nhập thành công!", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(LoginActivity.this,MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                this.finish();
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void saveInfo(String response){

        preferenceHelper.putIsLogin(true);
        try {
            JSONObject jsonObject = new JSONObject(response);
            if (jsonObject.getString("status").equals("true")) {
                JSONArray dataArray = jsonObject.getJSONArray("data");
                for (int i = 0; i < dataArray.length(); i++) {

                    JSONObject dataobj = dataArray.getJSONObject(i);
                    preferenceHelper.putName(dataobj.getString("username"));

                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


    private void setKieuChu(){
        SetKieuChu s= new SetKieuChu();
        s.set(this,(TextView)findViewById(R.id.txvsignup));
        s.set(this,(TextView)findViewById(R.id.txvforgetPass));
        s.set(this,(EditText) findViewById(R.id.edtnamelg));
        s.set(this,(EditText) findViewById(R.id.edtpasslg));
        s.set(this,(Button) findViewById(R.id.btnlogin));
    }

    public void signup(View view) {
        startActivity(new Intent(this,RegisterActivity.class));
    }
}
