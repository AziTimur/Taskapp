package com.example.taskapp.ui.home;

import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.taskapp.App;
import com.example.taskapp.R;
import com.example.taskapp.databinding.FragmentTaskBinding;

import org.jetbrains.annotations.NotNull;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;


public class TaskFragment extends Fragment {

    FragmentTaskBinding binding;
    private Task task;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentTaskBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setTask();
        view.findViewById(R.id.btnSave).setOnClickListener(v -> {
            save();
        });

    }

    private void setTask() {
        if (requireArguments().getSerializable("task") != null) {
            task = (Task) requireArguments().getSerializable("task");
            binding.ediText.setText(task.getTitle());

        }
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void save() {
        String text = binding.ediText.getText().toString();
        if (text.isEmpty()) {
            return;
        }
        if (task == null) {
            long date = System.currentTimeMillis();
            ZonedDateTime dateTime = Instant.ofEpochMilli(date).atZone(ZoneId.of("Asia/Bishkek"));
            String form = dateTime.format(DateTimeFormatter.ofPattern("HH:mm dd MMM yyyy"));
            task = new Task(text, form);
            App.getAppDataBase().taskDao().insert(task);
        } else {
            task.setTitle(text);
            App.getAppDataBase().taskDao().update(task);

        }


        Bundle bundle = new Bundle();
        bundle.putSerializable("task", task);
        getParentFragmentManager().setFragmentResult("rk_task", bundle);
        close();
    }

    private void close() {
        NavController navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment_activity_main);
        navController.navigateUp();
    }
}