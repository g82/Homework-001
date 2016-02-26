package com.madwhale.g82.homework_001;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.madwhale.g82.homework_001.server.ServerAPI;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText etEmail, etPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etEmail = (EditText) findViewById(R.id.et_email);
        etPassword = (EditText) findViewById(R.id.et_password);

        findViewById(R.id.btn_login).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_login:
                attemptLogin();
                break;
        }
    }

    private void attemptLogin() {
        String email = etEmail.getText().toString();
        String password = etPassword.getText().toString();

        LoginApiTask loginApiTask = new LoginApiTask();
        loginApiTask.execute(email, password);
    }

    private class LoginApiTask extends AsyncTask<String, Void, Boolean> {

        @Override
        protected Boolean doInBackground(String... params) {

            String email = params[0];
            String password = params[1];

            try {

                URL url = new URL(ServerAPI.LOGIN + "?email=" + email + "&password=" + password);

                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                InputStream in = new BufferedInputStream(urlConnection.getInputStream());
                StringBuilder stringBuilder = new StringBuilder();

                byte[] buffer = new byte[8];
                int i;
                while ( (i = in.read(buffer)) != -1)
                {
                    stringBuilder.append(new String(buffer,0,i));
                }

                urlConnection.disconnect();

                String result = stringBuilder.toString();

                if (result.equals("success")) return true;
                else return false;

            } catch (MalformedURLException e) {
                return false;
            } catch (IOException e) {
                return false;
            }
        }

        @Override
        protected void onPostExecute(Boolean aBoolean) {
            super.onPostExecute(aBoolean);
        }

    }

}
