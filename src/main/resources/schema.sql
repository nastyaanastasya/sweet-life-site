create table if not exists persistent_logins (
    username  varchar (64) not null,
    series    varchar (64) not null primary key,
    token     varchar (64) not null,
    last_used varchar (64) not null
);