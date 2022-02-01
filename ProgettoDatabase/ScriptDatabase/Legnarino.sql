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
   FOREIGN KEY (idTest) REFERENCES Test (idTest),
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

--calcolo automatico del ruolo dell'utente a partire dall'email
CREATE FUNCTION CalcolaRuolo()
RETURNS TRIGGER
AS $body$
BEGIN
    IF NEW.Email LIKE '%@Studenti.Universita.it' THEN 
	    UPDATE Utente
		    SET Ruolo = 'Studente'
		WHERE idUtente = NEW.idUtente;
	ELSE 
	    UPDATE Utente
		    SET Ruolo = 'Insegnante'
		WHERE idUtente = NEW.idUtente;
	END IF;
	RETURN NEW;
END;
$body$ LANGUAGE plpgsql;

CREATE TRIGGER ruolo
AFTER INSERT ON Utente
FOR EACH ROW
EXECUTE PROCEDURE CalcolaRuolo();

--un test può essere registrato solamente da un insegnante 
CREATE FUNCTION ControlloDocenti()
RETURNS TRIGGER
AS $body$
DECLARE 
    _Ruolo VARCHAR(10);
BEGIN 
    SELECT Ruolo INTO _Ruolo
	FROM Utente 
	WHERE NEW.Insegnante = idUtente;
	
	IF _Ruolo = 'Studente' THEN 
	    ROLLBACK;
	END IF;
	RETURN NEW;
END;
$body$ LANGUAGE plpgsql;

CREATE TRIGGER CheckDocenti
BEFORE INSERT ON Test
FOR EACH ROW 
EXECUTE PROCEDURE ControlloDocenti();

--Aggiorna il numDomande e il punteggio totale ogni volta che si inserisce una nuova domanda

CREATE FUNCTION AggiornaDatiTestInsert()
RETURNS TRIGGER
AS $body$
DECLARE 
    PunteggioMassimoD Test.punteggiodmax%TYPE;
BEGIN 

    SELECT punteggiodmax INTO PunteggioMassimoD
	FROM Test
	WHERE idTest = NEW.idTest;
	
    UPDATE Test
	    SET numdomande = numdomande + 1, maxpunteggio = maxpunteggio + PunteggioMassimoD
	WHERE idTest = NEW.idTest;
	
	RETURN NEW;
END;
$body$ LANGUAGE plpgsql;

CREATE TRIGGER AggiornadatiTestInsert
AFTER INSERT ON Domanda
FOR EACH ROW 
EXECUTE PROCEDURE AggiornaDatiTestInsert();

--Aggiorna il numDomande e il punteggio totale ogni volta che si cancella una domanda

CREATE FUNCTION AggiornaDatiTestDelete()
RETURNS TRIGGER
AS $body$
DECLARE 
    PunteggioMassimoD Test.punteggiodmax%TYPE;
BEGIN 

    SELECT punteggiodmax INTO PunteggioMassimoD
	FROM Test
	WHERE idTest = OLD.idTest; 
	
    UPDATE Test
	    SET numdomande = numdomande - 1, maxpunteggio = maxpunteggio - PunteggioMassimoD
	WHERE idTest = OLD.idTest;
	RETURN NEW;
END;
$body$ LANGUAGE plpgsql;

CREATE TRIGGER AggiornaDatiTestDelete
AFTER DELETE ON Domanda
FOR EACH ROW 
EXECUTE PROCEDURE AggiornaDatiTestDelete();


--Verifica che gli inserimenti di domande siano consistenti con la tipologia di quiz:
--Una domanda aperta non può referenziare un test a risposta multipla 
--Una domanda chiusa non può referenziare un test a risposta aperta

CREATE FUNCTION CheckTest()
RETURNS TRIGGER
AS $body$
DECLARE
    _tipoQuiz Test.tipoQuiz%TYPE;
BEGIN 
    SELECT TipoQuiz INTO _tipoQuiz
	FROM Test
	WHERE NEW.idTest = idTest;
	
	IF _tipoQuiz = 'Aperta' AND NEW.TipoDomanda = 'Multipla' THEN 
	    ROLLBACK;
	ELSE 
	    IF _tipoQuiz = 'Multipla' AND NEW.TipoDomanda = 'Aperta' THEN
	        ROLLBACK;
	    END IF;
	END IF;
	RETURN NEW;
END;
$body$ LANGUAGE plpgsql;

CREATE TRIGGER CheckTestD
BEFORE INSERT ON Domanda
FOR EACH ROW 
EXECUTE PROCEDURE CheckTest();

--Verifica che solamente una domanda a risposta multipla abbia associato un set di risposte automatizzate

CREATE FUNCTION ControlloRisposteAutomatizzate()
RETURNS TRIGGER
AS $body$
DECLARE 
    _TipoDomanda VARCHAR(10);
BEGIN 
    SELECT TipoDomanda INTO _TipoDomanda
	FROM Domanda
	WHERE NEW.idDomanda = idDomanda;
	
	IF _TipoDomanda = 'Aperta' THEN 
	    ROLLBACK;
	END IF;
	RETURN NEW;
END;
$body$ LANGUAGE plpgsql;

CREATE TRIGGER IntegritaRisposteAutomatizzate
BEFORE INSERT ON Risposta
FOR EACH ROW 
EXECUTE PROCEDURE ControlloRisposteAutomatizzate();

--Si assicura che un istanza di test possa essere aperta esclusivamente da uno studente

