-- One admin user, named admin1 with passwor 4dm1n and authority admin
INSERT INTO users(username,password,enabled) VALUES ('admin1','admin1',TRUE);
INSERT INTO authorities(id,username,authority) VALUES (1,'admin1','administrador');

INSERT INTO users(username,password,enabled) VALUES ('luctorgom','luctorgom',TRUE);
INSERT INTO authorities(id,username,authority) VALUES (4,'luctorgom','administrador');

INSERT INTO users(username,password,enabled) VALUES ('luctorgom1','luctorgom1',TRUE);
INSERT INTO authorities(id,username,authority) VALUES (15,'luctorgom1','cliente');

INSERT INTO users(username,password,enabled) VALUES ('raupargor','raupargor',TRUE);
INSERT INTO authorities(id,username,authority) VALUES (5,'raupargor','cliente');

INSERT INTO users(username,password,enabled) VALUES ('margarcac1','margarcac1',TRUE);
INSERT INTO authorities(id,username,authority) VALUES (6,'margarcac1','cliente');

INSERT INTO users(username,password,enabled) VALUES ('jesrolcad','jesrolcad',TRUE);
INSERT INTO authorities(id,username,authority) VALUES (7,'jesrolcad','cliente');

INSERT INTO users(username,password,enabled) VALUES ('serfiggom','serfiggom',TRUE);
INSERT INTO authorities(id,username,authority) VALUES (8, 'serfiggom','administrador');

INSERT INTO users(username,password,enabled) VALUES ('serfiggom1','serfiggom1',TRUE);
INSERT INTO authorities(id,username,authority) VALUES (10, 'serfiggom1','cliente');

INSERT INTO users(username,password,enabled) VALUES ('cliente1','cliente1',TRUE);
INSERT INTO authorities(id,username,authority) VALUES (13,'cliente1','cliente');

INSERT INTO users(username,password,enabled) VALUES ('pepinho','pepinho',TRUE);
INSERT INTO authorities(id,username,authority) VALUES (14,'pepinho','cliente');

INSERT INTO NIVEL_SOCIO(id, name) VALUES(3,'ORO');
INSERT INTO NIVEL_SOCIO(id, name) VALUES(2,'PLATA');
INSERT INTO NIVEL_SOCIO(id, name) VALUES(1,'BRONCE');
INSERT INTO NIVEL_SOCIO(id, name) VALUES(4,'No tiene nivel de socio');



INSERT INTO clientes(nombre,apellidos,fecha_nacimiento,telefono,email, usuario,fecha_alta, nivel_socio) 
VALUES ('María','García Cáceres','2000-01-01','654123987','margarcar@alum.us.es', 'margarcac1', '2020-01-02', 3);

INSERT INTO clientes(nombre,apellidos,fecha_nacimiento,telefono,email, usuario, fecha_alta, nivel_socio) 
VALUES ('Pepe','García Cáceres','2000-01-01','654123987','pepepalotes@gmail.com', 'pepinho', '2020-01-02', 3);

INSERT INTO clientes(nombre,apellidos,fecha_nacimiento,telefono,email, usuario, fecha_alta, nivel_socio) 
VALUES ('Servando','Figueroa Gómez','2000-08-12','698745213','serfiggom@alum.us.es', 'serfiggom1', '2020-01-02', 3);

INSERT INTO clientes(nombre,apellidos,fecha_nacimiento,telefono,email, usuario, fecha_alta, nivel_socio) 
VALUES ('Raúl','Parrado Gordón','2000-05-29','717548963','raupargor@alum.us.es', 'raupargor', '2020-01-02', 3);

INSERT INTO clientes(nombre,apellidos,fecha_nacimiento,telefono,email, usuario, fecha_alta, nivel_socio) 
VALUES ('Álvaro','Sánchez González','2000-01-30','687452196','alvsangon@alum.us.es', 'cliente1', '2020-01-02', 3);

INSERT INTO clientes(nombre,apellidos,fecha_nacimiento,telefono,email, usuario, fecha_alta, nivel_socio) 
VALUES ('Jesús','Roldán Cadena','2000-08-07','632145879','jesrolcad@alum.us.es', 'jesrolcad', '2020-01-02', 3);


INSERT INTO clientes(nombre,apellidos,fecha_nacimiento,telefono,email, usuario) 
VALUES ('Lucía','Torres Gómez','2000-03-30','614589725','luctorgom@alum.us.es', 'luctorgom1');


/*cocinero util */
INSERT INTO users(username,password,enabled) VALUES ('cocinero1','cocinero1',TRUE);
INSERT INTO authorities(id,username,authority) VALUES (11,'cocinero1','cocinero');

/*repartidor util*/
INSERT INTO users(username,password,enabled) VALUES ('repartidor1','repartidor1',TRUE);
INSERT INTO authorities(id,username,authority) VALUES (12,'repartidor1','repartidor');


INSERT INTO cocineros(nombre,apellidos,fecha_nacimiento,telefono,email, fecha_inicio_contrato, usuario) 
VALUES ('Paco','Pérez Maldonado','1998-12-29','777777777','paquitorechulon@gmail.com', '2020-10-01', 'cocinero1');

/*INSERT INTO cocineros(nombre,apellidos,fecha_nacimiento,telefono,email,usuario, fecha_inicio_contrato) 
VALUES ('Marmona','Jimenez Ronaldinha','1997-12-29','777555555','mariana@gmail.com','ejemploCocinero', '2020-09-30');*/


