package com.example.jorge_pc.seccion_04_realm.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.jorge_pc.seccion_04_realm.R;
import com.example.jorge_pc.seccion_04_realm.models.Board;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * Created by JORGE-PC on 22/01/2018.
 */

public class BoardAdapter extends BaseAdapter {

    private Context context;
    private List<Board> list;
    private int layout;

    public BoardAdapter(Context context,List<Board> boards,int layout){
        this.context=context;
        this.list=boards;
        this.layout=layout;

    }



    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int id) {
        return id;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {

        ViewHolder vh;
        if (convertView==null){
            convertView= LayoutInflater.from(context).inflate(layout,null);
            vh=new ViewHolder();
            vh.title=(TextView)convertView.findViewById(R.id.textViewBoardTitle);
            vh.notes=(TextView)convertView.findViewById(R.id.textViewBoardNotes);
            vh.createdAt=(TextView)convertView.findViewById(R.id.textViewBoardDate);
            convertView.setTag(vh);
        }else {
            vh=(ViewHolder)convertView.getTag();

        }

        Board board=list.get(position);
        vh.title.setText(board.getTitle());

        int numberOfNotes = board.getNotes().size();
        String textForNotes=(numberOfNotes==1)?numberOfNotes+"-Note":numberOfNotes+"-Notes";

        DateFormat df= new SimpleDateFormat("dd/MM/yyyy");
        String createdAt= df.format(board.getCreatedAt());
        vh.notes.setText(textForNotes);
        vh.createdAt.setText(createdAt);

        return convertView;
    }

    public class ViewHolder{
        TextView title;
        TextView notes;
        TextView createdAt;
    }

}
