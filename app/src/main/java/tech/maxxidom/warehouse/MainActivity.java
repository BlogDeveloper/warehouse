package tech.maxxidom.warehouse;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Date;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemClickListener {

    private ArrayList<Warehouse> warehousesList = new ArrayList<>();
    private ArrayAdapter<Warehouse> adapter;

    private File file;

    public static final int RESULT_WAREHOUSE_ADD     = 3;
    public static final int RESULT_WAREHOUSE_DETAIL  = 4;
    public static final int RESULT_WAREHOUSE_EDIT    = 5;
    public static final int RESULT_WAREHOUSE_DELETE  = 6;
    public static final int RESULT_WAREHOUSE_SAVE    = 7;

    public static final String RESULT_ROOM_NUMBER = "RESULT_ROOM_NUMBER";
    public static final String POSITION = "POSITION";
    public static final String DATA = "DATA";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        file = new File(getExternalFilesDir(null), "warehouse.txt");
        Deserialize();

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, warehousesList);

        ListView lvRoomList = findViewById(R.id.lvRoomList);
        lvRoomList.setOnItemClickListener(this);
        lvRoomList.setAdapter(adapter);

        Button btnNewRoom = findViewById(R.id.btnNewRoom);
        btnNewRoom.setOnClickListener(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        Serialize();
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
                onActivityDetailWarehouseResult(resultCode, data);
                break;
        }
    }

    private void onActivityDetailWarehouseResult(int resultCode, Intent data) {
        if (resultCode == RESULT_WAREHOUSE_EDIT) {
            int position = data.getIntExtra(POSITION, -1);
            if (position >= 0) {
                Warehouse resultWarehouse = (Warehouse) data.getSerializableExtra(DATA);
                Warehouse warehouse = warehousesList.get(position);
                warehouse.setRoomNumber(resultWarehouse.getRoomNumber());
                warehouse.setArticles(resultWarehouse.getArticles());
            }
        }
        
        if (resultCode == RESULT_WAREHOUSE_DELETE) {
            int position = data.getIntExtra(POSITION, -1);
            if (position >= 0) {
                Warehouse warehouse = warehousesList.get(position);
                warehousesList.remove(warehouse);
            }
        }

        adapter.notifyDataSetChanged();
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


    public void Serialize() {

        try {
            FileOutputStream fos = new FileOutputStream(file);
            ObjectOutputStream oos = new ObjectOutputStream(fos);

//            for (Warehouse warehouse : warehousesList) {
//                oos.writeObject(warehouse);
//            }

            oos.writeObject(warehousesList);

            fos.close();
            oos.close();

        } catch (Exception ex) {
            Log.e("test", "save()", ex);
        }
    }

    public void Deserialize() {
        try {

            FileInputStream fis = new FileInputStream(file);
            ObjectInputStream ois = new ObjectInputStream(fis);

            warehousesList.clear();
            Object obj;

            while ((obj = ois.readObject()) != null)  {
//                warehousesList.add((Warehouse) obj);
                warehousesList = (ArrayList<Warehouse>) obj;
            }

        } catch (Exception ex) {
            Log.e("test", "save()", ex);
        }
    }

}
