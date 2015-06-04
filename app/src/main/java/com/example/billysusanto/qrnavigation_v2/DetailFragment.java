package com.example.billysusanto.qrnavigation_v2;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.billysusanto.qrnavigation_v2.dummy.DummyContent;

import java.lang.reflect.Field;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link DetailFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link DetailFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DetailFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private static final String ARG_PARAM3 = "param3";
    // TODO: Rename and change types of parameters
    private int mParam1;
    private String mParam2 = "";
    private String mParam3 = "";
    private ImageView imageView;

    public static TextView tv1;
    public static TextView tv2;
    public Button nav;
    private OnFragmentInteractionListener mListener;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment DetailFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static Fragment newInstance(int param1, String param2, String param3) {
        Fragment fragment = new DetailFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        args.putString(ARG_PARAM3, param3);
        fragment.setArguments(args);
        return fragment;
    }

    public DetailFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getInt(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
            mParam3 = getArguments().getString(ARG_PARAM3);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_detail, container, false);
        tv1 = (TextView)v.findViewById(R.id.detailText1);
        setText1(mParam2);

        imageView = (ImageView) v.findViewById(R.id.imageView);
        if(mParam2.equalsIgnoreCase("Burger Lapar")){
            imageView.setImageResource(R.drawable.burgergantisementara);
        }
        else if(mParam2.equalsIgnoreCase("Ayam Pedas")){
            imageView.setImageResource(R.drawable.nasiayampedas);
        }
        else if(mParam2.equalsIgnoreCase("Nasi Uduk Enak")){
            imageView.setImageResource(R.drawable.nasiuduk);
        }
        else if(mParam2.equalsIgnoreCase("Minuman Segarrr")){
            imageView.setImageResource(R.drawable.minumansegar);
        }
        else if(mParam2.equalsIgnoreCase("Semua Ada")){
            imageView.setImageResource(R.drawable.anyfood);
        }
        else if(mParam2.equalsIgnoreCase("Kentang Rebus")){
            imageView.setImageResource(R.drawable.kentangrebus);
        }

        tv2 = (TextView)v.findViewById(R.id.detailText2);
        setText2(mParam3+DummyContent.getItem(mParam1).alamat);

        nav = (Button)v.findViewById(R.id.detailMaps);
        nav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent maps = new Intent(android.content.Intent.ACTION_VIEW,
                Uri.parse("http://maps.google.com/maps?saddr="+
                        DummyContent.getItem(ItemFragment.position).sLat+","+DummyContent.getItem(ItemFragment.position).sLong+
                        "&daddr="+
                        DummyContent.getItem(ItemFragment.position).dLat+","+DummyContent.getItem(ItemFragment.position).dLong));
                maps.setClassName("com.google.android.apps.maps", "com.google.android.maps.MapsActivity");
                startActivity(maps);
            }
        });
        return v;
    }

    public static void setText1(String text){
        tv1.setText(text);
    }
    public static void setText2(String text){
        tv2.setText(text);
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnFragmentInteractionListener) activity;

        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        try {
            Field childFragmentManager = Fragment.class.getDeclaredField("mChildFragmentManager");
            childFragmentManager.setAccessible(true);
            childFragmentManager.set(this, null);

        } catch (NoSuchFieldException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        public void onFragmentInteraction(Uri uri);
    }

}
