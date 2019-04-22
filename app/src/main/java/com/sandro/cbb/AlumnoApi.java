package com.sandro.cbb;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface AlumnoApi {

    @GET("alumnos")
    Call<List<Alumno>> getAlumnos();
}
