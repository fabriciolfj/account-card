create table customer (
  id bigint not null auto_increment,
  name varchar(60) not null,
  status_proposal varchar(15) not null,
  email varchar(60) not null,
  cpf varchar(14),
  primary key (id)
) engine=InnoDB default charset=utf8;