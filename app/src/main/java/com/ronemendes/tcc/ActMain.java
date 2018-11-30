package com.ronemendes.tcc;

import android.content.Intent;
import android.os.Bundle;

import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;

import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

public class ActMain extends AppCompatActivity {

    private RecyclerView lstDados;
   // private FloatingActionButton fab;

    private EditText edtLogin;
    private EditText edtSenha2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_act_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        lstDados = (RecyclerView)findViewById(R.id.lstDados);

        //recuperando os componentes da interface.
        edtLogin   = (EditText)findViewById(R.id.edtLogin);
        edtSenha2  = (EditText)findViewById(R.id.edtSenha2);
    }

    // método para autenticar client
    public void login(View view){

        String email = edtLogin.getText().toString();
        String senha = edtSenha2.getText().toString();

        //exibir mensagem de validação dos campos
        if(email.isEmpty() || senha.isEmpty()){ //exibir mensagem de validação
            AlertDialog.Builder dlg = new AlertDialog.Builder(this);
            dlg.setTitle(R.string.title_aviso);
            dlg.setMessage("Email ou senha não podem estar em branco");
            dlg.setNeutralButton(R.string.lbl_ok, null); //exibe um botão na janela de dialogo
            dlg.show();
            return;
        }

        //validando os parâmetros com os capos do banco
        RequestParams params = new RequestParams();
        params.put("email", email);
        params.put("senha", senha);

        //chamando a api REST
        ApiRestClient.post("autenticar", params , new JsonHttpResponseHandler() {

            //Em caso de sucesso
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {

                Intent it = new Intent(ActMain.this, CadClienteActivity.class);
                startActivity(it);
            }

            //Em caso de falha
            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                //mostrar mensagem erro
                System.out.println(responseString);
            }
        });


    }

    public void cadastrar(View view){

        Intent it = new Intent(ActMain.this, CadClienteActivity.class);
        startActivity(it);
    }

}
