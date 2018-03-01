package hpu.edu.gfontan1.server;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.*;
public class ServerTest extends Thread {

  public static void main(String args[])throws IOException{
    int clientData;
    ServerSocket getServed = new ServerSocket(60000);//connects to specified port number from client
    Socket serverSide = getServed.accept();//listens for any connections on the port and accepts them
    System.out.println("Connected from client side");
     DataInputStream server_in = new DataInputStream(serverSide.getInputStream());
     DataOutputStream server_out = new DataOutputStream(serverSide.getOutputStream());

     while(true){
       int whatEquation = server_in.readInt();//determines what equation user will want to solve
       System.out.println("Current number: "+whatEquation);//
       //do some code here
       switch(whatEquation){//if the user entered 2, the server will solve the quadratic equation
         case 2:
         int a,b,c;
         double discriminant, root1, root2;
         a = server_in.readInt();
         b = server_in.readInt();
         c = server_in.readInt();
         discriminant = server_in.readDouble();
         System.out.println("Starting to solve the Quadratic Equation....");
         System.out.println("Current discriminant is: "+discriminant);
         if(discriminant> 0)
                {
                    String roots = "Roots are real and unequal";
                    root1 = ( - b + Math.sqrt(discriminant))/(2*a);
                    root2 = (-b - Math.sqrt(discriminant))/(2*a);
                    server_out.writeUTF(roots);
                    server_out.writeDouble(discriminant);
                    server_out.writeDouble(root1);
                    server_out.writeDouble(root2);
                }
                else if(discriminant == 0)
                {
                    String roots2 = "Roots are real and equal";
                    root1 = (-b+Math.sqrt(discriminant))/(2*a);
                    server_out.writeUTF(roots2);
                    server_out.writeDouble(discriminant);
                    server_out.writeDouble(root1);
                }
                else
                {
                    String roots3 = "Roots are imaginary.";
                    server_out.writeUTF(roots3);
                    server_out.writeDouble(discriminant);
                }
                break;

       case 1://the server will use this equation if one is entered
         double a2,b2;
         a2= server_in.readDouble();
         b2= server_in.readDouble();
         System.out.println("Here are the numbers you entered: "+a2+" "+b2);
         double c2 = Math.sqrt((a2*a2)+(b2*b2));
         server_out.writeDouble(c2);
         break;
         case 0:  serverSide.close();

       }
       }

     }
  }
