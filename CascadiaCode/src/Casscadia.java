import javax.imageio.ImageIO;
import javax.management.StringValueExp;
import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.Scanner;
import java.io.*;
import java.awt.*;
import java.awt.geom.*;


public class Casscadia implements ActionListener {


    public int NumhabitatTiles; //used ot store numeber of habitat tiles that will be used depending on number of players//

    public Casscadia() throws IOException {
    }

    static String CurrentText = "Click any of the 4 habitats above to begin";
    static String turnInfo = "hello player 1";

    @Override
    public void actionPerformed(ActionEvent e) {

    }


    enum Habitatselect {forest, wetland, river, mountain, prairie,none} // used when implementing habitat class//

    enum Animalselect {Hawk, Bear, Elk, Salmon, Fox, none} // used when implementing animal class//

    ArrayList<Player> Players = new ArrayList<Player>();
    ArrayList<Animal> AnimalCards = new ArrayList<Animal>();
    ArrayList<Habitat> HabitatCards = new ArrayList<Habitat>();
    ArrayList<BoardTile> allTiles = new ArrayList<BoardTile>();
    Habitat currentHabitat = null;
    Animal currentAnimal = null;
    boolean useWildlifeToken=false;
    boolean canReshuffle = false;

    int turnCount = 0;
    int currentRotation=0;
    int PlayerTurn = 0;
    int turns = 1;
    int NumPLayers;
    static int animalIndex;

