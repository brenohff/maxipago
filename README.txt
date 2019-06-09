MaxiPago Project

Developed by Breno Franco. 
Available at https://github.com/brenohff/maxipago;

/* MySQL Script */

CREATE SCHEMA maxipago;
USE maxipago;

CREATE TABLE IF NOT EXISTS city (
    id INT AUTO_INCREMENT,
    name VARCHAR(50) NOT NULL,
    latitude DOUBLE,
    longitude DOUBLE,
    PRIMARY KEY (id)
);

INSERT INTO maxipago.city (`id`,`name`,`latitude`,`longitude`) VALUES (1,'Brasília',-15.7797203,-47.9297218);
INSERT INTO maxipago.city (`id`,`name`,`latitude`,`longitude`) VALUES (2,'Cuiabá',-15.5961103,-56.0966682);
INSERT INTO maxipago.city (`id`,`name`,`latitude`,`longitude`) VALUES (3,'Campinas',-22.9055595,-47.0608292);
INSERT INTO maxipago.city (`id`,`name`,`latitude`,`longitude`) VALUES (4,'São Paulo',-23.5475006,-46.6361084);
INSERT INTO maxipago.city (`id`,`name`,`latitude`,`longitude`) VALUES (5,'Belo Horizonte',-19.9208298,-43.9377785);
INSERT INTO maxipago.city (`id`,`name`,`latitude`,`longitude`) VALUES (6,'Rio de Janeiro',-22.9064198,-43.1822319);
INSERT INTO maxipago.city (`id`,`name`,`latitude`,`longitude`) VALUES (7,'Recife',-8.0538902,-34.8811111);
INSERT INTO maxipago.city (`id`,`name`,`latitude`,`longitude`) VALUES (8,'Goiânia',-16.6786098,-49.253891);
INSERT INTO maxipago.city (`id`,`name`,`latitude`,`longitude`) VALUES (9,'Maceió',-9.6658297,-35.7352791);
INSERT INTO maxipago.city (`id`,`name`,`latitude`,`longitude`) VALUES (10,'Teresina',-5.08917,-42.8019409);

SELECT * FROM maxipago.city;


/* How to use */

- There are 2 endpoints in the service:
	* /v1/cidade/buscarTodas
		> Request type: GET
		> Don't have any required parameters
		> Is returned in the JSON pattern
		> This endpoint is to search all the cities registered in the database, facilitating the visualization of the same ones
		> Example: http://localhost:8080/v1/cidade/buscarTodasCidades

	* /v1/cidade/obterCombinacoes
		> Request type: GET
		> It has 2 parameters required:
			-> tipoRetorno (json ou xml)
			-> unidade (mi ou km)
		> It is returned in json or xml, depending on the value passed in the parameter "unidade"
		> This endpoints returns all possible combinations of cities
		> Example: http://localhost:8080/v1/cidade/obterCombinacoes?tipoRetorno=json&unidade=km


/* Justifications */

- To calculate the distance between cities, a method has been taken from the site "https://www.geodatasource.com/developers/java" since it is already used in all products of the company GEODATASOURCE, so it is reliable and easy implementation, making it easy to convert from miles to kilometers.

- The combining method was created considering recursion, seeking to perform all combinations with only one method, simplifying understanding and increasing performance. This method makes all combinations of the first element of the list and, when finished, calls itself by passing a new updated list without the element that has already been combined.


/* Comments */

- The database must be running locally (localhost) and the default MySQL port (3306), otherwise the project will not communicate with the database.

