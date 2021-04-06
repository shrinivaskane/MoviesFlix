package com.example.moviesflix;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MovieDetailsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MovieDetailsFragment extends Fragment {

    TextView titlemain, origintitle,romantitle,direct,produce,runtime,rate,rels,desc,url;
    MovieResponse movieResponse;
    String maintitle,origin,roman,run,dir,prod,rt,des,urls,rel;
    Button btnback;



    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public MovieDetailsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MovieDetailsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MovieDetailsFragment newInstance(String param1, String param2) {
        MovieDetailsFragment fragment = new MovieDetailsFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        assert getArguments() != null;
        movieResponse=(MovieResponse)getArguments().getSerializable("data");

        return inflater.inflate(R.layout.movie_details, container, false);



    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        titlemain= getView().findViewById(R.id.title);
        origintitle = getView().findViewById(R.id.original_title);
        romantitle  = getView().findViewById(R.id.original_title_romanised);
        desc=getView().findViewById(R.id.description);
        runtime=getView().findViewById(R.id.runningtime);
        rels=getView().findViewById(R.id.releasedate);
        rate=getView().findViewById(R.id.rtscore);
        direct=getView().findViewById(R.id.director);
        produce=getView().findViewById(R.id.producer);
        url=getView().findViewById(R.id.url);
        btnback=getView().findViewById(R.id.button);

        maintitle=movieResponse.getTitle();
        origin=movieResponse.getOriginal_title();
        roman=movieResponse.getOriginal_title_romanised();
        run=movieResponse.getRunning_time();
        dir=movieResponse.getDirector();
        prod=movieResponse.getProducer();
        rt=movieResponse.getRt_score();
        des=movieResponse.getDescription();
        urls=movieResponse.getUrl();
        rel=movieResponse.getRelease_date();

        titlemain.setText(maintitle);
        origintitle.setText(origin);
        romantitle.setText(roman);
        runtime.setText(run);
        desc.setText(des);
        rels.setText(rel);
        rate.setText(rt);
        url.setText(urls);
        produce.setText(prod);
        direct.setText(dir);

        btnback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                MovieListFragment ldf = new MovieListFragment();
                getFragmentManager().beginTransaction().replace(R.id.myNavHostFragment, ldf).commit();

            }
        });




    }
}