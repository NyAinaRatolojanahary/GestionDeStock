create database stock;

\c stock;

create table unite(
    idUnite serial primary key,
    nomUnite varchar(15),
    abbreviation varchar(8)
);

create table article(
    idArticle serial primary key,
    nomArticle varchar(30),
    reference varchar(30),
    idUnite int,
    modeSortie int,
    foreign key (idUnite) references unite(idUnite)
);

create table magasin(
    idMagasin varchar(15) primary key,
    nomMagasin varchar(30),
    localisation varchar(30),
    contact varchar(14)
);

create table entreeStock(
    idEntreeStock varchar(15) primary key,
    dateEntree date,
    idArticle int,
    PU decimal,
    quantite decimal,
    idMagasin varchar(15),
    reste decimal,
    foreign key (idMagasin) references magasin(idMagasin)
);

create table sortieStock(
    idSortieStock varchar(15) primary key,
    dateSortie date,
    idArticle int,
    quantite decimal,
    idMagasin varchar(15),
    foreign key (idMagasin) references magasin(idMagasin)
);

create table mouvement(
    idMouvement varchar(15) primary key,
    idEntree varchar(15),
    quantiteDisponible decimal,
    idSortie varchar(15),
    foreign key (idEntree) references entreeStock(idEntreeStock),
    foreign key (idSortie) references sortieStock(idSortieStock)
);



-- Data --

insert into unite(idUnite,nomUnite,abbreviation) values (default,'Kilogramme','Kg');
insert into unite(idUnite,nomUnite,abbreviation) values (default,'Tonne','T');
insert into unite(idUnite,nomUnite,abbreviation) values (default,'Boite','Bt');
insert into unite(idUnite,nomUnite,abbreviation) values (default,'Carton','Crt');
insert into unite(idUnite,nomUnite,abbreviation) values (default,'Litre','L');


-- modeSortie : 1 pour FIFO et -1 pour LIFO

insert into article(idArticle,nomArticle,reference,idUnite,modeSortie) values (default,'Vary','V01',2,1);
insert into article(idArticle,nomArticle,reference,idUnite,modeSortie) values (default,'Vary Mena','V011',2,1);
insert into article(idArticle,nomArticle,reference,idUnite,modeSortie) values (default,'Vary Makalioka','V012',2,1);
insert into article(idArticle,nomArticle,reference,idUnite,modeSortie) values (default,'Vary Mavokely','V013',2,1);

create sequence seqMagasin
    START WITH 1
    INCREMENT BY 1
    MINVALUE 1
    MAXVALUE 9999
    CACHE 1;

insert into magasin(idMagasin,nomMagasin,localisation,contact) values ('MG1','Magasin Voalohany','Behoririka', '+261340456748');
insert into magasin(idMagasin,nomMagasin,localisation,contact) values ('MG2','Magasin Faharoa', 'Tsaralalana', '+261340456748');
insert into magasin(idMagasin,nomMagasin,localisation,contact) values ('MG3','Magasin Fahatelo', 'Ivato', '+261340456748');
insert into magasin(idMagasin,nomMagasin,localisation,contact) values ('MG4','Magasin Fahaefatra', 'Anosibe', '+261340456748');


create sequence seqEntreeStock
    START WITH 1
    INCREMENT BY 1
    MINVALUE 1
    MAXVALUE 9999
    CACHE 1;

insert into entreeStock(idEntreeStock,dateEntree,idArticle,PU,quantite,idMagasin,reste) values ('ES1','2023-10-01',2,2500.0,1000,'MG1',1000);
insert into entreeStock(idEntreeStock,dateEntree,idArticle,PU,quantite,idMagasin,reste) values ('ES2','2023-10-05',3,3200.0,1000,'MG1',1000);


create sequence seqSortieStock
    START WITH 1
    INCREMENT BY 1
    MINVALUE 1
    MAXVALUE 9999
    CACHE 1;


insert into sortieStock(idSortieStock,dateSortie,idArticle,quantite,idMagasin) values ('SS1','2023-10-05',2,500,'MG1');


create sequence seqMouvement
    START WITH 1
    INCREMENT BY 1
    MINVALUE 1
    MAXVALUE 9999
    CACHE 1;

select art.reference,art.nomArticle,es.quantite,ss.quantite,mv.quantiteDisponible,es.PU,(es.PU*ss.quantite) as montant ,mg.nomMagasin
from mouvement as mv
join entreeStock as es on mv.idEntree = es.idEntreeStock
join sortieStock as ss on mv.idSortie = ss.idSortieStock
join article as art on es.idArticle = art.idArticle
join magasin as mg on es.idMagasin = mg.idMagasin
join unite as ut on art.idUnite = ut.idUnite
where ss.idArticle = 2
AND es.idArticle = 2
AND es.idMagasin ='MG1'
AND ss.idMagasin ='MG1' 
AND es.dateEntree BETWEEN '2023-10-01' AND '2023-11-16'
AND ss.dateSortie BETWEEN '2023-10-01' AND '2023-11-16';