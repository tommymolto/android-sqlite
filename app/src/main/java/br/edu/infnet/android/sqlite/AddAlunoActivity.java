package br.edu.infnet.android.sqlite;

/**
 * Created by pm on 19/10/15.
 */
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class AddAlunoActivity extends Activity  {

    /*setup de variaveis*/
    private Button btSalva;
    private EditText txtNome;
    private EditText txtSobrenome;

    private DBManager dbManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        /*setup de variaveis de layout*/
        setTitle("Adicionar Aluno");
        setContentView(R.layout.activity_add_record);
        txtNome = (EditText) findViewById(R.id.editNome);
        txtSobrenome = (EditText) findViewById(R.id.editSobrenome);
        btSalva = (Button) findViewById(R.id.salvaRegistro);

        /*instanciando db*/
        dbManager = new DBManager(this);
        dbManager.open();
        btSalva.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                chama(view);
            }
        });
    }


    public void chama(View v) {
        /*se botao for de adicionar*/
        switch (v.getId()) {
            case R.id.salvaRegistro:

                /*pega variaveis da tela*/
                final String name = txtNome.getText().toString();
                final String desc = txtSobrenome.getText().toString();

                /*insere no db*/
                dbManager.insert(name, desc);

                /*redireciona apra lista de alunos*/
                Intent main = new Intent(AddAlunoActivity.this, AlunoListActivity.class)
                        .setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

                startActivity(main);
                break;
        }
    }
}