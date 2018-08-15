package th.co.cdg.quiz4;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class UpdateActivity extends AppCompatActivity {

    private UserTable userTable;
    private EditText tvID, tvName, tvAge;
    private Button btnUpdate, btnBack;
    private UserModel user;

    @Override
    public void onBackPressed() {
//        super.onBackPressed();
        back();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input);

        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if (extras == null) {
                user = null;
            } else {
                user = extras.getParcelable("DETAIL");
            }
        } else {
            user = savedInstanceState.getParcelable("DETAIL");
        }

        userTable = new UserTable(getApplicationContext());

        tvID = findViewById(R.id.tv_id);
        tvName = findViewById(R.id.tv_name);
        tvAge = findViewById(R.id.tv_age);
        btnUpdate = findViewById(R.id.btn_add);
        btnBack = findViewById(R.id.btn_back);

        tvID.setEnabled(false);
        btnUpdate.setText("Update");

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("dld", "onClick: ");
                userTable.updateValue(Integer.valueOf(tvID.getText().toString()), tvName.getText().toString(), Integer.valueOf(tvAge.getText().toString()));
            }
        });

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                back();
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        tvID.setText(String.valueOf(user.getId()));
        tvName.setText(user.getName());
        tvAge.setText(String.valueOf(user.getAge()));
    }

    private void back() {
        Intent intent = new Intent(UpdateActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}
