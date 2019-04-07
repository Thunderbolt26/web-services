package client;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class FootballClub {
    private int id;
    private String name;
    private String country;
    private String city;
    private int age;


    public FootballClub() {
    }

    public FootballClub(int id, String name, String country, String city, int age) {
        this.id = id;
        this.name = name;
        this.country = country;
        this.city = city;
        this.age = age;
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

    public int getAge() {
        return age;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Override
    public String toString() {
        return "id=" + id + ", name=" + name + ", country=" + country + ", city=" + city + ", age=" + age;
    }
}