CREATE FUNCTION ControlloRisposteStudenti()
RETURNS TRIGGER
AS $body$
DECLARE 
    _Ruolo VARCHAR(10);
BEGIN 
    SELECT Ruolo INTO _Ruolo
	FROM Utente 
	WHERE NEW.Studente = idUtente;
	
	IF _Ruolo = 'Insegnante' THEN 
	    ROLLBACK;
	END IF;
	RETURN NEW;
END;
$body$ LANGUAGE plpgsql;

CREATE TRIGGER CheckRisposteUtente
BEFORE INSERT ON IstanzaDiTest
FOR EACH ROW 
EXECUTE PROCEDURE ControlloRisposteStudenti();

--Un test non può essere sostenuto in una data antecedente alla sua pubblicazione

CREATE FUNCTION CheckDataIstanzaTest()
RETURNS TRIGGER
AS $body$
DECLARE
    _dataS Test.data_ora%TYPE;
BEGIN 
    SELECT data_ora INTO _dataS
	FROM Test
	WHERE NEW.idTest = idTest;
	
	IF _dataS > NEW.DataSostenuto THEN
	    ROLLBACK;
	END IF;
	RETURN NEW;
END;
$body$ LANGUAGE plpgsql;

CREATE TRIGGER CheckDataIstanza
BEFORE INSERT ON IstanzaDiTest
FOR EACH ROW 
EXECUTE PROCEDURE CheckDataIstanzaTest();

--Una rispostaUtente fornita ad una domanda a risposta multipla deve rientrare in una delle risposte possibili

CREATE FUNCTION CheckConsistenzaRisposteUtente()
RETURNS TRIGGER 
AS $body$
DECLARE
    risp CURSOR FOR
	    SELECT testorisposta
		FROM risposta
		WHERE NEW.idDomanda = idDomanda;
		
	_risp Risposta.testoRisposta%TYPE;
	_flag INTEGER := 0;
	_tipoDomanda VARCHAR(10);
BEGIN
    SELECT TipoDomanda INTO _tipoDomanda
	FROM Domanda
	WHERE NEW.idDomanda = idDomanda;
	
	IF _tipoDomanda = 'Multipla' THEN
        OPEN risp;
	        LOOP
		        EXIT WHEN NOT FOUND OR _flag = 1;
			    FETCH risp INTO _risp;
			    IF _risp = NEW.TestoRisposta THEN
			        _flag = 1;
			    END IF;
		    END LOOP;
	    CLOSE risp;
	
	    IF _flag = 0 THEN 
	        ROLLBACK;
		END IF;
	END IF;
	
	RETURN NEW;
END;

$body$ LANGUAGE plpgsql;
CREATE TRIGGER CheckRisposteUtente
BEFORE INSERT ON RispostaUtente
FOR EACH ROW 
EXECUTE PROCEDURE CheckConsistenzaRisposteUtente();

--Calcola il punteggio totalizzato sui quesiti a risposta multipla

CREATE FUNCTION CalcoloPunteggioRispostaChiusa()
RETURNS TRIGGER 
AS $body$
DECLARE
    Risposte CURSOR FOR
 SELECT U.TestoRisposta rispUtente, R.TestoRisposta rispcorretta, D.iddomanda dom
 FROM rispostautente U, domanda D, risposta R
 WHERE U.iddomanda = D.iddomanda AND D.iddomanda = R.iddomanda AND U.idistanzaDiTest = NEW.idIstanzaDiTest AND D.tipoDomanda = 'Multipla' AND R.Corretta = 'true';
					
	punteggioMin Test.punteggioDmin%TYPE;
	punteggioMax Test.punteggioDmax%TYPE;
	Quiz Test.tipoQuiz%TYPE;
	domandeCorrette INTEGER := 0;
	domandeErrate INTEGER := 0;
	
BEGIN

    SELECT punteggioDmin, punteggioDmax, TipoQuiz INTO punteggioMin, PunteggioMax, Quiz
	FROM Test
	WHERE idTest = NEW.idTest;
	
	FOR I IN Risposte 
	LOOP
	    IF I.rispUtente = I.rispCorretta THEN 
		    UPDATE RispostaUtente
			SET punteggioRisposta = punteggioMax
            WHERE idIstanzaDiTest = NEW.idIstanzaDiTest AND idDomanda = I.dom;	
			domandeCorrette := domandeCorrette + 1;
			
			ELSE 
		    UPDATE RispostaUtente
			SET punteggioRisposta = punteggioMin
		    WHERE idIstanzaDiTest = NEW.idIstanzaDiTest AND idDomanda = I.dom;
			domandeErrate := domandeErrate + 1;
		END IF;
	END LOOP;
	
	UPDATE ISTANZADITEST
	    SET numerorcorrette = domandeCorrette, numerorerrate = domandeErrate
	WHERE idIstanzaDiTest = NEW.idIstanzaDiTest;
	
	IF Quiz = 'Multipla' THEN 
	    UPDATE ISTANZADITEST
		    SET Stato = 'Valutato'
		WHERE idIstanzaDiTest = NEW.idIstanzaDiTest;
	END IF;
	
	RETURN NEW;
END;

$body$ LANGUAGE plpgsql;

CREATE TRIGGER CompletamentoTest
AFTER UPDATE OF OrarioFine ON ISTANZADITEST
FOR EACH ROW 
EXECUTE PROCEDURE CalcoloPunteggioRispostaChiusa();

--Si assicura che il voto assegnanto ad una risposta rientri tra il minimo e massimo consentito e aggiorna risultati

