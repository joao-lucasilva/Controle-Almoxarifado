
CREATE USER 'usuario'@'localhost' IDENTIFIED BY 'etec123';
GRANT ALL PRIVILEGES ON * . * TO 'usuario'@'localhost';
FLUSH PRIVILEGES;

create database projetoEtec;

use projetoEtec;

show variables like '%max_connections%';

set global max_connections = 1000;

create table if not exists tbl_produto(
id_prod int not null auto_increment,
constraint id_prod_pk primary key(id_prod),
nome_prod nvarchar(60) not null,
qtd_estoque int not null,
dt_cadastro datetime null DEFAULT NOW() not null
);

create table if not exists tbl_setor(
id_setor int not null auto_increment,
constraint id_setor_pk primary key(id_setor),
nome_setor nvarchar(60) not null
);

create table if not exists tbl_funcionario(
id_func int not null auto_increment,
constraint id_func_pk primary key(id_func),
nome_func nvarchar(60) not null,
id_setor_fk int,
constraint id_setor_fk foreign key (id_setor_fk) references tbl_setor on delete cascade on update cascade
);


create table if not exists tbl_almoxarifado(
id_almx int not null auto_increment,
constraint id_almx_pk primary key(id_almx),
id_nome_fk int,
constraint id_nome_fk foreign key (id_nome_fk) references tbl_funcionario on delete cascade on update cascade,
dt_retiro datetime null DEFAULT NOW() not null
);

create table if not exists tbl_lista(
id_lista int not null auto_increment,
constraint id_lista_pk primary key(id_lista),
qtd_retirada int not null,
id_prod_fk int,
constraint id_prod_fk foreign key (id_prod_fk) references tbl_produto on delete cascade on update cascade,
id_almx_fk int,
constraint id_almx_fk foreign key (id_almx_fk) references tbl_almoxarifado (id_almx) on delete cascade on update cascade,
dt_lista datetime default now()
);

CREATE VIEW VWlista AS select tbl_lista.qtd_retirada,tbl_produto.nome_prod,tbl_funcionario.nome_func, tbl_lista.dt_lista as dt_lista
from tbl_lista inner join tbl_produto on tbl_lista.id_prod_fk=tbl_produto.id_prod inner join
tbl_funcionario on tbl_lista.id_almx_fk=tbl_funcionario.id_func;
SELECT * FROM VWlista where dt_lista between '2018-08-11' and '2018-08-13';

delimiter $$
drop procedure lista $$
create procedure lista(
in prod VARCHAR(255),
in qtd INT,
in func VARCHAR(255)
)
begin

set @prod:=(select id_prod from  tbl_produto where nome_prod=prod);
insert into tbl_lista (qtd_retirada,id_prod_fk,id_almx_fk) values (qtd, @prod, 
(select id_almx from tbl_almoxarifado WHERE id_nome_fk = (select id_func from tbl_funcionario where nome_func = func)));

end $$;
delimiter ;


DELIMITER //
drop trigger if exists TGR_BAIXAPRODUTO //
CREATE TRIGGER TGR_BAIXAPRODUTO AFTER insert on tbl_lista
for each row
BEGIN

SET @qtd_retirada := NEW.qtd_retirada;
SET @id_produto := NEW.id_prod_fk;
SET @qtd_atual := (select qtd_estoque from tbl_produto where id_prod = @id_produto);
set @novaQtd := @qtd_atual - @qtd_retirada;
	update tbl_produto set qtd_estoque = @novaQtd where id_prod = @id_produto;
	
END//
DELIMITER ;	


DELIMITER //
drop trigger if exists TGR_CADALMX //
CREATE TRIGGER TGR_CADALMX AFTER insert on tbl_funcionario
for each row
BEGIN

SET @id_func := NEW.id_func;
insert into tbl_almoxarifado (id_nome_fk) value (@id_func);
	
END//
DELIMITER ;	

create view formulario as 
	select tbl_funcionario.*, tbl_lista.*, tbl_produto.*, tbl_setor.*, tbl_almoxarifado.*
		from tbl_funcionario 
		left join tbl_almoxarifado on tbl_funcionario.id_func = tbl_almoxarifado.id_nome_fk
		left join tbl_setor on tbl_funcionario.id_setor_fk = tbl_setor.id_setor
		left join  tbl_lista on tbl_almoxarifado.id_almx = tbl_lista.id_almx_fk
		left join tbl_produto on tbl_lista.id_prod_fk = tbl_produto.id_prod;

-- create view kk as select tbl_funcionario.*,tbl_lista.*,tbl_setor.*,tbl_almoxarifado.* 
-- from tbl_funcionario left join tbl_almoxarifado on tbl_funcionario.id_func = tbl_almoxarifado.id_nome_fk
-- left join tbl_setor on tbl_funcionario.id_setor_fk = tbl_setor.id_setor
-- left join  tbl_lista on tbl_almoxarifado.id_almx = tbl_lista.id_almx_fk
-- left join tbl_produto on tbl_lista.id_prod_fk = tbl_produto.id_prod;
-- select * from tbl_almoxarifado;
-- select id_almx,tbl_funcionario.nome_func,date_format(dt_retiro,'%d/%m/%Y') as dt_retiro from tbl_almoxarifado
-- inner join tbl_funcionario on tbl_funcionario.id_func=tbl_almoxarifado.id_nome_fk where nome_func='Alberto';

