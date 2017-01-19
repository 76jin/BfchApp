package kr.bfch.app.player;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayer.OnInitializedListener;
import com.google.android.youtube.player.YouTubePlayerView;

import kr.bfch.app.R;

public class YouTubePlayerActivity extends YouTubeBaseActivity implements
        OnInitializedListener {
	private static final String TAG = "YouTubePlayerActivity";
	private static final int RECOVERY_DIALOG_REQUEST = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.i(TAG, "## onCreate()....");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.youtube_view);

        Log.i(TAG, "## onCreate() 1111....");

        // YoutubePlayerView에 Developer 키를 설정
        YouTubePlayerView youTubeView = (YouTubePlayerView) findViewById(R.id.youtube_view);
        Log.i(TAG, "## onCreate() 2222 ....");
        youTubeView.initialize(DeveloperKey.DEVELOPER_KEY, this);

        Log.i(TAG, "## onCreate() 3333 ....");
    }

    @Override
    public void onInitializationFailure(YouTubePlayer.Provider provider,
            YouTubeInitializationResult errorReason) {
        Log.i(TAG, "## onInitializationFailure() 1111....");
        // 초기화 실패한 경우 처리
        if (errorReason.isUserRecoverableError()) {
            Log.i(TAG, "## onInitializationFailure() 2222....");
            // 오류 대화 상자 표시
            errorReason.getErrorDialog(this, RECOVERY_DIALOG_REQUEST).show();
        } else {
            Log.i(TAG, "## onInitializationFailure() 3333....");
            // 오류 표시
            String errorMessage = String.format(
                    getString(R.string.text_error_player),
                    errorReason.toString());
            Toast.makeText(this, errorMessage, Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onInitializationSuccess(YouTubePlayer.Provider provider,
            YouTubePlayer player, boolean wasRestored) {
        Log.i(TAG, "## onInitializationSuccess() 1111....");
        // YouTube 동영상 ID를 설정
        if (!wasRestored) {
            Log.i(TAG, "## onInitializationSuccess() 2222....");
            player.cueVideo("ZqOg_wqGGWk");
        }
    }
}
