package project.models.appointments;
/* Клас медичних призначень */
public class Appointment {
    private int id;
    private String name;

    public Appointment(String name) {
        this.name = name;
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

    @Override
    public String toString() {
        return "Appointment" + name ;
    }
}
