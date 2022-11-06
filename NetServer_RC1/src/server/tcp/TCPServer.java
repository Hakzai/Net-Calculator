// DEVELOPED BY ISAAC PELLEGRINI ALENCAR 

package server.tcp;

import java.io.*;

import java.net.*;



class TCPServer {



    public static void main(String argv[]) throws Exception {

        /* Register service at port 5050 and wait for connection */

        ServerSocket server = new ServerSocket(5050);

        System.out.println("Waiting for connections...");

        Socket connection = server.accept();

        System.out.println("Client connected " + connection);

        /**

         * ******************************************************

         */

        /* Creating input and output streams */

        DataInputStream inputStream = new DataInputStream(new BufferedInputStream(connection.getInputStream()));

        DataOutputStream outputStream = new DataOutputStream(connection.getOutputStream());

        /**

         * ******************************************************

         */

        /* Start Communication */
        String mathOperation = "";
        
        while(true){
        	
        	mathOperation = inputStream.readUTF();
        	if(mathOperation.equals("EXIT"))
        		break;
        	
        	String getNumber_1 = inputStream.readUTF();
        	String getNumber_2 = inputStream.readUTF();
        	
        	Double number_1, number_2;
        	
        	try {
        		number_1 = Double.parseDouble(getNumber_1);
        		number_2 = Double.parseDouble(getNumber_2);
        	}catch(NumberFormatException e) {
        		System.out.println("\nResult: CLIENT SENT INVALID NUMBER");
        		outputStream.writeUTF("INVALID NUMBER(S)!");
        		continue;
        	}
        	
        	if(mathOperation.equals("ADDITION")) {        		
	        	double addition = number_1 + number_2;
	        
	        	System.out.println("\nCLIENT> Addition: " + number_1 + " + " + number_2);
	        	System.out.println("Result: " + addition);
	
	        	outputStream.writeUTF(String.valueOf(addition).toUpperCase());
        	}
        	
        	else if(mathOperation.equals("SUBTRACTION")) {
	        	double subtraction = number_1 - number_2;
	        
	        	System.out.println("\nCLIENT> Subtraction: " + number_1 + " - " + number_2);
	        	System.out.println("Result: " + subtraction);
	
	        	outputStream.writeUTF(String.valueOf(subtraction).toUpperCase());
        	}
        	
        	else if(mathOperation.equals("MULTIPLY")) {
	        	double multiply = number_1 * number_2;
	        
	        	System.out.println("\nCLIENT> Multiply: " + number_1 + " x " + number_2);
	        	System.out.println("Result: " + multiply);
	
	        	outputStream.writeUTF(String.valueOf(multiply).toUpperCase());
        	}
        	
        	else if(mathOperation.equals("DIVIDE")) {
        		if(number_2 == 0) {
        			System.out.println("\nCLIENT> Divide: " + number_1 + " / " + number_2);
        			System.out.println("Result: IT IS NOT POSSIBLE DIVIDE BY ZERO");
        			outputStream.writeUTF("IT IS NOT POSSIBLE DIVIDE BY ZERO");
        			continue;
        		}
        		
	        	double divide = number_1 / number_2;
	        
	        	System.out.println("\nCLIENT> Divide: " + number_1 + " / " + number_2);
	        	System.out.println("Result: " + divide);
	
	        	outputStream.writeUTF(String.valueOf(divide).toUpperCase());
        	}
        	
        }
        
        if(mathOperation.equals("EXIT"))
        	System.out.println("\nFinishing server and app...");
        
        /**

         * ******************************************************

         */



        /* Close streams and socket */

        inputStream.close();

        outputStream.close();

        connection.close();

        server.close();

    }



}

