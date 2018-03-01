import java.rmi.*;
import java.rmi.registry.*;
import java.rmi.server.*;
import java.util.*;
import java.io.*;
public class Pokedex implements ObjectDevice {
static ArrayList<String> pokemon = new ArrayList<String>();
static ArrayList<String> entries = new ArrayList<String>();
static ArrayList<String> types = new ArrayList<String>();
  public static void main(String[] args) throws Exception{
    entries.add("It can go for days without eating a single morsel. In the bulb on its back, it stores energy.");//1
    entries.add("The bulb on its back grows by drawing energy. It gives off an aroma when it is ready to bloom.");//2
    entries.add("The flower on its back catches the sun's rays. The sunlight is then absorbed and used for energy.");//3
    entries.add("The flame at the tip of its tail makes a sound as it burns. You can only hear it in quiet places.");//4
    entries.add("Tough fights could excite this POKéMON. When excited, it may blow out bluish-white flames.");//5
    entries.add("When expelling a blast of super hot fire, the red flame at the tip of its tail burns more intensely.");//6
    entries.add("Shoots water at prey while in the water. Withdraws into its shell when in danger.");//7
    entries.add("When tapped, this POKéMON will pull in its head, but its tail will still stick out a little bit.");//8
    entries.add("Once it takes aim at its enemy, it blasts out water with even more force than a fire hose.");//9
    entries.add("If you touch the feeler on top of its head, it will release a horrible stink to protect itself.");//10
    entries.add("Hardens its shell to protect itself. However, a large impact may cause it to pop out of its shell.");//11
    entries.add("Its wings, covered with poisonous powders, repel water. This allows it to fly in the rain.");//12
    entries.add("Beware of the sharp stinger on its head. It hides in grass and bushes where it eats leaves.");//13
    entries.add("Able to move only slightly. When endangered, it may stick out its stinger and poison its enemy.");//14
    entries.add("It has 3 poisonous stingers on its forelegs and its tail. They are used to jab its enemy repeatedly.");//15
    entries.add("	Very docile. If attacked, it will often kick up sand to protect itself rather than fight back.");//16
    entries.add("This POKéMON is full of vitality. It constantly flies around its large territory in search of prey.");//17
    entries.add("This POKéMON flies at Mach 2 speed, seeking prey. Its large talons are feared as wicked weapons.");//18
    entries.add("Will chew on anything with its fangs. If you see one, it is certain that 40 more live in the area.");//19
    entries.add("Its hind feet are webbed. They act as flippers, so it can swim in rivers and hunt for prey.");//20
    entries.add("Inept at flying high. However, it can fly around very fast to protect its territory.");//21
    entries.add("A POKéMON that dates back many years. If it senses danger, it flies high and away, instantly.");//22
    entries.add("The older it gets, the longer it grows. At night, it wraps its long body around tree branches to rest.");//23
    entries.add("The frightening patterns on its belly have been studied. Six variations have been confirmed.");//24
    entries.add("It keeps its tail raised to monitor its surroundings. If you yank its tail, it will try to bite you.");//25

    types.add("grass/poison");//1
    types.add("grass/poison");//2
    types.add("grass/poison");//3
    types.add("fire");//4
    types.add("fire");//5
    types.add("fire/flying");//6
    types.add("water");//7
    types.add("water");//8
    types.add("water");//9
    types.add("bug");//10
    types.add("bug");//11
    types.add("bug/flying");//12
    types.add("bug/poison");//13
    types.add("bug/poison");//14
    types.add("bug/poison");//15
    types.add("flying/normal");//16
    types.add("flying/normal");//17
    types.add("flying/normal");//18
    types.add("normal");//19
    types.add("normal");//20
    types.add("flying/normal");//21
    types.add("flying/normal");//22
    types.add("poison");//23
    types.add("poison");//24
    types.add("electric");//25

    pokemon.add("bulbasaur");//1
    pokemon.add("ivysaur");//2
    pokemon.add("venasaur");//3
    pokemon.add("charmander");//4
    pokemon.add("charmeleon");//5
    pokemon.add("charizard");//6
    pokemon.add("squirtle");//7
    pokemon.add("wartortle");//8
    pokemon.add("blastoise");//9
    pokemon.add("caterpie");//10
    pokemon.add("metapod");//11
    pokemon.add("butterfree");//12
    pokemon.add("weedle");//13
    pokemon.add("kakuna");//14
    pokemon.add("beedrill");//15
    pokemon.add("pidgey");//16
    pokemon.add("pidgetto");//17
    pokemon.add("pidgeot");//18
    pokemon.add("rattata");//19
    pokemon.add("raticate");//20
    pokemon.add("spearow");//21
    pokemon.add("fearow");//22
    pokemon.add("ekans");//23
    pokemon.add("arbok");//24
    pokemon.add("pikachu");//25

    ObjectDevice dex = new Pokedex();
    Remote stub = UnicastRemoteObject.exportObject(dex, 6969);
    Registry registry = LocateRegistry.getRegistry("localhost");
    registry.rebind("dex", stub);
  }

  public String getAll(){
    String what = "";
    for(int i = 0; i<pokemon.size(); i++){
      what = pokemon.get(i);
    }
    return what;
  }

  public String search(String p){
    int i = pokemon.indexOf(p);
    return pokemon.get(i);
  }

  public String entry(String p){
    int index_pokemon = pokemon.indexOf(p);
    String answer="";
    for(int i = 0; i<entries.size(); i++){
      if(index_pokemon==i)
      answer = entries.get(i);
    }
    return answer;
  }

  public String type(String p){
    int index_pokemon = pokemon.indexOf(p);
    String answer = "";
    for(int i = 0; i<types.size(); i++){
      if(index_pokemon == i)
      answer = types.get(i);
    }
    return answer;
  }
  public String weakness(String p){
    int index_pokemon = pokemon.indexOf(p);
    String answer = "";
    for(int i = 0; i<types.size(); i++){
      answer = types.get(i);
    }
    String response = "";
    if(answer.matches("grass/poison")) {//gras poison
      response= "Weak against pyschic, fire, flying, and ground attacks";
    } else if(answer.matches("fire/flying")){//fire flying
      response = "Weak against water, rock, and electric attacks";
    }else if(answer.matches("water")){//water
      response = "Weak against grass and electric attacks";
    } else if(answer.matches("bug/flying")){//bug flying
      response = "Weak against fire, rock, electric and poison attacks";
    } else if(answer.matches("bug/poison")){//bug poison
      response = "Weak against fire, ground, and flying attacks";
    } else if(answer.matches("flying/normal")){//flyig normal
      response = "Weak against electric and rock attacks";
    } else if(answer.matches("electric")){//electric
      response = "Weak against ground attacks";
    } else if(answer.matches("fire")){//fire
      response = "Weak against water, ground and rock attacks";
    } else if(answer.matches("normal")){//normal
      response = "Weak against fighting and ghost attacks";
    } else if(answer.matches("bug")){//bug
      response = "Weak against fire and flying attacks";
    } else if(answer.matches("poison")) {//poison
      response = "Weak against ground and psychic moves";
    }
    return response;
  }
}
