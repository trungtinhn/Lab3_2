package com.example.sqlite2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class ContactListAdapter extends ArrayAdapter<Contact> {
    private Context mContext;
    private int mResource;

    public ContactListAdapter(Context context, int resource, List<Contact> objects) {
        super(context, resource, objects);
        mContext = context;
        mResource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        convertView = inflater.inflate(mResource, parent, false);

        TextView textViewName = convertView.findViewById(R.id.textViewName);
        TextView textViewPhoneNumber = convertView.findViewById(R.id.textViewPhoneNumber);

        Contact contact = getItem(position);

        if (contact != null) {
            textViewName.setText(contact.getName());
            textViewPhoneNumber.setText(contact.getPhoneNumber());
        }

        return convertView;
    }

}
