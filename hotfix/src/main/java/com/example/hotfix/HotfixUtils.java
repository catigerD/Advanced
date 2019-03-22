package com.example.hotfix;

import android.content.Context;

import com.example.tools.FileUtils;
import com.example.tools.ReflectUtils;

import java.io.File;
import java.lang.reflect.Array;

import dalvik.system.DexClassLoader;
import dalvik.system.PathClassLoader;

/**
 * Created by dengchong on 19-3-22.
 */
public class HotfixUtils {

    private static final String DEX_DIR = "o_dex";
    private static final String OPTIMIZED_DIR = "o_optimized";
    private static final String FIELD_DEX_PATH_LIST = "pathList";
    private static final String FIELD_DEX_ELEMENTS = "dexElements";
    private static final String DEX_SUFFIX = ".dex";

    public static final void fix(Context context) {
        //获取fix Element[]
        String absolutePath = context.getExternalFilesDir(null).getAbsolutePath();
        String dexPathDir = absolutePath + File.separatorChar + DEX_DIR;
        String optimizedDirectory = absolutePath + File.separatorChar + OPTIMIZED_DIR;
        StringBuilder dexPath = new StringBuilder();
        FileUtils.createOrExistsDir(dexPathDir);
        File fileByPath = FileUtils.getFileByPath(dexPathDir);
        File[] files = fileByPath.listFiles();
        for (File file : files) {
            if (file.getName().endsWith(DEX_SUFFIX)) {
                dexPath.append(file.getAbsolutePath() + File.pathSeparator);
            }
        }
        FileUtils.createOrExistsDir(optimizedDirectory);
        DexClassLoader fixClassLoader = new DexClassLoader(dexPath.toString(), optimizedDirectory,
                null, context.getClassLoader());
        Object fixDexPathList = ReflectUtils.reflect(fixClassLoader).field(FIELD_DEX_PATH_LIST).get();
        Object fixElements = ReflectUtils.reflect(fixDexPathList).field(FIELD_DEX_ELEMENTS).get();
        //获取原app Element[]
        PathClassLoader pathClassLoader = (PathClassLoader) context.getClassLoader();
        Object oldDexPathList = ReflectUtils.reflect(pathClassLoader).field(FIELD_DEX_PATH_LIST).get();
        Object oldElements = ReflectUtils.reflect(oldDexPathList).field(FIELD_DEX_ELEMENTS).get();
        //合并 Element[]
        Object newElements = mergeElements(fixElements, oldElements);
        //赋值 Element[]
        ReflectUtils.reflect(oldDexPathList).field(FIELD_DEX_ELEMENTS, newElements);
    }

    private static Object mergeElements(Object fixElements, Object oldElements) {
        if (!fixElements.getClass().isArray()) {
            return oldElements;
        }
        Class<?> componentType = fixElements.getClass().getComponentType();
        int fixLen = Array.getLength(fixElements);
        int oldLen = Array.getLength(oldElements);

        Object newElements = Array.newInstance(componentType, fixLen + oldLen);
        int k = 0;
        for (int i = 0; i < fixLen; i++, k++) {
            Array.set(newElements, k, Array.get(fixElements, i));
        }
        for (int i = 0; i < oldLen; i++, k++) {
            Array.set(newElements, k, Array.get(oldElements, i));
        }
        return newElements;
    }
}
