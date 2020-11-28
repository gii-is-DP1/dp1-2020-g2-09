-- One admin user, named admin1 with passwor 4dm1n and authority admin
INSERT INTO users(username,password,enabled) VALUES ('admin1','4dm1n',TRUE);
INSERT INTO authorities(id,username,authority) VALUES (1,'admin1','administrador');
-- One owner user, named owner1 with passwor 0wn3r
INSERT INTO users(username,password,enabled) VALUES ('owner1','0wn3r',TRUE);
INSERT INTO authorities(id,username,authority) VALUES (2,'owner1','owner');
-- One vet user, named vet1 with passwor v3t
INSERT INTO users(username,password,enabled) VALUES ('vet1','v3t',TRUE);
INSERT INTO authorities(id,username,authority) VALUES (3,'vet1','veterinarian');


-- One owner user, named luctorgom with password luctorgom
INSERT INTO users(username,password,enabled) VALUES ('luctorgom','luctorgom',TRUE);
INSERT INTO authorities(id,username,authority) VALUES (4,'luctorgom','administrador');
-- One owner user, named raupargor with password raupargor
INSERT INTO users(username,password,enabled) VALUES ('raupargor','raupargor',TRUE);
INSERT INTO authorities(id,username,authority) VALUES (5,'raupargor','owner');
-- One owner user, named margarcac1 with password margarcac1
INSERT INTO users(username,password,enabled) VALUES ('margarcac1','margarcac1',TRUE);
INSERT INTO authorities(id,username,authority) VALUES (6,'margarcac1','owner');
-- One owner user, named jesrolcad with passwor jesrolcad
INSERT INTO users(username,password,enabled) VALUES ('jesrolcad','jesrolcad',TRUE);
INSERT INTO authorities(id,username,authority) VALUES (7,'jesrolcad','owner');

INSERT INTO users(username,password,enabled) VALUES ('serfiggom','serfiggom',TRUE);
INSERT INTO authorities(id,username,authority) VALUES (8, 'serfiggom','administrador');

INSERT INTO users(username,password,enabled) VALUES ('ejemplo1','ejemplo1',TRUE);
INSERT INTO authorities(id,username,authority) VALUES (9,'ejemplo1','ejemplo');


INSERT INTO vets VALUES (1, 'James', 'Carter');
INSERT INTO vets VALUES (2, 'Helen', 'Leary');
INSERT INTO vets VALUES (3, 'Linda', 'Douglas');
INSERT INTO vets VALUES (4, 'Rafael', 'Ortega');
INSERT INTO vets VALUES (5, 'Henry', 'Stevens');
INSERT INTO vets VALUES (6, 'Sharon', 'Jenkins');

INSERT INTO specialties VALUES (1, 'radiology');
INSERT INTO specialties VALUES (2, 'surgery');
INSERT INTO specialties VALUES (3, 'dentistry');

INSERT INTO vet_specialties VALUES (2, 1);
INSERT INTO vet_specialties VALUES (3, 2);
INSERT INTO vet_specialties VALUES (3, 3);
INSERT INTO vet_specialties VALUES (4, 2);
INSERT INTO vet_specialties VALUES (5, 1);

INSERT INTO types VALUES (1, 'cat');
INSERT INTO types VALUES (2, 'dog');
INSERT INTO types VALUES (3, 'lizard');
INSERT INTO types VALUES (4, 'snake');
INSERT INTO types VALUES (5, 'bird');
INSERT INTO types VALUES (6, 'hamster');

