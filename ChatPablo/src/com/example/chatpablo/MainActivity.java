package com.example.chatpablo;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

//import com.koushikdutta.*;
//import com.koushikdutta.async.callback.ConnectCallback;
/*import com.koushikdutta.async.http.AsyncHttpClient;
import com.koushikdutta.async.http.socketio.EventCallback;
import com.koushikdutta.async.http.socketio.JSONCallback;
import com.koushikdutta.async.http.socketio.ConnectCallback;
import com.koushikdutta.async.http.socketio.SocketIOClient;
import com.koushikdutta.async.http.socketio.StringCallback;*/
//import com.koushikdutta.async.http.socketio.SocketIOClient;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
//import clwillingham.socket.io.*;
//import io.socket.*;

//import com.github.nkzawa.*;
import com.github.nkzawa.emitter.Emitter;
import com.github.nkzawa.socketio.client.*;

public class MainActivity extends Activity 
{	
	//IOSocket socket;
	TextView txtUsuario;
	TextView txtContrasena;
	Button boton;
	Button boton2;
	Socket socket;
	String usuario;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		try
		{
			setContentView(R.layout.entrada);
			boton = (Button)this.findViewById(R.id.entrada);
			boton2 = (Button)this.findViewById(R.id.btnRegistro);
			txtUsuario = (TextView)this.findViewById(R.id.txtUsuario);
			txtContrasena = (TextView)this.findViewById(R.id.txtContrasena);
			super.onCreate(savedInstanceState);		
			boton.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v)
				{
					/*try
					{*/
						Log.i("click", "Has cliqueado");
						usuario =  txtUsuario.getText().toString();
						CharSequence contrasena = txtContrasena.getText();
						JSONObject obj = new JSONObject();
						obj.put("nombre", usuario);
						obj.put("contrasena", contrasena);
						socket.emit("login",obj);
					/*}
					catch(IOException e)
					{				
					
					}*/
				}	
			});
			boton2.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v)
				{
				    String nombrePrograma="cargarRegistro";
				    Intent i = new Intent(nombrePrograma);
				    startActivity(i); 
				}	
			});
			socket = IO.socket("http://192.168.1.10:1337");
			socket.on(Socket.EVENT_CONNECT, new Emitter.Listener() {

			  @Override
			  public void call(Object... args) {
			   /* socket.emit("foo", "hi");
			    socket.disconnect();*/
			  }

			}).on("iniciado", new Emitter.Listener() {

			  @Override
			  public void call(Object... args) {
				  Log.i("conectado","si");
			  }
			  
			}).on(Socket.EVENT_DISCONNECT, new Emitter.Listener() {

			  @Override
			  public void call(Object... args) {}

			});
			socket.connect();
			
			socket.on("iniciado", new Emitter.Listener() {
				  @Override
				  public void call(Object... args)
				  {
				    Log.i("conectado","si");
				    String nombrePrograma="listaContactos";
				    Intent i = new Intent(nombrePrograma);
				    i.putExtra("contacto", usuario);
				    Log.i("contacto",usuario);
				    startActivity(i); 
				  }
				});
			
			socket.on("mensaje", new Emitter.Listener() {
				  @Override
				  public void call(Object... args)
				  {
					JSONObject obj = (JSONObject)args[0];  
					final CharSequence mensaje = obj.getString("mensaje");
	                  runOnUiThread(new Runnable() {
	                      @Override
	                      public void run() {
	                      	Toast.makeText(getApplicationContext(), mensaje, Toast.LENGTH_SHORT).show();	                      	
	                     }			                  
	                 });
				  }
				});
		}
		catch(Exception e)
		{
			//throw new Exception(e);
		}

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
