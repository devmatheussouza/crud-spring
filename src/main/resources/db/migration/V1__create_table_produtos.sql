create table produtos(
    id bigint not null auto_increment,
    nome varchar(200) not null,
    marca varchar(100) not null,
    valor varchar(20) not null,
    url varchar(400) not null,
    primary key (id)
)