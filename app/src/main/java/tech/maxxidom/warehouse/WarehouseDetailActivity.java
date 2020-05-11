package tech.maxxidom.warehouse;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

public class WarehouseDetailActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemClickListener {

    private EditText etRoomNumberDetail;
    private ArrayList<Article> articlesList = new ArrayList<>();
    private ArrayAdapter<Article> adapter;

    private Warehouse warehouse;
    private int position;

    public static final int RESULT_ARTICLE_ADD     = 3;
    public static final int RESULT_ARTICLE_DETAIL  = 4;
    public static final int RESULT_ARTICLE_EDIT    = 5;
    public static final int RESULT_ARTICLE_DELETE  = 6;

    public static final String POSITION = "POSITION";
    public static final String DATA     = "DATA";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_warehouse_detail);

        etRoomNumberDetail = findViewById(R.id.etRoomNumberDetail);

        Intent intent = getIntent();
        warehouse = (Warehouse) intent.getSerializableExtra(MainActivity.DATA);
        position = intent.getIntExtra(MainActivity.POSITION, -1);

        etRoomNumberDetail.setText(warehouse.getRoomNumber());
        articlesList = warehouse.getArticles();

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, articlesList);
        ListView lvArticleList = findViewById(R.id.lvArticleList);
        lvArticleList.setOnItemClickListener(this);
        lvArticleList.setAdapter(adapter);

        Button btnRoomNumberSave = findViewById(R.id.btnRoomNumberSave);
        btnRoomNumberSave.setOnClickListener(this);

        Button btnRoomNumberDelete = findViewById(R.id.btnRoomNumberDelete);
        btnRoomNumberDelete.setOnClickListener(this);

        Button btnNewArticle = findViewById(R.id.btnNewArticle);
        btnNewArticle.setOnClickListener(this);
    }

    @Override
    public void onBackPressed() {
        Save();
        super.onBackPressed();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnRoomNumberSave:
                OnClickSave();
                break;
            case R.id.btnRoomNumberDelete:
                OnClickRoomNumberDelete();
                break;
            case R.id.btnNewArticle:
                OnClickNewArticle();
                break;
        }
    }

    private void OnClickSave() {
        Save();
    }

    private void Save() {
        Intent intent = new Intent();

        String roomNumber = etRoomNumberDetail.getText().toString();
        warehouse.setRoomNumber(roomNumber);

        intent.putExtra(MainActivity.DATA, warehouse);
        intent.putExtra(MainActivity.POSITION, position);

        setResult(MainActivity.RESULT_WAREHOUSE_EDIT, intent);
        finish();
    }

    private void OnClickRoomNumberDelete() {
        Intent intent = new Intent();
        intent.putExtra(MainActivity.POSITION, position);

        setResult(MainActivity.RESULT_WAREHOUSE_DELETE, intent);
        finish();
    }

    private void OnClickNewArticle() {
        Intent intent = new Intent(this, ArticleNewActivity.class);
        startActivityForResult(intent, RESULT_ARTICLE_ADD);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Article article = articlesList.get(position);

        Intent intent = new Intent(this, ArticleDetailActivity.class);
        intent.putExtra(MainActivity.POSITION, position);
        intent.putExtra(MainActivity.DATA, article);

        startActivityForResult(intent, RESULT_ARTICLE_DETAIL);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {
            case RESULT_ARTICLE_DETAIL:
                OnActivityDetailArticleResult(resultCode, data);
                break;
            case RESULT_ARTICLE_ADD:
                OnActivityNewArticleResult(resultCode, data);
                break;
        }
    }

    private void OnActivityNewArticleResult(int resultCode, Intent data) {
        if (resultCode == RESULT_ARTICLE_ADD) {
            Article resultArticle = (Article) data.getSerializableExtra(DATA);
            warehouse.setArticle(resultArticle.getArticleName(), resultArticle.getArticleQuantity());
        }

        adapter.notifyDataSetChanged();
    }

    private void OnActivityDetailArticleResult(int resultCode, Intent data) {
        if (resultCode == RESULT_ARTICLE_EDIT) {
            int position = data.getIntExtra(POSITION, -1);

            if (position >= 0) {
                Article resultArticle = (Article) data.getSerializableExtra(DATA);

                Article article = articlesList.get(position);
                article.setArticleName(resultArticle.getArticleName());
                article.setArticleQuantity(resultArticle.getArticleQuantity());
            }
        }

        if (resultCode == RESULT_ARTICLE_DELETE) {
            int position = data.getIntExtra(POSITION, -1);
            if (position >= 0) {
                Article article = articlesList.get(position);
                articlesList.remove(article);
            }
        }

        adapter.notifyDataSetChanged();
    }
}
