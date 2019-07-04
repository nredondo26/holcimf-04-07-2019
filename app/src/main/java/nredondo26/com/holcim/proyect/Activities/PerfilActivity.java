package nredondo26.com.holcim.proyect.Activities;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.error.VolleyError;
import com.android.volley.request.JsonObjectRequest;
import com.android.volley.request.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import org.json.JSONException;
import org.json.JSONObject;
import de.hdodenhof.circleimageview.CircleImageView;
import nredondo26.com.holcim.R;

public class PerfilActivity extends AppCompatActivity {

    ImageButton emegerncias;
    TextView nombre;
    TextView area;
    TextView zona;
    Button bleve;
    Button bquimicos;
    Button btoxicos;
    Button bregular;
    Button bgraves;
    CircleImageView foto;
    RequestQueue requestQueue;
    private  RequestQueue requestQueuee;
    private  RequestQueue requestQueueee;
    int rid;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        rid=getIntent().getExtras().getInt("id");
        requestQueue = Volley.newRequestQueue(getApplicationContext());
        requestQueuee = Volley.newRequestQueue(getApplicationContext());
        requestQueueee = Volley.newRequestQueue(getApplicationContext());

        solicita_permisos();

        if(rid!=0){
            extraer(rid);
        }else{
            Intent inte = new Intent(PerfilActivity.this,LoginActivity.class);
            startActivity(inte);
            finish();
        }
        nombre = findViewById(R.id.te);
        area = findViewById(R.id.tbriga);
        zona = findViewById(R.id.tplanta);

        emegerncias = findViewById(R.id.imageButton);
        bleve = findViewById(R.id.leve);
        bquimicos = findViewById(R.id.quimicas);
        btoxicos= findViewById(R.id.toxicas);
        bregular= findViewById(R.id.regular);
        bgraves= findViewById(R.id.grave);
        foto= findViewById(R.id.foto);


        bleve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ent = new Intent(getApplicationContext(), Leve.class);
                ent.putExtra("id", rid);
                startActivity(ent);
            }
        });
        bquimicos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ent = new Intent(getApplicationContext(), Sustancias_quimicas.class);
                ent.putExtra("id", rid);
                startActivity(ent);
            }
        });
        btoxicos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ent = new Intent(getApplicationContext(), Toxicas.class);
                ent.putExtra("id", rid);
                startActivity(ent);
            }
        });
        bregular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ent = new Intent(getApplicationContext(), Reguladas.class);
                ent.putExtra("id", rid);
                startActivity(ent);
            }
        });
        bgraves.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ent = new Intent(getApplicationContext(), Grave.class);
                ent.putExtra("id", rid);
                startActivity(ent);
            }
        });

        emegerncias.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ent = new Intent(getApplicationContext(), NumerosActivity.class);
                ent.putExtra("id", rid);
                startActivity(ent);

            }
        });
    }

    public String azona(int zona){
        if(zona==1){return "Bello"; }
        if(zona==2){return "Buga"; }
        if(zona==3){return "Cali Sur"; }
        if(zona==4){return "Cemento"; }
        if(zona==5){return "Chia"; }
        if(zona==6){return "Floridablanca"; }
        if(zona==7){return "Nobsa Cemento"; }
        if(zona==8){return "Palmira"; }
        if(zona==9){return "Puente Aranda"; }
        if(zona==10){return "Ricaurte"; }
        if(zona==11){return "Teleport"; }
        if(zona==12){return "Tunja"; }
        if(zona==13){return "Villavicencio"; }else{
            return "";
        }
    }

    public void solicita_permisos(){
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE,Manifest.permission.READ_EXTERNAL_STORAGE,Manifest.permission.READ_CONTACTS,Manifest.permission.CALL_PHONE,Manifest.permission.ACCESS_FINE_LOCATION},100);
    }

    public void  extraer(int id) {

        String HttpUrlc = "http://appholcim.com/extraertodo.php?id="+id;

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, HttpUrlc, null, new Response.Listener<JSONObject>() {
                    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
                    @SuppressLint("SetTextI18n")
                    @Override
                    public void onResponse(JSONObject response) {
                        String rnombres=null;
                        String rapellidos=null;
                        String rzona=null;
                        String rarea=null;
                        String rimagenperfil=null;
                        try {
                           // Log.e("Repuesta", String.valueOf(response));
                            rnombres = response.getString("nombre");
                            rapellidos = response.getString("apellidos");
                            rzona = response.getString("zona");
                            rarea = response.getString("area");
                            rimagenperfil = response.getString("imagenperfil");

                            String zon= azona(Integer.parseInt(rzona));

                            nombre.setText(rnombres+" "+rapellidos);
                            area.setText(rarea);
                            zona.setText(zon);
                            Glide.with(PerfilActivity.this).load("http://appholcim.com/img_perfil/"+rimagenperfil).into(foto);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("RESPUSTA" , "Hubo error volley");
                    }
                });
        requestQueuee.add(jsonObjectRequest);
        requestQueue.getCache().clear();
    }

    public void cerrar() {
        limpiarpreferencias();
        Intent intent = new Intent(PerfilActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }
    public void editarinformacion() {
        Intent intent = new Intent(PerfilActivity.this, EditarActivity.class);
        intent.putExtra("id", rid);
        startActivity(intent);
        finish();
    }

    public void cambiarimagen() {
        Intent intent = new Intent(PerfilActivity.this, Cambiar_imagen_Activity.class);
        intent.putExtra("id", rid);
        startActivity(intent);
    }

    public void cambiazona() {
        Intent intent = new Intent(PerfilActivity.this, MapcolActivity.class);
        intent.putExtra("idz", rid);
        startActivity(intent);
        finish();
    }

   /* public void cambiazona() {
        Intent intent = new Intent(PerfilActivity.this, PrincipalActivity.class);
        intent.putExtra("idz", rid);
        startActivity(intent);
        finish();
    }*/

    public void eliminarcuenta(int id) {
        String HttpUrld = "http://appholcim.com/eliminarcuenta.php?id="+id;
            StringRequest stringRequest = new StringRequest(Request.Method.GET, HttpUrld,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String ServerResponse) {
                            if (ServerResponse.contains("si")) {
                                Toast.makeText(getApplicationContext(), "Cuenta Eliminada", Toast.LENGTH_LONG).show();
                                Intent intent = new Intent(PerfilActivity.this, LoginActivity.class);
                                startActivity(intent);
                                finish();
                            } else {
                                Toast.makeText(PerfilActivity.this, ServerResponse, Toast.LENGTH_LONG).show();
                            }
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError volleyError) {
                            Toast.makeText(getApplicationContext(), "Problemas con la conexión", Toast.LENGTH_LONG).show();
                        }
                    });
        requestQueueee.add(stringRequest);
    }

    private void limpiarpreferencias(){
        SharedPreferences preferences = getSharedPreferences("CREDENCIALES", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor= preferences.edit();
        editor.clear();
        editor.apply();
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_telefonos, menu);
        return super.onCreateOptionsMenu(menu);
    }

    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.action_settings:
                cerrar();
                return true;

            case R.id.action_settings1:
                editarinformacion();
                return true;
            case R.id.action_settings2:
                cambiarimagen();
                return true;
            case R.id.action_settings3:
                eliminarcuenta(rid);
                return true;
            case R.id.action_settings4:
                cambiazona();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}
