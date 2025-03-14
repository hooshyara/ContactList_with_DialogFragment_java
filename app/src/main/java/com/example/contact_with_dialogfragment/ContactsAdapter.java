package com.example.contact_with_dialogfragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ContactsAdapter extends RecyclerView.Adapter<ContactsAdapter.ContactViewHolder> {
    private ArrayList<String> contacts = new ArrayList<>();
    private static final String TAG = "ContactsAdapter";
    private ItemEventListener itemEventListener;

    public ContactsAdapter(ItemEventListener itemEventListener) {
        this.itemEventListener = itemEventListener;
        contacts.add("Ruthann Trustrie");
        contacts.add("Peadar Dawtrey");
        contacts.add("elipe Bradtke");
        contacts.add("Claude Crissil");
        contacts.add("Jacky Girardeau");
        contacts.add("Rubia Dominguez");
        contacts.add("Michaela Churchley");
        contacts.add("Harvey Pentelow");
        contacts.add("Neilla Langton");
        contacts.add("Marco Greaves");
        contacts.add("Liz Batchley");
        contacts.add("Lamond Littlepage");
        contacts.add("Malina Weir");
        contacts.add("Tomlin Lenchenko");
        contacts.add("Hy Pavelin");
        contacts.add("Jenelle Palin");
        contacts.add("Damon Knewstubb");
        contacts.add("Alex Ivanusyev");
        contacts.add("Hamil Callery");
        contacts.add("Karol Syer");
    }

    public void addNewContact(String fullName) {
        Log.d("ContactsAdapter", "Contacts: " + contacts.get(0));

        contacts.add(0, fullName);
        notifyItemInserted(0);


//        Toast.makeText(this, contact, Toast.LENGTH_LONG).show();
    }

    public void updateContact(String fullName, int position) {

        contacts.set(position, fullName);
        notifyItemChanged(position);

    }

    @NonNull
    @Override
    public ContactViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_contact, parent, false);
        return new ContactViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ContactViewHolder holder, int position) {
        Log.d("ContactsAdapter", "Contacts: " + contacts);
        holder.bindContact(contacts.get(position));
    }

    @Override
    public int getItemCount() {
        return contacts.size();
    }

    public class ContactViewHolder extends RecyclerView.ViewHolder {
        private TextView firstCharacterTv;
        private TextView fullnameTv;

        public ContactViewHolder(@NonNull View itemView) {
            super(itemView);
            firstCharacterTv = itemView.findViewById(R.id.tv_contact_firstCharacter);
            fullnameTv = itemView.findViewById(R.id.tv_contact_fullName);
        }

        public void bindContact(String fullname) {
            fullnameTv.setText(fullname);
            firstCharacterTv.setText(fullname.substring(0, 1));
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    itemEventListener.onItemClick(fullname, getAbsoluteAdapterPosition());
//                    Toast.makeText(view.getContext(), fullname, Toast.LENGTH_SHORT).show();
                }
            });
            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    contacts.remove(getAbsoluteAdapterPosition());
                    notifyItemRemoved(getAbsoluteAdapterPosition());
                    return false;
                }
            });
        }
    }

    public interface ItemEventListener {
        void onItemClick(String fullName, int position);
    }
}
