package nredondo26.com.holcim.proyect.Activities;

import android.annotation.SuppressLint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import android.widget.Toast;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.error.AuthFailureError;
import com.android.volley.error.VolleyError;
import com.android.volley.request.StringRequest;
import com.android.volley.toolbox.Volley;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import nredondo26.com.holcim.R;
import nredondo26.com.holcim.proyect.Adapter.Adapter_plantas;
import nredondo26.com.holcim.proyect.Model.Atributos_plantas;


@SuppressLint("Registered")
public class ContenedoresActivity extends AppCompatActivity {

    private RecyclerView rv;
    public List<Atributos_plantas> atributosList;
    public Adapter_plantas adapter;
    private RequestQueue rq;
    int region;
    int idpersona;

    static String URL_LICENCIA="http://appholcim.com/inicio.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_centros);

        region=getIntent().getExtras().getInt("region");
        idpersona=getIntent().getExtras().getInt("idpersona");

        rq= Volley.newRequestQueue(ContenedoresActivity.this);

        atributosList = new ArrayList<>();
        rv = findViewById(R.id.recyclerView1);
        LinearLayoutManager lm = new LinearLayoutManager(this);
        rv.setLayoutManager(lm);
        adapter = new Adapter_plantas(atributosList,this);
        rv.setAdapter(adapter);

        consulta_centrosmedicos(region);

    }

    private void consulta_centrosmedicos(final int region){
        StringRequest str = new StringRequest(Request.Method.POST,URL_LICENCIA,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response){
                        Log.e("idquimicos", response);
                        try {
                            JSONArray json = new JSONArray(response);
                            for (int i=0; i < json.length(); i++)
                            {
                                JSONObject oneObject = json.getJSONObject(i);
                                String nombre=oneObject.getString("nombre");
                                String imagen=oneObject.getString("imagen");
                                String id_planta=oneObject.getString("id");
                                Llenar(nombre,imagen,id_planta);

                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error){
                Log.e("TAG",error.toString());
                Toast.makeText(getApplicationContext(),"Error al autenticarse por favor revise su conexión a internet",Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> parametros = new HashMap<>();
                parametros.put("region", String.valueOf(region));
                return parametros;
            }
        };
        rq.add(str);
    }

    public void Llenar(String nom,String img,String idpl){

        Atributos_plantas plantas=new Atributos_plantas(nom,img,idpl);

        plantas.setNombre(nom);
        plantas.setImagen(img);
        plantas.setGetidplanta(idpl);

        atributosList.add(plantas);
        adapter.notifyDataSetChanged();
    }

}