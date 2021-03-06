/*
 * Copyright (c) 2017 Zhang Hai <Dreaming.in.Code.ZH@Gmail.com>
 * All Rights Reserved.
 */

package me.zhanghai.android.douya.network.api.info.frodo;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.reflect.TypeToken;

/**
 * Only for use with Gson deserialization.
 */
public class CompleteCollectableItem {

    private CompleteCollectableItem() {}

    public static class Deserializer implements JsonDeserializer<CollectableItem> {

        @Override
        public CollectableItem deserialize(JsonElement json, java.lang.reflect.Type typeOfT,
                                           JsonDeserializationContext context)
                throws JsonParseException {
            java.lang.reflect.Type type = null;
            CollectableItem.Type itemType = CollectableItem.Type.ofApiString(
                    json.getAsJsonObject().get("type").getAsString());
            if (itemType != null) {
                switch (itemType) {
//                case APP:
//
//                case BOOK:
//
//                case EVENT:
//
//                case GAME:
//
                    case MOVIE:
                    case TV:
                        type = new TypeToken<Movie>() {}.getType();
//                case MUSIC:
//                    return context.deserialize(json, new TypeToken<Music>() {}.getClass());
                }
            }
            if (type == null) {
                type = new TypeToken<UnknownCollectableItem>() {}.getType();
            }
            return context.deserialize(json, type);
        }
    }
}
