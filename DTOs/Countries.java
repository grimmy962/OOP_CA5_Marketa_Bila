package DTOs;

public class Countries {
    int id;
    String name;
    String capital;
    int population;
    String religion;
    double area;

    public Countries() {
    }

    public Countries(int id, String name, String capital, int population, String religion, double area) {
        this.id = id;
        this.name = name;
        this.capital = capital;
        this.population = population;
        this.religion = religion;
        this.area = area;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCapital() {
        return capital;
    }

    public void setCapital(String capital) {
        this.capital = capital;
    }

    public int getPopulation() {
        return population;
    }

    public void setPopulation(int population) {
        this.population = population;
    }

    public String getReligion() {
        return religion;
    }

    public void setReligion(String religion) {
        this.religion = religion;
    }

    public double getArea() {
        return area;
    }

    public void setArea(double area) {
        this.area = area;
    }

    @Override
    public String toString() {
        return "DTOs.Countries{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", capital='" + capital + '\'' +
                ", population=" + population +
                ", religion='" + religion + '\'' +
                ", area=" + area +
                '}';
    }
}
