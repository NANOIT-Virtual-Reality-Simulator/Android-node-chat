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
import android.view.MenuItem;
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

public class SalaGrupos extends Activity 
{	

	/*TextView txtUsuario;
	TextView txtContrasena;*/
	Button boton;
	Socket socket;
	ListView listado;
    ArrayList<String> lista;
    String contacto;
    String usuario;
    String propietario;
    int tipo;
    TextView txtMensaje;
    ArrayAdapter<String> adaptador;
    boolean entrado = false;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		try
		{
			Bundle bundle = getIntent().getExtras();		
			contacto = bundle.getString("contacto");
			usuario = bundle.getString("usuario");
			propietario = "";
			//tipo = bundle.getInt("tipo");
			Log.i("recibido",contacto);
			setContentView(R.layout.sala_grupos);
			listado = (ListView)this.findViewById(R.id.listaMensajes);
			boton = (Button)this.findViewById(R.id.envio);
			txtMensaje = (TextView)this.findViewById(R.id.mensaje);
			lista = new ArrayList<String>();
            adaptador = new ArrayAdapter<String>(SalaGrupos.this, android.R.layout.simple_list_item_1, lista);
            listado.setAdapter(adaptador);	
			/*txtUsuario = (TextView)this.findViewById(R.id.txtUsuario);
			txtContrasena = (TextView)this.findViewById(R.id.txtContrasena);*/
			super.onCreate(savedInstanceState);		
			
			boton.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v)
				{
					/*try
					{*/
						Log.i("click", "Has cliqueado");
						CharSequence mensaje = txtMensaje.getText();						
						JSONObject obj = new JSONObject();
						obj.put("de", usuario);
						obj.put("para", contacto);
						obj.put("mensaje", mensaje);
						socket.emit("enviarMensaje",obj);
						adaptador.add(mensaje.toString());
						adaptador.notifyDataSetChanged();
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
			
			JSONObject obj = new JSONObject();
			
			/*if(tipo == 1)
			{				
				obj.put("usuario", usuario);
				obj.put("contacto", contacto);
				socket.emit("unirmeAsala",obj);
			}
			else
			{*/
			

			
			//}
			
			socket.on("recibirMensajes", new Emitter.Listener() {
				  @Override
				  public void call(Object... args) {					 
				    JSONObject obj = (JSONObject)args[0];
				    JSONArray ja = obj.getJSONArray("mensajes");			    
			           for(int i = 0; i < ja.length(); i++) {
			                try {
			                	JSONObject jo = ja.getJSONObject(i);			                	
			                	String datos = jo.getString("mensaje");
			                    lista.add(datos);
			                    runOnUiThread(new Runnable() {
			                        @Override
			                        public void run() 
			                        {
			                           	adaptador.notifyDataSetChanged();  
			                        }			                     
			                    });			                   
			                } catch (JSONException e) {
			                    e.printStackTrace();
			                }
			            }  
				    Log.i("lista","si");
				  }
				});
			
			socket.on("enviarMensaje", new Emitter.Listener() {
				  @Override
				  public void call(Object... args) {					 
				    JSONObject obj = (JSONObject)args[0];
				    //JSONArray ja = obj.getJSONArray("mensajes");			    
			          // for(int i = 0; i < ja.length(); i++) {
			                try {
			                	//JSONObject jo = ja.getJSONObject(i);			                	
			                	String datos = obj.getString("mensaje");
			                	Log.i("datos",datos);
			                    lista.add(datos);
			                    runOnUiThread(new Runnable() {
			                        @Override
			                        public void run() {
			                        	adaptador.notifyDataSetChanged();
			                       }			                  
			                   });			                   
			                } catch (JSONException e) {
			                    e.printStackTrace();
			                }
			            //}
			           
				    Log.i("lista","si");
				  }
				});
			
			socket.on("darPropietario", new Emitter.Listener() {
				  @Override
				  public void call(Object... args) {					 
				    JSONObject obj = (JSONObject)args[0];
				    //JSONArray ja = obj.getJSONArray("mensajes");			    
			          // for(int i = 0; i < ja.length(); i++) {
			                try {
			                	//JSONObject jo = ja.getJSONObject(i);			                	
			                	propietario = obj.getString("propietario");
			                    runOnUiThread(new Runnable() {
			                        @Override
			                        public void run() {
			                        	//adaptador.notifyDataSetChanged();
			                       }			                  
			                   });
			                   Log.i("propietario",propietario);
			                } catch (JSONException e) {
			                    e.printStackTrace();
			                }
			            //}
			           
				    Log.i("lista","si");
				  }
				});
			
			obj.put("grupo", contacto);				
			socket.emit("unirmeAgrupo",obj);
				
			JSONObject obj2 = new JSONObject();
			obj2.put("grupo", contacto);				
			socket.emit("verPropietario",obj);
			
			
			//socket.on("recibirMensaje, fn)		
		}
		catch(Exception e)
		{
			       //throw new Exception(e);
		}

	}

    @Override
    public boolean onPrepareOptionsMenu(Menu menu)
    { 
        //if(canAddItem){
            Log.i("propietario",propietario);
            Log.i("usuario",usuario);
    		MenuItem mi = menu.findItem(R.id.nuevoMiembro);
    		Log.i("menu",mi.toString());
        	if(propietario.equals(usuario))
        	{
        		Log.i("menu",mi.toString());
        		mi.setVisible(true);
                //mi.setShowAsActionFlags(MenuItem.SHOW_AS_ACTION_IF_ROOM);
                //canAddItem = false;  			              
        	}  
        	else
        	{
        		mi.setVisible(false);       		
        	}
        	entrado = true;
        	return super.onPrepareOptionsMenu(menu);
        	//return false;
    }
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
			getMenuInflater().inflate(R.menu.menu_sala_grupos, menu);		
		return true;
	}
	
    @Override
	public boolean onOptionsItemSelected(MenuItem item) {
		//AdapterContextMenuInfo info = (AdapterContextMenuInfo)item.getMenuInfo();
		// posicion = info.position;
    	if(!entrado)
    	{
    		invalidateOptionsMenu();    		
    	}
		switch(item.getItemId())
		{
			case R.id.nuevoMiembro:
			{
				String nombrePrograma="AgregarMiembro";
                Intent i = new Intent(nombrePrograma);
                i.putExtra("usuario", contacto);
                i.putExtra("contacto", usuario);
                startActivity(i);
                finish();
			}
		}
		return false;
	}

}
