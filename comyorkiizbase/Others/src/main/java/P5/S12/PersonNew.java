package P5.S12;

public class PersonNew <T> {

    private T t;

    public T getT() {
        return t;
    }

    public void setT(T t) {
        this.t = t;
    }

    public PersonNew(T t) {
        this.t = t;
    }
}