CREATE FUNCTION CorrezioneDomandaRispostaAperta()
RETURNS TRIGGER
AS $body$
DECLARE 
    tipoD Domanda.tipodomanda%TYPE;
	punteggioMin Test.punteggioDmin%TYPE;
	punteggioMax Test.punteggioDmax%TYPE;
BEGIN
    SELECT tipoDomanda INTO tipoD
	FROM Domanda
	WHERE idDomanda = NEW.idDomanda;
	
	SELECT punteggioDmin, punteggioDmax INTO punteggioMin, PunteggioMax
	FROM Test T, Domanda D
	WHERE T.idTest = D.idTest AND D.idDomanda = NEW.idDomanda;
	
	IF NEW.punteggiorisposta > PunteggioMax OR NEW.punteggiorisposta < punteggioMin THEN
	    ROLLBACK;
	ELSE 
	    UPDATE ISTANZADITEST
		    SET risultato = risultato + NEW.punteggiorisposta
		WHERE idIstanzaDiTest = NEW.idIstanzaDiTest;
	END IF;
	
	RETURN NEW;
END;

$body$ LANGUAGE plpgsql;

CREATE TRIGGER correzioneDAperte
BEFORE UPDATE OF punteggiorisposta ON rispostaUtente
FOR EACH ROW
EXECUTE PROCEDURE CorrezioneDomandaRispostaAperta();



CREATE FUNCTION ConsistenzaDomandeRispostaUtente()
RETURNS TRIGGER
AS $body$
DECLARE
    testDomanda Test.idTest%TYPE;
	testIstanzaDiTest Test.idTest%TYPE;
BEGIN 
    SELECT idTest INTO testDomanda
	FROM Domanda
	WHERE idDomanda = NEW.idDomanda;
	
	SELECT idTest INTO testIstanzaDiTest
	FROM IstanzaDiTest
	WHERE idistanzaDiTest = NEW.idIstanzaDiTest;
	
	IF testDomanda <> testIstanzaDiTest THEN 
	    ROLLBACK;
    END IF;
	RETURN NEW;
END;

$body$ LANGUAGE plpgsql;

CREATE TRIGGER ConsistenzaRisposteUtente
BEFORE INSERT ON rispostaUtente
FOR EACH ROW
EXECUTE PROCEDURE ConsistenzaDomandeRispostaUtente();

INSERT INTO Utente (idUtente, nome, cognome, email, password) VALUES(1, 'Silvio', 'Barra',  'Silvio.Barra@Docenti.Universita.it', 'La meravigliosa Salernitana');
INSERT INTO Utente (idUtente, nome, cognome, email, password) VALUES(2, 'Francesca', 'Cioffi', 'Cioffifr@Docenti.Universita.it', 'Catenacci pesanti');
INSERT INTO Utente (idUtente, nome, cognome, email, password) VALUES(3,'Antonio', 'Legnante', 'anto.legnante@Studenti.Universita.it', 'NURAPDUUDE');
INSERT INTO Utente (idUtente, nome, cognome, email, password) VALUES(4, 'Porfirio', 'Tramontana', 'ptramont@Docenti.Universita.it', 'Procida');
INSERT INTO Utente (idUtente, nome, cognome, email, password) VALUES(5, 'Iolanda', 'Balletta', 'I.Balletta.@Docenti.Universita.it', 'Fedone scritto da Socrate');
INSERT INTO Utente (idUtente, nome, cognome, email, password) VALUES(6, 'Daniele', 'Castorina', 'DanieleCastorina@Docenti.Universita.it', 'password5');
INSERT INTO Utente (idUtente, nome, cognome, email, password) VALUES(7, 'Vincenzo ', 'Pannone', 'Vinc.Pannone@Studenti.Universita.it', 'password6');
INSERT INTO Utente (idUtente, nome, cognome, email, password) VALUES(8, 'Alfredo ', 'Laino', 'Alfry.Laino@Studenti.Universita.it', 'Non era vera Analisi Matematica');
INSERT INTO Utente (idUtente, nome, cognome, email, password) VALUES(9, 'Silvia ', 'Rossi', 'Silvia.Rossi@Docenti.Universita.it', 'password7');
INSERT INTO Utente (idUtente, nome, cognome, email, password) VALUES(10, 'Giovanni ', 'Cutolo', 'Giovanni.Cutolo@Docenti.Universita.it', 'Quanto è bella Algebra');

INSERT INTO Test (idtest, nometest, data_ora, punteggiodmax, punteggiodmin, tipoquiz, categoria, insegnante)
VALUES (1, 'Prova intercorso', '2022-01-12 18:05:36', 2, -1, 'Mista', 'Basi di dati', 1);
INSERT INTO Test (idtest, nometest, data_ora, punteggiodmax, tipoquiz, categoria, insegnante)
VALUES (2, 'Spazi Vettoriali', '2022-01-12 15:35:26', 1, 'Mista', 'Algebra Lineare', 2);
INSERT INTO Test (idtest, nometest, data_ora, punteggiodmax, punteggiodmin, tipoquiz, categoria, insegnante)
VALUES (3, 'Gruppi algebrici', '2022-01-16 17:14:28', 1, -2, 'Multipla', 'Algebra', 10);
INSERT INTO Test (idtest, nometest, data_ora, punteggiodmax, punteggiodmin, tipoquiz, categoria, insegnante)
VALUES (4, 'Java', '2022-01-08 09:23:21', 2, -2, 'Multipla', 'Object orientation', 4);
INSERT INTO Test (idtest, nometest, data_ora, punteggiodmax, punteggiodmin, tipoquiz, categoria, insegnante)
VALUES (5, 'Codifica binaria', '2022-01-11 10:20:19', 2, -1, 'Aperta', 'Architettura degli elaboratori', 9);
INSERT INTO Test (idtest, nometest, data_ora, punteggiodmax, punteggiodmin, tipoquiz, categoria, insegnante)

