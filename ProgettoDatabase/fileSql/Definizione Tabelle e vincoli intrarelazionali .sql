CREATE DOMAIN FormatoEmail AS VARCHAR(64)
CHECK (VALUE LIKE '%@Studenti.Universita.it' OR VALUE LIKE '%Docenti.Universita.it');

CREATE DOMAIN ruolo AS VARCHAR(16)
CHECK (VALUE = 'Studente' OR VALUE = 'Insegnante');

CREATE TABLE Utente
(
    idUtente BIGSERIAL NOT NULL,
    Nome VARCHAR(32) NOT NULL,
    Cognome VARCHAR(32) NOT NULL,
    Email FormatoEmail NOT NULL, 
    Password VARCHAR(32) NOT NULL,
    Ruolo ruolo, 
    PRIMARY KEY(idUtente)
);

ALTER TABLE Utente ADD CONSTRAINT ConsistenzaRuolo CHECK((Ruolo = 'Studente' AND Email LIKE '%@Studenti.Universita.it') OR (Ruolo = 'Insegnante' AND Email LIKE '%@Docenti.Universita.it'));
ALTER TABLE Utente ADD UNIQUE(Email);

CREATE DOMAIN tipoQuiz VARCHAR(16)
CHECK (VALUE = 'Multipla' OR VALUE = 'Aperta' OR VALUE = 'Mista');

CREATE DOMAIN punteggioDmax AS FLOAT
CHECK (VALUE > 0 OR VALUE = 0);

CREATE DOMAIN punteggioDmin AS FLOAT
CHECK (VALUE < 0 OR VALUE = 0);

CREATE TABLE Test
(
    idTest BIGSERIAL NOT NULL,
	nomeTest VARCHAR(32),
    Data_Ora TIMESTAMP NOT NULL,
	TipoQuiz tipoQuiz NOT NULL,
    MaxPunteggio FLOAT DEFAULT 0,
    NumDomande INTEGER DEFAULT 0,
    PunteggioDMax punteggioDmax NOT NULL DEFAULT 0,
    PunteggioDMin punteggioDmin DEFAULT 0, 
    Categoria VARCHAR(32) NOT NULL,
    Insegnante BIGSERIAL NOT NULL,
    PRIMARY KEY (idTest),
    FOREIGN KEY (Insegnante) REFERENCES Utente (idUtente)
    ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE DOMAIN tipoDomanda AS VARCHAR(16)
CHECK (VALUE = 'Multipla' OR VALUE = 'Aperta');
                                                   
CREATE TABLE Domanda
(
    idDomanda BIGSERIAL NOT NULL,
    TipoDomanda tipoDomanda NOT NULL,
    TestoDomanda    VARCHAR(256)  NOT NULL,
    idTest    BIGSERIAL  NOT NULL,
    PRIMARY KEY (idDomanda),
    FOREIGN KEY (idTest) REFERENCES test (idTest)
    ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE Risposta (	
	idRisposta BIGSERIAL NOT NULL,
    idDomanda  BIGSERIAL    NOT NULL,
    TestoRisposta  VARCHAR(512),
    Corretta    BOOLEAN,
    PRIMARY KEY (idRisposta),
    FOREIGN KEY (idDomanda) REFERENCES Domanda (idDomanda)
    ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE DOMAIN StatoValutazione VARCHAR(32)
CHECK (VALUE = 'In fase di valutazione' OR VALUE = 'Valutato') DEFAULT 'In fase di valutazione';

CREATE TABLE IstanzaDiTest
(
   idIstanzaDiTest BIGSERIAL NOT NULL,
   Stato StatoValutazione,
   Risultato FLOAT DEFAULT 0,
   idTest BIGSERIAL NOT NULL,
   Studente BIGSERIAL NOT NULL,
   dataSostenuto TIMESTAMP NOT NULL,
   OrarioFine TIME,
   NumeroRCorrette INTEGER DEFAULT 0,
   NumeroRErrate INTEGER DEFAULT 0,
   PRIMARY KEY (idIstanzaDiTest),
   FOREIGN KEY (idTest) REFERENCES Test (idTest)
   ON DELETE CASCADE ON UPDATE CASCADE,
   FOREIGN KEY (Studente) REFERENCES Utente (idUtente)
   ON DELETE CASCADE ON UPDATE CASCADE
);

ALTER TABLE IstanzaDiTest ADD CONSTRAINT ConsistenzaOrarioFine CHECK (datasostenuto::TIME < OrarioFine);

CREATE TABLE RispostaUtente (
    idRispostaUtente  BIGSERIAL   NOT NULL,
    TestoRisposta    VARCHAR(256),
	punteggioRisposta FLOAT,
    idIstanzaDiTest BIGSERIAL NOT NULL,
    idDomanda BIGSERIAL NOT NULL,
    PRIMARY KEY (idRispostaUtente),
    FOREIGN KEY (idIstanzaDiTest) REFERENCES IstanzaDiTest (idIstanzaDiTest)
    ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (idDomanda) REFERENCES Domanda (idDomanda)
    ON DELETE CASCADE ON UPDATE CASCADE
);

ALTER TABLE Rispostautente ADD UNIQUE(idistanzaditest, iddomanda);
