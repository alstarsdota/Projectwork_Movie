package com.example.project_work
import kotlinx.android.synthetic.main.fragment_home.*
//import android.support.v7.app.AppCompatActivity
import android.os.Bundle
//import android.support.v7.widget.LinearLayoutManager
//import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import kotlinx.coroutines.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response



class HomeFragment : Fragment() {
    private var movieListAdapter: MovieListAdapter? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        retainInstance = true
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        inflater.inflate(R.layout.fragment_home, container, false)
    companion object {
        fun newInstance(): HomeFragment = HomeFragment()
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        movieListAdapter = MovieListAdapter()
        recyclerView.apply{
            setHasFixedSize(true)
            layoutManager = GridLayoutManager(context,2)
            adapter = movieListAdapter
        }
        swipeRefreshLayout.setOnRefreshListener {
            Log.d("asdfadsdf", "Assem is this you bitch")
            movieListAdapter?.clearAll()
           getMovies()
        }

        getMovies()

//        val request = RetrofitService.buildService(RetrofitService.PostApi::class.java)
//        val call = request.getMovies("9bfd5d32b929d0e783715ffd0c364000")
//        call.enqueue(object : Callback<MovieResponse> {
//            override fun onResponse(call: Call<MovieResponse>, response: Response<MovieResponse>) {
//                if (response.isSuccessful) {
//                    movieListAdapter?.notifyDataSetChanged()
//                    recyclerView.apply {
//                        setHasFixedSize(true)
//                        layoutManager = GridLayoutManager(context, 2)
//                        adapter = MovieListAdapter(response.body()!!.movies)
//                    }
//                }
//            }
//            override fun onFailure(call: Call<MovieResponse>, t: Throwable) {
//            }
//        })
    }
    private fun getMovies(){
        swipeRefreshLayout.isRefreshing = true
        val request: RetrofitService.PostApi = RetrofitService.buildService((RetrofitService.PostApi::class.java))
        val call: Call<MovieResponse> = request.getMovies("9bfd5d32b929d0e783715ffd0c364000")
        call.enqueue(object: Callback<MovieResponse> {
            override fun onResponse(call: Call<MovieResponse>, response: Response<MovieResponse>){
                if(response.isSuccessful){
                    movieListAdapter = MovieListAdapter(response.body()!!.movies)
                    recyclerView.apply {
                        setHasFixedSize(true)
                        layoutManager = GridLayoutManager(context, 2)
                        adapter = movieListAdapter
                    }
                }
                swipeRefreshLayout.isRefreshing = false
            }

            override fun onFailure(call: Call<MovieResponse>, t: Throwable) {
                swipeRefreshLayout.isRefreshing = false
            }
        })
    }
}




