package edu.aku.hassannaqvi.smk_hfa_2020.ui.other;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.google.android.material.badge.BadgeDrawable;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Locale;

import edu.aku.hassannaqvi.smk_hfa_2020.R;
import edu.aku.hassannaqvi.smk_hfa_2020.contracts.FormsContract;
import edu.aku.hassannaqvi.smk_hfa_2020.core.DatabaseHelper;
import edu.aku.hassannaqvi.smk_hfa_2020.core.MainApp;
import edu.aku.hassannaqvi.smk_hfa_2020.databinding.ActivitySectionMainBinding;
import edu.aku.hassannaqvi.smk_hfa_2020.ui.sections.SectionAActivity;
import edu.aku.hassannaqvi.smk_hfa_2020.ui.sections.SectionBActivity;
import edu.aku.hassannaqvi.smk_hfa_2020.ui.sections.SectionC1Activity;
import edu.aku.hassannaqvi.smk_hfa_2020.ui.sections.SectionD1Activity;
import edu.aku.hassannaqvi.smk_hfa_2020.ui.sections.SectionE101Activity;
import edu.aku.hassannaqvi.smk_hfa_2020.ui.sections.SectionF1Activity;
import edu.aku.hassannaqvi.smk_hfa_2020.ui.sections.SectionG1Activity;
import edu.aku.hassannaqvi.smk_hfa_2020.ui.sections.SectionH16Activity;
import edu.aku.hassannaqvi.smk_hfa_2020.ui.sections.SectionH1Activity;
import edu.aku.hassannaqvi.smk_hfa_2020.ui.sections.SectionI1Activity;
import edu.aku.hassannaqvi.smk_hfa_2020.ui.sections.SectionJ1Activity;
import edu.aku.hassannaqvi.smk_hfa_2020.ui.sections.SectionK1Activity;
import edu.aku.hassannaqvi.smk_hfa_2020.utils.JSONUtils;

import static edu.aku.hassannaqvi.smk_hfa_2020.core.MainApp.fc;

public class SectionMainActivity extends AppCompatActivity {
    public static int countC2 = 0;
    ActivitySectionMainBinding bi;
    boolean flag = false;

    @SuppressLint("RestrictedApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bi = DataBindingUtil.setContentView(this, R.layout.activity_section_main);
        bi.setCallback(this);

        if (countC2 != 0 && !flag) {

            JSONObject json = new JSONObject();

            try {
                json.put("countC2", String.valueOf(countC2));
            } catch (JSONException e) {
                e.printStackTrace();
            }
            try {
                JSONObject json_merge = JSONUtils.mergeJSONObjects(new JSONObject(fc.getsC()), json);
                fc.setsC(String.valueOf(json_merge));
            } catch (JSONException e) {
                e.printStackTrace();
            }

            DatabaseHelper db = MainApp.appInfo.getDbHelper();
            db.updatesFormColumn(FormsContract.FormsTable.COLUMN_SC, MainApp.fc.getsC());
            Toast.makeText(this, "countC2: 0" + countC2, Toast.LENGTH_SHORT).show();
        }

        if (fc.getsB() != null) {
            bi.form01.setEnabled(false);
            bi.form01.setBackgroundResource(R.color.dullWhite);
        }

        if (fc.getsC() != null || fc.getA10().equals("2")) {
            bi.form02.setEnabled(false);
            bi.form02.setBackgroundResource(R.color.dullWhite);
            flag = true;
        }

        if (fc.getsD() != null) {
            bi.form03.setEnabled(false);
            bi.form03.setBackgroundResource(R.color.dullWhite);
        }

        if (fc.getsE() != null) {
            bi.form04.setEnabled(false);
            bi.form04.setBackgroundResource(R.color.dullWhite);
        }

        if (fc.getsF() != null || fc.getA10().equals("2")) {
            bi.form05.setEnabled(false);
            bi.form05.setBackgroundResource(R.color.dullWhite);
        }

        if (fc.getsG() != null || fc.getA10().equals("2")) {
            bi.form06.setEnabled(false);
            bi.form06.setBackgroundResource(R.color.dullWhite);
        }

        if (fc.getsH() != null) {
            bi.form07.setEnabled(false);
            bi.form07.setBackgroundResource(R.color.dullWhite);
        }

        BadgeDrawable badgeDrawable = BadgeDrawable.create(this);
        badgeDrawable.setNumber(SectionAActivity.countI);
        bi.fldGrpForm08.setForeground(badgeDrawable);
        bi.fldGrpForm08.addOnLayoutChangeListener((v, left, top, right, bottom, oldLeft, oldTop, oldRight, oldBottom) -> {
//            BadgeUtils.attachBadgeDrawable(badgeDrawable, bi.form08Lbl, bi.fldGrpForm08);
            badgeDrawable.updateBadgeCoordinates(bi.form08Lbl, bi.fldGrpForm08);
        });
        if (flagSelectionForSecI()) {
            bi.form08.setEnabled(false);
            bi.form08.setBackgroundResource(R.color.dullWhite);
        }

        if (fc.getsJ() != null) {
            bi.form09.setEnabled(false);
            bi.form09.setBackgroundResource(R.color.dullWhite);
        }

        if (fc.getsK() != null) {
            bi.form10.setEnabled(false);
            bi.form10.setBackgroundResource(R.color.dullWhite);
        }

        bi.form08.setText(bi.form08.getText().toString().concat(String.format(Locale.ENGLISH, " [ Conducted %d interviews ]", SectionAActivity.countI)));
        bi.lblMainMenu.setText((fc.getA10().equals("1") ? "Public" : "Private").concat(" Health Facility Modules"));

    }


