package th.co.cdg.quiz4;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class InputActivity extends AppCompatActivity {

    private EditText tvID, tvName, tvAge;
    private Button btnAdd, btnBack;

    @Override
    public void onBackPressed() {
//        super.onBackPressed();
        back();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input);

        final UserTable userTable = new UserTable(getApplicationContext());

//        ArrayList<UserModel> users = userTable.getAll();

        tvID = findViewById(R.id.tv_id);
        tvName = findViewById(R.id.tv_name);
        tvAge = findViewById(R.id.tv_age);
        btnAdd = findViewById(R.id.btn_add);
        btnBack = findViewById(R.id.btn_back);
        tvID.setEnabled(false);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("dld", "onClick: ");
                userTable.addValue(tvName.getText().toString(), Integer.valueOf(tvAge.getText().toString()));
            }
        });

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                back();
            }
        });

    }

    private void back() {
        Intent intent = new Intent(InputActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}
