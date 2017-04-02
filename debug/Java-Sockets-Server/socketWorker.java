package server;


import java.net.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


public class SocketWorker extends Thread {
  private Socket client;
  
  String clientPort;
    //Constructor: inizializza le variabili
    SocketWorker(Socket client) {
        this.client = client;
        System.out.println("Connesso con: " + client);
    }
    
    BufferedReader in = null;
    PrintWriter out = null;
    String username;
    private String nomeGruppo;
    private boolean Exited = false;
    // Questa e' la funzione che viene lanciata quando il nuovo "Thread" viene generato
    public void run(){
    	
        try{
          // connessione con il socket per ricevere (in) e mandare(out) il testo
          in = new BufferedReader(new InputStreamReader(client.getInputStream()));
          out = new PrintWriter(client.getOutputStream(), true);
        } catch (IOException e) {
          System.out.println("Errore: in|out fallito");
          System.exit(-1);
        }
        
    	String line = "";
        //SETTAGGIO USERNAME
        try{
            do
            {
                //richiede username al client
                out.println("Scegli NickName: ");
                //riceve username dal client
                line = in.readLine();
            }while(ServerTestoMultiThreaded.ControllaNickname(line));
            
            username = line;
            ServerTestoMultiThreaded.utenti.add(line);
            clientPort = line; //il "nome" del mittente (client)
            
           
            System.out.println("NickName host: " + line);
        }catch(IOException e){
        	System.out.println("Ricezione username fallita");
        	System.exit(-1);
        }
        
       Seleziona(line, in, out);
    }

    public String getUsername() {
        return username;
    }
    
    private void Seleziona(String line, BufferedReader in, PrintWriter out)
    {
         String risp = null;
        try
        {
            out.println("Vuoi far parte di un gruppo? (Y/N)");
            risp = in.readLine();
            
        }catch(Exception e){
            
        }
        
         switch(risp)
        {
            case "N":
                No(line);
                break;
            case "Y":
                Si(line);
                break;
            default:
                out.println("Risposta non valida: Digitare Y per si o N per no");
                Seleziona(line, in, out);
        }
        
    }
    
    
    private void No(String line)
    {
                while(line != null){
                  try{
                    line = in.readLine();
                    //Manda lo stesso messaggio appena ricevuto con in aggiunta il "nome" del client
                    out.println("Server-->" + clientPort + ">> " + line);
                    //controlla se la lista utenti è richiesta
                    if(line.equals("listaUtenti"))
                    {
                        out.println(ServerTestoMultiThreaded.getLista());
                    }

                    //scrivi messaggio ricevuto su terminale
                    System.out.println(clientPort + ">> " + line);
                   } catch (IOException e) {
                    System.out.println("lettura da socket fallito");
                    try {
                    client.close();
                    System.out.println("connessione con client: " + client + " terminata!");
                    line = null;
                    } catch (IOException e1) {
                        System.out.println("Errore connessione con client: " + client);
                    }
                   }
                }
    }
    
    private void Si(String line)
    {
    	String risp = null;
        out.println("Numero gruppi esistenti: " + ServerTestoMultiThreaded.getGroupSize());
        out.println(ServerTestoMultiThreaded.printNames());
        out.println("Inserire nome del gruppo in cui entrare oppure digitare \"new\" per creare un  nuovo gruppo");
        
        try{
            risp = in.readLine();
        }catch(IOException e)
        {
            System.out.println("Impossibile ricevere dati dall'host " + clientPort);
            System.exit(-1);
        }
        
        switch(risp)
        {
            case "new":
            	String arg = null;
                try
                {  
                    out.println("Scegli il nome del nuovo gruppo: ");
                    risp = in.readLine();
                    out.println("Scrivi una breve descrizione sul gruppo: ");
                    arg = in.readLine();
                }catch(IOException e)
                {
                    System.out.println("Ricevimento dati fallito ");
                }
                
                GroupClass nuovoGruppo = new GroupClass(risp, arg, this);
                ServerTestoMultiThreaded.gruppi.add(nuovoGruppo);
                nomeGruppo = risp;
                
               
                out.println("Gruppo creato con successo!");
                
                break;
            
            default:
                if(ServerTestoMultiThreaded.checkGroup(risp))
                {
                    for(int i=0; i<ServerTestoMultiThreaded.gruppi.size(); i++)
                    {
                        if(ServerTestoMultiThreaded.gruppi.get(i).getNomeGruppo().equals(risp))
                        {
                            ServerTestoMultiThreaded.gruppi.get(i).aggiungiUtente(this);
                            nomeGruppo = ServerTestoMultiThreaded.gruppi.get(i).getNomeGruppo();
                            out.println("Argomento del gruppo: " + ServerTestoMultiThreaded.gruppi.get(i).getArgomento());
                        }
                    }
                }
                else
                {
                    out.println("Non esistono gruppi con il nome inserito");
                    Si(line);  //funzione ricorsiva! Ricomincia dall'inizio la stessa funzione
                }
            
        }
            GroupCommunications();
            Exited = false;
            Seleziona(username, in, out);
    }
    
    private void GroupCommunications()
    {
        String line;
        
         while(Exited == false){
                  try{
                    line = in.readLine();
                    
                 
                    
                    if(line.equals("listaUtenti"))
                    {
                            ServerTestoMultiThreaded.printUtentiFromNomeGruppo(nomeGruppo, out);
                            GroupCommunications();
                    }
                    //controlla se l'argomento del gruppo � richiesto
                    if(line.equals("argomentoGruppo"))
                    {
                    
                        out.println(ServerTestoMultiThreaded.getGroupArgument(nomeGruppo));
                         GroupCommunications();
                                        
                    }           
                    
                    if(line.equals("esciGruppo"))
                    {
                        ServerTestoMultiThreaded.LeaveGroup(nomeGruppo, this);
                        Exited = true;
                        GroupCommunications();
                        
                        
                    }
                    
                    if(Exited == false)
                    {
                    for(int i=0; i<ServerTestoMultiThreaded.gruppi.size(); i++)
                    {
                        if(ServerTestoMultiThreaded.gruppi.get(i).getNomeGruppo().equals(nomeGruppo))
                        {
                                    
                            ServerTestoMultiThreaded.gruppi.get(i).sendMessageToGroup(line, this);
                              
                      
                        }
                    }
                    
                    }
                   } catch (IOException e) {
                    System.out.println("lettura da socket fallito");
                    try {
                    client.close();
                    System.out.println("connessione con client: " + client + " terminata!");
                    line = null;
                    } catch (IOException e1) {
                        System.out.println("Errore connessione con client: " + client);
                    }
                   }
                }
        
        
    }
    
    public void sendMessage(String msg)
    {
        out.println(msg);
    }

    public String getNomeGruppo() {
        return nomeGruppo;
    }

    public void setNomeGruppo(String nomeGruppo) {
        this.nomeGruppo = nomeGruppo;
    }
    
}
