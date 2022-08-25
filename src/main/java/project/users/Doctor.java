package project.users;

public class Doctor {
    private int id;
    private String surname;
    private String name;
    private int numberOfPatients;
    private int categoryId;
    private String category;


    public Doctor(User user) {
        this.id = user.getId();
        this.surname = user.getSurname();
        this.name = user.getName();
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
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

    public int getNumberOfPatients() {
        return numberOfPatients;
    }

    public void setNumberOfPatients(int numberOfPatients) {
        this.numberOfPatients = numberOfPatients;
    }

    @Override
    public String toString() {
        return "Doctor "+surname+" " + name + " " + category + " " + getCategoryId() + " " + numberOfPatients;
    }
}
