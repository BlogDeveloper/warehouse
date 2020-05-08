package tech.maxxidom.warehouse;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

public class WarehouseDetailActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText etRoomNumberDetail;
    private ArrayList<Article> articlesList = new ArrayList<>();

    private Warehouse warehouse;
    private int position;

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


        ArrayAdapter<Article> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, articlesList);
        ListView lvArticleList = findViewById(R.id.lvArticleList);
        lvArticleList.setAdapter(adapter);

        Button btnRoomNumberSave = findViewById(R.id.btnRoomNumberSave);
        btnRoomNumberSave.setOnClickListener(this);

        Button btnNewArticle = findViewById(R.id.btnNewArticle);
        btnNewArticle.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.btnRoomNumberSave:
                OnClickRoomNumberSave();
                break;
            case R.id.btnNewArticle:
                OnClickNewArticle();
                break;
        }

    }

    private void OnClickRoomNumberSave() {
        Intent intent = new Intent();

        String roomNumber = etRoomNumberDetail.getText().toString();
        warehouse.setRoomNumber(roomNumber);

        intent.putExtra(MainActivity.RESULT_ROOM_NUMBER, warehouse.getRoomNumber());
        intent.putExtra(MainActivity.POSITION, position);

        setResult(MainActivity.RESULT_WAREHOUSE_DETAIL, intent);
        finish();
    }

    private void OnClickNewArticle() {

    }
}
