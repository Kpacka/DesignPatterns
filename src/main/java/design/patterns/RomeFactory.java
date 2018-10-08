package design.patterns;

public class RomeFactory implements SnowboardFactory {
    @Override
    public Snowboard createSnowboard() {
        return new RomeSnowboard();
    }
}
