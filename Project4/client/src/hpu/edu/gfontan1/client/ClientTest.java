/**
* This program will calculate some things using either the Quadratic Equation or
* the Pythagorean Theorem.
* @author	Gabriel Fontanilla
* @version 	1.0
* @since 	2017-09-22
*/
package hpu.edu.gfontan1.client;
import java.util.*;
import java.io.*;
import java.net.Socket;
import java.net.*;
import java.text.DecimalFormat;
public class ClientTest{
  static DecimalFormat df = new DecimalFormat(".##");
    public static void main(String args[]) throws UnknownHostException,IOException{
    Scanner user = new Scanner(System.in); //for user input'
    Socket clientSocket = new Socket("localhost",60000);////sets up connection from this computer through port 5678
    System.out.println("Client succesfully connected to "+clientSocket.getRemoteSocketAddress());
    //data input and output stream is going to pass in and recieve data from my server
    DataInputStream client_in = new DataInputStream(clientSocket.getInputStream());
    DataOutputStream client_out = new DataOutputStream(clientSocket.getOutputStream());
    while(true){
      int num,answer,a,b,c;
      double x,y,answer1;
      System.out.println("Type in 1 for Pythagoras Theorem or 2 for Quadratic Equation \nOr type in '0' to quit");//gets number from user and stores it
      num = user.nextInt();
      if(num == 0) break;//will break if user enteres 0
      /*
      The code below will send numbers to the server to calculate for the Pythagorean Theorem
      */
      switch(num){
      case 1:
        client_out.writeInt(num);
        System.out.println("Enter the 2 numbers for your Pythagorean Theorem: ");
        x = user.nextDouble();
        client_out.writeDouble(x);

        client_out.flush();

        y = user.nextDouble();
        client_out.writeDouble(y);

        answer1 = client_in.readDouble();

        System.out.println("For the Pythagoras Theorem your answer is: "+df.format(answer1));
        System.out.println(" ");
      break;

      case 2:
        client_out.writeInt(num);//sends current selection to the server
      System.out.println("Enter the 3 numbers for you Quadratic Equation. ");

      a = user.nextInt();
      client_out.writeInt(a);

      client_out.flush();

      b = user.nextInt();
      client_out.writeInt(b);

      client_out.flush();

      c = user.nextInt();
      client_out.writeInt(c);

      client_out.flush();

      System.out.println("This is your current Quadratic Equation: "+a+"x^2 + "+b+"x +"+c);
      System.out.println("Now solving for the discriminant..");
      double discriminant = b * b - 4 * a * c;
      System.out.println(discriminant);
      client_out.writeDouble(discriminant);
      Double root1, root2,d;
      String roots = "";
      d = client_in.readDouble();//checks if discriminant is either greater than 0, less than 0, or neither
      if(d<0){
        roots = client_in.readUTF();
        root1 = client_in.readDouble();
        root2 = client_in.readDouble();
        System.out.println(roots+root1+root2);
      }
      else if(d==0){
        roots = client_in.readUTF();
        root1 = client_in.readDouble();
        System.out.println(roots+root1);
      }
      else{
        roots = client_in.readUTF();
        System.out.println(roots);
      }
      }
      }
      clientSocket.close();
    }

}
//E world
//DRC Decision Research coporation jr.dev, entry level
