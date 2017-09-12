package com.kevin.tech.kevinsummary.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.kevin.tech.kevinsummary.R;
import com.kevin.tech.kevinsummary.constants.Constants;

/**
 * Created by <a href="http://blog.csdn.net/student9128">Kevin</a> for Project KevinSummary on 2017/9/8.
 * <h3>Description:</h3>
 * <div>
 * <div/>
 */


public class ThirdFragment extends Fragment {
    public static ThirdFragment newInstance(String s) {
        ThirdFragment fragment = new ThirdFragment();
        Bundle bundle = new Bundle();
        bundle.putString(Constants.ARGS, s);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_android, container, false);
        TextView textView = (TextView) view.findViewById(R.id.text);
        Bundle bundle = getArguments();

        String string = bundle.getString(Constants.ARGS);
        textView.setText(string);
        return view;
    }
}
