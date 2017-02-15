package br.com.atrixdevelopers.rafael.example.java.sqlite.ui.activity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatDelegate;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;

import br.com.atrixdevelopers.rafael.example.java.sqlite.R;
import br.com.atrixdevelopers.rafael.example.java.sqlite.adapter.ContatoAdapter;
import br.com.atrixdevelopers.rafael.example.java.sqlite.database.sqlite.SQLiteAdapter;
import br.com.atrixdevelopers.rafael.example.java.sqlite.model.Contato;

public class MainActivity extends AppCompatActivity {

    private static final int REQUEST_ADICIONA_CONTATO = 0;
    private static final int REQUEST_EDITA_CONTATO = 1;

    private ContatoAdapter contatoAdapter;
    private SQLiteAdapter sqLiteAdapter;

    static {
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sqLiteAdapter = new SQLiteAdapter(this);

        RecyclerView rcvContato = (RecyclerView) findViewById(R.id.rcv_contato);

        contatoAdapter = new ContatoAdapter(this, sqLiteAdapter.getAllContato());
        contatoAdapter.setContatoActionListener(new ContatoAdapter.ContatoActionListener() {

            @Override
            public void onEditar(Contato contato, int position) {
                Intent intent = new Intent(MainActivity.this, EditarContatoActivity.class);
                intent.putExtra("contato", contato);
                startActivityForResult(intent, REQUEST_EDITA_CONTATO);
            }

            @Override
            public void onDeletar(Contato contato, int position) {
                sqLiteAdapter.deleteContato(contato.getId());
                contatoAdapter.removeContato(position);
            }
        });

        rcvContato.setHasFixedSize(true);
        rcvContato.setLayoutManager(new LinearLayoutManager(this));
        rcvContato.setAdapter(contatoAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main_activity, menu);

        MenuItem itemAdicionar = menu.findItem(R.id.item_adiciona_contato);
        itemAdicionar.getIcon().setColorFilter(Color.WHITE, PorterDuff.Mode.SRC_IN);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if(R.id.item_adiciona_contato == item.getItemId()) {
            startActivityForResult(new Intent(this, NovoContatoActivity.class), REQUEST_ADICIONA_CONTATO);
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == REQUEST_ADICIONA_CONTATO && resultCode == RESULT_OK) {
            if (contatoAdapter != null) {
                contatoAdapter.changeList(sqLiteAdapter.getAllContato());
            }
        }

        if(requestCode == REQUEST_EDITA_CONTATO && resultCode == RESULT_OK) {
            if (contatoAdapter != null) {
                contatoAdapter.changeList(sqLiteAdapter.getAllContato());
            }
        }
    }
}
