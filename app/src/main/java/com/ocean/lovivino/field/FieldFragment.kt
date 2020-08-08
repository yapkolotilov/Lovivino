package com.ocean.lovivino.field

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.ocean.lovivino.R
import com.ocean.lovivino.common.FragmentWithViewModel
import kotlinx.android.synthetic.main.fragment_field.*
import kotlin.reflect.KClass

class FieldFragment : FragmentWithViewModel<FieldViewModel, FieldViewModelFactory>() {

    override val classType: KClass<FieldViewModel> = FieldViewModel::class

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_field, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (apiKey == "") {
            findNavController().navigate(R.id.action_fieldFragment_to_logInFragment)
            return
        }

        viewModel.fieldIds.subscribe({
            field_pager.adapter = FieldPagerAdapter(requireNotNull(activity).supportFragmentManager, it)
        }, {
            toastShortShow(it.message)
        }).autoDispose()


    }
}
