package com.remotearth.fake_coder.chatapp.screens.fragments.chatList

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager

import com.remotearth.fake_coder.chatapp.R
import com.remotearth.fake_coder.chatapp.pojos.User
import com.remotearth.fake_coder.chatapp.adapters.UserListAdapter
import com.remotearth.fake_coder.chatapp.databinding.ChatListFragmentBinding
import com.remotearth.fake_coder.chatapp.base.BaseFragment
import com.remotearth.fake_coder.chatapp.services.impls.FireBaseAuthServiceImpl
import com.remotearth.fake_coder.chatapp.services.impls.FireBaseRealTimeDataBaseServiceImpl
import com.remotearth.fake_coder.chatapp.utils.config.Constant
import com.remotearth.fake_coder.chatapp.factories.ChatListViewModelFactory
import kotlinx.android.synthetic.main.chat_list_fragment.*

class ChatListFragment : BaseFragment(), ChatListView {

    private lateinit var viewModel: ChatListViewModel
    private lateinit var chatListFragmentBinding: ChatListFragmentBinding
    private lateinit var userListAdapter: UserListAdapter

    override fun initDataBinding(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        chatListFragmentBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.chat_list_fragment,
            container,
            false
        )
        return chatListFragmentBinding.root
    }

    override fun initWidget() {
        userListAdapter = UserListAdapter {
            val bundle = Bundle()
            bundle.putParcelable(Constant.BUNDLE_USER, it)
            navigateTo(R.id.chatList_to_chat, bundle)
        }

        userListRecyclerView.layoutManager = LinearLayoutManager(context)
        userListRecyclerView.adapter = userListAdapter
    }

    override fun initViewModel() {
        viewModel = ViewModelProviders.of(
            this,
            ChatListViewModelFactory(
                FireBaseAuthServiceImpl(),
                FireBaseRealTimeDataBaseServiceImpl(),
                this
            )
        ).get(ChatListViewModel::class.java)

        chatListFragmentBinding.chatListViewModel = viewModel
        chatListFragmentBinding.userId = viewModel.getUserId()
        viewModel.userList.observe(this, Observer { userListAdapter.submitList(it as ArrayList<User>) })
    }

    override fun bundleCommunication() {}

    override fun onStart() {
        super.onStart()
        viewModel.checkUserStatus()
        viewModel.retrieveAllUsers()
    }

    override fun navigateToLogin() {
        navigateTo(R.id.chatList_to_login)
    }

    override fun navigateToUserInfo() {
        navigateTo(R.id.chatList_to_userInfo)
    }

}