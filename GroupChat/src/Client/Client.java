package Client;

import java.io.*;
import java.net.*;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Client {
    public static void main(String [] args)throws Exception
    {
      Socket clientSocket=new Socket("localhost",7000);  
      
      
       
        {
            InputStream is = clientSocket.getInputStream();
            OutputStream os = clientSocket.getOutputStream();

             final DataOutputStream outToServer = new DataOutputStream(os);
            
             final BufferedReader inFromServer = new BufferedReader(new InputStreamReader(is));
            
           Thread send = new Thread ( new Runnable() {
           @Override
           public void run() {
               try {
                   while(true){
                       Scanner sc=new Scanner(System.in);
                   String msg = sc.nextLine();
                   outToServer.writeBytes(msg+"\n");
                   }
                   
               } catch (IOException ex) {
                   Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
               }
              
          }
       });
           
           Thread receive = new Thread ( new Runnable() {
           @Override
           public void run() {
               try {
                   while(true){
                       String clientSentense = inFromServer.readLine();
                   System.out.println(clientSentense);
                   }
                   
               } catch (IOException ex) {
                   Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
               }
          }
       });
      
       send.start();
       receive.start();
    }
    }
}