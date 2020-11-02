INSERT INTO shape(name) VALUE ('shape2');
INSERT INTO position(x,y,shape_id) VALUES (50,50,1),(25,100),(75,100),(50,50);

SELECT * FROM shape INNER JOIN position ON
        shape.shape_id = position.shape_id WHERE shape.name = 'shape2';


INSERT INTO shape(name) VALUES ('shape2');
INSERT INTO position(x,y,shape_id) VALUES (50,50,3),(25,100,3),(75,100,3),(50,50,3);

INSERT INTO shape(name) VALUES ('shape4');
INSERT INTO position(x,y,shape_id) VALUES (100,100,7),(50,200,7),(150,200,7),(100,100,7);



SELECT * FROM shape INNER JOIN position ON
        shape.shape_id = position.shape_id WHERE shape.name = 'shape4';

SELECT * FROM shape WHERE shape_id = 3

SELECT * FROM shape INNER JOIN position ON
        shape.shape_id = position.shape_id WHERE shape.shape_id = 34;

SELECT shape_id FROM shape WHERE shape_id = (SELECT MAX(shape_id) FROM shape)

SELECT MAX(shape_id) FROM shape
SELECT * FROM shape;
SELECT * FROM position;

INSERT INTO position(x,y,shape_id) VALUES ( 100,100,24),( 100,50,24),( 50,200,24),( 200,150,24),( 150,200,24),( 200,100,24),( 100,100,24);

triangle

100 100 50 200 150 200 100 100

square

100 100 100 200 200 200 200 100 100 100

rectangle

100 100 100 200 400 200 400 100 100 100