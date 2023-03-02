import javax.swing.*;
import java.applet.Applet;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import java.io.*;
import java.awt.*;
import java.awt.geom.*;


public class Casscadia{


    public static void main(String[] args) {
        Casscadia hello= new Casscadia();
        hello.Initialprompt();
       hello.Play();


            }



    public int NumhabitatTiles; //used ot store numeber of habitat tiles that will be used depending on number of players//
    enum Habitatselect{none,forest,wetland,river,mountain,prairie} // used when implementing habitat class//
    enum Animalselect{Hawk,Bear,Elk,Salmon,Fox,none} // used when implementing animal class//
    enum StartTile{Tile1,Tile2,Tile3,Tile4,Tile5};      //not in use yet
    ArrayList<Player> Players = new ArrayList<Player>();
    ArrayList<Animal> AnimalCards= new ArrayList<Animal>();
    ArrayList<Habitat> HabitatCards= new ArrayList<Habitat>();
    int NumPLayers;




    public void Initialprompt() {   //Game Set-up
        Scanner NumPlayersScanner = new Scanner(System.in);  // Create a Scanner object
        System.out.println("Enter number of players (2-4): ");
//        int NumPLayers = NumPlayersScanner.nextInt();  // asks user to input number of players
        NumPLayers=2;
        if (NumPLayers<2||NumPLayers>4){
            throw new IllegalArgumentException("Incorrect number of users");
        }
        switch (NumPLayers){//stores number of habitat tiles neccessary, have not implemented use of this variable yet. SEE LINE 89//
            case 2:
                NumhabitatTiles=43;
            case 3:
                NumhabitatTiles=63;
            case 4:
                NumhabitatTiles=83;
        }

        CreateTiles();

//        NumPlayersScanner.nextLine();
        for (int i=0;i<NumPLayers;i++){
            Scanner PlayerNameScanner = new Scanner(System.in);  // Create a Scanner object
            System.out.println("Enter the name for player "+ (i+1) +":");//User inputs Name
//            String userName = PlayerNameScanner.nextLine();
            String userName=("greg"+i);
            Players.add(new Player(userName));
            System.out.println("Player "+(i+1)+"'s name is: " + userName +"\n");  // Output user input
        }

        Collections.shuffle(Players);
        System.out.println("Order of Players:");
        for (int i=0;i<NumPLayers;i++) {
            System.out.println(Players.get(i).getName());//Shows order of PLayers
        }

    }

    public void CreateTiles(){          //Creates Array lists fo animal and habitat Tiles needed to play Casscadia
        for (int i=0;i<20;i++) {
//            AnimalCards.add(new Animal(Animalselect.Hawk));
//            AnimalCards.add(new Animal(Animalselect.Bear));
//            AnimalCards.add(new Animal(Animalselect.Elk));
//            AnimalCards.add(new Animal(Animalselect.Salmon));
            AnimalCards.add(new Animal(Animalselect.Fox));
        }
        for (int l=0;l<5;l++){//keystone tiles//
            HabitatCards.add(new Habitat(Habitatselect.forest,Habitatselect.none));
            HabitatCards.add(new Habitat(Habitatselect.wetland,Habitatselect.none));
            HabitatCards.add(new Habitat(Habitatselect.river,Habitatselect.none));
            HabitatCards.add(new Habitat(Habitatselect.mountain,Habitatselect.none));
            HabitatCards.add(new Habitat(Habitatselect.prairie,Habitatselect.none));
        }
        //need to add habitat tiles with more than one habitat on them//

    }

public void DisplayTurn(){
    Collections.shuffle(HabitatCards);
    boolean reshuffle=false;// boolean variable used when 3 or more of the same animal cards appears and you can draw 4 more//
    int YesNo=2;
    int animalReshuffleCheck=0;


    while (YesNo==2){
        Collections.shuffle(AnimalCards);
        for (int i=0;i<4;i++) {
            System.out.print(HabitatCards.get(i).HabitatName+"  ||");
        }
        System.out.println();
        for (int i=0;i<4;i++) {
            System.out.print(AnimalCards.get(i).AnimalName+"  ||"); //prints animal name
        }
        for (int l=0;l<4;l++) {
            animalReshuffleCheck=0;
            for (int i = 0; i < 4; i++) {
                if (AnimalCards.get(l).getAnimalName().equals(AnimalCards.get(i).AnimalName)) {
                    animalReshuffleCheck++;     //checks how many of each animal appear each turn
                }
            }}
                System.out.println(animalReshuffleCheck);
                if (animalReshuffleCheck == 4) {
                    System.out.println("Would you like to cull the 4 animals? Please enter 1 for yes or 2 for no");
                    Scanner reshuffleOption= new Scanner(System.in);
                    int onetwo=reshuffleOption.nextInt();
                    if (onetwo==2){
                        YesNo=1;                //sets variable that controls while loop to 1 which ends thw while loop
                    }} else if (animalReshuffleCheck == 3) {
                        System.out.println("Would you like to cull the three animals? Please enter 1 for yes or 2 for no");
                    Scanner reshuffleOption= new Scanner(System.in);
                    int onetwo=reshuffleOption.nextInt();
                    if (onetwo==2){
                        YesNo=1;
                        animalReshuffleCheck=0;
                    }
                    }
                    else { YesNo=1;}
    }

    for (int j=0;j<NumPLayers;j++) {
            System.out.println(Players.get(j).getName() + " which cards would you like to select. Please choose a number between 1 and 4");
            Scanner CardSelect = new Scanner(System.in);
            int CardSelectNumber = CardSelect.nextInt();
            Players.get(j).setCurrentAnimalCardsandCurrentHabitatCards(AnimalCards.get(CardSelectNumber - 1), HabitatCards.get(CardSelectNumber - 1));
            AnimalCards.remove(CardSelectNumber - 1);
            HabitatCards.remove(CardSelectNumber - 1);

        for (int l = 0; l < NumPLayers; l++) {

            System.out.println(Players.get(l).toString());
        }
    }
}



    public void Play(){

            DisplayTurn();

        }


    }


