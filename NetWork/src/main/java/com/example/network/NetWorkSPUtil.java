package com.example.network;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.text.TextUtils;
import android.util.Base64;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.io.StreamCorruptedException;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by lfh on 2016/8/13.
 */
public class NetWorkSPUtil {

    private static NetWorkSPUtil prefsUtil;
    public Context context;
    public SharedPreferences prefs;
    public SharedPreferences.Editor editor;

    public synchronized static NetWorkSPUtil getInstance() {
        return prefsUtil;
    }

    public static void init(Context context, String prefsname, int mode) {
        prefsUtil = new NetWorkSPUtil();
        prefsUtil.context = context;
        prefsUtil.prefs = prefsUtil.context.getSharedPreferences(prefsname, mode);
        prefsUtil.editor = prefsUtil.prefs.edit();
    }

    private NetWorkSPUtil() {
    }


    public boolean getBoolean(String key, boolean defaultVal) {
        return this.prefs.getBoolean(key, defaultVal);
    }

    public boolean getBoolean(String key) {
        return this.prefs.getBoolean(key, false);
    }


    public String getString(String key, String defaultVal) {
        return this.prefs.getString(key, defaultVal);
    }

    public String getString(String key) {
        return this.prefs.getString(key, "");
    }

    public int getInt(String key, int defaultVal) {
        return this.prefs.getInt(key, defaultVal);
    }

    public int getInt(String key) {
        return this.prefs.getInt(key, 0);
    }


    public float getFloat(String key, float defaultVal) {
        return this.prefs.getFloat(key, defaultVal);
    }

    public float getFloat(String key) {
        return this.prefs.getFloat(key, 0f);
    }

    public long getLong(String key, long defaultVal) {
        return this.prefs.getLong(key, defaultVal);
    }

