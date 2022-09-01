package project.users;

/* Клас сістемних адміністраторів */

public class SysAdmin {
    private int id;
    private String surname;
    private String name;

    public SysAdmin(User user) {
        this.id = user.getId();
        this.surname = user.getSurname();
        this.name = user.getName();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "SysAdmin "+ surname+" "+ name;
    }

}
