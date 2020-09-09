package com.example.userauthenticationapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    String name;
    int password;

    EditText login_name_id;
    EditText login_password_id;
    Button login_btn;


    ArrayList<String> passwordList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        login_name_id = (EditText) findViewById(R.id.login_name_id);
        login_password_id = (EditText) findViewById(R.id.login_password_id);

        login_btn = (Button) findViewById(R.id.login_btn);
        login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //name = login_name_id.getText().toString();
                //password = Integer.valueOf(login_password_id.getText().toString());

                get_json();

            }
        });

    }

    public void get_json(){
        String json;
        try{
            InputStream is = getAssets().open("AppJson.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();

            json = new String(buffer, "UTF-8");
            JSONArray jsonArray = new JSONArray(json);

            for(int i = 0; i<jsonArray.length(); i++){

                JSONObject obj = jsonArray.getJSONObject(i);
                if(obj.getString("username").equals("Shuaib")) {
                    passwordList.add(obj.getString("password"));
                }
            }

            Toast.makeText(getApplicationContext(), passwordList.toString(), Toast.LENGTH_SHORT).show();

        }
        catch (IOException e){
            e.printStackTrace();
        }
        catch(JSONException e){
            e.printStackTrace();
        }

    }

    public void loginSuccesful(View view){
        Toast.makeText(MainActivity.this, "Login successful", Toast.LENGTH_SHORT).show();
    }

    public void signupPage(View view){
        Intent intent = new Intent(this, SignUpActivity.class);
        startActivity(intent);
    }
}