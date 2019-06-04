package nredondo26.com.holcim.proyect.Activities;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import nredondo26.com.holcim.R;

public class MapcolActivity extends AppCompatActivity {


    Handler handler;
    private ImageView mapa;
    private Animation rotacion;
    int idr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mapcol);

        mapa=findViewById(R.id.mapacol);

        rotacion = AnimationUtils.loadAnimation(MapcolActivity.this, R.anim.animacion);
        mapa.startAnimation(rotacion);
        handler = new Handler();
        handler.postDelayed(new Runnable(){
            @Override
            public void run() {
                Intent intent = new Intent(MapcolActivity.this, PrincipalActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.putExtra("idz", idr);
                startActivity(intent);
                finish();
            }
        }, 2100);


    }
}