/*DATETIME fecha_actual = DATETIME.NOW() -> No funciona*/
INSERT INTO repartidores(nombre,apellidos,fecha_nacimiento,telefono,email, fecha_inicio_contrato, usuario)
VALUES ('Minguito','Gutiérrez Ronaldo','1998-11-03','682547321','minguitoo@gmail.com', '2019-12-12', 'repartidor1');

/*INSERT INTO repartidores(nombre,apellidos,fecha_nacimiento,telefono,email, fecha_inicio_contrato, usuario)
VALUES ('Pepa','Cansado Levante','1995-09-13','985432158','cansado_levante@gmail.com', '2020-11-22', 'ejemplo1');*/


INSERT INTO TIPO_PAGO(id, name) VALUES(2,'EFECTIVO');
INSERT INTO TIPO_PAGO(id, name) VALUES(1,'TARJETA');

INSERT INTO ESTADO_PEDIDO(id, name)VALUES(1,'EN COCINA');
INSERT INTO ESTADO_PEDIDO(id, name) VALUES(2,'PREPARADO');
INSERT INTO ESTADO_PEDIDO(id, name) VALUES(3,'EN REPARTO');
INSERT INTO ESTADO_PEDIDO(id, name) VALUES(4,'ENTREGADO');
INSERT INTO ESTADO_PEDIDO(id, name) VALUES(5,'RECOGIDO');

INSERT INTO TIPO_ENVIO(id, name) VALUES(2,'DOMICILIO');
INSERT INTO TIPO_ENVIO(id, name) VALUES(1,'RECOGER EN TIENDA');


INSERT INTO administradores(nombre,apellidos,fecha_nacimiento,telefono,email, usuario)
VALUES ('Juan','Pérez Ruíz','1990-01-01','863838343','perez_ruiz@gmail.com', 'admin1');

INSERT INTO administradores(nombre,apellidos,fecha_nacimiento,telefono,email, usuario)
VALUES ('Servando','Figueroa','1990-01-01','863838343','perez_ruiz@gmail.com', 'serfiggom');

INSERT INTO administradores(nombre,apellidos,fecha_nacimiento,telefono,email, usuario)
VALUES ('Lucia','Torres','2000-01-01','654789654','luciatg30@gmail.com', 'luctorgom');


INSERT INTO TIPO_RESERVA(id, name) VALUES(1,'ALMUERZO');
INSERT INTO TIPO_RESERVA(id, name) VALUES(2,'CENA');

INSERT INTO RESERVAS(id, fecha_reserva, hora_reserva, numero_personas, tipo_reserva) VALUES(1,'2020-05-29','10:34:09',5,1);
INSERT INTO RESERVAS(id, fecha_reserva, hora_reserva, numero_personas, tipo_reserva) VALUES(2,'2020-07-12','20:34:09',2,1); 
INSERT INTO RESERVAS(id, fecha_reserva, hora_reserva, numero_personas, tipo_reserva) VALUES(3,'2020-05-29','10:34:09',5,1);
INSERT INTO RESERVAS(id, fecha_reserva, hora_reserva, numero_personas, tipo_reserva) VALUES(4,'2020-07-12','20:34:09',2,1);
INSERT INTO RESERVAS(id, fecha_reserva, hora_reserva, numero_personas, tipo_reserva) VALUES(5,'2020-05-29','10:34:09',5,2);
INSERT INTO RESERVAS(id, fecha_reserva, hora_reserva, numero_personas, tipo_reserva) VALUES(6,'2020-07-12','20:34:09',2,2);
INSERT INTO RESERVAS(id, fecha_reserva, hora_reserva, numero_personas, tipo_reserva) VALUES(7,'2020-05-29','10:34:09',5,2);
INSERT INTO RESERVAS(id, fecha_reserva, hora_reserva, numero_personas, tipo_reserva) VALUES(8,'2020-07-12','20:34:09',2,2);
INSERT INTO RESERVAS(id, fecha_reserva, hora_reserva, numero_personas, tipo_reserva) VALUES(9,'2020-05-29','10:34:09',5,2);
INSERT INTO RESERVAS(id, fecha_reserva, hora_reserva, numero_personas, tipo_reserva) VALUES(10,'2020-07-12','20:34:09',2,2);


INSERT INTO RECLAMACIONES(observacion, respuesta)
VALUES('Había un hueso de aceituna en mi pizza. ¿Qué tipo de broma es esta?', 'Lo sentimos mucho');

INSERT INTO RECLAMACIONES(observacion, respuesta)
VALUES('Mi pizza carbonara llevaba 1 sola unidad de champiñón.', '');

INSERT INTO MESAS VALUES(1,6,1);
INSERT INTO MESAS VALUES(2,6,1);
INSERT INTO MESAS VALUES(3,6,1);
INSERT INTO MESAS VALUES(4,6,1);
INSERT INTO MESAS VALUES(5,6,1);
INSERT INTO MESAS VALUES(6,6,1);
INSERT INTO MESAS VALUES(7,6,1);
INSERT INTO MESAS VALUES(8,5,1);


