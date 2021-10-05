package com.rianezza.modlist;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView listmod;
    Spinner kategori;
    ArrayAdapter<Mods> adapter;
    String[] list_kategori = {"All","Graphics","Gameplay","Tools"};
    private static final String[] auto = new String[] {"Realistic Physics", "Sounds Mod Pack", "Texture Pack", "Squid Game Mod", "Battle Royale Mod"};

    //Inisialisasi pada saat menjalankan activity
    private void inisialisasi() {

        //setAdapter untuk autocomplete, belum mampu untuk mensearch berdasarkan input
        AutoCompleteTextView autocomplete;
        autocomplete = findViewById(R.id.search_bar);
        ArrayAdapter<String> autos = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, auto);
        autocomplete.setAdapter(autos);

        //setAdapter untuk spinner filter
        kategori = findViewById(R.id.filter);
        kategori.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, list_kategori));

        //setAdapter untuk ListView
        listmod = findViewById(R.id.mod_list);
        listmod.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, modlist()));

        //Event saat memilih anggota object spinner
        kategori.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long itemID) {
                if (position >= 0 && position < list_kategori.length) {
                    selectedcategory(position);
                } else {
                    Toast.makeText(MainActivity.this, "Category does not exist", Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    //Isi dari ArrayList yang diinisiasi dari awal
    private ArrayList<Mods> modlist() {
        ArrayList<Mods> list = new ArrayList<>();
        list.clear();

        list.add(new Mods("Realistic Physics", 2));
        list.add(new Mods("Sounds Mod Pack", 2));
        list.add(new Mods("Texture Pack", 1));
        list.add(new Mods("Squid Game Mod", 2));
        list.add(new Mods("Battle Royale Mod", 2));
        list.add(new Mods("Raytracing Mod", 1));
        list.add(new Mods("Optifine", 3));
        list.add(new Mods("Just Enough Items (JEI)", 3));
        list.add(new Mods("Biomes O'Plenty", 2));
        list.add(new Mods("DimensionalDoors", 2));
        list.add(new Mods("Fire and Ice: Dragons", 2));
        list.add(new Mods("Engineer's Tools", 2));
        list.add(new Mods("The Lost Cities", 2));

        return list;
    }
    //Mendapatkan anggota dari filter yang dipilih dan menggabungkan pada ListView
    private void selectedcategory(int categoryID) {
        //ArrayList untuk menampung dari item yang difilter
        ArrayList<Mods> kategori_terpilih = new ArrayList<>();
        if(categoryID == 0)
        {
            adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, modlist());
        }
        else{
            //Difilter berdasarkan ID
            for (Mods cosmicBody : modlist()) {
                if (cosmicBody.getIDkategori() == categoryID) {
                    kategori_terpilih.add(cosmicBody);
                }
            }
            adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, kategori_terpilih);
        }
        //setAdapter pada ListView
        listmod.setAdapter(adapter);
    }

    //Pada saat activity dibuat akan langsung menjalankan inisialisasi();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        inisialisasi();
    }
}

/*
    class yang berisikan method untuk mendapatkan ID kategori
    dan mendefinisikan karakteristik object untuk list
 */
class Mods {
    private String name;
    private int categoryID;

    public int getIDkategori() {
        return categoryID;
    }

    public Mods(String name, int categoryID) {
        this.name = name;
        this.categoryID = categoryID;
    }

    @Override
    public String toString() {
        return name;
    }
}
