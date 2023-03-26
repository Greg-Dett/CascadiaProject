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


    public ArrayList<BoardTile> checkPair(BoardTile tileToCheck) {
        int X=tileToCheck.X;
        int Y=tileToCheck.Y;
        ArrayList<BoardTile> occuredTiles=new ArrayList<BoardTile>();
        int originalX = X;
        int originalY = Y;
        if (Board[X][Y]==null||Board[X][Y].animal==null){
            return null;
        }
        if (Y % 2 == 0) {
            Y = Y - 1;
            X = X - 1;
            for (int i = 0; i < 3; i++) {
                Y += i;
                for (int l = 0; l < 2; l++) {
                    X += l;
                    if (X < 0 || X > 8 || Y < 0 || Y > 8) {}
                    else if (Board[X][Y]==null||Board[X][Y].animal==null){}
                    else if (X == originalX && Y == originalY) {
                        if (Board[X + 1][Y]==null||Board[X+1][Y].animal==null){}
                        else if (Board[originalX][originalY].animal.AnimalName == Board[X + 1][Y].animal.AnimalName) {
                            occuredTiles.add(findTile(X+1,Y));
                        }
                    } else if (Board[originalX][originalY].animal.AnimalName == Board[X][Y].animal.AnimalName) {
                        occuredTiles.add(findTile(X,Y));
                    }
                    X=originalX-1;
                }
                Y=originalY-1;
            }

        } else {
            Y = Y - 1;
            for (int i = 0; i < 3; i++) {
                Y += i;
                for (int l = 0; l < 2; l++) {
                    X += l;
                    if (X < 0 || X > 8 || Y < 0 || Y > 8) {
                    }
                    else if (Board[X][Y]==null||Board[X][Y].animal==null) {}
                    else if (X == originalX && Y == originalY) {
                        if (Board[X - 1][Y]==null||Board[X-1][Y].animal==null){}
                        else if (Board[originalX][originalY].animal.AnimalName == Board[X - 1][Y].animal.AnimalName) {
                            occuredTiles.add(findTile(X-1,Y));
                        }
                    } else if (Board[originalX][originalY].animal.AnimalName == Board[X][Y].animal.AnimalName) {
                        occuredTiles.add(findTile(X,Y));
                    }
                    X=originalX;
                }
                Y=originalY-1;
            }
        }
        return occuredTiles;
    }
    public BoardTile findTile(int x,int y){
        for (int i=0;i<allTiles.size();i++){
            if (allTiles.get(i).X==x&&allTiles.get(i).Y==y){
                return allTiles.get(i);
            }
        }
        return null;
    }

}
