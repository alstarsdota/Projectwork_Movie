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
//import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val request = RetrofitService.buildService(RetrofitService.PostApi::class.java)
        val call = request.getMovies("9bfd5d32b929d0e783715ffd0c364000")
        call.enqueue(object : Callback<MovieResponse> {
            override fun onResponse(call: Call<MovieResponse>, response: Response<MovieResponse>) {
                if (response.isSuccessful){
                    movieListAdapter?.notifyDataSetChanged()
                    recyclerView.apply {
                        setHasFixedSize(true)
                        layoutManager = GridLayoutManager(context, 2)
                        adapter = MovieListAdapter(response.body()!!.movies)
                    }
                }
            }
            override fun onFailure(call: Call<MovieResponse>, t: Throwable) {
            }
        })
    }
    companion object {
        fun newInstance(): HomeFragment = HomeFragment()
    }
}



//    private fun getPosts() {
//        RetrofitService.getPostApi().getPostList().enqueue(object : Callback<List<Movie>> {
//            override fun onFailure(call: retrofit2.Call<List<Movie>>, t: Throwable) {
//                //swipeRefreshLayout.isRefreshing = false
//            }
//
//            override fun onResponse(call: retrofit2.Call<List<Movie>>, response: Response<List<Movie>>) {
//                Log.d("My_post_list", response.body().toString())
//                if (response.isSuccessful) {
//                    val list = response.body()
//                    movieListAdapter?.list = list
//                    movieListAdapter?.notifyDataSetChanged()
//                }
//            }
//        })
//    }




