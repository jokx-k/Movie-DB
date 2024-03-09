package com.example.lab5;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MovieDBHelper extends SQLiteOpenHelper {

    private static String databaseName="Movie Database";
    SQLiteDatabase movieDatabase;

    public MovieDBHelper(Context context){
        super(context,databaseName,null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("create table movie (id integer primary key," + "name text not null,description text)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("drop table if exists movie");
        onCreate(db);

    }

    public void createNewMovie(String name ,String desc ){

        ContentValues row=new ContentValues();
        row.put("name",name);
        row.put("description",desc);
        movieDatabase=getWritableDatabase();
        movieDatabase.insert("movie",null,row);
        movieDatabase.close();
    }

    public Cursor fetchAllMovies(){

        movieDatabase=getReadableDatabase();
        String[]rowDetails={"name","description","id"};
        Cursor cursor=movieDatabase.query("movie",rowDetails,null,null,null,null,null);
        if(cursor!=null){
            cursor.moveToFirst();
        }
        movieDatabase.close();
        return cursor;




    }

    public String getMovieDesc(String name){

        movieDatabase =getReadableDatabase();
        String[] arg={name};
        Cursor cursor=movieDatabase.rawQuery("Select description from movie where name like ?",arg );
        cursor.moveToFirst();
        movieDatabase.close();
        return cursor.getString(0);

    }


}
