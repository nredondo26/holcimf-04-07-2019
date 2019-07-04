package nredondo26.com.holcim.proyect.Activities;



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
import nredondo26.com.holcim.proyect.Adapter.Adapter_centrosmedicos;
import nredondo26.com.holcim.proyect.Model.Atributos_centrosmedicos;


public class Centros extends AppCompatActivity {

    private RecyclerView rv;
    public List<Atributos_centrosmedicos> atributosList;
    public Adapter_centrosmedicos adapter;
    private RequestQueue rq;
    int idd;

    static String URL_LICENCIA="http://appholcim.com/centrosmedicos.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_centros);

        idd=getIntent().getExtras().getInt("id");
        rq= Volley.newRequestQueue(Centros.this);

        atributosList = new ArrayList<>();
        rv = findViewById(R.id.recyclerView1);
        LinearLayoutManager lm = new LinearLayoutManager(this);
        rv.setLayoutManager(lm);
        adapter = new Adapter_centrosmedicos(atributosList,this);
        rv.setAdapter(adapter);

        consulta_centrosmedicos(String.valueOf(idd));

    }

    private void consulta_centrosmedicos(final String id){
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
                                String direccion=oneObject.getString("direccion");
                                String latitud=oneObject.getString("latitud");
                                String longitud=oneObject.getString("longitud");

                                Llenar(nombre,direccion,latitud,longitud);

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
                parametros.put("id", id);
                return parametros;
            }
        };
        rq.add(str);
    }

    public void Llenar(String nom,String dir, String lat, String lon){

        Atributos_centrosmedicos centros=new Atributos_centrosmedicos(nom,dir,lat,lon);

        centros.setNombre(nom);
        centros.setDireccion(dir);
        centros.setLatitud(lat);
        centros.setLongitud(lon);

        atributosList.add(centros);
        adapter.notifyDataSetChanged();
    }

    /*public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.muni_bitacora, menu);
        return super.onCreateOptionsMenu(menu);
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_settings:
                Intent intebitacora = new Intent(Centros.this,BitacoraActivity.class);
                startActivity(intebitacora);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }*/

}
