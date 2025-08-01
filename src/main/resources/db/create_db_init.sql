create table t_team
(
    id               uuid      default gen_random_uuid() not null
        primary key,
    name             text                                not null,
    created_at       timestamp default CURRENT_TIMESTAMP not null,
    modified_at      timestamp default CURRENT_TIMESTAMP not null,
    default_location varchar(10),
    product          text                                not null
);

alter table t_team
    owner to postgres;

create table t_rack
(
    id               uuid      default gen_random_uuid() not null
        primary key,
    serial_number    varchar(20)                         not null
        unique,
    team_id          uuid                                not null
        references t_team,
    created_at       timestamp default CURRENT_TIMESTAMP,
    default_location varchar(10),
    status           varchar(20)
        constraint rack_status_types
            check (((status)::text = 'AVAILABLE'::text) OR ((status)::text = 'BOOKED'::text) OR
                   ((status)::text = 'UNAVAILABLE'::text)),
    modified_at      timestamp default CURRENT_TIMESTAMP not null
);

alter table t_rack
    owner to postgres;

create table t_rack_asset
(
    id        uuid default gen_random_uuid() not null
        primary key,
    asset_tag varchar(10)                    not null,
    rack_id   uuid                           not null
        references t_rack
);

alter table t_rack_asset
    owner to postgres;

create table t_team_member
(
    id          uuid      default gen_random_uuid() not null
        primary key,
    team_id     uuid      default gen_random_uuid()
        references t_team,
    name        text                                not null,
    created_at  timestamp default CURRENT_TIMESTAMP not null,
    modified_at timestamp default CURRENT_TIMESTAMP not null,
    ctw_id      text                                not null
);

alter table t_team_member
    owner to postgres;

create table t_booking
(
    id           uuid      default gen_random_uuid() not null
        primary key,
    rack_id      uuid      default gen_random_uuid()
        references t_rack,
    requester_id uuid      default gen_random_uuid()
        references t_team_member,
    book_from    timestamp default CURRENT_TIMESTAMP not null,
    book_to      timestamp default CURRENT_TIMESTAMP not null,
    created_at   timestamp default CURRENT_TIMESTAMP not null,
    modified_at  timestamp default CURRENT_TIMESTAMP not null
);

alter table t_booking
    owner to postgres;


