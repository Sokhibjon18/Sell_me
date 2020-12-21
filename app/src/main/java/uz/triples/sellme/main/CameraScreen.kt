package uz.triples.sellme.main

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import kotlinx.android.synthetic.main.fragment_camera.*
import uz.triples.sellme.R

class CameraScreen : Fragment(R.layout.fragment_camera) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        exit_main.setOnClickListener {
            findNavController().navigateUp()
        }


        bt_gallery.setOnClickListener {
            Toast.makeText(requireContext(), "open galery", Toast.LENGTH_SHORT).show()
        }

        camera.setOnClickListener {
            Toast.makeText(requireContext(), "open camera", Toast.LENGTH_SHORT).show()
        }

        video.setOnClickListener {
            Toast.makeText(requireContext(), "open video", Toast.LENGTH_SHORT).show()
        }
    }
}