INSERT INTO RESERVA_MESA (RESERVA_ID, MESAS_EN_RESERVA_ID) VALUES (1,2);
INSERT INTO RESERVA_MESA (RESERVA_ID, MESAS_EN_RESERVA_ID) VALUES (2,1);
INSERT INTO RESERVA_MESA (RESERVA_ID, MESAS_EN_RESERVA_ID) VALUES (3,3);
INSERT INTO RESERVA_MESA (RESERVA_ID, MESAS_EN_RESERVA_ID) VALUES (4,4);
INSERT INTO RESERVA_MESA (RESERVA_ID, MESAS_EN_RESERVA_ID) VALUES (5,7);
INSERT INTO RESERVA_MESA (RESERVA_ID, MESAS_EN_RESERVA_ID) VALUES (6,6);
INSERT INTO RESERVA_MESA (RESERVA_ID, MESAS_EN_RESERVA_ID) VALUES (8,2);
INSERT INTO RESERVA_MESA (RESERVA_ID, MESAS_EN_RESERVA_ID) VALUES (10,8);
INSERT INTO RESERVA_MESA (RESERVA_ID, MESAS_EN_RESERVA_ID) VALUES (7,5);

INSERT INTO TAMANO_PRODUCTO(id, name) VALUES(1,'NORMAL');
INSERT INTO TAMANO_PRODUCTO(id, name) VALUES(2,'GRANDE');

INSERT INTO TAMANO_OFERTA(id, name) VALUES(1,'NORMAL');
INSERT INTO TAMANO_OFERTA(id, name) VALUES(3,'GRANDE');


INSERT INTO OFERTAS(NAME,ESTADO_OFERTA,TAMANO_OFERTA,COSTE,FECHA_INICIAL,NIVEL_SOCIO,FECHA_FINAL) values
('4 QUESOS POR 5.90€',false,1,5.90,'2020-01-05',3,'2020-01-31');
INSERT INTO OFERTAS(NAME,ESTADO_OFERTA,TAMANO_OFERTA,COSTE,FECHA_INICIAL,NIVEL_SOCIO,FECHA_FINAL) values
('LAS 4 FAVORITAS CON 4 BEBIDAS + COMPLEMENTO 60.60€',true,1,60.6,'2020-02-05',2,'2020-02-28');
INSERT INTO OFERTAS(NAME,ESTADO_OFERTA,TAMANO_OFERTA,COSTE,FECHA_INICIAL,NIVEL_SOCIO,FECHA_FINAL) values
('Chicken wings a 15.8€',true,1,15.8,'2020-03-05',1,'2020-03-31');
INSERT INTO OFERTAS(NAME,ESTADO_OFERTA,TAMANO_OFERTA,COSTE,FECHA_INICIAL,NIVEL_SOCIO,FECHA_FINAL) values
('BBQ Y TONNATA 9.90€',true,1,9.9,'2020-04-05',3,'2022-04-30');
INSERT INTO OFERTAS(NAME,ESTADO_OFERTA,TAMANO_OFERTA,COSTE,FECHA_INICIAL,NIVEL_SOCIO,FECHA_FINAL) values
('2 HAWAIANAS 5€ CADA UNA',false,1,10,'2020-04-05',3,'2025-04-30');

INSERT INTO pedido(precio,gastos_envio,direccion,fecha_pedido,estado_pedido, tipo_pago, tipo_envio, pedidocliente)
VALUES ('30','3.5','C/Ferrara, 5, 7b', '2020-11-15', 2, 1,1, '1');
INSERT INTO pedido(precio,gastos_envio,direccion,fecha_pedido,estado_pedido, tipo_pago, tipo_envio,pedidocliente)
VALUES ('29','3','C/Ferrara, 5, 7b', '2020-11-16',3,1,1, '1');
INSERT INTO pedido(precio,gastos_envio,direccion,fecha_pedido,estado_pedido, tipo_pago, tipo_envio,pedidocliente)
VALUES ('50','2.30','C/Ferrara, 5, 7b', '2020-11-18',2,1,1,'2');
INSERT INTO pedido(precio,gastos_envio,direccion,fecha_pedido,estado_pedido, tipo_pago, tipo_envio,pedidocliente)
VALUES ('57','2','C/Ferrara, 5, 7b', '2020-11-30',3,1,2, '3');
INSERT INTO pedido(precio,gastos_envio,direccion,fecha_pedido,estado_pedido, tipo_pago, tipo_envio,pedidocliente)
VALUES ('57','2','C/Ferrara, 5, 7b', '2020-11-30',3,1,2, '3');
INSERT INTO pedido(precio,gastos_envio,direccion,fecha_pedido,estado_pedido, tipo_pago, tipo_envio, pedidocliente)
VALUES ('200','3.5','C/Ferrara, 5, 7b', '2020-11-15', 2, 1,1, '2');

INSERT INTO OFERTA_PEDIDO(PEDIDO_ID, OFERTAS_EN_PEDIDO_ID) values (1, 1);
INSERT INTO OFERTA_PEDIDO(PEDIDO_ID, OFERTAS_EN_PEDIDO_ID) values (1, 2); 
INSERT INTO OFERTA_PEDIDO(PEDIDO_ID, OFERTAS_EN_PEDIDO_ID) values (4, 3); 

INSERT INTO TIPO_MASA(id, name) VALUES(1,'FINA');
INSERT INTO TIPO_MASA(id, name) VALUES(3,'GRUESA');
INSERT INTO TIPO_MASA(id, name) VALUES(2,'RELLENA');

