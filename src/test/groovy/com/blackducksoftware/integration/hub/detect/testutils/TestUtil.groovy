/*
 * Copyright (C) 2017 Black Duck Software Inc.
 * http://www.blackducksoftware.com/
 * All rights reserved.
 *
 * This software is the confidential and proprietary information of
 * Black Duck Software ("Confidential Information"). You shall not
 * disclose such Confidential Information and shall use it only in
 * accordance with the terms of the license agreement you entered into
 * with Black Duck Software.
 */
package com.blackducksoftware.integration.hub.detect.testutils

import java.lang.reflect.Type
import java.nio.charset.StandardCharsets
import java.nio.file.Path

import org.skyscreamer.jsonassert.JSONAssert

import com.blackducksoftware.integration.util.ResourceUtil
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.JsonElement
import com.google.gson.JsonPrimitive
import com.google.gson.JsonSerializationContext
import com.google.gson.JsonSerializer

class TestUtil {
    private final JsonSerializer<Path> serializer = new JsonSerializer<Path>() {
        @Override
        public JsonElement serialize(Path src, Type typeOfSrc, JsonSerializationContext context) {
            String pathString = src.toFile().getCanonicalPath()
            JsonPrimitive jsonPath = new JsonPrimitive(pathString)

            return jsonPath
        }
    }

    private final Gson gson = new GsonBuilder().setPrettyPrinting().registerTypeAdapter(Path, serializer).create()

    void printJsonObject(Object object) {
        final String jsonifiedObject = gson.toJson(object)
        println jsonifiedObject
    }

    void testJsonResource(String expectedResourcePath, Object object) {
        JsonSerializer<Path> serializer
        final String expected = getResourceAsUTF8String(expectedResourcePath)
        final String actual = gson.toJson(object)
        println actual
        testJson(expected, actual)
    }

    void testJson(String expectedJson, String actualJson) {
        JSONAssert.assertEquals(expectedJson, actualJson, false)
    }

    String getResourceAsUTF8String(String resourcePath) {
        if (resourcePath.startsWith('/')) {
            resourcePath = resourcePath.replaceFirst('/', '')
        }
        String data = ResourceUtil.getResourceAsString(resourcePath, StandardCharsets.UTF_8.toString())
        data.split("\r?\n").join(System.lineSeparator)
    }

    void createExpectedFile(String resourcePath, Object expectedObject) {
        final String expectedJson = gson.toJson(expectedObject)
        final File outputFile = new File('src/test/resources', resourcePath)
        outputFile.delete()
        outputFile << expectedJson
    }
}
