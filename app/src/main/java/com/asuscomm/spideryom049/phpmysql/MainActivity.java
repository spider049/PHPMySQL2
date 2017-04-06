package com.asuscomm.spideryom049.phpmysql;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.kosalgeek.asynctask.AsyncResponse;
import com.kosalgeek.asynctask.PostResponseAsyncTask;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity implements AsyncResponse, View.OnClickListener {

    EditText etUsername, etPassword;
    Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etUsername = (EditText) findViewById(R.id.etUsername);
        etPassword = (EditText) findViewById(R.id.etPassword);
        btnLogin = (Button) findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(this);

    }

    @Override
    public void processFinish(String result) {
        // ตรวจสอบข้อมูลจาก login.php echo กลับมาให้

        if(result.equals("pass")){
         Toast.makeText(this, result, Toast.LENGTH_LONG).show();

            Intent in = new Intent(this, SubActivity.class);
            startActivity(in);
        }
        else {
            Toast.makeText(this, result, Toast.LENGTH_LONG).show();
        }

    }

    @Override
    public void onClick(View view) {
        HashMap postData = new HashMap();
        postData.put("mobile", "android");
        postData.put("txtUsername", etUsername.getText().toString());
        postData.put("txtPassword", etPassword.getText().toString());

        PostResponseAsyncTask task = new PostResponseAsyncTask(this, postData);
        task.execute("http://spider049.16mb.com/android/client/login.php");
    }
}
