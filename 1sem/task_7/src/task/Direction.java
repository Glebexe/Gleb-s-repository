package task;

public enum Direction {
    North(0,-1),
    West(-1,0),
    South(0,1),
    East(1,0);

    private int xController;
    private int yController;

    Direction(int xController, int yController){
        this.xController = xController;
        this.yController = yController;
    }

    public Vector2 move(Vector2 start, double distance){
        return new Vector2(start.getX()+distance*xController,start.getY()+distance*yController);
     }
}
