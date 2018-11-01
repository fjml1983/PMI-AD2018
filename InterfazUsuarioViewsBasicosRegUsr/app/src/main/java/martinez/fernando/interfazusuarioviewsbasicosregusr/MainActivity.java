package martinez.fernando.interfazusuarioviewsbasicosregusr;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.TimePickerDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;
import android.widget.ToggleButton;

import java.util.Calendar;
import java.util.Date;
import java.util.Formatter;

public class MainActivity extends AppCompatActivity {

    public static TextView txtHoraNacimiento, txtFechaNacimiento;
    public TextView txtUserName, txtPassword, txtNombrePila;
    public CheckBox chkJava, chkDotNet, chkPhyton;
    public RadioButton rdbMasculino, rdbFemenino;
    public Switch swNotificaciones;
    public ToggleButton tbPublicidad;
    public Spinner spOrigen;
    public ImageButton btnHoraNacimiento,btnFechaNacimiento;
    public Button btnGuardar;
    public static int year, month, date, hrs, min;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtUserName = (TextView) findViewById(R.id.tv_username);
        txtPassword = (TextView) findViewById(R.id.tv_password);
        txtNombrePila = (TextView) findViewById(R.id.tv_nombrePila);
        chkDotNet = (CheckBox) findViewById(R.id.cb_dotnet);
        chkJava = (CheckBox) findViewById(R.id.cb_java);
        chkPhyton = (CheckBox) findViewById(R.id.cb_phyton);
        rdbFemenino = (RadioButton) findViewById(R.id.rd_femenino);
        rdbMasculino = (RadioButton) findViewById(R.id.rd_masculino);
        swNotificaciones = (Switch) findViewById(R.id.sw_notificaciones);
        tbPublicidad = (ToggleButton) findViewById(R.id.tg_publicidad);
        spOrigen = (Spinner) findViewById(R.id.sp_origen);

        btnHoraNacimiento =
                (ImageButton)findViewById(R.id.ib_horaFechaNacimiento);
        btnFechaNacimiento =
                (ImageButton)findViewById(R.id.ib_calenFechaNacimiento);
        txtHoraNacimiento =
                (TextView) findViewById(R.id.tv_horaNacimiento);
        txtFechaNacimiento =
                (TextView) findViewById(R.id.tv_fechaNacimiento);
        btnGuardar =
                (Button) findViewById(R.id.btn_guardar);


        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO: Bajar todos los valores de los GUI Widgets a un pojo
                Usuario objUsr = new Usuario();
                objUsr.setUsername(txtUserName.getText().toString());
                objUsr.setPassword(txtPassword.getText().toString());
                objUsr.setNombrePila(txtNombrePila.getText().toString());

                String tecnologias = "";
                tecnologias += (chkDotNet.isChecked())? ".NET;" : "";
                tecnologias += (chkJava.isChecked())? "JAVA;" : "";
                tecnologias += (chkPhyton.isChecked())? "PHYTON;":"";
                objUsr.setTecnologias(tecnologias );

                objUsr.setGenero((rdbFemenino.isChecked())?"FEMENINO":"MASCULINO");
                objUsr.setNotificaciones(swNotificaciones.isActivated());
                objUsr.setPublicidad(tbPublicidad.isChecked());
                objUsr.setIes_origen(spOrigen.getSelectedItem().toString() );
                objUsr.setFechaHoraNacimiento(new Date(year, month, date, hrs, min));

                Toast.makeText(MainActivity.this, "Se guardó el usuario: "
                        + objUsr.toString(), Toast.LENGTH_LONG).show();
            }
        });


        btnHoraNacimiento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment newFragment = new TimePickerFragment();
                newFragment.show(getFragmentManager(), "timePicker");
            }
        });
        btnFechaNacimiento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment newFragment = new DatePickerFragment();
                newFragment.show(getFragmentManager(), "DatePicker");
            }
        });

    }

    public static class TimePickerFragment extends DialogFragment
            implements TimePickerDialog.OnTimeSetListener{

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            // Use the current time as the default values for the picker
            final Calendar c = Calendar.getInstance();
            int hour = c.get(Calendar.HOUR_OF_DAY);
            int minute = c.get(Calendar.MINUTE);

            // Create a new instance of TimePickerDialog and return it
            return new TimePickerDialog(getActivity(), this, hour, minute,
                    DateFormat.is24HourFormat(getActivity()));
        }

        @Override
        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            //Programar nuestro código en respuesta a que se seleccionó hora
            hrs = hourOfDay;
            min = minute;
            txtHoraNacimiento.setText("Hora:" + hourOfDay + " Minuto:" + minute);
        }
    }

    public static class DatePickerFragment extends DialogFragment
            implements DatePickerDialog.OnDateSetListener {

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            // Use the current date as the default date in the picker
            final Calendar c = Calendar.getInstance();
            int year = c.get(Calendar.YEAR);
            int month = c.get(Calendar.MONTH);
            int day = c.get(Calendar.DAY_OF_MONTH);

            // Create a new instance of DatePickerDialog and return it
            return new DatePickerDialog(getActivity(), this, year, month, day);

        }

        public void onDateSet(DatePicker view, int y, int m, int d) {
            // Do something with the date chosen by the user
            year = y;
            month = m;
            date = d;
            txtFechaNacimiento.setText("Día:" + d +
                                       " Mes:" + m +
                                       " Año:" + y);
        }
    }

}
