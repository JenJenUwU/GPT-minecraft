package com.jenjenuwu.gptminecraft.config;

import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;
import me.shedaniel.autoconfig.serializer.GsonConfigSerializer;
import me.shedaniel.cloth.clothconfig.shadowed.blue.endless.jankson.Comment;

@Config(name = "gpt-minecraft")
public class ModConfig implements ConfigData {


    public static void init() {
        AutoConfig.register(ModConfig.class, GsonConfigSerializer::new);
    }

    @Comment("OpenAI Api key, can be obtained from https://platform.openai.com/account/api-keys")
    private String apiKey = "";

    public String getApiKey() {
        return apiKey;
    }
}