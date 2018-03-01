package hpu.edu.gfontan1.serialServer;

import java.io.*;
import java.net.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
public class Server implements Serializable {

ServerSocket socket;
ListeningThread thread;

public static void main(String [] args) throws Exception {
  Server server = new Server(6969);
  server.start();
}
public Server(int port) throws IOException {

    socket = new ServerSocket(port);

} // end of constructor


/*************************************************************************
* Starts the listening thread.
*************************************************************************/
public void start() {

    thread = new ListeningThread();
    thread.start();

} // end of start method

/*************************************************************************
* Stops the listening thread to shut down the server.
*************************************************************************/
public void shutdown() throws IOException {

    thread.shutdown();

} // end of start method
private class ListeningThread extends Thread {

boolean keep_going;

/*************************************************************************
* Constructor. Always name your threads.
*************************************************************************/
public ListeningThread() {
    super("Server Listener");
}

/*************************************************************************
* Stops this thread to shut down the server.
*************************************************************************/
public void shutdown() throws IOException {

    /***********************************
    * set the keep_going flag to false *
    ***********************************/
    keep_going = false;
    System.out.println("closing server socket");
    socket.close();

    /*****************************************
    * wait until the thread is actually done *
    *****************************************/
    System.out.println("Waiting for listening thread to exit");
    try { join(); }
    catch(InterruptedException e) {}

    System.out.println("Server shut down");

} // end of shutdown method

public void run() {

    try {
        boolean keep_going = true;
        while(keep_going) {
            System.out.println("Listening for connection on port "+
                               socket.getLocalPort());
            Socket s = socket.accept();
            ClientHandler handler = new ClientHandler(s);
            handler.start();

            System.out.println("Got a connection");

        } // end of loop

    } catch(IOException e) {
        e.printStackTrace();
    }

} // end of run method

} // end of ListeningThread


private class ClientHandler extends Thread {

Socket socket;
public ClientHandler(Socket socket) {

    this.socket = socket;

} // end of constructor
public void run() {
    try {
        /**********************************************************
        * create a buffered reader to read from the socket.
        * The buffered reader has a readLine() method
        **********************************************************/
        BufferedReader reader = new BufferedReader(
                                new InputStreamReader(
                                         socket.getInputStream()));

        /*******************************************************
        * Create a PrintWriter for writing to the socket.
        * PrintWriter has a println() method
        *******************************************************/
        PrintWriter writer = new PrintWriter(
                            new OutputStreamWriter(
                                        socket.getOutputStream()));
        while(true) {
            String line = reader.readLine();//takes in what the client sent

            if(line == null) {
                System.out.println("Client: "+socket.getLocalSocketAddress()+" closed connection");
                socket.close();
                return;
            }

            System.out.println("got "+line);
            if(line.equals("shutdown")) {
                Server.this.shutdown();

                writer.println("Server won't take any more connections");
                writer.flush();
                return;
            }
            StringBuilder builder = new StringBuilder();
            for(int i=line.length()-1; i>=0; --i) {
                builder.append(line.charAt(i));
            }
            String reply = builder.toString();
            System.out.println("replying: "+reply);
            writer.println(reply);
            writer.flush();

        } // end of loop

    } catch(IOException e) {
        e.printStackTrace();

    }

} // end of run method

} // end of ClientHandler inner class

//Inner class will handle the objects being sent to the server to display in a gui back to the client
private class sendData{
  String line;
  public sendData(String line){
    this.line = line;
  }
  try{
    ObjectInputStream is = new ObjectInputStream(new FileInputStream(fileName));
   SerialClassTest p = (SerialClassTest)is.readObject();

   is.close();
  }catch(FileNotFoundException e){
    e.printStackTrace();
  }catch(IOException e){
    e.printStackTrace();
  }catch(ClassNotFoundException e){
    e.printStackTrace();
  }
}//end of sendData class

//This inner class will show what data the user has inputted and will confirm if it is correct or not
private class ServerGUI extends JFrame{
  JLabel firstName;
  JLabel lastName;
  JLabel role;
  JLabel playerName;
  JPanel panel = new JPanel();
  JFrame window = new JFrame();
public void runGUI(){
  window.add(panel);
  window.setSize(400,400);
  window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

}


}//end of server GUI class

} // end of DemoServer class
