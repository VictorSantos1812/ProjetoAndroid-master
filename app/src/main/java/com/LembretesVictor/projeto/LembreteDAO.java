package com.LembretesVictor.projeto;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;


import java.util.List;

import static android.database.sqlite.SQLiteDatabase.openOrCreateDatabase;


public class LembreteDAO {

    public List<Lembrete> pesquisarLembretes(){
        List<Lembrete> lista = null;
        try{
            //Criando/chamando BD
            SQLiteDatabase BancoDados = openOrCreateDatabase("Projeto",null);
            //Pesquisando email do usuario
            Cursor search = BancoDados.rawQuery("SELECT nome FROM usuarioID", null);
            search.moveToFirst();
            String email = search.getString(0);
            //Recuperando dados
            Cursor cursor = BancoDados.rawQuery("SELECT nome, conteudo, data_Atual, data_Previsao FROM lembrete2 WHERE email_Usuario='"+ email +"'", null);
            cursor.moveToFirst();
            //startActivity(new Intent(MainActivity.this, PrincipalActivity.class));
            while(cursor != null){
                lista.add(new Lembrete(cursor.getString(0),
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getString(3)));
                if(cursor != null) cursor.moveToNext();

            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return lista;
    }
}
