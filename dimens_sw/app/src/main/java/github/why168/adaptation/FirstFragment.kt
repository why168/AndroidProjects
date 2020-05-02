package github.why168.adaptation

import android.os.Bundle
import android.util.DisplayMetrics
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import kotlinx.android.synthetic.main.fragment_first.*

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_first, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        button_first.setOnClickListener {
            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
        }

        tv1.postDelayed({
            val dm = DisplayMetrics()
            activity?.windowManager?.defaultDisplay?.getMetrics(dm)

            val width = dm.widthPixels.coerceAtMost(dm.heightPixels)
            tv1.text = "dpi : " + dm.densityDpi + "   smallest width pixels : " + width
            tv2.text = "计算出来的smallestWidth : " + width / (dm.densityDpi / 160.0) + "dp"
            tv3.text = "实际使用的smallestWidth :  " + resources.getString(R.string.base_dpi)
            tv4.text = "当前手机： " + SystemUtil.getDeviceBrand()
                .toString() + "  " + SystemUtil.getSystemModel()
                .toString() + " \n" + "当前系统： " + SystemUtil.getSystemVersion()
                .toString() + " "
            val p = view.layoutParams as FrameLayout.LayoutParams
            p.width = resources.getDimensionPixelSize(R.dimen.qb_px_375)
            view.layoutParams = p
        }, 500)

    }
}
