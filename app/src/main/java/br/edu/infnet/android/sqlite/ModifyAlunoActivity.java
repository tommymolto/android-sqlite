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

public class ModifyAlunoActivity extends Activity implements OnClickListener {

    private EditText txtNome;
    private Button btAtualiza, btDeleta;
    private EditText txtSobrenome;
    private long _id;
    private DBManager dbManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        /* seta tela */
        setTitle("Editar Aluno");
        setContentView(R.layout.activity_modify_record);

        /*abre banco*/
        dbManager = new DBManager(this);
        dbManager.open();

        /* pega interface*/
        txtNome = (EditText) findViewById(R.id.editaNome);
        txtSobrenome = (EditText) findViewById(R.id.editaSobrenome);

        btAtualiza = (Button) findViewById(R.id.editaRegistro);
        btDeleta = (Button) findViewById(R.id.deletaRegistro);

        /*prepara passagem de parametros para outra act*/

        Intent intent = getIntent();
        String id = intent.getStringExtra("id");
        String nome = intent.getStringExtra("nome");
        String sobrenome = intent.getStringExtra("sobrenome");

        _id = Long.parseLong(id);

        txtNome.setText(nome);
        txtSobrenome.setText(sobrenome);

        /* cria listeners de botoes */
        btAtualiza.setOnClickListener(this);
        btDeleta.setOnClickListener(this);
    }

    /*um listener para os 2 botoes*/
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.editaRegistro:
                String nome = txtNome.getText().toString();
                String sobrenome = txtSobrenome.getText().toString();

                dbManager.update(_id, nome, sobrenome);
                this.returnHome();
                break;

            case R.id.deletaRegistro:
                dbManager.delete(_id);
                this.returnHome();
                break;
        }
    }

    /*retorno a tela principal
    limpando a fila de threads
     */
    public void returnHome() {
        Intent home_intent = new Intent(getApplicationContext(), AlunoListActivity.class)
                .setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(home_intent);
    }
}
