package com.example.moviesflix;

import android.content.Intent;
import android.graphics.Movie;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toolbar;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MovieListFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MovieListFragment extends Fragment implements MovieAdapter.ClickedItem{

    Toolbar toolbar;
    RecyclerView recyclerView;
    MovieAdapter movieAdapter;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    public MovieListFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MovieListFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MovieListFragment newInstance(String param1, String param2) {
        MovieListFragment fragment = new MovieListFragment();
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
        View rootView = inflater.inflate(R.layout.movie_list, container, false);
        recyclerView = (RecyclerView) rootView.findViewById(R.id.recyclerview);
        movieAdapter = new MovieAdapter(this::ClickedMovie);
        getAllMovies();
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

      //  toolbar = (Toolbar)getView().findViewById(R.id.toolbar);




//        View rootView = inflater.inflate(R.layout.fragment_colors, container, false);
//        // 1. get a reference to recyclerView
//        RecyclerView recyclerView = (RecyclerView) rootView.findViewById(R.id.list);
//
//        // 2. set layoutManger
//        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
//
//        // this is data fro recycler view
//        ItemData itemsData[] = {
//                new ItemData("Indigo", R.drawable.circle),
//                new ItemData("Red", R.drawable.color_ic_launcher),
//                new ItemData("Blue", R.drawable.indigo),
//                new ItemData("Green", R.drawable.circle),
//                new ItemData("Amber", R.drawable.color_ic_launcher),
//                new ItemData("Deep Orange", R.drawable.indigo)
//        };
//
//
//        // 3. create an adapter
//        MyAdapter mAdapter = new MyAdapter(itemsData);
//        // 4. set adapter
//        recyclerView.setAdapter(mAdapter);
//        // 5. set item animator to DefaultAnimator
//        recyclerView.setItemAnimator(new DefaultItemAnimator());
//
//



    }

    public void getAllMovies(){

        Call<List<MovieResponse>> movielist = ApiClient.getUserService().getAllMovies();

        movielist.enqueue(new Callback<List<MovieResponse>>() {
            @Override
            public void onResponse(Call<List<MovieResponse>> call, Response<List<MovieResponse>> response) {

                if(response.isSuccessful()){
                    List<MovieResponse> movieResponses = response.body();
                    movieAdapter.setData(movieResponses);
                    recyclerView.setAdapter(movieAdapter);

                }

            }

            @Override
            public void onFailure(Call<List<MovieResponse>> call, Throwable t) {
                Log.e("failure",t.getLocalizedMessage());

            }
        });
    }

    @Override
    public void ClickedMovie(MovieResponse movieResponse) {
        MovieDetailsFragment ldf = new MovieDetailsFragment();
        Bundle args = new Bundle();
        args.putSerializable("data",movieResponse);
        ldf.setArguments(args);

//Inflate the fragment
        getFragmentManager().beginTransaction().replace(R.id.myNavHostFragment, ldf).commit();



    }
}