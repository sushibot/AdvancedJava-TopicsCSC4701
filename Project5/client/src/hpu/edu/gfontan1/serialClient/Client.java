package hpu.edu.gfontan1.serialClient;
import java.io.*;
import java.net.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;
public class Client implements Serializable{

/*************************************************************************
* Here's the main method.
*************************************************************************/
public static void main(String[] args) throws Exception {
  Scanner in = new Scanner(System.in);

    Socket socket = new Socket("localhost", 6969);
    BufferedReader reader = new BufferedReader(
                            new InputStreamReader(
                                        socket.getInputStream()));
    PrintWriter writer = new PrintWriter(
                         new OutputStreamWriter(
                                    socket.getOutputStream()));
    BufferedReader keyboard = new BufferedReader(
                                new InputStreamReader(System.in));
    while(true) {
        String line = keyboard.readLine();
        if(line.equals("disconnect")) {
            socket.close();
            break;
        }
        System.out.println("sending: "+line);
        writer.println(line);
        writer.flush();
        if(writer.checkError()) {
            throw new IOException("Error writing");
        }
        /*********************************
        * read the reply from the server *
        *********************************/
        String reply = reader.readLine();
        if(reply == null) {
            System.out.println("Server disconnected");
            socket.close();
            break;
        }
        System.out.println("received: "+reply);
    } // end of loop
} // end of main

/*
*This class will start a gui when the client runs and the user will input information
*/
private class clientGUI extends JFrame{//start of GUI class
JPanel panel = new Panel();
JFrame window = new JFrame();
JTextField fName = new JTextField();
JTextField lName = new JTextField();
JTextfield pName = new JTextField();
JTextField role = new JTextField();
JLabel fn = new JLabel("First Name");
JLabel ln = new JLabel("Last Name");
JLabel pn = new JLabel("Player Name");
JLabel rn = new JLabel("Role");
JButton saveData;
String obj_first_name;
String obj_last_name;
String obj_player_name;
String obj_role;
SaveObject p1 = new SaveObject(String obj_first_name, String obj_last_name, String obj_player_name, String obj_role);

public clientGUI(String firstN, String lastN, String roleN, String playerN){
  this.obj_first_name = firstN;
  this.obj_last_name = lastN;
  this.obj_player_name = playerN;
  this.obj_role = roleN;
}
  public void run(){//start of run method
    window.setTitle("Enter a First Name, Last Name, Player Name and Role");
    window.setSize(300,400);
    window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    window.add(panel);
    window.add(fName);
    window.add(fn);
    window.add(lName);
    window.add(ln);
    window.add(pName);
    window.add(pn);
    window.add(role);
    window.add(rn);
    window.add(saveData);
    panel.setLayout(new GridLayout(3,4,5,10));
    panel.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
    saveData = new Samples("Save");
    saveData.addActionListener(new ActionListener(){
      @Override
      public void actionPerformed(ActionEvent e){
        try{
          saveData();
        }catch(Exception ex){
          ex.printStackTrace();
        }
      }
    });
    window.setVisible(true);
  }//end of run method
}//end of gui class

/*
*
*This method will save and send the player object to the server
*
*/
private class SaveObject{
  Object obj = new Object();
  public SaveObject(Object obj){
    this.obj = obj;
  }

  public void saveData(){//start of save data method
    try{
      ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream(fileName));
      os.writeObject(obj);
      os.close();
    } catch(FileNotFoundException e){
      e.printStackTrace();
    } catch(IOException e){
      e.printStackTrace();
    }
    System.out.println("Saving...");
  }//end of saveData
}

} // end of Main class
