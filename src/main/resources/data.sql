-- CREWS
INSERT INTO crew (id, name) VALUES ('2a7d1875-4d1a-4ddc-9001-0602a5719eaa', 'Straw Hat Pirates');
INSERT INTO crew (id, name) VALUES ('bc8a8b7d-a865-4994-972d-7275037ea172', 'Whitebeard Pirates');
INSERT INTO crew (id, name) VALUES ('e5849ca2-a2af-45d9-b00b-3b9b859c9e76', 'Crew to be deleted');

-- CREW POSITIONS
INSERT INTO crew_position (id, name) VALUES ('de48283d-723d-4890-831e-4482fa73934d', 'Captain');
INSERT INTO crew_position (id, name) VALUES ('7ea49456-64a2-426c-8221-cd559ba360ab', 'First mate');
INSERT INTO crew_position (id, name) VALUES ('02a4b120-73b1-4bd3-8703-ae4f3d02468d', 'Cook');
INSERT INTO crew_position (id, name) VALUES ('96571c52-0e84-458d-8c08-e60e46254a94', 'Doctor');
INSERT INTO crew_position (id, name) VALUES ('61e6d03a-4fc1-4e28-9e3b-78fbcd7baaec', 'Gunner');
INSERT INTO crew_position (id, name) VALUES ('4c826590-fcce-42b1-92e4-3cc6289cee7e', 'Shipwright');
INSERT INTO crew_position (id, name) VALUES ('41e7a76e-2f1e-43ca-8ab2-00055c8cd4de', 'Second Mate');
INSERT INTO crew_position (id, name) VALUES ('af751437-7b60-4a39-9b36-a1dd7fa1aa2e', 'Helmsman');
INSERT INTO crew_position (id, name) VALUES ('acb99c76-3b03-44b0-a2ec-761a36baef2e', 'Lookout');
INSERT INTO crew_position (id, name) VALUES ('3678807d-6b33-44c3-bd6c-1178d944b685', 'Musician');
INSERT INTO crew_position (id, name) VALUES ('835a4dfa-fbbb-41ac-b9cd-d80c6e8e8f82', 'Crew position to be deleted');

-- PIRATES
INSERT INTO pirate
  (id, name, aka, is_currently, crew_id, crew_position_id) 
VALUES
  ('505cefb4-2a9e-48e4-a678-e7459416f32e', 'Monkey D. Luffy', 'Straw Hat', 'FREE', '2a7d1875-4d1a-4ddc-9001-0602a5719eaa', 'de48283d-723d-4890-831e-4482fa73934d');
INSERT INTO pirate
  (id, name, aka, is_currently, crew_id, crew_position_id) 
VALUES
  ('8c2f8a2c-eefc-41bd-97f5-2d5763b036aa', 'Roronoa Zoro', 'Pirate Hunter', 'FREE', '2a7d1875-4d1a-4ddc-9001-0602a5719eaa', '7ea49456-64a2-426c-8221-cd559ba360ab');

INSERT INTO pirate
  (id, name, aka, is_currently, crew_id, crew_position_id) 
VALUES
  ('4df4f5df-ffef-4b43-9528-249495432c45', 'Edward Newgate', 'Whitebeard', 'FREE', 'bc8a8b7d-a865-4994-972d-7275037ea172', 'de48283d-723d-4890-831e-4482fa73934d');

INSERT INTO pirate
  (id, name, is_currently, crew_id, crew_position_id) 
VALUES
  ('2937e5f6-51b6-455d-96fb-9ff7b2d57cd6', 'Pirate to be deleted', 'FREE', 'bc8a8b7d-a865-4994-972d-7275037ea172', 'de48283d-723d-4890-831e-4482fa73934d');

-- BOUNTIES
INSERT INTO bounty 
  (id, value, reason_description, pirate_id)
VALUES
  ('8182ad64-46a6-4c3e-ae6b-12a6efba586b', 30000000, 'Defeated Buggy, Don Krieg, and Arlong', '505cefb4-2a9e-48e4-a678-e7459416f32e');
INSERT INTO bounty 
  (id, value, reason_description, pirate_id)
