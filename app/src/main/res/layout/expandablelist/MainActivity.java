package com.vinay.expandablelist;

import android.app.Activity;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends Activity implements
        SearchView.OnQueryTextListener, SearchView.OnCloseListener{

    private SearchView search;
    private com.vinay.expandablelist.Adapter.MyListAdapter listAdapter;
    private ExpandableListView myList;
    private ArrayList<com.vinay.expandablelist.Group> groupList = new ArrayList<com.vinay.expandablelist.Group>();
   private ArrayList<Item> itemList = new ArrayList<Item>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        search = (SearchView) findViewById(R.id.search);
        search.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        search.setIconifiedByDefault(false);
        search.setOnQueryTextListener(this);
        search.setOnCloseListener(this);

        displayList();//display the list
       myList.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {

            @Override
            public boolean onChildClick(ExpandableListView listview, View view,
                                        int groupPos, int childPos, long id) {
                /*Toast.makeText(getApplicationContext(),
                        "You clicked : " + listAdapter.getChild(groupPos,childPos),
                        Toast.LENGTH_SHORT).show();*/
                Toast.makeText(com.vinay.expandablelist.MainActivity.this, ""+ ((TextView) view.findViewById(R.id.name)).getText(), Toast.LENGTH_SHORT).show();
               String select= (String) ((TextView) view.findViewById(R.id.name)).getText();
               // startActivity(new Intent(MainActivity.this,Anemia.class));
               if(select.equals("Anemia")){
                   startActivity(new Intent(com.vinay.expandablelist.MainActivity.this,Anemia.class));
               }
                if(select.equals("Flood")){
                    startActivity(new Intent(com.vinay.expandablelist.MainActivity.this,Flood.class));
                }
               if(select.replaceAll("\\s","").equalsIgnoreCase("ElectricShock")){
                    startActivity(new Intent(com.vinay.expandablelist.MainActivity.this, com.vinay.expandablelist.Electric.class));
                }
                if(select.replaceAll("\\s","").equalsIgnoreCase("CardiacArrest")){
                    startActivity(new Intent(com.vinay.expandablelist.MainActivity.this,Cardiac.class));
                }
                if(select.replaceAll("\\s","").equalsIgnoreCase("HeartBlock")){
                    startActivity(new Intent(com.vinay.expandablelist.MainActivity.this,Heart.class));
                }
                if(select.equals("Rabies")){
                    startActivity(new Intent(com.vinay.expandablelist.MainActivity.this,Rabies.class));
                }
                if(select.equals("Malaria")){
                    startActivity(new Intent(com.vinay.expandablelist.MainActivity.this, com.vinay.expandablelist.Malaria.class));
                }
                if(select.replaceAll("\\s","").equalsIgnoreCase("ChickenPox")){
                    startActivity(new Intent(com.vinay.expandablelist.MainActivity.this,Pox.class));
                }
                if(select.replaceAll("\\s","").equalsIgnoreCase("EarInfection")){
                    startActivity(new Intent(com.vinay.expandablelist.MainActivity.this,Ear.class));
                }
                return true;
            }
        });

    }

    private void expandAll() {
        int count = listAdapter.getGroupCount();
        for (int i = 0; i < count; i++){
            myList.expandGroup(i);
        }
    }

    //method to expand all groups
    private void displayList() {
        loadSomeData();
        myList = (ExpandableListView) findViewById(R.id.expandableList);
        listAdapter = new com.vinay.expandablelist.Adapter.MyListAdapter(com.vinay.expandablelist.MainActivity.this, groupList);
        myList.setAdapter(listAdapter);
    }

    private void loadSomeData() {

        ArrayList<Item> itemList = new ArrayList<Item>();
        Item item = new Item("Anemia");
        itemList.add(item);
        com.vinay.expandablelist.Group group = new com.vinay.expandablelist.Group("Blood", itemList);
        groupList.add(group);

        itemList = new ArrayList<Item>();
        item = new Item("Flood");
        itemList.add(item);
        item = new Item("Electric Shock");
        itemList.add(item);
        group = new com.vinay.expandablelist.Group("Environmental", itemList);
        groupList.add(group);

        itemList = new ArrayList<Item>();
        item = new Item("Cardiac arrest");
        itemList.add(item);
        item = new Item("Heart block");
        itemList.add(item);
        group = new com.vinay.expandablelist.Group("Heart", itemList);
        groupList.add(group);

        itemList = new ArrayList<Item>();
        item = new Item("Rabies");
        itemList.add(item);
        item = new Item("Malaria");
        itemList.add(item);
        item = new Item("Chicken Pox");
        itemList.add(item);
        item = new Item("Ear Infection");
        itemList.add(item);
        group = new com.vinay.expandablelist.Group("Infectious Disease", itemList);
        groupList.add(group);

        itemList = new ArrayList<Item>();
        item = new Item("Nose bleed");
        itemList.add(item);
        item = new Item("Bite");
        itemList.add(item);
        item = new Item("Bone fracture");
        itemList.add(item);
        item = new Item("Burns");
        itemList.add(item);
        item = new Item("Head injury");
        itemList.add(item);
        item = new Item("Wound");
        itemList.add(item);
        group = new com.vinay.expandablelist.Group("Injury", itemList);
        groupList.add(group);

        itemList = new ArrayList<Item>();
        item = new Item("Drowning");
        itemList.add(item);
        item = new Item("Asthma");
        itemList.add(item);
        item = new Item("Pneumonia");
        itemList.add(item);
        item = new Item("Respiratory failure");
        itemList.add(item);
        group = new com.vinay.expandablelist.Group("Lungs", itemList);
        groupList.add(group);

        itemList = new ArrayList<Item>();
        item = new Item("Poisoning");
        itemList.add(item);
        item = new Item("Food Poisoning");
        itemList.add(item);
        group = new com.vinay.expandablelist.Group("Toxicological", itemList);
        groupList.add(group);

        itemList = new ArrayList<Item>();
        item = new Item("Skin disease");
        itemList.add(item);
        item = new Item("Spinal cord Injury");
        itemList.add(item);
        group = new com.vinay.expandablelist.Group("Other", itemList);
        groupList.add(group);

    }

    @Override
    public boolean onClose() {
        listAdapter.filterData("");
        expandAll();
        return false;
    }

    @Override
    public boolean onQueryTextChange(String query) {
        listAdapter.filterData(query);
        expandAll();
        return false;
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        listAdapter.filterData(query);
        expandAll();
        return false;
    }
}
