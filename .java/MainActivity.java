package com.example.novigrad30;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import com.example.novigrad30.loading;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.example.novigrad30.loading;
import java.util.ArrayList;
import java.util.List;

//import com.google.android.material.textfield.TextInputLayout;

public class MainActivity extends AppCompatActivity {
    private EditText password ;
    private Button login;
    private Button signup;
    private TextView sNumber;
    EditText userName;
    List<EmployeHelperClass>Crew;
    List<Client>Customer;
    List<ServicesHelperClass> u;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        userName = (EditText) findViewById(R.id.etNom);
        password = (EditText) findViewById(R.id.etPassword);
        login = (Button)findViewById(R.id.btnLogin);
        signup = (Button)findViewById(R.id.btnSignUp);
        sNumber = (TextView)findViewById(R.id.sNumber) ;

        Crew = new ArrayList<>();
        Crew = loading.crew;

        Customer = new ArrayList<>();
        Customer = loading.clients;

        u = new ArrayList<>();
        u = loading.products;
        sNumber.setText(Integer.toString(Crew.size()));

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // comparer les informations pour savoir si il peut visiter le portail administrateur
                String id  = userName.getText().toString();
                String pass = password.getText().toString();

              //  Intent intentA = new Intent(getApplicationContext(),PortailAdministrateur.class);
                // startActivity(intentA);

                if ( id.equals("Admin") && pass.equals("ABC"))
                {
                    Intent intent = new Intent(getApplicationContext(),PortailAdministrateur.class);
                    startActivity(intent);
                }

              for (short i = 0; i < Crew.size() ; i++){
                  if ( id.equals(Crew.get(i).getID()) && pass.equals(Crew.get(i).getPassword()) )
                  {
                      Intent intent = new Intent(getApplicationContext(),PortailSuccursale.class);
                      startActivity(intent);
                  }
              }

                for (short i = 0; i < Customer.size() ; i++){
                    if ( id.equals(Customer.get(i).getNom()) && pass.equals(Crew.get(i).getPassword()) )
                    {
                        Intent intent = new Intent(getApplicationContext(),PortailClient.class);
                        startActivity(intent);
                    }
                }


            }

        });
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),SignUPRedirection.class);
                startActivity(intent);
            }
        });
      //  sNumber.setText(Integer.toString(loading.clients.size()));
    }


    public boolean Verification (String id, String password){
        for (short i = 0; i < Crew.size();i++){
            if ( id.equals(Crew.get(i).getName()))
                return true;
        }
        return false;
    }


}