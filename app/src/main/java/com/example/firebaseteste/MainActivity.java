package com.example.firebaseteste;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {
    private DatabaseReference BD = FirebaseDatabase.getInstance().getReference();
    private EditText txtNome;
    private EditText txtSobrenome;
    private EditText txtIdade;
    private Button btnEnviar;
    private Button btnListar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtNome = findViewById(R.id.txtNome);
        txtSobrenome = findViewById(R.id.txtSobrenome);
        txtIdade = findViewById(R.id.txtIdade);
        btnEnviar = findViewById(R.id.btnEnviar);
        btnListar = findViewById(R.id.btnListar);
        btnEnviar.setOnClickListener(view -> {
            String nome = txtNome.getText().toString();
            String sobrenome = txtSobrenome.getText().toString();
            int idade = Integer.parseInt(txtIdade.getText().toString());
            Usuario usuario = new Usuario(nome, sobrenome, idade);
            DatabaseReference dados = BD.child("dados");
            String chave = dados.push().getKey();
            dados.child(chave).setValue(usuario);
            txtNome.setText("");
            txtSobrenome.setText("");
            txtIdade.setText("");
        });
        btnListar.setOnClickListener(view -> {
            DatabaseReference dados = BD.child("dados");
            dados.addListenerForSingleValueEvent(new FirebaseListener());
        });
        DatabaseReference dados = BD.child("dados");
        dados.addValueEventListener(new FirebaseListener());
    }

    private class FirebaseListener implements ValueEventListener {
        @Override
        public void onDataChange(@NonNull DataSnapshot snapshot) {
            if (snapshot.exists()) {
                String nome, sobrenome;
                int idade;
                for (DataSnapshot dadosUsuario : snapshot.getChildren()) {
                    Usuario usuario = dadosUsuario.getValue(Usuario.class);
                    nome = usuario.getNome();
                    sobrenome = usuario.getSobrenome();
                    idade = usuario.getIdade();

                    Toast.makeText(MainActivity.this, "Nome: " + nome + "\nSobrenome: " + sobrenome + "\nIdade: " + idade, Toast.LENGTH_SHORT).show();
                }
            }
        }

        @Override
        public void onCancelled(@NonNull DatabaseError error) {

        }
    }
}