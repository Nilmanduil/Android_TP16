package fr.codevallee.formation.android_tp16;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.TextView;

import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLEncoder;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link UtilisateurFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link UtilisateurFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class UtilisateurFragment extends Fragment {

    private OnFragmentInteractionListener mListener;

    public UtilisateurFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment UtilisateurFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static UtilisateurFragment newInstance(String param1, String param2) {
        UtilisateurFragment fragment = new UtilisateurFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_utilisateur, container, false);

        //*
        Bundle args = getArguments();
        if (args != null) {
            String userName = (String) args.get("userName");
            if (userName != null) {
                TextView text = (TextView) view.findViewById(R.id.utilisateurFragmentText);
                text.setText(userName);
            }

            String formattedUserName = URLEncoder.encode(userName.replaceAll(" ", "_"));
            WebView web = (WebView) view.findViewById(R.id.utilisateurFragmentWeb);
            web.loadUrl("https://fr.wikipedia.org/wiki/" + formattedUserName);
        }
        //*/

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
