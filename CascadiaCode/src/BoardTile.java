/*
 *  Student number 21400044 -        Thomas Reynolds  -         gitID Reynolds21
 *  Student number 21305806 -        Greg Dettling    -         gitID Greg-Dett
 *  Student number 21463472 -        Ganto Badammoyun -         gitID Gantobadan
 * */

import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class BoardTile {
    int xPosition;  //pixel x coordinate of tile
    int yPosition; // pixel y coordinate of tile
    int X; //board x coordinate of tile
    int Y; //board y coordiante of tile
    Habitat habitat; // habitat on the tile
    boolean isAnimal=false; //boolean whether an animal os on the tile or not
    Animal animal;// animal on the tile

    public BoardTile(Habitat newHabitat,int x,int y,int rotation) throws IOException {
        this.habitat=newHabitat;
        habitat.rotateHabitat(rotation);
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


    public void removeAnimal()
    {
        animal = null;
        isAnimal = false;
    }


    public void addAnimal(Animal newAnimal){//places an animal on the tile
        this.animal=newAnimal;
    }
   ArrayList<BoardTile> surroundingTiles;

}
