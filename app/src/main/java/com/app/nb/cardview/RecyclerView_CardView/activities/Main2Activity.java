package com.app.nb.cardview.RecyclerView_CardView.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.app.nb.cardview.R;
import com.app.nb.cardview.RecyclerView_CardView.adapter.MiAdaptador;
import com.app.nb.cardview.RecyclerView_CardView.model.Pelicula;

import java.util.ArrayList;
import java.util.List;

public class Main2Activity extends AppCompatActivity {

    private List<Pelicula> peliculas;

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;

    private int contador = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        peliculas = getPeliculas();

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        layoutManager = new LinearLayoutManager(this);
        //layoutManager = new GridLayoutManager(this, 2);
        //  layoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);

        adapter = new MiAdaptador(peliculas, R.layout.recycler_view_item, new MiAdaptador.OnItemClickListener() {
            @Override
            public void onItemClick(Pelicula pelicula, int posicion) {
                Toast.makeText(Main2Activity.this, pelicula.getNombre() + " - " + posicion, Toast.LENGTH_SHORT).show();
                deletePelicula(posicion);
            }
        });
        //recyclerView.setHasFixedSize(true);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.add_nombre:
                addPelicula(0);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private List<Pelicula> getPeliculas() {
        return new ArrayList<Pelicula>() {{
            add(new Pelicula("Deep Blue Sea", R.drawable.deepbluesea));
            add(new Pelicula("Grave Encounters", R.drawable.graveencounters));
            add(new Pelicula("Tales of Hallowen", R.drawable.halloweentales));
            add(new Pelicula("VHS", R.drawable.vhs));
        }};
    }

    private void addPelicula(int posicion) {
        peliculas.add(posicion, new Pelicula("Nueva Pelicula" + (++contador), R.drawable.hassams));
        // notificamos de un elemento insertado a la coleccion
        adapter.notifyItemInserted(posicion);
        // hacemos scroll hacia la posicion donde el nuevo elemento se aloja
        layoutManager.scrollToPosition(posicion);
    }

    private void deletePelicula(int posicion) {
        peliculas.remove(posicion);
        // notificamos de un item borrado en la coleccion
        adapter.notifyItemRemoved(posicion);
    }
}
