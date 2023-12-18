package com.aichatbot.singles.date.aiask.askai.datingcube;

import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.RecyclerView;

public class CustomItemAnimator extends DefaultItemAnimator {

    @Override
    public boolean animateAdd(RecyclerView.ViewHolder holder) {
        // Implement your custom add animation logic here
        return super.animateAdd(holder);
    }

    @Override
    public boolean animateRemove(RecyclerView.ViewHolder holder) {
        // Implement your custom remove animation logic here
        return super.animateRemove(holder);
    }

    // Override other animation methods as needed
}

