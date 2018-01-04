var express = require('express');
var http = require('http');
var app = express();
/*var engine = require('engine.io');
var engineServer = engine.listen(1338);*/
var server = http.createServer(app);
server.listen(1337);
var io = require('socket.io').listen(server);
var mysql = require('mysql');
var conexion = mysql.createConnection({
  host     : 'localhost',
  user     : 'root',
  password : '',
  database : 'chat_pablo',
});
conexion.connect(function(err)
	{
		if(err)
		{
			console.log(err);
			console.log('error');
		}
		else
		{
			console.log('bien');
		}
	}
)

/*conexion.query('SELECT 1 + 1 AS solution', function(err, rows, fields) {
  if (err) throw err;
  console.log('The solution is: ', rows[0].solution);
});*/


//conexion.end();

io.sockets.on("connection",function(socket)
{
	console.log("Hola");
	
	socket.on('login',function(datos)
	{
		var usuario = datos.nombre;
		var contrasena = datos.contrasena;
		console.log(datos.nombre);
		console.log(datos.contrasena);
		var consulta =  'select nombre from usuarios where nombre="'+usuario+'" && contrasena="'+contrasena+'"';
		console.log(consulta);
		conexion.query(consulta,function(err, rows,campos)
		{
			if(rows.length > 0)
			{
				console.log(rows);
				console.log('bien');
				socket.logueado = true;
				socket.emit("iniciado",{});
			}
			else
			{
				socket.emit('mensaje',{mensaje:"El nombre de usuario o la clave son incorrectas"});
			}
		});		
	});
	
	socket.on('verContactos',function(datos)
	{
		var usuario = datos.nombre;
		var contrasena = datos.contrasena;	
		conexion.query('select usuario from usuarios where nombre='+usuario+' && contrasena='+contrasena+'',function(err, rows)
		{
			if(rows)
			{
				console.log('bien');
				socket.logueado = true;
			}
		});		
	});
	
	socket.on('agregarContacto',function(datos)
	{
		var consulta =  'insert into contactos values("'+datos.contacto+'","'+datos.pertenencia+'",'+datos.tipo+')';
		var consulta2 =  'insert into contactos values("'+datos.pertenencia+'","'+datos.contacto+'",'+datos.tipo+')';
		var consulta3 = 'insert into sala(contacto1,contacto2) values("'+datos.contacto+'","'+datos.pertenencia+'")';
		console.log(consulta);
		console.log(consulta2);
		console.log(consulta3);
		conexion.query(consulta,function(error)
		{
			if(!error)
			{
				conexion.query(consulta2,function(error)
				{
					if(!error)
					{
						conexion.query(consulta3,function(error)
						{
							if(!error)
							{
								socket.emit('correcto',{});
							}
							else
							{
								console.log(error);
							}
						});
					}
					else
					{
						console.log(error);
					}
				});
			}
			else
			{
				console.log(error);
			}
		});	
	});
	
	socket.on('agregarGrupo',function(datos)
	{
		var consulta =  'insert into grupo values("'+datos.nombre+'","'+datos.propietario+'")';
		var consulta2 =  'insert into contactos values("'+datos.nombre+'","'+datos.propietario+'",2)';
		console.log(consulta);
		console.log(consulta2);
		conexion.query(consulta,function(error)
		{
			if(!error)
			{
				conexion.query(consulta2,function(error)
				{
					if(!error)
					{
					
						socket.emit('correcto',{});
					}
					else
					{
						console.log(error);
					}
				});
			}
			else
			{
				console.log(error);
			}
		});	
	});
	
	socket.on('listarContactos',function(datos)
	{
		var usuario = datos.nombre;
		//var contrasena = datos.contrasena;
		console.log(usuario);
		conexion.query('select contacto from contactos where pertenencia="'+usuario+'" && tipo = 1',function(err, rows)
		{
			if(err)
			{
				console.log('error '+err);
			}
			else
			{
				if(rows.length>0)
				{
					console.log('listaContactos');
					console.log(rows);
					socket.emit('listaContactos',{'lista':rows});
					socket.logueado = true;
				}
			}
		});		
	});
	
	socket.on('listarGrupos',function(datos)
	{
		var usuario = datos.nombre;
		//var contrasena = datos.contrasena;
		console.log(usuario);
		conexion.query('select contacto from contactos where pertenencia="'+usuario+'" && tipo = 2',function(err, rows)
		{
			if(err)
			{
				console.log('error '+err);
			}
			else
			{
				if(rows.length>0)
				{			
					console.log('listaGrupos');
					console.log(rows);
					socket.emit('listaGrupos',{'lista':rows});
					socket.logueado = true;
				}
			}
		});		
	});
	
	socket.on("unirmeAgrupo",function (datos)
	{
		grupo = datos.grupo;	
		console.log('uniendo a grupo '+grupo);
		socket.join(grupo);
		socket.sala = grupo;
		conexion.query('select mensaje from mensajes where para="'+grupo+'"',function(error,rows)
		{
			if(error)
			{
				console.log('error '+error);
			}
			else
			{
				if(rows.length > 0)
				{
					console.log('recibirMensajes');
					console.log(rows);					
					socket.emit('recibirMensajes',{'mensajes':rows});								
				}
			}
		});
	});
	
	socket.on("agregarMiembro",function (datos)
	{
		var consulta2 =  'insert into contactos values("'+datos.nombre+'","'+datos.pertenece+'",2)';
		conexion.query(consulta2,function(error)
		{
			if(!error)
			{
			
				socket.emit('correcto',{});
			}
			else
			{
				console.log(error);
			}
		});
	});
	
	socket.on("verPropietario",function(datos)
	{
		var consulta =  'select propietario from grupo where nombre ="'+datos.grupo+'"';
		conexion.query(consulta,function(error,rows)
		{
			if(!error)
			{
				console.log(rows[0].propietario);
				socket.emit('darPropietario',{propietario:rows[0].propietario});
			}
			else
			{
				console.log(error);
			}
		});	
	});
	
	socket.on("registro",function(datos)
	{
		console.log('registro');
		console.log(datos);
		var usuario = datos.nombre;
		var contrasena = datos.contrasena;
		//var rcontrasena = datos.rcontrasena;
		var consulta =  'insert into usuarios values("'+usuario+'","'+contrasena+'")';
		var consulta2 = 'select nombre from usuarios where nombre="'+usuario+'"' 
		conexion.query(consulta2,function(err,rows)
		{
			console.log(consulta2);
			if(rows.length > 0)
			{
				console.log("Ya existe");
				socket.emit('mensaje',{mensaje:"El nombre de usuario ya existe, por favor elija otro"});
			}
			else
			{
				conexion.query(consulta,function(err, resultado)
				{
					if(!err)
					{
						socket.emit('correcto',{mensaje:"Usuario registrado correctamente"});
					}
					else
					{
						console.log("error "+err);
						socket.emit('mensaje',{mensaje:"Error, no se ha podido insertar al usuario en la base de datos de la aplicación, por favor pruebe mas tarde"});
					}
				});
			}
		});		
	});
	
	socket.on("unirmeAsala",function (sala)
	{
		console.log('uniendo a sala');
		var usuario = sala.usuario;
		var contacto = sala.contacto;
		console.log(usuario);
		console.log(contacto);
		conexion.query('select numero from sala where contacto1="'+usuario+'" || contacto2="'+usuario+'" && contacto1="'+contacto+'" || contacto2="'+contacto+'"',function(err,rows)
		{
			if(err)
			{
				console.log('error '+err);				
			}
			else
			{
				if(rows.length>0)
				{
					console.log(rows[0].numero);
					socket.join(rows[0].numero);
					socket.sala = rows[0].numero;
					conexion.query('select mensaje from mensajes where de="'+usuario+'" || para="'+usuario+'" || de="'+contacto+'" || para ="'+contacto+'"',function(error,rows)
					{
						if(error)
						{
							console.log('error '+error);
						}
						else
						{
							if(rows.length > 0)
							{
								console.log('recibirMensajes');
								console.log(rows);
								socket.emit('recibirMensajes',{'mensajes':rows});								
							}
						}
					});
				}
			}
		});	
	});
	socket.on("enviarMensaje",function(datosMensaje)
	{
		var de = datosMensaje.de;
		var para = datosMensaje.para;
		var mensaje = datosMensaje.mensaje;
		conexion.query('insert into mensajes values("'+de+'","'+para+'","'+mensaje+'")',function(error,resultado)
		{
			if(error)
			{
				console.log('Error '+error);
			}
			else
			{
				socket.broadcast.to(socket.sala).emit('enviarMensaje',{mensaje:mensaje});
			}
		});
	})
	socket.on('message', function (msg)
	{
        //if ('echo' == msg) socket.send(msg);
		if(msg == hola)
		{
			console.log('login');
		}
    });
});

    /*cliente = new Cliente();  
	cliente.user = 'root';
	cliente.password = '';
	cliente.host='127.0.01';
	cliente.port='3306';
	cliente.database='chat_pablo'
	cliente.connect();*/
	