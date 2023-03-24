import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class Habitat {
    public Habitat(Casscadia.Habitatselect ex1, Casscadia.Habitatselect ex2) throws IOException {
    this.habitat1=ex1;
    this.habitat2=ex2;
        switch (habitat1){
            case forest -> {
                switch (habitat2){
                    case mountain -> {
                        HabitatName="forest/mountain";
                        habitatImage=ImageIO.read(Casscadia.class.getResource("cascadiaPNGs/MountainForest.png")).getScaledInstance(64, 64, Image.SCALE_SMOOTH);
                    }
                    case river -> {
                        HabitatName="forest/river";
                        habitatImage=ImageIO.read(Casscadia.class.getResource("cascadiaPNGs/ForestRiver.png")).getScaledInstance(64, 64, Image.SCALE_SMOOTH);
                    }
                    case wetland -> {
                        HabitatName="forest/wetland";
                        habitatImage=ImageIO.read(Casscadia.class.getResource("cascadiaPNGs/WetlandRiver.png")).getScaledInstance(64, 64, Image.SCALE_SMOOTH);
                    }
                    case prairie -> {
                        HabitatName="forest/prairie";
                        habitatImage=ImageIO.read(Casscadia.class.getResource("cascadiaPNGs/SandForest.png")).getScaledInstance(64, 64, Image.SCALE_SMOOTH);
                    }
                    case none -> {
                        HabitatName="forest";
                        habitatImage = ImageIO.read(Casscadia.class.getResource("cascadiaPNGs/Forest.png")).getScaledInstance(64, 64, Image.SCALE_SMOOTH);
                    }

                }
            }
            case mountain -> {
                switch (habitat2) {
                        case river -> {
                            HabitatName="mountain/river";
                            habitatImage=ImageIO.read(Casscadia.class.getResource("cascadiaPNGs/MountainRiver.png")).getScaledInstance(64, 64, Image.SCALE_SMOOTH);
                        }
                        case wetland -> {
                            HabitatName="mountain/wetland";
                            habitatImage=ImageIO.read(Casscadia.class.getResource("cascadiaPNGs/WetlandMountain.png")).getScaledInstance(64, 64, Image.SCALE_SMOOTH);
                        }
                        case prairie -> {
                            HabitatName="mountain/prairie";
                            habitatImage=ImageIO.read(Casscadia.class.getResource("cascadiaPNGs/SandMountain.png")).getScaledInstance(64, 64, Image.SCALE_SMOOTH);
                        }
                        case forest -> {
                            HabitatName="forest/mountain";
                            habitatImage=ImageIO.read(Casscadia.class.getResource("cascadiaPNGs/MountainForest.png")).getScaledInstance(64, 64, Image.SCALE_SMOOTH);
                        }
                       case none -> {
                        HabitatName="mountain";
                        habitatImage = ImageIO.read(Casscadia.class.getResource("cascadiaPNGs/Mountain.png")).getScaledInstance(64, 64, Image.SCALE_SMOOTH);
                    }

                    }
                }

            case river -> {
                switch (habitat2) {
                    case wetland -> {
                        HabitatName="river/wetland";
                        habitatImage=ImageIO.read(Casscadia.class.getResource("cascadiaPNGs/WetlandRiver.png")).getScaledInstance(64, 64, Image.SCALE_SMOOTH);
                    }
                    case prairie -> {
                        HabitatName="river/prairie";
                        habitatImage=ImageIO.read(Casscadia.class.getResource("cascadiaPNGs/SandRiver.png")).getScaledInstance(64, 64, Image.SCALE_SMOOTH);
                    }
                    case forest -> {
                        HabitatName="forest/river";
                        habitatImage=ImageIO.read(Casscadia.class.getResource("cascadiaPNGs/ForestRiver.png")).getScaledInstance(64, 64, Image.SCALE_SMOOTH);
                    }
                    case mountain -> {
                        HabitatName="mountain/river";
                        habitatImage=ImageIO.read(Casscadia.class.getResource("cascadiaPNGs/MountainRiver.png")).getScaledInstance(64, 64, Image.SCALE_SMOOTH);
                    }
                    case none -> {
                        HabitatName="river";
                        habitatImage = ImageIO.read(Casscadia.class.getResource("cascadiaPNGs/River.png")).getScaledInstance(64, 64, Image.SCALE_SMOOTH);
                    }



                }
            }
            case wetland -> {
            switch (habitat2) {
                case river -> {
                    HabitatName="river/wetland";
                    habitatImage=ImageIO.read(Casscadia.class.getResource("cascadiaPNGs/WetlandRiver.png")).getScaledInstance(64, 64, Image.SCALE_SMOOTH);
                }
                case prairie -> {
                    HabitatName="river/prairie";
                    habitatImage=ImageIO.read(Casscadia.class.getResource("cascadiaPNGs/WetlandSand.png")).getScaledInstance(64, 64, Image.SCALE_SMOOTH);
                }
                case mountain -> {
                    HabitatName="mountain/wetland";
                    habitatImage=ImageIO.read(Casscadia.class.getResource("cascadiaPNGs/WetlandMountain.png")).getScaledInstance(64, 64, Image.SCALE_SMOOTH);
                }
                case forest -> {
                    HabitatName="forest/wetland";
                    habitatImage=ImageIO.read(Casscadia.class.getResource("cascadiaPNGs/WetlandRiver.png")).getScaledInstance(64, 64, Image.SCALE_SMOOTH);
                }
                case none -> {
                    HabitatName="wetland";
                    habitatImage = ImageIO.read(Casscadia.class.getResource("cascadiaPNGs/Wetland.png")).getScaledInstance(64, 64, Image.SCALE_SMOOTH);
                }

        }}
            case prairie -> {
                switch (habitat2) {
                    case wetland -> {
                        HabitatName="wetland/prairie";
                        habitatImage=ImageIO.read(Casscadia.class.getResource("cascadiaPNGs/WetlandSand.png")).getScaledInstance(64, 64, Image.SCALE_SMOOTH);
                    }
                    case river -> {
                        HabitatName="river/prairie";
                        habitatImage=ImageIO.read(Casscadia.class.getResource("cascadiaPNGs/SandRiver.png")).getScaledInstance(64, 64, Image.SCALE_SMOOTH);
                    }
                    case mountain -> {
                        HabitatName="mountain/prairie";
                        habitatImage=ImageIO.read(Casscadia.class.getResource("cascadiaPNGs/SandMountain.png")).getScaledInstance(64, 64, Image.SCALE_SMOOTH);
                    }
                    case forest -> {
                        HabitatName="forest/prairie";
                        habitatImage=ImageIO.read(Casscadia.class.getResource("cascadiaPNGs/SandForest.png")).getScaledInstance(64, 64, Image.SCALE_SMOOTH);
                    }
                    case none -> {
                        HabitatName="prairie";
                        habitatImage = ImageIO.read(Casscadia.class.getResource("cascadiaPNGs/Sand.png")).getScaledInstance(64, 64, Image.SCALE_SMOOTH);
                    }

                }
            }
        }





            Random rand = new Random();

            numPossibleAnimals = rand.nextInt(3-1+1)+1;
            while (possibleAnimals.size()<numPossibleAnimals) {
                int pick = new Random().nextInt(4);
                Animal newpick = new Animal(Casscadia.Animalselect.values()[pick]);
                if (isPresent(newpick)==false) {
                    possibleAnimals.add(newpick);
                }
            }
        }


    public String getHabitatName(){
        return HabitatName;
    }
    String HabitatName;
    Image habitatImage;
    ArrayList<Animal> possibleAnimals=new ArrayList<Animal>();
    int numPossibleAnimals;

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
    public boolean isPossible(Animal animal){
        for (int i=0;i<numPossibleAnimals;i++){
            if (animal.AnimalName==possibleAnimals.get(i).AnimalName){
                return true;
            }
        }
        return false;
    }

}
