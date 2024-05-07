package com.example.sqlite2;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ListView listViewContacts;
    private List<Contact> contactList;
    private ContactListAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        DatabaseHandler db = new DatabaseHandler(this);

        /**
         * CRUD Operations
         * */
        // Inserting Contacts
        Log.d("Insert: ", "Inserting ..");
        db.addContact(new Contact('1', "Ravi", "9100000000"));
        db.addContact(new Contact('2', "Srinivas", "9199999999"));
        db.addContact(new Contact('3', "Tommy", "9522222222"));
        db.addContact(new Contact('4', "Karthik", "9533333333"));

        // Reading all contacts
        Log.e("Reading: ", "Reading all contacts..");
        List<Contact> contacts = db.getAllContacts();

        for (Contact cn : contacts) {
            String log = "Id: " + cn.getId() + " ,Name: " + cn.getName() + ",Phone: " + cn.getPhoneNumber();
            // Writing Contacts to log
            Log.e("Name: ", log);
        }
        listViewContacts = findViewById(R.id.listViewContacts);
        contactList = new ArrayList<>();


        // Populate contactList from database
        contactList.addAll(db.getAllContacts());

        adapter = new ContactListAdapter(this, R.layout.list_item_contact, contactList);
        listViewContacts.setAdapter(adapter);

        // Set long-click listener to delete a contact
        listViewContacts.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                Contact contactToDelete = contactList.get(position);
                // Delete the contact from the database
                db.deleteContact(contactToDelete);
                // Remove the contact from the list
                contactList.remove(position);
                adapter.notifyDataSetChanged();
                return true;
            }
        });
    }
}