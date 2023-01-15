package com.electrocaps.aurasmart;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.ToggleButton;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {

    private ImageView imageView ;
    private View mcolorview;
    private Bitmap bitmap;
    private ToggleButton living_room,bed_room,kitchen,breathing,smooth,rgb,lamp,pendant,overhead;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Write a message to the database
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("message");
        myRef.setValue("App Opened");

        // color pikcer
        imageView = findViewById(R.id.colorpicker);
        // color view bar
        mcolorview = findViewById(R.id.discolor);

        //rooms
        living_room=findViewById(R.id.living_room);
        bed_room=findViewById(R.id.bed_room);
        kitchen=findViewById(R.id.kitchen);
        //modes
        breathing=findViewById(R.id.breathing);
        smooth=findViewById(R.id.smooth);
        rgb=findViewById(R.id.rgb);

        //lights
        overhead=findViewById(R.id.overhead);
        lamp=findViewById(R.id.lamp);
        pendant=findViewById(R.id.pendant);
        
        /////// color wheel 
        
//        imageView.setDrawingCacheEnabled(true);
//        imageView.buildDrawingCache(true);


        /// end colr wheel
        
        
        // roooms set

        living_room.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if (isChecked)
                {
                    living_room.setBackgroundResource(R.drawable.living_on);
                    bed_room.setBackgroundResource(R.drawable.bed_ff);
                    kitchen.setBackgroundResource(R.drawable.kitch_off);



//                    lay1.setBackgroundResource(R.drawable.gradient2);
                }
                else
                {
                    living_room.setBackgroundResource(R.drawable.living_off);
//                    lay1.setBackgroundResource(R.drawable.gradient);
                }
            }
        });



        bed_room.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if (isChecked)
                {
                    bed_room.setBackgroundResource(R.drawable.bed_on);
                    kitchen.setBackgroundResource(R.drawable.kitch_off);
                    living_room.setBackgroundResource(R.drawable.living_off);
//                    lay1.setBackgroundResource(R.drawable.gradient2);
                }
                else
                {
                    bed_room.setBackgroundResource(R.drawable.bed_ff);
//                    lay1.setBackgroundResource(R.drawable.gradient);
                }
            }
        });



        kitchen.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if (isChecked)
                {
                    kitchen.setBackgroundResource(R.drawable.kitchen_on);
                    bed_room.setBackgroundResource(R.drawable.bed_ff);
                    living_room.setBackgroundResource(R.drawable.living_off);
//                    lay1.setBackgroundResource(R.drawable.gradient2);
                }
                else
                {
                    kitchen.setBackgroundResource(R.drawable.kitch_off);
//                    lay1.setBackgroundResource(R.drawable.gradient);
                }
            }
        });

        /// end

        /// modes

        breathing.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if (isChecked)
                {
                    breathing.setBackgroundResource(R.drawable.breath_on);
                    rgb.setBackgroundResource(R.drawable.rgb_off);
                    smooth.setBackgroundResource(R.drawable.smooth_off);

                    FirebaseDatabase database = FirebaseDatabase.getInstance();
                    DatabaseReference myRef = database.getReference("breath");
                    myRef.setValue(1);
                   DatabaseReference myRef1 = database.getReference("smooth");
                    myRef1.setValue(0);
                    imageView.destroyDrawingCache();
//                    lay1.setBackgroundResource(R.drawable.gradient2);
                }
                else
                {
                    breathing.setBackgroundResource(R.drawable.breath_off);
//                    lay1.setBackgroundResource(R.drawable.gradient);

                    FirebaseDatabase database = FirebaseDatabase.getInstance();
                    DatabaseReference myRef = database.getReference("breath");
                    myRef.setValue(0);

                }
            }
        });

        smooth.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @SuppressLint("ClickableViewAccessibility")
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if (isChecked)
                {
                    smooth.setBackgroundResource(R.drawable.smooth_on);
                    rgb.setBackgroundResource(R.drawable.rgb_off);
                    breathing.setBackgroundResource(R.drawable.breath_off);


                    imageView.setOnTouchListener(new View.OnTouchListener() {
                        @SuppressLint("ClickableViewAccessibility")
                        @Override
                        public boolean onTouch(View view, MotionEvent event) {

                            if (event.getAction() == MotionEvent.ACTION_DOWN || event.getAction() == MotionEvent.ACTION_MOVE) {
                                bitmap = imageView.getDrawingCache();
                                int pixels = bitmap.getPixel((int) event.getX(), (int) event.getY());
                                int r = Color.red(255);
                                int g = Color.green(255);
                                int b = Color.blue(255);
//                                String hex = "#" + Integer.toHexString(pixels);

//                mcolorValues.setText("RBG :"+r+","+g+","+b+"\nHex : "+hex);
                                mcolorview.setBackgroundColor(Color.rgb(r, g, b));

                                FirebaseDatabase database = FirebaseDatabase.getInstance();
                                DatabaseReference myRefr = database.getReference("R");
                                DatabaseReference myRefg = database.getReference("G");
                                DatabaseReference myRefb = database.getReference("B");
                                myRefr.setValue(r);
                                myRefg.setValue(g);
                                myRefb.setValue(b);
                            }
                            return true;
                        }
                    });



                    //                    lay1.setBackgroundResource(R.drawable.gradient2);

                    FirebaseDatabase database = FirebaseDatabase.getInstance();
                    DatabaseReference myRef = database.getReference("smooth");
                    myRef.setValue(1);

                    DatabaseReference myRef2 = database.getReference("breath");
                    myRef2.setValue(0);
                }
                else
                {
                    smooth.setBackgroundResource(R.drawable.smooth_off);
//                    lay1.setBackgroundResource(R.drawable.gradient);

                    FirebaseDatabase database = FirebaseDatabase.getInstance();
                    DatabaseReference myRef = database.getReference("smooth");
                    myRef.setValue(0);


                }
            }
        });

        rgb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @SuppressLint("ClickableViewAccessibility")
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if (isChecked)
                {

                    rgb.setBackgroundResource(R.drawable.rgb_on);
                    smooth.setBackgroundResource(R.drawable.smooth_off);
                    breathing.setBackgroundResource(R.drawable.breath_off);
                    FirebaseDatabase database = FirebaseDatabase.getInstance();
                    DatabaseReference myRef = database.getReference("breath");
                    myRef.setValue(0);

                    DatabaseReference myRef3 = database.getReference("smooth");
                    myRef3.setValue(0);
//                    lay1.setBackgroundResource(R.drawable.gradient2);
                    imageView.setDrawingCacheEnabled(true);
                    imageView.buildDrawingCache(true);

                    imageView.setOnTouchListener(new View.OnTouchListener() {
                        @SuppressLint("ClickableViewAccessibility")
                        @Override
                        public boolean onTouch(View view, MotionEvent event) {

                            if (event.getAction() == MotionEvent.ACTION_DOWN || event.getAction() == MotionEvent.ACTION_MOVE) {
                                bitmap = imageView.getDrawingCache();
                                int pixels = bitmap.getPixel((int) event.getX(), (int) event.getY());
                                int r = Color.red(pixels);
                                int g = Color.green(pixels);
                                int b = Color.blue(pixels);
                                String hex = "#" + Integer.toHexString(pixels);

//                mcolorValues.setText("RBG :"+r+","+g+","+b+"\nHex : "+hex);
                                mcolorview.setBackgroundColor(Color.rgb(r, g, b));

                    FirebaseDatabase database = FirebaseDatabase.getInstance();
                    DatabaseReference myRefr = database.getReference("R");
                    DatabaseReference myRefg = database.getReference("G");
                    DatabaseReference myRefb = database.getReference("B");
                    myRefr.setValue(r);
                    myRefg.setValue(g);
                    myRefb.setValue(b);
                            }
                            return true;
                        }
                    });

                }
                else
                {
                    rgb.setBackgroundResource(R.drawable.rgb_off);
                   //  lay1.setBackgroundResource(R.drawable.gradient);

                    FirebaseDatabase database = FirebaseDatabase.getInstance();
                    DatabaseReference myRefr = database.getReference("R");
                    DatabaseReference myRefg = database.getReference("G");
                    DatabaseReference myRefb = database.getReference("B");
                    myRefr.setValue("255");
                    myRefg.setValue("255");
                    myRefb.setValue("255");

                }
            }
        });
//        end

        /// lights

        overhead.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if (isChecked)
                {
                    overhead.setBackgroundResource(R.drawable.overhead_on);
//                    lay1.setBackgroundResource(R.drawable.gradient2);
                }
                else
                {
                    overhead.setBackgroundResource(R.drawable.toggle__overhead);
//                    lay1.setBackgroundResource(R.drawable.gradient);
                }
            }
        });
        lamp.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if (isChecked)
                {
                    lamp.setBackgroundResource(R.drawable.lamp_on);
//                    lay1.setBackgroundResource(R.drawable.gradient2);
                }
                else
                {
                    lamp.setBackgroundResource(R.drawable.lamp_off);
//                    lay1.setBackgroundResource(R.drawable.gradient);
                }
            }
        });
        pendant.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if (isChecked)
                {
                    pendant.setBackgroundResource(R.drawable.pendant_on);
//                    lay1.setBackgroundResource(R.drawable.gradient2);
                }
                else
                {
                    pendant.setBackgroundResource(R.drawable.toggle__pendant);
//                    lay1.setBackgroundResource(R.drawable.gradient);
                }
            }
        });
        // end



    }
}






