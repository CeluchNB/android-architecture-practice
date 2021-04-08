package com.noah.architecturepractice.ui.booklist

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
import com.noah.architecturepractice.utils.BookListViewModelFactory
import com.noah.architecturepractice.utils.GenericSavedStateViewModelFactory
import kotlinx.android.synthetic.main.activity_book_list.*
import javax.inject.Inject

class BookListActivity: AppCompatActivity(), BookListAdapter.OnItemClickListener {

    @Inject
    lateinit var viewModelFactory: BookListViewModelFactory

    private val viewModel: BookListViewModel by viewModels {
        GenericSavedStateViewModelFactory(viewModelFactory, this, intent.extras)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_book_list)

        (application as DPApplication).component.inject(this)

        // set layout manager and item decorator
        bookList.layoutManager = LinearLayoutManager(this)
        val deco: RecyclerView.ItemDecoration = DividerItemDecoration(this, DividerItemDecoration.VERTICAL)
        bookList.addItemDecoration(deco)

        viewModel.titles.observe(this, Observer {titles ->
            if (!titles.isNullOrEmpty()) {
                val adapter = BookListAdapter(titles)
                adapter.onItemClickListener = this
                bookList.adapter = adapter
            } else {
                bookList.visibility = View.GONE
                errorNotification.visibility = View.VISIBLE
            }
        })
    }

    override fun onItemClicked(itemView: View, position: Int) {
        viewModel.navigateToBookList(position, this)
    }
}