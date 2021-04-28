package com.egoriku.activityresult.fragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import androidx.fragment.app.setFragmentResultListener
import androidx.fragment.app.viewModels
import by.kirich1409.viewbindingdelegate.viewBinding
import com.egoriku.activityresult.R
import com.egoriku.activityresult.databinding.FragmentResultBinding
import com.egoriku.activityresult.fragment.InputType.SubTitle
import com.egoriku.activityresult.fragment.InputType.Title
import com.egoriku.activityresult.fragment.dialog.SubTitleInputDialogFragment
import com.egoriku.activityresult.fragment.dialog.TitleInputDialogFragment

const val REQUEST_KEY = "request_code"
const val VALUE_TITLE = "VALUE_TITLE"
const val VALUE_SUB_TITLE = "VALUE_SUB_TITLE"

class FragmentResultApi : Fragment(R.layout.fragment_result) {

    private val binding by viewBinding(FragmentResultBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setFragmentResultListener(
            requestKey = REQUEST_KEY,
        ) { _, bundle -> processResult(bundle) }

        with(binding) {
            title.setOnClickListener {
                show(inputType = Title)
            }

            subTitle.setOnClickListener {
                show(inputType = SubTitle)
            }
        }
    }

    private fun processResult(bundle: Bundle) {
        if (bundle.containsKey(VALUE_TITLE)) {
            binding.title.text = bundle.getString(VALUE_TITLE)
        }

        if (bundle.containsKey(VALUE_SUB_TITLE)) {
            binding.subTitle.text = bundle.getString(VALUE_SUB_TITLE)
        }
    }

    private fun show(inputType: InputType) {
        when (inputType) {
            is Title -> TitleInputDialogFragment().show(parentFragmentManager, "")
            is SubTitle -> SubTitleInputDialogFragment().show(parentFragmentManager, "")
        }
    }
}