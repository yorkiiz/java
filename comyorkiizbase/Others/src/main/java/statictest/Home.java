package statictest;

/**
 * @auther:
 * @date:
 * @describtion:
 **/

public class Home {

    private String adress;

    public Home(String adress) {
        this.adress = adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Home home = (Home) o;

        return adress != null ? adress.equals(home.adress) : home.adress == null;
    }

    @Override
    public int hashCode() {
        return adress != null ? adress.hashCode() : 0;
    }

    public String getAdress() {
        return adress;
    }
}
