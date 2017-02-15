package br.com.atrixdevelopers.rafael.example.java.sqlite.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AppCompatDelegate;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import br.com.atrixdevelopers.rafael.example.java.sqlite.R;
import br.com.atrixdevelopers.rafael.example.java.sqlite.database.sqlite.SQLiteAdapter;
import br.com.atrixdevelopers.rafael.example.java.sqlite.model.Contato;

/**
 * Atrix Developers
 *
 * @author Rafael de Azeredo
 */
public class EditarContatoActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView txtAuthor;
    private TextInputEditText edtNome;
    private TextInputEditText edtIdade;
    private TextInputLayout txiNome;
    private TextInputLayout txiIdade;
    private FloatingActionButton fabCadastrar;

    private Contato contato;

    static {
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_contato);

        contato = getIntent().getParcelableExtra("contato");

        edtNome = (TextInputEditText) findViewById(R.id.edt_nome);
        txiNome = (TextInputLayout) findViewById(R.id.txi_nome);
        edtIdade = (TextInputEditText) findViewById(R.id.edt_idade);
        txiIdade = (TextInputLayout) findViewById(R.id.txi_idade);
        txtAuthor = (TextView) findViewById(R.id.txt_author);
        fabCadastrar = (FloatingActionButton) findViewById(R.id.fab_cadastrar);

        edtNome.setText(contato.getNome());
        edtIdade.setText(String.valueOf(contato.getIdade()));

        fabCadastrar.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        if(R.id.fab_cadastrar == v.getId()) {
            if(validaCampos()) {

                contato.setNome(edtNome.getText().toString());
                contato.setIdade(Integer.parseInt(edtIdade.getText().toString()));

                SQLiteAdapter sqLiteAdapter = new SQLiteAdapter(this);
                sqLiteAdapter.updateContato(contato);

                setResult(RESULT_OK);
                finish();
            } else {
                Toast.makeText(this, "Preencha todos os campos!", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private boolean validaCampos() {

        if(TextUtils.isEmpty(edtNome.getText())) {
            return false;
        }

        if(TextUtils.isEmpty(edtIdade.getText())) {
            return false;
        }

        return true;
    }
}
