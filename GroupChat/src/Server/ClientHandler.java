package Server;

import java.io.*;
import java.net.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ClientHandler implements Runnable{
    Socket clientSocket=null;
    BufferedReader fr=null;
    DataOutputStream dataOutputStream=null;
    String name = null;

   

    ClientHandler(String name, Socket clientSocket, BufferedReader fr, DataOutputStream dataOutputStream) {
        this.name = name;
        this.clientSocket=clientSocket;
        this.fr=fr;
        this.dataOutputStream=dataOutputStream;
        
    }
    
  
    @Override
    public void run() {
        String msg = null;
        while(true){
            try {
                //System.out.println(name);
                msg = fr.readLine();
                //System.out.println(msg);
            } catch (IOException ex) {
                Logger.getLogger(ClientHandler.class.getName()).log(Level.SEVERE, null, ex);
            }
            
           for (int i = 0; i < Server.clientList.size(); i++) {
                    
                try {
                    Server.clientList.get(i).dataOutputStream.writeBytes(name+": "+msg+'\n');
                } catch (IOException ex) {
                    Logger.getLogger(ClientHandler.class.getName()).log(Level.SEVERE, null, ex);
                }
                    
                } 
        } 
        }
        
    
}
