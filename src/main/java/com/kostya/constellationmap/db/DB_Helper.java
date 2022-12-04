package com.kostya.constellationmap.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import androidx.annotation.Nullable;

public class DB_Helper extends SQLiteOpenHelper {

    public DB_Helper(@Nullable Context context, @Nullable String name, int version) {
        super(context, name, null, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        // створення таблиць
        sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS Researchers(_id INTEGER PRIMARY KEY AUTOINCREMENT, Researcher CHAR(12));");
        sqLiteDatabase.execSQL(
                "CREATE TABLE IF NOT EXISTS Info(_id INTEGER PRIMARY KEY AUTOINCREMENT, name VARCHAR(20), " +
                "Latin_name VARCHAR(20), Brightest_Star VARCHAR(20), id_Described_by INTEGER, Area INTEGER," +
                " ImageId INTEGER, Description TEXT, FOREIGN KEY(id_Described_by) REFERENCES Researchers(_id));");

        // запис даних у головну таблицю
        for(int i=0; i < DataForDB.MainTableSize; i++){
            ContentValues contentValues = new ContentValues();
            contentValues.put("name", DataForDB.Constellations_Names[i]);
            contentValues.put("Latin_name", DataForDB.Constellations_Latin_Names[i]);
            contentValues.put("Brightest_Star", DataForDB.Brighest_Star[i]);
            contentValues.put("id_Described_by", DataForDB.ResearchersId[i]);
            contentValues.put("Area", DataForDB.Area[i]);
            contentValues.put("Description", DataForDB.Description[i]);
            contentValues.put("ImageId", DataForDB.imageId[i]);
            sqLiteDatabase.insert("Info", null, contentValues);
            contentValues.clear();
        }

        // запис даних у таблицю дослідників
        for(int i=0; i < DataForDB.SecondaryTableSize; i++){
            ContentValues contentValues = new ContentValues();
            contentValues.put("Researcher", DataForDB.Researchers[i]);
            sqLiteDatabase.insert("Researchers", null, contentValues);
            contentValues.clear();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        if(oldVersion != newVersion) {
            sqLiteDatabase.execSQL("DROP TABLE IF EXISTS Info");
            sqLiteDatabase.execSQL("DROP TABLE IF EXISTS Researchers");
            onCreate(sqLiteDatabase);
        }
    }
}
