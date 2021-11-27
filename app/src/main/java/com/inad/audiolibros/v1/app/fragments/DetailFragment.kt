package com.inad.audiolibros.v1.app.fragments

import android.media.MediaPlayer
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.MediaController
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.inad.audiolibros.v1.app.App
import com.inad.audiolibros.v1.app.R
import com.inad.audiolibros.v1.app.utils.Constants
import java.io.IOException


class DetailFragment : Fragment(), View.OnTouchListener,
    MediaPlayer.OnPreparedListener, MediaController.MediaPlayerControl {
    var mediaPlayer: MediaPlayer? = null
    var mediaController: MediaController? = null
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val vista: View = inflater.inflate(R.layout.fragment_detalle, container, false)
        val args = arguments
        if (args != null) {
            val position = args.getInt(Constants.ARG_ID_LIBRO)
            ponInfoLibro(position, vista)
        } else {
            ponInfoLibro(INICIO, vista)
        }
        return vista
    }

    private fun ponInfoLibro(id: Int, vista: View?) {
        val book = (requireActivity().application as App).vectorBooks!!.elementAt(id)
        (vista!!.findViewById<View>(R.id.titulo) as TextView).text = book.title
        (vista.findViewById<View>(R.id.autor) as TextView).text = book.author
        (vista.findViewById<View>(R.id.portada) as ImageView).setImageResource(book.imageResource)
        vista.setOnTouchListener(this)
        if (mediaPlayer != null) {
            mediaPlayer!!.release()
        }
        mediaPlayer = MediaPlayer()
        mediaPlayer!!.setOnPreparedListener(this)
        mediaController = MediaController(activity)
        val audio = Uri.parse(book.urlAudio)
        try {
            mediaPlayer!!.setDataSource(requireActivity(), audio)
            mediaPlayer!!.prepareAsync()
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

    fun ponInfoLibro(id: Int) {
        ponInfoLibro(id, view)
    }

    override fun onPrepared(mp: MediaPlayer) {
        Log.d("Audiolibros", "Entramos en onPrepared de Media Player")
        mp.start()
        mediaController!!.setMediaPlayer(this)
        mediaController!!.setAnchorView(requireView().findViewById(R.id.fragment_detalle))
        mediaController!!.isEnabled = true
        mediaController!!.show()
    }

    override fun onTouch(v: View, event: MotionEvent): Boolean {
        mediaController!!.show()
        return false
    }

    override fun onStop() {
        try {
            mediaController!!.hide()
            mediaPlayer!!.stop()
            mediaPlayer!!.release()
        } catch (e: Exception) {
            Log.e("Audiolibros", "ERROR: mediaPlayer.stop() no funciona")
        }
        super.onStop()
    }

    override fun canPause(): Boolean {
        return true
    }

    override fun canSeekBackward(): Boolean {
        return true
    }

    override fun canSeekForward(): Boolean {
        return true
    }

    override fun getBufferPercentage(): Int {
        return 0
    }

    override fun getCurrentPosition(): Int {
        return try {
            mediaPlayer!!.currentPosition
        } catch (e: Exception) {
            0
        }
    }

    override fun getDuration(): Int {
        return mediaPlayer!!.duration
    }

    override fun isPlaying(): Boolean {
        return mediaPlayer!!.isPlaying
    }

    override fun pause() {
        mediaPlayer!!.pause()
    }

    override fun seekTo(pos: Int) {
        mediaPlayer!!.seekTo(pos)
    }

    override fun start() {
        mediaPlayer!!.start()
    }

    override fun getAudioSessionId(): Int {
        return 0
    }

    companion object {
        var INICIO = 0
    }
}
