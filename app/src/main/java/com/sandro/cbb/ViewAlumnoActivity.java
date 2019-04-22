package com.sandro.cbb;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import org.threeten.bp.DateTimeUtils;
import org.threeten.bp.LocalDate;
import org.threeten.bp.ZoneId;
import org.threeten.bp.format.DateTimeFormatter;
import org.threeten.bp.temporal.ChronoUnit;

import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ViewAlumnoActivity extends AppCompatActivity {

    @BindView(R.id.tvName) TextView tvName;
    @BindView(R.id.tvBirthday) TextView tvBirthday;
    @BindView(R.id.tvDaysToBirthDay) TextView tvDaysToBirthDay;
    @BindView(R.id.tvHobby) TextView tvHobby;
    @BindView(R.id.tvProfession) TextView tvProfession;
    @BindView(R.id.tvAboutYou) TextView tvAboutYou;
    @BindView(R.id.tvLikePercent) TextView tvLikePercent;
    @BindView(R.id.tvVotesCount) TextView tvVotesCount;
    @BindView(R.id.imgViewLarge) ImageView imgViewLarge;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_alumno);

        ButterKnife.bind(this);

        Alumno alumno = getIntent().getParcelableExtra("selectedAlumno");

        LocalDate date = DateTimeUtils.toInstant(alumno.getBirthday()).atZone(ZoneId.systemDefault()).toLocalDate();
        DateTimeFormatter df = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        LocalDate now = LocalDate.now();
        LocalDate birthDayDate = date.withYear(now.getYear());
        String TAG = "GGRANADOS";
        Log.i(TAG, "now:" + now);
        Log.i(TAG, "birthDayDate:" + birthDayDate);
        Log.i(TAG, "date:" + date);

        final int minP = 80;
        final int maxP = 100;
        final int randomPercentage = new Random().nextInt((maxP - minP) + 1) + minP;

        final int minV = 500;
        final int maxV = 1000;
        final int randomVotes = new Random().nextInt((maxV - minV) + 1) + minV;

        long daysToMyBirthday = 0L;

        if(now.isAfter(birthDayDate)){
            daysToMyBirthday = ChronoUnit.DAYS.between(now, birthDayDate.plusYears(1L));
        } else {
            daysToMyBirthday = ChronoUnit.DAYS.between(now, birthDayDate);
        }

        Log.i(TAG, "daysToMyBirthday:" + daysToMyBirthday);

        tvName.setText(alumno.getName() + " " + alumno.getLastName());
        tvBirthday.setText(date.format(df));
        tvHobby.setText("Me gusta mucho: " + alumno.getHobby());
        tvProfession.setText("De grande quiero ser: " + alumno.getProfession());
        tvAboutYou.setText("Te cuento que: " + alumno.getAboutYou());
        tvDaysToBirthDay.setText("Faltan " + (daysToMyBirthday - 1) + " días para mi cumpleaños!!!");
        tvLikePercent.setText(String.valueOf(randomPercentage) + "%");
        tvVotesCount.setText(String.valueOf(randomVotes) + " votos");

        Glide.with(getApplicationContext())
                .load(alumno.getPhotoLarge())
                .into(imgViewLarge);

        Log.i("GGRANADOS", alumno.toString());
    }
}
