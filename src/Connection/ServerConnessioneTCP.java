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

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ServerConnessioneTCP {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // porta del server maggiore di 1024 
        int port=2000;
        //oggetto ServerSocket necessario per accettare richieste dal client
        ServerSocket sSocket = null;
        //oggetto da usare per realizzare la connessione TCP
        Socket connection;

        while(true){
            try{
                // il server si mette in ascolto sulla porta voluta
                sSocket = new ServerSocket(port);
                System.out.println("In attesa di connessioni!");
                //si è stabilita la connessione
                connection = sSocket.accept();
                System.out.println("Connessione stabilita!");
                System.out.println("Socket server: " + connection.getLocalSocketAddress());
                System.out.println("Socket client: " + connection.getRemoteSocketAddress());
                //leggo il messaggio inviato dal client attraverso il socket e stampo il messaggio arrivato in output
                BufferedReader read = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String a = read.readLine();
                System.out.println("Il messaggio e': "+a);
                
                //scrivo il messaggio di conferma che devo inviare dal server al client
                System.out.println("Inserire il messaggio di conferma");
                BufferedReader rea = new BufferedReader(new InputStreamReader(System.in));
                String input = rea.readLine();
                rea.close();
                
                BufferedWriter outp = new BufferedWriter(new OutputStreamWriter(connection.getOutputStream()));
                outp.write(input+ "\n");//se non metto lo \n non va perchè il buffered utilizza il new line e non lo mette in modo automatico a capo
                outp.flush();
            }
               catch(IOException e){
                   System.err.println("Errore di I/O!");
            }
            
            //chiusura della connessione con il client
            try {
                if (sSocket!=null) sSocket.close();
            } catch (IOException ex) {
                System.err.println("Errore nella chiusura della connessione!");
            }
            System.out.println("Connessione chiusa!");
        }
      }
}