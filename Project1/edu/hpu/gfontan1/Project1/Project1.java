/************************************
*3, 5, 3 and 5
*This Program prints out fizz if a number is divisible by 3,
*buzz if a number is divisible by 5, or fizzbuzz if divisble
*by 3 and 5.
*@Author Gabriel Fontanilla
*@Since 2017-08-29
*@Version 1.8.0_144
*************************************/
package edu.hpu.gfontan.project1;
import java.util.Scanner;
class project1{
    public static void main(String[] args) {
        Scanner user = new Scanner(System.in);
        System.out.println("Enter your name: ");
        String name =user.nextLine();
        System.out.print("How many times do you want your name to be printed?");
        int print = user.nextInt();

        for (int i = 0; i <=print; i++) {

            if(i%3 == 0 && i%5==0){
                System.out.println("__FIZZ BUZZ!!!!__"+" "+i);
            }
            else if(i%5 == 0 ){
                System.out.println("__BUZZ!!__"+" "+i);
            }
            else if(i%3 == 0 ){
                System.out.println("__FIZZ!!__"+" "+i);
            }
            else{
                    System.out.println(name);
            }
        }
        }
    }
