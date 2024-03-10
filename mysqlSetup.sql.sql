CREATE DATABASE IF NOT EXISTS countries;

CREATE TABLE IF NOT EXISTS Countries (
                                         ID INT AUTO_INCREMENT PRIMARY KEY,
                                         Name VARCHAR(50) NOT NULL,
    Capital VARCHAR(50) NOT NULL,
    Population INT NOT NULL,
    Religion VARCHAR(50),
    Area DECIMAL(10,2) NOT NULL -- Assuming area is measured in square kilometers
    );

INSERT INTO Countries (Name, Capital, Population, Religion, Area) VALUES
                                                                      (1, 'United States', 'Washington, D.C.', 331449281, 'Christianity', 9833517),
                                                                      (2, 'Canada', 'Ottawa', 38005238, 'Christianity', 9984670),
                                                                      (3, 'United Kingdom', 'London', 67886011, 'Christianity', 242495),
                                                                      (4, 'Germany', 'Berlin', 83783942, 'Christianity', 357386),
                                                                      (5, 'Australia', 'Canberra', 25788227, 'Christianity', 7692024),
                                                                      (6, 'Brazil', 'Brasília', 213993437, 'Christianity', 8515767),
                                                                      (7, 'India', 'New Delhi', 1393409038, 'Hinduism', 3287263),
                                                                      (8, 'China', 'Beijing', 1444216107, 'Buddhism', 9596960),
                                                                      (9, 'South Africa', 'Pretoria', 59308690, 'Christianity', 1221037),
                                                                      (10, 'New Zealand', 'Wellington', 4822233, 'Christianity', 270467);