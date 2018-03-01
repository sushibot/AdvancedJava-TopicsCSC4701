import java.rmi.*;


public interface ObjectDevice extends Remote{
  public String search(String p)throws RemoteException;
  public String getAll() throws RemoteException;
  public String type(String p)throws RemoteException;
  public String entry(String p) throws RemoteException;
  public String weakness(String p)throws RemoteException;


}
