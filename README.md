# mmluciano
TrabalhosJava
Utilizei o Eclipse para desenvolver o exercicio e o banco mysql

DADOS PARA O BANCO DE DADOS

/*senha root do mysql
123456

nome do banco de dados
autorizador
*/

// https://github.com/michelluciano/mmluciano.git

create table regras(
id_regra     int auto_increment not null primary key,
cd_procedimento    varchar(45) not null,
idade_regra        varchar(3) not null,
sexo_regra         varchar(45) not null,
permitido_regra    varchar(45) not null
);

insert into regras(cd_procedimento,idade_regra,sexo_regra,permitido_regra) values ('1234','10','M','SIM');
insert into regras(cd_procedimento,idade_regra,sexo_regra,permitido_regra) values ('4567','20','M','SIM');
insert into regras(cd_procedimento,idade_regra,sexo_regra,permitido_regra) values ('6789','10','F','SIM');
insert into regras(cd_procedimento,idade_regra,sexo_regra,permitido_regra) values ('6789','10','M','SIM');
insert into regras(cd_procedimento,idade_regra,sexo_regra,permitido_regra) values ('1234','20','M','SIM');
insert into regras(cd_procedimento,idade_regra,sexo_regra,permitido_regra) values ('4567','30','F','SIM');