    public void openForm(View v) {
        OpenFormFunc(v.getId());
    }


    public void BtnContinue() {
        if (!bi.form01.isEnabled()
                && !bi.form02.isEnabled()
                && !bi.form03.isEnabled()
                && !bi.form04.isEnabled()
                && !bi.form05.isEnabled()
                && !bi.form06.isEnabled()
                && !bi.form07.isEnabled()
                && SectionAActivity.countI > 0
                && !bi.form09.isEnabled()
                && !bi.form10.isEnabled()) {
            finish();
            startActivity(new Intent(this, EndingActivity.class).putExtra("complete", true));
        } else {
            Toast.makeText(this, "Sections still in Pending!", Toast.LENGTH_SHORT).show();
        }
    }


    public void BtnEnd() {
        if (bi.form01.isEnabled()
                || bi.form02.isEnabled()
                || bi.form03.isEnabled()
                || bi.form04.isEnabled()
                || bi.form05.isEnabled()
                || bi.form06.isEnabled()
                || bi.form07.isEnabled()
                || bi.form08.isEnabled()
                || bi.form09.isEnabled()
                || bi.form10.isEnabled()) {
            finish();
            startActivity(new Intent(this, EndingActivity.class).putExtra("complete", false));
            //MainApp.endActivity(this, EndingActivity.class).putExtra("complete", false);
        } else {
            Toast.makeText(this, "ALL SECTIONS FILLED \n Good to GO GREEN!", Toast.LENGTH_SHORT).show();
        }

    }


    private void OpenFormFunc(int id) {
        Intent oF = new Intent();
        if (!MainApp.userName.equals("0000")) {
            switch (id) {
                case R.id.form01:
                    oF = new Intent(this, SectionBActivity.class);
                    break;
                case R.id.form02:
                    oF = new Intent(this, SectionC1Activity.class);
                    break;
                case R.id.form03:
                    oF = new Intent(this, SectionD1Activity.class);
                    break;
                case R.id.form04:
                    oF = new Intent(this, SectionE101Activity.class);
                    break;
                case R.id.form05:
                    oF = new Intent(this, SectionF1Activity.class);
                    break;
                case R.id.form06:
                    oF = new Intent(this, SectionG1Activity.class);
                    break;
                case R.id.form07:
                    oF = new Intent(this, fc.getA10().equals("2") ? SectionH16Activity.class : SectionH1Activity.class);
                    break;
                case R.id.form08:
                    oF = new Intent(this, SectionI1Activity.class);
                    break;
                case R.id.form09:
                    oF = new Intent(this, SectionJ1Activity.class);
                    break;
                case R.id.form10:
                    oF = new Intent(this, SectionK1Activity.class);
                    break;
            }
            startActivity(oF);
        } else {
            Toast.makeText(this, "Please login Again!", Toast.LENGTH_LONG).show();
        }
    }


    @Override
    public void onBackPressed() {
        Toast.makeText(this, "Back Press Not Allowed", Toast.LENGTH_SHORT).show();
    }

    private Boolean flagSelectionForSecI() {
        if (MainApp.fc.getA10().equals("1") && SectionAActivity.countI == 4)
            return true;
        else return MainApp.fc.getA10().equals("2") && SectionAActivity.countI == 2;
    }
}
