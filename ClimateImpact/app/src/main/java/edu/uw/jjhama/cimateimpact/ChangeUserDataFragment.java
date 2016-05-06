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

/**
 * Created by iguest on 4/22/16.
 */
public class ChangeUserDataFragment extends DialogFragment {

    private static final String TAG = "ChangeUserDataFragment";

    public ChangeUserDataFragment(){

    }
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        // Get the layout inflater
        LayoutInflater inflater = getActivity().getLayoutInflater();

        // Inflate and set the layout for the dialog
        // Pass null as the parent view because its going in the dialog layout
        builder.setView(inflater.inflate(R.layout.alter_user_info_dialog, null))
                // Add action buttons
                .setPositiveButton("submit", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        // sign in the user ...
                        Dialog f = (Dialog) dialog;

                        //EditText firstName = (EditText) f.findViewById(R.id.);

                        String firstName = ((EditText) f.findViewById(R.id.firstName)).getText().toString();
                        String lastName = ((EditText) f.findViewById(R.id.lastName)).getText().toString();
                        String zip = ((EditText) f.findViewById(R.id.zip)).getText().toString();
                        String state = ((EditText) f.findViewById(R.id.state)).getText().toString();
                        String country = ((EditText) f.findViewById(R.id.country)).getText().toString();

                        //todo: save to backend
                        //ProfileFragment g = new ProfileFragment();

                        // Supply num input as an argument.
//                        Bundle args = new Bundle();
//                        args.putString("name", firstName);
//                        //args.putString("lName",);
//                        g.setArguments(args);
                        Toast.makeText(getActivity().getApplicationContext(),"User info saved!", Toast.LENGTH_LONG).show();


//                        Bundle bundle = new Bundle();
//                        bundle.putString("lName", firstName);
//                        bundle.putString("zip", zip);
//                        bundle.putString("country", country);
//                        bundle.putString("state",state);
//                        bundle.putString("lName", lastName);

//                        getActivity().getSupportFragmentManager()
//                                .beginTransaction().replace(R.id.container, new Signin())
//                                .addToBackStack(null)
//                                .commit();



                        //Log.v(TAG, "Submit was selected \n username: "+ );
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

    public View getViewByPosition(int pos, ListView listView) {
        final int firstListItemPosition = listView.getFirstVisiblePosition();
        final int lastListItemPosition = firstListItemPosition + listView.getChildCount() - 1;

        if (pos < firstListItemPosition || pos > lastListItemPosition ) {
            return listView.getAdapter().getView(pos, null, listView);
        } else {
            final int childIndex = pos - firstListItemPosition;
            return listView.getChildAt(childIndex);
        }
    }
}
