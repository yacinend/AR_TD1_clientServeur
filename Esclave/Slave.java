package jus.aor.printing.Esclave;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;

public class Slave implements Runnable{
	Socket socket;
	public Slave(Socket s){
		socket =s;
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		InputStream inFromServer;
		try {
			inFromServer = socket.getInputStream();
			DataInputStream in =new DataInputStream(inFromServer);
			while(in.readLine()!=null){
			System.out.println("requete");	
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
		
	}

}
