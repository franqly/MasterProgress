package com.sellersbit.masterprogress;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by moreyd_2 on 5/11/2015.
 */
public class SkillAdapter extends ArrayAdapter<Skill> {


    private final Context mContext;
    private final MainActivity mainActivity;
    private ArrayList<Skill> mSkills;

    public SkillAdapter(Context applicationContext, MainActivity mainActivity, ArrayList<Skill> skills) {
        super(applicationContext, 0);
        mContext = applicationContext;
        this.mainActivity = mainActivity;
        mSkills = skills;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        if(convertView == null){
            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.list_item_skill, parent, false);
        }

        TextView title = (TextView)convertView.findViewById(R.id.listItemSkill_title);
        title.setText(mSkills.get(position).getTitle());



        ImageButton button = (ImageButton)convertView.findViewById(R.id.listItemSkill_buttonAddTime);
        LinearLayout buttonLayout = (LinearLayout)convertView.findViewById(R.id.listItemSkill_buttonLayout);
        buttonLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mainActivity.showEnterTimeDialog(mSkills.get(position));
            }
        });



        return convertView;
    }

    @Override
    public int getCount() {
        return mSkills.size();
    }
}
