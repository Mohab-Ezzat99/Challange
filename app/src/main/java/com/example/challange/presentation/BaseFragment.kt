package com.example.challange.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import com.example.challange.data.remote.NetworkHandling
import retrofit2.Response

abstract class BaseFragment<out T : ViewBinding> : Fragment(), NetworkHandling.Status {

    private var _binding: ViewBinding? = null

    @Suppress("UNCHECKED_CAST")
    protected val binding: T get() = _binding as T
    protected abstract val bindingInflater: (LayoutInflater) -> ViewBinding

    abstract fun setUI(view: View, savedInstanceState: Bundle?)
    abstract fun clicks()
    abstract fun callApis()
    abstract fun observer()
    protected open fun observerLoadingCancellation() {}

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
//        LoadingDialog.init(requireContext())
        baseObserver()
        _binding = bindingInflater(inflater)
        return _binding!!.root
    }

    private fun baseObserver() {
//        LoadingDialog.isLoadingCancelled?.observe(viewLifecycleOwner) { isCancelled ->
//            if (isCancelled) observerLoadingCancellation()
//        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUI(view, savedInstanceState)
        clicks()
        callApis()
        observer()
    }

    override fun onResume() {
        super.onResume()
        NetworkHandling.observeNetworkStatus(this)
    }

    override fun onBadRequest(exception: String?) {

    }

    override fun onMakeAction(exception: String?) {

    }

    override fun onNotVerifyRequest(exception: String?) {

    }

    override fun onNotAuthorized(exception: String?) {

    }

    override fun onServerSideError(exception: String?) {

    }

    override fun <T> onDynamicCode(response: Response<T>) {

    }

    override fun onConnectionFail(exception: String?) {

    }

    override fun onNotAllowed() {

    }

    override fun onApiNotFound() {

    }

    override fun onNoInternet() {

    }

    override fun onDestroyView() {
        super.onDestroyView()
//        LoadingDialog.clear()
        _binding = null
    }
}