package com.gavin.pushnotification.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

public class PreferencesUtils
{
	Context context;
	SharedPreferences mPreferences;

	public static PreferencesUtils getPreferencesUtils(Context context, String preferenceName)
	{
		PreferencesUtils preferencesUtils = new PreferencesUtils(context, preferenceName);
		return preferencesUtils;
	}

	private PreferencesUtils(Context context, String preferenceName)
	{
		this.context = context;
		getPreferenceByName(preferenceName);
	}

	/**
	 * 获取SharedPreferences
	 * 
	 * @param name
	 * @return
	 */
	public SharedPreferences getPreferenceByName(String preferenceName)
	{
		mPreferences = context.getSharedPreferences(preferenceName, Context.MODE_PRIVATE);
		return mPreferences;
	}

	/**
	 * 将�?写入Preference�?字符�?
	 * 
	 * @param keyName
	 * @param value
	 */
	public void setValueToPreference(String keyName, String value)
	{
		Editor editor = mPreferences.edit();
		editor.putString(keyName, value);
		editor.commit();
	}

	/**
	 * 将�?写入Preference�?数字)
	 * 
	 * @param keyName
	 * @param value
	 */
	public void setIntValueToPreference(String keyName, int value)
	{
		Editor editor = mPreferences.edit();
		editor.putInt(keyName, value);
		editor.commit();
	}

	/**
	 * 获取字符串�?
	 * 
	 * @param key
	 * @param defValue
	 * @return
	 */
	public String getStringValueByKeyName(String key, String defValue)
	{
		return mPreferences.getString(key, defValue);
	}

	/**
	 * 获取整数�?	 * 
	 * @param key
	 * @param defValue
	 * @return
	 */
	public int getIntValueByKey(String key, int defValue)
	{
		return mPreferences.getInt(key, defValue);
	}
}
