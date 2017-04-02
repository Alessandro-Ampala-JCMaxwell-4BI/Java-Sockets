# Java-Sockets
Sviluppo di due software in grado di comunicare tra di loro attraverso i **Sockets**
```
Obiettivo del progetto: Realizzare due software in grado di comunicare 
```
**Per saperne di più sui Sockets** --> https://docs.google.com/presentation/d/19vhath-GbtUO7ofpukJE4Opi16aTfrnurJ2lUizB0iE/edit?usp=sharing

### Prerequisiti
E' richiesto NetBeans IDE (Integrated Development Environment) per poter avviare le due classi Server e Client e ovviamente Java SDK (necessario per l'installazione di NetBeans).

### Installazione
- Scaricare i files del progetto cliccando "Clone/Download"
- in NetBeans creare due nuovi progetti "Client" e "Server"

## Uso
Da NetBeans dopo aver creato il progetto:

### Client 
- Cliccare sul pulsante verde (Esegui),
- Quando viene richiesto il nome dell'host (indirizzo ip/nome alfanumerico), inserire il nome dell'host e premere invio,
- Quando viene richiesta la porta, inserire la porta e premere invio,
- Se non stampa nessun errore la connessione è effettuata

### Server 
- Cliccare sul pulsante verde (Esegui),
- Quando viene richiesto la porta del server, inserire il nome dell'host e premere invio,
- Se non stampa nessun errore la connessione è effettuata

NOTA: è possibile collegarsi al server con quanti Clients desidero, sia sullo stesso computer che da terminali su computer diversi.

### Esempio usando stesso computer sia per eseguire Server che multipli Clients

Da Netbeans creare un progetto java e denominarlo "Server", nel progetto appena creato inserire le classi Server scaricate e eseguire il file "ServerTestoMultiThread" (Cliccando sul pulsante verde, ed eventualmente in caso di richiesta, selezionando il file appena citato):
```
- Quando viene richiesto la porta del server, inserire il numero della porta sulla quale si vogliono effettuare le connessioni e premere invio,
- Se non stampa nessun errore la connessione è effettuata
```
Da Netbeans creare un altro progetto java e denominarlo "Client", nel progetto appena creato inserire le classi Client scaricate e eseguire il file "ClientTesto" (Cliccando sul pulsante verde, ed eventualmente in caso di richiesta, selezionando il file appena citato):
```
- Quando viene richiesto il nome dell'host (indirizzo ip/nome alfanumerico), inserire il nome dell'host e premere invio,
- Quando viene richiesta la porta, inserire la porta e premere invio,
- Se non stampa nessun errore la connessione è effettuata
- Quando viene richiesto, scegliere il proprio NickName
```
Da Netbeans, selezionato il progetto Client creato e eseguire di nuovo il file "ClientTesto" (Cliccando nuovamente sul pulsante verde)
in modo da creare un altra istanza della classe Client.
```
NOTA:se già un istanza della classe è in esecuzione, alla nuova richiesta di esecuzione Netbeans non fermerà il processo già in esecuzione ma ne creerà un altro. 

- Quando viene richiesto il nome dell'host inserire il nome dello stesso host inserito con il primo client (solo se si vuole effettuare una connessione allo stesso Server) e premere invio, 
- Quando viene richiesta la porta, inserire la porta dello stesso host inserita con il primo client (solo se si vuole effettuare una connessione allo stesso Server) e premere invio,
- Se non stampa nessun errore la connessione è effettuata
- Quando viene richiesto, scegliere il proprio NickName
```
### Chat Diretta con il Server
Per far si che i messaggi vengano inviati direttamente al server e non ad un gruppo, subito dopo la scelta del NickName, alla richiesta "Vuoi far parte di un gruppo? (Y/N)" digitare N e premere invio. In questo modo il server riceverà direttamente i messaggi che verranno inviati. Per inviare un messaggio scriverne il testo e premere invio.
### Chat Di Gruppo

NOTA: Per poter creare una chat di gruppo funzionante, un Server deve avere più di 1 Client collegato



 - 

## Comandi Client
- listaUtenti = Ottiene una lista dei client collegati al server
- argomentoGruppo = Ottiene l'argomento della discussione del gruppo di cui si fa parte
- esciGruppo = Uscita dal gruppo di cui si fa parte
- Bye. = Disconnette il client dal server

## Licenza
Completamente Open Source.

## Autori
-Alessandro Ampala

-Gianmarco Rampulla

-Gerardo Scaricaciottoli

## Ringraziamenti
Al Prof per la proposta del progetto e per il codice iniziale