VALUES (6, 'Algebra elementare', '2022-01-14 18:23:36', 2, -2, 'Multipla', 'Analisi Matematica', 6);

INSERT INTO Domanda (iddomanda, tipoDomanda, testoDomanda, idtest) VALUES (1, 'Multipla', 'La compilazione di un programma Java si effettua con il tool javac disponibile nel JDK?', 4);
INSERT INTO Domanda (iddomanda, tipoDomanda, testoDomanda, idtest) VALUES (2, 'Multipla', 'Quale delle seguenti sequenza di parole contiene solo Java keyword?', 4);
INSERT INTO Domanda (iddomanda, tipoDomanda, testoDomanda, idtest) VALUES (3, 'Multipla', 'String è un tipo di dato primitivo?', 4);
INSERT INTO Domanda (iddomanda, tipoDomanda, testoDomanda, idtest) VALUES (4, 'Multipla', 'La naming convention sulle classi prevede che il nome della classe venga scritto in minuscolo?', 4);
INSERT INTO Domanda (iddomanda, tipoDomanda, testoDomanda, idtest) VALUES (5, 'Multipla', 'Il tool javadoc.exe è disponibile anche nel JRE?', 4);

INSERT INTO Domanda (iddomanda, tipoDomanda, testoDomanda, idtest) VALUES (6, 'Multipla', 'Il Polinomio x^3y -4xy^2 + 5 è:', 6);
INSERT INTO Domanda (iddomanda, tipoDomanda, testoDomanda, idtest) VALUES (7, 'Multipla', 'Quali delle seguenti espressioni non è un polinomio?', 6);
INSERT INTO Domanda (iddomanda, tipoDomanda, testoDomanda, idtest) VALUES (8, 'Multipla', 'Un polinomio di primo grado è sempre omogeneo?', 6);
INSERT INTO Domanda (iddomanda, tipoDomanda, testoDomanda, idtest) VALUES (9, 'Multipla', 'Se un polinomio è ordinato è anche completo?', 6);
INSERT INTO Domanda (iddomanda, tipoDomanda, testoDomanda, idtest) VALUES (10, 'Multipla', 'Il polinomio (x^2-2a) + (-y -x^2) è uguale ad:', 6);
INSERT INTO Domanda (iddomanda, tipoDomanda, testoDomanda, idtest) VALUES (11, 'Aperta', 'Descrivere la differenza tra indipendenza dei dati logica e indipendenza dei dati fisica?', 1);
INSERT INTO Domanda (iddomanda, tipoDomanda, testoDomanda, idtest) VALUES (12, 'Aperta', 'Cosa si intende per tipo di associazione ricorsiva? Se ne fornisca un esempio', 1);
INSERT INTO Domanda (iddomanda, tipoDomanda, testoDomanda, idtest) VALUES (13, 'Multipla', 'Quale tra le seguenti non è una funzione del database?', 1);
INSERT INTO Domanda (iddomanda, tipoDomanda, testoDomanda, idtest) VALUES (14, 'Multipla', 'Indica quale, tra le seguenti query, rappresentauna selezione + proiezione:', 1);
INSERT INTO Domanda (iddomanda, tipoDomanda, testoDomanda, idtest) VALUES (15, 'Aperta', 'Qual è la differenza tra specializzazione e generalizzazione?', 1);
INSERT INTO Domanda (iddomanda, tipoDomanda, testoDomanda, idtest) VALUES (16, 'Multipla', 'Quali sono le operazioni in Algebra relazionale che rendono la struttura delle relazioni chiusa?', 1);
INSERT INTO Domanda (iddomanda, tipoDomanda, testoDomanda, idtest) VALUES (17, 'Aperta', 'Descrivere il vincolo di entita?', 1);
INSERT INTO Domanda (iddomanda, tipoDomanda, testoDomanda, idtest) VALUES (18, 'Aperta', 'Descrivere il vincolo fondamentale del modello relazionale?', 1);
INSERT INTO Domanda (iddomanda, tipoDomanda, testoDomanda, idtest) VALUES (19, 'Aperta', 'In cosa consistono le proprietà ACID legate alle transizioni?', 1);
INSERT INTO Domanda (iddomanda, tipoDomanda, testoDomanda, idtest) VALUES (20, 'Multipla', 'Quale tra le seguenti frasi interpreta correttamente l’operatore AVG?', 1);
INSERT INTO Domanda (iddomanda, tipoDomanda, testoDomanda, idtest) VALUES (21, 'Aperta', 'Dare la definizione di Spazio Vettoriale?', 2);
INSERT INTO Domanda (iddomanda, tipoDomanda, testoDomanda, idtest) VALUES (22, 'Aperta', 'Dare la definizione di Trasformazione Lineare?', 2);
INSERT INTO Domanda (iddomanda, tipoDomanda, testoDomanda, idtest) VALUES (23, 'Aperta', 'Dare la definizione di Prodotto Scalare?', 2);

