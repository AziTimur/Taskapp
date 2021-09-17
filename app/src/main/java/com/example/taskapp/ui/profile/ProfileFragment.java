 package com.example.taskapp.ui.profile;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;


import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.taskapp.R;
import com.example.taskapp.databinding.FragmentProfileBinding;
import com.example.taskapp.ui.Prefs;

import org.jetbrains.annotations.NotNull;


public class ProfileFragment extends Fragment {
  private   FragmentProfileBinding binding;
    int SELECT_PICTURE = 2;
   private ImageView image;
   private EditText editText;
   private Prefs prefs;

    @Override
    public void onCreate(@Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        prefs=new Prefs(requireContext());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment\
        binding=FragmentProfileBinding.inflate(inflater, container, false);
        return binding.getRoot();


    }

    @Override
    public void onViewCreated(@NotNull View view, @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        image = view.findViewById(R.id.IVPreviewImage);
        editText.setText(prefs.getName());
        image.setImageURI(prefs.getImageUri());
        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                choiser();
            }
        });

    }

    @Override
    public void onStop() {
        super.onStop();
        prefs.saveName(editText.getText().toString());
    }


    public void choiser(){
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent, SELECT_PICTURE);
    }
    ;

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
            if (requestCode == SELECT_PICTURE) {
                Uri selectedImageUri = data.getData();
                Log.e("test2", " =" + selectedImageUri);
                if (null != selectedImageUri) {
                    image.setImageURI(selectedImageUri);
                    prefs.saveImageUri(selectedImageUri);
                }

            }
        }

}

