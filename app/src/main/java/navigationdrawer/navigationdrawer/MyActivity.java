package navigationdrawer.navigationdrawer;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;


public class MyActivity extends Activity implements android.support.v4.widget.DrawerLayout.DrawerListener, AdapterView.OnItemClickListener
{
    private DrawerLayout mDrawerLayout;
    private ListView menu_derecha, menu_izquierda;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);

        // ***** Obtengo los recursos de la actividad *****
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        menu_derecha = (ListView) findViewById(R.id.menu_derecha);
        menu_izquierda = (ListView) findViewById(R.id.menu_izquierda);
        // ************************************************

        // Pongo los elementos del menú en la lista
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getBaseContext(),
                R.layout.drawer_list_item, getResources().getStringArray(R.array.menu_derecha));
        menu_derecha.setAdapter(adapter);
        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(getBaseContext(),
                R.layout.drawer_list_item, getResources().getStringArray(R.array.menu_izquierda));
        menu_izquierda.setAdapter(adapter2);


        mDrawerLayout.setDrawerListener(this);
        menu_derecha.setOnItemClickListener(this);
        menu_izquierda.setOnItemClickListener(this);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.my, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    // ***** Métodos de la interfaz android.support.v4.widget.DrawerLayout.DrawerListener *****

    @Override
    public void onDrawerSlide(View view, float v)
    {
        switch(view.getId())
        {
            case R.id.menu_derecha:
                getActionBar().setTitle("Menú derecho deslizándose");
                break;
            case R.id.menu_izquierda:
                getActionBar().setTitle("Menú izquierdo deslizándose");
                break;
        }
    }

    @Override
    public void onDrawerOpened(View view) {
        getActionBar().setTitle("Menú desplegado");
    }

    @Override
    public void onDrawerClosed(View view) {
        getActionBar().setTitle(R.string.app_name);
    }

    @Override
    public void onDrawerStateChanged(int i) {

    }

    // ****************************************************************************************

    // ***** Métodos de la interfaz AdapterView.OnItemClickListener *****

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long l)
    {
        String[] menuItems = null;
        switch(adapterView.getId())
        {
            case R.id.menu_derecha:
                menuItems = getResources().getStringArray(R.array.menu_derecha);
                break;
            case R.id.menu_izquierda:
                menuItems = getResources().getStringArray(R.array.menu_izquierda);
                break;
        }
        mostrarToastText("Has pulsado " + menuItems[position]);
    }

    // ******************************************************************

    /**
     * Muestra un texto en la pantalla
     * @param texto Texto a mostrar
     */
    public void mostrarToastText(String texto)
    {
        Toast toast = Toast.makeText(this, texto, Toast.LENGTH_LONG);
        toast.show();
    }
}