INSERT INTO Domanda (iddomanda, tipoDomanda, testoDomanda, idtest) VALUES (24, 'Multipla', 'se ∗ è un operazione commutativa ininsieme S, allora tutti gli elementi neutri a destra in (S,∗) sono anche neutri a sinistra?', 3);
INSERT INTO Domanda (iddomanda, tipoDomanda, testoDomanda, idtest) VALUES (25, 'Multipla', 'se ∗ è un operazione commutativa in S, esiste al massimo un elemento neutro a sinistra?', 3);
INSERT INTO Domanda (iddomanda, tipoDomanda, testoDomanda, idtest) VALUES (26, 'Multipla', 'se a e b sono due elementi neutri a sinistra distinti in (S,∗), in (S,∗) non esistono elementi neutri a destra?', 3);
INSERT INTO Domanda (iddomanda, tipoDomanda, testoDomanda, idtest) VALUES (27, 'Multipla', 'per ogni anello R ed ogni a, b ∈ R, (a+b)^2 = a^2+2ab+b^2?', 3);
INSERT INTO Domanda (iddomanda, tipoDomanda, testoDomanda, idtest) VALUES (28, 'Multipla', 'La seguente operazione è associativa? α:(a,b) ∈ Z×Z ↦ a + b + 1 ∈ Z', 3);

INSERT INTO Risposta (idrisposta, idDomanda, testorisposta, corretta) VALUES (1, 6, 'ordinato rispetto ad x e completo rispetto a y', TRUE);
INSERT INTO Risposta (idrisposta, idDomanda, testorisposta, corretta) VALUES (2, 6, 'ordinato rispetto ad x e ordinato rispetto a y', FALSE); 
INSERT INTO Risposta (idrisposta, idDomanda, testorisposta, corretta) VALUES (3, 6, 'completo rispetto ad x e completo rispetto a y', FALSE); 
INSERT INTO Risposta (idrisposta, idDomanda, testorisposta, corretta) VALUES (4, 6, 'completo rispetto ad x e ordinato rispetto a y', FALSE);

INSERT INTO Risposta (idrisposta, idDomanda, testorisposta, corretta) VALUES (5, 7, '2x', FALSE);
INSERT INTO Risposta (idrisposta, idDomanda, testorisposta, corretta) VALUES (6, 7, 'y^2/6 + ay -1', TRUE);
INSERT INTO Risposta (idrisposta, idDomanda, testorisposta, corretta) VALUES (7, 7, '1/3 * t', FALSE);
INSERT INTO Risposta (idrisposta, idDomanda, testorisposta, corretta) VALUES (8, 7, '1/4', FALSE);
INSERT INTO Risposta (idrisposta, idDomanda, testorisposta, corretta) VALUES (9, 8, 'Vero', FALSE);
INSERT INTO Risposta (idrisposta, idDomanda, testorisposta, corretta) VALUES (10, 8, 'Falso', TRUE);
INSERT INTO Risposta (idrisposta, idDomanda, testorisposta, corretta) VALUES (11, 9, 'Vero', FALSE);
INSERT INTO Risposta (idrisposta, idDomanda, testorisposta, corretta) VALUES (12, 9, 'Falso', TRUE);
INSERT INTO Risposta (idrisposta, idDomanda, testorisposta, corretta) VALUES (13, 10, 'a - 2', FALSE);
INSERT INTO Risposta (idrisposta, idDomanda, testorisposta, corretta) VALUES (14, 10, '4a + 3b', FALSE);
INSERT INTO Risposta (idrisposta, idDomanda, testorisposta, corretta) VALUES (15, 10, '-2a -y', TRUE); 

INSERT INTO Risposta (idrisposta, idDomanda, testorisposta, corretta) VALUES (20, 24, 'Vero', TRUE);
INSERT INTO Risposta (idrisposta, idDomanda, testorisposta, corretta) VALUES (21, 24, 'Falso', FALSE);
INSERT INTO Risposta (idrisposta, idDomanda, testorisposta, corretta) VALUES (22, 25, 'Vero', FALSE);
INSERT INTO Risposta (idrisposta, idDomanda, testorisposta, corretta) VALUES (23, 25, 'Falso', TRUE);
INSERT INTO Risposta (idrisposta, idDomanda, testorisposta, corretta) VALUES (24, 26, 'Vero', TRUE);
INSERT INTO Risposta (idrisposta, idDomanda, testorisposta, corretta) VALUES (25, 26, 'Falso', FALSE);
INSERT INTO Risposta (idrisposta, idDomanda, testorisposta, corretta) VALUES (26, 27, 'Vero', FALSE);
INSERT INTO Risposta (idrisposta, idDomanda, testorisposta, corretta) VALUES (27, 27, 'Falso', TRUE);
INSERT INTO Risposta (idrisposta, idDomanda, testorisposta, corretta) VALUES (28, 28, 'Vero', TRUE);
INSERT INTO Risposta (idrisposta, idDomanda, testorisposta, corretta) VALUES (29, 28, 'Falso', FALSE);

INSERT INTO Risposta (idrisposta, idDomanda, testorisposta, corretta) VALUES (30, 13, 'Deve gestire grandi quantità su dati', FALSE);
INSERT INTO Risposta (idrisposta, idDomanda, testorisposta, corretta) VALUES (31, 13, 'Deve garantire la correttezza dei dati', TRUE);
INSERT INTO Risposta (idrisposta, idDomanda, testorisposta, corretta) VALUES (32, 13, 'Deve garantire la condivisione', FALSE);
INSERT INTO Risposta (idrisposta, idDomanda, testorisposta, corretta) VALUES (33, 13, 'Deve garantire la persistenza', FALSE);

