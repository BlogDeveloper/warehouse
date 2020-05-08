package tech.maxxidom.warehouse;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class ArticleDetailActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText etArticleNameDetail;
    private EditText etArticleQuantityDetail;
    private int position;
    private Article article;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article_detail);

        etArticleNameDetail     = findViewById(R.id.etArticleNameDetail);
        etArticleQuantityDetail = findViewById(R.id.etArticleQuantityDetail);

        Intent intent = getIntent();
        position = intent.getIntExtra(WarehouseDetailActivity.POSITION, -1);
        article = (Article) intent.getSerializableExtra(WarehouseDetailActivity.DATA);

        etArticleNameDetail.setText(article.getArticleName());
        etArticleQuantityDetail.setText(String.valueOf(article.getArticleQuantity()));

        Button btnArticleSave = findViewById(R.id.btnArticleSave);
        btnArticleSave.setOnClickListener(this);

        Button btnArticleDelete = findViewById(R.id.btnArticleDelete);
        btnArticleDelete.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

    }
}
