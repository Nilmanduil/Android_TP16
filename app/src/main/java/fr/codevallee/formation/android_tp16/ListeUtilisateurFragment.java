package fr.codevallee.formation.android_tp16;

import android.app.Activity;
import android.app.FragmentTransaction;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ListeUtilisateurFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ListeUtilisateurFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ListeUtilisateurFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    OnUserSelectedListener mCallback;

    public interface OnUserSelectedListener {
        public void onUserSelected(int position);
    }

    public ListeUtilisateurFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ListeUtilisateurFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ListeUtilisateurFragment newInstance(String param1, String param2) {
        ListeUtilisateurFragment fragment = new ListeUtilisateurFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_liste_utilisateur, container, false);

        final String[] utilisateurs = {"William Wallace", "Jeanne d'Arc", "Arthur Pendragon", "Nostradamus", "Galileo Galilei", "Jeanne Poisson", "William Shakespeare", "Lewis Caroll", "Wolfgang Amadeus Mozart", "Eugène Delacroix"};
        ArrayList<String> utilisateursArray = new ArrayList<>(utilisateurs.length);
        for (String utilisateur : utilisateurs) {
            utilisateursArray.add(utilisateur);
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(ListeUtilisateurFragment.super.getContext(), android.R.layout.simple_list_item_1, utilisateursArray);
        ListView usersList = (ListView) view.findViewById(R.id.users_list);
        usersList.setAdapter(adapter);

        usersList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                mCallback.onUserSelected(position);

                UtilisateurFragment userFragment = new UtilisateurFragment();

                Bundle args = new Bundle();
                args.putString("userName", utilisateurs[position]);
                userFragment.setArguments(args);

                FragmentTransaction transaction = getFragmentManager().beginTransaction();

                if (getFragmentManager().findFragmentById(R.id.utilisateurFragment) != null) {
                    transaction.replace(R.id.utilisateurFragment, userFragment);
                } else {
                    transaction.replace(R.id.fragment_container, userFragment);
                }

                transaction.addToBackStack(null);
                transaction.commit();
            }
        });

        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    /*@Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }*/

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        // This makes sure that the container activity has implemented
        // the callback interface. If not, it throws an exception
        try {
            mCallback = (OnUserSelectedListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnUserSelectedListener");
        }
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
