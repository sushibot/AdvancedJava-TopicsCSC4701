import java.io.*;
public class Role implements Serializable{
  String roleName = "";
  int health = 0;
  int attack =0;
  int defence = 0;
  int magicAtk = 0;
  int rangeAtk = 0;
  int speed = 0;

  public Role(Sting rn){
  this.roleName = rn;
  }
  public static String chosenRole(String name){
    if(name.toLowerCase().matches("tank")){
      return tank();
    }else if(name .toLowerCase().matches("warrior")){
      warrior();
    }

  }
  public void tank(){
    health = 100;
    attack = 68;
    defence = 80;
    magicAtk = 0;
    speed = 40;
  }

  public void warrior(){
    health = 100;
    attack = 80;
    defence = 55;
    magicAtk = 10;
    speed = 60;
  }

  public void mage(){
    health = 100;
    attack =10;
    defence = 50;
    magicAtk = 85;
    speed = 45;
  }

  public void range(){
    health =100;
    attack = 30;
    defence = 60;
    magicAtk = 0;
    rangeAtk = 70;
    speed = 80;
  }
}