INSERT INTO Risposta (idrisposta, idDomanda, testorisposta, corretta) VALUES (34, 14, 'SELECT * FROM Colleghi', FALSE);
INSERT INTO Risposta (idrisposta, idDomanda, testorisposta, corretta) VALUES (35, 14, 'SELECT cognome, nome FROM Colleghi', FALSE);
INSERT INTO Risposta (idrisposta, idDomanda, testorisposta, corretta) VALUES (36, 14, 'SELECT DISTINCT cognome, nome FROM Colleghi WHERE città = ”Como”', TRUE);
INSERT INTO Risposta (idrisposta, idDomanda, testorisposta, corretta) VALUES (37, 14, 'SELECT C.nome FROM Colleghi C, Filiale F WHERE C.ID_filiale=F.ID', FALSE);

INSERT INTO Risposta (idrisposta, idDomanda, testorisposta, corretta) VALUES (38, 16, 'PROIEZIONE E SELEZIONE', FALSE);
INSERT INTO Risposta (idrisposta, idDomanda, testorisposta, corretta) VALUES (39, 16, 'PROIEZIONE, SELEZIONE, UNIONE, PRODOTTO CARTESIANO', FALSE);
INSERT INTO Risposta (idrisposta, idDomanda, testorisposta, corretta) VALUES (40, 16, 'PROIEZIONE, SELEZIONE, UNIONE, DIFFERENZA PRODOTTO CARTESIANO', TRUE);

INSERT INTO Risposta (idrisposta, idDomanda, testorisposta, corretta) VALUES (41, 20, 'Conta il numero di campi numerici NOT NULL', FALSE);
INSERT INTO Risposta (idrisposta, idDomanda, testorisposta, corretta) VALUES (42, 20, 'Calcola la media aritmetica dei valori NOT NULL', TRUE);
INSERT INTO Risposta (idrisposta, idDomanda, testorisposta, corretta) VALUES (43, 20, 'Calcola il massimo tra valori numerici', FALSE);
INSERT INTO Risposta (idrisposta, idDomanda, testorisposta, corretta) VALUES (44, 20, 'Calcola la media aritmetica dei valori comprendendo anche valori NOT NULL', FALSE);

INSERT INTO Risposta (idrisposta, idDomanda, testorisposta, corretta) VALUES (45, 1, 'Si', TRUE);
INSERT INTO Risposta (idrisposta, idDomanda, testorisposta, corretta) VALUES (46, 1, 'No', FALSE);

INSERT INTO Risposta (idrisposta, idDomanda, testorisposta, corretta) VALUES (47, 2, 'final, classe, int', FALSE);
INSERT INTO Risposta (idrisposta, idDomanda, testorisposta, corretta) VALUES (48, 2, 'abstruct, synchronized, static', TRUE);
INSERT INTO Risposta (idrisposta, idDomanda, testorisposta, corretta) VALUES (49, 2, 'method, void, boolean', FALSE);

INSERT INTO Risposta (idrisposta, idDomanda, testorisposta, corretta) VALUES (50, 3, 'Si', FALSE);
INSERT INTO Risposta (idrisposta, idDomanda, testorisposta, corretta) VALUES (51, 3, 'No', TRUE);

INSERT INTO Risposta (idrisposta, idDomanda, testorisposta, corretta) VALUES (52, 4, 'Si', FALSE);
INSERT INTO Risposta (idrisposta, idDomanda, testorisposta, corretta) VALUES (53, 4, 'No', TRUE);

INSERT INTO Risposta (idrisposta, idDomanda, testorisposta, corretta) VALUES (54, 5, 'Si', FALSE);
INSERT INTO Risposta (idrisposta, idDomanda, testorisposta, corretta) VALUES (55, 5, 'No', TRUE);

INSERT INTO IstanzaDiTest (idistanzaditest, idtest, studente, datasostenuto) VALUES (1, 1, 3, '2022-01-25 17:48:22');
INSERT INTO IstanzaDiTest (idistanzaditest, idtest, studente, datasostenuto) VALUES (2, 2, 3, '2022-01-22 15:18:29');
INSERT INTO IstanzaDiTest (idistanzaditest, idtest, studente, datasostenuto) VALUES (3, 3, 3, '2022-01-18 10:23:49');
INSERT INTO IstanzaDiTest (idistanzaditest, idtest, studente, datasostenuto) VALUES (4, 4, 3, '2022-01-11 11:43:21');
INSERT INTO IstanzaDiTest (idistanzaditest, idtest, studente, datasostenuto) VALUES (5, 6, 3, '2022-01-26 09:18:34');

INSERT INTO IstanzaDiTest (idistanzaditest, idtest, studente, datasostenuto) VALUES (6, 2, 8, '2022-01-28 12:00:37');
INSERT INTO IstanzaDiTest (idistanzaditest, idtest, studente, datasostenuto) VALUES (7, 3, 8, '2022-01-20 10:00:03');
INSERT INTO IstanzaDiTest (idistanzaditest, idtest, studente, datasostenuto) VALUES (8, 3, 7, '2022-01-20 10:00:05');

INSERT INTO IstanzaDiTest (idistanzaditest, idtest, studente, datasostenuto) VALUES (9, 4, 8, '2022-01-13 15:10:37');
INSERT INTO IstanzaDiTest (idistanzaditest, idtest, studente, datasostenuto) VALUES (10, 4, 7, '2022-01-26 18:32:27');

