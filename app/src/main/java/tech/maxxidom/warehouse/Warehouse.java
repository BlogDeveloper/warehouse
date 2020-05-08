package tech.maxxidom.warehouse;

import java.util.ArrayList;

public class Warehouse {

    private String RoomNumber;
    private ArrayList<Article> articles = new ArrayList<>();

    public Warehouse(String roomNumber) {
        setRoomNumber(roomNumber);
    }

    public String getRoomNumber() {
        return RoomNumber;
    }

    public void setRoomNumber(String roomNumber) {
        RoomNumber = roomNumber;
    }

    public ArrayList<Article> getArticles() {
        return articles;
    }

    public void setArticle(String articleName, int articleQuantity) {
        articles.add(new Article(articleName, articleQuantity));
    }
}
