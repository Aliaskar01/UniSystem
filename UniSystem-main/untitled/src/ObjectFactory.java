import java.io.Serializable;

public abstract class ObjectFactory implements Serializable {
    public ObjectFactory() {}
    public abstract void create();
}
