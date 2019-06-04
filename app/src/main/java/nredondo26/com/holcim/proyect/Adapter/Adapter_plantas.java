package nredondo26.com.holcim.proyect.Adapter;


import android.annotation.SuppressLint;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.error.VolleyError;
import com.android.volley.request.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;

import java.util.List;

import nredondo26.com.holcim.R;
import nredondo26.com.holcim.proyect.Activities.MainActivity;
import nredondo26.com.holcim.proyect.Activities.PerfilActivity;
import nredondo26.com.holcim.proyect.Model.Atributos_plantas;


public class Adapter_plantas extends RecyclerView.Adapter<Adapter_plantas.ViewHolder> {

    private List<Atributos_plantas> atributosList;
    private Context context;

    public Adapter_plantas(List<Atributos_plantas> atributosList, Context context) {
        this.atributosList = atributosList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View vista = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_plantas, viewGroup, false);
        return new ViewHolder(vista);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, final int i) {

        final String id_planta=atributosList.get(i).getGetidplanta();

        viewHolder.txt_nombre.setText(atributosList.get(i).getNombre());
        Glide.with(context).load("http://appholcim.com/imagplantas/"+atributosList.get(i).getImagen()).into(viewHolder.txt_imagen);

        SharedPreferences preferences = context.getSharedPreferences("CREDENCIALES", Context.MODE_PRIVATE);
        final int idp= preferences.getInt("id", 0);


        viewHolder.carV_plantas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("id de la planto",id_planta);

                Actializar_zona(idp, id_planta);
            }
        });
    }

    public void  Actializar_zona(final int idusuario, String idzona) {

        String HttpUrl = "http://appholcim.com/actualizarzona.php?id="+idusuario+"&zona="+idzona;
        StringRequest stringRequest = new StringRequest(Request.Method.GET, HttpUrl,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String ServerResponse) {
                        if (ServerResponse.contains("si")) {
                            Toast.makeText(context, "Actualizado", Toast.LENGTH_LONG).show();
                            Intent intent = new Intent(context, PerfilActivity.class);
                            intent.putExtra("id", idusuario);
                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                            context.startActivity(intent);

                        } else {
                            Toast.makeText(context, ServerResponse, Toast.LENGTH_LONG).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        Toast.makeText(context, "Problemas con la conexión", Toast.LENGTH_LONG).show();
                    }
                });
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        requestQueue.getCache().clear();
        requestQueue.add(stringRequest);

    }

    @Override
    public int getItemCount() {
        return atributosList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView txt_nombre;
        ImageView txt_imagen;
        ConstraintLayout carV_plantas;

        ViewHolder(View item){
            super(item);
            txt_nombre = item.findViewById(R.id.nombrecm);
            txt_imagen = item.findViewById(R.id.imagencm);
            carV_plantas = item.findViewById(R.id.carV_plantas);
        }
    }




}