/*INSERT INTO TAMAÑO VALUES(1,'GRANDE');
INSERT INTO TAMAÑO VALUES(3,'PEQUEÑA');
INSERT INTO TAMAÑO VALUES(2,'MEDIANA');*/

INSERT INTO Alergenos(id,name) VALUES(1,'Crustaceos y productos a base de crustaceos');
INSERT INTO Alergenos(id,name) VALUES(2,'Cereales que contengan gluten');
INSERT INTO Alergenos(id,name) VALUES(3,'Huevos y productos a base de huevo');
INSERT INTO Alergenos(id,name) VALUES(4,'Pescado y productos a base de pescado');
INSERT INTO Alergenos(id,name) VALUES(5,'Cacahuetes y productos a base de cacahuetes');
INSERT INTO Alergenos(id,name) VALUES(6,'Soja y productos a base de soja');
INSERT INTO Alergenos(id,name) VALUES(7,'Leche y sus derivados');
INSERT INTO Alergenos(id,name) VALUES(8,'Frutos de cascara');
INSERT INTO Alergenos(id,name) VALUES(9,'Apio y productos derivados');
INSERT INTO Alergenos(id,name) VALUES(10,'Mostaza y productos derivados');
INSERT INTO Alergenos(id,name) VALUES(11,'Granos de sesamo');
INSERT INTO Alergenos(id,name) VALUES(12,'Dioxido de azufre y sulfitos');
INSERT INTO Alergenos(id,name) VALUES(13,'Altramuces y productos a base de altramuces');
INSERT INTO Alergenos(id,name) VALUES(14,'Moluscos y productos a base de moluscos');

INSERT INTO INGREDIENTE (fecha_caducidad, nombre, tipo, alergenos) values ( '2021-01-01' ,'Pan', 'Rico en carbohidratos', 2); 
INSERT INTO INGREDIENTE (fecha_caducidad, nombre, tipo, alergenos) values ('2021-02-02' ,'Queso', 'Rico en proteinas', 7);
INSERT INTO INGREDIENTE (fecha_caducidad, nombre, tipo) values ('2021-03-03' , 'Tomate', 'Verduras y frutas');
INSERT INTO INGREDIENTE (fecha_caducidad, nombre, tipo, alergenos) values ('2021-05-05' ,'Huevo', 'Rico en proteinas', 3);
INSERT INTO INGREDIENTE (fecha_caducidad, nombre, tipo, alergenos) values ( '2021-06-06' , 'Soja', 'Verduras y frutas', 6);
INSERT INTO INGREDIENTE (fecha_caducidad, nombre, tipo) values ( '2021-01-01' ,'Salami', 'Carnes, pescados y huevos'); 
INSERT INTO INGREDIENTE (fecha_caducidad, nombre, tipo) values ('2021-02-02' ,'Jamón York', 'Carnes, pescados y huevos');
INSERT INTO INGREDIENTE (fecha_caducidad, nombre, tipo) values ('2021-02-02' ,'Pollo kebab', 'Carnes, pescados y huevos');
INSERT INTO INGREDIENTE (fecha_caducidad, nombre, tipo) values ('2021-02-02' ,'Pollo', 'Carnes, pescados y huevos');
INSERT INTO INGREDIENTE (fecha_caducidad, nombre, tipo) values ('2021-02-02' ,'Ternera', 'Carnes, pescados y huevos');
INSERT INTO INGREDIENTE (fecha_caducidad, nombre, tipo) values ('2021-02-02' ,'Ternera kebab', 'Carnes, pescados y huevos');
INSERT INTO INGREDIENTE (fecha_caducidad, nombre, tipo, alergenos) values ('2021-03-03' , 'Atún', 'Carnes, pescados y huevos', 4);
INSERT INTO INGREDIENTE (fecha_caducidad, nombre, tipo, alergenos) values ('2021-04-04' ,'Anchoas', 'Rico en proteinas', 4);
INSERT INTO INGREDIENTE (fecha_caducidad, nombre, tipo, alergenos) values ('2021-03-03' , 'Merluza', 'Carnes, pescados y huevos', 4);
INSERT INTO INGREDIENTE (fecha_caducidad, nombre, tipo, alergenos) values ( '2021-06-06' , 'Huevo', 'Huevo', 3);
INSERT INTO INGREDIENTE (fecha_caducidad, nombre, tipo, alergenos) values ('2021-02-02' ,'Gorgonzola', 'Rico en proteinas', 7);
INSERT INTO INGREDIENTE (fecha_caducidad, nombre, tipo, alergenos) values ('2021-02-02' ,'Camembert', 'Rico en proteinas', 7);
INSERT INTO INGREDIENTE (fecha_caducidad, nombre, tipo, alergenos) values ('2021-02-02' ,'Roquefort', 'Rico en proteinas', 7);
INSERT INTO INGREDIENTE (fecha_caducidad, nombre, tipo, alergenos) values ('2021-02-02' ,'Emmental', 'Rico en proteinas', 7);
INSERT INTO INGREDIENTE (fecha_caducidad, nombre, tipo, alergenos) values ('2021-02-02' ,'Gouda', 'Rico en proteinas', 7);
INSERT INTO INGREDIENTE (fecha_caducidad, nombre, tipo) values ('2021-03-03' , 'Pepino', 'Verduras y frutas');
INSERT INTO INGREDIENTE (fecha_caducidad, nombre, tipo) values ('2021-03-03' , 'Rúcula', 'Verduras y frutas');
INSERT INTO INGREDIENTE (fecha_caducidad, nombre, tipo) values ('2021-03-03' , 'Berenjena', 'Verduras y frutas');
INSERT INTO INGREDIENTE (fecha_caducidad, nombre, tipo) values ('2021-03-03' , 'Rúcula', 'Verduras y frutas');
INSERT INTO INGREDIENTE (fecha_caducidad, nombre, tipo) values ('2021-03-03' , 'Calabacín', 'Verduras y frutas');
INSERT INTO INGREDIENTE (fecha_caducidad, nombre, tipo) values ('2021-03-03' , 'Calabaza', 'Verduras y frutas');
INSERT INTO INGREDIENTE (fecha_caducidad, nombre, tipo) values ('2021-03-03' , 'Pimiento verde', 'Verduras y frutas');
INSERT INTO INGREDIENTE (fecha_caducidad, nombre, tipo) values ('2021-03-03' , 'Pimiento rojo', 'Verduras y frutas');
INSERT INTO INGREDIENTE (fecha_caducidad, nombre, tipo) values ('2021-03-03' , 'Pimiento amarillo', 'Verduras y frutas');
INSERT INTO INGREDIENTE (fecha_caducidad, nombre, tipo) values ('2021-03-03' , 'Patatas', 'Verduras y frutas');
INSERT INTO INGREDIENTE (fecha_caducidad, nombre, tipo, alergenos) values ('2021-04-04' ,'Barbacoa', 'Rico en proteinas', 10);
INSERT INTO INGREDIENTE (fecha_caducidad, nombre, tipo, alergenos) values ('2021-05-05' ,'Salsa de yogurt', 'Rico en proteinas', 7);
INSERT INTO INGREDIENTE (fecha_caducidad, nombre, tipo, alergenos) values ('2021-04-04' ,'Salsa Belouté', 'Rico en proteinas', 8);
INSERT INTO INGREDIENTE (fecha_caducidad, nombre, tipo, alergenos) values ('2021-05-05' ,'Salsa holandesa', 'Rico en proteinas', 3);
INSERT INTO INGREDIENTE (fecha_caducidad, nombre, tipo, alergenos) values ('2021-04-04' ,'Salsa española', 'Rico en proteinas', 5);
INSERT INTO INGREDIENTE (fecha_caducidad, nombre, tipo, alergenos) values ('2021-05-05' ,'Mayonesa', 'Rico en proteinas', 3);




