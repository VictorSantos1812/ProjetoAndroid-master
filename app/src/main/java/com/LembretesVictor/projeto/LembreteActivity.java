 package com.LembretesVictor.projeto;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.sql.SQLClientInfoException;

public class LembreteActivity extends AppCompatActivity {

    private EditText Nome;
    private EditText Conteudo;
    private EditText DataAtual;
    private EditText DataPrevista;
    private Button Salvar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lembrete);

        Nome = (EditText) findViewById(R.id.ptNomeDoLembrete);
        Conteudo = (EditText) findViewById(R.id.ptConteudo);
        DataAtual = (EditText) findViewById(R.id.ptDataAtual);
        DataPrevista = (EditText) findViewById(R.id.ptDataPrevista);
        Salvar = (Button) findViewById(R.id.btnSalvarLembrete);


        DataPrevista.addTextChangedListener(MaskEditUtil.mask(DataPrevista, MaskEditUtil.FORMAT_DATE));
        DataAtual.addTextChangedListener(MaskEditUtil.mask(DataAtual, MaskEditUtil.FORMAT_DATE));
    }

    public void Botao(View view){
            try{
                //Criando/chamando BD
                SQLiteDatabase BancoDados = openOrCreateDatabase("Projeto", MODE_PRIVATE, null);
                //Criando tabela
                BancoDados.execSQL("CREATE TABLE IF NOT EXISTS lembrete2(nome VARCHAR(100), conteudo VARCHAR(100), data_Atual VARCHAR(100), data_Previsao Varchar(100), email_Usuario VARCHAR(100))");
                //Pesquisa
                Cursor cursor = BancoDados.rawQuery("SELECT nome FROM usuarioId", null);
                // Cursor search = BancoDados.rawQuery("SELECT nome, conteudo, data_Atual, data_Previsao, email_Usuario FROM lembrete2", null);
                // search.moveToFirst();
                cursor.moveToFirst();
                //Inserindo dados na tabela
                BancoDados.execSQL("INSERT INTO lembrete2(nome, conteudo, data_Atual, data_Previsao, email_Usuario) VALUES('"+Nome.getText()+"','"+Conteudo.getText()+"','"+DataAtual.getText()+"','"+DataPrevista.getText()+"','"+cursor.getString(0)+"')");
                //Recuperando dados
                startActivity(new Intent(LembreteActivity.this, PrincipalActivity.class));

            }catch(Exception e){
                e.printStackTrace();
        }
    }
}