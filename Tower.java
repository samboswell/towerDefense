public class Tower {
    int[] range; // [minX, maxX, minY, maxY] 
    int damage;
    int cost;
    int speed;
    int xCoordinate;
    int yCoordinate;
    public Tower(int[] range, int damage, int cost, int speed, int xCoordinate,
                 int yCoordinate) {
        this.range = range;
        this.damage = damage;
        this.cost = cost;
        this.speed = speed;
        this.xCoordinate = xCoordinate;
        this.yCoordinate = yCoordinate;
    }
    public int[] getRange() {
        return this.range;
    }
    public int getDamage() {
        return this.damage;
    }
    public int getCost() {
        return this.cost;
    }
    public int getSpeed() {
        return this.speed;
    }
    public int getX() {
        return this.xCoordinate;
    }
    public int getY() {
        return this.yCoordinate;
    }
    public String getStats() {
        //this is probably incomplete ######################################
        int coveredRange = range[1]*range[3] - range[0]*range[2];
        String stats = "Covered range: "+coveredRange;
        stats += "Damage: " + this.damage;
        stats += "Attack Speed: " + this.speed;
        return stats;
    }
    
    
    // just for testing ###############################################
    public static void main(String[] args) {
        int[] a = {1,2,1,2};
        Tower t = new Tower(a,5,10,5,0,0);
        System.out.println(t.getCost());
    }
}