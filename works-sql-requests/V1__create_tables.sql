create table if not exists sql_req_exo.Representation
(
    num_rep   int primary key auto_increment,
    titre_rep varchar(255) not null,
    lieu      varchar(255) not null
);

create table if not exists sql_req_exo.Musicien
(
    num_mus int primary key auto_increment,
    nom     varchar(255) not null,
    num_rep int          not null,
    constraint num_rep_foreign_key foreign key (num_rep) references Representation (num_rep)
);

create table if not exists sql_req_exo.Programmer
(
    date    DATE,
    num_rep int,
    tarif   int,
    constraint num_rep_fk foreign key (num_rep) references Representation (num_rep)
);

create table if not exists Departements
(
    DNO   int primary key auto_increment,
    DNOM  varchar(255) not null,
    DIR   varchar(255) not null,
    VILLE varchar(255) not null
);

create table if not exists Employes
(
    ENO int primary key auto_increment,
    ENOM varchar(255) not null,
    PROF varchar(255) not null,
    DATEEMB DATETIME not null,
    SAL double not null,
    COMM double default null,
    DNO int not null,
    constraint CONSTR_DNO_FK foreign key (DNO) references Departements(DNO)
);