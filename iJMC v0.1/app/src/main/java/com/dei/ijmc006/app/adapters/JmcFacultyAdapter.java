package com.dei.ijmc006.app.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.dei.ijmc006.app.R;
import com.dei.ijmc006.app.model.FacultyModel;

import java.util.ArrayList;

/**
 * Created by user on 10/1/2014.
 */
public class JmcFacultyAdapter extends BaseAdapter {
    ArrayList<FacultyModel> facultyList;
    Context context;

    public JmcFacultyAdapter (Context context, ArrayList<FacultyModel> facultyList){
        this.facultyList = facultyList;
        this.context = context;
    }

    @Override
    public int getCount() {
        return facultyList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        FacultyModel faculty = this.facultyList.get(position);

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.faculty, null);

//        ImageView facultyImage = (ImageView) view.findViewById(R.id.faculty_image);
//        facultyImage.setImageDrawable();

        TextView facultyFname = (TextView) view.findViewById(R.id.faculty_fname);
        facultyFname.setText(faculty.facultyFname);

        TextView facultyMname = (TextView) view.findViewById(R.id.faculty_mname);
        facultyMname.setText(faculty.facultyMname);

        TextView facultyLname = (TextView) view.findViewById(R.id.faculty_lname);
        facultyLname.setText(faculty.facultyLname);

        TextView facultySfx = (TextView) view.findViewById(R.id.faculty_suffix);
        facultySfx.setText(faculty.facultySfx);

        return view;
    }
}
