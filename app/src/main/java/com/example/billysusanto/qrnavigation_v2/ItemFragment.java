package com.example.billysusanto.qrnavigation_v2;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Fragment;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.example.billysusanto.qrnavigation_v2.dummy.DummyContent;

import java.lang.reflect.Field;

/**
 * A fragment representing a list of Items.
 * <p/>
 * Large screen devices (such as tablets) are supported by replacing the ListView
 * with a GridView.
 * <p/>
 * Activities containing this fragment MUST implement the {@link OnFragmentInteractionListener}
 * interface.
 */
@TargetApi(Build.VERSION_CODES.HONEYCOMB)
public class ItemFragment extends Fragment implements AbsListView.OnItemClickListener {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    public static int position = 0;
    /*
    public static String []slatLang = new String [] {"-6.890388", "107.610272"};
    public static String [][]dlatLang = new String [][] {{"-6.88950", "107.61265"}, {"-6.869319", "107.604135"}, {"-6.923640" ,"107.623833"}, {"-6.923640" ,"107.623833"}, {"-6.923640" ,"107.623833"}};
    public static String [][]detail = new String [][] {
            {"Burger Lapar", "Nikmati Promo Burger Terbaru kami\n\nhanya dengan Rp. 12.999,--\n\nDapatkan di outlet terdekat kami di kota anda\n\n"},
            {"Ayam Pedas", "Bagi Penikmat Makanan Pedas,\ntelah hadir dikota anda.\n\nAyam Pedas.\n\nPromo Teh Botoh bagi 1000 Pengunjung Pertama\n\n"},
            {"Nasi Uduk Enak" ,"Nasi Uduk Gitu-Gitu Aja?\nCobain Nasi Uduk disini,\n\nNasi Uduk Enak!\n\nwalau Mahal Tapi Enak..\n\n"},
            {"Minuman Segarrrr" ,"Udara Panas, Kepanasan\nHarus Banyak Minum,\n\nMinuman Segarrrr!\n\nTersegarrr yang pernah anda rasakan\n\n"},
            {"Semua Ada" ,"Mau Makan Apa?\nMau Minum Apa?\n\nSemua Ada!\n\nHarga Terjangkau\n\n"}};

    public static String alamat[] = new String [] {
        "Jln.Asal Asal no 12\n" +
                "Bandung\n" +
                "Indonesia",
        "Jln.Cari Makan no 109\n" +
                "Bandung\n" +
                "Indonesia",
        "Jln.Laper Banget no 7\n" +
                "Bandung\n" +
                "Indonesia",
        "Jln.Haus Terus no 76\n" +
                "Bandung\n" +
                "Indonesia",
        "Jln.Tambah Baru no 100\n" +
                "Bandung\n" +
                "Indonesia"
    };
    */

    Context context = null;
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private Activity thisActivity;
    private View view;
    private OnFragmentInteractionListener mListener;

    /**
     * The fragment's ListView/GridView.
     */
    private AbsListView mListView;

    /**
     * The Adapter which will be used to populate the ListView/GridView with
     * Views.
     */
    private ListAdapter mAdapter;

    // TODO: Rename and change types of parameters
    public static ItemFragment newInstance(String param1, String param2) {
        ItemFragment fragment = new ItemFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public ItemFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

        thisActivity = getActivity();
        // TODO: Change Adapter to display your content

        for(int i=0; i<DummyContent.ITEMS.size(); i++){
            if(DummyContent.ITEMS.get(i).isNew == true){
                if(!DummyContent.ITEMS.get(i).content.contains(" ^  ")) {
                    DummyContent.ITEMS.get(i).content = " ^  " + DummyContent.ITEMS.get(i).content;
                }
            }
        }

        mAdapter = new ArrayAdapter<DummyContent.DummyItem>(thisActivity,
                android.R.layout.simple_list_item_1, android.R.id.text1, DummyContent.ITEMS);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_item, container, false);

        // Set the adapter
        mListView = (AbsListView) view.findViewById(android.R.id.list);
        ((AdapterView<ListAdapter>) mListView).setAdapter(mAdapter);

        // Set OnItemClickListener so we can be notified on item clicks
        mListView.setOnItemClickListener(this);
        if(context == null) {
            context = container.getContext();
        }
        return view;
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


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if (null != mListener) {
            // Notify the active callbacks interface (the activity, if the
            // fragment is attached to one) that an item has been selected.
            mListener.onFragmentInteraction(DummyContent.ITEMS.get(position).id);
        }
        Object obj = mListView.getAdapter().getItem(position);
        this.position = position;
        String value = obj.toString();
        AlertDialog.Builder builder = new AlertDialog.Builder(context);

        //mListView.getItemAtPosition(position).toString();

