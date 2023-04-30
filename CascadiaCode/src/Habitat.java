/*
 *  Student number 21400044 -        Thomas Reynolds  -         gitID Reynolds21
 *  Student number 21305806 -        Greg Dettling    -         gitID Greg-Dett
 *  Student number 21463472 -        Ganto Badammoyun -         gitID Gantobadan
 * */

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class Habitat {
    public Habitat(Casscadia.Habitatselect ex1, Casscadia.Habitatselect ex2) throws IOException {




        switch (ex1){//sets name and picture for habitat based on constructor variables
            case forest -> {
                switch (ex2){
                    case mountain -> {
                        HabitatName="forest/mountain";
                        this.habitat1=ex1;
                        this.habitat2=ex2;
                        habitatImage=ImageIO.read(Casscadia.class.getResource("cascadiaPNGs/MountainForest0.png")).getScaledInstance(64, 64, Image.SCALE_SMOOTH);
                    }
                    case river -> {
                        HabitatName="river/forest";
                        this.habitat1=ex2;
                        this.habitat2=ex1;
                        habitatImage=ImageIO.read(Casscadia.class.getResource("cascadiaPNGs/ForestRiver0.png")).getScaledInstance(64, 64, Image.SCALE_SMOOTH);
                    }
                    case wetland -> {
                        HabitatName="forest/wetland";
                        this.habitat1=ex1;
                        this.habitat2=ex2;
                        habitatImage=ImageIO.read(Casscadia.class.getResource("cascadiaPNGs/WetlandForest0.png")).getScaledInstance(64, 64, Image.SCALE_SMOOTH);
                    }
                    case prairie -> {
                        HabitatName="forest/prairie";
                        this.habitat1=ex1;
                        this.habitat2=ex2;
                        habitatImage=ImageIO.read(Casscadia.class.getResource("cascadiaPNGs/SandForest0.png")).getScaledInstance(64, 64, Image.SCALE_SMOOTH);
                    }
                    case none -> {
                        HabitatName="forest";
                        this.habitat1=ex1;
                        this.habitat2= Casscadia.Habitatselect.none;
                        habitatImage = ImageIO.read(Casscadia.class.getResource("cascadiaPNGs/Forest.png")).getScaledInstance(64, 64, Image.SCALE_SMOOTH);
                    }

                }
            }
            case mountain -> {
                switch (ex2) {
                        case river -> {
                            HabitatName="river/mountain";
                            this.habitat1=ex2;
                            this.habitat2=ex1;
                            habitatImage=ImageIO.read(Casscadia.class.getResource("cascadiaPNGs/MountainRiver0.png")).getScaledInstance(64, 64, Image.SCALE_SMOOTH);
                        }
                        case wetland -> {
                            HabitatName="mountain/wetland";
                            this.habitat1=ex1;
                            this.habitat2=ex2;
                            habitatImage=ImageIO.read(Casscadia.class.getResource("cascadiaPNGs/WetlandMountain0.png")).getScaledInstance(64, 64, Image.SCALE_SMOOTH);
                        }
                        case prairie -> {
                            HabitatName="mountain/prairie";
                            this.habitat1=ex1;
                            this.habitat2=ex2;
                            habitatImage=ImageIO.read(Casscadia.class.getResource("cascadiaPNGs/SandMountain0.png")).getScaledInstance(64, 64, Image.SCALE_SMOOTH);
                        }
                        case forest -> {
                            HabitatName="forest/mountain";
                            this.habitat1=ex2;
                            this.habitat2=ex1;
                            habitatImage=ImageIO.read(Casscadia.class.getResource("cascadiaPNGs/MountainForest0.png")).getScaledInstance(64, 64, Image.SCALE_SMOOTH);
                        }
                       case none -> {
                        HabitatName="mountain";
                           this.habitat1=ex1;
                           this.habitat2= Casscadia.Habitatselect.none;
                        habitatImage = ImageIO.read(Casscadia.class.getResource("cascadiaPNGs/Mountain.png")).getScaledInstance(64, 64, Image.SCALE_SMOOTH);
                    }

                    }
                }

            case river -> {
                switch (ex2) {
                    case wetland -> {
                        HabitatName="river/wetland";
                        this.habitat1=ex1;
                        this.habitat2=ex2;
                        habitatImage=ImageIO.read(Casscadia.class.getResource("cascadiaPNGs/WetlandRiver0.png")).getScaledInstance(64, 64, Image.SCALE_SMOOTH);
                    }
                    case prairie -> {
                        HabitatName="river/prairie";
                        this.habitat1=ex1;
                        this.habitat2=ex2;
                        habitatImage=ImageIO.read(Casscadia.class.getResource("cascadiaPNGs/SandRiver0.png")).getScaledInstance(64, 64, Image.SCALE_SMOOTH);
                    }
                    case forest -> {
                        HabitatName="river/forest";
                        this.habitat1=ex1;
                        this.habitat2=ex2;
                        habitatImage=ImageIO.read(Casscadia.class.getResource("cascadiaPNGs/ForestRiver0.png")).getScaledInstance(64, 64, Image.SCALE_SMOOTH);
                    }
                    case mountain -> {
                        HabitatName="river/mountain";
                        this.habitat1=ex1;
                        this.habitat2=ex2;
                        habitatImage=ImageIO.read(Casscadia.class.getResource("cascadiaPNGs/MountainRiver0.png")).getScaledInstance(64, 64, Image.SCALE_SMOOTH);
                    }
                    case none -> {
                        HabitatName="river";
                        this.habitat1=ex1;
                        this.habitat2= Casscadia.Habitatselect.none;
                        habitatImage = ImageIO.read(Casscadia.class.getResource("cascadiaPNGs/River.png")).getScaledInstance(64, 64, Image.SCALE_SMOOTH);
                    }



                }
            }
            case wetland -> {
            switch (ex2) {
                case river -> {
                    HabitatName="river/wetland";
                    this.habitat1=ex2;
                    this.habitat2=ex1;
                    habitatImage=ImageIO.read(Casscadia.class.getResource("cascadiaPNGs/WetlandRiver0.png")).getScaledInstance(64, 64, Image.SCALE_SMOOTH);
                }
                case prairie -> {
                    HabitatName="prairie/wetland";
                    this.habitat1=ex2;
                    this.habitat2=ex1;
                    habitatImage=ImageIO.read(Casscadia.class.getResource("cascadiaPNGs/WetlandSand0.png")).getScaledInstance(64, 64, Image.SCALE_SMOOTH);
                }
                case mountain -> {
                    HabitatName="mountain/wetland";
                    this.habitat1=ex2;
                    this.habitat2=ex1;
                    habitatImage=ImageIO.read(Casscadia.class.getResource("cascadiaPNGs/WetlandMountain0.png")).getScaledInstance(64, 64, Image.SCALE_SMOOTH);
                }
                case forest -> {
                    HabitatName="forest/wetland";
                    this.habitat1=ex2;
                    this.habitat2=ex1;
                    habitatImage=ImageIO.read(Casscadia.class.getResource("cascadiaPNGs/WetlandForest0.png")).getScaledInstance(64, 64, Image.SCALE_SMOOTH);
                }
                case none -> {
                    HabitatName="wetland";
                    this.habitat1=ex1;
                    this.habitat2= Casscadia.Habitatselect.none;
                    habitatImage = ImageIO.read(Casscadia.class.getResource("cascadiaPNGs/Wetland.png")).getScaledInstance(64, 64, Image.SCALE_SMOOTH);
                }

        }}
            case prairie -> {
                switch (ex2) {
                    case wetland -> {
                        HabitatName="prairie/wetland";
                        this.habitat1=ex1;
                        this.habitat2=ex2;
                        habitatImage=ImageIO.read(Casscadia.class.getResource("cascadiaPNGs/WetlandSand0.png")).getScaledInstance(64, 64, Image.SCALE_SMOOTH);
                    }
                    case river -> {
                        HabitatName="river/prairie";
                        this.habitat1=ex2;
                        this.habitat2=ex1;
                        habitatImage=ImageIO.read(Casscadia.class.getResource("cascadiaPNGs/SandRiver0.png")).getScaledInstance(64, 64, Image.SCALE_SMOOTH);
                    }
                    case mountain -> {
                        HabitatName="mountain/prairie";
                        this.habitat1=ex2;
                        this.habitat2=ex1;
                        habitatImage=ImageIO.read(Casscadia.class.getResource("cascadiaPNGs/SandMountain0.png")).getScaledInstance(64, 64, Image.SCALE_SMOOTH);
                    }
                    case forest -> {
                        HabitatName="forest/prairie";
                        this.habitat1=ex2;
                        this.habitat2=ex1;
                        habitatImage=ImageIO.read(Casscadia.class.getResource("cascadiaPNGs/SandForest0.png")).getScaledInstance(64, 64, Image.SCALE_SMOOTH);
                    }
                    case none -> {
                        HabitatName="prairie";
                        this.habitat1=ex1;
                        this.habitat2= Casscadia.Habitatselect.none;
                        habitatImage = ImageIO.read(Casscadia.class.getResource("cascadiaPNGs/Sand.png")).getScaledInstance(64, 64, Image.SCALE_SMOOTH);
                    }

                }
            }
        }


        this.topleft=ex1;
        if (ex2==Casscadia.Habitatselect.none){
            this.bottomright=ex1;
        }else{
            this.bottomright=ex2;
        }

            //chooses animals which can be placed on the habitat
            Random rand = new Random();
            numPossibleAnimals = rand.nextInt(3-1+1)+1;
            while (possibleAnimals.size()<numPossibleAnimals) {
                int pick = new Random().nextInt(5);
                Animal newpick = new Animal(Casscadia.Animalselect.values()[pick]);
                if (isPresent(newpick)==false) {
                    possibleAnimals.add(newpick);
                }
            }
        }
        public void rotateHabitat(int rotation) throws IOException {
            Rotation=rotation;
            topleft=null;
            bottomright=null;
            topright=null;
            bottomleft=null;

            switch (rotation){
                case 0 -> {
                    topleft=this.habitat1;
                    bottomright=this.habitat2;
                }
                case 1 -> {
                    topright=this.habitat1;
                    bottomleft=this.habitat2;
                }
                case 2 -> {
                    bottomright=this.habitat1;
                    topleft=this.habitat2;
                }
                case 3 -> {
                    bottomleft=this.habitat1;
                    topright=this.habitat2;

                }
            }

            switch (this.habitat1){//code that changes the image of the habitat when it is rotated
                case forest -> {
                    switch (this.habitat2){
                        case mountain -> {
                            habitatImage=ImageIO.read(Casscadia.class.getResource("cascadiaPNGs/MountainForest"+rotation+".png")).getScaledInstance(64, 64, Image.SCALE_SMOOTH);
                        }
                        case river -> {
                            habitatImage=ImageIO.read(Casscadia.class.getResource("cascadiaPNGs/ForestRiver"+rotation+".png")).getScaledInstance(64, 64, Image.SCALE_SMOOTH);
                        }
                        case wetland -> {
                            habitatImage=ImageIO.read(Casscadia.class.getResource("cascadiaPNGs/WetlandRiver"+rotation+".png")).getScaledInstance(64, 64, Image.SCALE_SMOOTH);
                        }
                        case prairie -> {
                            habitatImage=ImageIO.read(Casscadia.class.getResource("cascadiaPNGs/SandForest"+rotation+".png")).getScaledInstance(64, 64, Image.SCALE_SMOOTH);
                        }
                        case none -> {
                        }

                    }
                }
                case mountain -> {
                    switch (this.habitat2) {
                        case river -> {
                            habitatImage=ImageIO.read(Casscadia.class.getResource("cascadiaPNGs/MountainRiver"+rotation+".png")).getScaledInstance(64, 64, Image.SCALE_SMOOTH);
                        }
                        case wetland -> {
                            habitatImage=ImageIO.read(Casscadia.class.getResource("cascadiaPNGs/WetlandMountain"+rotation+".png")).getScaledInstance(64, 64, Image.SCALE_SMOOTH);
                        }
                        case prairie -> {
                            habitatImage=ImageIO.read(Casscadia.class.getResource("cascadiaPNGs/SandMountain"+rotation+".png")).getScaledInstance(64, 64, Image.SCALE_SMOOTH);
                        }
                        case forest -> {
                            habitatImage=ImageIO.read(Casscadia.class.getResource("cascadiaPNGs/MountainForest"+rotation+".png")).getScaledInstance(64, 64, Image.SCALE_SMOOTH);
                        }
                        case none -> {
                        }

                    }
                }

                case river -> {
                    switch (this.habitat2) {
                        case wetland -> {
                            habitatImage=ImageIO.read(Casscadia.class.getResource("cascadiaPNGs/WetlandRiver"+rotation+".png")).getScaledInstance(64, 64, Image.SCALE_SMOOTH);
                        }
                        case prairie -> {
                            habitatImage=ImageIO.read(Casscadia.class.getResource("cascadiaPNGs/SandRiver"+rotation+".png")).getScaledInstance(64, 64, Image.SCALE_SMOOTH);
                        }
                        case forest -> {
                            habitatImage=ImageIO.read(Casscadia.class.getResource("cascadiaPNGs/ForestRiver"+rotation+".png")).getScaledInstance(64, 64, Image.SCALE_SMOOTH);
                        }
                        case mountain -> {
                            habitatImage=ImageIO.read(Casscadia.class.getResource("cascadiaPNGs/MountainRiver"+rotation+".png")).getScaledInstance(64, 64, Image.SCALE_SMOOTH);
                        }
                        case none -> {
                        }



                    }
                }
                case wetland -> {
                    switch (this.habitat2) {
                        case river -> {
                            habitatImage=ImageIO.read(Casscadia.class.getResource("cascadiaPNGs/WetlandRiver"+rotation+".png")).getScaledInstance(64, 64, Image.SCALE_SMOOTH);
                        }
                        case prairie -> {

                            habitatImage=ImageIO.read(Casscadia.class.getResource("cascadiaPNGs/WetlandSand"+rotation+".png")).getScaledInstance(64, 64, Image.SCALE_SMOOTH);
                        }
                        case mountain -> {

                            habitatImage=ImageIO.read(Casscadia.class.getResource("cascadiaPNGs/WetlandMountain"+rotation+".png")).getScaledInstance(64, 64, Image.SCALE_SMOOTH);
                        }
                        case forest -> {

                            habitatImage=ImageIO.read(Casscadia.class.getResource("cascadiaPNGs/WetlandRiver"+rotation+".png")).getScaledInstance(64, 64, Image.SCALE_SMOOTH);
                        }
                        case none -> {
                        }

                    }}
                case prairie -> {
                    switch (this.habitat2) {
                        case wetland -> {

                            habitatImage=ImageIO.read(Casscadia.class.getResource("cascadiaPNGs/WetlandSand"+rotation+".png")).getScaledInstance(64, 64, Image.SCALE_SMOOTH);
                        }
                        case river -> {

                            habitatImage=ImageIO.read(Casscadia.class.getResource("cascadiaPNGs/SandRiver"+rotation+".png")).getScaledInstance(64, 64, Image.SCALE_SMOOTH);
                        }
                        case mountain -> {

                            habitatImage=ImageIO.read(Casscadia.class.getResource("cascadiaPNGs/SandMountain"+rotation+".png")).getScaledInstance(64, 64, Image.SCALE_SMOOTH);
                        }
                        case forest -> {

                            habitatImage=ImageIO.read(Casscadia.class.getResource("cascadiaPNGs/SandForest"+rotation+".png")).getScaledInstance(64, 64, Image.SCALE_SMOOTH);
                        }
                        case none -> {
                        }

                    }
                }
            }


        }


    public String getHabitatName(){
        return HabitatName;
    }
    String HabitatName;
    Image habitatImage;
    ArrayList<Animal> possibleAnimals=new ArrayList<Animal>(); //the animals that can be placed on the habitat
    int numPossibleAnimals; //number of animals that can be placed on teh habitat
    int Rotation=0;

    Casscadia.Habitatselect topleft;
    Casscadia.Habitatselect topright;
    Casscadia.Habitatselect bottomleft;
    Casscadia.Habitatselect bottomright;


    Casscadia.Habitatselect habitat1;
    Casscadia.Habitatselect habitat2;
    public boolean isPresent(Animal animal){
        for (int i=0;i<this.possibleAnimals.size();i++){
            if (possibleAnimals.get(i).AnimalName==animal.AnimalName){
                return true;
            }
        }
        return false;
    }
    public boolean isPossible(Animal animal){//checks whether an animal can be placed on the habitat
        for (int i=0;i<numPossibleAnimals;i++){
            if (animal.AnimalName==possibleAnimals.get(i).AnimalName){
                return true;
            }
        }
        return false;
    }

    }



