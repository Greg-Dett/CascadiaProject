/*
 *  Student number 21400044 -        Thomas Reynolds  -         gitID Reynolds21
 *  Student number 21305806 -        Greg Dettling    -         gitID Greg-Dett
 *  Student number 21463472 -        Ganto Badammoyun -         gitID Gantobadan
 * */

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;

public class Animal {
    public Animal(Casscadia.Animalselect type) throws IOException {
    this.Animaltype=type;
    switch (type){
        case Hawk -> AnimalName="Hawk";
        case Elk -> AnimalName="Elk";
        case Fox -> AnimalName="Fox";
        case Bear -> AnimalName="Bear";
        case Salmon -> AnimalName="Salmon";
    }
    switch (type){//sets animal picture for when its placed
            case Hawk ->   animalImage= ImageIO.read(Casscadia.class.getResource("cascadiaPNGs/hawk.png")).getScaledInstance(32,32,Image.SCALE_SMOOTH);
            case Elk -> animalImage=ImageIO.read(Casscadia.class.getResource("cascadiaPNGs/elk.png")).getScaledInstance(32,32,Image.SCALE_SMOOTH);
            case Fox -> animalImage=ImageIO.read(Casscadia.class.getResource("cascadiaPNGs/fox.png")).getScaledInstance(32,32,Image.SCALE_SMOOTH);
            case Bear -> animalImage=ImageIO.read(Casscadia.class.getResource("cascadiaPNGs/Bear.png")).getScaledInstance(32,32,Image.SCALE_SMOOTH);
            case Salmon -> animalImage=ImageIO.read(Casscadia.class.getResource("cascadiaPNGs/fish.png")).getScaledInstance(32,32,Image.SCALE_SMOOTH);
        }
        switch (type){//sets animal picture for when it is a possible placed animal
            case Hawk ->   possibleImage= ImageIO.read(Casscadia.class.getResource("cascadiaPNGs/hawk.png")).getScaledInstance(20,20,Image.SCALE_SMOOTH);
            case Elk -> possibleImage=ImageIO.read(Casscadia.class.getResource("cascadiaPNGs/elk.png")).getScaledInstance(20,20,Image.SCALE_SMOOTH);
            case Fox -> possibleImage=ImageIO.read(Casscadia.class.getResource("cascadiaPNGs/fox.png")).getScaledInstance(20,20,Image.SCALE_SMOOTH);
            case Bear -> possibleImage=ImageIO.read(Casscadia.class.getResource("cascadiaPNGs/Bear.png")).getScaledInstance(20,20,Image.SCALE_SMOOTH);
            case Salmon -> possibleImage=ImageIO.read(Casscadia.class.getResource("cascadiaPNGs/fish.png")).getScaledInstance(20,20,Image.SCALE_SMOOTH);
        }
}
    Image animalImage;
    Image possibleImage;
    public String getAnimalName(){
        return this.AnimalName;
    }
    Casscadia.Animalselect Animaltype;
    String AnimalName;
}