    public static void main(String[] args) throws IOException {
        Casscadia hello = new Casscadia();
        hello.Initialprompt();
        hello.Turn();

        JFrame frame = new JFrame();
        frame.setBounds(10, 10, 512, 650);
        frame.setUndecorated(true);
        frame.setLocationRelativeTo(null);
        JPanel pn = new JPanel() {


            @Override
            public void paint(Graphics g) {
                //draw square grid/board
                for (int y = 0; y < 8; y++) {
                    for (int x = 0; x < 8; x++) {

                        if (y % 2 != 0) {
                            g.drawRect(x * 64 + 32, y * 64, 64, 64);
                        } else {
                            g.drawRect(x * 64, y * 64, 64, 64);
                        }

                    }
                }
                if (hello.turns>40){
                    return;
                }



                //Current Selection
                for (int i = 0; i < 4; i++) {
                    g.drawImage(hello.AnimalCards.get(i).animalImage, 154 + (64 * i), 576, this);
                    g.drawImage(hello.HabitatCards.get(i).habitatImage, 154 + (64 * i), 518, this);
                    for (int l = 0; l < hello.HabitatCards.get(i).numPossibleAnimals; l++) {
                        g.drawImage(hello.HabitatCards.get(i).possibleAnimals.get(l).possibleImage, 154 + (64 * i) + (15 * l), 518, this);
                    }
                }

                for (int z = 0; z < hello.Players.get(hello.PlayerTurn).allTiles.size(); z++) {
                    g.drawImage(hello.Players.get(hello.PlayerTurn).allTiles.get(z).habitat.habitatImage, hello.Players.get(hello.PlayerTurn).allTiles.get(z).xPosition, hello.Players.get(hello.PlayerTurn).allTiles.get(z).yPosition, this);
                    if (hello.Players.get(hello.PlayerTurn).allTiles.get(z).isAnimal == true) {
                        g.drawImage(hello.Players.get(hello.PlayerTurn).allTiles.get(z).animal.animalImage, hello.Players.get(hello.PlayerTurn).allTiles.get(z).xPosition+16, hello.Players.get(hello.PlayerTurn).allTiles.get(z).yPosition+16, this);
                    } else {
                        for (int l = 0; l < hello.Players.get(hello.PlayerTurn).allTiles.get(z).habitat.numPossibleAnimals; l++) {
                            g.drawImage(hello.Players.get(hello.PlayerTurn).allTiles.get(z).habitat.possibleAnimals.get(l).possibleImage, hello.Players.get(hello.PlayerTurn).allTiles.get(z).xPosition + (l * 15), hello.Players.get(hello.PlayerTurn).allTiles.get(z).yPosition , this);
                        }
                    }
                }

                hello.ReshuffleCheck();
                JButton yes = new JButton("Reshuffle");
                yes.addActionListener(new ActionListener() {

                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (Boolean.compare(hello.canReshuffle, true) == 0) {
                            Collections.shuffle(hello.AnimalCards);
                            frame.repaint();
                        }
                    }
                });
                yes.setBounds(0, 512, 100, 50);
                frame.add(yes);

                    JButton wildlife_token = new JButton("Wildlife Token");
                    wildlife_token.addActionListener(new ActionListener() {

                        @Override
                        public void actionPerformed(ActionEvent e) {
                            if (hello.Players.get(hello.PlayerTurn).wildlifeTokens>0) {
                                hello.useWildlifeToken = true;
                                hello.currentAnimal = null;
                                hello.CurrentText="Please choose an animal";
                                frame.repaint();
                            }
                        }
                    });
                    wildlife_token.setBounds(0, 562, 100, 50);
                    frame.add(wildlife_token);

                JButton rotate = new JButton("Rotate");
                rotate.addActionListener(new ActionListener() {

                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (hello.currentHabitat!=null) {
                            hello.plusRotation();
                            try {
                                hello.currentHabitat.rotateHabitat(hello.currentRotation);
                            } catch (IOException ex) {
                                throw new RuntimeException(ex);
                            }
                            frame.repaint();
                        }
                    }
                });
                rotate.setBounds(410, 562, 100, 50);
                frame.add(rotate);

                JTextField textbox = new JTextField();
                textbox.setSize(new Dimension(512, 38));
                textbox.setBounds(0, 612, 512, 38);
                textbox.setText(CurrentText);

                add(textbox);
                JTextField turnInfoBox = new JTextField();
                turnInfoBox.setSize(new Dimension(102, 50));
                turnInfoBox.setBounds(410, 512, 102, 50);
                turnInfoBox.setText(turnInfo);

                add(turnInfoBox);

                JTextField numWildlifeTokens = new JTextField();
                numWildlifeTokens.setSize(new Dimension(50, 50));
                numWildlifeTokens.setBounds(100, 562, 50, 50);
                numWildlifeTokens.setText(String.valueOf(hello.Players.get(hello.PlayerTurn).wildlifeTokens));

                add(numWildlifeTokens);
                //wildlife token button
                JTextField CanReshuffle = new JTextField();
                CanReshuffle.setSize(new Dimension(50, 50));
                CanReshuffle.setBounds(100, 512, 50, 50);
                if (hello.canReshuffle==true){
                CanReshuffle.setText("Yes");
                }
                else {CanReshuffle.setText("No");}
                add(CanReshuffle);
            }
        };

        frame.add(pn);
        frame.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

            }

            @Override
            public void mousePressed(MouseEvent e) {
                int x;
                int y = Math.floorDiv((e.getY()), 64);
                if (e.getX()<32){
                    x=9;
                }
                else if (y%2!=0){
                    x = Math.floorDiv(e.getX()-32, 64);
                }
                else{x = Math.floorDiv(e.getX(), 64);}

              if (hello.currentHabitat != null && x < 8 && y < 8 && hello.Players.get(hello.PlayerTurn).Board[x][y] == null && hello.Players.get(hello.PlayerTurn).canPlace(x,y)) {
                    BoardTile newtile = null;
                    try {
                        newtile = new BoardTile(hello.currentHabitat, x, y,hello.currentRotation);
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                    hello.Players.get(hello.PlayerTurn).Board[x][y] = newtile;
                    hello.Players.get(hello.PlayerTurn).allTiles.add(newtile);
                    hello.currentAnimal = hello.AnimalCards.get(animalIndex);
                    frame.repaint();
                    hello.currentHabitat = null;
                    hello.currentRotation=0;
                    hello.CurrentText = ("Please place " + hello.currentAnimal.AnimalName+" or use a wildlife token");
                } else if (hello.currentAnimal != null && x < 8 && y < 8 && hello.Players.get(hello.PlayerTurn).Board[x][y] != null && hello.Players.get(hello.PlayerTurn).Board[x][y].isAnimal == false) {
                    if (hello.Players.get(hello.PlayerTurn).Board[x][y].habitat.isPossible((hello.currentAnimal)) == true) {
                        hello.Players.get(hello.PlayerTurn).Board[x][y].addAnimal(hello.currentAnimal);
                        for (int z = 0; z < hello.Players.get(hello.PlayerTurn).allTiles.size(); z++) {
                            if (hello.Players.get(hello.PlayerTurn).allTiles.get(z).X == x && hello.Players.get(hello.PlayerTurn).allTiles.get(z).Y == y) {
                                hello.Players.get(hello.PlayerTurn).allTiles.get(z).addAnimal(hello.currentAnimal);
                                hello.Players.get(hello.PlayerTurn).allTiles.get(z).isAnimal = true;
                            }
                        }
                        if (hello.Players.get(hello.PlayerTurn).Board[x][y].habitat.habitat2==Habitatselect.none){
                            hello.Players.get(hello.PlayerTurn).wildlifeTokens++;
                        }
                        hello.HabitatCards.remove(animalIndex);
                        hello.AnimalCards.remove(animalIndex);
                        frame.repaint();
                        hello.currentAnimal = null;
                        hello.plusPlayerTurn();

                    } else {
                        hello.CurrentText=("You can't Place "+ hello.currentAnimal.AnimalName+ " here");
                        frame.repaint();
                    }

                } else if (hello.currentAnimal == null && hello.useWildlifeToken==false) {
                    if (e.getX() > 154 && e.getX() < 216 && e.getY() > 518 && e.getY() < 580) {
                        hello.currentHabitat = hello.HabitatCards.get(0);
                        animalIndex = 0;
                        hello.CurrentText = (hello.currentHabitat.getHabitatName() + " is selected");

                        frame.repaint();
                    } else if (e.getX() > 218 && e.getX() < 280 && e.getY() > 518 && e.getY() < 580) {
                        hello.currentHabitat = hello.HabitatCards.get(1);
                        animalIndex = 1;
                        hello.CurrentText = (hello.currentHabitat.getHabitatName() + " is selected");
                        frame.repaint();
                    } else if (e.getX() > 282 && e.getX() < 348 && e.getY() > 518 && e.getY() < 580) {
                        hello.currentHabitat = hello.HabitatCards.get(2);
                        animalIndex = 2;
                        hello.CurrentText = (hello.currentHabitat.getHabitatName() + " is selected");
                        frame.repaint();
                    } else if (e.getX() > 350 && e.getX() < 412 && e.getY() > 518 && e.getY() < 580) {
                        hello.currentHabitat = hello.HabitatCards.get(3);
                        animalIndex = 3;
                        hello.CurrentText = (hello.currentHabitat.getHabitatName() + " is selected");
                        frame.repaint();
                    }
                }
                else if (hello.currentAnimal == null && hello.useWildlifeToken==true) {
                    if (e.getX() > 154 && e.getX() < 186 && e.getY() > 576 && e.getY() < 608) {
                        hello.currentAnimal = hello.AnimalCards.get(0);
                        animalIndex = 0;
                        hello.CurrentText = (hello.currentAnimal.getAnimalName() + " is selected");
                        //if current habitat is not keystone
                        //button
                        //button rotates image 90 degrees
                        frame.repaint();
                    } else if (e.getX() > 218 && e.getX() < 250 && e.getY() > 576 && e.getY() < 608) {
                        hello.currentAnimal = hello.AnimalCards.get(1);
                        animalIndex = 1;
                        hello.CurrentText = (hello.currentAnimal.getAnimalName() + " is selected");
                        frame.repaint();
                    } else if (e.getX() > 282 && e.getX() < 304 && e.getY() > 576 && e.getY() < 608) {
                        hello.currentAnimal = hello.AnimalCards.get(2);
                        animalIndex = 2;
                        hello.CurrentText = (hello.currentAnimal.getAnimalName() + " is selected");
                        frame.repaint();
                    } else if (e.getX() > 350 && e.getX() < 382 && e.getY() > 576 && e.getY() < 608) {
                        hello.currentAnimal = hello.AnimalCards.get(3);
                        animalIndex = 3;
                        hello.CurrentText = (hello.currentAnimal.getAnimalName() + " is selected");
                        frame.repaint();
                    }
                    hello.useWildlifeToken=false;
                    hello.Players.get(hello.PlayerTurn).wildlifeTokens--;
                }


            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });
        frame.setVisible(true);

    }


    public void Initialprompt() throws IOException {   //Game Set-up
        Scanner NumPlayersScanner = new Scanner(System.in);  // Create a Scanner object
        System.out.println("Enter number of players (2-4): ");
//        int NumPLayers = NumPlayersScanner.nextInt();  // asks user to input number of players
        NumPLayers = 2;
        if (NumPLayers < 2 || NumPLayers > 4) {
            throw new IllegalArgumentException("Incorrect number of users");
        }
        switch (NumPLayers) {//stores number of habitat tiles neccessary, have not implemented use of this variable yet. SEE LINE 89//
            case 2:
                NumhabitatTiles = 43;
            case 3:
                NumhabitatTiles = 63;
            case 4:
                NumhabitatTiles = 83;
        }

        CreateTiles();

//        NumPlayersScanner.nextLine();
        for (int i = 0; i < NumPLayers; i++) {
            Scanner PlayerNameScanner = new Scanner(System.in);  // Create a Scanner object
            System.out.println("Enter the name for player " + (i + 1) + ":");//User inputs Name
//            String userName = PlayerNameScanner.nextLine();
            String userName = ("greg" + i);
            Players.add(new Player(userName));
            System.out.println("Player " + (i + 1) + "'s name is: " + userName + "\n");  // Output user input
        }

        Collections.shuffle(Players);
        System.out.println("Order of Players:");
        for (int i = 0; i < NumPLayers; i++) {
            System.out.println(Players.get(i).getName());//Shows order of PLayers
        }

    }

    public void CreateTiles() throws IOException {          //Creates Array lists fo animal and habitat Tiles needed to play Casscadia
        for (int i = 0; i < 20; i++) {
            AnimalCards.add(new Animal(Animalselect.Hawk));
            AnimalCards.add(new Animal(Animalselect.Bear));
            AnimalCards.add(new Animal(Animalselect.Elk));
            AnimalCards.add(new Animal(Animalselect.Salmon));
            AnimalCards.add(new Animal(Animalselect.Fox));
        }
        for (int l = 0; l < 5; l++) {//keystone tiles//
            HabitatCards.add(new Habitat(Habitatselect.forest, Habitatselect.none));
            HabitatCards.add(new Habitat(Habitatselect.wetland, Habitatselect.none));
            HabitatCards.add(new Habitat(Habitatselect.river, Habitatselect.none));
            HabitatCards.add(new Habitat(Habitatselect.mountain, Habitatselect.none));
            HabitatCards.add(new Habitat(Habitatselect.prairie, Habitatselect.none));
        }
        int m=0;
        while (m<NumhabitatTiles-25){
            Random rand = new Random();
            int selectingHabitat1 = rand.nextInt(4);
            int selectingHabitat2 = rand.nextInt(4);
            if (selectingHabitat1!=selectingHabitat2){
            Habitat newpick = new Habitat(Casscadia.Habitatselect.values()[selectingHabitat1],Casscadia.Habitatselect.values()[selectingHabitat2]);
            HabitatCards.add(newpick);
            m++;
            }
        }
        //need to add habitat tiles with more than one habitat on them//

    }

    public void Turn() {
        Collections.shuffle(HabitatCards);
        int animalReshuffleCheck = 0;
        Collections.shuffle(AnimalCards);
        for (int l = 0; l < 4; l++) {
            animalReshuffleCheck = 0;
            for (int i = 0; i < 2; i++) {
                if (AnimalCards.get(l).getAnimalName().equals(AnimalCards.get(i).AnimalName)) {
                    animalReshuffleCheck++;     //checks how many of each animal appear each turn
                }

            }
            if (animalReshuffleCheck == 4 || animalReshuffleCheck == 3) {
                canReshuffle = true;
            }
        }

    }

    public void ReshuffleCheck() {
        int animalReshuffleCheck = 0;
        for (int l = 0; l < 2; l++) {
            animalReshuffleCheck = 0;
            for (int i = 0; i < 4; i++) {
                if (AnimalCards.get(l).getAnimalName().equals(AnimalCards.get(i).AnimalName)) {
                    animalReshuffleCheck++;     //checks how many of each animal appear each turn
                }

            }
            if (animalReshuffleCheck == 4 || animalReshuffleCheck == 3) {
                canReshuffle = true;
            }
            else {
                canReshuffle=false;
            }
        }

    }

    public void plusPlayerTurn() {
        if (this.PlayerTurn < NumPLayers - 1) {
            this.PlayerTurn++;
        } else {
            PlayerTurn = 0;
        }

        this.turnInfo = (this.Players.get(this.PlayerTurn).getName() + "'s turn "+turns);
        Collections.shuffle(this.HabitatCards);
        Collections.shuffle(this.AnimalCards);
        this.CurrentText="Select a Habitat and Animal or place a habitat and use a wildlife token to choose an animal";
        turns++;
    }
    public void plusRotation() {
        if (this.currentRotation < 3) {
            this.currentRotation++;
        } else {
            currentRotation = 0;
        }

    }}
