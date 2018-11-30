package com.ronemendes.tcc;

import android.content.Intent;
import android.os.Bundle;

import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import org.json.*;
import com.loopj.android.http.*;

import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONException;

import cz.msebera.android.httpclient.Header;

public class CadClienteActivity extends AppCompatActivity {

    private EditText edtNome;
    private EditText editCpf;
    private EditText edtEndereco;
    private EditText edtMunicipio;
    private EditText editUF;
    private EditText edtTelefone;
    private EditText edtEmail;
    private EditText editSenha;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cad_cliente);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //recuperando os componentes da interface.
        edtNome      = (EditText)findViewById(R.id.edtNome);
        editCpf       = (EditText)findViewById(R.id.editCpf);
        edtEndereco  = (EditText)findViewById(R.id.edtEndereco);
        edtMunicipio = (EditText)findViewById(R.id.edtMunicipio);
        editUF        = (EditText)findViewById(R.id.editUF);
        edtTelefone  = (EditText)findViewById(R.id.edtTelefone);
        edtEmail     = (EditText)findViewById(R.id.edtEmail);
        editSenha     = (EditText)findViewById(R.id.editSenha);

    }

    // método para cadastrar cliente
    public void cadastrarCliente(){

        String nome = edtNome.getText().toString();
        String cpf = editCpf.getText().toString();
        String endereco = edtEndereco.getText().toString();
        String municipio = edtMunicipio.getText().toString();
        String estado = editUF.getText().toString();
        String telefone  = edtTelefone.getText().toString();
        String email = edtEmail.getText().toString();
        String senha = editSenha.getText().toString();

        //exibir mensagem de validação dos campos
        if(nome.isEmpty() || cpf.isEmpty() || endereco.isEmpty() || municipio.isEmpty() ||
                estado.isEmpty() || telefone.isEmpty()|| email.isEmpty() || senha.isEmpty()){
            AlertDialog.Builder dlg = new AlertDialog.Builder(this);
            dlg.setTitle(R.string.title_aviso);
            dlg.setMessage("Campos nao podem ficar em branco");
            dlg.setNeutralButton(R.string.lbl_ok, null); //exibe um botão na janela de dialogo
            dlg.show();
            return;
        }

        //validando os parâmetros com os capos do banco
        RequestParams params = new RequestParams();
        params.put("nome", nome);
        params.put("cpf", cpf);
        params.put("endereco", endereco);
        params.put("municipio", municipio);
        params.put("estado", estado);
        params.put("telefone", telefone);
        params.put("email", email);
        params.put("senha", senha);

        //chamando a api REST
        ApiRestClient.post("clientes/cadastrar", params , new JsonHttpResponseHandler() {

            //Em caso de sucesso
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                System.out.println("Cliente cadastrado");
            }

            //Em caso de falha
            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                //mostra mensagem erro
                System.out.println(responseString);
            }
        });


    }



    @Override
    //configurar menu do app
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_act_cad_cliente, menu);

        return super.onCreateOptionsMenu(menu);
    }


    @Override
    //tratar ações do menu
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        switch (id){

            case R.id.action_ok:

                //chamando o método cadastrar no botão de Salvar
                this.cadastrarCliente();
                Toast.makeText(this, "Cliente cadastrado com sucesso", Toast.LENGTH_SHORT).show();

                break;

            //action do botão de menu
            case R.id.action_cancelar:

                //Toast.makeText(this, "Botão Cancelar Selecionado", Toast.LENGTH_SHORT).show();
                finish();

                break;

        }
        return super.onOptionsItemSelected(item);
    }

//    public void getClientes() {
//         ApiRestClient.get("clientes", null, new JsonHttpResponseHandler() {
//            @Override
//            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
//                // If the response is JSONObject instead of expected JSONArray
//                System.out.println(response);
//            }
//
//            @Override
//            public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
//                // Pull out the first event on the public timeline
//
//                System.out.println(response);
////                try {
////                    JSONObject firstEvent = timeline.get(0);
////                    String tweetText = firstEvent.getString("text");
////                }catch (Exception )
//
//
//                // Do something with the response
//
//            }
//        });
//    }
}
