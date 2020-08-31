package com.example.gads2020leaderboardapplicationproject;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Submit extends AppCompatActivity {
    private EditText first;
    private EditText last;
    private EditText email;
    private EditText link;
    private Button btn;
    private ImageView imgBack;
    private submitInterface sbInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_submit);
        first = (EditText) findViewById(R.id.editFirstSubmitForm);
        last = (EditText) findViewById(R.id.editLastSubmitForm);
        email = (EditText) findViewById(R.id.editEmailSubmitForm);
        link = (EditText) findViewById(R.id.editLinkSubmitForm);
        imgBack = (ImageView) findViewById(R.id.imgBack);
        btn = (Button) findViewById(R.id.btnSubmitForm);

        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Submit.this, LeaderBoard.class));
            }
        });

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String firstName = first.getText().toString();
                String lastName = last.getText().toString();
                String Email = email.getText().toString();
                String mLink = link.getText().toString();
                if (!TextUtils.isEmpty(firstName) && !TextUtils.isEmpty(lastName) && !TextUtils.isEmpty(Email) && !TextUtils.isEmpty(mLink)){
                    AlertDialog.Builder mBuilder = new AlertDialog.Builder(Submit.this);
                    LayoutInflater layoutInflater = Submit.this.getLayoutInflater();
                    View view = layoutInflater.inflate(R.layout.confirmation, null);
                    ImageView imageViewConfirm = (ImageView) view.findViewById(R.id.imgCancel);
                    Button btn = (Button) view.findViewById(R.id.btnQ);
                    mBuilder.setView(view);
                    AlertDialog alertDialog = mBuilder.create();
                    alertDialog.show();
                    btn.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            try {
                                getResponse(firstName, lastName, Email, mLink);
                                alertDialog.dismiss();
                            }
                            catch (IOException e){
                                e.printStackTrace();
                            }
                        }
                    });
                    imageViewConfirm.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            alertDialog.dismiss();
                        }
                    });
                }
                else if (TextUtils.isEmpty(firstName)){
                    first.setError("Enter first Name");
                }
                else if (TextUtils.isEmpty(lastName)){
                    last.setError("Enter Last Name");
                }
                else if (TextUtils.isEmpty(Email)){
                    email.setError("Enter Email");
                }
                else if (TextUtils.isEmpty(mLink)){
                    link.setError("Enter Link");
                }
            }
        });

    }

    private void getResponse(String firstName, String lastName, String email, String mLink) throws IOException {
        sbInterface = submitRetrofit.getRetrofit().create(submitInterface.class);
        Call<Void> stringCall = sbInterface.postProjectData(firstName, lastName, email, mLink);
        stringCall.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                AlertDialog.Builder builder = new AlertDialog.Builder(Submit.this);
                LayoutInflater layoutInflater = Submit.this.getLayoutInflater();
                View v = layoutInflater.inflate(R.layout.success, null);
                builder.setView(v);
                AlertDialog dialog = builder.create();
                dialog.show();
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                AlertDialog.Builder builder = new AlertDialog.Builder(Submit.this);
                LayoutInflater layoutInflater = Submit.this.getLayoutInflater();
                View v = layoutInflater.inflate(R.layout.failure, null);
                builder.setView(v);
                AlertDialog dialog = builder.create();
                dialog.show();

            }
        });
    }
}


