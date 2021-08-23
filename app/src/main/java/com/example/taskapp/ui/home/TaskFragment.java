package com.example.taskapp.ui.home;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.taskapp.R;

import org.jetbrains.annotations.NotNull;

import java.util.Calendar;
import java.util.Date;


public class TaskFragment extends Fragment {

    private EditText editText;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_task, container, false);

    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        editText = view.findViewById(R.id.ediText);
        view.findViewById(R.id.btnSave).setOnClickListener(v -> {
            save();
        });

    }

    private void save() {
        String text = editText.getText().toString();
        if(text.isEmpty()){
            return;
        }
       Date date = Calendar.getInstance().getTime();
        String strDate = date.toString();

        Task task = new Task(text, strDate);
        Bundle bundle = new Bundle();
        bundle.putSerializable("text", task);
        getParentFragmentManager().setFragmentResult("rk_task", bundle);
        close();
    }

    private void close() {
        NavController navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment_activity_main);
        navController.navigateUp();
    }
}