package tech.maxxidom.warehouse;

import androidx.annotation.NonNull;

import java.io.Serializable;

public class Article implements Serializable {

    private String articleName;
    private int articleQuantity;

    public Article(String articleName, int articleQuantity) {
        setArticleName(articleName);
        setArticleQuantity(articleQuantity);
    }

    public String getArticleName() {
        return articleName;
    }

    public void setArticleName(String articleName) {
        this.articleName = articleName;
    }

    public int getArticleQuantity() {
        return articleQuantity;
    }

    public void setArticleQuantity(int articleQuantity) {
        this.articleQuantity = articleQuantity;
    }

    @NonNull
    @Override
    public String toString() {
        return getArticleName() + " ( " + getArticleQuantity() + " )";
    }
}
