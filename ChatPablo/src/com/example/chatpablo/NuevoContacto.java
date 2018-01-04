package com.example.chatpablo;

import java.io.IOException;
import java.util.ArrayList;

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
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


//import com.github.nkzawa.*;
import com.github.nkzawa.emitter.Emitter;
import com.github.nkzawa.socketio.client.*;
import com.google.gson.Gson;

public class NuevoContacto extends Activity 
{	

	/*TextView txtUsuario;
	TextView txtContrasena;*/
	Button boton;
	Socket socket;
    String contacto;
    String usuario;
    int tipo;
    TextView txtContacto;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		try
		{
			Bundle bundle = getIntent().getExtras();			
			usuario = bundle.getString("usuario");
			setContentView(R.layout.nuevo_contacto);
			txtContacto = (TextView)this.findViewById(R.id.txtNombreContacto);
			/*txtUsuario = (TextView)this.findViewById(R.id.txtUsuario);
			txtContrasena = (TextView)this.findViewById(R.id.txtContrasena);*/
			boton = (Button)this.findViewById(R.id.btnAceptar);
			super.onCreate(savedInstanceState);		
			boton.setOnClickListener(new OnClickListener() 
			{
				@Override
				public void onClick(View v)
				{
					/*try
					{*/
						Log.i("click", "Has cliqueado");
						CharSequence contacto = txtContacto.getText();						
						JSONObject obj = new JSONObject();
						obj.put("contacto", contacto);
						obj.put("pertenencia", usuario);
						obj.put("tipo", 1);
						socket.emit("agregarContacto",obj);
						//listado.
					/*}
					catch(IOException e)
					{				
					
					}*/
				}	
			});
		    //Toast.makeText(context, text, duration)
			/*SocketIO socket = new SocketIO("http://192.168.1.10:1337/");
	        socket.connect(new IOCallback() {
	            @Override
	            public void onMessage(JSONObject json, IOAcknowledge ack) {
	                try {
	                   // System.out.println("Server said:" + json.toString(2));
	                } catch (JSONException e) {
	                    e.printStackTrace();
	                }
	            }

	            @Override
	            public void onMessage(String data, IOAcknowledge ack) {
	               // System.out.println("Server said: " + data);
	            }

	            @Override
	            public void onError(SocketIOException socketIOException) {
	            	//Toast.makeText(MainActivity.this, "Hola", Toast.LENGTH_SHORT).show();
	                //System.out.println("an Error occured");
	                socketIOException.printStackTrace();
	                //Toast.makeText(null, "error", Toast.LENGTH_SHORT).show();
	            }

	            @Override
	            public void onDisconnect() {
	                //System.out.println("Connection terminated.");
	            }

	            @Override
	            public void onConnect() {
	                //System.out.println("Connection established");
	            	Toast.makeText(null, "Hola", Toast.LENGTH_SHORT).show();
	            }

	            @Override
	            public void on(String event, IOAcknowledge ack, Object... args) {
	                //System.out.println("Server triggered event '" + event + "'");
	            	Toast.makeText(null, "Hola", Toast.LENGTH_SHORT).show();
	            }
	        });*/
			/*Log.i("prueba", "hola");
			 SocketIOClient.connect(AsyncHttpClient.getDefaultInstance(), "http://192.168.1.10:1337", new ConnectCallback() {
			    @Override
			    public void onConnectCompleted(Exception ex, SocketIOClient client) {
			    	Log.i("prueba", "He llegado");
			        if (ex != null) {
			            ex.printStackTrace();
			            return;			         
			        }
			       /*client.setStringCallback(new StringCallback() {
			            @Override
			            public void onString(String string) {
			                System.out.println(string);
			            }
			        });
			        client.on("someEvent", new EventCallback() {
			            @Override
			            public void onEvent(JSONArray argument, Acknowledge acknowledge) {
			                System.out.println("args: " + arguments.toString());
			            }
			        });
			        client.setJSONCallback(new JSONCallback() {
			            @Override
			            public void onJSON(JSONObject json) {
			                System.out.println("json: " + json.toString());
			            }
			        });
			    }
			});*/
			/*socket = new Socket("ws://192.168.1.10:1338");
			socket.on(Socket.EVENT_OPEN, new Emitter.Listener() {
			  @Override
			  public void call(Object... args) {
			    socket.send("hi");
			    socket.close();
			  }
			});
			socket.open();*/
			socket = IO.socket("http://192.168.1.10:1337");
			socket.on(Socket.EVENT_CONNECT, new Emitter.Listener() {

			  @Override
			  public void call(Object... args) {
	  
			   /* socket.emit("foo", "hi");
			    socket.disconnect();*/
			  }

			}).on("correcto", new Emitter.Listener() {

			  @Override
			  public void call(Object... args) {
				  Log.i("conectado","si");
			  }
			  
			}).on(Socket.EVENT_DISCONNECT, new Emitter.Listener() {

			  @Override
			  public void call(Object... args) {}

			});
			socket.connect();
			
			socket.on("correcto", new Emitter.Listener() {
				  @Override
				  public void call(Object... args) 
				  {					 		    
		                try 
		                {
		                    runOnUiThread(new Runnable() 
		                    {
		                        @Override
		                        public void run() 
		                        {
		                           	Toast.makeText(getApplicationContext(), "Contacto añadido correctamente", Toast.LENGTH_SHORT).show();
		                           	Toast.makeText(getApplicationContext(), "Grupo creado correctamente", Toast.LENGTH_SHORT).show();
		        				    String nombrePrograma="listaContactos";
		        				    Intent i = new Intent(nombrePrograma);
		        				    i.putExtra("contacto", usuario);
		        				    startActivity(i);
		                           	finish();
		                        }			                     
		                    });			                   
		                } 
		                catch (JSONException e) 
		                {
		                    e.printStackTrace();
		                }  
				  }
				});
						
			//socket.on("recibirMensaje, fn)		
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
