package com.reprezen.jsonoverlay.parser;

import static com.fasterxml.jackson.core.JsonToken.FIELD_NAME;

import java.io.IOException;
import java.io.Reader;
import java.util.Map;

import com.fasterxml.jackson.core.JsonLocation;
import com.fasterxml.jackson.core.JsonPointer;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.core.io.IOContext;
import com.fasterxml.jackson.core.util.BufferRecycler;
import com.fasterxml.jackson.dataformat.yaml.YAMLParser;
import com.reprezen.jsonoverlay.PositionInfo;

public class LocationRecorderYamlParser extends YAMLParser {

	private final LocationProcessor processor = new LocationProcessor();

	public LocationRecorderYamlParser(IOContext ctxt, BufferRecycler br, int parserFeatures, int formatFeatures,
			ObjectCodec codec, Reader reader) {
		super(ctxt, br, parserFeatures, formatFeatures, codec, reader);
	}

	public Map<JsonPointer, PositionInfo> getLocations() {
		return processor.getLocations();
	}

	@Override
	public JsonToken nextToken() throws IOException {
		JsonToken token = super.nextToken();
		String fieldName = token == FIELD_NAME ? getCurrentName() : null;
		JsonLocation tokenStart = getTokenLocation();
		JsonLocation tokenEnd = getCurrentLocation();
		processor.processTokenLocation(token, fieldName, tokenStart, tokenEnd);
		return token;
	}
}
