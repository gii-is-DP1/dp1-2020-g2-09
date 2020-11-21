-- One admin user, named admin1 with passwor 4dm1n and authority admin
INSERT INTO users(username,password,enabled) VALUES ('admin1','4dm1n',TRUE);
INSERT INTO authorities(id,username,authority) VALUES (1,'admin1','admin');
-- One owner user, named owner1 with passwor 0wn3r
INSERT INTO users(username,password,enabled) VALUES ('owner1','0wn3r',TRUE);
INSERT INTO authorities(id,username,authority) VALUES (2,'owner1','owner');
-- One vet user, named vet1 with passwor v3t
INSERT INTO users(username,password,enabled) VALUES ('vet1','v3t',TRUE);
INSERT INTO authorities(id,username,authority) VALUES (3,'vet1','veterinarian');

-- One owner user, named jesrolcad with passwor jesrolcad
INSERT INTO users(username,password,enabled) VALUES ('jesrolcad','jesrolcad',TRUE);
INSERT INTO authorities(id,username,authority) VALUES (7,'jesrolcad','owner');

-- One owner user, named luctorgom with password luctorgom
INSERT INTO users(username,password,enabled) VALUES ('luctorgom','luctorgom',TRUE);
INSERT INTO authorities(id,username,authority) VALUES (4,'luctorgom','owner');
-- One owner user, named raupargor with password raupargor
INSERT INTO users(username,password,enabled) VALUES ('raupargor','raupargor',TRUE);
INSERT INTO authorities(id,username,authority) VALUES (5,'raupargor','owner');
-- One owner user, named margarcac1 with password margarcac1
INSERT INTO users(username,password,enabled) VALUES ('margarcac1','margarcac1',TRUE);
INSERT INTO authorities(id,username,authority) VALUES (6,'margarcac1','owner');


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

INSERT INTO clientes(nombre,apellidos,fecha_nacimiento,telefono,usuario,contraseña,email) 
VALUES ('María','García Cáceres','2000-01-01','654123987','margarcar','MariaGarcia15','margarcar@alum.us.es');

INSERT INTO clientes(nombre,apellidos,fecha_nacimiento,telefono,usuario,contraseña,email) 
VALUES ('Servando','Figueroa Gómez','2000-08-12','698745213','serfiggom','ServandoFigueroa78','serfiggom@alum.us.es');

INSERT INTO clientes(nombre,apellidos,fecha_nacimiento,telefono,usuario,contraseña,email) 
VALUES ('Raúl','Parrado Gordón','2000-05-29','717548963','raupargor','RaulParrado74','raupargor@alum.us.es');

INSERT INTO clientes(nombre,apellidos,fecha_nacimiento,telefono,usuario,contraseña,email) 
VALUES ('Álvaro','Sánchez González','2000-01-30','687452196','alvsangon','AlvaroSanchez65','alvsangon@alum.us.es');

INSERT INTO clientes(nombre,apellidos,fecha_nacimiento,telefono,usuario,contraseña,email) 
VALUES ('Jesús','Roldán Cadena','2000-08-07','632145879','jesrolcad','JesusRoldan16','jesrolcad@alum.us.es');

INSERT INTO clientes(nombre,apellidos,fecha_nacimiento,telefono,usuario,contraseña,email) 
VALUES ('Lucía','Torres Gómez','2000-03-30','614589725','luctorgom','LuciaTorres30','luctorgom@alum.us.es');

INSERT INTO cocineros(nombre,apellidos,fecha_nacimiento,telefono,usuario,contraseña,email) 
VALUES ('Paco','Pérez Maldonado','1998-12-29','777777777','PaquitoelChocolatero','PacoPacoPacoDeMiPaco','paquitorechulon@gmail.com');

INSERT INTO cocineros(nombre,apellidos,fecha_nacimiento,telefono,usuario,contraseña,email) 
VALUES ('Marmona','Jimenez Ronaldinha','1997-12-29','777555555','MarianaRajoy','aroaroaro','mariana@gmail.com');

