// FEITO POR ISAAC PELLEGRINI ALENCAR - 3000141 - ADS

package client.tcp;

import java.io.*;

import java.net.*;



class TCPClient {

    public static void main(String argv[]) throws Exception {

        int port = 5050;

        String ip = "127.0.0.1";

        BufferedReader scanner = new BufferedReader(new InputStreamReader(System.in));


        /* Create connection with server */

        Socket connection = new Socket(ip, port);

        System.out.println("Connected! " + connection);

        /**

         * ******************************************************

         */

        /* Creating input and output streams */

        DataOutputStream outputStream = new DataOutputStream(connection.getOutputStream());

        DataInputStream inputStream = new DataInputStream(new BufferedInputStream(connection.getInputStream()));

        /**

         * ******************************************************

         */

        /* Start communication */
        String mathOperation = "";
        
        while(!mathOperation.equals("EXIT")) {
	        
        	System.out.println("\n*** NET CALCULATOR ***\n\n"
	        		+ "ADDITION \nSUBTRACTION \nMULTIPLY \nDIVIDE \n\n"
	        		+ "EXIT to quit\n"
	        		+ "Insert the option: \n");
	
        	mathOperation = scanner.readLine().toUpperCase();
	        
        	if(mathOperation.equals("EXIT")) {
        		outputStream.writeUTF(mathOperation);
        		System.out.println("\nEND OF APPLICATION!");
        		break;
        	}
        	else if(mathOperation.equals("ADDITION") || mathOperation.equals("SUBTRACTION") ||
        			mathOperation.equals("MULTIPLY") || mathOperation.equals("DIVIDE")) {
	        
    	        outputStream.writeUTF(mathOperation);
        		
		        String number_1 = scanner.readLine();
		        String number_2 = scanner.readLine();	        
		
		        outputStream.writeUTF(number_1);
		        outputStream.flush();
		        outputStream.writeUTF(number_2);
		        outputStream.flush();
		
		        String result = inputStream.readUTF();
		
		        System.out.println("\nSERVER> Result: " + result);
        	}
        	else
        		System.out.println("\n!! WRITE A VALID OPTION !!");
        }
        /**

         * ******************************************************

         */

        /* Close streams and socket */

        outputStream.close();

        inputStream.close();

        connection.close();

        
        }

}