INSERT INTO owners VALUES (1, 'George', 'Franklin', '110 W. Liberty St.', 'Madison', '6085551023', 'owner1');
INSERT INTO owners VALUES (2, 'Betty', 'Davis', '638 Cardinal Ave.', 'Sun Prairie', '6085551749', 'owner1');
INSERT INTO owners VALUES (3, 'Eduardo', 'Rodriquez', '2693 Commerce St.', 'McFarland', '6085558763', 'owner1');
INSERT INTO owners VALUES (4, 'Harold', 'Davis', '563 Friendly St.', 'Windsor', '6085553198', 'owner1');
INSERT INTO owners VALUES (5, 'Peter', 'McTavish', '2387 S. Fair Way', 'Madison', '6085552765', 'owner1');
INSERT INTO owners VALUES (6, 'Jean', 'Coleman', '105 N. Lake St.', 'Monona', '6085552654', 'owner1');
INSERT INTO owners VALUES (7, 'Jeff', 'Black', '1450 Oak Blvd.', 'Monona', '6085555387', 'owner1');
INSERT INTO owners VALUES (8, 'Maria', 'Escobito', '345 Maple St.', 'Madison', '6085557683', 'owner1');
INSERT INTO owners VALUES (9, 'David', 'Schroeder', '2749 Blackhawk Trail', 'Madison', '6085559435', 'owner1');
INSERT INTO owners VALUES (10, 'Carlos', 'Estaban', '2335 Independence La.', 'Waunakee', '6085555487', 'owner1');

INSERT INTO owners VALUES (14, 'Jesus', 'Roldan', 'San Diego 14', 'Sevilla', '633438316', 'jesrolcad');

INSERT INTO owners VALUES (11, 'Lucia', 'Torres', 'Ferrara 4, 41089','Montequinto', '654987321', 'luctorgom');
INSERT INTO owners VALUES (13, 'Maria', 'Garcia', 'Laguillo 27, 41003','Sevilla', '601242743', 'margarcac1');
INSERT INTO owners VALUES (12, 'Raul', 'Parrado', 'Sirio 5, 41015','PinoMontano', '622682440', 'raupargor');


INSERT INTO pets(id,name,birth_date,type_id,owner_id) VALUES (1, 'Leo', '2010-09-07', 1, 1);
INSERT INTO pets(id,name,birth_date,type_id,owner_id) VALUES (2, 'Basil', '2012-08-06', 6, 2);
INSERT INTO pets(id,name,birth_date,type_id,owner_id) VALUES (3, 'Rosy', '2011-04-17', 2, 3);
INSERT INTO pets(id,name,birth_date,type_id,owner_id) VALUES (4, 'Jewel', '2010-03-07', 2, 3);
INSERT INTO pets(id,name,birth_date,type_id,owner_id) VALUES (5, 'Iggy', '2010-11-30', 3, 4);
INSERT INTO pets(id,name,birth_date,type_id,owner_id) VALUES (6, 'George', '2010-01-20', 4, 5);
INSERT INTO pets(id,name,birth_date,type_id,owner_id) VALUES (7, 'Samantha', '2012-09-04', 1, 6);
INSERT INTO pets(id,name,birth_date,type_id,owner_id) VALUES (8, 'Max', '2012-09-04', 1, 6);
INSERT INTO pets(id,name,birth_date,type_id,owner_id) VALUES (9, 'Lucky', '2011-08-06', 5, 7);
INSERT INTO pets(id,name,birth_date,type_id,owner_id) VALUES (10, 'Mulligan', '2007-02-24', 2, 8);
INSERT INTO pets(id,name,birth_date,type_id,owner_id) VALUES (11, 'Freddy', '2010-03-09', 5, 9);
INSERT INTO pets(id,name,birth_date,type_id,owner_id) VALUES (12, 'Lucky', '2010-06-24', 2, 10);
INSERT INTO pets(id,name,birth_date,type_id,owner_id) VALUES (13, 'Sly', '2012-06-08', 1, 10);
INSERT INTO pets(id,name,birth_date,type_id,owner_id) VALUES (17, 'Luka', '2020-09-15', 2, 14);
INSERT INTO pets(id,name,birth_date,type_id,owner_id) VALUES (14, 'Flamenka', '2020-01-16', 6, 11);
INSERT INTO pets(id,name,birth_date,type_id,owner_id) VALUES (16, 'Periqui', '2019-09-11', 5, 13);
INSERT INTO pets(id,name,birth_date,type_id,owner_id) VALUES (15, 'Illo', '2000-05-29', 1, 12);


