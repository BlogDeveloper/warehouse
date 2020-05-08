package tech.maxxidom.warehouse;

public class Article {

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
}
