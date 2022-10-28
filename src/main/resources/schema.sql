CREATE TABLE crew (
  id UUID NOT NULL PRIMARY KEY,
  name VARCHAR(100) NOT NULL
);

CREATE TABLE crew_position (
  id UUID NOT NULL PRIMARY KEY,
  name VARCHAR(100) NOT NULL
);

CREATE TABLE pirate (
  id UUID NOT NULL PRIMARY KEY,
  name VARCHAR(100) NOT NULL,
  aka VARCHAR(100) DEFAULT NULL,
  is_currently ENUM('FREE', 'IN_JAIL', 'DECEASED') NOT NULL,
  
  crew_id UUID NOT NULL,
  CONSTRAINT fk_pirate_crew FOREIGN KEY (crew_id) REFERENCES crew(id)
    ON UPDATE CASCADE
    ON DELETE CASCADE,
  
  crew_position_id UUID NOT NULL,
  CONSTRAINT fk_pirate_crew_position FOREIGN KEY (crew_position_id) REFERENCES crew_position(id)
    ON UPDATE CASCADE
    ON DELETE CASCADE
);

CREATE TABLE bounty (
  id UUID NOT NULL PRIMARY KEY,
  value DECIMAL,
  reason_description VARCHAR(250) DEFAULT NULL,
  pirate_id UUID NOT NULL,
  CONSTRAINT fk_bounty_pirate FOREIGN KEY (pirate_id) REFERENCES pirate(id)
    ON UPDATE CASCADE
    ON DELETE CASCADE
);