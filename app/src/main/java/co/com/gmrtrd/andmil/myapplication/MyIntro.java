package co.com.gmrtrd.andmil.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.github.paolorotolo.appintro.AppIntro;

/**
 * Clase que se utiliza para la creación del intro de la aplicación.
 * Es propiedad de PaoloRotolo y se puede encontrar en
 * https://github.com/PaoloRotolo/AppIntro.
 */
public class MyIntro extends AppIntro implements View.OnClickListener {
    // Please DO NOT override onCreate. Use init
    @Override
    public void init(Bundle savedInstanceState) {

        addSlide(SampleSlide.newInstance(R.layout.intro_fragment1));
        addSlide(SampleSlide.newInstance(R.layout.intro_fragment2));
        addSlide(SampleSlide.newInstance(R.layout.intro_fragment3));
        addSlide(SampleSlide.newInstance(R.layout.intro_fragment4));
        addSlide(SampleSlide.newInstance(R.layout.intro_fragment5));

        final TextView conCuentaLink = (TextView) findViewById(R.id.conCuentaLink);
        final TextView sinCuentaLink = (TextView) findViewById(R.id.sinCuentaLink);

        // Hide Skip/Done button
        showSkipButton(false);
        showStatusBar(false);
        // Turn vibration on and set intensity
        // NOTE: you will probably need to ask VIBRATE permisssion in Manifest
        setVibrate(true);
        setVibrateIntensity(30);
        setDepthAnimation();
        // Animations -- use only one of the below. Using both could cause errors.
        setFadeAnimation(); // OR
/*            setZoomAnimation();
            setFlowAnimation(); // OR
            setSlideOverAnimation(); // OR
            setDepthAnimation(); // OR
            setCustomTransformer(yourCustomTransformer);*/

        // Permissions -- takes a permission and slide number
        //askForPermissions(new String[]{Manifest.permission.CAMERA}, 3);
    }

    @Override
    public void onSkipPressed() {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }

    @Override
    public void onNextPressed() {
        // Do something when users tap on Next button.
    }

    @Override
    public void onDonePressed() {
        // Do something when users tap on Done button.
        finish();
    }

    @Override
    public void onSlideChanged() {
        // Do something when slide is changed
    }

    public void sinCuentaClickListener(View v) {
        Intent intent = new Intent(this, RegisterActivity.class);
        startActivity(intent);
    }

    public void conCuentaClickListener(View v) {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }

    @Override
    public void onClick(View v) {

    }
}
