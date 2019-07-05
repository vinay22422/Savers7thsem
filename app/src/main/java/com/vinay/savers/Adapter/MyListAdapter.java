package com.vinay.savers.Adapter;

import android.widget.BaseExpandableListAdapter;

import java.util.ArrayList;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.vinay.savers.Group;
import com.vinay.savers.Item;
import com.vinay.savers.R;

public class MyListAdapter extends BaseExpandableListAdapter {

    private Context context;
    private ArrayList<Group> groupList;
    private ArrayList<Group> originalList;

    public MyListAdapter(Context context, ArrayList<Group> groupList) {
        this.context = context;
        this.groupList = new ArrayList<Group>();
        this.groupList.addAll(groupList);
        this.originalList = new ArrayList<Group>();
        this.originalList.addAll(groupList);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        ArrayList<Item> itemList = groupList.get(groupPosition).getItemList();
        return itemList.get(childPosition);
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild,
                             View view, ViewGroup parent) {

        Item item = (Item) getChild(groupPosition, childPosition);
        if (view == null) {
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = layoutInflater.inflate(R.layout.child, null);
        }

        TextView name = (TextView) view.findViewById(R.id.name);
        name.setText(item.getName().trim());
        return view;
    }

    @Override
    public int getChildrenCount(int groupPosition) {

        ArrayList<Item> itemList = groupList.get(groupPosition).getItemList();
        return itemList.size();

    }

    @Override
    public Object getGroup(int groupPosition) {
        return groupList.get(groupPosition);
    }

    @Override
    public int getGroupCount() {
        return groupList.size();
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isLastChild, View view,
                             ViewGroup parent) {

        Group group = (Group) getGroup(groupPosition);
        if (view == null) {
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = layoutInflater.inflate(R.layout.group_rwp, null);
        }

        TextView heading = (TextView) view.findViewById(R.id.heading);
        ImageView image=(ImageView)view.findViewById(R.id.image);
        heading.setText(group.getName().trim());
        if(groupPosition==0)
            image.setImageResource(R.drawable.blood);
        if(groupPosition==1)
            image.setImageResource(R.drawable.envi);
        if(groupPosition==2)
            image.setImageResource(R.drawable.heart);
        if(groupPosition==3)
            image.setImageResource(R.drawable.infect);
        if(groupPosition==4)
            image.setImageResource(R.drawable.injury);
        if(groupPosition==5)
            image.setImageResource(R.drawable.lungs);
        if(groupPosition==6)
            image.setImageResource(R.drawable.toxic);
        if(groupPosition==7)
            image.setImageResource(R.drawable.others);


        return view;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

    public void filterData(String query) {

        query = query.toLowerCase();
        Log.v("MyListAdapter", String.valueOf(groupList.size()));
        groupList.clear();

        if (query.isEmpty()) {
            groupList.addAll(originalList);
        } else {

            for (Group group : originalList) {

                ArrayList<Item> itemList = group.getItemList();
                ArrayList<Item> newList = new ArrayList<Item>();
                for (Item item : itemList) {
                    if (item.getName().toLowerCase().contains(query)) {
                        newList.add(item);
                    }
                }
                if (newList.size() > 0) {
                    Group nGroup = new Group(group.getName(), newList);
                    groupList.add(nGroup);
                }
            }
        }

        Log.v("MyListAdapter", String.valueOf(groupList.size()));
        notifyDataSetChanged();

    }
}