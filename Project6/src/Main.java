import java.rmi.*;
import java.rmi.registry.*;
import java.util.*;


public class Main {
public static void main(String [] args) throws Exception {



        Scanner in = new Scanner(System.in);
        Registry registry = LocateRegistry.getRegistry("localhost");
        ObjectDevice dex = (ObjectDevice)registry.lookup("dex");

        //call method in the stub
        System.out.println("Aloha! This is a Pokedex. It contains 25 pokemon entries. You can learn about each one!");
        System.out.println("Which pokemon do you want to learn about? ");
        String choose = in.next();
        String found = dex.search(choose);
        String info;
        String weak;
        String strong;
        String category;
        char choice = 'o';

        while(choice != 'n') {
                System.out.println("["+found+"]");
                System.out.println("Type in a number to learn more about this pokemon: ");
                System.out.println("1 to learn about what it is \n2 to learn it's typing \n3 to learn what it's weak against");
                int what = in.nextInt();
                switch(what) {
                case 1:
                        info = dex.entry(found);
                        System.out.println("[-"+info+"-]");
                        break;
                case 2:
                        category = dex.type(found);
                        System.out.println("{-"+category+"-}");
                        break;
                case 3:
                        weak = dex.weakness(found);
                        System.out.println(weak);
                        break;
                default:
                        System.out.println("I didn't understand what you said!");
                }
                System.out.println("Would you like to learn more? Type in y or n ");
                String input = in.next().toLowerCase();
                choice = input.charAt(0);
        }
}
}
