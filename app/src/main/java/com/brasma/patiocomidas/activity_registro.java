package com.brasma.patiocomidas;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.InputType;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.squareup.picasso.Picasso;

public class activity_registro extends AppCompatActivity implements View.OnClickListener {

    private Button btnCerrarSesion;
    private Button btnRemove;
    private ImageButton btnMostrarContrseña;
    private GoogleSignInClient mGoogleSignInClient;
    private TextInputLayout txtContraseña,txtNombre,txtCorreo;
    private ImageView foto;
    boolean accesoRapido;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ingreso_activity_registro);
        btnCerrarSesion= (Button)findViewById(R.id.btnCerrarSesion);
        btnRemove= (Button)findViewById(R.id.btnRemove);
        txtContraseña=(TextInputLayout)findViewById(R.id.txtContraseña_Registro);
        foto=(ImageView)findViewById(R.id.imageView_Registro);
        txtNombre=(TextInputLayout)findViewById(R.id.txtNombre_Registro);
        txtCorreo=(TextInputLayout)findViewById(R.id.txtCorreo_Registro);
        btnMostrarContrseña=(ImageButton) findViewById(R.id.btnMostrarContraseña_Registrar);

        //cerrar sesion y remover sesion
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);

        //obtener datos del correo
        GoogleSignInAccount acct = GoogleSignIn.getLastSignedInAccount(this);
        if (acct != null) {
           txtNombre.getEditText().setText(acct.getDisplayName()) ;
            String personGivenName = acct.getGivenName();
            String personFamilyName = acct.getFamilyName();
            txtCorreo.getEditText().setText(acct.getEmail());
            String personId = acct.getId();
            Uri personPhoto = acct.getPhotoUrl();
            try{// verificar si el correo tiene foto
                Toast.makeText(this,personPhoto.getPath(),Toast.LENGTH_SHORT);
                Picasso.get().load(personPhoto).into(foto);
                //Glide.with(this).load(Uri.parse("http://goo.gl/gEgYUd")).into(foto);
            }catch (Exception a){
            }
        }

        btnCerrarSesion.setOnClickListener(this);
        btnRemove.setOnClickListener(this);
        btnMostrarContrseña.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch ( event.getAction() ) {
                    case MotionEvent.ACTION_DOWN:
                        txtContraseña.getEditText().setInputType(InputType.TYPE_CLASS_TEXT);
                        break;
                    case MotionEvent.ACTION_UP:
                        txtContraseña.getEditText().setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                        break;
                }
                return true;
            }
        });
        accesoRapido=getIntent().getBooleanExtra("Acceso rapido",false);
        if(accesoRapido){
            txtContraseña.setVisibility(View.GONE);
            btnMostrarContrseña.setVisibility(View.GONE);
            txtNombre.getEditText().setFocusableInTouchMode(false);
            txtCorreo.getEditText().setFocusableInTouchMode(false);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnCerrarSesion:
                signOut();
                break;
            case R.id.btnRemove:
                revokeAccess();
                break;
            // ...
        }
    }
    private void abrirLogin() {
        Intent intent= new Intent(this, LoginActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
    private void revokeAccess() {
        mGoogleSignInClient.revokeAccess()
                .addOnCompleteListener(this, new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        Toast.makeText(activity_registro.this,"removido",Toast.LENGTH_SHORT).show();
                        abrirLogin();
                    }
                });
    }

    private void signOut() {
        mGoogleSignInClient.signOut()
                .addOnCompleteListener(this, new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        Toast.makeText(activity_registro.this,"Sesion cerrada",Toast.LENGTH_SHORT).show();
                        abrirLogin();
                    }
                });
    }
}
