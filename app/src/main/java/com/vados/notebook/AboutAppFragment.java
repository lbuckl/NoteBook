package com.vados.notebook;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class AboutAppFragment extends Fragment {
    private TextView textView_version;
    private TextView textView_author;
    private final String AppVersion = "2.1";
    private final String Author = "Молчанов В.Н.";

    public AboutAppFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_about_app, container, false);
        textView_version = view.findViewById(R.id.textView_versionVal);
        textView_author = view.findViewById(R.id.textView_authorVal);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        textView_version.setText(AppVersion);
        textView_author.setText(Author);
        super.onViewCreated(view, savedInstanceState);
    }
}