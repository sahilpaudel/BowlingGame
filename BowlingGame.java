import java.util.*;

class Player {
    
    private int id;
    private String name;
    private List<Shot> points;
    
    public Player(int id, String name) {
        this.id = id;
        this.name = name;
        points = new ArrayList<Shot>(Collections.nCopies(10, null));
    }
    
    public String getName() {
        return name;
    }
    
    public int getId() {
        return id;
    }
    
    public void saveShot(Shot s, int i) throws Exception {
        if (i > 10) {
            throw new Exception("Invalid Shot");
        }
        
        points.set(i, s);
    }
    
    public List<Shot> getPoints() {
        return points;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public void setId(int id) {
        this.id = id;
    }
}

class Game {
    
    private int laneNumber;
    private List<Player> players;
    
    public Game(int laneNumber, List<Player> players) {
        this.laneNumber = laneNumber;
        this.players = players;
    }
    
    private int getScore() {
        Random rand = new Random();
        return rand.nextInt(11);
    }
    
    public void play(int game) throws Exception {
        int n = players.size();
        
        for (int i = 0; i < n; i++) {
            
            Player p = players.get(i);
            
            int first = getScore();
            int second = getScore();
            
            Shot s = new Shot(first, second);
            int sum = first + second;
            
            int oldSum = 0;
            
            if (game > 0)
                oldSum = p.getPoints().get(game - 1).getSum();
            s.setSum(sum + oldSum);
            
            p.saveShot(s, game);
        }
    }
    
    public int getLaneNumber() {
        return laneNumber;
    }
    
    public List<Player> getPlayers() {
        return players;
    }
    
    public void setLaneNumber(int laneNumber) {
        this.laneNumber = laneNumber;
    }
    
    public void setPlayers(List<Player> players) {
        this.players = players;
    }
}

class Shot {
    private int first;
    private int second;
    private int sum;
    
    public Shot(int first, int second) {
        this.first = first;
        this.second = second;
    }
    
    public int getFirst() {
        return first;
    }
    
    private int getSecond() {
        return second;
    }
    
    public void setFirst(int first) {
        this.first = first;
    }
    
    public void setSecond(int second) {
        this.second = second;
    }
    
    public int getSum(){
        return sum;
    }
    
    public void setSum(int sum){
        this.sum = sum;
    }
    
    public String toString() {
        return "{"+first+","+second+"}";
    }
}


public class BowlingGame {
    public static void main(String args[]) throws Exception {
        Player p1 = new Player(1, "PlayerOne");
        Player p2 = new Player(2, "PlayerTwo");
        Player p3 = new Player(3, "PlayerThree");
        Player p4 = new Player(4, "PlayerFour");
        
        List<Player> players = new ArrayList();
        players.add(p1);
        players.add(p2);
        players.add(p3);
        players.add(p4);
        
        Game game = new Game(1, players);
        for (int i = 0; i < 10; i++)
            game.play(i);
        
        players = game.getPlayers();
        
        int highest = 0;
        String winner = "";
        
        for (Player p: players) {
            System.out.println("Name: " + p.getName());
            System.out.println("Points: " + p.getPoints());
            
            int lastPoint = p.getPoints().get(9).getSum();
            
            if (lastPoint > highest) {
                highest = lastPoint;
                winner = p.getName();
            }
        }
        System.out.println("\nWinner: " + winner + " Points: " + highest);
        
    }
}