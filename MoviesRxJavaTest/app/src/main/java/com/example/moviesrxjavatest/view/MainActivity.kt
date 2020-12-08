package com.example.moviesrxjavatest.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.moviesrxjavatest.R
import com.example.moviesrxjavatest.model.MoviesResponse
import com.example.moviesrxjavatest.viewmodel.MoviesViewModel
import com.google.android.material.snackbar.Snackbar
import io.reactivex.rxjava3.core.Observer
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.observers.DisposableObserver

private const val TAG = "MainActivity"
class MainActivity : AppCompatActivity() {

    private val viewModel : MoviesViewModel by lazy {
        MoviesViewModel()
    }
    private var disposable = object : DisposableObserver<List<MoviesResponse>>()
    {
        override fun onNext(t: List<MoviesResponse>?) {
            TODO("Not yet implemented")
        }

        override fun onError(e: Throwable?) {
            TODO("Not yet implemented")
        }

        override fun onComplete() {
            TODO("Not yet implemented")
        }

    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel.getMovies().subscribe(
            object : Observer<List<MoviesResponse>> {

                override fun onSubscribe(d: Disposable?) {

                }

                //Data received from the observable
                //Possible data
                override fun onNext(t: List<MoviesResponse>?) {
                    t?.let {
                        Log.d(TAG, "onNext: ${it.toString()}")
                    }
                }

                override fun onError(e: Throwable?) {
                    e?.let {
                        Snackbar.make(findViewById(R.id.main_activity_root),
                        "${it.message}",
                        Snackbar.LENGTH_LONG).show()
                    }
                }

                override fun onComplete() {

                }

            }
        )
        //Defensive code to prevent observation
        //when the view is in a configuration change
        viewModel.getMovies().subscribe(
                disposable
        )
    }

    override fun onStop() {
        super.onStop()
        //Disconnect current Observation
        disposable.dispose()
    }
}