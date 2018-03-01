package hpu.edu.gfontan1.ref;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.Reference;
import java.lang.ref.SoftReference;
import java.util.*;
import java.io.*;

public class What {
static Random rng = new Random();
public static class A {
static int index = 0;
static int myIndex = 0;
static int[][] data = new int [512][512];
public A(){
    System.out.println("≈≈≈≈≈≈≈≈≈ Hello from Object A ≈≈≈≈≈≈≈≈≈");
}

}

public static class B {
//strong reference to Object A
private A strRef;
public void setStrRef(A ref){
          this.strRef = ref;
          System.out.println("˜˜˜˜˜˜˜ Hello from Object B ˜˜˜˜˜˜˜");
}
}

public static SoftReference<A> cache;
static Runtime run = Runtime.getRuntime();
public static void main(String [] args) throws InterruptedException {
        //creates many instances of object A
        System.out.println("Creating new instance of Object A...");
        What.A instanceA = new What.A();
        //wraps it in a soft reference
        Thread.sleep(50);
        System.out.println("Storing it into a cache....");
        cache = new SoftReference<What.A>(instanceA);
        //Object A is only soft referencable now and will be deleted in some time
        Thread.sleep(50);
        System.out.println("Deleting Strong reference to Object A.....");
        instanceA = null;
        System.out.println("Object A is now deleted. It is encapsuled in a cache and will only be referencable for sometime");
        System.out.println();

        What.B instanceB = new What.B();
        System.out.println("Creating new instance of Object B....."+instanceB);
        instanceA = cache.get();
        System.out.println(instanceA);
        if(instanceA == null) {
            System.out.println("Creating new instance of Object A and wrapping it in a Soft Reference called cache: ");
                instanceA = new What.A();
                cache = new SoftReference<What.A>(instanceA);
        }
        instanceB.setStrRef(instanceA);
        instanceA=null;
        }
}
