package com.dei.ijmc006.app.helper;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import com.dei.ijmc006.app.config.Config;
import com.dei.ijmc006.app.model.ContentModel;
import com.dei.ijmc006.app.model.DepartmentModel;
import com.dei.ijmc006.app.model.StudentModel;

import java.util.ArrayList;

/**
 * Created by user on 9/7/2014.
 */
public class Queries {

    public static String getStudentId(SQLiteDatabase sqLiteDB, DatabaseHandler dbHandler)
    {
        String studentId = "";

        sqLiteDB = dbHandler.getReadableDatabase();
        Cursor mCursor = sqLiteDB.rawQuery("SELECT * FROM " + DatabaseHandler.studentsTbl.toString(), null);

        mCursor.moveToFirst();
        if(!mCursor.isAfterLast())
        {
            do {
                studentId = mCursor.getString(1);
            } while (mCursor.moveToNext());
        }

        return studentId;
    }

    public static StudentModel getStudent(SQLiteDatabase sqLiteDB, DatabaseHandler dbHandler)
    {
        StudentModel studentModel = null;

        sqLiteDB = dbHandler.getReadableDatabase();
        Cursor mCursor = sqLiteDB.rawQuery("SELECT * FROM " + DatabaseHandler.studentsTbl.toString(), null);

        mCursor.moveToFirst();
        if (!mCursor.isAfterLast())
        {
            do
            {
                studentModel = new StudentModel();

                studentModel.studId = mCursor.getString(1);
                studentModel.studentFname = mCursor.getString(2);
                studentModel.studentMname = mCursor.getString(3);
                studentModel.studentLname = mCursor.getString(4);
                studentModel.deptId = mCursor.getInt(5);

            } while (mCursor.moveToNext());
        }

        mCursor.close();
        dbHandler.close();

        return studentModel;
    }

    public static String getJMCProf(SQLiteDatabase sqLiteDb, DatabaseHandler dbHandler)
    {
        String jmcProf = "";
        Config config;

        sqLiteDb = dbHandler.getReadableDatabase();
        Cursor mCursor = sqLiteDb.rawQuery("SELECT * FROM `contents` WHERE `content_type` = 'JMC Profile'", null);

        mCursor.moveToFirst();
        if (!mCursor.isAfterLast())
        {
            do
            {
                jmcProf = mCursor.getString(2);

            } while (mCursor.moveToNext());
        }

        mCursor.close();
        dbHandler.close();

        return jmcProf;
    }

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

    public static void InsertStudents(SQLiteDatabase sqLiteDB, DatabaseHandler dbHandler, StudentModel studentModel) {
        Log.e("INSERTING STUDENTS", "##COMMENT##");
        sqLiteDB = dbHandler.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("stud_idnum", studentModel.getStudentId());
        values.put("stud_fname", studentModel.getStudentFname());
        values.put("stud_mname", studentModel.getStudentMname());
        values.put("stud_lname", studentModel.getStudentLname());
        values.put("dept_id", studentModel.getDeptId());

        sqLiteDB.insert(DatabaseHandler.studentsTbl, null, values);
        sqLiteDB.close();
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
        Log.e("DEPT", values.getAsString("dept_id"));
        values.put("dept_title", department.getDeptTitle());
        values.put("dept_desc", department.getDeptDesc());

        sqLiteDB.insert(DatabaseHandler.departmentsTbl, null, values);
        sqLiteDB.close();
    }


}