INSERT INTO TIPO_PAGO VALUES(0,'EFECTIVO');
INSERT INTO TIPO_PAGO VALUES(1,'TARJETA');

INSERT INTO ESTADO_PEDIDO VALUES(0,'EN COCINA');
INSERT INTO ESTADO_PEDIDO VALUES(1,'PREPARADO');
INSERT INTO ESTADO_PEDIDO VALUES(2,'EN REPARTO');
INSERT INTO ESTADO_PEDIDO VALUES(3,'ENTREGADO');

INSERT INTO TIPO_ENVIO VALUES(0,'DOMICILIO');
INSERT INTO TIPO_ENVIO VALUES(1,'RECOGER EN TIENDA');

INSERT INTO pedido(precio,gastos_envio,direccion,fecha_pedido,estado_pedido, tipo_pago, tipo_envio)
VALUES ('30','3.5','C/Ferrara, 5, 7b', '2020-11-15', 0, 1,0);
INSERT INTO pedido(precio,gastos_envio,direccion,fecha_pedido,estado_pedido, tipo_pago, tipo_envio)
VALUES ('29','3','C/Ferrara, 5, 7b', '2020-11-16',3,1,1);
INSERT INTO pedido(precio,gastos_envio,direccion,fecha_pedido,estado_pedido, tipo_pago, tipo_envio)
VALUES ('50','2.30','C/Ferrara, 5, 7b', '2020-11-18',2,0,1);
INSERT INTO pedido(precio,gastos_envio,direccion,fecha_pedido,estado_pedido, tipo_pago, tipo_envio)
VALUES ('57','2','C/Ferrara, 5, 7b', '2020-11-30',3,1,0);


INSERT INTO repartidores(nombre,apellidos,fecha_nacimiento,telefono,usuario,contraseña,email)
VALUES ('Minguito','Gutiérrez Ronaldo','1998-11-03','682547321','ErMingui','aroaroaro','minguitoo@gmail.com');

INSERT INTO repartidores(nombre,apellidos,fecha_nacimiento,telefono,usuario,contraseña,email)
VALUES ('Pepa','Cansado Levante','1995-09-13','985432158','CansadoEstoy','jejejeje','cansado_levante@gmail.com');


INSERT INTO administradores(nombre,apellidos,fecha_nacimiento,telefono,usuario,contraseña,email)
VALUES ('Juan','Pérez Ruíz','1990-01-01','863838343','Pereez28','peresitoConFlow','perez_ruiz@gmail.com');

INSERT INTO administradores(nombre,apellidos,fecha_nacimiento,telefono,usuario,contraseña,email)
VALUES ('Rodolfa','Abduzcan Play','1996-07-22','685390102','Auroner1','daleAlPlay','abduzcan_1@gmail.com');


INSERT INTO TIPO_RESERVA VALUES(0,'ALMUERZO');
INSERT INTO TIPO_RESERVA VALUES(1,'CENA');

INSERT INTO RESERVAS VALUES(0,'2020-05-29','10:34:09',5,0);
INSERT INTO RESERVAS VALUES(1,'2020-07-12','20:34:09',2,1);

INSERT INTO RECLAMACIONES(fecha_reclamacion, observacion)
VALUES('2020-06-27', 'Había un hueso de aceituna en mi pizza. ¿Qué tipo de broma es esta?');

INSERT INTO RECLAMACIONES(fecha_reclamacion, observacion)
VALUES('2020-11-15', 'Mi pizza carbonara llevaba 1 sola unidad de champiñón.');


INSERT INTO TAMANO_PRODUCTO VALUES(0,'NORMAL');
INSERT INTO TAMANO_PRODUCTO VALUES(1,'GRANDE');

INSERT INTO NIVEL_SOCIO VALUES(0,'ORO');
INSERT INTO NIVEL_SOCIO VALUES(1,'PLATA');
INSERT INTO NIVEL_SOCIO VALUES(2,'BRONCE');




