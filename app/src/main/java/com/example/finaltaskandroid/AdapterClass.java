package com.example.finaltaskandroid;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;

public class AdapterClass extends FirestoreRecyclerAdapter< com.example.finaltaskandroid.ModelClass,  com.example.finaltaskandroid.AdapterClass.ViewHolder1> {
   String val="";
   Context ctx;

    public AdapterClass(@NonNull FirestoreRecyclerOptions<ModelClass> options, String s,Context ct) {
        super(options);
        val=s;
        ctx=ct;

    }
   @Override
    protected void onBindViewHolder (@NonNull ViewHolder1 viewHolder1, int i, @NonNull final ModelClass modelClass) {

        if (modelClass.getNews_title ().contains(val)){

        viewHolder1.newsTitleV.setText(modelClass.getNews_title ());
        viewHolder1.newsTitleV.setVisibility(View.VISIBLE);
        viewHolder1.newsDateV.setText(modelClass.getNews_date ());
            viewHolder1.newsDateV.setVisibility(View.VISIBLE);
        viewHolder1.newsDescriptionV.setText(modelClass.getNews_description ());
            viewHolder1.newsDescriptionV.setVisibility(View.VISIBLE);
            viewHolder1.line.setVisibility(View.VISIBLE);
        }

        viewHolder1.readmore.setOnClickListener (new View.OnClickListener () {
            @Override
                public void onClick (View v) {

                Intent intent = new Intent(ctx, UserInfoFromAdmin.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("name", modelClass.getNews_title ());
                intent.putExtra("email", modelClass.getNews_date ());
                intent.putExtra("issue", modelClass.getNews_description ());
                intent.putExtra ("description",modelClass.getNews_description ());
                ctx.startActivity(intent);
        


            }
        });



    }

    @NonNull
    @Override
    public com.example.finaltaskandroid.AdapterClass.ViewHolder1 onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View singleRow1 = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.single_row_news, parent, false
               );
        return new ViewHolder1(singleRow1,"");
    }

    class ViewHolder1 extends RecyclerView.ViewHolder {


        TextView newsTitleV;
        TextView newsDateV;
        Button readmore;
        TextView newsDescriptionV;
        TextView line;
        RelativeLayout relativeLayout;


        public ViewHolder1(@NonNull View itemView,String v) {
            super(itemView);

            newsTitleV = itemView.findViewById(R.id.news_title);
            newsDateV = itemView.findViewById(R.id.news_date);
            newsDescriptionV = itemView.findViewById(R.id.news_description);
            readmore=itemView.findViewById (R.id.readmoreBtnn);
            line=itemView.findViewById(R.id.line);
            relativeLayout=itemView.findViewById(R.id.reeee);

        }

    }

}
