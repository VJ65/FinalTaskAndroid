package com.example.finaltaskandroid;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FirebaseFirestore;

public class UserInfoFromAdmin extends AppCompatActivity {
    public TextView nameTVINFO,emailTVINFO,issueTVINFO;
    private Animation fadeIn,slideDown;
    private Button sent1,back1;
    private EditText emailAdmin,subjectAdmin;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_info_from_admin);

//        nameTVINFO=findViewById (R.id.userInfoStNameTextViewTV);
        nameTVINFO=findViewById (R.id.userInfoStNameTV);
        back1=findViewById (R.id.deleteCollectionNews);
        final FirebaseFirestore db = FirebaseFirestore.getInstance();
        emailTVINFO=findViewById (R.id.userInfoStEmailTV);
        issueTVINFO=findViewById (R.id.userInfoStIssueTV);

back1.setOnClickListener (new View.OnClickListener () {
    @Override
    public void onClick (View v) {
        db.collection("NewsList").document("DC")
                .delete()
                .addOnSuccessListener(new OnSuccessListener<Void> () {
                    @Override
                    public void onSuccess(Void aVoid) {

                        Toast.makeText (UserInfoFromAdmin.this, "News Deleted", Toast.LENGTH_SHORT).show ();
                     //   Log.d(TAG, "DocumentSnapshot successfully deleted!");
                    }
                })
                .addOnFailureListener(new OnFailureListener () {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText (UserInfoFromAdmin.this, "Not Deleted", Toast.LENGTH_SHORT).show ();
                    }
                });

    }
});




        //Reading data from activity
        Intent intent = getIntent();

        String news_titlecheck = intent.getStringExtra("news_title");
        nameTVINFO.setText (news_titlecheck);;

        String news_datecheck = intent.getStringExtra("news_date");
        emailTVINFO.setText (news_datecheck);
        emailAdmin.setText (news_datecheck);

//        random_recycle_name= intent.getStringExtra("recycle");
        String news_description = intent.getStringExtra("news_description");
        String news_detail = intent.getStringExtra ("news_detail");

        issueTVINFO.setText (news_description);


        //Toast.makeText(this, "------------\n"+name+"\n"+issue+"\n"+email+"\n------------", Toast.LENGTH_LONG).show();

    }



}
