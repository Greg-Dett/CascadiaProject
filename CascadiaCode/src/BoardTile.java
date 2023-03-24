import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class BoardTile {
    int xPosition;
    int yPosition;
    int X;
    int Y;
    Habitat habitat;
    boolean isAnimal=false;
    Animal animal;
    int numPossibleAnimals;
    public BoardTile(Habitat newHabitat,int x,int y) throws IOException {
        this.habitat=newHabitat;
        if (y%2!=0) {
            this.xPosition = x * 64+32;
        }
        else {
            this.xPosition=x*64;
        }
        this.yPosition=y*64;
        this.X=x;
        this.Y=y;

    }
    enum direction{
        topleft,
        topright,
        bottomleft,
        bottomright
    }


    public void addAnimal(Animal newAnimal){
        this.animal=newAnimal;
    }


}
