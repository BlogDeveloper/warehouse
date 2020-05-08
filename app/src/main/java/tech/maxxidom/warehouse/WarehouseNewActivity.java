package tech.maxxidom.warehouse;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


public class WarehouseNewActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText etRoomNumberNew;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_warehouse_new);

        etRoomNumberNew = findViewById(R.id.etRoomNumberNew);

        Button btnRoomNumberAdd = findViewById(R.id.btnRoomNumberAdd);
        btnRoomNumberAdd.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent result = new Intent();

        String roomNumber = etRoomNumberNew.getText().toString();
        result.putExtra(MainActivity.RESULT_ROOM_NUMBER, roomNumber);

        setResult(MainActivity.RESULT_WAREHOUSE_ADD, result);
        finish();
    }
}
