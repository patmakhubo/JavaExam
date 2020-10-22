DROP Database IF NOT EXISTS `registrants`;
CREATE DATABASE `registrants`;

CREATE TABLE `students` (
  `idnumber` int(20) NOT NULL,
  `name` varchar(255) NOT NULL,
  `surname` varchar(255) NOT NULL,
  `age` int(11) NOT NULL,
  `cellnumber` bigint(20) NOT NULL,
  `degree` varchar(255) NOT NULL
);

ALTER TABLE `students`
  ADD PRIMARY KEY (`idnumber`);
COMMIT;