INSERT INTO visits(id,pet_id,visit_date,description) VALUES (1, 7, '2013-01-01', 'rabies shot');
INSERT INTO visits(id,pet_id,visit_date,description) VALUES (2, 8, '2013-01-02', 'rabies shot');
INSERT INTO visits(id,pet_id,visit_date,description) VALUES (3, 8, '2013-01-03', 'neutered');
INSERT INTO visits(id,pet_id,visit_date,description) VALUES (4, 7, '2013-01-04', 'spayed');


INSERT INTO clientes(nombre,apellidos,fecha_nacimiento,telefono,email, usuario) 
VALUES ('María','García Cáceres','2000-01-01',654123987,'margarcar@alum.us.es', 'margarcac1');

INSERT INTO clientes(nombre,apellidos,fecha_nacimiento,telefono,email, usuario) 
VALUES ('Servando','Figueroa Gómez','2000-08-12','698745213','serfiggom@alum.us.es', 'serfiggom');

INSERT INTO clientes(nombre,apellidos,fecha_nacimiento,telefono,email, usuario) 
VALUES ('Raúl','Parrado Gordón','2000-05-29','717548963','raupargor@alum.us.es', 'raupargor');

INSERT INTO clientes(nombre,apellidos,fecha_nacimiento,telefono,email, usuario) 
VALUES ('Álvaro','Sánchez González','2000-01-30','687452196','alvsangon@alum.us.es', 'raupargor');

INSERT INTO clientes(nombre,apellidos,fecha_nacimiento,telefono,email, usuario) 
VALUES ('Jesús','Roldán Cadena','2000-08-07','632145879','jesrolcad@alum.us.es', 'jesrolcad');

INSERT INTO clientes(nombre,apellidos,fecha_nacimiento,telefono,email, usuario) 
VALUES ('Lucía','Torres Gómez','2000-03-30','614589725','luctorgom@alum.us.es', 'luctorgom');

INSERT INTO cocineros(nombre,apellidos,fecha_nacimiento,telefono,email, fecha_inicio_contrato, usuario) 
VALUES ('Paco','Pérez Maldonado','1998-12-29','777777777','paquitorechulon@gmail.com', '2020-10-01', 'ejemplo1');

INSERT INTO cocineros(nombre,apellidos,fecha_nacimiento,telefono,email,usuario, fecha_inicio_contrato) 
VALUES ('Marmona','Jimenez Ronaldinha','1997-12-29','777555555','mariana@gmail.com','ejemplo1', '2020-09-30');


/*DATETIME fecha_actual = DATETIME.NOW() -> No funciona*/
INSERT INTO repartidores(nombre,apellidos,fecha_nacimiento,telefono,email, fecha_inicio_contrato, usuario)
VALUES ('Minguito','Gutiérrez Ronaldo','1998-11-03','682547321','minguitoo@gmail.com', '2019-12-12', 'ejemplo1');

INSERT INTO repartidores(nombre,apellidos,fecha_nacimiento,telefono,email, fecha_inicio_contrato, usuario)
VALUES ('Pepa','Cansado Levante','1995-09-13','985432158','cansado_levante@gmail.com', '2020-11-22', 'ejemplo1');


INSERT INTO TIPO_PAGO VALUES(2,'EFECTIVO');
INSERT INTO TIPO_PAGO VALUES(1,'TARJETA');

INSERT INTO ESTADO_PEDIDO VALUES(4,'EN COCINA');
INSERT INTO ESTADO_PEDIDO VALUES(1,'PREPARADO');
INSERT INTO ESTADO_PEDIDO VALUES(2,'EN REPARTO');
INSERT INTO ESTADO_PEDIDO VALUES(3,'ENTREGADO');

INSERT INTO TIPO_ENVIO VALUES(2,'DOMICILIO');
INSERT INTO TIPO_ENVIO VALUES(1,'RECOGER EN TIENDA');

INSERT INTO pedido(precio,gastos_envio,direccion,fecha_pedido,estado_pedido, tipo_pago, tipo_envio)
VALUES ('30','3.5','C/Ferrara, 5, 7b', '2020-11-15', 2, 1,1);
INSERT INTO pedido(precio,gastos_envio,direccion,fecha_pedido,estado_pedido, tipo_pago, tipo_envio)
VALUES ('29','3','C/Ferrara, 5, 7b', '2020-11-16',3,1,1);
INSERT INTO pedido(precio,gastos_envio,direccion,fecha_pedido,estado_pedido, tipo_pago, tipo_envio)
VALUES ('50','2.30','C/Ferrara, 5, 7b', '2020-11-18',2,1,1);
INSERT INTO pedido(precio,gastos_envio,direccion,fecha_pedido,estado_pedido, tipo_pago, tipo_envio)
VALUES ('57','2','C/Ferrara, 5, 7b', '2020-11-30',3,1,1);


