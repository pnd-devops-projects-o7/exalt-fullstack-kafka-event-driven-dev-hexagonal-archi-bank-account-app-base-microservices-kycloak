#insert data into tables using procedure
DELIMITER &&
CREATE PROCEDURE InsertRepresentations()
BEGIN
    DECLARE
        i INT DEFAULT 1;
    WHILE i <= 1000
        DO
            INSERT INTO sql_req_exo.Representation (titre_rep, lieu)
            VALUES (CONCAT('Representation ', i), CONCAT('Lieu ', CHAR(64 + MOD(i, 10) + 1)));
            SET i = i + 1;
        END WHILE;
END &&
DELIMITER ;

CALL InsertRepresentations();

DELIMITER &&
CREATE PROCEDURE InsertMusiciens()
BEGIN
    DECLARE
        i INT DEFAULT 1;
    WHILE i <= 1000
        DO
            INSERT INTO sql_req_exo.Musicien (nom, num_rep)
            VALUES (CONCAT('Musicien ', i), FLOOR(1 + RAND() * 100));
            SET i = i + 1;
        END WHILE;
end &&
DELIMITER ;

CALL InsertMusiciens();

DELIMITER &&
CREATE PROCEDURE InsertProgrammer()
BEGIN
    DECLARE i INT DEFAULT 1;
    WHILE i <= 1000
        DO
            INSERT INTO sql_req_exo.Programmer (date, num_rep, tarif)
            VALUES (DATE_ADD(CURDATE(), INTERVAL i DAY), FLOOR(1 + RAND() * 100), FLOOR(50 + RAND() * 150));
            SET i = i + 1;
        END WHILE;
END &&
DELIMITER ;

CALL InsertProgrammer();

DELIMITER $$
CREATE PROCEDURE InsertDepartements()
BEGIN
    DECLARE i INT DEFAULT 1;
    WHILE i <= 100 DO
            INSERT INTO Departements (DNOM, DIR, VILLE)
            VALUES (
                       CONCAT('Department ', i),
                       CONCAT('Director ', i),
                       CONCAT('City ', i)
                   );
            SET i = i + 1;
        END WHILE;
END$$

DELIMITER ;

-- Call the procedure
CALL InsertDepartements();

DELIMITER $$

CREATE PROCEDURE InsertEmployes()
BEGIN
    DECLARE i INT DEFAULT 1;
    DECLARE dept_id INT DEFAULT 1;
    WHILE i <= 10000 DO
            SET dept_id = 1 + (i MOD 100); -- Distribute employees among 100 departments
            INSERT INTO Employes (ENOM, PROF, DATEEMB, SAL, COMM, DNO)
            VALUES (
                       CONCAT('Employee ', i),
                       CONCAT('Profession ', i),
                       DATE_ADD('2020-01-01', INTERVAL i DAY),
                       50000 + (i * 100),
                       IF(i MOD 5 = 0, 1000 + (i * 50), NULL), -- Every 5th employee gets a commission
                       dept_id
                   );
            SET i = i + 1;
        END WHILE;
END$$

DELIMITER ;

-- Call the procedure
CALL InsertEmployes();
