package com.example.modernartui1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.SeekBar;
import android.widget.Toast;

public class RelativeLayout extends AppCompatActivity {

    SeekBar seekBar1;
    View view1,view2,view3,view4,view5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_relative_layout);

        view1 = findViewById(R.id.view6);
        view2 = findViewById(R.id.view7);
        view3 = findViewById(R.id.view8);
        view4 = findViewById(R.id.view9);
        view5 = findViewById(R.id.view10);
        seekBar1 = findViewById(R.id.seekBar2);

        seekBar1.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                switch (i){
                    case 0:
                        view1.setBackgroundColor(getResources().getColor(R.color.color1));
                        view2.setBackgroundColor(getResources().getColor(R.color.color2));
                        view3.setBackgroundColor(getResources().getColor(R.color.color3));
                        view4.setBackgroundColor(getResources().getColor(R.color.color4));
                        view5.setBackgroundColor(getResources().getColor(R.color.color5));
                        break;
                    case 1:
                        view1.setBackgroundColor(getResources().getColor(R.color.color21));
                        view2.setBackgroundColor(getResources().getColor(R.color.color22));
                        view3.setBackgroundColor(getResources().getColor(R.color.color23));
                        view4.setBackgroundColor(getResources().getColor(R.color.color24));
                        view5.setBackgroundColor(getResources().getColor(R.color.color25));
                        break;
                    case 2:
                        view1.setBackgroundColor(getResources().getColor(R.color.color6));
                        view2.setBackgroundColor(getResources().getColor(R.color.color7));
                        view3.setBackgroundColor(getResources().getColor(R.color.color8));
                        view4.setBackgroundColor(getResources().getColor(R.color.color9));
                        view5.setBackgroundColor(getResources().getColor(R.color.color10));
                        break;
                    case 3:
                        view1.setBackgroundColor(getResources().getColor(R.color.color11));
                        view2.setBackgroundColor(getResources().getColor(R.color.color12));
                        view3.setBackgroundColor(getResources().getColor(R.color.color13));
                        view4.setBackgroundColor(getResources().getColor(R.color.color14));
                        view5.setBackgroundColor(getResources().getColor(R.color.color15));
                        break;
                    case 4:
                        view1.setBackgroundColor(getResources().getColor(R.color.color16));
                        view2.setBackgroundColor(getResources().getColor(R.color.color17));
                        view3.setBackgroundColor(getResources().getColor(R.color.color18));
                        view4.setBackgroundColor(getResources().getColor(R.color.color19));
                        view5.setBackgroundColor(getResources().getColor(R.color.color20));
                        break;
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    // Hàm tạo menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //Lệnh lấy menu đã tạo trong res/menu
        getMenuInflater().inflate(R.menu.modern_art_ui_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }
    //Hàm chọn menu
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()){
            case R.id.linearLayout:
                Intent intent = new Intent(RelativeLayout.this,MainActivity.class);
                startActivity(intent);
                break;
            case R.id.relativeLayout:
                Toast.makeText(this, "Đang ở giao diện Relative Layout", Toast.LENGTH_SHORT).show();
                break;
            case R.id.tableLayout:
                Intent intent1 = new Intent(RelativeLayout.this,TableLayout.class);
                startActivity(intent1);
                break;
            case R.id.moreInformation:
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
                alertDialog.setTitle("Thông tin về nhóm");
                alertDialog.setMessage("Nhóm em là nhóm Những cậu bé\nNhóm em gồm các thành viên:\n" +
                        "Lê Anh Quân - nhóm trưởng\nPhan Hoàng Nhật Tân\nHuỳnh Tuấn Thanh\nĐinh Nhật Tân");
                alertDialog.show();
            default:
                return true;
        }

        return super.onOptionsItemSelected(item);
    }
}