INSERT INTO administradores(nombre,apellidos,fecha_nacimiento,telefono,email, usuario)
VALUES ('Juan','Pérez Ruíz','1990-01-01','863838343','perez_ruiz@gmail.com', 'ejemplo1');

INSERT INTO administradores(nombre,apellidos,fecha_nacimiento,telefono,email, usuario)
VALUES ('Rodolfa','Abduzcan Play','1996-07-22','685390102','abduzcan_1@gmail.com', 'ejemplo1');

INSERT INTO administradores(nombre,apellidos,fecha_nacimiento,telefono,email, usuario)
VALUES ('Servando','Figueroa','1990-01-01','863838343','perez_ruiz@gmail.com', 'serfiggom');

INSERT INTO administradores(nombre,apellidos,fecha_nacimiento,telefono,email, usuario)
VALUES ('Lucia','Torres','2000-01-01','654789654','luciatg30@gmail.com', 'luctorgom');


INSERT INTO TIPO_RESERVA VALUES(2,'ALMUERZO');
INSERT INTO TIPO_RESERVA VALUES(1,'CENA');

INSERT INTO RESERVAS VALUES(1,'2020-05-29','10:34:09',5,1);
INSERT INTO RESERVAS VALUES(2,'2020-07-12','20:34:09',2,1); 
INSERT INTO RESERVAS VALUES(3,'2020-05-29','10:34:09',5,2);
INSERT INTO RESERVAS VALUES(4,'2020-07-12','20:34:09',2,1);
INSERT INTO RESERVAS VALUES(5,'2020-05-29','10:34:09',5,2);
INSERT INTO RESERVAS VALUES(6,'2020-07-12','20:34:09',2,1);
INSERT INTO RESERVAS VALUES(7,'2020-05-29','10:34:09',5,1);
INSERT INTO RESERVAS VALUES(8,'2020-07-12','20:34:09',2,1);
INSERT INTO RESERVAS VALUES(9,'2020-05-29','10:34:09',5,1);
INSERT INTO RESERVAS VALUES(10,'2020-07-12','20:34:09',2,1);

INSERT INTO RECLAMACIONES(fecha_reclamacion, observacion)
VALUES('2020-06-27', 'Había un hueso de aceituna en mi pizza. ¿Qué tipo de broma es esta?');

INSERT INTO RECLAMACIONES(fecha_reclamacion, observacion)
VALUES('2020-11-15', 'Mi pizza carbonara llevaba 1 sola unidad de champiñón.');

INSERT INTO MESAS VALUES(1,6);
INSERT INTO MESAS VALUES(2,6);
INSERT INTO MESAS VALUES(3,6);
INSERT INTO MESAS VALUES(4,6);
INSERT INTO MESAS VALUES(5,6);
INSERT INTO MESAS VALUES(6,6);
INSERT INTO MESAS VALUES(7,6);

INSERT INTO RESERVA_ASOCIADA (MESAS_RESERVADAS,RESERVA_ID) VALUES (1,1);
INSERT INTO RESERVA_ASOCIADA (MESAS_RESERVADAS,RESERVA_ID) VALUES (2,1);
INSERT INTO RESERVA_ASOCIADA (MESAS_RESERVADAS,RESERVA_ID) VALUES (3,2);
INSERT INTO RESERVA_ASOCIADA (MESAS_RESERVADAS,RESERVA_ID) VALUES (4,2);
INSERT INTO RESERVA_ASOCIADA (MESAS_RESERVADAS,RESERVA_ID) VALUES (5,6);
INSERT INTO RESERVA_ASOCIADA (MESAS_RESERVADAS,RESERVA_ID) VALUES (6,8);
INSERT INTO RESERVA_ASOCIADA (MESAS_RESERVADAS,RESERVA_ID) VALUES (1,8);
INSERT INTO RESERVA_ASOCIADA (MESAS_RESERVADAS,RESERVA_ID) VALUES (2,9);
INSERT INTO RESERVA_ASOCIADA (MESAS_RESERVADAS,RESERVA_ID) VALUES (5,10);

