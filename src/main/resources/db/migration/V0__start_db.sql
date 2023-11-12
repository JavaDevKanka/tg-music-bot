create table IF NOT EXISTS music
(
    id      uuid        not null primary key,
    author  varchar(85) not null,
    country varchar(35) not null
);

create table IF NOT EXISTS song
(
    id           uuid         not null primary key,
    name         varchar(230) not null unique,
    created      timestamp    not null,
    style        varchar(50)  not null,
    release_date timestamp    not null,
    music_id uuid references music(id)
);

create index MBT_NAME_STYLE_IDX on song (name, style);