VALUES
  ('580dea8d-a16f-4082-a0f1-a7f3f237bf89', 400000000, 'Receveid after his participation in the Summit War of Marineford', '505cefb4-2a9e-48e4-a678-e7459416f32e');
INSERT INTO bounty 
  (id, value, pirate_id)
VALUES
  ('62fa0512-6a6a-47b5-ae79-0cba13d58eb1', 150000000, '505cefb4-2a9e-48e4-a678-e7459416f32e');


-- AVAILABLE UUIDS
-- 'ae8ccd2a-cc6f-40cb-9141-8e0425b12bb1'
-- '512242b3-79c0-43c5-acd7-6d3e23197557'
-- '28df5d00-5b01-4fae-97c6-a8d44705340a'
-- 'be994278-ceed-4604-ba11-7b4deb23fd62'
-- 'b155257a-e8f6-421d-9109-f20cbcab35fa'
-- '1ed1ced1-4113-41a1-b6ad-cb0dbda8a9de'
-- '9080e1ce-afd8-4ad6-a8c2-b74df7d4a5b4'
-- '7e8cb056-8c07-4904-b5c0-a372ded5ea21'
-- '58ff5f2f-f181-403c-af71-4c3c6608ab80'
-- 'ab969cce-6553-4348-88ad-aa670bd74873'
-- '7441a8b6-5e8c-4aea-a10a-c2029b037696'
-- '7936910a-fa89-4437-997f-17fe34f40cb6'
-- 'b5fbe8c9-160a-4c8d-8180-c73041b03e4a'
-- 'a1a2199a-4b72-4a96-9ac5-9a157bb9ffc6'
-- 'b9ea089a-19e4-46f2-8ef2-eaf7e044180c'
-- '7ba88d7d-d6c1-4238-b39f-d6cc85e983d6'
-- '5289c122-b3c6-4e7f-a5e5-7b8cef3ad681'
-- '7619a2a2-82d2-4d5d-8f95-07489a150c36'
-- '6d469521-7ce8-4f24-9e4d-7d2b9c82fcaa'
-- 'b4bd5629-d67f-47b0-a9fd-2a4da3053f8e'
-- 'b40a4d6d-01aa-4bea-a288-faa65405742c'
-- 'b97a7688-8e8f-47e2-8f8e-55170a6c06d4'
-- 'b11f6fbe-3bd3-4236-8aed-457407203ef1'
-- '64c65857-65ad-4014-87fd-e9aca37c3c77'
-- '095afc75-4224-4918-9208-4009877ed5c4'
-- '75b1038c-a9cf-4376-bd2a-94dc8440a76b'
-- 'c6bf4147-9726-4b47-a9cf-5a78fe85d730'
-- '18d175d0-3814-4a5b-b82f-b6e4eac4f0c4'
-- '99803f6d-39c3-4945-a070-538250c0dec6'
-- '72b528c6-0b79-46b2-bcd7-c895432999ae'
-- 'e241981d-2dc8-4d5f-8527-f6b4ecd87c50'
-- '7a7e6319-8eba-4d72-b16c-08ecc8486dfc'
-- '2198df1b-5036-4a50-9030-c44fb9651b7c'
-- '4f20856e-6732-4118-a867-8595997cac9f'
-- '55882b40-31cd-4e4b-a135-77ebc0f9a81d'
-- 'caa1fc6a-973f-4fff-88d1-66e6d767b7cc'
-- 'd52aafdd-1b30-47c4-8e09-7a3d44031423'
-- 'acb7f8fe-4da3-4573-ac46-8fa6afa413e9'
-- '630c6b08-d4db-4a13-afc5-c964901b0011'
-- '21c2c881-b643-49a8-a0c8-23c2cb04681c'
-- '2d506df9-8885-41ed-8b1a-f8b660d84688'
-- '7e7a83d1-0ad6-4664-8ee2-5304d18c2fc3'
-- '3855d07c-5fdc-4e03-a89b-e9e7b5cd5d24'
-- 'c37462b8-fa2f-4a82-b4c3-6bc7a2a48a0a'
-- 'e690fd52-60d1-4802-85d8-4387d7328e38'