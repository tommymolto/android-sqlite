package br.edu.infnet.android.sqlite;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.widget.SimpleCursorAdapter;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

public class AlunoListActivity extends AppCompatActivity {

    /*setup de variaveis*/

    private DBManager dbManager;
    private ListView listView;
    private SimpleCursorAdapter adapter;

    /* " query" de conexao ao banco */
    final String[] from = new String[] {
            DatabaseHelper._ID,
            DatabaseHelper.NOME,
            DatabaseHelper.SOBRENOME };

    /* modelo de retorno para a view*/
    final int[] to = new int[] { R.id.id,
            R.id.nome,
            R.id.sobrenome };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        /*setup de layout*/
        setContentView(R.layout.fragment_emp_list);
        listView = (ListView) findViewById(R.id.list_view);
/*abre db*/
        dbManager = new DBManager(this);
        dbManager.open();
        /* faz query de consulta */
        Cursor cursor = dbManager.fetch();
        /*adapter para a lista*/
        adapter = new SimpleCursorAdapter(this, R.layout.activity_view_record, cursor, from, to, 0);
        adapter.notifyDataSetChanged();

        /*integracao com a listview*/
        listView.setAdapter(adapter);

        // listener de click
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long viewId) {

                /*encontra campos no layout*/
                TextView idTextView = (TextView) view.findViewById(R.id.id);
                TextView nomeView = (TextView) view.findViewById(R.id.nome);
                TextView sobrenomeView = (TextView) view.findViewById(R.id.sobrenome);

                String id = idTextView.getText().toString();
                String nome = nomeView.getText().toString();
                String sobrenome = sobrenomeView.getText().toString();

                /*exibe view com os valorespara edicao */
                Intent modify_intent = new Intent(getApplicationContext(), ModifyAlunoActivity.class);
                modify_intent.putExtra("nome", nome);
                modify_intent.putExtra("sobrenome", sobrenome);
                modify_intent.putExtra("id", id);

                startActivity(modify_intent);
            }
        });
    }

    /* cria menu */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
public void addAluno(View v){
    Intent i = new Intent(this, AddAlunoActivity.class);
    startActivity(i);
}
    /* ao clicar em um item*/
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.add_record) {
            Intent add_mem = new Intent(this, AddAlunoActivity.class);
            startActivity(add_mem);
        }
        return super.onOptionsItemSelected(item);
    }

}