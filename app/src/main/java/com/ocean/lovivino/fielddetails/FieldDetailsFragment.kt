package com.ocean.lovivino.fielddetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import com.ocean.lovivino.R
import com.ocean.lovivino.common.FragmentWithViewModel
import com.ocean.lovivino.web.SensorDto
import kotlinx.android.synthetic.main.fragment_field_details.*
import kotlin.math.sqrt
import kotlin.reflect.KClass


class FieldDetailsFragment(private val fieldId: Long) : FragmentWithViewModel<FieldDetailsViewModel, FieldDetailsViewModelFactory>() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_field_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        rating_bar.rating = 4.5f

        viewModel.fieldAt(fieldId).subscribe({
            field_name.text = getString(R.string.field_title, it.name, it.status)
            field_description.text = it.description
            val dimen = sqrt(it.sensors.size.toDouble()).toInt()
            field_grid.columnCount = dimen
            field_grid.rowCount = dimen
            val grasSize = view.width / dimen - 20

            for (cell in it.sensors) {
                val grass = ImageView(context).apply {
                    setImageResource(
                        when (cell.status) {
                            SensorDto.Status.OK -> R.drawable.grass_tile
                            SensorDto.Status.WEED_FOUND -> R.drawable.grass_tile_weed_found
                            SensorDto.Status.WORKING -> R.drawable.grass_tile_working
                        }
                    )
                    layoutParams = LinearLayout.LayoutParams(grasSize, grasSize).apply {
                        val margin = 5
                        setMargins(margin, margin, margin, margin)
                    }
                }
                field_grid.addView(grass)
            }
        }, {}).autoDispose()
    }

    override val classType: KClass<FieldDetailsViewModel> = FieldDetailsViewModel::class
}
