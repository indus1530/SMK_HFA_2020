package edu.aku.hassannaqvi.smk_hfa_2020.ui.sections;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.validatorcrawler.aliazaz.Validator;

import org.json.JSONException;
import org.json.JSONObject;

import edu.aku.hassannaqvi.smk_hfa_2020.R;
import edu.aku.hassannaqvi.smk_hfa_2020.contracts.FormsContract;
import edu.aku.hassannaqvi.smk_hfa_2020.core.DatabaseHelper;
import edu.aku.hassannaqvi.smk_hfa_2020.core.MainApp;
import edu.aku.hassannaqvi.smk_hfa_2020.databinding.ActivitySectionG2Binding;
import edu.aku.hassannaqvi.smk_hfa_2020.utils.JSONUtils;

import static edu.aku.hassannaqvi.smk_hfa_2020.core.MainApp.fc;
import static edu.aku.hassannaqvi.smk_hfa_2020.utils.UtilKt.openSectionMainActivity;

public class SectionG2Activity extends AppCompatActivity {

    ActivitySectionG2Binding bi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bi = DataBindingUtil.setContentView(this, R.layout.activity_section_g2);
        bi.setCallback(this);
    }


    private boolean UpdateDB() {
        DatabaseHelper db = MainApp.appInfo.getDbHelper();
        int updcount = db.updatesFormColumn(FormsContract.FormsTable.COLUMN_SG, fc.getsG());
        if (updcount == 1) {
            return true;
        } else {
            Toast.makeText(this, "Sorry. You can't go further.\n Please contact IT Team (Failed to update DB)", Toast.LENGTH_SHORT).show();
            return false;
        }
    }


    private void SaveDraft() throws JSONException {

        JSONObject json = new JSONObject();

        json.put("g0201", bi.g0201a.isChecked() ? "1"
                : bi.g0201b.isChecked() ? "2"
                : bi.g0201c.isChecked() ? "3"
                : "-1");

        json.put("g0202", bi.g0202a.isChecked() ? "1"
                : bi.g0202b.isChecked() ? "2"
                : bi.g0202c.isChecked() ? "3"
                : "-1");

        json.put("g0203", bi.g0203a.isChecked() ? "1"
                : bi.g0203b.isChecked() ? "2"
                : "-1");
        json.put("g0203x", bi.g0203x.getText().toString().trim().isEmpty() ? "-1" : bi.g0203x.getText().toString());

        json.put("g0204", bi.g0204a.isChecked() ? "1"
                : bi.g0204b.isChecked() ? "2"
                : bi.g0204c.isChecked() ? "3"
                : "-1");

        json.put("g0205", bi.g0205a.isChecked() ? "1"
                : bi.g0205b.isChecked() ? "2"
                : "-1");

        json.put("g0206", bi.g0206a.isChecked() ? "1"
                : bi.g0206b.isChecked() ? "2"
                : "-1");

        json.put("g0207", bi.g0207a.isChecked() ? "1"
                : bi.g0207b.isChecked() ? "2"
                : bi.g0207c.isChecked() ? "3"
                : "-1");

        json.put("g0208a", bi.g0208a.isChecked() ? "1" : "-1");
        json.put("g0208b", bi.g0208b.isChecked() ? "2" : "-1");
        json.put("g0208c", bi.g0208c.isChecked() ? "3" : "-1");
        json.put("g0208d", bi.g0208d.isChecked() ? "4" : "-1");
        json.put("g0208e", bi.g0208e.isChecked() ? "5" : "-1");

        try {
            JSONObject json_merge = JSONUtils.mergeJSONObjects(new JSONObject(MainApp.fc.getsG()), json);

            MainApp.fc.setsG(String.valueOf(json_merge));

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }


    private boolean formValidation() {
        return Validator.emptyCheckingContainer(this, bi.GrpName);
    }


    public void BtnContinue() {
        if (!formValidation()) return;
        try {
            SaveDraft();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        if (UpdateDB()) {
            finish();
            startActivity(new Intent(this, SectionG311Activity.class));
        }
    }


    public void BtnEnd() {
        openSectionMainActivity(this, "G");
    }


    @Override
    public void onBackPressed() {
        Toast.makeText(this, "Back Press Not Allowed", Toast.LENGTH_SHORT).show();
    }

}
