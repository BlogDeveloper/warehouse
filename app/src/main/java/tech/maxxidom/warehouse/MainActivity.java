package tech.maxxidom.warehouse;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemClickListener {

    private ArrayList<Warehouse> warehousesList = new ArrayList<>();
    private ArrayAdapter<Warehouse> adapter;

    public static final int RESULT_WAREHOUSE_ADD = 3;
    public static final int RESULT_WAREHOUSE_DETAIL = 4;
    public static final int RESULT_WAREHOUSE_DELETE = 9;

    public static final String RESULT_ROOM_NUMBER = "RESULT_ROOM_NUMBER";

    public static final String POSITION = "POSITION";
    public static final String DATA = "DATA";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Warehouse h1 = new Warehouse("11.5");
        h1.setArticle("Brot", 12);
        h1.setArticle("Milch", 3);
        h1.setArticle("Butter", 136);

        Warehouse h2 = new Warehouse("11.6");
        h2.setArticle("Wasser", 200);
        h2.setArticle("Cola", 540);

        warehousesList.add(h1);
        warehousesList.add(h2);

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, warehousesList);

        ListView lvRoomList = findViewById(R.id.lvRoomList);
        lvRoomList.setOnItemClickListener(this);
        lvRoomList.setAdapter(adapter);

        Button btnNewRoom = findViewById(R.id.btnNewRoom);
        btnNewRoom.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnNewRoom:
                onNewWarehouseClick();
                break;
        }
    }

    private void onNewWarehouseClick() {
        Intent intent = new Intent(this, WarehouseNewActivity.class);
        startActivityForResult(intent, RESULT_WAREHOUSE_ADD);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {
            case RESULT_WAREHOUSE_ADD:
                onActivityNewWarehouseResult(resultCode, data);
                break;
            case RESULT_WAREHOUSE_DETAIL:
                onActivityEditWarehouseResult(resultCode, data);
                break;
        }

    }

    private void onActivityEditWarehouseResult(int resultCode, Intent data) {
        if (resultCode == RESULT_WAREHOUSE_DETAIL) {

            int position = data.getIntExtra(POSITION, -1);

            if (position >= 0) {
                String roomNumber = data.getStringExtra(RESULT_ROOM_NUMBER);

                Warehouse warehouse = warehousesList.get(position);
                warehouse.setRoomNumber(roomNumber);
            }

            adapter.notifyDataSetChanged();
        }
    }

    private void onActivityNewWarehouseResult(int resultCode, Intent data) {
        if (resultCode == RESULT_WAREHOUSE_ADD) {
            String roomNumber = data.getStringExtra(RESULT_ROOM_NUMBER);

            warehousesList.add(new Warehouse(roomNumber));
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Warehouse warehouse = warehousesList.get(position);

        Intent intent = new Intent(this, WarehouseDetailActivity.class);
        intent.putExtra(POSITION, position);
        intent.putExtra(DATA, warehouse);

        startActivityForResult(intent, RESULT_WAREHOUSE_DETAIL);
    }
}
