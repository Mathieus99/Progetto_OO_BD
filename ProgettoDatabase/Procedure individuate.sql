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




