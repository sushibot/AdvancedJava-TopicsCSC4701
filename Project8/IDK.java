package src/h

import java.lang.ref.ReferenceQueue;
import java.lang.ref.Reference;
import java.lang.ref.SoftReference;
import java.util.*;
import java.io.*;

public class IDK{

  public static class Data{
    static int index = 0;
    int[][] data = new int[1024][1024];
    int myIndex;
    public Data(){
      index+=1;
      this.myIndex = index;
      System.out.println("INDEX: "+this.myIndex);
    }
  }

  public static int removedSoftRefs = 0;
  public static Set<SoftReference<Data>> someData = new HashSet<>(1000);
  public static ReferenceQueue <Data> makeSpace = new ReferenceQueue<>();
  static Runtime run = Runtime.getRuntime();
  static Random rng = new Random();

  public static void main(String [] args){
    Scanner in = new Scanner(System.in);

    for(int i = 0; i<5000; i++){
      Data stats = new Data();
      someData.add(new SoftReference<>(stats,makeSpace));
      System.out.println("FREE MEMORY: "+run.freeMemory());
      System.out.println("TOTAL MEMORY: "+run.totalMemory());
      double usage = 1-(run.freeMemory() / (double) run.totalMemory());
      usage *= 100;
      System.out.println("MEMORY USED: "+usage);
      clearMem();
      try
      {
        Thread.sleep( (long) (10* (1+ rng.nextDouble())));
      }
      catch(Exception e)
      {
        System.err.println("Sleep sad :(");
      }
    }
  }
    public static void clearMem() {
      Reference<? extends Data> ref = makeSpace.poll();
      while(ref!=null){
        System.out.println("Making Space....");
        if(someData.remove(ref)){
            removedSoftRefs++;
        }
        ref.clear();
        ref = makeSpace.poll();
      }
    }
}
