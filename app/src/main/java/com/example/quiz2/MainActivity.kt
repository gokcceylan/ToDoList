package com.example.quiz2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
//import com.info.sqlitekullanimihazirveritabani.DatabaseCopyHelper
import kotlinx.android.synthetic.main.activity_main.*
import java.io.IOException

class MainActivity : AppCompatActivity(), SearchView.OnQueryTextListener {
    private lateinit var gorevlerListele:ArrayList<Gorevler>
    private lateinit var adapter:GorevlerAdapter
    private lateinit var vt:VeritabaniYardimcisi


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    //veritabani dersinde eklendi
    veritabaniKopyala()

    toolbarMainActivity.title = "Tasks"
    //toolbarMainActivity.subtitle = "Alt Başlık"
    setSupportActionBar(toolbarMainActivity) //arayuzde degisikligi gormek icin

    rv.setHasFixedSize(true) //recycler view görünür yaptık galiba
    rv.layoutManager = LinearLayoutManager(this@MainActivity)

    vt = VeritabaniYardimcisi(this@MainActivity)

    tumGorevleriAl()

    fab.setOnClickListener {
        startActivity(Intent(this@MainActivity, GorevKayitActivity::class.java))

    }

}

override fun onCreateOptionsMenu(menu: Menu): Boolean {

    menuInflater.inflate(R.menu.arama_menu, menu) //res dosyasi altindaki tasarim-menu vsyi kodlamaya aktarmak icin kullanilir

    val item = menu.findItem(R.id.action_ara)
    val searchView = item.actionView as SearchView
    searchView.setOnQueryTextListener(this) //this buradaki interface i isaret ediyor


    return super.onCreateOptionsMenu(menu)
}

override fun onBackPressed() {
    val intent = Intent(Intent.ACTION_MAIN)
    intent.addCategory(Intent.CATEGORY_HOME)
    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
    startActivity(intent)
} //uygulamayi anasayfadan geri tusuyla her zaman kapatir

override fun onQueryTextSubmit(query: String): Boolean {
    Log.e("Gönderilen arama sonucu",query) // arama sonucunu bize gondericek
    aramaYap(query)
    return true
}

override fun onQueryTextChange(newText: String): Boolean {
    Log.e("Harf girdikçe sonuç",newText) // arama sonucunu bize gondericek
    aramaYap(newText)
    return true
}

fun veritabaniKopyala(){
    val copyHelper = DatabaseCopyHelper(this@MainActivity)
    try{
        copyHelper.createDataBase()
        copyHelper.openDataBase()
    }catch(e: IOException){
        e.printStackTrace()
    }
}

fun tumGorevleriAl(){
    gorevlerListele = Gorevlerdao().tumGorevler(vt)
    //VeritabaniYardımcısı almazsa hata verir
    //bunun için ta en üstte private lateinit vt yarattık
    adapter = GorevlerAdapter(this@MainActivity, gorevlerListele, vt)
    rv.adapter = adapter

}
fun aramaYap(aramaKelimesi:String){
    gorevlerListele = Gorevlerdao().gorevAra(vt, aramaKelimesi)
    adapter = GorevlerAdapter(this@MainActivity, gorevlerListele, vt)
    rv.adapter = adapter
}
}