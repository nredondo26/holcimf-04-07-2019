package nredondo26.com.holcim.proyect.Activities.Procedimientos.quimica;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import nredondo26.com.holcim.R;
import nredondo26.com.holcim.proyect.Activities.Paciente;

public class quimicocero extends AppCompatActivity {
//gripe

    Button paciente;
    Button procedimiento;
    String cod= "500";
    int id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quimicocero);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        id=getIntent().getExtras().getInt("id");

        paciente= findViewById(R.id.botonpaciente);
        procedimiento= findViewById(R.id.botonprocedimiento);

        paciente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ent = new Intent(getApplicationContext(), Paciente.class);
                ent.putExtra("cod", cod);
                ent.putExtra("id", id);
                startActivity(ent);
            }
        });

        procedimiento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ent = new Intent(getApplicationContext(), quimicocerop.class);
                ent.putExtra("id", id);
                startActivity(ent);
            }
        });
    }
}