    public long getLong(String key) {
        return this.prefs.getLong(key, 0l);
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    public Set<String> getStringSet(String key, Set<String> defaultVal) {
        return this.prefs.getStringSet(key, defaultVal);
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    public Set<String> getStringSet(String key) {
        return this.prefs.getStringSet(key, null);
    }

    public Map<String, ?> getAll() {
        return this.prefs.getAll();
    }

    public boolean exists(String key) {
        return prefs.contains(key);
    }


    public boolean putString(String key, String value) {
        editor.putString(key, value);
        return editor.commit();
    }

    public NetWorkSPUtil putInt(String key, int value) {
        editor.putInt(key, value);
        editor.commit();
        return this;
    }

    public NetWorkSPUtil putFloat(String key, float value) {
        editor.putFloat(key, value);
        editor.commit();
        return this;
    }

    public NetWorkSPUtil putLong(String key, long value) {
        editor.putLong(key, value);
        editor.commit();
        return this;
    }

    public NetWorkSPUtil putBoolean(String key, boolean value) {
        editor.putBoolean(key, value);
        editor.commit();
        return this;
    }

    public void commit() {
        editor.commit();
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    public NetWorkSPUtil putStringSet(String key, Set<String> value) {
        editor.putStringSet(key, value);
        editor.commit();
        return this;
    }

    public void putObject(String key, Object object) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream out = null;
        try {
            out = new ObjectOutputStream(baos);
            out.writeObject(object);
            String objectVal = new String(Base64.encode(baos.toByteArray(), Base64.DEFAULT));
            editor.putString(key, objectVal);
            editor.commit();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (baos != null) {
                    baos.close();
                }
                if (out != null) {
                    out.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public <T> T getObject(String key, Class<T> clazz) {
        if (prefs.contains(key)) {
            String objectVal = prefs.getString(key, null);
            byte[] buffer = Base64.decode(objectVal, Base64.DEFAULT);
            ByteArrayInputStream bais = new ByteArrayInputStream(buffer);
            ObjectInputStream ois = null;
            try {
                ois = new ObjectInputStream(bais);
                T t = (T) ois.readObject();
                return t;
            } catch (StreamCorruptedException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } finally {
                try {
                    if (bais != null) {
                        bais.close();
                    }
                    if (ois != null) {
                        ois.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    /**
     * ??????List??????
     *
     * @param context ?????????
     * @param list    ???????????????
     */
    public  void putSearchHisList(Context context, List<? extends Serializable> list) {
        try {
            put(context, "search_his_list", list);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * ??????List??????
     *
     * @param context ?????????
     * @param <E>     ????????????
     * @return List??????
     */
    public  <E extends Serializable> List<E> getSearchHisList(Context context) {
        try {
            return (List<E>) get(context, "search_his_list");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * ??????List??????
     *
     * @param context ?????????
     * @param list    ???????????????
     */
    public  void putReadingPlanSearchHisList(Context context, List<? extends Serializable> list) {
        try {
            put(context, "readingplan_search_his_list", list);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * ??????List??????
     *
     * @param context ?????????
     * @param <E>     ????????????
     * @return List??????
     */
    public  <E extends Serializable> List<E> getReadingPlanSearchHisList(Context context) {
        try {
            return (List<E>) get(context, "readingplan_search_his_list");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * ??????List??????
     *
     * @param context ?????????
     * @param list    ???????????????
     */
    public  void putVideoSearchHisList(Context context, List<? extends Serializable> list) {
        try {
            put(context, "video_search_his_list", list);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * ??????List??????
     *
     * @param context ?????????
     * @param <E>     ????????????
     * @return List??????
     */
    public  <E extends Serializable> List<E> getVideoSearchHisList(Context context) {
        try {
            return (List<E>) get(context, "video_search_his_list");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * ????????????
     */
    private void put(Context context, String key, Object obj)
            throws IOException {
        if (obj == null) {//????????????????????????
            return;
        }
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = null;
        oos = new ObjectOutputStream(baos);
        oos.writeObject(obj);
        // ???????????????OutputStream???
        // ??????????????????byte????????????????????????base64??????
        String objectStr = new String(Base64.encode(baos.toByteArray(), Base64.DEFAULT));
        baos.close();
        oos.close();

        putString(context, key, objectStr);
    }

    /**
     * ????????????
     */
    private Object get(Context context, String key)
            throws IOException, ClassNotFoundException {
        String wordBase64 = getString(context, key);
        // ???base64????????????????????????byte??????
        if (TextUtils.isEmpty(wordBase64)) { //?????????????????????????????????java.io.StreamCorruptedException
            return null;
        }
        byte[] objBytes = Base64.decode(wordBase64.getBytes(), Base64.DEFAULT);
        ByteArrayInputStream bais = new ByteArrayInputStream(objBytes);
        ObjectInputStream ois = new ObjectInputStream(bais);
        // ???byte???????????????product??????
        Object obj = ois.readObject();
        bais.close();
        ois.close();
        return obj;
    }

    /**
     * ???????????????
     *
     * @param context ?????????
     * @param key     ???????????????
     * @return ??????????????????
     */
    public  String getString(Context context, String key) {
        SharedPreferences preferences = context.getSharedPreferences("SpUtil", Context.MODE_PRIVATE);
        return preferences.getString(key, "");
    }

    /**
     * ???????????????
     *
     * @param context ?????????
     * @param key     ???????????????
     * @param value   ???????????????
     */
    public  void putString(Context context, String key, String value) {
        SharedPreferences preferences = context.getSharedPreferences("SpUtil", Context.MODE_PRIVATE);
        //????????????
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(key, value);
        editor.commit();
    }


    public void putToken(Context context, String value){
        putString(context,Constant.USER_ACCESS_TOKEN,value);
    }

    public String getToken(){
        return getString(Constant.USER_ACCESS_TOKEN);
    }

    public NetWorkSPUtil remove(String key) {
        editor.remove(key);
        editor.commit();
        return this;
    }

    public NetWorkSPUtil removeAll() {
        editor.clear();
        editor.commit();
        return this;
    }

}
