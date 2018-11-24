package com.fpoly.dong.assignment_duan1.adapter;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.fpoly.dong.assignment_duan1.NewsActivity;
import com.fpoly.dong.assignment_duan1.R;
import com.fpoly.dong.assignment_duan1.model.News;
import com.fpoly.dong.assignment_duan1.webtintucActivity;

import java.util.ArrayList;

public class AdapterTinTuc extends RecyclerView.Adapter<AdapterTinTuc.HolderXML> {
    private NewsActivity activity;
    private ArrayList<News> newss;

    public AdapterTinTuc(NewsActivity activity, ArrayList<News> newss) {
        this.activity = activity;
        this.newss = newss;
    }

    @NonNull
    @Override
    public HolderXML onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.cardview_tintuc,viewGroup,false);
        HolderXML xml = new HolderXML(view);
        return xml;
    }

    @Override
    public void onBindViewHolder(@NonNull HolderXML holderXML, final  int i) {
        final News news =  newss.get(i);
        holderXML.title.setText(news.title);
        holderXML.sumary.setText(news.summary);
        holderXML.title.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activity,webtintucActivity.class) ;
                intent.putExtra("link",newss.get(i).link);
              activity.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return newss.size();
    }

    public class HolderXML extends RecyclerView.ViewHolder {
        private TextView title;
        private TextView sumary;
        public HolderXML(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title);
            sumary = itemView.findViewById(R.id.sumary);

        }
    }
}

