public class Car {
    private String mark;
    private int age;
    private double price;

    public Car() {
    }

    public Car(String mark, int age, double price) {
        this.mark = mark;
        this.age = age;
        this.price = price;
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
