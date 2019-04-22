package com.sandro.cbb;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import org.threeten.bp.DateTimeUtils;
import org.threeten.bp.LocalDate;
import org.threeten.bp.ZoneId;
import org.threeten.bp.format.DateTimeFormatter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AlumnoAdapter extends RecyclerView.Adapter<AlumnoAdapter.ViewHolder>{

    List<Alumno> alumnos;
    Context ctx;
    OnAlumnoListener onAlumnoListener;

    public AlumnoAdapter(List<Alumno> alumnos, Context ctx, OnAlumnoListener onAlumnoListener) {
        this.alumnos = alumnos;
        this.ctx = ctx;
        this.onAlumnoListener = onAlumnoListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.layout_alumno_row, parent, false);
        return new ViewHolder(v, onAlumnoListener);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Alumno alumno = alumnos.get(position);
        holder.tvName.setText(alumno.getName() + " " + alumno.getLastName());
        LocalDate date = DateTimeUtils.toInstant(alumno.getBirthday()).atZone(ZoneId.systemDefault()).toLocalDate();
        DateTimeFormatter df = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        holder.tvBirthday.setText(date.format(df));

        Glide.with(ctx)
                .load(alumno.getPhotoSmall())
                .into(holder.imgViewSmall);
    }

    @Override
    public int getItemCount() {
        return alumnos.size();
    }

    public Alumno getAlumno(int position){
        return alumnos.get(position);
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        @BindView(R.id.textViewName) TextView tvName;
        @BindView(R.id.textViewBirthday) TextView tvBirthday;
        @BindView(R.id.imageViewSmall) ImageView imgViewSmall;

        OnAlumnoListener onAlumnoListener;

        public ViewHolder(@NonNull View itemView, OnAlumnoListener onAlumnoListener) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            this.onAlumnoListener = onAlumnoListener;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            onAlumnoListener.onAlumnoClick(getAdapterPosition());
        }
    }

    public interface OnAlumnoListener{
        void onAlumnoClick(int position);
    }

}