package info.androidhive.jsonparsing;

import android.app.Activity;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper{

	public final static String dBName = "iJMC_db";
	final static String jmcContentTable = Config.CONTENT_TABLE;
	final static int DB_VERSION = 1;
	
	static Activity activity;
	
	public DatabaseHelper(Activity activity) {
		super(activity.getApplicationContext(), dBName, null, DB_VERSION);
		// TODO Auto-generated constructor stub
		this.activity = activity;
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		
		db.execSQL("CREATE TABLE IF NOT EXISTS " + jmcContentTable + " ("
	    		+ "ID INTEGER PRIMARY KEY AUTOINCREMENT, "
	    		+ "content_title VARCHAR(1000), "
	    		+ "content_body VARCHAR(1000), "
	    		+ ");");
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		db.execSQL("DROP TABLE IF EXISTS " + jmcContentTable);
	}

    public void getJmcContent(SQLiteDatabase db) {
        db.execSQL("SELECT * from ");
    }
}
