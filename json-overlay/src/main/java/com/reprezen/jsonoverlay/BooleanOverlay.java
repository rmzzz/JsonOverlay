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

public final class BooleanOverlay extends ScalarOverlay<Boolean> {

	private BooleanOverlay(Boolean value, JsonOverlay<?> parent, ReferenceManager refMgr) {
		super(value, parent, factory, refMgr);
	}

	private BooleanOverlay(JsonNode json, JsonOverlay<?> parent, ReferenceManager refMgr) {
		super(json, parent, factory, refMgr);
	}

	@Override
	protected Boolean _fromJson(JsonNode json) {
		return json.isBoolean() ? json.booleanValue() : null;
	}

	@Override
	protected JsonNode _toJsonInternal(SerializationOptions options) {
		return value != null ? _jsonBoolean(value) : _jsonMissing();
	}

	@Override
	protected OverlayFactory<Boolean> _getFactory() {
		return factory;
	}

	public static OverlayFactory<Boolean> factory = new OverlayFactory<Boolean>() {
		@Override
		protected Class<BooleanOverlay> getOverlayClass() {
			return BooleanOverlay.class;
		}

		@Override
		public BooleanOverlay _create(Boolean value, JsonOverlay<?> parent, ReferenceManager refMgr) {
			return new BooleanOverlay(value, parent, refMgr);
		}

		@Override
		public BooleanOverlay _create(JsonNode json, JsonOverlay<?> parent, ReferenceManager refMgr) {
			return new BooleanOverlay(json, parent, refMgr);
		}
	};

	public static Builder<Boolean> builder(JsonOverlay<?> modelMember) {
		return new Builder<Boolean>(factory, modelMember);
	}

	public static JsonOverlay<Boolean> create(JsonOverlay<?> modelMember) {
		return builder(modelMember).build();
	}

	public static JsonOverlay<Boolean> create(boolean value, JsonOverlay<?> modelMember) {
		JsonOverlay<Boolean> result = create(modelMember);
		result._set(value);
		return result;
	}
}