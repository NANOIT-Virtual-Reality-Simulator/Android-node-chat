package com.example.chatpablo;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


import android.app.Activity;
import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

//import com.github.nkzawa.*;
import com.github.nkzawa.emitter.Emitter;
import com.github.nkzawa.socketio.client.*;
import com.google.gson.Gson;

public class Contactos extends Activity 
{	

	/*TextView txtUsuario;
	TextView txtContrasena;*/
	Button boton;
	Socket socket;
	ListView listado;
	ListView listado2;
    ArrayList<String> lista;
    ArrayList<String> lista2;
    String contacto;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		try
		{
			Bundle bundle = getIntent().getExtras();
			contacto = bundle.getString("contacto");
			Log.i("recibido",contacto);
			setContentView(R.layout.activity_main);
			listado = (ListView)this.findViewById(R.id.lista);
			//listado2 = (ListView)this.findViewById(R.id.listadoGrupos);
			lista = new ArrayList<String>();
			boton = (Button)this.findViewById(R.id.btnGrupos);
			socket = IO.socket("http://192.168.1.10:1337");
			socket.connect();
			//lista2 = new ArrayList<String>();
			/*boton = (Button)this.findViewById(R.id.entrada);
			txtUsuario = (TextView)this.findViewById(R.id.txtUsuario);
			txtContrasena = (TextView)this.findViewById(R.id.txtContrasena);*/
			super.onCreate(savedInstanceState);		
			
			listado.setOnItemClickListener(new OnItemClickListener() {
		            @Override
		            public void onItemClick(AdapterView<?> parent, View view, int posicion,
		                    long id) {
		            	//ListEntry entry= (ListEntry) parent.getAdapter().getItem(position);
		            	String amigo = (String) lista.get(posicion); 
		            	
		               // String message = "abc";
		                //listado.g
		                String nombrePrograma="cargarSala";
		                Intent i = new Intent(nombrePrograma);
		                i.putExtra("usuario", contacto);
		                i.putExtra("contacto", amigo);
		                i.putExtra("tipo", 1);
		                startActivity(i);
		            }
		        });
			
			boton.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v)
				{
					/*try
					{*/
				    Log.i("conectado","si");
				    String nombrePrograma="listaGrupos";
				    Intent i = new Intent(nombrePrograma);
				    i.putExtra("contacto", contacto);
				    Log.i("contacto",contacto);
				    startActivity(i);
				    finish();
					/*}
					catch(IOException e)
					{				
					
					}*/
				}	
			});
			
			/*listado2.setOnItemClickListener(new OnItemClickListener() {
	            @Override
	            public void onItemClick(AdapterView<?> parent, View view, int posicion,
	                    long id) {
	            	//ListEntry entry= (ListEntry) parent.getAdapter().getItem(position);
	            	String amigo = (String) lista2.get(posicion); 
	            	
	               // String message = "abc";
	                //listado.g
	                String nombrePrograma="cargarSala";
	                Intent i = new Intent(nombrePrograma);
	                i.putExtra("usuario", contacto);
	                i.putExtra("contacto", amigo);
	                i.putExtra("tipo", 2);
	                startActivity(i);
	            }
	        });*/
			
			/*boton.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v)
				{
					try
					{
						Log.i("click", "Has cliqueado");
						CharSequence usuario = txtUsuario.getText();
						CharSequence contrasena = txtContrasena.getText();
						JSONObject obj = new JSONObject();
						obj.put("nombre", usuario);
						obj.put("contrasena", contrasena);
						socket.emit("login",obj);
					}
					catch(IOException e)
					{				
					
					}
				}	
			});*/
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
;
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
			
			//socket.emit("listarGrupos",obj);
			
			socket.on("listaContactos", new Emitter.Listener() {
				  @Override
				  public void call(Object... args) {	
					lista.clear();
				    JSONObject obj = (JSONObject)args[0];
				    JSONArray ja = obj.getJSONArray("lista");				    
				   // obj.toJSONArray(ja);
				    //ArrayList<String> alumnos2=gson.;
					//Toast.makeText(MainActivity.this, "Hola", Toast.LENGTH_SHORT).show();
			           for(int i = 0; i < ja.length(); i++) {
			                try {
			                	JSONObject jo = ja.getJSONObject(i);			                	
			                    //String datos = ja.get(i).toString();
			                	String datos = jo.getString("contacto");
			                    lista.add(datos);
			                    runOnUiThread(new Runnable() {
			                        @Override
			                        public void run() {

			                   //stuff that updates ui
					                    ArrayAdapter<String> adaptador = new ArrayAdapter<String>(Contactos.this, android.R.layout.simple_list_item_1, lista);
					                    listado.setAdapter(adaptador);	
			                       }
			                   });
			                } catch (JSONException e) {
			                    e.printStackTrace();
			                }
			            }
			           
				    Log.i("lista","si");
				  }
				});
			
			JSONObject obj = new JSONObject();
			obj.put("nombre", contacto);
			socket.emit("listarContactos",obj);
			
		}
		catch(Exception e)
		{
			       //throw new Exception(e);
		}

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.menu_contactos, menu);
		return true;
	}
	
    @Override
	public boolean onOptionsItemSelected(MenuItem item) {
		//AdapterContextMenuInfo info = (AdapterContextMenuInfo)item.getMenuInfo();
		// posicion = info.position;
		switch(item.getItemId())
		{
			case R.id.nuevoContacto:
			{
				String nombrePrograma="nuevoContacto";
                Intent i = new Intent(nombrePrograma);
                i.putExtra("usuario", contacto);
                startActivity(i);
                finish();
			}
		}
		return false;
	}

}
