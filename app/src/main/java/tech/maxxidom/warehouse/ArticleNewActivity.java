package tech.maxxidom.warehouse;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class ArticleNewActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText etArticleNameNew;
    private EditText etArticleQuantityNew;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article_new);

        etArticleNameNew     = findViewById(R.id.etArticleNameNew);
        etArticleQuantityNew = findViewById(R.id.etArticleQuantityNew);

        Button btnArticleAdd = findViewById(R.id.btnArticleAdd);
        btnArticleAdd.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnArticleAdd:
                addNewArticle();
                break;
        }
    }

    private void addNewArticle() {
        Intent intent = new Intent(this, WarehouseDetailActivity.class);

        Article article = new Article(
                etArticleNameNew.getText().toString(),
                Integer.parseInt(etArticleQuantityNew.getText().toString())
        );

        intent.putExtra(WarehouseDetailActivity.DATA, article);

        setResult(WarehouseDetailActivity.RESULT_ARTICLE_ADD, intent);
        finish();
    }
}
