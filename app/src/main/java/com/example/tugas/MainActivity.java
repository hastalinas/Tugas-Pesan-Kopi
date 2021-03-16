package com.example.tugas;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.util.Log;
import android.net.Uri;
import android.content.Intent;

import java.text.NumberFormat;


public class MainActivity extends AppCompatActivity {
    private EditText mLocationEditText;
    int quantity=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mLocationEditText = (EditText) findViewById(R.id.location_edittext);
    }

    public void openLocation (View view) {
        String loc = mLocationEditText.getText().toString();
        Uri addressUri = Uri.parse("geo:0,0?=" +loc);
        Intent intent = new Intent(Intent.ACTION_VIEW, addressUri);

        if (intent.resolveActivity(getPackageManager()) !=null){
            startActivity(intent);
        }else {
            Log.d("ImplicitIntents","Can't handle this intent!");
        }
    }

    public void increment(View view){//perintah tombol tambah
        if(quantity==100){
            Toast.makeText(this,"pesanan maximal 100",Toast.LENGTH_SHORT).show();
            return;
        }
        quantity = quantity+1 ;
        display(quantity);
    }
    public void decrement(View view){//perintah tombol tambah
        if (quantity==1){
            Toast.makeText(this,"pesanan minimal 1",Toast.LENGTH_SHORT).show();
            return;
        }
        quantity = quantity -1;
        display(quantity);
    }

    public void Submitorder(View view){
        EditText nameEditText=(EditText)findViewById(R.id.edt_name);
        String name = nameEditText.getText().toString();
        String loc = mLocationEditText.getText().toString();
        Log.v("MainActivityctivity", "Nama:"+name);

        CheckBox whippedcreamChekBox = (CheckBox) findViewById(R.id.WhippedCream_checkbox);
        boolean haswhippedcream=whippedcreamChekBox.isChecked();
        Log.v("MainActivity","has whippedcream:"+haswhippedcream);

        CheckBox chocolateChekBox= (CheckBox) findViewById(R.id.Chocolate_checkbox);
        boolean haschocolate=chocolateChekBox.isChecked();//mengidentifikasi check
        Log.v("MainActivity","has whippedcream:"+haschocolate);

        int price=calculateprice(haswhippedcream,haschocolate);//memanggil method jumlah harga

        String pricemessage=createOrderSummary(price,name,loc,haswhippedcream,haschocolate);

        displayMessage(pricemessage);

    }

    private void displayMessage(String message) {
        TextView priceTextView = (TextView) findViewById(R.id.price_textview);
        priceTextView.setText(message);
    }

    private String createOrderSummary(int price, String name, String loc, boolean addWhippedcream, boolean addChocolate) {
        String pricemessage =" Nama = "+name;
        pricemessage+="\n Tambahkan Coklat = " +addWhippedcream;
        pricemessage+="\n Tambahkan Krim = " +addChocolate;
        pricemessage+="\n Jumlah Pemesanan = " +quantity;
        pricemessage+="\n Total = Rp " +price;
        pricemessage+="\n Alamat = "+loc ;
        pricemessage+="\n Terimakasih";
        return  pricemessage;
    }

    private int calculateprice(boolean addwhippedcream, boolean addchocolate) {
        int harga = 10000;
        if(addwhippedcream){
            harga=harga+1000;//harga tambahan toping
        }
        if(addchocolate){
            harga=harga+2000;
        }
        return quantity * harga;
    }

    private void display(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_textview);
        quantityTextView.setText("" + number);
    }

    private void displayPrice(int number){
        TextView priceTextView = (TextView) findViewById(R.id.price_textview);
        priceTextView.setText(NumberFormat.getCurrencyInstance().format(number));
    }

}