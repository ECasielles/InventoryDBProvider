package com.example.usuario.inventorydbprovider.ui.dashboard;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayout;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.usuario.inventorydbprovider.data.prefs.AppPreferencesHelper;
import com.example.usuario.inventorydbprovider.data.db.InventoryApplication;
import com.example.usuario.inventorydbprovider.ui.prefs.AccountSettingsActivity;
import com.example.usuario.inventorydbprovider.ui.prefs.GeneralSettingsActivity;
import com.example.usuario.inventorydbprovider.ui.inventory.InventoryActivity;
import com.example.usuario.inventorydbprovider.R;
import com.example.usuario.inventorydbprovider.ui.dependency.DependencyActivity;
import com.example.usuario.inventorydbprovider.ui.product.ProductActivity;
import com.example.usuario.inventorydbprovider.ui.sector.SectorActivity;

/**
 * Actividad que muestra los distintos inventarios y sus iconos.
 *
 * @author Enrique Casielles Lapeira
 * @version 1.0
 * @see android.app.Activity
 * @see AppCompatActivity
 */
public class DashboardActivity extends AppCompatActivity {

    private GridLayout gdlDashboard;

    int[] images = {
            R.drawable.ic_format_list_bulleted_type,
            R.drawable.ic_archive,
            R.drawable.ic_map_marker_multiple,
            R.drawable.ic_locker_multiple,
            R.drawable.ic_format_indent_increase
    };

    @Override
    protected void onStart() {
        super.onStart();
        showAppPreferences();
    }

    private void showAppPreferences() {
        AppPreferencesHelper appPreferencesHelper =
                ((InventoryApplication) getApplicationContext()).getAppPreferencesHelper();
        appPreferencesHelper.getCurrentUserName("Lolita");
        String message = "Tu usuario de sesión es: " + appPreferencesHelper.getCurrentUserName();
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    //Especificando con LayoutParams: Fila, columna, espaciado y peso.
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard_support);
        gdlDashboard = (GridLayout) findViewById(R.id.android_R_id_content);

        final int INVENTARIO = View.generateViewId();
        final int PRODUCTO = View.generateViewId();
        final int DEPENDENCIAS = View.generateViewId();
        final int SECCION = View.generateViewId();
        final int PREFERENCIAS = View.generateViewId();

        int[] viewIds = {
                INVENTARIO,
                PRODUCTO,
                DEPENDENCIAS,
                SECCION,
                PREFERENCIAS
        };

        //Definir un array de ImageView

        //No se utiliza en Java/Android array de objetos (sí con tipos primitivos)
        //Se utiliza Vector o colecciones
        //ImageView[] imageViews = new ImageView[images.length];

        //No implementamos la clase Vector porque no trabajamos con hilos
        //Y no se requiere sincronización
        //Vector<ImageView> vectorImageViews = new Vector<ImageView>();

        //El ArrayList debe ser parametrizado
        /*ArrayList<ImageView> arrayImageViews = new ArrayList<ImageView>();
        for (int i = 0; i < images.length; i++) {
            arrayImageViews.add(new ImageView(this));
            if (arrayImageViews.get(i) instanceof ImageView)
                ((ImageView) arrayImageViews.get(i)).setImageResource(images[i]);
        }*/

        //Debemos evitar la costumbre de crear un ArrayList siempre
        //GridLayout ya nos permite agregar los elementos vista como colección

        float width = getResources().getDimension(R.dimen.imgDashboardWidth);
        float height = getResources().getDimension(R.dimen.imgDashboardHeight);
        ImageView imageView;
        GridLayout.LayoutParams params;

        //Creamos los elementos de forma dinámica
        for (int i = 0; i < images.length; i++) {
            imageView = new ImageView(this);

            //Asigna Id y recurso al ImageView
            imageView.setId(viewIds[i]);
            imageView.setImageResource(images[i]);

            params = new GridLayout.LayoutParams();
            params.width = (int) width;
            params.height = (int) height;
            params.rowSpec = GridLayout.spec(GridLayout.UNDEFINED, GridLayout.FILL, 1f);
            params.columnSpec = GridLayout.spec(GridLayout.UNDEFINED, GridLayout.FILL, 1f);
            //imageView.setLayoutParams(params);
            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = null;

                    if(v.getId() == INVENTARIO)
                        intent = new Intent(DashboardActivity.this, InventoryActivity.class);
                    if(v.getId() == PRODUCTO)
                        intent = new Intent(DashboardActivity.this, ProductActivity.class);
                    if(v.getId() == DEPENDENCIAS)
                        intent = new Intent(DashboardActivity.this, DependencyActivity.class);
                    if(v.getId() == SECCION)
                        intent = new Intent(DashboardActivity.this, SectorActivity.class);

                    if (intent != null) startActivity(intent);
                }
            });
            gdlDashboard.addView(imageView, params);
        }
    }

    /**
     * Infla el menú en la Activity
     * @param menu
     * @return
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_activity_dashboard, menu);
        return super.onCreateOptionsMenu(menu);
    }

    /**
     * Define las distintas acciones que se realizan por cada acción del menú seleccionada
     * @param item
     * @return
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_account_settings:
                startActivity(new Intent(DashboardActivity.this, AccountSettingsActivity.class));
                break;
            case R.id.action_general_settings:
                startActivity(new Intent(DashboardActivity.this, GeneralSettingsActivity.class));
                break;
        }

        return super.onOptionsItemSelected(item);
    }

}
