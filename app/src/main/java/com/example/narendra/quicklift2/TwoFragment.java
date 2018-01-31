package com.example.narendra.quicklift2;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class TwoFragment extends Fragment implements View.OnClickListener {

    private OnFragmentInteractionListener mListener;
    private static View view;
    private static Button ride,confirm,bike,car,shareCar,auto,shareAuto,rickshaw,shareRickshaw;
    private MainActivity mainActivity;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void onClick(View view) {

        if(view.getId() == R.id.bike){
            onDetach();
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_two, container, false);
        bike = view.findViewById(R.id.bike);
        bike.setOnClickListener(this);
        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
          //  throw new RuntimeException(context.toString()
                //    + " must implement OnFragmentInteractionListener");
        }


    }

    @Override
    public void onDetach() {
        super.onDetach();
        this.getView().setVisibility(View.GONE);
        Log.i("onDetach","Detaching Fragment");
        mListener = null;
    }

    public interface OnFragmentInteractionListener {

    }
}
