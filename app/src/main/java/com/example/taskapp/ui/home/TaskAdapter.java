package com.example.taskapp.ui.home;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.taskapp.R;
import com.example.taskapp.ui.interfaces.OnItemClickListener;

import org.jetbrains.annotations.NotNull;

import java.time.temporal.Temporal;
import java.util.ArrayList;

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.ViewHolder> {
    ArrayList<Task> list = new ArrayList<>();
    private int index;

    public void setList(ArrayList<Task> list) {
        this.list = list;

    }

    itemClickListener itemClickListener;

    @Override
    public ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_task, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull ViewHolder holder, int position) {
        holder.bind(list.get(position));
        if (position % 2 == 0) {
            holder.itemView.setBackgroundColor(Color.BLACK);
        } else {
            holder.itemView.setBackgroundColor(Color.WHITE);
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void addItem(Task task) {
        list.add(task);
        notifyDataSetChanged();
    }

    public void setOnItemClickListener(itemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    public void removeItem(int position) {
        this.list.remove(position);
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView textView, data;

        public ViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);

            itemView.setOnClickListener(v -> {
            });
            data = itemView.findViewById(R.id.data_tv);
            textView = itemView.findViewById(R.id.task_tv);
        }

        public void bind(Task text) {
            textView.setText(text.getTitle());
            data.setText(text.getCreateAt());
            itemView.setOnLongClickListener(v -> {
                AlertDialog.Builder builder = new AlertDialog.Builder(this.itemView.getContext());
                builder.setTitle("Удалено")
                        .setIcon(R.drawable.ic_launcher_background)
                        .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                       //         itemClickListener.onLong(getAdapterPosition());
                                Toast.makeText(itemView.getContext(), "Ok", Toast.LENGTH_SHORT).show();
                            }

                        }).show();
                      /*  .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Toast.makeText(itemView.getContext(), "Ok", Toast.LENGTH_SHORT).show();
                            }
                        }).show();*/
                itemClickListener.onLong(getAdapterPosition());

                return true;
            });
        }
    }

    interface itemClickListener {
        void onLong(int position);
    }

}
