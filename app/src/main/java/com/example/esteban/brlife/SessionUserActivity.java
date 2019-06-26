package com.example.esteban.brlife;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.esteban.brlife.ConeionWebServices.CargarRegistroUsuarioHttpConexion;
import com.example.esteban.brlife.ConeionWebServices.CrudUsuarioHttpConecction;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import org.json.JSONException;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import me.dm7.barcodescanner.zxing.ZXingScannerView;

public class SessionUserActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener  {
      private Button btnScannBar1,btnAgregarComidaManual, btnHorarioComidaDesayuno, btnHorarioComidaAlmuerzo, btnHorarioComidaOnce, btnHorarioComidaCena;
      private TextView tvNombreUsuario, tvCorreo, tvMaximoCalorias, tvCaloriasConsumidas;
      private ZXingScannerView scanner;
      public SimpleDateFormat sdfDia = new SimpleDateFormat("dd");
      public SimpleDateFormat sdfMes = new SimpleDateFormat("MM");
      public SimpleDateFormat sdfAno = new SimpleDateFormat("yyyy");


    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_session_user);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        btnScannBar1=(Button)findViewById(R.id.btnScannBar1);
        btnAgregarComidaManual=(Button)findViewById(R.id.btnAgregarComidaManual);
        tvMaximoCalorias = (TextView)findViewById(R.id.tvMaximoCalorias);
        btnHorarioComidaDesayuno = (Button)findViewById(R.id.btnHorarioComidaDesayuno);
        btnHorarioComidaAlmuerzo = (Button)findViewById(R.id.btnHorarioComidaAlmuerzo);
        btnHorarioComidaOnce = (Button)findViewById(R.id.btnHorarioComidaOnce);
        btnHorarioComidaCena = (Button)findViewById(R.id.btnHorarioComidaCena);

        btnHorarioComidaDesayuno.setEnabled(true);
        btnHorarioComidaAlmuerzo.setEnabled(true);
        btnHorarioComidaOnce.setEnabled(true);
        btnHorarioComidaCena.setEnabled(true);


        tvCaloriasConsumidas = (TextView)findViewById(R.id.tvCaloriasConsumidas);

        final Intent intent =new Intent(this,RegistroProductoHorarioActivity.class);


        Date fechactual = new Date();

        CargarRegistroUsuarioHttpConexion.dia = Integer.parseInt(sdfDia.format(fechactual));
        CargarRegistroUsuarioHttpConexion.mes = Integer.parseInt(sdfMes.format(fechactual));
        CargarRegistroUsuarioHttpConexion.ano = Integer.parseInt(sdfAno.format(fechactual));



        try {
            CargarRegistroUsuarioHttpConexion.TraerDatosRegistroUsuarioTotales("RegistroUsuario",
                    CrudUsuarioHttpConecction.usuario.getIdUsuario(),
                    CargarRegistroUsuarioHttpConexion.dia,
                    CargarRegistroUsuarioHttpConexion.mes,
                    CargarRegistroUsuarioHttpConexion.ano);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }


        btnHorarioComidaDesayuno.setText(CargarRegistroUsuarioHttpConexion.listatotales.get(0).getTotalhorariocomida() + "");
        btnHorarioComidaAlmuerzo.setText(CargarRegistroUsuarioHttpConexion.listatotales.get(1).getTotalhorariocomida() + "");
        btnHorarioComidaOnce.setText(CargarRegistroUsuarioHttpConexion.listatotales.get(2).getTotalhorariocomida() + "");
        btnHorarioComidaCena.setText(CargarRegistroUsuarioHttpConexion.listatotales.get(3).getTotalhorariocomida() + "");


        try {
            tvCaloriasConsumidas.setText(CargarRegistroUsuarioHttpConexion.TraerDatosRegistroUsuarioDiaria("RegistroUsuario",
                    CrudUsuarioHttpConecction.usuario.getIdUsuario(),
                    CargarRegistroUsuarioHttpConexion.dia,
                    CargarRegistroUsuarioHttpConexion.mes,
                    CargarRegistroUsuarioHttpConexion.ano) + "");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        if (CrudUsuarioHttpConecction.usuario != null) {
            tvMaximoCalorias.setText(CrudUsuarioHttpConecction.maximocalorias + "");
        }

            final Intent in =new Intent(this,SeleccionarProductoActivity.class);
        btnScannBar1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             scanner();
            }
        });

        btnAgregarComidaManual.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(in);
            }
        });
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        btnHorarioComidaDesayuno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnHorarioComidaDesayuno.setEnabled(false);
                try {
                    CargarRegistroUsuarioHttpConexion.TraerDatosRegistroUsuario("RegistroUsuario",
                            CrudUsuarioHttpConecction.usuario.getIdUsuario(),
                            CargarRegistroUsuarioHttpConexion.dia,
                            CargarRegistroUsuarioHttpConexion.mes,
                            CargarRegistroUsuarioHttpConexion.ano,1);
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                startActivity(intent);
            }
        });

        btnHorarioComidaAlmuerzo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnHorarioComidaAlmuerzo.setEnabled(false);
                try {
                    CargarRegistroUsuarioHttpConexion.TraerDatosRegistroUsuario("RegistroUsuario",
                            CrudUsuarioHttpConecction.usuario.getIdUsuario(),
                            CargarRegistroUsuarioHttpConexion.dia,
                            CargarRegistroUsuarioHttpConexion.mes,
                            CargarRegistroUsuarioHttpConexion.ano,2);
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                startActivity(intent);
            }
        });
        btnHorarioComidaOnce.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnHorarioComidaOnce.setEnabled(false);
                try {
                    CargarRegistroUsuarioHttpConexion.TraerDatosRegistroUsuario("RegistroUsuario",
                            CrudUsuarioHttpConecction.usuario.getIdUsuario(),
                            CargarRegistroUsuarioHttpConexion.dia,
                            CargarRegistroUsuarioHttpConexion.mes,
                            CargarRegistroUsuarioHttpConexion.ano,3);
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                startActivity(intent);
            }
        });

        btnHorarioComidaCena.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnHorarioComidaCena.setEnabled(false);
                try {
                    CargarRegistroUsuarioHttpConexion.TraerDatosRegistroUsuario("RegistroUsuario",
                            CrudUsuarioHttpConecction.usuario.getIdUsuario(),
                            CargarRegistroUsuarioHttpConexion.dia,
                            CargarRegistroUsuarioHttpConexion.mes,
                            CargarRegistroUsuarioHttpConexion.ano,4);
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                startActivity(intent);
            }
        });

    }


    /**
     * metodo que escaneara codigo de barra
     */
    public void scanner(){
        IntentIntegrator intent= new IntentIntegrator(this);
        intent.setDesiredBarcodeFormats(IntentIntegrator.PRODUCT_CODE_TYPES);

        intent.setPrompt("ESCANEAR CODIGO BARRA");
        intent.setCameraId(0);
        intent.setBeepEnabled(false);
        intent.setBarcodeImageEnabled(false);
        intent.initiateScan();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        IntentResult result=IntentIntegrator.parseActivityResult(requestCode,resultCode,data);

        if (result !=null){
            if (result.getContents()==null){
                Toast.makeText(this, "Cancelaste escaneo", Toast.LENGTH_LONG).show();

            }else{
                Toast.makeText(this, " "+result.getContents().toString(), Toast.LENGTH_SHORT).show();
            }

        }else {
            super.onActivityResult(requestCode,resultCode,data);
        }
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.opciones_menu2, menu);
        tvNombreUsuario = (TextView)findViewById(R.id.tvNombreUsuarioNav);
        tvCorreo = (TextView)findViewById(R.id.tvCorreoElectronicoNav);

        if (CrudUsuarioHttpConecction.usuario != null){
            String alias = CrudUsuarioHttpConecction.usuario.getNombreAlias();
            String correo= CrudUsuarioHttpConecction.usuario.getCorreoElectronico();
            tvNombreUsuario.setText(alias);
            tvCorreo.setText(correo);
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        }  else if (id == R.id.nav_manage) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    protected void onResume() {
        super.onResume();
        btnHorarioComidaDesayuno.setEnabled(true);
        btnHorarioComidaAlmuerzo.setEnabled(true);
        btnHorarioComidaOnce.setEnabled(true);
        btnHorarioComidaCena.setEnabled(true);
    }
}
