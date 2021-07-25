insert into address (id, city, houseno, postcode, street) values ('79785a91-5398-49f6-afb5-ccbdafa6248b', 'Bremen', '30', '28199', 'Neustadtswall');
insert into address (id, city, houseno, postcode, street) values ('dc2c4977-c616-4515-83dd-62b395158c1d', 'Hamburg', '177', '20146', 'Mittelweg');


insert into carstation values ('a25991e8-a436-4622-9a03-5f279edf0c6b','79785a91-5398-49f6-afb5-ccbdafa6248b');
insert into carstation values ('4cc85ba3-b348-4e2d-83a0-1399549c60fd','dc2c4977-c616-4515-83dd-62b395158c1d');


insert into rfid (id, chip_number) values ('56f2fc53-8c60-4a8d-a5f8-6125394e4585', 1084494845829);
insert into rfid (id, chip_number) values ('f57d1ca9-a075-4b40-914c-faf45d630f93', 1084494845829);


insert into tariff values ('ec0a8edb-7cc5-4ec7-bd09-8f34a325e04d','Student',25);
insert into tariff values ('97a53cb9-8cb1-4eb3-bc5e-76839bb53943','Standard',5);
insert into tariff values ('f96369d5-5aed-4b7c-b27a-113f459e0630','Exklusiv',35);


insert into car (id, active, category, consumption, description, fuel_aircondition, gps, license_plate, maintenance_date, mileage, price_per_hour, seats, transmission, type, stationid, rfid_id) values ('5b1a33b2-1b08-4cb1-9ba0-77d8d29fc0a2', true, 'E', 5.5, 'Opel Corsa', 'N', 'GHLF219', 'HB-CG-775', '2023-01-13', 129810, 20.0, 5, 'M', 'C', 'a25991e8-a436-4622-9a03-5f279edf0c6b', '56f2fc53-8c60-4a8d-a5f8-6125394e4585');
insert into car (id, active, category, consumption, description, fuel_aircondition, gps, license_plate, maintenance_date, mileage, price_per_hour, seats, transmission, type, stationid, rfid_id) values ('eb683eec-0d5a-430e-a4fd-89aa2d82ec0b', true, 'E', 6.1, 'VW Polo', 'N', 'oMXeMBI', 'HB-CG-768', '2021-07-15', 143400, 20.0, 5, 'M', 'C', 'a25991e8-a436-4622-9a03-5f279edf0c6b', '56f2fc53-8c60-4a8d-a5f8-6125394e4585');
insert into car (id, active, category, consumption, description, fuel_aircondition, gps, license_plate, maintenance_date, mileage, price_per_hour, seats, transmission, type, stationid, rfid_id) values ('d3517a8d-b432-47d0-8933-514f6b193d4b', true, 'C', 6.9, 'Ford Focus', 'R', 'XwRIkb1', 'HB-CG-655', '2022-04-19', 34019, 25.8, 5, 'M', 'D', 'a25991e8-a436-4622-9a03-5f279edf0c6b', '56f2fc53-8c60-4a8d-a5f8-6125394e4585');
insert into car (id, active, category, consumption, description, fuel_aircondition, gps, license_plate, maintenance_date, mileage, price_per_hour, seats, transmission, type, stationid, rfid_id) values ('d70a595b-875c-4522-81b9-cd16f5c9fcd8', true, 'F', 15.8, 'BMW 3er Touring', 'R', 'r3uoD1C', 'HB-CG-031', '2022-11-30', 66746, 48.1, 5, 'M', 'R', 'a25991e8-a436-4622-9a03-5f279edf0c6b', '56f2fc53-8c60-4a8d-a5f8-6125394e4585');
insert into car (id, active, category, consumption, description, fuel_aircondition, gps, license_plate, maintenance_date, mileage, price_per_hour, seats, transmission, type, stationid, rfid_id) values ('772fb451-3f3e-4174-a297-6d91d387f70e', true, 'C', 7.7, 'VW Golf', 'R', 'tShZQhZ', 'HB-CG-191', '2023-05-02', 34340, 30.3, 5, 'M', 'L', 'a25991e8-a436-4622-9a03-5f279edf0c6b', '56f2fc53-8c60-4a8d-a5f8-6125394e4585');
insert into car (id, active, category, consumption, description, fuel_aircondition, gps, license_plate, maintenance_date, mileage, price_per_hour, seats, transmission, type, stationid, rfid_id) values ('3c6048c1-e445-4738-9d10-6a89a098060d', true, 'C', 9.0, 'Mini Cooper Cabriolet', 'R', 'SRQaUDV', 'HB-CG-310', '2023-03-10', 144003, 35.2, 3, 'M', 'T', 'a25991e8-a436-4622-9a03-5f279edf0c6b', '56f2fc53-8c60-4a8d-a5f8-6125394e4585');

