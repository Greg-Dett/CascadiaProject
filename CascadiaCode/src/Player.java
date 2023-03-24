import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class Player {

    public Player(String Name) throws IOException {
        this.Name=Name;
        Random rand = new Random();
        this.startNum = rand.nextInt(4);
        allTiles.add(startingTile.up);
        allTiles.add(startingTile.left);
        allTiles.add(startingTile.right);
        Board[3][3]=startingTile.up;
        Board[3][4]=startingTile.left;
        Board[4][4]=startingTile.right;

    }
    String Name;
    int startNum;//number to choose which starter tile you get
    StarterTIle startingTile=new StarterTIle(startNum);// players starting tile
    BoardTile[][] Board=new BoardTile[8][8]; //board with tiles on it
    ArrayList<BoardTile> allTiles=new ArrayList<BoardTile>(); //all tiles the user has placed
    int wildlifeTokens=1;

    public String getName(){
        return this.Name;
    }
    public boolean canPlace(int X,int Y){ //multiple if statements that check surrounding tiles of a tile to see if it can be placed
        if (X==7&&Y==7) {
            if (Board[X - 1][Y] == null && Board[X][Y - 1] == null) {
                return false;
            }
            else {
                return true;
            }
        }
        else if (X==0&&Y==7) {
            if (Board[X + 1][Y] == null && Board[X][Y - 1] == null&& Board[X+1][Y - 1] == null) {
                return false;
            }
            else {
                return true;
            }
        }
        else if (X==0&&Y==0) {
            if (Board[X + 1][Y] == null && Board[X][Y + 1] == null) {
                return false;
            }
            else {
                return true;
            }
        }
        else if (X==7&&Y==0) {
            if (Board[X - 1][Y] == null && Board[X][Y + 1] == null && Board[X+-1][Y + 1] == null) {
                return false;
            }
            else {
                return true;
            }
        }
        else if (Y==0) {
            if (Board[X - 1][Y] == null && Board[X+1][Y] == null && Board[X][Y + 1] == null && Board[X-1][Y + 1] == null) {
                return false;
            }
            else {
                return true;
            }
        }
        else if (Y==7) {
            if (Board[X - 1][Y] == null && Board[X+1][Y] == null && Board[X][Y-1] == null && Board[X+1][Y-1] == null) {
                return false;
            }
            else {
                return true;
            }
        }
        else if (X==0) {
            if (Y % 2 == 0) {
                if (Board[X + 1][Y] == null && Board[X][Y - 1] == null && Board[X - 1][Y - 1] == null && Board[X - 1][Y + 1] == null && Board[X][Y + 1] == null) {
                    return false;
                } else {
                    return true;
                }
            }
            else {  if (Board[X + 1][Y] == null && Board[X][Y - 1] == null && Board[X][Y + 1] == null) {
                return false;
            } else {
                return true;
            }}
        }
        else if (X==7) {
            if (Y % 2 == 0) {
                if (Board[X][Y - 1] == null && Board[X - 1][Y - 1] == null && Board[X - 1][Y + 1] == null && Board[X][Y + 1] == null) {
                    return false;
                } else {
                    return true;
                }
            }
            else {  if (Board[X - 1][Y] == null && Board[X][Y - 1] == null && Board[X][Y + 1] == null) {
                return false;
            } else {
                return true;
            }}
        }
        else if (Y%2==0){
            if (Board[X-1][Y-1]==null&&Board[X-1][Y]==null&&Board[X-1][Y+1]==null&&Board[X][Y-1]==null&&Board[X][Y+1]==null){
                return false;
            }
            else {return true;}
        }
        else{
            if (Board[X-1][Y]==null&&Board[X+1][Y]==null&&Board[X][Y-1]==null&&Board[X+1][Y-1]==null&&Board[X+1][Y+1]==null&&Board[X][Y+1]==null){
                return false;
            }
            else {return true;}

        }
    }
}
