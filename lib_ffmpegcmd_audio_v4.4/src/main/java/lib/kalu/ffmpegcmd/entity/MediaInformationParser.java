package lib.kalu.ffmpegcmd.entity;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import lib.kalu.ffmpegcmd.util.LogUtil;

public class MediaInformationParser {

    /**
     * Extracts MediaInformation from the given ffprobe json output.
     *
     * @param ffprobeJsonOutput ffprobe json output
     * @return created {@link MediaInformation} instance of null if a parsing error occurs
     */
    public static MediaInformation from(final String ffprobeJsonOutput) {
        try {
            return fromWithError(ffprobeJsonOutput);
        } catch (JSONException e) {
            LogUtil.e("MediaInformation parsing failed.", e);
            return null;
        }
    }

    /**
     * Extracts MediaInformation from the given ffprobe json output.
     *
     * @param ffprobeJsonOutput ffprobe json output
     * @return created {@link MediaInformation} instance
     * @throws JSONException if a parsing error occurs
     */
    public static MediaInformation fromWithError(final String ffprobeJsonOutput) throws JSONException {
        JSONObject jsonObject = new JSONObject(ffprobeJsonOutput);
        JSONArray streamArray = jsonObject.optJSONArray("streams");

        ArrayList<StreamInformation> arrayList = new ArrayList<>();
        for (int i = 0; streamArray != null && i < streamArray.length(); i++) {
            JSONObject streamObject = streamArray.optJSONObject(i);
            if (streamObject != null) {
                arrayList.add(new StreamInformation(streamObject));
            }
        }

        return new MediaInformation(jsonObject, arrayList);
    }

}
