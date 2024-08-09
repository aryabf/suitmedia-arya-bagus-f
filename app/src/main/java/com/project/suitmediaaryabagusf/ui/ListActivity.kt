package com.project.suitmediaaryabagusf.ui

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View.GONE
import android.view.View.VISIBLE
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.project.suitmediaaryabagusf.R
import com.project.suitmediaaryabagusf.api.ApiConfig
import com.project.suitmediaaryabagusf.databinding.ActivityListBinding
import com.project.suitmediaaryabagusf.models.User
import com.project.suitmediaaryabagusf.models.UserResponse
import com.project.suitmediaaryabagusf.ui.adapter.UserAdapter
import com.project.suitmediaaryabagusf.utils.Utils
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ListActivity : AppCompatActivity(), UserAdapter.OnItemClickListener {

    private lateinit var binding: ActivityListBinding
    private lateinit var adapter: UserAdapter
    private var currentPage = 1
    private val itemsPerPage = 10

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityListBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        initViews()
        setupPagination()
        loadPage(currentPage)
        setSwipeRefresh()

    }

    private fun initViews() {
        binding.apply {

            btnBack.setOnClickListener { finish() }

            adapter = UserAdapter().apply {
                addItems(mutableListOf())
                listener = this@ListActivity
                rvUsers.adapter = this
            }
            rvUsers.layoutManager = LinearLayoutManager(this@ListActivity)

            pbList.visibility = VISIBLE
            tvFailed.visibility = GONE
            rvUsers.visibility = GONE

        }
    }

    private fun setSwipeRefresh() {
        binding.apply {
            srlUsers.setOnRefreshListener {
                refreshPage()
                srlUsers.isRefreshing = false
            }
        }
    }

    private fun loadPage(page: Int) {

        val client = ApiConfig.getAPIService()
        client.getUsers(page, itemsPerPage).enqueue(object: Callback<UserResponse> {
            override fun onResponse(call: Call<UserResponse>, response: Response<UserResponse>) {
                if (response.isSuccessful) {
                    response.body()?.let {
                        adapter.addItems(it.data)
                        setPageState(true)
                    }
                } else {
                    setPageState(false)
                }
            }

            override fun onFailure(call: Call<UserResponse>, t: Throwable) {
                setPageState(false)
            }

        })
    }

    private fun setPageState(isSuccess: Boolean) {
        if (isSuccess) {
            binding.apply {
                pbList.visibility = GONE
                tvFailed.visibility = GONE
                rvUsers.visibility = VISIBLE
            }
        } else {
            binding.apply {
                pbList.visibility = GONE
                tvFailed.visibility = VISIBLE
                rvUsers.visibility = GONE
            }
            Utils.shortToast(this@ListActivity, "Failed to fetch data.")
        }
    }

    private fun refreshPage() {
        currentPage = 1
        adapter.clearItems()
        loadPage(currentPage)
    }

    private fun setupPagination() {
        binding.rvUsers.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                val layoutManager = recyclerView.layoutManager as LinearLayoutManager
                val totalItemCount = layoutManager.itemCount
                val lastVisibleItem = layoutManager.findLastVisibleItemPosition()

                if (lastVisibleItem + 1 == totalItemCount) {
                    currentPage++
                    loadPage(currentPage)
                }
            }
        })
    }

    override fun onItemClicked(item: User) {
        val intent = Intent()
        intent.putExtra("selectedUser", "${item.firstName} ${item.lastName}")
        setResult(Activity.RESULT_OK, intent)
        finish()
    }

}