INSERT INTO PIZZAS(id, coste, nombre, tamano_producto, tipo_masa, cliente_id, personalizada) values (1, 20, 'PROSCIUTTO E FUNGHI', 1 ,2, null, false);
INSERT INTO PIZZAS(id, coste, nombre, tamano_producto, tipo_masa, cliente_id, personalizada) values (2, 22, 'PROSCIUTTO', 2 ,3, null, false);
INSERT INTO PIZZAS(id, coste, nombre, tamano_producto, tipo_masa, cliente_id, personalizada) values (3, 10, 'HAWAIANA', 2 ,1, null, false);
INSERT INTO PIZZAS(id, coste, nombre, tamano_producto, tipo_masa, cliente_id, personalizada) values (4, 15, 'DIAVOLA', 1 ,3, null, false);
INSERT INTO PIZZAS(id, coste, nombre, tamano_producto, tipo_masa, cliente_id, personalizada) values (5, 19, '4 STAGIONI', 1 ,1, null, false);
INSERT INTO PIZZAS(id, coste, nombre, tamano_producto, tipo_masa, cliente_id, personalizada) values (6, 22, 'TONNATA', 2 ,3, null, false);
INSERT INTO PIZZAS(id, coste, nombre, tamano_producto, tipo_masa, cliente_id, personalizada) values (7, 10, '4 FORMAGGI ', 2 ,1, null, false);
INSERT INTO PIZZAS(id, coste, nombre, tamano_producto, tipo_masa, cliente_id, personalizada) values (8, 15, 'POMODORINI', 1 ,3, null, false);
INSERT INTO PIZZAS(id, coste, nombre, tamano_producto, tipo_masa, cliente_id, personalizada) values (9, 19, 'BARBACOA', 1 ,1, null, false);


INSERT INTO OTROS(coste,nombre) values (10, 'Patatas bravas');
INSERT INTO OTROS(coste,nombre) values (22, 'Papitas Rellenas con Carne Molida');
INSERT INTO OTROS(coste,nombre) values (10, 'papas aliñás');
INSERT INTO OTROS(coste,nombre) values (11, 'Gazpacho');
INSERT INTO OTROS(coste,nombre) values (15, 'Salmorejo');
INSERT INTO OTROS(coste,nombre) values (19, 'Pescaíto frito');
INSERT INTO OTROS(coste,nombre) values (2, 'Aceitunas');
INSERT INTO OTROS(coste,nombre) values (22, 'Boquerones en vinagre');
INSERT INTO OTROS(coste,nombre) values (10, 'Tortillita de camarones');
INSERT INTO OTROS(coste,nombre) values (15, 'Adobos de pescado');
INSERT INTO OTROS(coste,nombre) values (19, 'Chicken Wings');
INSERT INTO OTROS(coste,nombre) values (15, 'Bacalao deshebrado');
INSERT INTO OTROS(coste,nombre) values (22, 'Huevos tontos');
INSERT INTO OTROS(coste,nombre) values (11, 'Pastel de pescado');
INSERT INTO OTROS(coste,nombre) values (15, 'Chipirones afogaos');
INSERT INTO OTROS(coste,nombre) values (15, 'Adobos de pescado');
INSERT INTO OTROS(coste,nombre) values (19, 'Ortiguillas');
INSERT INTO OTROS(coste,nombre) values (2, 'Bollos preñaos');
INSERT INTO OTROS(coste,nombre) values (2, 'Chorizo de untar');
INSERT INTO OTROS(coste,nombre) values (10, 'Papas arrugadas');
INSERT INTO OTROS(coste,nombre) values (15, 'Morcilla dulce');
INSERT INTO OTROS(coste,nombre) values (9, 'Berenjenas de Almagro');
/*INSERT INTO OTROS values ( 1, 20, 'patatas bravas', 5);
INSERT INTO OTROS values ( 1, 22, 'Spaghetti con salsa de tomate casera',	2);
INSERT INTO OTROS values ( 1, 10, 'crema catalana',1);
INSERT INTO OTROS values ( 1, 15, 'tiramisú',	4);
INSERT INTO OTROS values ( 2, 19, 'brownie',	3);*/


