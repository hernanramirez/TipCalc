package info.hernanramirez.tipcalc;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import info.hernanramirez.tipcalc.fragments.TipHistoryListFragment;
import info.hernanramirez.tipcalc.fragments.TipHistoryListFragmentListener;
import info.hernanramirez.tipcalc.models.TipRecord;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.inputBill)
    EditText inputBill;
    @BindView(R.id.btnSubmit)
    Button btnSubmit;
    @BindView(R.id.txtTip)
    TextView txtTip;
    @BindView(R.id.btnDecrease)
    Button btnDecrease;
    @BindView(R.id.btnIncrease)
    Button btnIncrease;
    @BindView(R.id.inputPercentage)
    EditText inputPercentage;


    //RelativeLayout container;

    private TipHistoryListFragmentListener fragmentListener;
    private final static int TIP_STEP_CHANGE = 1;
    private final static int DEFAULT_TIP_PERCENTAGE = 10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        TipHistoryListFragment fragment = (TipHistoryListFragment) getSupportFragmentManager().findFragmentById(R.id.fragmentList);
        fragment.setRetainInstance(true);
        fragmentListener = (TipHistoryListFragmentListener)fragment;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_about){
            about();
        }
        return super.onOptionsItemSelected(item);
    }

    @OnClick(R.id.btnSubmit)
    public void handleClickSubmit(){
        hideKeyboard();
        String inputTotal = inputBill.getText().toString().trim();
        if (!inputTotal.isEmpty()){
            double total = Double.parseDouble(inputTotal);
            int tipPercentage = getTipoPercentage();
            double tip = total*(tipPercentage/100d);

            TipRecord tipRecord = new TipRecord();
            tipRecord.setBill(total);
            tipRecord.setTipPercentage(tipPercentage);
            tipRecord.setTimestamp(new Date() );

            String strTip = String.format(getString(R.string.global_message_tip),
                    tipRecord.getTip());
            fragmentListener.addToList(tipRecord);
            txtTip.setVisibility(View.VISIBLE);
            txtTip.setText(strTip);

        }
    }

    @OnClick(R.id.btnIncrease)
    public void handleClickIncrease(){
        hideKeyboard();
        handleTipChange(TIP_STEP_CHANGE);

    }

    @OnClick(R.id.btnDecrease)
    public void handleClickDecrease(){
        hideKeyboard();
        handleTipChange(-TIP_STEP_CHANGE);
    }

    @OnClick(R.id.btnClear)
    public void handleClickClear(){
        fragmentListener.clearList();
    }

    private void handleTipChange(int change) {
        int tipPercentage = getTipoPercentage();
        tipPercentage += change;
        if (tipPercentage > 0){
            inputPercentage.setText(String.valueOf(tipPercentage));
        }


    }


    private int getTipoPercentage() {
        int tipPrecentage = DEFAULT_TIP_PERCENTAGE;
        String strInputTipPorcentage = inputPercentage.getText().toString().trim();
        if (!strInputTipPorcentage.isEmpty()){
            tipPrecentage = Integer.parseInt(strInputTipPorcentage);
        }else{
            inputPercentage.setText(String.valueOf(tipPrecentage));
        }
        return tipPrecentage;
    }


    private void hideKeyboard() {
        InputMethodManager inputManaget = (InputMethodManager)
                getSystemService(Context.INPUT_METHOD_SERVICE);

        try{

        inputManaget.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),
                InputMethodManager.HIDE_NOT_ALWAYS);
        } catch (NullPointerException npe) {
            Log.e(getLocalClassName(),Log.getStackTraceString(npe));
        }
    }

    private void about() {
        TipCalcApp app = (TipCalcApp)getApplication();
        String strUrl = app.getAboutUrl();

        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(strUrl));
        startActivity(intent);
    }
}