INSERT INTO IstanzaDiTest (idistanzaditest, idtest, studente, datasostenuto) VALUES (11, 6, 8, '2022-01-15 09:30:11');
INSERT INTO IstanzaDiTest (idistanzaditest, idtest, studente, datasostenuto) VALUES (12, 6, 7, '2022-01-27 11:01:17');

INSERT INTO RispostaUtente (idrispostautente, testorisposta, idistanzaditest, iddomanda) VALUES (1, 'ordinato rispetto ad x e completo rispetto a y', 5, 6);
INSERT INTO RispostaUtente (idrispostautente, testorisposta, idistanzaditest, iddomanda) VALUES (2, 'y^2/6 + ay -1', 5, 7);
INSERT INTO RispostaUtente (idrispostautente, testorisposta, idistanzaditest, iddomanda) VALUES (3, 'Falso', 5, 8);
INSERT INTO RispostaUtente (idrispostautente, testorisposta, idistanzaditest, iddomanda) VALUES (4, 'Falso', 5, 9);
INSERT INTO RispostaUtente (idrispostautente, testorisposta, idistanzaditest, iddomanda) VALUES (5, '4a + 3b', 5, 10);
INSERT INTO RispostaUtente (idrispostautente, testorisposta, idistanzaditest, iddomanda) VALUES (6, 'completo rispetto ad x e completo rispetto a y', 11, 6);
INSERT INTO RispostaUtente (idrispostautente, testorisposta, idistanzaditest, iddomanda) VALUES (7, '2x', 11, 7);
INSERT INTO RispostaUtente (idrispostautente, testorisposta, idistanzaditest, iddomanda) VALUES (8, 'Falso', 11, 8);
INSERT INTO RispostaUtente (idrispostautente, testorisposta, idistanzaditest, iddomanda) VALUES (9, 'Falso', 11, 9);
INSERT INTO RispostaUtente (idrispostautente, testorisposta, idistanzaditest, iddomanda) VALUES (10, 'a - 2', 11, 10);
INSERT INTO RispostaUtente (idrispostautente, testorisposta, idistanzaditest, iddomanda) VALUES (11, 'ordinato rispetto ad x e completo rispetto a y', 12, 6);
INSERT INTO RispostaUtente (idrispostautente, testorisposta, idistanzaditest, iddomanda) VALUES (12, 'y^2/6 + ay -1', 12, 7);
INSERT INTO RispostaUtente (idrispostautente, testorisposta, idistanzaditest, iddomanda) VALUES (13, 'Vero', 12, 8);
INSERT INTO RispostaUtente (idrispostautente, testorisposta, idistanzaditest, iddomanda) VALUES (14, 'Falso', 12, 9);
INSERT INTO RispostaUtente (idrispostautente, testorisposta, idistanzaditest, iddomanda) VALUES (15, '4a + 3b', 12, 10);

INSERT INTO RispostaUtente (idrispostautente, testorisposta, idistanzaditest, iddomanda) VALUES (16, 'Lorem ipsum dolor sit amet, consectetur adipiscing elit', 1, 11);
INSERT INTO RispostaUtente (idrispostautente, testorisposta, idistanzaditest, iddomanda) VALUES (17, 'Lorem ipsum dolor sit amet, consectetur adipiscing elit', 1, 12);
INSERT INTO RispostaUtente (idrispostautente, testorisposta, idistanzaditest, iddomanda) VALUES (18, 'Deve garantire la correttezza dei dati', 1, 13);
INSERT INTO RispostaUtente (idrispostautente, testorisposta, idistanzaditest, iddomanda) VALUES (19, 'SELECT DISTINCT cognome, nome FROM Colleghi WHERE città = ”Como”', 1, 14);

INSERT INTO RispostaUtente (idrispostautente, testorisposta, idistanzaditest, iddomanda) VALUES (20, 'Lorem ipsum dolor sit amet, consectetur adipiscing elit', 1, 15);
INSERT INTO RispostaUtente (idrispostautente, testorisposta, idistanzaditest, iddomanda) VALUES (21, 'PROIEZIONE, SELEZIONE, UNIONE, DIFFERENZA PRODOTTO CARTESIANO', 1, 16);


INSERT INTO RispostaUtente (idrispostautente, testorisposta, idistanzaditest, iddomanda) VALUES (22, 'Lorem ipsum dolor sit amet, consectetur adipiscing elit', 1, 17);
INSERT INTO RispostaUtente (idrispostautente, testorisposta, idistanzaditest, iddomanda) VALUES (23, 'Lorem ipsum dolor sit amet, consectetur adipiscing elit', 1, 18);
INSERT INTO RispostaUtente (idrispostautente, testorisposta, idistanzaditest, iddomanda) VALUES (24, 'Lorem ipsum dolor sit amet, consectetur adipiscing elit', 1, 19);
INSERT INTO RispostaUtente (idrispostautente, testorisposta, idistanzaditest, iddomanda) VALUES (25, 'Calcola la media aritmetica dei valori NOT NULL', 1, 20);
INSERT INTO RispostaUtente (idrispostautente, testorisposta, idistanzaditest, iddomanda) VALUES (26, 'Lorem ipsum dolor sit amet, consectetur adipiscing elit', 2, 21);
INSERT INTO RispostaUtente (idrispostautente, testorisposta, idistanzaditest, iddomanda) VALUES (27, 'Lorem ipsum dolor sit amet, consectetur adipiscing elit', 2, 22);