INSERT INTO BEBIDAS(coste,nombre,es_carbonatada, tamano_producto) values (1,'Nestea', false ,1);
INSERT INTO BEBIDAS(coste,nombre,es_carbonatada, tamano_producto) values (1,'Fanta naranja', false ,1);
INSERT INTO BEBIDAS(coste,nombre,es_carbonatada, tamano_producto) values (1,'Fanta limon', true ,2);
INSERT INTO BEBIDAS(coste,nombre,es_carbonatada, tamano_producto) values (2,'Agua', false ,1);
INSERT INTO BEBIDAS(coste,nombre,es_carbonatada, tamano_producto) values (1,'Coca-cola', true ,2);
INSERT INTO BEBIDAS(coste,nombre,es_carbonatada, tamano_producto) values ( 1, 'Coca-cola Zero azúcar y Zero cafeína', true ,2);
INSERT INTO BEBIDAS(coste,nombre,es_carbonatada, tamano_producto) values (1, 'Coca-cola Sabor light', true ,2);
INSERT INTO BEBIDAS(coste,nombre,es_carbonatada, tamano_producto) values ( 1, 'Coca-cola Zero azúcar', true ,2);
INSERT INTO BEBIDAS(coste,nombre,es_carbonatada, tamano_producto) values ( 1, 'Sprite', true ,1);
INSERT INTO BEBIDAS(coste,nombre,es_carbonatada, tamano_producto) values ( 1, 'Powerade', false ,1);
INSERT INTO BEBIDAS(coste,nombre,es_carbonatada, tamano_producto) values ( 1, 'Aquarius', true ,2);
INSERT INTO BEBIDAS(coste,nombre,es_carbonatada, tamano_producto) values ( 2, 'Schweppes', true ,1);

/*NO ME TOQUEIS LAS FECHAS DE CARTAS*/
INSERT INTO CARTAS(nombre, fecha_Creacion, fecha_Final) values ('CartaPrincipal', '2021-01-01', '2021-12-31');
INSERT INTO CARTAS(nombre, fecha_Creacion, fecha_Final) values ('CartaPrueba','2022-01-01', '2022-12-31');

INSERT INTO COMPOSICION_CARTA_PIZZA(PIZZAS_EN_CARTA_ID, CARTA_ID) VALUES (1,1);
INSERT INTO COMPOSICION_CARTA_PIZZA(PIZZAS_EN_CARTA_ID, CARTA_ID) VALUES (2,1);
INSERT INTO COMPOSICION_CARTA_PIZZA(PIZZAS_EN_CARTA_ID, CARTA_ID) VALUES (3,1);
INSERT INTO COMPOSICION_CARTA_PIZZA(PIZZAS_EN_CARTA_ID, CARTA_ID) VALUES (4,1);
INSERT INTO COMPOSICION_CARTA_PIZZA(PIZZAS_EN_CARTA_ID, CARTA_ID) VALUES (1,2);
INSERT INTO COMPOSICION_CARTA_PIZZA(PIZZAS_EN_CARTA_ID, CARTA_ID) VALUES (3,2);


INSERT INTO COMPOSICION_CARTA_BEBIDA(CARTA_ID, BEBIDAS_EN_CARTA_ID) VALUES (1,1);
INSERT INTO COMPOSICION_CARTA_BEBIDA(CARTA_ID, BEBIDAS_EN_CARTA_ID) VALUES (2,1);
INSERT INTO COMPOSICION_CARTA_BEBIDA(CARTA_ID, BEBIDAS_EN_CARTA_ID) VALUES (1,5);
INSERT INTO COMPOSICION_CARTA_BEBIDA(CARTA_ID, BEBIDAS_EN_CARTA_ID) VALUES (1,2);

INSERT INTO COMPOSICION_CARTA_OTROS(CARTA_ID, OTROS_EN_CARTA_ID) VALUES (1,2);
INSERT INTO COMPOSICION_CARTA_OTROS(CARTA_ID, OTROS_EN_CARTA_ID) VALUES (1,4);
INSERT INTO COMPOSICION_CARTA_OTROS(CARTA_ID, OTROS_EN_CARTA_ID) VALUES (2,1);

