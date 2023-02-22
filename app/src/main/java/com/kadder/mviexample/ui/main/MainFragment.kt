package com.kadder.mviexample.ui.main

import android.content.Context
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.kadder.mviexample.model.Photo
import com.kadder.mviexample.ui.DataStateListener
import com.kadder.mviexample.ui.main.state.MainStateEvent.GetPhotosEvent
import com.kadder.mviexample.ui.main.state.MainStateEvent.GetUserEvent
import com.kadder.mviexample.util.TopSpacingItemDecoration
import com.kader.mviexample.R
import kotlinx.android.synthetic.main.fragment_main.*

class MainFragment : Fragment(), MainRecyclerAdapter.Interaction{

    lateinit var viewModel: MainViewModel
    lateinit var dataStateListener: DataStateListener
    lateinit var mainRecyclerAdapter: MainRecyclerAdapter

    override fun onItemSelected(position: Int, item: Photo) {
        println("DEBUG: CLICKED ${position}")
        println("DEBUG: CLICKED ${item}")
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)

        viewModel = activity?.run {
            ViewModelProvider(this).get(MainViewModel::class.java)
        } ?: throw Exception("Invalid Activity")
        initRecyclerView()
        subscribeObservers()
    }

    private fun initRecyclerView(){
        recycler_view.apply {
            layoutManager = LinearLayoutManager(this@MainFragment.context)
            val topSpacingDecorator = TopSpacingItemDecoration(30)
            addItemDecoration(topSpacingDecorator)
            mainRecyclerAdapter = MainRecyclerAdapter(this@MainFragment)
            adapter = mainRecyclerAdapter
        }
    }

    fun subscribeObservers() {
        viewModel.dataState.observe(viewLifecycleOwner, Observer { dataState ->

            //Handling loading and error message
            dataStateListener.onDataStateChange(dataState)

            dataState.data?.let { event ->
                event.getContentIfNotHandled()?.let { mainViewState ->

                    println("DEBUG: DataState: $dataState")
                    mainViewState.photos?.let { photos ->
                        viewModel.setPhotosData(photos)
                    }
                    mainViewState.user?.let { user ->
                        viewModel.setUserData(user)
                    }
                } 
            }
        })

        viewModel.viewState.observe(viewLifecycleOwner, Observer { viewState ->
            viewState.photos?.let {
                println("DEBUG: setting photos to recyclerView: $it")
                mainRecyclerAdapter.submitList(it)
            }
            viewState.user?.let {
                println("DEBUG: setting user  date: $it")
            }
        })
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.main_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_get_user ->
                triggerGetUSerEvent()

            R.id.action_get_photos ->

                triggerGetPhotoEvent()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun triggerGetPhotoEvent() {
        viewModel.setStateEvent(GetPhotosEvent)
    }

    private fun triggerGetUSerEvent() {
        viewModel.setStateEvent(GetUserEvent("1"))
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        try {
            dataStateListener = context as DataStateListener
        } catch (ex: ClassCastException) {
            println("DEBUG: $context  must implement DataState listener")
        }
    }
}