/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Stefano
 */

package Connection;

import java.io.*;
import static java.lang.System.in;
import java.net.*;

public class ClientConnessioneTCP {
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException{
        //oggetto da usare per realizzare la connessione TCP
        Socket connection = null;
        //nome o IP del server
        String serverAddress = "localhost";
        //porta del server in ascolto
        int port = 2000;

        //apertura della connessione al server sulla porta specificata
        try{
            connection = new Socket(serverAddress, port);
            System.out.println("Connessione aperta");
            
            //leggo da tastiera il messaggio che voglio mandare
            System.out.println("Inserire il messaggio");
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            String input = reader.readLine();
            reader.close();
            
            //scrivo sul buffer di output e quindi sul socket la stringa inserita da tastiera
            BufferedWriter out = new BufferedWriter(new OutputStreamWriter(connection.getOutputStream()));
            out.write(input+ "\n"); //se non viene inserito non legge il buffer e quindi non comunicano correttamente
            out.flush();//svuolta l'area di memoria e la spedisce a destinazione
            
            //leggo il messaggio di conferma che mi è arrivato dal server e lo stampo
            BufferedReader reade = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String b = reade.readLine();
            System.out.println("Il messaggio di conferma e': "+b);
        }
        catch(ConnectException e){
            System.err.println("Server non disponibile!");
        }
        catch(UnknownHostException e1){
            System.err.println("Errore DNS!");
        }

        catch(IOException e2){//
            System.err.println(e2);
            e2.printStackTrace();
        }
        
        //chiusura della connnessione
        finally{
                try {
            if (connection!=null)
                {
                    connection.close();
                    System.out.println("Connessione chiusa!");
                }
            }
            catch(IOException e){
                System.err.println("Errore nella chiusura della connessione!");
            }
        }
        
   }
}