        builder.setCancelable(true);
        builder.setPositiveButton("Navigation", new MapsOnClickListener());
        builder.setNeutralButton("Details", new DetailOnClickListener());
        builder.setNegativeButton("Delete", new DeleteOnClickListener());
        builder.setTitle(DummyContent.getItem(position).content);
        if(mListView.getItemAtPosition(position).toString().equalsIgnoreCase("Burger Lapar") ||
                mListView.getItemAtPosition(position).toString().equalsIgnoreCase(" ^  Burger Lapar")) {
            builder.setIcon(R.drawable.burgericon).setMessage(DummyContent.getItem(position).detail);
        }
        else if(mListView.getItemAtPosition(position).toString().equalsIgnoreCase("Ayam Pedas") ||
                mListView.getItemAtPosition(position).toString().equalsIgnoreCase(" ^  Ayam Pedas")) {
            builder.setIcon(R.drawable.nasiayampedasicon).setMessage(DummyContent.getItem(position).detail);
        }
        else if(mListView.getItemAtPosition(position).toString().equalsIgnoreCase("Nasi Uduk Enak") ||
                mListView.getItemAtPosition(position).toString().equalsIgnoreCase(" ^  Nasi Uduk Enak") ) {
            builder.setIcon(R.drawable.nasiudukicon).setMessage(DummyContent.getItem(position).detail);
        }
        else if(mListView.getItemAtPosition(position).toString().equalsIgnoreCase("Minuman Segarrr") ||
                mListView.getItemAtPosition(position).toString().equalsIgnoreCase(" ^  Minuman Segarrr")) {
            builder.setIcon(R.drawable.minumansegaricon).setMessage(DummyContent.getItem(position).detail);
        }
        else if(mListView.getItemAtPosition(position).toString().equalsIgnoreCase("Semua Ada") ||
                mListView.getItemAtPosition(position).toString().equalsIgnoreCase(" ^  Semua Ada")) {
            builder.setIcon(R.drawable.anyfoodicon).setMessage(DummyContent.getItem(position).detail);
        }
        else if(mListView.getItemAtPosition(position).toString().equalsIgnoreCase("Kentang Rebus") ||
                mListView.getItemAtPosition(position).toString().equalsIgnoreCase(" ^  Kentang Rebus")) {
            builder.setIcon(R.drawable.kentangrebusicon).setMessage(DummyContent.getItem(position).detail);
        }
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    /**
     * The default content for this Fragment has a TextView that is shown when
     * the list is empty. If you would like to change the text, call this method
     * to supply the text it should use.
     */
    public void setEmptyText(CharSequence emptyText) {
        View emptyView = mListView.getEmptyView();

        if (emptyView instanceof TextView) {
            ((TextView) emptyView).setText(emptyText);
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
        public void onFragmentInteraction(String id);
    }

    private final class MapsOnClickListener implements
            DialogInterface.OnClickListener {
        public void onClick(DialogInterface dialog, int which) {
            Toast.makeText(context, "Choose Directions",
                    Toast.LENGTH_LONG).show();
            if(DummyContent.ITEMS.get(position).content.contains(" ^  ")) {
                DummyContent.ITEMS.get(position).content = DummyContent.ITEMS.get(position).content.substring(4);
            }
            Intent maps = new Intent(android.content.Intent.ACTION_VIEW,
                    Uri.parse("http://maps.google.com/maps?saddr="+DummyContent.getItem(position).sLat+","+DummyContent.getItem(position).sLong+
                            "&daddr="+DummyContent.getItem(position).dLat+","+DummyContent.getItem(position).dLong));
            maps.setClassName("com.google.android.apps.maps", "com.google.android.maps.MapsActivity");
            startActivity(maps);
        }
    }

    private final class DetailOnClickListener implements
            DialogInterface.OnClickListener {
        public void onClick(DialogInterface dialog, int which) {
            DummyContent.ITEMS.get(position).isNew = false;
            //String split []= DummyContent.ITEMS.get(position).content.substring(4);
            if(DummyContent.ITEMS.get(position).content.contains(" ^  ")) {
                DummyContent.ITEMS.get(position).content = DummyContent.ITEMS.get(position).content.substring(4);
            }
            MainActivity main = new MainActivity();
            ((MainActivity) getActivity()).loadDetailFragment(position,
                    DummyContent.getItem(position).sLat, DummyContent.getItem(position).sLong,
                    DummyContent.getItem(position).dLat, DummyContent.getItem(position).dLong,
                    DummyContent.getItem(position).content, DummyContent.getItem(position).detail);

            Toast.makeText(context, "Showing Details", Toast.LENGTH_LONG).show();
        }
    }

    private final class DeleteOnClickListener implements
            DialogInterface.OnClickListener {
        public void onClick(DialogInterface dialog, int which) {
            AlertDialog.Builder builder = new AlertDialog.Builder(context);

            //mListView.getItemAtPosition(position).toString();
            builder.setCancelable(true);
            builder.setPositiveButton("Delete", new ConfirmationDeleteOnClickListener());
            builder.setNeutralButton("Cancel", new CancelDeleteOnClickListener());
            builder.setTitle("Are You Sure want to Delete : \n" + DummyContent.getItem(position).content);
            AlertDialog dialog2 = builder.create();
            dialog2.show();
        }
    }

    private final class ConfirmationDeleteOnClickListener implements
            DialogInterface.OnClickListener {
        public void onClick(DialogInterface dialog, int which) {

            if(mListView.getItemAtPosition(position).toString().equalsIgnoreCase("Burger Lapar") ||
                    mListView.getItemAtPosition(position).toString().equalsIgnoreCase(" ^  Burger Lapar")){
                MainActivity.burger = false;
            }
            else if(mListView.getItemAtPosition(position).toString().equalsIgnoreCase("Kentang Rebus") ||
                    mListView.getItemAtPosition(position).toString().equalsIgnoreCase(" ^  Kentang Rebus")){
                MainActivity.kentangRebus = false;
            }
            DummyContent.ITEMS.remove(position);
            MainActivity main = new MainActivity();
            ((MainActivity) getActivity()).onNavigationDrawerItemSelected(0);
        }
    }

    private final class CancelDeleteOnClickListener implements
            DialogInterface.OnClickListener {
        public void onClick(DialogInterface dialog, int which) {
        }
    }
}

