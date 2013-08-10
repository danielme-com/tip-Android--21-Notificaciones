package com.danielme.tipsandroid.notificaciones;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.danieme.tipsandroid.notificaciones.R;

/**
 * 
 * @author danielme.com
 * 
 */
public class MainActivity extends Activity {

	private static final String DESDE_NOTIFICACION = "desdeNotificacion";

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		if (getIntent().getExtras() != null)
		{
			boolean mostrarToast = getIntent().getExtras().getBoolean(DESDE_NOTIFICACION, false);
			if (mostrarToast)
			{
				Toast.makeText(this, R.string.desdeNoti, Toast.LENGTH_SHORT).show();
			}
		}
	}
	
	public void mostrarNotificacion(View button)
	{
	    NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

	//		Notification notificacion = new NotificationBuilder(this)
	//        .setContentTitle("New mail from " + "test@gmail.com")
	//        .setContentText("Subject")
	//        .setSmallIcon(R.drawable.ic_launcher);
	    
	    //indicamos icono, texto emergente en la barra y la fecha que queramos
	    Notification notificacion = new Notification(R.drawable.ic_launcher, getText(R.string.mensajebarra), System.currentTimeMillis());
	    //intent hacia la activity que se ejecutará cuando se pulse la notificación. Enviamos un parámetro para saber que llegamos
	    //a la activity desde la notificación y mostrar un Toast
	    Intent notificacionIntent = new Intent(getApplicationContext(), MainActivity.class);
	    notificacionIntent.putExtra(DESDE_NOTIFICACION, true);
	    PendingIntent notificacionPendingIntent = PendingIntent.getActivity(getApplicationContext(), 0, notificacionIntent, 0);
	    //contenido de la notificación
	    notificacion.setLatestEventInfo(this.getApplicationContext(), getText(R.string.titulo), getText(R.string.descripcion), notificacionPendingIntent);
		//el atributo flags de la notifiacción nos permite ciertas opciones
	    notificacion.flags |= Notification.FLAG_AUTO_CANCEL;//oculta la notificación una vez pulsada
	    //idem para defaults
	    notificacion.defaults |= Notification.DEFAULT_SOUND; //sonido
	    //añadimos efecto de vibración, necesitamos el permiso <uses-permission android:name="android.permission.VIBRATE" />
	    notificacion.defaults |= Notification.DEFAULT_VIBRATE;
	    //se muestra
	    notificationManager.notify(0, notificacion);		
	}

}