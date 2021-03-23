package sample.models;

/**
 * TheSusanin
 * 3/16/2021
 */
public class Department {
    private int id;
    private String name;

    public Department(int id, String name) {
        setId(id);
        setName(name);
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
        if (name.isEmpty() || name.length() < 5)
            throw new RuntimeException("Неверное значение для названия!");
        this.name = name;
    }
}
