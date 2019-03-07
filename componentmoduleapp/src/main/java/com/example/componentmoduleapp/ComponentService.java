package com.example.componentmoduleapp;

import android.content.Context;
import android.content.Intent;

import com.example.componentlibrary.IComponentInterface;

/**
 * Created by dengchong on 19-3-7.
 */
public class ComponentService implements IComponentInterface {
    @Override
    public void launch(Context context) {
        Intent intent = new Intent(context, ModuleActivity.class);
        context.startActivity(intent);
    }
}