INSERT INTO OTROS_INGREDIENTES(OTRO_ID,INGREDIENTES_ID) VALUES (1,30);
INSERT INTO OTROS_INGREDIENTES(OTRO_ID,INGREDIENTES_ID) VALUES (2,30);
INSERT INTO OTROS_INGREDIENTES(OTRO_ID,INGREDIENTES_ID) VALUES (3,30);
INSERT INTO OTROS_INGREDIENTES(OTRO_ID,INGREDIENTES_ID) VALUES (4,21);
INSERT INTO OTROS_INGREDIENTES(OTRO_ID,INGREDIENTES_ID) VALUES (4,3);
INSERT INTO OTROS_INGREDIENTES(OTRO_ID,INGREDIENTES_ID) VALUES (5,21);
INSERT INTO OTROS_INGREDIENTES(OTRO_ID,INGREDIENTES_ID) VALUES (5,21);
INSERT INTO OTROS_INGREDIENTES(OTRO_ID,INGREDIENTES_ID) VALUES (6,14);
INSERT INTO OTROS_INGREDIENTES(OTRO_ID,INGREDIENTES_ID) VALUES (8,34);
INSERT INTO OTROS_INGREDIENTES(OTRO_ID,INGREDIENTES_ID) VALUES (9,12);
INSERT INTO OTROS_INGREDIENTES(OTRO_ID,INGREDIENTES_ID) VALUES (9,13);
INSERT INTO OTROS_INGREDIENTES(OTRO_ID,INGREDIENTES_ID) VALUES (10,12);
INSERT INTO OTROS_INGREDIENTES(OTRO_ID,INGREDIENTES_ID) VALUES (10,35);
INSERT INTO OTROS_INGREDIENTES(OTRO_ID,INGREDIENTES_ID) VALUES (11,9);
INSERT INTO OTROS_INGREDIENTES(OTRO_ID,INGREDIENTES_ID) VALUES (12,27);
INSERT INTO OTROS_INGREDIENTES(OTRO_ID,INGREDIENTES_ID) VALUES (12,28);
INSERT INTO OTROS_INGREDIENTES(OTRO_ID,INGREDIENTES_ID) VALUES (13,4);
INSERT INTO OTROS_INGREDIENTES(OTRO_ID,INGREDIENTES_ID) VALUES (14,12);
INSERT INTO OTROS_INGREDIENTES(OTRO_ID,INGREDIENTES_ID) VALUES (14,14);
INSERT INTO OTROS_INGREDIENTES(OTRO_ID,INGREDIENTES_ID) VALUES (15,14);
INSERT INTO OTROS_INGREDIENTES(OTRO_ID,INGREDIENTES_ID) VALUES (16,14);
INSERT INTO OTROS_INGREDIENTES(OTRO_ID,INGREDIENTES_ID) VALUES (17,6);
INSERT INTO OTROS_INGREDIENTES(OTRO_ID,INGREDIENTES_ID) VALUES (18,25);
INSERT INTO OTROS_INGREDIENTES(OTRO_ID,INGREDIENTES_ID) VALUES (19,17);
INSERT INTO OTROS_INGREDIENTES(OTRO_ID,INGREDIENTES_ID) VALUES (20,30);
INSERT INTO OTROS_INGREDIENTES(OTRO_ID,INGREDIENTES_ID) VALUES (21,16);
INSERT INTO OTROS_INGREDIENTES(OTRO_ID,INGREDIENTES_ID) VALUES (22,23);