INSERT INTO RispostaUtente (idrispostautente, testorisposta, idistanzaditest, iddomanda) VALUES (28, 'Lorem ipsum dolor sit amet, consectetur adipiscing elit', 2, 23);
INSERT INTO RispostaUtente (idrispostautente, testorisposta, idistanzaditest, iddomanda) VALUES (29, 'Lorem ipsum dolor sit amet, consectetur adipiscing elit', 6, 21);
INSERT INTO RispostaUtente (idrispostautente, testorisposta, idistanzaditest, iddomanda) VALUES (30, 'Lorem ipsum dolor sit amet, consectetur adipiscing elit', 6, 22);
INSERT INTO RispostaUtente (idrispostautente, testorisposta, idistanzaditest, iddomanda) VALUES (31, 'Lorem ipsum dolor sit amet, consectetur adipiscing elit', 6, 23);

INSERT INTO RispostaUtente (idrispostautente, testorisposta, idistanzaditest, iddomanda) VALUES (32, 'Vero', 3, 24);
INSERT INTO RispostaUtente (idrispostautente, testorisposta, idistanzaditest, iddomanda) VALUES (33, 'Falso', 3, 25);
INSERT INTO RispostaUtente (idrispostautente, testorisposta, idistanzaditest, iddomanda) VALUES (34, 'Falso', 3, 26);
INSERT INTO RispostaUtente (idrispostautente, testorisposta, idistanzaditest, iddomanda) VALUES (35, 'Vero', 3, 27);
INSERT INTO RispostaUtente (idrispostautente, testorisposta, idistanzaditest, iddomanda) VALUES (36, 'Vero', 3, 28);

INSERT INTO RispostaUtente (idrispostautente, testorisposta, idistanzaditest, iddomanda) VALUES (37, 'Falso', 7, 24);
INSERT INTO RispostaUtente (idrispostautente, testorisposta, idistanzaditest, iddomanda) VALUES (38, 'Vero', 7, 25);
INSERT INTO RispostaUtente (idrispostautente, testorisposta, idistanzaditest, iddomanda) VALUES (39, 'Falso', 7, 26);
INSERT INTO RispostaUtente (idrispostautente, testorisposta, idistanzaditest, iddomanda) VALUES (40, 'Falso', 7, 27);
INSERT INTO RispostaUtente (idrispostautente, testorisposta, idistanzaditest, iddomanda) VALUES (41, 'Vero', 7, 28);

INSERT INTO RispostaUtente (idrispostautente, testorisposta, idistanzaditest, iddomanda) VALUES (42, 'Vero', 8, 24);
INSERT INTO RispostaUtente (idrispostautente, testorisposta, idistanzaditest, iddomanda) VALUES (43, 'Vero', 8, 25);
INSERT INTO RispostaUtente (idrispostautente, testorisposta, idistanzaditest, iddomanda) VALUES (44, 'Falso', 8, 26);
INSERT INTO RispostaUtente (idrispostautente, testorisposta, idistanzaditest, iddomanda) VALUES (45, 'Falso', 8, 27);
INSERT INTO RispostaUtente (idrispostautente, testorisposta, idistanzaditest, iddomanda) VALUES (46, 'Falso', 8, 28);



INSERT INTO RispostaUtente (idrispostautente, testorisposta, idistanzaditest, iddomanda) VALUES (47, 'No', 4, 1);
INSERT INTO RispostaUtente (idrispostautente, testorisposta, idistanzaditest, iddomanda) VALUES (48, 'abstruct, synchronized, static', 4, 2);
INSERT INTO RispostaUtente (idrispostautente, testorisposta, idistanzaditest, iddomanda) VALUES (49, 'No', 4, 3);
INSERT INTO RispostaUtente (idrispostautente, testorisposta, idistanzaditest, iddomanda) VALUES (50, 'No', 4, 4);
INSERT INTO RispostaUtente (idrispostautente, testorisposta, idistanzaditest, iddomanda) VALUES (51, 'Si', 4, 5);

INSERT INTO RispostaUtente (idrispostautente, testorisposta, idistanzaditest, iddomanda) VALUES (52, 'Si', 9, 1);
INSERT INTO RispostaUtente (idrispostautente, testorisposta, idistanzaditest, iddomanda) VALUES (53, 'final, classe, int', 9, 2);
INSERT INTO RispostaUtente (idrispostautente, testorisposta, idistanzaditest, iddomanda) VALUES (54, 'Si', 9, 3);
INSERT INTO RispostaUtente (idrispostautente, testorisposta, idistanzaditest, iddomanda) VALUES (55, 'No', 9, 4);
INSERT INTO RispostaUtente (idrispostautente, testorisposta, idistanzaditest, iddomanda) VALUES (56, 'No', 9, 5);

INSERT INTO RispostaUtente (idrispostautente, testorisposta, idistanzaditest, iddomanda) VALUES (57, 'Si', 10, 1);
INSERT INTO RispostaUtente (idrispostautente, testorisposta, idistanzaditest, iddomanda) VALUES (58, 'method, void, boolean', 10, 2);
INSERT INTO RispostaUtente (idrispostautente, testorisposta, idistanzaditest, iddomanda) VALUES (59, 'No', 10, 3);
INSERT INTO RispostaUtente (idrispostautente, testorisposta, idistanzaditest, iddomanda) VALUES (60, 'No', 10, 4);
INSERT INTO RispostaUtente (idrispostautente, testorisposta, idistanzaditest, iddomanda) VALUES (61, 'No', 10, 5);