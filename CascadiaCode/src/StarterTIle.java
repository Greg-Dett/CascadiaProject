import java.io.IOException;

public class StarterTIle {
    BoardTile up;
    BoardTile left;
    BoardTile right;
    public StarterTIle(int startNumber) throws IOException {
        switch (startNumber){
            case 0 ->{
                Habitat uphab=new Habitat(Casscadia.Habitatselect.wetland, Casscadia.Habitatselect.none);
                Habitat lefthab=new Habitat(Casscadia.Habitatselect.forest,Casscadia.Habitatselect.river);
                Habitat righthab=new Habitat(Casscadia.Habitatselect.mountain,Casscadia.Habitatselect.prairie);
                this.up=new BoardTile(uphab,3,3,0);
                this.left=new BoardTile(lefthab,3,4,0);
                this.right=new BoardTile(righthab,4,4,0);
            }
            case 1 ->{
                Habitat uphab=new Habitat(Casscadia.Habitatselect.mountain,Casscadia.Habitatselect.none);
                Habitat lefthab=new Habitat(Casscadia.Habitatselect.forest,Casscadia.Habitatselect.wetland);
                Habitat righthab=new Habitat(Casscadia.Habitatselect.prairie,Casscadia.Habitatselect.river);
                this.up=new BoardTile(uphab,3,3,0);
                this.left=new BoardTile(lefthab,3,4,0);
                this.right=new BoardTile(righthab,4,4,0);
            }
            case 2 ->{
                Habitat uphab=new Habitat(Casscadia.Habitatselect.forest,Casscadia.Habitatselect.none);
                Habitat lefthab=new Habitat(Casscadia.Habitatselect.mountain,Casscadia.Habitatselect.river);
                Habitat righthab=new Habitat(Casscadia.Habitatselect.wetland,Casscadia.Habitatselect.prairie);
                this.up=new BoardTile(uphab,3,3,0);
                this.left=new BoardTile(lefthab,3,4,0);
                this.right=new BoardTile(righthab,4,4,0);
            }
            case 3 ->{
                Habitat uphab=new Habitat(Casscadia.Habitatselect.river,Casscadia.Habitatselect.none);
                Habitat lefthab=new Habitat(Casscadia.Habitatselect.prairie,Casscadia.Habitatselect.forest);
                Habitat righthab=new Habitat(Casscadia.Habitatselect.mountain,Casscadia.Habitatselect.wetland);
                this.up=new BoardTile(uphab,3,3,0);
                this.left=new BoardTile(lefthab,3,4,0);
                this.right=new BoardTile(righthab,4,4,0);
            }case 4 ->{
                Habitat uphab=new Habitat(Casscadia.Habitatselect.prairie,Casscadia.Habitatselect.none);
                Habitat lefthab=new Habitat(Casscadia.Habitatselect.river,Casscadia.Habitatselect.wetland);
                Habitat righthab=new Habitat(Casscadia.Habitatselect.forest,Casscadia.Habitatselect.mountain);
                this.up=new BoardTile(uphab,3,3,0);
                this.left=new BoardTile(lefthab,3,4,0);
                this.right=new BoardTile(righthab,4,4,0);
            }


        }

    }
}
