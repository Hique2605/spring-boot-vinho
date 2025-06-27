package com.example.lucasappvinho.utils;

import android.app.Activity;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.lucasappvinho.R;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.util.List;

public class BottomSheetHelper {

    public static void showBottomSheet(Activity activity, String title, List<Pair<String, String>> fields) {
        BottomSheetDialog bottomSheet = new BottomSheetDialog(activity);
        View view = LayoutInflater.from(activity).inflate(R.layout.bottomsheet_global, null);

        TextView txtTitle = view.findViewById(R.id.txtTitle);
        LinearLayout container = view.findViewById(R.id.containerFields);

        txtTitle.setText(title);

        for (Pair<String, String> field : fields) {
            TextView textView = new TextView(activity);
            textView.setLayoutParams(new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT));
            textView.setText(String.format("%s: %s", field.first, field.second));
            textView.setPadding(0, 8, 0, 8);
            container.addView(textView);
        }

        view.findViewById(R.id.btnClose).setOnClickListener(v -> bottomSheet.dismiss());

        bottomSheet.setContentView(view);
        bottomSheet.show();
    }
}
