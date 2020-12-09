package com.LembretesVictor.projeto;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegistrationActivity extends AppCompatActivity {

    private EditText userLogin, userPassword, userEmail;
    private Button cadButton;
    private TextView userName;
    private TextView userLoging;
    private FirebaseAuth firebaseAuth;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        setupUIViews();

        userLoging = (TextView) findViewById(R.id.tvUserLogin);

        firebaseAuth = firebaseAuth.getInstance();

        cadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               if(validate()) {
                   //Inserindo o usuario dentro da base de dados
                   String user_email = userEmail.getText().toString().trim();
                   String user_password = userPassword.getText().toString().trim();

                   firebaseAuth.createUserWithEmailAndPassword(user_email, user_password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                       @Override
                       public void onComplete(@NonNull Task<AuthResult> task) {

                           if (task.isSuccessful()) {
                               Toast.makeText(RegistrationActivity.this, "Cadastro realizado!", Toast.LENGTH_SHORT).show();
                               startActivity(new Intent(RegistrationActivity.this, MainActivity.class));
                           } else {
                               Toast.makeText(RegistrationActivity.this, "Cadastro nao realizado!", Toast.LENGTH_SHORT).show();
                           }
                       }

                   });
               }
            }
        });

        userLoging.setOnClickListener (new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(RegistrationActivity.this, MainActivity.class));
            }
        });

    }


    @SuppressLint("WrongViewCast")
    private void setupUIViews(){
        userLogin = (EditText) findViewById(R.id.etLogin);
        userPassword = (EditText) findViewById(R.id.etSenha);
        userEmail = (EditText) findViewById(R.id.etEmail);
        cadButton = (Button) findViewById(R.id.btnRegister);
        userName  = (TextView) findViewById(R.id.tvUserLogin);
    }

    private Boolean validate(){
        Boolean result = false;

        String login = userLogin.getText().toString();
        String senha = userPassword.getText().toString();
        String email = userEmail.getText().toString();

        if(login.isEmpty() && senha.isEmpty() && email.isEmpty()){
            Toast.makeText(this, "Preencha todos os campos!", Toast.LENGTH_SHORT).show();
        }else{
            result = true;
        }
        return result;
    }

}