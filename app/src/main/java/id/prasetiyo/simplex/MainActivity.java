package id.prasetiyo.simplex;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private EditText n_var;
    private EditText n_batas;
    private Button btnSolve;

    private TextInputLayout input_layout_var,input_layout_batasan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        n_var = (EditText) findViewById(R.id.input_n_var);
        n_batas = (EditText) findViewById(R.id.input_n_batas);
        btnSolve = (Button) findViewById(R.id.btn_solve);
        input_layout_var = (TextInputLayout) findViewById(R.id.input_layout_var);
        input_layout_batasan= (TextInputLayout) findViewById(R.id.input_layout_batasan);

        n_var.addTextChangedListener(new MyTextWatcher(n_var));
        n_batas.addTextChangedListener(new MyTextWatcher(n_batas));

        btnSolve.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                submitForm();
            }
        });

    }

    private void hitung(){
        int var,batas;
        var = Integer.parseInt(n_var.getText().toString());
        batas = Integer.parseInt(n_batas.getText().toString());
        Intent intent = new Intent(MainActivity.this, DataActivity.class);
        Bundle data = new Bundle();
        data.putInt("var", var);
        data.putInt("batas", batas);
        intent.putExtras(data);
        startActivity(intent);
    }

    private void submitForm() {
        if (!validateNum()) {
            return;
        }
        //Toast.makeText(getApplicationContext(), "Thank You!", Toast.LENGTH_SHORT).show();
        hitung();
    }

    private boolean validateNum() {
        if (n_var.getText().toString().trim().isEmpty()) {
            input_layout_var.setError("Form tidak boleh kosong");
            requestFocus(n_var);
            return false;
        } else {
            input_layout_var.setErrorEnabled(false);
        }
        if (n_batas.getText().toString().trim().isEmpty()) {
            input_layout_batasan.setError("Form tidak boleh kosong");
            requestFocus(n_batas);
            return false;
        } else {
            input_layout_batasan.setErrorEnabled(false);
        }

        return true;
    }

    private void requestFocus(View view) {
        if (view.requestFocus()) {
            getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
        }
    }

    private class MyTextWatcher implements TextWatcher {

        private View view;

        private MyTextWatcher(View view) {
            this.view = view;
        }

        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        }

        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        }

        public void afterTextChanged(Editable editable) {
            switch (view.getId()) {
                case R.id.input_n_var:
                    validateNum();
                    break;
                case R.id.input_n_batas:
                    validateNum();
                    break;
            }
        }
    }
}
