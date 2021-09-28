package com.example.taskapp.ui.profile;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.example.taskapp.App;
import com.example.taskapp.R;
import com.example.taskapp.databinding.FragmentProfileBinding;
import com.example.taskapp.ui.Prefs;

import org.jetbrains.annotations.NotNull;


public class ProfileFragment extends Fragment {

    private FragmentProfileBinding binding;
    int SELECT_PICTURE = 2;

    @Override
    public void onCreate(@Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu(@NonNull @NotNull Menu menu, @NonNull @NotNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.optinos_menu, menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull @NotNull MenuItem item) {
        if (item.getItemId() == R.id.clear_all) {
            App.getPrefs().clearSetting();
            binding.nameHint.setText("");
            binding.email.setText("");
            binding.phone.setText("");
            binding.address.setText("");
            binding.dateBirth.setText("");
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onStart() {
        super.onStart();
        if (App.getPrefs().getImageUri() != null) {
            Glide.with(binding.getRoot())
                    .load(App.getPrefs().getImageUri())
                    .circleCrop()
                    .into(binding.IVPreviewImage);
        }
       etActions();
    }

    private void etActions() {
        if (App.getPrefs().getName() != null) {
            binding.nameHint.setText(App.getPrefs().getName());
            binding.email.setText(App.getPrefs().getEmail());
            binding.phone.setText(App.getPrefs().getPhone());
            binding.dateBirth.setText(App.getPrefs().getBirthDay());
            binding.address.setText(App.getPrefs().getAddress());
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentProfileBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NotNull View view, @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.IVPreviewImage.setOnClickListener(v -> choiser());

    }

    @Override
    public void onStop() {
        super.onStop();
        /*binding.nameHint.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE){
                    App.getPrefs().saveName(binding.nameHint.getText().toString());
                }
                return false;
            }
        });*/
        App.getPrefs().saveName(binding.nameHint.getText().toString());
         App.getPrefs().saveEmail(binding.email.getText().toString());
         App.getPrefs().savePhone(binding.phone.getText().toString());
         App.getPrefs().saveBirthDay(binding.dateBirth.getText().toString());
         App.getPrefs().saveAddress(binding.address.getText().toString());

    }


    public void choiser() {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent, SELECT_PICTURE);
    }

    ;

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == SELECT_PICTURE && resultCode == Activity.RESULT_OK) {
            Uri selectedImageUri = data.getData();
            Log.e("test2", " =" + selectedImageUri);

            if (null != selectedImageUri) {
                Glide.with(binding.getRoot())
                        .load(selectedImageUri)
                        .circleCrop()
                        .into(binding.IVPreviewImage);
                App.getPrefs().saveImageUri(selectedImageUri);
            }

        }
    }

}

