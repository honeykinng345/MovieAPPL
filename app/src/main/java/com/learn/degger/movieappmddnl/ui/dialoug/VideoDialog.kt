package com.learn.degger.movieappmddnl.ui.dialoug

import androidx.appcompat.app.AppCompatDialogFragment
import android.annotation.SuppressLint
import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import android.widget.Toast
import android.content.DialogInterface
import com.learn.degger.movieappmddnl.databinding.VideoDialogBinding
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.PlayerConstants

class VideoDialog(private val videoId: String) : AppCompatDialogFragment() {
    private var binding: VideoDialogBinding? = null
    @SuppressLint("SourceLockedOrientationActivity")
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        binding = VideoDialogBinding.inflate(
            LayoutInflater.from(
                context
            )
        )
        val builder = AlertDialog.Builder(context)
        builder.setView(binding!!.root)
        lifecycle.addObserver(binding!!.youtubePlayer)
        binding!!.youtubePlayer.addYouTubePlayerListener(object : AbstractYouTubePlayerListener() {
            override fun onError(youTubePlayer: YouTubePlayer, error: PlayerConstants.PlayerError) {
                super.onError(youTubePlayer, error)
                Toast.makeText(context, "Some error occurred!", Toast.LENGTH_SHORT).show()
            }

            override fun onReady(youTubePlayer: YouTubePlayer) {
                super.onReady(youTubePlayer)
                youTubePlayer.loadVideo(videoId, 0f)
            }
        })

        //binding.youtubePlayer.enterFullScreen();
        //getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        return builder.create()
    }

    override fun onDismiss(dialog: DialogInterface) {
        super.onDismiss(dialog)
        binding!!.youtubePlayer.release()
    }
}