/**
 * J<i>ava</i> U<i>tilities</i> for S<i>tudents</i>
 */
package jus.aor.printing;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;

/**
 * Classe de service fournissant toutes les interactions (read, write) en mode TCP.
 * @author Morat 
 */
class TCP{
	private static final int MAX_LEN_BUFFER = 1024;
	/**
	 * 
	 * @param soc the socket
	 * @param not the notification
	 * @throws IOException
	 */
	static void writeProtocole(Socket soc,  Notification not) throws IOException {
		OutputStream outToServer = soc.getOutputStream();
		DataOutputStream out = new DataOutputStream(outToServer);
        System.out.println("Envoie en cours");
        switch(not){
        case QUERY_PRINT:
        	out.writeInt(1);
        	System.out.println("envoyer");
        	break;
        case REPLY_PRINT_OK:
        	out.writeInt(2);
     	   break;
        }
		
		
	}
	/**
	 * 
	 * @param soc the socket 
	 * @return the notification
	 * @throws IOException
	 */
	static Notification readProtocole(Socket soc) throws IOException {
		InputStream inFromServer = soc.getInputStream();
        DataInputStream in =new DataInputStream(inFromServer);
       Notification not = null;
      
       int temp=in.readInt();
       switch(temp){
       case 1:
    	   not=not.QUERY_PRINT;
    	   break;
       case 2:
    	   not=not.REPLY_PRINT_OK;
    	   break;
       }
        return not;
	}
	/**
	 * 
	 * @param soc the socket
	 * @param key the JobKey to write
	 * @throws IOException
	 */
	static void writeJobKey(Socket soc, JobKey key) throws IOException {
		OutputStream outToServer = soc.getOutputStream();
		DataOutputStream out = new DataOutputStream(outToServer);
		
		byte[] tobyte=key.toString().getBytes();
		int length=tobyte.length;
		out.writeInt(length);
		out.write(tobyte);
	}
	/**
	 * 
	 * @param soc the socket
	 * @return the JobKey
	 * @throws IOException
	 */
	static JobKey readJobKey(Socket soc) throws IOException {
		InputStream inFromServer = soc.getInputStream();
        DataInputStream in =new DataInputStream(inFromServer);
        int length=in.readInt();
        byte[] temp = new byte[length];
        in.read(temp,0,length);
        return new JobKey(temp);
	}
	/**
	 * 
	 * @param soc the socket
	 * @param fis the input stream ti transfert
	 * @param len th len of the input stream
	 * @throws IOException
	 */
/*	static void writeData(Socket soc, InputStream fis, int len) throws IOException {
	//----------------------------------------------------------------------------- A COMPLETER
	}
	/**
	 * 
	 * @param soc th socket
	 * @return string data 
	 * @throws IOException
	 */
/*	static String readData(Socket soc) throws IOException {
	//----------------------------------------------------------------------------- A COMPLETER
	}
	/**
	 * 
	 * @param soc the socket
	 * @param jobs the JobState
	 * @throws IOException
	 */
/*	static void writeJobState(Socket soc,  JobState jobs) throws IOException {
	//----------------------------------------------------------------------------- A COMPLETER
	}
	/**
	 * 
	 * @param soc the socket 
	 * @return the JobState
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
//	static JobState readJobState(Socket soc) throws IOException, ClassNotFoundException {
	//----------------------------------------------------------------------------- A COMPLETER
//	}
}
