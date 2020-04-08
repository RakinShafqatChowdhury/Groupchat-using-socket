package Server;

import java.io.*;
import java.net.*;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Server {
     static Vector<ClientHandler> clientList = new Vector<>(); 
      
    static int clientCounter = 0;
    
    public static void main(String[] args) throws IOException {
        
        ServerSocket serverSocket = new ServerSocket(7000);
        
        
        while (true) {
            Socket clientSocket = serverSocket.accept();
            System.out.println(clientCounter+1+" Client Connected..."+clientSocket);
            
            DataOutputStream dataOutputStream = new DataOutputStream(clientSocket.getOutputStream());
            InputStream inputStream = clientSocket.getInputStream();
            BufferedReader fr=new BufferedReader(new InputStreamReader(inputStream));
            
            dataOutputStream.writeBytes("Username\n");
            String Name = fr.readLine();

            ClientHandler handler = new ClientHandler(Name, clientSocket,fr,dataOutputStream);
            
            Thread thread = new Thread(handler);
            
            clientList.add(handler);
            for (int i = 0; i < clientList.size(); i++) {
                  
                try {
                    clientList.get(i).dataOutputStream.writeBytes(Name+" has joined "+'\n');
                } catch (IOException ex) {
                    Logger.getLogger(ClientHandler.class.getName()).log(Level.SEVERE, null, ex);
                }
                    
                } 
            
            
            thread.start();
            clientCounter++;
        }    
    }
}
