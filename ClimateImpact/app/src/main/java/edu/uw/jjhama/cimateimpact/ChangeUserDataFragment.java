package edu.uw.jjhama.cimateimpact;

import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

/**
 * Created by iguest on 4/22/16.
 */
public class ChangeUserDataFragment extends DialogFragment {

    private static final String TAG = "ChangeUserDataFragment";
    String email;
    private DatabaseReference mDatabase;

    public ChangeUserDataFragment(){
        //required empty constructor
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        // Get the layout inflater
        LayoutInflater inflater = getActivity().getLayoutInflater();

        //get email from previous fragment
        Bundle getBundle = getArguments();
        if(getBundle.getString("email") != null) {
            email = getBundle.getString("email");
        }

        builder.setView(inflater.inflate(R.layout.alter_user_info_dialog, null))
                .setPositiveButton("submit", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        Dialog f = (Dialog) dialog;
                        final String firstName = ((EditText) f.findViewById(R.id.firstName)).getText().toString();
                        final String lastName = ((EditText) f.findViewById(R.id.lastName)).getText().toString();
                        final String zip = ((EditText) f.findViewById(R.id.zip)).getText().toString();

                        //todo: save to backend
                        mDatabase = FirebaseDatabase.getInstance().getReference().child("users").child(email);
                        mDatabase.addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot snapshot) {
                                if(!lastName.equals("")) {
                                    mDatabase.child("lName").setValue(lastName);
                                }
                                if(!firstName.equals("")) {
                                    mDatabase.child("fName").setValue(firstName);
                                }
                                if(!zip.equals("")) {
                                    mDatabase.child("zip").setValue(zip);
                                }
                            }

                            @Override
                            public void onCancelled(DatabaseError databaseError) {
                                Log.w(TAG, "loadPost:onCancelled", databaseError.toException());
                                Toast.makeText(getActivity(), "Failed to load post.",
                                        Toast.LENGTH_SHORT).show();
                            }
                        });
                        Toast.makeText(getActivity().getApplicationContext(),"User info saved!", Toast.LENGTH_LONG).show();
                    }
                })
                .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        Log.v(TAG, "Cancel was selected");
                        ChangeUserDataFragment.this.getDialog().cancel();
                    }
                });
        return builder.create();
    }
}
