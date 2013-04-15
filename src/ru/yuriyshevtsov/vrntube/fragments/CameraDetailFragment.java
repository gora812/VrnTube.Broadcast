package ru.yuriyshevtsov.vrntube.fragments;

import io.vov.utils.Log;
import io.vov.vitamio.LibsChecker;
import io.vov.vitamio.MediaPlayer;
import io.vov.vitamio.MediaPlayer.OnBufferingUpdateListener;
import io.vov.vitamio.MediaPlayer.OnInfoListener;
import io.vov.vitamio.MediaPlayer.OnPreparedListener;
import io.vov.vitamio.widget.MediaController;
import io.vov.vitamio.widget.VideoView;
import ru.yuriyshevtsov.vrntube.CameraDetailActivity;
import ru.yuriyshevtsov.vrntube.CameraListActivity;
import ru.yuriyshevtsov.vrntube.R;
import ru.yuriyshevtsov.vrntube.model.Camera;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * A fragment representing a single Camera detail screen.
 * This fragment is either contained in a {@link CameraListActivity} in two-pane mode (on tablets) or a {@link CameraDetailActivity} on handsets.
 */
public class CameraDetailFragment extends Fragment {
    /**
     * The fragment argument representing the item ID that this fragment
     * represents.
     */
    public static final String ARG_ITEM_ID = "camera_id";

    /**
     * The dummy content this fragment is presenting.
     */
    private Camera camera;
    private VideoView videoView;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public CameraDetailFragment() {}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments().containsKey(ARG_ITEM_ID)) {
            // Load the dummy content specified by the fragment
            // arguments. In a real-world scenario, use a Loader
            // to load content from a content provider.
            camera = Camera.CAMERAS_MAP.get(getArguments().getString(ARG_ITEM_ID));
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_camera_detail, container, false);

        // Show the dummy content as text in a TextView.
        if (camera != null && LibsChecker.checkVitamioLibs(getActivity())) {

            videoView = (VideoView) rootView.findViewById(R.id.surface_view);

            videoView.setVideoPath(camera.url);
            videoView.setVideoQuality(MediaPlayer.VIDEOQUALITY_LOW);
            videoView.setMediaController(new MediaController(getActivity()));
            videoView.setOnPreparedListener(new OnPreparedListener() {
                public void onPrepared(MediaPlayer player) {
                    player.setBufferSize(200);
                    player.setOnInfoListener(new OnInfoListener() {
                        public boolean onInfo(MediaPlayer mp, int what, int extra) {
                            if (what == MediaPlayer.MEDIA_INFO_BUFFERING_START ||
                                    what == MediaPlayer.MEDIA_INFO_BUFFERING_END)
                                if (!mp.isPlaying())
                                    mp.start();
                            return true;
                        }
                    });
                }
            });
            videoView.setOnBufferingUpdateListener(new OnBufferingUpdateListener() {
                public void onBufferingUpdate(MediaPlayer mp, int percent) {
                    Log.d("OnBufferProgress %d from %d", mp.getBufferProgress(), percent);
                    if ((percent > 0) || (!mp.isPlaying()))
                        mp.start();

                }
            });
            videoView.requestFocus();

            getActivity().setTitle(camera.address);
        }

        return rootView;
    }
}
