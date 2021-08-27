package com.example.taskapp.ui.onBoarding;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.example.taskapp.R;
import com.example.taskapp.databinding.ItemBoardBinding;

import org.jetbrains.annotations.NotNull;

public class BoardAdapter extends RecyclerView.Adapter<BoardAdapter.ViewHolder> {
    private ItemBoardBinding binding;
    private Button button;
    private String[] title = new String[]{"Hello", "Using", "Register"};
    private String[] desc = new String[]{"Hey my friend this is my app",
            "Come on quickly look at my application", "If you want to use my app, then you need to register"};
    private int[] imgs = new int[]{R.drawable.ic_boar_first, R.drawable.ic_boar_sevond, R.drawable.ic_boar_third};

    @NotNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_board, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull ViewHolder holder, int position) {
        holder.bind(position);
        if (position == 2) {
            button.setVisibility(View.VISIBLE);
        } else {
            button.setVisibility(View.INVISIBLE);
        }

    }

    @Override
    public int getItemCount() {
        return 3;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView textTitle, textDesc;
        private ImageView imagesBoard;


        public ViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            button = itemView.findViewById(R.id.startBtn);
            textTitle = itemView.findViewById(R.id.titleTV);
            textDesc = itemView.findViewById(R.id.descTV);
            imagesBoard = itemView.findViewById(R.id.imageBoard);

        }

        public void bind(int position) {
            textTitle.setText(title[position]);
            textDesc.setText(desc[position]);
            imagesBoard.setImageResource(imgs[position]);
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    NavController navController = Navigation.findNavController((Activity) v.getContext(),
                            R.id.nav_host_fragment_activity_main);
                    navController.navigateUp();
                }
            });


        }
    }
}
