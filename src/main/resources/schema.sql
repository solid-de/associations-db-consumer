
create table if not exists cities(
  city_id int not null GENERATED ALWAYS AS IDENTITY  PRIMARY KEY,
  city_name varchar(100) not null,
  postal_code  varchar(100) not null UNIQUE
);



create table if not exists associations(
  association_id int not null GENERATED ALWAYS AS IDENTITY  PRIMARY KEY,
  association_name varchar(200) not null UNIQUE,
  association_domain_name varchar(200),
  association_sector_label varchar(200),
  association_target_audience varchar(200),
  association_geo_sector varchar(200),
  external_id varchar(200),

  city_id int not null,
  foreign key (city_id) references cities(city_id)
);