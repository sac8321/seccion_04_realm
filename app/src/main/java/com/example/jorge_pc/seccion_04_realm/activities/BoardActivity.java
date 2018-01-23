package com.example.jorge_pc.seccion_04_realm.activities;

import android.content.DialogInterface;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.jorge_pc.seccion_04_realm.R;
import com.example.jorge_pc.seccion_04_realm.adapters.BoardAdapter;
import com.example.jorge_pc.seccion_04_realm.models.Board;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;

public class BoardActivity extends AppCompatActivity {

    private Realm realm;

    private FloatingActionButton fab;
    private ListView listView;
    private BoardAdapter adapter;
    private RealmResults<Board> boards;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_board);

        //Db Realm
        realm=Realm.getDefaultInstance();
        boards=realm.where(Board.class).findAll();

        adapter=new BoardAdapter(this,boards,R.layout.list_view_board_item);
        listView=(ListView) findViewById(R.id.listViewBoard);
        listView.setAdapter(adapter);
        fab = (FloatingActionButton) findViewById(R.id.fabAddBoard);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showAlertForCreatingBoard("Add New Board","Type a name for your board");
            }
        });

    }

    //**CRUD Actions **/

    private void createNewBoard(String boardName) {
        //Hay que hacer transacciones para usar la BD

        realm.beginTransaction();
        Board board = new Board (boardName);
        realm.copyToRealm(board);
        realm.commitTransaction();
    }

    //**Dialogs**//

    private void showAlertForCreatingBoard(String title, String message){

        AlertDialog.Builder builder= new AlertDialog.Builder(this);

        if(title !=null) builder.setTitle(title);
        if(message!=null) builder.setMessage(message);


        View viewInfalted=LayoutInflater.from(this).inflate(R.layout.dialog_create_board,null);
        builder.setView(viewInfalted);

        final EditText input = (EditText) viewInfalted.findViewById(R.id.EditTextNewBoard);

        builder.setPositiveButton("Add", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
             String boardName= input.getText().toString().trim();
             if (boardName.length()>0)
                 createNewBoard(boardName);
                 else
                 Toast.makeText(getApplicationContext(),"Se requiere un nombre para nueva pizarra",Toast.LENGTH_SHORT).show();
            }
        });

        AlertDialog dialog= builder.create();
        dialog.show();
    }



}
