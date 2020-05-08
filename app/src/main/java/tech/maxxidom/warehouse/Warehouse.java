package tech.maxxidom.warehouse;

import androidx.annotation.NonNull;

import java.io.Serializable;
import java.util.ArrayList;

public class Warehouse implements Serializable {

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

    public int quantity() {
        return articles.size();
    }

    public void setArticle(String articleName, int articleQuantity) {
        articles.add(new Article(articleName, articleQuantity));
    }

    @NonNull
    @Override
    public String toString() {
        return getRoomNumber() + " ( articles: " + quantity() + " )";
    }
}
