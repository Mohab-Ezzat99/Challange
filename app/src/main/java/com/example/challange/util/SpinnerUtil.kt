package com.example.challange.util

import android.content.Context
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.appcompat.R
import androidx.core.view.isVisible
import com.example.challange.databinding.LayoutDefaultSpinnerBinding

class SpinnerUtil<T>(
    private val context: Context,
    private val binding: LayoutDefaultSpinnerBinding,
    private val icon: Int? = null,
    private val arrow: Int? = null,
    private val background: Int? = null,
    private val hint: String? = null,
    private val hasHeader: Boolean = false,
    private var selectedItem: T? = null,
    private val isClickable: Boolean = true,
    private val isRequired: Boolean = false,
    private var list: MutableList<T>? = null,
    private var labels: MutableList<String> = mutableListOf(),
    private var selectedAction: (T?) -> Unit = {}
) {

    init {
        binding.apply {
            if (isClickable) root.setOnClickListener { spinner.performClick() }
            icon?.let {
                ivIcon.setImageResource(it)
                ivIcon.isVisible = true
            }
            arrow?.let { ivArrow.setImageResource(it) }
            background?.let { root.setBackgroundResource(it) }
            hint?.let {
                tvText.hint = it
//                if (isRequired) tvText.isRequired()
            }
            setupWithSpinner()
        }
    }

    private var isCreated = false
    private var currentItem: T? = null

    private fun setupWithSpinner() {
        list?.let { list ->
            binding.apply {
                if (hasHeader) labels.add(0, hint ?: "")
                spinner.adapter = spinnerAdapter(context, labels)

                spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                    override fun onItemSelected(
                        p0: AdapterView<*>?,
                        v: View?,
                        position: Int,
                        id: Long
                    ) {

                        if (!isCreated) {
                            selectedItem?.let {
                                val index = if (hasHeader) list.indexOf(it) + 1
                                else list.indexOf(it)
                                spinner.setSelection(index)
                                currentItem = it
                            }
                            isCreated = true
                            return
                        }

                        if (hasHeader && position == 0) {
                            tvText.text = ""
                            currentItem = null
                            selectedAction(null)
                            return
                        }

                        tvText.text = labels[position]
                        currentItem = if (hasHeader) list[position - 1] else list[position]
                        selectedAction(currentItem)
                    }

                    override fun onNothingSelected(p0: AdapterView<*>?) {

                    }
                }
            }
        }
    }

    fun submitList(
        list: MutableList<T>,
        labels: MutableList<String>,
        selectedItem: T? = null
    ) {
        this.list = list
        this.labels = labels
        this.selectedItem = selectedItem
        setupWithSpinner()
    }

    fun onItemSelected(selectedAction: (T?) -> Unit) {
        this.selectedAction = selectedAction
    }

    fun getCurrentItem() = currentItem

    private fun spinnerAdapter(context: Context, list: List<String>) =
        ArrayAdapter(
            context,
            R.layout.support_simple_spinner_dropdown_item,
            list
        )

}