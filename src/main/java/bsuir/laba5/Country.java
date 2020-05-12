package bsuir.laba5;


public class Country {
    private int id;
    private String name;
    private String capital;
    private int population;
    private double area;
    private String govermentForm;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Country{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", capital='" + capital + '\'' +
                ", population=" + population +
                ", area=" + area +
                ", govermentForm='" + govermentForm + '\'' +
                '}';
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

    public double getArea() {
        return area;
    }

    public void setArea(double area) {
        this.area = area;
    }

    public String getGovermentForm() {
        return govermentForm;
    }

    public void setGovermentForm(String govermentForm) {
        this.govermentForm = govermentForm;
    }

    public Country(int id, String name, String capital, int population, double area, String govermentForm) {
        this.id = id;
        this.name = name;
        this.capital = capital;
        this.population = population;
        this.area = area;
        this.govermentForm = govermentForm;
    }

    public Country() {
    }
}