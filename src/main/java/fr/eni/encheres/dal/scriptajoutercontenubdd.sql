use bdd_encheres;
go

insert into CATEGORIES values 
('toutes', 'Toutes'),
('meuble', 'Ameublement'), 
('infor', 'Informatique'),
('sport_loisir', 'Sports/Loisir'),
('vetements', 'Vêtements'),
('divers', 'Divers')

SELECT * FROM CATEGORIES;

INSERT INTO UTILISATEURS VALUES ('toto91', 'dupont', 'thomas', 'thomas@dupont.com', '01.01.01.01.01', 'rue1', '35353', 'Laville', 'totototo', 500, 0, 0);
INSERT INTO UTILISATEURS VALUES ('Dede85', 'dubois', 'didier', 'didier@dubois.com', '02.02.02.02.02', 'rue1','36363', 'Villedeux', 'totototo', 600, 0, 0);

SELECT * FROM UTILISATEURS;

INSERT INTO ARTICLES_VENDUS VALUES ('Fauteuil', 'un fauteuil rouge très confortable', '2021-12-06', '16:16:16', '2021-12-20','16:16:16', 125, null, 1, 2, 0, 0);
INSERT INTO ARTICLES_VENDUS VALUES ('Ecran ordinateur Apple', 'Ecran Apple en très bon état, juste une petite rayure sur le bord', '2021-12-06','17:17:17', '2021-12-08','17:17:17', 300, 420, 2, 3, 0, 0);
INSERT INTO ARTICLES_VENDUS VALUES ('Panier de basket', 'Panier de basket abimé mais avec matériel de fixation en bon état', '2021-12-06', '16:16:16', '2021-12-30','16:16:16', 50, null, 1, 4, 0, 0);
INSERT INTO ARTICLES_VENDUS VALUES ('Pantalon', 'Pantalon comme neuf porté une seule fois', '2021-12-06', '16:16:16', '2021-12-20','16:16:16', 115, null, 1, 5, 0, 0);
INSERT INTO ARTICLES_VENDUS VALUES ('Orchidée', 'Orchidée déjà en fleurs', '2021-12-06', '16:16:16', '2021-12-20','16:16:16', 25, null, 1, 6, 0, 0);

SELECT * FROM ARTICLES_VENDUS;

INSERT INTO [ENCHERES] VALUES (1, 1, '2021-12-15','10:10:10', 127);

SELECT * FROM ENCHERES;
