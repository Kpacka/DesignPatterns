package design.patterns.creational.factorydesign;

public class App {
    public static void main(String[] args){
        SnowboardFactory factory = new RomeFactory();
        Snowboard snowboard = factory.createSnowboard();
        snowboard.printSnowboard();
    }
}
