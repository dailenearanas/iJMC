package info.androidhive.jsonparsing;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;

/**
 * Created by user on 9/7/2014.
 */
public class Queries {

    public static ArrayList<ContentModel> getContents(SQLiteDatabase sqliteDB, DatabaseHandler dbHandler)
    {

        ArrayList<ContentModel> models = new ArrayList<ContentModel>();
        ContentModel contentModel;

        sqliteDB = dbHandler.getReadableDatabase();
        Cursor mCursor = sqliteDB.rawQuery("SELECT * FROM " + DatabaseHandler.contentsTbl.toString(), null);

        mCursor.moveToFirst();
        if (!mCursor.isAfterLast())
        {
            do
            {
                contentModel = new ContentModel();

                contentModel.contentType = mCursor.getString(1);
                contentModel.contentBody = mCursor.getString(2);

                models.add(contentModel);

            } while (mCursor.moveToNext());
        }

        mCursor.close();
        dbHandler.close();

        return models;

    }

    public static ArrayList<DepartmentModel> getDepartment(SQLiteDatabase sqliteDB, DatabaseHandler dbHandler)
    {

        ArrayList<DepartmentModel> models = new ArrayList<DepartmentModel>();
        DepartmentModel departmentModel;

        sqliteDB = dbHandler.getReadableDatabase();
        Cursor mCursor = sqliteDB.rawQuery("SELECT * FROM " + DatabaseHandler.departmentsTbl.toString(), null);

        mCursor.moveToFirst();
        if (!mCursor.isAfterLast())
        {
            do
            {
                departmentModel = new DepartmentModel();

                departmentModel.deptId = mCursor.getInt(0);
                departmentModel.deptTitle = mCursor.getString(1);
                departmentModel.deptDesc = mCursor.getString(2);

                models.add(departmentModel);

            } while (mCursor.moveToNext());
        }

        mCursor.close();
        dbHandler.close();

        return models;
    }

    public static void InsertContent(SQLiteDatabase sqLiteDB, DatabaseHandler dbHandler, ContentModel content) {
        Log.e("INSERTING CONTENT", "##COMMENT##");
        sqLiteDB = dbHandler.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("content_type", content.getContentType());
        values.put("content_body", content.getContentBody());

        sqLiteDB.insert(DatabaseHandler.contentsTbl, null, values);
        sqLiteDB.close();
    }

    public static void InsertDepartment(SQLiteDatabase sqLiteDB, DatabaseHandler dbHandler, DepartmentModel department) {
        Log.e("INSERTING DEPARTMENT", "##COMMENT##");
        sqLiteDB = dbHandler.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("dept_id", department.getDeptId());
        values.put("dept_title", department.getDeptTitle());
        values.put("dept_desc", department.getDeptDesc());

        sqLiteDB.insert(DatabaseHandler.departmentsTbl, null, values);
        sqLiteDB.close();
    }
}
