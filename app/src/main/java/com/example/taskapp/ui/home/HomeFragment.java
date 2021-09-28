package com.example.taskapp.ui.home;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentResultListener;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.example.taskapp.App;
import com.example.taskapp.R;
import com.example.taskapp.databinding.FragmentHomeBinding;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.jetbrains.annotations.NotNull;

public class HomeFragment extends Fragment {
    private TaskAdapter adapter;
    private FragmentHomeBinding binding;
    private int pos;

    @Override
    public void onCreate(@Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        adapter = new TaskAdapter();
        adapter.addItems(App.getAppDataBase().taskDao().getAll());
        adapter.deleteItems(App.getAppDataBase().taskDao().getAll());
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        FloatingActionButton fab = view.findViewById(R.id.fab);
        fab.setOnClickListener(view1 -> {
            openTaskFragment(null);
            pos= -1;
        });
        getParentFragmentManager().setFragmentResultListener("rk_task", getViewLifecycleOwner(), new FragmentResultListener() {
            @Override
            public void onFragmentResult(@NonNull @NotNull String requestKey, @NonNull @NotNull Bundle result) {
                Task task = (Task) result.getSerializable("task");
                if (pos==-1) adapter.addItem(task);
                else adapter.setItem(pos,task);

            }
        });
        initList();

    }

    private void initList() {
        binding.taskRV.setAdapter(adapter);
        adapter.setItemClickListener(new TaskAdapter.ItemClickListener() {
            @Override
            public void onClick(int position) {
                pos=position;
                Task task = adapter.getItem(position);
                openTaskFragment(task);
            }

            @Override
            public void onLong(int position, Task task) {
                task = adapter.getItem(position);
                showAlert(task);
            }


            private void showAlert(Task task) {
                new AlertDialog.Builder(requireActivity())
                        .setMessage("delete?")
                        .setPositiveButton("YES", (dialog, which) -> {
                            App.getAppDataBase().taskDao().delete(task);
                            adapter.removeItem(task);
                            Toast.makeText(getContext(), "deleted", Toast.LENGTH_SHORT).show();
                        })
                        .setNegativeButton("Cancel", null)
                        .show();
            }

        });

    }

    private void openTaskFragment(Task task) {
        Bundle bundle = new Bundle();
        bundle.putSerializable("task",task);
        NavController navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment_activity_main);
        navController.navigate(R.id.taskFragment,bundle);
    }

}