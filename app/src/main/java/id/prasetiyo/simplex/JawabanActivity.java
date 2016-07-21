package id.prasetiyo.simplex;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class JawabanActivity extends AppCompatActivity {

    int row, column, var;
    int[] value;
    double[][] data;
    String result = "";
    TableLayout tl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jawaban);
        this.setTitle("Jawaban");

        TextView hasil_txt = (TextView) findViewById(R.id.hasil_txt);
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        value   = bundle.getIntArray("value");
        row     = bundle.getInt("row");
        column  = bundle.getInt("column");
        var     = bundle.getInt("var");
        tl = (TableLayout) findViewById(R.id.main_table);
        //setTable(row, column);
        data    = new double[row][column];
        int d = -1;
        int e = 0;
        for(int i = 0; i < row; i++){
            for(int j = 0; j < column; j++){
                if(i == 0) {
                    if(j == 0){
                        data[i][j] = 1;
                    }else {
                        if (j > (var + 1)) {
                            data[i][j] = 0;
                        }else{
                            data[i][j] = value[e];
                            e++;
                        }
                    }
                }else {
                    if(j == 0){
                        data[i][j] = 0;
                        d++;
                    }else {
                        if (j >= var + 1) {
                            if (j == (var + 1 + d)) {
                                data[i][j] = 1;
                            } else {
                                if (j == column - 2) {
                                    data[i][j] = value[e];
                                    e++;
                                } else {
                                    data[i][j] = 0;
                                }
                            }
                        } else {
                            data[i][j] = value[e];
                            e++;
                        }
                    }
                }
            }
        }

        proses();
        hasil_txt.setText(""+result);

    }

    private void setTable(int row, int column) {
        for (int i = 1; i <= row; i++) {

            TableRow rows = new TableRow(this);
            rows.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT,
                    TableRow.LayoutParams.WRAP_CONTENT));

            // inner for loop
            for (int j = 1; j <= column; j++) {

                TextView tv = new TextView(this);
                tv.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT,
                        TableRow.LayoutParams.WRAP_CONTENT));
                tv.setPadding(1, 1, 1, 1);
                tv.setText("R " + i + ", C" + j);

                rows.addView(tv);

            }
            tl.addView(rows);
        }
    }


    public void proses(){
        result = "Iterasi 1\n\n";
        Simplex Simplex = new Simplex(row, column, var, data);
        for(int p = 0; p <= var; p++){
            Simplex.carizMinimum();
            Simplex.cariIndeks();
            result = result + Simplex.outputTabel() + "\n\n\n";
            if(p != var){
                result = result + "Iterasi " +(p+2)+ "\n\n";
            }
            Simplex.hitungt();
            Simplex.hitung();
            Simplex.nol();
        }
        result = result + Simplex.output() +"\n\n";
    }
}