INSERT INTO TAMANO_PRODUCTO VALUES(1,'NORMAL');
INSERT INTO TAMANO_PRODUCTO VALUES(2,'GRANDE');

INSERT INTO NIVEL_SOCIO VALUES(3,'ORO');
INSERT INTO NIVEL_SOCIO VALUES(1,'PLATA');
INSERT INTO NIVEL_SOCIO VALUES(2,'BRONCE');

INSERT INTO OFERTAS(TAMANO_PRODUCTO,COSTE,FECHA_INICIAL,NIVEL_SOCIO,FECHA_FINAL) values
(1,58.6,'2020-01-05',3,'2020-01-31');
INSERT INTO OFERTAS(TAMANO_PRODUCTO,COSTE,FECHA_INICIAL,NIVEL_SOCIO,FECHA_FINAL) values
(1,60.6,'2020-02-05',2,'2020-02-28');
INSERT INTO OFERTAS(TAMANO_PRODUCTO,COSTE,FECHA_INICIAL,NIVEL_SOCIO,FECHA_FINAL) values
(1,15.8,'2020-03-05',1,'2020-03-31');
INSERT INTO OFERTAS(TAMANO_PRODUCTO,COSTE,FECHA_INICIAL,NIVEL_SOCIO,FECHA_FINAL) values
(1,38.9,'2020-04-05',3,'2020-04-30');

INSERT INTO TIPO_MASA VALUES(1,'FINA');
INSERT INTO TIPO_MASA VALUES(3,'GRUESA');
INSERT INTO TIPO_MASA VALUES(2,'RELLENA');

/*INSERT INTO TAMAÑO VALUES(1,'GRANDE');
INSERT INTO TAMAÑO VALUES(3,'PEQUEÑA');
INSERT INTO TAMAÑO VALUES(2,'MEDIANA');*/

INSERT INTO Alergenos VALUES(1,'Crustaceos y productos a base de crustaceos');
INSERT INTO Alergenos VALUES(2,'Cereales que contengan gluten');
INSERT INTO Alergenos VALUES(3,'Huevos y productos a base de huevo');
INSERT INTO Alergenos VALUES(4,'Pescado y productos a base de pescado');
INSERT INTO Alergenos VALUES(5,'Cacahuetes y productos a base de cacahuetes');
INSERT INTO Alergenos VALUES(6,'Soja y productos a base de soja');
INSERT INTO Alergenos VALUES(7,'Leche y sus derivados');
INSERT INTO Alergenos VALUES(8,'Frutos de cascara');
INSERT INTO Alergenos VALUES(9,'Apio y productos derivados');
INSERT INTO Alergenos VALUES(10,'Mostaza y productos derivados');
INSERT INTO Alergenos VALUES(11,'Granos de sesamo');
INSERT INTO Alergenos VALUES(12,'Dioxido de azufre y sulfitos');
INSERT INTO Alergenos VALUES(13,'Altramuces y productos a base de altramuces');
INSERT INTO Alergenos VALUES(14,'Moluscos y productos a base de moluscos');


INSERT INTO PIZZAS values (1, 1, 20, 'PROSCIUTTO E FUNGHI', 1 ,2);
INSERT INTO PIZZAS values (2, 1, 22, 'PROSCIUTTO', 2 ,3);
INSERT INTO PIZZAS values (3, 1, 10, 'HAWAIANA', 2 ,1);
INSERT INTO PIZZAS values (4, 1, 15, 'DIAVOLA', 1 ,3);
INSERT INTO PIZZAS values (5, 2, 19, '4 STAGIONI', 1 ,1);
INSERT INTO PIZZAS values (6, 1, 22, 'TONNATA', 2 ,3);
INSERT INTO PIZZAS values (7, 1, 10, '4 FORMAGGI ', 2 ,1);
INSERT INTO PIZZAS values (8, 1, 15, 'POMODORINI', 1 ,3);
INSERT INTO PIZZAS values (9, 2, 19, 'BARBACOA', 1 ,1);

