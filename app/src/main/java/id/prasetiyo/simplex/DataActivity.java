package id.prasetiyo.simplex;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class DataActivity extends AppCompatActivity {

    int var, batas, row, column;
    TextView label_tv;
    LinearLayout layout;
    EditText data;
    ArrayList<EditText> datas;
    private Button btnSolve;
    int[] value;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data);
        getSupportActionBar().setElevation(0);
        this.setTitle("Masukkan Fungsi Z dan batasnya");
        Intent intent = getIntent();
        if (intent.getExtras() != null){
            Bundle bundle = intent.getExtras();
            var = bundle.getInt("var");
            batas = bundle.getInt("batas");
        }
        layout = (LinearLayout) findViewById(R.id.form_ly);
        datas = new ArrayList<EditText>();
        setData();

        btnSolve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                solve();
            }
        });
    }

    private void solve() {
        getData();
        Intent intent = new Intent(DataActivity.this, JawabanActivity.class);
        Bundle data = new Bundle();
        data.putIntArray("value", value);
        data.putInt("var", var);
        data.putInt("row", row);
        data.putInt("column", column);
        intent.putExtras(data);
        startActivity(intent);
    }

    private void getData() {
        value = new int[datas.size()];

        for(int k = 0; k < datas.size(); k++){
            try {
                value[k] = Integer.parseInt(datas.get(k).getText().toString());
            }catch(NumberFormatException e){

            }

        }
    }


    private void setData() {
        LinearLayout.LayoutParams p = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);

        row = batas + 1;
        column = batas + var + 3;

        label_tv = new TextView(this);
        label_tv.setText("Fungsi Z :");
        label_tv.setTypeface(Typeface.DEFAULT_BOLD);
        label_tv.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        layout.addView(label_tv);
        for(int i = 1; i <= row ; i++){
            for(int j = 1; j <= var+1; j++){
                //p.bottomMargin=5;
                TextInputLayout tl = new TextInputLayout(this);
                tl.setLayoutParams(p);

                data = new EditText(this);
                data.setLayoutParams(p);
                data.setSingleLine(true);
                if (j == var+1){
                    data.setHint("nilai");
                }else{
                    data.setHint("x"+j);
                }
                datas.add(data);
                layout.addView(tl);
                tl.addView(data);

            }
            if(i <= row-1) {
                label_tv = new TextView(this);
                label_tv.setPadding(0,10,0,0);
                label_tv.setTextSize(15);
                label_tv.setTypeface(Typeface.DEFAULT_BOLD);
                label_tv.setText("Batas ke-" + i);
                layout.addView(label_tv);
            }else{
                continue;
            }
        }
        label_tv = new TextView(this);
        label_tv.setPadding(0,10,0,0);
        layout.addView(label_tv);
        btnSolve = new Button(this);
        btnSolve.setText("Solve");
        btnSolve.setTextColor(getResources().getColor(R.color.white));
        btnSolve.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
        layout.addView(btnSolve);
    }
}
