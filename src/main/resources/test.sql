create db test;
use test;

CREATE TABLE `dosen` (
  `kodedosen` varchar(20) NOT NULL,
  `namadosen` varchar(20) NOT NULL,
  `usia` int(11) NOT NULL,
  PRIMARY KEY (`kodedosen`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE `mahasiswa` (
  `nim` varchar(20) NOT NULL DEFAULT '',
  `nama` varchar(20) DEFAULT NULL,
  `kodedosen` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`nim`),
  KEY `fk_kodedosen` (`kodedosen`),
  CONSTRAINT `fk_kodedosen` FOREIGN KEY (`kodedosen`) REFERENCES `dosen` (`kodedosen`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