insert into car (id, active, category, consumption, description, fuel_aircondition, gps, license_plate, maintenance_date, mileage, price_per_hour, seats, transmission, type, stationid, rfid_id) values ('03fa7cec-c0a7-4e4e-964d-ad6a59f421a0', true, 'E', 5.5, 'Opel Corsa', 'N', 'jIOsWov', 'HH-CG-413', '2022-06-29', 130793, 20.0, 5, 'M', 'C', '4cc85ba3-b348-4e2d-83a0-1399549c60fd', '56f2fc53-8c60-4a8d-a5f8-6125394e4585');
insert into car (id, active, category, consumption, description, fuel_aircondition, gps, license_plate, maintenance_date, mileage, price_per_hour, seats, transmission, type, stationid, rfid_id) values ('79727911-fd17-4c19-b267-30d79ca89cc2', true, 'E', 6.1, 'VW Polo', 'N', 'KlSgSnL', 'HH-CG-839', '2022-12-29', 92690, 20.0, 5, 'M', 'C', '4cc85ba3-b348-4e2d-83a0-1399549c60fd', '56f2fc53-8c60-4a8d-a5f8-6125394e4585');
insert into car (id, active, category, consumption, description, fuel_aircondition, gps, license_plate, maintenance_date, mileage, price_per_hour, seats, transmission, type, stationid, rfid_id) values ('61258629-095a-4028-8650-f35849d2a64d', true, 'C', 6.9, 'Ford Focus', 'R', 'vmlm9pJ', 'HH-CG-319', '2022-06-14', 120166, 35.8, 5, 'M', 'D', '4cc85ba3-b348-4e2d-83a0-1399549c60fd', '56f2fc53-8c60-4a8d-a5f8-6125394e4585');
insert into car (id, active, category, consumption, description, fuel_aircondition, gps, license_plate, maintenance_date, mileage, price_per_hour, seats, transmission, type, stationid, rfid_id) values ('52ff2c9c-0166-4984-b1e1-c452bd152990', true, 'F', 15.8, 'BMW 3er Touring', 'R', 'EDDneNu', 'HH-CG-591', '2022-02-25', 50239, 48.1, 5, 'M', 'R', '4cc85ba3-b348-4e2d-83a0-1399549c60fd', '56f2fc53-8c60-4a8d-a5f8-6125394e4585');
insert into car (id, active, category, consumption, description, fuel_aircondition, gps, license_plate, maintenance_date, mileage, price_per_hour, seats, transmission, type, stationid, rfid_id) values ('5dfe4167-7fb7-4515-8819-35f8c5d79b49', true, 'C', 7.7, 'VW Golf', 'R', 'bWHKtiD', 'HH-CG-876', '2022-06-11', 29085, 30.3, 5, 'M', 'L', '4cc85ba3-b348-4e2d-83a0-1399549c60fd', '56f2fc53-8c60-4a8d-a5f8-6125394e4585');
insert into car (id, active, category, consumption, description, fuel_aircondition, gps, license_plate, maintenance_date, mileage, price_per_hour, seats, transmission, type, stationid, rfid_id) values ('fd9cfab9-0bf2-42f4-9901-47456784284c', true, 'C', 9.0, 'Mini Cooper Cabriolet', 'R', 'dINUM2R', 'HH-CG-094', '2022-10-05', 65326, 35.2, 3, 'M', 'T', '4cc85ba3-b348-4e2d-83a0-1399549c60fd', '56f2fc53-8c60-4a8d-a5f8-6125394e4585');





