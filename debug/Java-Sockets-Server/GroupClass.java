package server;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author alessandro.ampala/gianmarco.rampulla/gerardo.scaricaciottoli
 */
public class GroupClass {
    
    
    private String NomeGruppo;
    private List<SocketWorker> listaSocket = new ArrayList<>();
    private String argomento;
    
    public GroupClass(String name, String argomento, SocketWorker socketDaAggiungere)
    {
        setNomeGruppo(name);
        setArgomento(argomento);
        listaSocket.add(socketDaAggiungere);
    }
    
    public String getNomeGruppo() {
        return NomeGruppo;
    }

    public List<SocketWorker> getListaSocket() {
        return listaSocket;
    }
    
    private void setNomeGruppo(String NomeGruppo) {
        this.NomeGruppo = NomeGruppo;
    }
    
    public String getArgomento() {
		return argomento;
	}

	private void setArgomento(String argomento) {
		this.argomento = argomento;
	}

	public void aggiungiUtente(SocketWorker socketDaAggiungere)
    {
        listaSocket.add(socketDaAggiungere);
    }
    
    public void rimuoviUtente(SocketWorker DaRimuovere)
    {
        int index = listaSocket.indexOf(DaRimuovere);
        
    	listaSocket.remove(index);
    }
    
    public void sendMessageToGroup(String msg, SocketWorker sender)
    {
        for(SocketWorker c : listaSocket)
        {
            if(c != sender)
            {
                c.sendMessage(sender.getUsername() + "@" + sender.getNomeGruppo() + " dice: " + msg);
            }
        }
    }
    
    public void sendMessageOfAbandoning( SocketWorker sender)
    {
    	for(SocketWorker c : listaSocket)
        {
            if(c != sender)
            {
                c.sendMessage(sender.getUsername() + " ha abbandonato il gruppo " + sender.getNomeGruppo());
            }
        }
    }
    
    
    
}
