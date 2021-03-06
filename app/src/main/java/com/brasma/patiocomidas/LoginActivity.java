package com.brasma.patiocomidas;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.brasma.patiocomidas.entidades.Procesos;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;

import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;


public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private GoogleSignInClient mGoogleSignInClient;
    private SignInButton btnAccedorGoogle;
    private Button btnRegistrarse;
    private Button btnIngresar;
    private EditText txtContraseña;
    private static final int SIGN_IN_CODE=77;
    private ImageButton btnMostrarContrseña;
    private Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ingreso_activity_login);
        context=this;
        btnAccedorGoogle=(SignInButton)findViewById(R.id.btnAccederGoogle_Login);
        btnRegistrarse=(Button) findViewById(R.id.btnRegistarse_Login);
        btnMostrarContrseña=(ImageButton) findViewById(R.id.btnMostrarContraseña_Login);
        txtContraseña=(EditText)findViewById(R.id.txtContraseña_Login);
        btnIngresar=(Button)findViewById(R.id.btnIngresar_Login);
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);
        btnAccedorGoogle.setOnClickListener(this);
        btnRegistrarse.setOnClickListener(this);
        btnIngresar.setOnClickListener(this);
        btnMostrarContrseña.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch ( event.getAction() ) {
                    case MotionEvent.ACTION_DOWN:
                        txtContraseña.setInputType(InputType.TYPE_CLASS_TEXT);
                        break;
                    case MotionEvent.ACTION_UP:
                        txtContraseña.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                        break;
                }
                return true;
            }
        });
        permisos();

    }

    private void permisos() {
        if(ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)== PackageManager.PERMISSION_GRANTED){
            Toast.makeText(this,"Tiene permisos",Toast.LENGTH_SHORT).show();
        }else{
            ActivityCompat.requestPermissions(this,new String[]{
                    Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.ACCESS_COARSE_LOCATION},1);
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(this);
        updateUI(account,R.id.btnIngresar_Login);
    }

    private void updateUI(GoogleSignInAccount account, int activity) {
        if(account!=null){
            abrirRegistro(activity);
        }
    }


    private void abrirRegistro(int aux) {
        Intent intent=null;
        Toast.makeText(this, "jfjfs", Toast.LENGTH_SHORT).show();
        if(aux==R.id.btnRegistarse_Login){
            intent= new Intent(this, activity_registro.class);
            intent.putExtra("Acceso rapido",false);
        }else if (aux==R.id.btnAccederGoogle_Login){//o fb
            intent= new Intent(this, activity_registro.class);
            intent.putExtra("Acceso rapido",true);
        }else if(aux==R.id.btnIngresar_Login){
            intent= new Intent(this, PrincipalActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
        }
        startActivity(intent);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnAccederGoogle_Login:
                signIn();
                break;
            case R.id.btnRegistarse_Login:
                abrirRegistro(R.id.btnRegistarse_Login);
                break;
            case R.id.btnIngresar_Login:
                Procesos.cargandoIniciar(this);
                ingresar();
                Procesos.cargandoDetener();
                break;
            // ...
        }
    }

    private void ingresar(){
        Intent intent= new Intent(this, PrincipalActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
    private void signIn() {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, SIGN_IN_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // Result returned from launching the Intent from GoogleSignInClient.getSignInIntent(...);
        if (requestCode == SIGN_IN_CODE) {
            // The Task returned from this call is always completed, no need to attach
            // a listener.
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            handleSignInResult(task);
        }
    }

    private void handleSignInResult(Task<GoogleSignInAccount> completedTask) {
        try {
            GoogleSignInAccount account = completedTask.getResult(ApiException.class);
            // Signed in successfully, show authenticated UI.
            updateUI(account,R.id.btnAccederGoogle_Login);
        } catch (ApiException e) {
            // The ApiException status code indicates the detailed failure reason.
            // Please refer to the GoogleSignInStatusCodes class reference for more information.
            Log.w("ERRORR", "signInResult:failed code=" + e.getStatusCode());
        }
    }
}
