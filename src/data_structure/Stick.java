package data_structure;

public class Stick implements Comparable {

    private boolean state;
    private int x,y,x2,y2;
    private int player;

    public Stick(int x,int y,int x2,int y2){
        this.x=x;
        this.y=y;
        this.x2=x2;
        this.y2=y2;
        this.state = false;
        this.player = -1;
    }

    public boolean is_click_inside(int x_click, int y_click){
        return (x_click<=x2 && x_click>=x && y_click<=y2 && y_click>=y);
    }

    public void lock(){
        state = true;
    }

    @Override
    public String toString() {
        return "Stick : {x="+x+",y="+y+",x2="+x2+",y2="+y2+"}======"+state+"====== by "+player;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getX2() {
        return x2;
    }

    public int getY2() {
        return y2;
    }

    public boolean getState() {return state;}

    public int getPlayer() {return player;}

    public void setPlayer(int p) {
        if(!state) if (player != -1) player = -1; else player=p;
    }


    @Override
    public int compareTo(Object o) {
        Stick stick = (Stick)o;
        if (stick.getX() > this.getX()) return -1;
        if (stick.getX() < this.getX()) return 1;
        return 0;
    }
}
