package entitites;

public class BookEntity {
    private Integer id;
    private String title;
    private Double price;

    public BookEntity() {
    }

    public BookEntity(Integer id, String title, Double price) {
        this.id = id;
        this.title = title;
        this.price = price;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "BookEntity{" + "id=" + id + ", title='" + title + '\'' + ", price=" + price + '}';
    }
}
