package nredondo26.com.holcim.proyect.Activities;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import nredondo26.com.holcim.R;

public class PrincipalActivity extends AppCompatActivity {

    ImageButton bello,chia,palmira,florida,nobsa,villavicencio;
    int idr;


    @SuppressLint("ResourceType")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);

        idr=getIntent().getExtras().getInt("idz");

        bello = findViewById(R.id.bello);
        chia = findViewById(R.id.chia);
        palmira = findViewById(R.id.palmira);
        florida = findViewById(R.id.florida);
        nobsa = findViewById(R.id.nobsa);
        villavicencio = findViewById(R.id.villavicencio);

        bello.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PrincipalActivity.this, CarviewActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.putExtra("id",1);
                intent.putExtra("region",1);
                intent.putExtra("idpersona",idr);
                startActivity(intent);
            }
        });

        chia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PrincipalActivity.this, CarviewActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.putExtra("id",2);
                intent.putExtra("region",5);
                intent.putExtra("idpersona",idr);
                startActivity(intent);
            }
        });

        palmira.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PrincipalActivity.this, CarviewActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.putExtra("id",3);
                intent.putExtra("region",6);
                intent.putExtra("idpersona",idr);
                startActivity(intent);
            }
        });

        florida.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PrincipalActivity.this, CarviewActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.putExtra("id",4);
                intent.putExtra("region",2);
                intent.putExtra("idpersona",idr);
                startActivity(intent);
            }
        });

        nobsa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PrincipalActivity.this, CarviewActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.putExtra("id",5);
                intent.putExtra("region",4);
                intent.putExtra("idpersona",idr);
                startActivity(intent);
            }
        });

        villavicencio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PrincipalActivity.this, CarviewActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.putExtra("id",6);
                intent.putExtra("region",3);
                intent.putExtra("idpersona",idr);
                startActivity(intent);
            }
        });




    }
}
