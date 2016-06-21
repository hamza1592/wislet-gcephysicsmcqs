package data;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.SQLException;

/**
 *
 * Created by Hamza on 4/20/2016.
 */
public class DatabaseHandler extends SQLiteOpenHelper{

    public static final String DATABASE_PATH = "/data/data/wislet.gcephysicsmcqs/databases/";
    public static final String DATABASE_NAME = "GCE_PHYSICS_MCQS";
    public static final int DATABASE_VERSION = 1;
    Context myContext;
    SQLiteDatabase myDatabase;

    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, (new LocalCursorFactory()), DATABASE_VERSION);
        myContext = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        /*(new GCE_PHY_CATEGORIESDB()).createTable(db);
        (new GCE_PHY_QUESTIONMAPPINGSDB()).createTable(db);
        (new GCE_PHY_ANSWERSDB()).createTable(db);
        (new GCE_PHY_QUESTIONSDB()).createTable(db);*/
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void createDataBase() throws IOException {

        boolean dbExist = checkDataBase();

        if(dbExist){
            //do nothing - database already exist
            /*ToDo: figure out why db always exists*/
            this.getReadableDatabase();

            try {

                copyDataBase();

            } catch (IOException e) {
                e.printStackTrace();
                throw new Error("Error copying database");

            }
        }else{

            //By calling this method and empty database will be created into the default system path
            //of your application so we are gonna be able to overwrite that database with our database.
            this.getReadableDatabase();

            try {

                copyDataBase();

            } catch (IOException e) {

                throw new Error("Error copying database");

            }
        }

    }

    private boolean checkDataBase(){

        SQLiteDatabase checkDB = null;

        try{
            String myPath = DATABASE_PATH + DATABASE_NAME;
            checkDB = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READONLY);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                SQLiteDatabase.deleteDatabase(new File(myPath));
            }
        }catch(SQLiteException e){

            //database does't exist yet.

        }

        if(checkDB != null){

            checkDB.close();

        }

        return checkDB != null;
    }

    private void copyDataBase() throws IOException{

        //Open your local db as the input stream
        InputStream myInput = myContext.getAssets().open(DATABASE_NAME+".db");

        // Path to the just created empty db
        String outFileName = DATABASE_PATH + DATABASE_NAME;

        //Open the empty db as the output stream
        OutputStream myOutput = new FileOutputStream(outFileName);

        //transfer bytes from the inputfile to the outputfile
        byte[] buffer = new byte[1024];
        int length;
        while ((length = myInput.read(buffer))>0){
            myOutput.write(buffer, 0, length);
        }

        //Close the streams
        myOutput.flush();
        myOutput.close();
        myInput.close();

    }

    public void openDataBase() throws SQLException {

        //Open the database
        String myPath = DATABASE_PATH + DATABASE_NAME;
        myDatabase = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READONLY);

    }



}
