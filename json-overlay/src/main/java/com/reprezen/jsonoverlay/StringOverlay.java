/*********************************************************************
*  Copyright (c) 2017 ModelSolv, Inc. and others.
*
* This program and the accompanying materials are made
* available under the terms of the Eclipse Public License 2.0
* which is available at https://www.eclipse.org/legal/epl-2.0/
*
* SPDX-License-Identifier: EPL-2.0
 *
 *  Contributors:
 *     ModelSolv, Inc. 
 *     - initial API and implementation and/or initial documentation
**********************************************************************/
package com.reprezen.jsonoverlay;

import com.fasterxml.jackson.databind.JsonNode;

public final class StringOverlay extends ScalarOverlay<String> {

	private StringOverlay(JsonNode json, JsonOverlay<?> parent, ReferenceManager refMgr) {
		super(json, parent, factory, refMgr);
	}

	private StringOverlay(String value, JsonOverlay<?> parent, ReferenceManager refMgr) {
		super(value, parent, factory, refMgr);
	}

	@Override
	protected String _fromJson(JsonNode json) {
		return json.isTextual() ? json.textValue() : null;
	}

	@Override
	protected JsonNode _toJsonInternal(SerializationOptions options) {
		return value != null ? _jsonScalar(value) : _jsonMissing();
	}

	@Override
	protected OverlayFactory<String> _getFactory() {
		return factory;
	}

	@Override
	public String toString() {
		// we don't want quotes here; the default rendering uses toJson, which does
		// include them.
		return value != null ? value : "";
	}

	public static OverlayFactory<String> factory = new OverlayFactory<String>() {

		@Override
		protected Class<StringOverlay> getOverlayClass() {
			return StringOverlay.class;
		}

		@Override
		public StringOverlay _create(String value, JsonOverlay<?> parent, ReferenceManager refMgr) {
			return new StringOverlay(value, parent, refMgr);
		}

		@Override
		public StringOverlay _create(JsonNode json, JsonOverlay<?> parent, ReferenceManager refMgr) {
			return new StringOverlay(json, parent, refMgr);
		}

	};

	public static Builder<String> builder(JsonOverlay<?> modelMember) {
		return new Builder<String>(factory, modelMember);
	}

	public static JsonOverlay<String> create(JsonOverlay<?> modelMember) {
		return builder(modelMember).build();
	}

	public static JsonOverlay<String> create(String value, JsonOverlay<?> modelMember) {
		JsonOverlay<String> result = create(modelMember);
		result._set(value);
		return result;
	}
}
