package com.example.finaltaskandroid;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.SearchView;
import android.widget.Toast;

import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

public class MainActivity extends AppCompatActivity {
    private FirebaseFirestore objectFirebaseAuth;
    SearchView searchBar;
    RecyclerView rcv;
    AdapterClass objectAdapter;
    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_main);

        objectFirebaseAuth=FirebaseFirestore.getInstance();
        rcv=findViewById(R.id.RVA);

        addValuestoRV1 ("");
        //--------------------------//












        //---------------------//
        searchBar.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                objectAdapter.stopListening();
                addValuestoRV1(query);
                objectAdapter.startListening();


//                serachIt=query.toLowerCase();
//                adNo=0;
//                resultLoad=0;
//                onStart();
//                if (beta.equals("on")){
//                    Toast.makeText(Item_Detail.this, "Searching Beta Version\nMay Contain Error", Toast.LENGTH_SHORT).show();}
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if (newText.equals("")) {
                    objectAdapter.stopListening();
                    addValuestoRV1("");
                    objectAdapter.startListening();

                }

                return false;
            }
        });
        //----------------------------//




    }

    @Override
    protected void onStart () {
        super.onStart ();
        objectAdapter.startListening();
    }

    @Override
    public void onStop() {
        super.onStop();
        objectAdapter.stopListening();
    }
    private void addValuestoRV1(String q) {
        try {
            Query objectQuery = objectFirebaseAuth.collection("Reports");
            FirestoreRecyclerOptions<ModelClass> options =
                    new FirestoreRecyclerOptions.Builder<ModelClass>().setQuery(objectQuery, ModelClass.class).build();

            objectAdapter = new AdapterClass (options,q,getApplicationContext());
            rcv.setLayoutManager (new LinearLayoutManager (getApplicationContext ()));
            rcv.setAdapter(objectAdapter);
        } catch (Exception ex) {
            Toast.makeText(MainActivity.this, "AddValuesToRV: " + ex.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }



}
