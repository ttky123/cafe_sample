create table if not exists owner
(
    phone_number varchar(255) not null
    primary key,
    password     varchar(255) null,
    role         varchar(255) null
    ) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

create table if not exists product
(
    cost            double       null,
    price           double       null,
    expiration_date datetime(6)  null,
    id              bigint       not null
    primary key,
    barcode         varchar(255) null,
    category        varchar(255) null,
    description     varchar(255) null,
    name            varchar(255) null,
    phone_number    varchar(255) null,
    size            varchar(255) null,
    initial_sound   varchar(255) null,
    constraint FKolllpwexdrqydrwh0w2gylk4u
    foreign key (phone_number) references owner (phone_number)
    ) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

create table if not exists product_seq
(
    next_val bigint null
) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
