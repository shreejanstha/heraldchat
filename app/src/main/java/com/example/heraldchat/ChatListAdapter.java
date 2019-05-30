package com.example.firedatabase;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class SingerListAdapter extends ArrayAdapter<Singer> {
    private Activity context;
    private List<Singer> singerList;

    public SingerListAdapter(Activity context,List<Singer> singerList){
        super(context,R.layout.item,singerList);
        this.context=context;
        this.singerList=singerList;
    }

    @NonNull
    @Override
    public View getView(int position,  View convertView,  ViewGroup parent) {
        LayoutInflater inflater=context.getLayoutInflater();
        View listItemView = inflater.inflate(R.layout.item,null,true);

        TextView tvName = listItemView.findViewById(R.id.textName);
        TextView tvGenre= listItemView.findViewById(R.id.textGenre);

        Singer singerObj = singerList.get(position);
        tvName.setText(singerObj.getSingerName());
        tvGenre.setText(singerObj.getSingerGenre());

        return listItemView;
    }
}
