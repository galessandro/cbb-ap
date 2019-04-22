package com.sandro.cbb;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.jakewharton.threetenabp.AndroidThreeTen;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements AlumnoAdapter.OnAlumnoListener {

    @BindView(R.id.refreshLayout) SwipeRefreshLayout refreshLayout;
    @BindView(R.id.recyclerViewAlumnos) RecyclerView recyclerView;

    AlumnoAdapter adapter;
    List<Alumno> alumnos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AndroidThreeTen.init(this);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

        refreshLayout.setOnRefreshListener(() -> fetchAlumnos());

        fetchAlumnos();
    }

    private void fetchAlumnos() {
        refreshLayout.setRefreshing(true);

        AlumnoApi alumnoApi = RetrofitClient.getRetrofitClient().create(AlumnoApi.class);
        Call<List<Alumno>> call = alumnoApi.getAlumnos();
        call.enqueue(new Callback<List<Alumno>>() {
            @Override
            public void onResponse(Call<List<Alumno>> call, Response<List<Alumno>> response) {
                refreshLayout.setRefreshing(false);
                alumnos = response.body();
                showAlumnos(alumnos);
            }

            @Override
            public void onFailure(Call<List<Alumno>> call, Throwable t) {
                refreshLayout.setRefreshing(false);
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

    private void showAlumnos(List<Alumno> alumnos) {
        adapter = new AlumnoAdapter(alumnos, this.getApplicationContext(), MainActivity.this);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onAlumnoClick(int position) {
        Alumno selectedAlumno = adapter.getAlumno(position);
        if (selectedAlumno != null) {
            Intent intent = new Intent(getApplicationContext(), ViewAlumnoActivity.class);
            intent.putExtra("selectedAlumno", selectedAlumno);
            startActivity(intent);
        }
    }
}
