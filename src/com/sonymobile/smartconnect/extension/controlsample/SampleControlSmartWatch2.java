package com.sonymobile.smartconnect.extension.controlsample;

import android.content.Context;
import android.os.CountDownTimer;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;

import com.sonyericsson.extras.liveware.aef.control.Control;
import com.sonyericsson.extras.liveware.extension.util.control.ControlExtension;
import com.sonyericsson.extras.liveware.extension.util.control.ControlObjectClickEvent;
import com.sonyericsson.extras.liveware.extension.util.control.ControlTouchEvent;
import com.sonyericsson.extras.liveware.extension.util.control.ControlView;
import com.sonyericsson.extras.liveware.extension.util.control.ControlView.OnClickListener;
import com.sonyericsson.extras.liveware.extension.util.control.ControlViewGroup;
import com.sonyericsson.extras.liveware.extension.util.registration.RegistrationInformation;

/**
 * The sample control for SmartWatch handles the control on the accessory. This
 * class exists in one instance for every supported host application that we
 * have registered to
 */
class SampleControlSmartWatch2 extends ControlExtension {

	private final int HAPPY_LAYOUT = 1;
	private final int SAD_LAYOUT = 2;
	private final int PAIN_LAYOUT = 3;
	private LayoutInflater inflater;
	private ControlViewGroup mLayout = null;
	

	/**
	 * Create sample control.
	 * 
	 * @param hostAppPackageName
	 *            Package name of host application.
	 * @param context
	 *            The context.
	 * @param handler
	 *            The handler to use
	 */
	SampleControlSmartWatch2(final String hostAppPackageName,
			final Context context, Handler handler) {

		super(context, hostAppPackageName);

		if (handler == null) {
			throw new IllegalArgumentException("handler == null");
		}

		inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View layout = inflater.inflate(R.layout.sample_control_2, null);
		mLayout = parseLayout(layout);
		if (mLayout != null) {
			ControlView happyButton = mLayout.findViewById(R.id.happy);
			ControlView sadButton = mLayout.findViewById(R.id.sad);
			ControlView painButton = mLayout.findViewById(R.id.pain);

			happyButton.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick() {
					setLayout(HAPPY_LAYOUT);
					restoreLayout();
				}
			});

			sadButton.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick() {
					setLayout(SAD_LAYOUT);
					restoreLayout();

				}
			});

			painButton.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick() {
					setLayout(PAIN_LAYOUT);
					restoreLayout();

				}
			});

		}
	}

	private void setLayout(int layout) {
		switch (layout) {

		case HAPPY_LAYOUT:
			showLayout(R.layout.happy_layout, null);
			break;
		case SAD_LAYOUT:
			showLayout(R.layout.sad_layout, null);
			break;
		case PAIN_LAYOUT:
			showLayout(R.layout.pain_layout, null);
			break;
		}
	}

	private void restoreLayout() {
		new CountDownTimer(1500, 1500) {

			public void onTick(long millisUntilFinished) {
			}

			public void onFinish() {
				showLayout(R.layout.sample_control_2, null);
			}

		}.start();
	}

	/**
	 * Get supported control width.
	 * 
	 * @param context
	 *            The context.
	 * @return the width.
	 */
	public static int getSupportedControlWidth(Context context) {
		return context.getResources().getDimensionPixelSize(
				R.dimen.smart_watch_2_control_width);
	}

	/**
	 * Get supported control height.
	 * 
	 * @param context
	 *            The context.
	 * @return the height.
	 */
	public static int getSupportedControlHeight(Context context) {
		return context.getResources().getDimensionPixelSize(
				R.dimen.smart_watch_2_control_height);
	}

	@Override
	public void onDestroy() {
		Log.d(SampleExtensionService.LOG_TAG,
				"SampleControlSmartWatch onDestroy");
	};

	@Override
	public void onStart() {
		// showLayout(R.layout.sample_control_2, null);
	}

	@Override
	public void onStop() {
	}

	@Override
	public void onResume() {
		
		showLayout(R.layout.sample_control_2, null);
	}

	@Override
	public void onPause() {

	}

	@Override
	public void onTouch(final ControlTouchEvent event) {

		if (event.getAction() == Control.Intents.TOUCH_ACTION_RELEASE) {
			Log.d(SampleExtensionService.LOG_TAG,
					"onTouch() " + event.getAction());

		}
	}

	@Override
	public void onObjectClick(final ControlObjectClickEvent event) {

		if (event.getLayoutReference() != -1) {
			mLayout.onClick(event.getLayoutReference());
		}
	}

	@Override
	public void onKey(final int action, final int keyCode, final long timeStamp) {

	}

	@Override
	public void onMenuItemSelected(final int menuItem) {
	}

}
