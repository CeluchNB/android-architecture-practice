package com.noah.architecturepractice.ui.authorlist

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.noah.architecturepractice.R
import com.noah.architecturepractice.application.DPApplication
import com.noah.architecturepractice.utils.AuthorListViewModelFactory
import com.noah.architecturepractice.utils.GenericSavedStateViewModelFactory
import kotlinx.android.synthetic.main.activity_authors_list.*
import javax.inject.Inject

class AuthorListActivity : AppCompatActivity(), AuthorListAdapter.OnItemClickListener {

    @Inject
    internal lateinit var viewModelFactory: AuthorListViewModelFactory

    private val viewModel: AuthorListViewModel by viewModels {
        GenericSavedStateViewModelFactory<AuthorListViewModel>(viewModelFactory, this, intent.extras)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_authors_list)

        (application as DPApplication).component.inject(this)

        // set layout manager and item decorator
        authorList.layoutManager = LinearLayoutManager(this)
        val deco: RecyclerView.ItemDecoration = DividerItemDecoration(this, DividerItemDecoration.VERTICAL)
        authorList.addItemDecoration(deco)

        // search then observe authors
        viewModel
            .authors
            .observe( this, Observer { authors ->
                run {
                    if (authors != null) {
                        val adapter = AuthorListAdapter(authors)
                        adapter.onItemClickListener = this
                        authorList.adapter = adapter
                    }

                }
        })

    }

    override fun onItemClick(itemView: View, position: Int) {
        viewModel.navigateToAuthor(position, this)
    }
}