/*INSERT INTO OTROS values (1, 1, 20, 'patatas bravas',  );
INSERT INTO OTROS values (2, 1, 22, 'Spaghetti con salsa de tomate casera',		);
INSERT INTO OTROS values (3, 1, 10, 'crema catalana',	);
INSERT INTO OTROS values (4, 1, 15, 'tiramisú',			);
INSERT INTO OTROS values (5, 2, 19, 'brownie',				);*/

INSERT INTO BEBIDAS values (1, 1, 20, 'Coca-cola', true ,2);
INSERT INTO BEBIDAS values (2, 1, 22, 'Nestea', false ,1);
INSERT INTO BEBIDAS values (3, 1, 10, 'Fanta naranja', false ,1);
INSERT INTO BEBIDAS values (4, 1, 15, 'Fanta limon', true ,2);
INSERT INTO BEBIDAS values (5, 2, 19, 'Agua', false ,1);


INSERT INTO CARTAS(nombre, fecha) values ('CartaPrincipal', '2020-04-30');
INSERT INTO CARTAS(nombre, fecha) values ('CartaPrueba','2020-11-20');

INSERT INTO COMPOSICION_CARTA_PIZZA(PIZZAS_EN_CARTA, CARTA_DE_PIZZAS_ID) VALUES (1,1);
INSERT INTO COMPOSICION_CARTA_PIZZA(PIZZAS_EN_CARTA, CARTA_DE_PIZZAS_ID) VALUES (2,1);
INSERT INTO COMPOSICION_CARTA_PIZZA(PIZZAS_EN_CARTA, CARTA_DE_PIZZAS_ID) VALUES (3,1);
INSERT INTO COMPOSICION_CARTA_PIZZA(PIZZAS_EN_CARTA, CARTA_DE_PIZZAS_ID) VALUES (4,1);
INSERT INTO COMPOSICION_CARTA_PIZZA(PIZZAS_EN_CARTA, CARTA_DE_PIZZAS_ID) VALUES (1,2);
INSERT INTO COMPOSICION_CARTA_PIZZA(PIZZAS_EN_CARTA, CARTA_DE_PIZZAS_ID) VALUES (3,2);

/*EN los siguientes inserts las columnas están intercambiadas y no sé por qué*/
INSERT INTO COMPOSICION_CARTA_BEBIDA(BEBIDAS_EN_CARTA, CARTA_DE_BEBIDAS_ID) VALUES (1,1);
INSERT INTO COMPOSICION_CARTA_BEBIDA(BEBIDAS_EN_CARTA, CARTA_DE_BEBIDAS_ID) VALUES (2,1);
INSERT INTO COMPOSICION_CARTA_BEBIDA(BEBIDAS_EN_CARTA, CARTA_DE_BEBIDAS_ID) VALUES (1,5);
INSERT INTO COMPOSICION_CARTA_BEBIDA(BEBIDAS_EN_CARTA, CARTA_DE_BEBIDAS_ID) VALUES (1,2);

/*INSERT INTO COMPOSICION_CARTA_OTROS(OTROS_EN_CARTA, CARTA_DE_OTROS_ID) VALUES ();*/

/*INSERT INTO INGREDIENTE values (1, 'gluten','2020/01/01' , 'pan', 'Rico en carbohidratos');
INSERT INTO INGREDIENTE values (2, 'lactosa','2020/02/02' , 'queso', 'Rico en proteinas');
INSERT INTO INGREDIENTE values (3, 'fructosa','2020/03/03' , 'tomate', 'Verduras y frutas');
INSERT INTO INGREDIENTE values (1, 'pescado','2020/04/04' , 'anchoas', 'Rico en proteinas');
INSERT INTO INGREDIENTE values (2, 'atopia','2020/05/05' , 'huevo', 'Rico en proteinas');
INSERT INTO INGREDIENTE values (3, 'fructosa','2020/06/06' , 'manzana', 'Verduras y frutas');*/



