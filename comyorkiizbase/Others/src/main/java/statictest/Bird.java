package statictest;

/**
 * @auther:
 * @date:
 * @describtion:
 **/

public class Bird implements Cloneable{

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Bird bird = (Bird) o;

        return name != null ? name.equals(bird.name) : bird.name == null;
    }

    @Override
    public int hashCode() {
        return name != null ? name.hashCode() : 0;
    }


    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
