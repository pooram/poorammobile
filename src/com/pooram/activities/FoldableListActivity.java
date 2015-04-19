package com.pooram.activities;

import android.os.Bundle;

import com.pooram.activities.R;
import com.pooram.library.foldablelayout.FoldableListLayout;
import com.pooram.library.foldablelayout.commons.utils.Views;
import com.pooram.library.foldablelayout.items.PaintingsAdapter;

public class FoldableListActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_foldable_list);

        FoldableListLayout foldableListLayout = Views.find(this, R.id.foldable_list);
        foldableListLayout.setAdapter(new PaintingsAdapter(this));
    }

}
