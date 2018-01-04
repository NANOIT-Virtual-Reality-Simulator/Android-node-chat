package com.example.chatpablo;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

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

public class Registro extends Activity 
{	
	//IOSocket socket;
	TextView txtUsuario;
	TextView txtContrasena;
	TextView txtRepetirContrasena;
	Button boton;
	Button boton2;
	Socket socket;
	//String usuario;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		try
		{
			setContentView(R.layout.registro);
			socket = IO.socket("http://192.168.1.10:1337");
			socket.connect();
			boton = (Button)this.findViewById(R.id.btnRegistro);
			boton2 = (Button)this.findViewById(R.id.btnVolver);
			txtUsuario = (TextView)this.findViewById(R.id.txtRegistroUsuario);
			txtContrasena = (TextView)this.findViewById(R.id.txtRegistroContrasena);
			txtRepetirContrasena = (TextView)this.findViewById(R.id.txtRepetirContrasena);
			super.onCreate(savedInstanceState);		
			boton.setOnClickListener(new OnClickListener() 
			{
				@Override
				public void onClick(View v)
				{
					/*try
					{*/
						Log.i("click", "Has cliqueado");
						String usuario =  txtUsuario.getText().toString();
						String contrasena = txtContrasena.getText().toString();
						String rcontrasena = txtRepetirContrasena.getText().toString();						
						if(usuario.length()  > 0 && contrasena.length() > 0 )
						{
							if(contrasena.equals(rcontrasena))
							{
								JSONObject obj = new JSONObject();
								obj.put("nombre", usuario);
								obj.put("contrasena", contrasena);
								//obj.put("rcontrasena", rcontrasena);
								socket.emit("registro",obj);
							}
							else
							{
								Toast.makeText(getApplicationContext(), "Error,  comprueba que los campos contraseña y repetir contraseña sean identicos",Toast.LENGTH_SHORT).show();							
							}
						}
						else
						{
							Toast.makeText(getApplicationContext(), "El usuario o la contraseña no pueden estar vacios",Toast.LENGTH_SHORT).show();							
						}
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
					/*try
					{*/
						Log.i("click", "Has cliqueado");
					   /* String nombrePrograma="android.intent.action.MAIN";
					    Intent i = new Intent(nombrePrograma);
					    startActivity(i);*/
						finish();
					/*}
					catch(IOException e)
					{				
					
					}*/
				}	
			});
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

			}).on("correcto", new Emitter.Listener() {
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
			
			/*socket.on("iniciado", new Emitter.Listener() {
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
				});*/
			
			/*socket.on("correcto", new Emitter.Listener() {
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
				});*/
			
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