INSERT INTO PIZZAS_INGREDIENTES(PIZZA_ID,INGREDIENTES_ID) VALUES (1,1);
INSERT INTO PIZZAS_INGREDIENTES(PIZZA_ID,INGREDIENTES_ID) VALUES (1,2);
INSERT INTO PIZZAS_INGREDIENTES(PIZZA_ID,INGREDIENTES_ID) VALUES (1,3);
INSERT INTO PIZZAS_INGREDIENTES(PIZZA_ID,INGREDIENTES_ID) VALUES (1,4);
INSERT INTO PIZZAS_INGREDIENTES(PIZZA_ID,INGREDIENTES_ID) VALUES (2,1);
INSERT INTO PIZZAS_INGREDIENTES(PIZZA_ID,INGREDIENTES_ID) VALUES (2,2);
INSERT INTO PIZZAS_INGREDIENTES(PIZZA_ID,INGREDIENTES_ID) VALUES (2,3);
INSERT INTO PIZZAS_INGREDIENTES(PIZZA_ID,INGREDIENTES_ID) VALUES (2,4);
INSERT INTO PIZZAS_INGREDIENTES(PIZZA_ID,INGREDIENTES_ID) VALUES (3,1);
INSERT INTO PIZZAS_INGREDIENTES(PIZZA_ID,INGREDIENTES_ID) VALUES (3,2);
INSERT INTO PIZZAS_INGREDIENTES(PIZZA_ID,INGREDIENTES_ID) VALUES (3,3);
INSERT INTO PIZZAS_INGREDIENTES(PIZZA_ID,INGREDIENTES_ID) VALUES (3,4);
INSERT INTO PIZZAS_INGREDIENTES(PIZZA_ID,INGREDIENTES_ID) VALUES (3,21);
INSERT INTO PIZZAS_INGREDIENTES(PIZZA_ID,INGREDIENTES_ID) VALUES (4,1);
INSERT INTO PIZZAS_INGREDIENTES(PIZZA_ID,INGREDIENTES_ID) VALUES (4,2);
INSERT INTO PIZZAS_INGREDIENTES(PIZZA_ID,INGREDIENTES_ID) VALUES (4,3);
INSERT INTO PIZZAS_INGREDIENTES(PIZZA_ID,INGREDIENTES_ID) VALUES (4,4);
INSERT INTO PIZZAS_INGREDIENTES(PIZZA_ID,INGREDIENTES_ID) VALUES (4,15);
INSERT INTO PIZZAS_INGREDIENTES(PIZZA_ID,INGREDIENTES_ID) VALUES (5,1);
INSERT INTO PIZZAS_INGREDIENTES(PIZZA_ID,INGREDIENTES_ID) VALUES (5,2);
INSERT INTO PIZZAS_INGREDIENTES(PIZZA_ID,INGREDIENTES_ID) VALUES (5,3);
INSERT INTO PIZZAS_INGREDIENTES(PIZZA_ID,INGREDIENTES_ID) VALUES (5,4);
INSERT INTO PIZZAS_INGREDIENTES(PIZZA_ID,INGREDIENTES_ID) VALUES (5,9);
INSERT INTO PIZZAS_INGREDIENTES(PIZZA_ID,INGREDIENTES_ID) VALUES (6,1);
INSERT INTO PIZZAS_INGREDIENTES(PIZZA_ID,INGREDIENTES_ID) VALUES (6,2);
INSERT INTO PIZZAS_INGREDIENTES(PIZZA_ID,INGREDIENTES_ID) VALUES (6,3);
INSERT INTO PIZZAS_INGREDIENTES(PIZZA_ID,INGREDIENTES_ID) VALUES (7,1);
INSERT INTO PIZZAS_INGREDIENTES(PIZZA_ID,INGREDIENTES_ID) VALUES (7,2);
INSERT INTO PIZZAS_INGREDIENTES(PIZZA_ID,INGREDIENTES_ID) VALUES (7,3);
INSERT INTO PIZZAS_INGREDIENTES(PIZZA_ID,INGREDIENTES_ID) VALUES (7,18);
INSERT INTO PIZZAS_INGREDIENTES(PIZZA_ID,INGREDIENTES_ID) VALUES (8,1);
INSERT INTO PIZZAS_INGREDIENTES(PIZZA_ID,INGREDIENTES_ID) VALUES (8,2);
INSERT INTO PIZZAS_INGREDIENTES(PIZZA_ID,INGREDIENTES_ID) VALUES (8,3);
INSERT INTO PIZZAS_INGREDIENTES(PIZZA_ID,INGREDIENTES_ID) VALUES (8,23);
INSERT INTO PIZZAS_INGREDIENTES(PIZZA_ID,INGREDIENTES_ID) VALUES (9,1);
INSERT INTO PIZZAS_INGREDIENTES(PIZZA_ID,INGREDIENTES_ID) VALUES (9,2);
INSERT INTO PIZZAS_INGREDIENTES(PIZZA_ID,INGREDIENTES_ID) VALUES (9,3);
INSERT INTO PIZZAS_INGREDIENTES(PIZZA_ID,INGREDIENTES_ID) VALUES (9,31);
INSERT INTO PIZZAS_INGREDIENTES(PIZZA_ID,INGREDIENTES_ID) VALUES (9,10);

INSERT INTO OFERTA_PIZZA (OFERTA_ID,PIZZAS_EN_OFERTA_ID) VALUES (1,7);
INSERT INTO OFERTA_PIZZA (OFERTA_ID,PIZZAS_EN_OFERTA_ID) VALUES (2,1);
INSERT INTO OFERTA_PIZZA (OFERTA_ID,PIZZAS_EN_OFERTA_ID) VALUES (2,2);
INSERT INTO OFERTA_PIZZA (OFERTA_ID,PIZZAS_EN_OFERTA_ID) VALUES (2,3);
INSERT INTO OFERTA_PIZZA (OFERTA_ID,PIZZAS_EN_OFERTA_ID) VALUES (2,4);
INSERT INTO OFERTA_PIZZA (OFERTA_ID,PIZZAS_EN_OFERTA_ID) VALUES (4,6);
INSERT INTO OFERTA_PIZZA (OFERTA_ID,PIZZAS_EN_OFERTA_ID) VALUES (4,9);
INSERT INTO OFERTA_PIZZA (OFERTA_ID,PIZZAS_EN_OFERTA_ID) VALUES (5,3);
INSERT INTO OFERTA_PIZZA (OFERTA_ID,PIZZAS_EN_OFERTA_ID) VALUES (5,3);

INSERT INTO OFERTA_BEBIDA (OFERTA_ID,BEBIDAS_EN_OFERTA_ID) VALUES (2,3);
INSERT INTO OFERTA_BEBIDA (OFERTA_ID,BEBIDAS_EN_OFERTA_ID) VALUES (2,3);
INSERT INTO OFERTA_BEBIDA (OFERTA_ID,BEBIDAS_EN_OFERTA_ID) VALUES (2,2);
INSERT INTO OFERTA_BEBIDA (OFERTA_ID,BEBIDAS_EN_OFERTA_ID) VALUES (2,2);

INSERT INTO OFERTA_OTRO (OFERTA_ID,OTROS_EN_OFERTA_ID) VALUES (2,3);
INSERT INTO OFERTA_OTRO (OFERTA_ID,OTROS_EN_OFERTA_ID) VALUES (3,11);
 