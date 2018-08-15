package th.co.cdg.quiz4;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;

import java.util.List;

public class MainActivity extends AppCompatActivity implements UserAdapter.ItemClickListener, UserAdapter.ItemLongClickListener {

    private static final String TAG = "dlg";
    private UserAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        requestPermission();

        Button btnAdd = findViewById(R.id.btn_add);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, InputActivity.class);
                startActivity(intent);
            }
        });

        RecyclerView recyclerView = findViewById(R.id.recycler);

        adapter = new UserAdapter(getApplicationContext());
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter.setClickListener(this);
        adapter.setLongClickListener(this);
        recyclerView.setAdapter(adapter);

        getData();
    }

    private void getData() {
        adapter.clear();
        UserTable userTable = new UserTable(getApplicationContext());

        List<UserModel> users = userTable.getAll();
        for (UserModel user : users) {
            Log.d(TAG, String.format("user: %s, %s", user.getName(), String.valueOf(user.getAge())));
            adapter.add(user);
        }
        adapter.notifyDataSetChanged();
    }

    private void requestPermission() {
        Dexter.withActivity(this)
                .withPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                .withListener(new PermissionListener() {
                    @Override
                    public void onPermissionGranted(PermissionGrantedResponse response) {/* ... */}

                    @Override
                    public void onPermissionDenied(PermissionDeniedResponse response) {/* ... */}

                    @Override
                    public void onPermissionRationaleShouldBeShown(PermissionRequest permission, PermissionToken token) {
                        token.continuePermissionRequest();
                    }
                }).check();
    }

    @Override
    public void onItemClick(View view, int position) {
        Intent intent = new Intent(MainActivity.this, UpdateActivity.class);
        intent.putExtra("DETAIL", new UserModel(
                adapter.getItem(position).getId(),
                adapter.getItem(position).getName(),
                adapter.getItem(position).getAge()));
        startActivity(intent);
//        Toast.makeText(getApplicationContext(), String.valueOf(adapter.getItem(position).getId()), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onLongItemClick(View view, int position) {
        UserTable userTable = new UserTable(getApplicationContext());
        long l = userTable.DeleteData(String.valueOf(adapter.getItem(position).getId()));
        if (l > 0) {
            Snackbar.make(findViewById(android.R.id.content), adapter.getItem(position).getName() + " ลบแล้ว", Snackbar.LENGTH_LONG).show();
            getData();
        }
        //        Toast.makeText(getApplicationContext(), String.valueOf(adapter.getItem(position).getId()), Toast.LENGTH_SHORT).show();
    }
}

