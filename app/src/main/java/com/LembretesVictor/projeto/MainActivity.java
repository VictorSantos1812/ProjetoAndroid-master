package com.LembretesVictor.projeto;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    private EditText Name;
    private EditText Password;
    private TextView Info;
    private Button Login;
    private int counter = 5;
    private TextView userCadastro;
    private FirebaseAuth firebaseAuth;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Name = (EditText) findViewById(R.id.etName);
        Password = (EditText) findViewById(R.id.etPassword);
        Info = (TextView) findViewById(R.id.tvInfo);
        Login = (Button) findViewById(R.id.btnLogin);
        userCadastro = (TextView) findViewById(R.id.tvCadastro);

        Info.setText("Numero de tentativas restantes: 5");

        firebaseAuth = FirebaseAuth.getInstance();
        progressDialog = new ProgressDialog(this);

        FirebaseUser user = firebaseAuth.getCurrentUser();

        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try{
                    //Criando/chamando BD
                    SQLiteDatabase BancoDados = openOrCreateDatabase("Projeto", MODE_PRIVATE, null);
                    //Fechando Tabela
                    BancoDados.execSQL("DROP TABLE usuarioId");
                    //Criando tabela
                    BancoDados.execSQL("CREATE TABLE IF NOT EXISTS usuarioId(nome VARCHAR(100))");
                    //Inserindo dados na tabela
                    BancoDados.execSQL("INSERT INTO usuarioId(nome) VALUES('"+Name.getText()+"')");
                    //Recuperando dados
                    Cursor cursor = BancoDados.rawQuery("SELECT nome FROM usuarioId", null);
                    cursor.moveToFirst();
                    //startActivity(new Intent(MainActivity.this, PrincipalActivity.class));
                    while(cursor != null){
                        Log.d("Linha--", "nome: "+ cursor.getString(0));
                        if(cursor != null) cursor.moveToNext();
                        validate(Name.getText().toString(), Password.getText().toString());
                    }
                }catch(Exception e){
                    e.printStackTrace();
                }
            }
        });



        userCadastro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, RegistrationActivity.class));
            }
        });
    }

    public void Botao(View view){
        try{
            //Criando/chamando BD
            SQLiteDatabase BancoDados = openOrCreateDatabase("Projeto", MODE_PRIVATE, null);
            //Criando tabela
            BancoDados.execSQL("CREATE TABLE IF NOT EXISTS usuarioId(nome VARCHAR(100), id int(100))");
            //Inserindo dados na tabela
            BancoDados.execSQL("INSERT INTO usuarioId(nome, conteudo, data_Atual, data_Previsao) VALUES('"+Name.getText()+"')");
            //Recuperando dados
            Cursor cursor = BancoDados.rawQuery("SELECT nome FROM usuarioId", null);
            cursor.moveToFirst();
            startActivity(new Intent(MainActivity.this, PrincipalActivity.class));
            while(cursor != null){
                Log.d("Linha--", "nome: "+ cursor.getString(0));
                if(cursor != null) cursor.moveToNext();
                validate(Name.getText().toString(), Password.getText().toString());
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    private void validate(String userName, String userPassword) {

        progressDialog.setMessage("Verificando Login...");
        progressDialog.show();


        firebaseAuth.signInWithEmailAndPassword(userName, userPassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    progressDialog.dismiss();
                    Toast.makeText(MainActivity.this, "Login realizado com sucesso!!", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(MainActivity.this, PrincipalActivity.class));
                }else{
                    Toast.makeText(MainActivity.this, "Falha ao realizar o login!", Toast.LENGTH_SHORT).show();
                    counter--;
                    Info.setText("Tentativas restantes: "+ counter);
                    progressDialog.dismiss();

                    if(counter == 0){
                        Login.setEnabled(false);
                    }
                }
            }
        });


        }
    }
